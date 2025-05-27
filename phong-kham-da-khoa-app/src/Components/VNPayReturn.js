import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { Alert, Button, Container } from 'react-bootstrap';
import axios from 'axios';

function VNPayReturn() {
  const [paymentStatus, setPaymentStatus] = useState(null);
  const [transactionInfo, setTransactionInfo] = useState({});
  const location = useLocation();

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const params = Object.fromEntries(queryParams);

    // Gửi dữ liệu về backend để kiểm tra tính toàn vẹn
    const verifyPayment = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/verify-payment', params);
        setPaymentStatus(response.data.status);
        setTransactionInfo({
          orderId: params.vnp_TxnRef,
          amount: params.vnp_Amount / 100, // Chia 100 để lấy giá trị gốc
          transactionNo: params.vnp_TransactionNo,
        });
      } catch (error) {
        setPaymentStatus('error');
        console.error('Lỗi khi kiểm tra thanh toán:', error);
      }
    };

    if (params.vnp_ResponseCode) {
      verifyPayment();
    }
  }, [location]);

  return (
    <Container className="mt-5">
      <h2>Kết quả thanh toán</h2>
      {paymentStatus === 'success' && (
        <Alert variant="success">
          <h4>Thanh toán thành công!</h4>
          <p>Mã đơn hàng: {transactionInfo.orderId}</p>
          <p>Số tiền: {transactionInfo.amount} VND</p>
          <p>Mã giao dịch VNPAY: {transactionInfo.transactionNo}</p>
        </Alert>
      )}
      {paymentStatus === 'error' && (
        <Alert variant="danger">
          <h4>Thanh toán thất bại</h4>
          <p>Vui lòng thử lại hoặc liên hệ hỗ trợ.</p>
        </Alert>
      )}
      {paymentStatus === null && (
        <Alert variant="info">
          <h4>Đang xử lý...</h4>
        </Alert>
      )}
      <Button variant="primary" href="/">
        Quay lại trang chủ
      </Button>
    </Container>
  );
}

export default VNPayReturn;