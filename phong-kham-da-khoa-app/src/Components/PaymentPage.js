import { useState, useEffect } from 'react';
import { Modal, Button, Form, Alert } from 'react-bootstrap';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import '../Styles/PaymentPage.css'; 
import cookies from 'react-cookies';
import { authApis, endpoints } from '../Configs/Apis';

function PaymentPage() {
  const { userId, idLichKham } = useParams();
  const [showModal, setShowModal] = useState(false);
  const [paymentUrl, setPaymentUrl] = useState('');
    const [giaTien, setGiaTien] = useState(0);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const token = cookies.load('token');

  // Thời điểm hiện tại: 09:45 PM +07, 27/05/2025
  const currentDate = new Date('2025-05-27T21:45:00+07:00');
  const minDateTime = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // 24 giờ sau

  // Lấy thông tin lịch khám và giá tiền từ API
  useEffect(() => {
    const fetchAppointmentDetails = async () => {
      try {
        const response = await authApis(token).get(endpoints['getGiaTien'](idLichKham), {
          params: { userId }
        });
        setGiaTien(response.data.giaTien)
        console.log('Thông tin lịch khám:', response.data);
      } catch (err) {
        setError('Không thể tải thông tin lịch khám.');
      }
    };
    fetchAppointmentDetails();
  }, [userId, idLichKham]);

  const handlePayment = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await authApis(token).post(endpoints['createThanhToan'], {
        userId,
        idLichKham,
        amount: giaTien,
        orderInfo: `Thanh toan lich kham ${idLichKham} cho user ${userId}`,
        ipAddr: '127.0.0.1', // Thay bằng IP thực tế từ request
      });
      setPaymentUrl(response.data.paymentUrl);
      window.location.href = response.data.paymentUrl; // Chuyển hướng đến VNPAY
    } catch (err) {
      setError('Lỗi khi tạo yêu cầu thanh toán.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="pm-container mt-5">
      <h2>Thanh toán lịch khám</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <div className="pm-card p-4">
        <h4>Thông tin thanh toán</h4>
        <p><strong>User ID:</strong> {userId}</p>
        <p><strong>ID Lịch khám:</strong> {idLichKham}</p>
        <p><strong>Giá tiền:</strong> {giaTien} VND</p>
        <p><strong>Ngày thanh toán sớm nhất:</strong> {minDateTime.toLocaleString()}</p>
        <Button
          variant="primary"
          onClick={() => setShowModal(true)}
          disabled={!giaTien || loading}
        >
          {loading ? 'Đang xử lý...' : 'Thanh toán ngay'}
        </Button>

        {/* Modal xác nhận */}
        <Modal show={showModal} onHide={() => setShowModal(false)} centered>
          <Modal.Header closeButton>
            <Modal.Title>Xác nhận thanh toán</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>Bạn có chắc chắn muốn thanh toán <strong>{giaTien} VND</strong> cho lịch khám <strong>{idLichKham}</strong>?</p>
            <p>Thời gian thanh toán phải sau <strong>{minDateTime.toLocaleString()}</strong>.</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowModal(false)}>
              Hủy
            </Button>
            <Button variant="primary" onClick={handlePayment}>
              Xác nhận thanh toán
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
}

export default PaymentPage;