import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import '../Styles/BookingSummary.css';

const BookingSummary = () => {

  const navigate = useNavigate();

  const bookingData = [];

  if (!bookingData) {
    return (
      <div className="summary-container">
        <h2>Không có dữ liệu lịch khám</h2>
        <button onClick={() => navigate('/')}>Về trang chủ</button>
      </div>
    );
  }

  const { hospital, specialty, service, date } = bookingData;

  return (
    <div className="summary-container">
      <h2>Thông tin lịch khám của bạn</h2>
      <div className="summary-info">
        <p><strong>Bệnh viện:</strong> {hospital}</p>
        <p><strong>Chuyên khoa:</strong> {specialty}</p>
        <p><strong>Dịch vụ:</strong> {service}</p>
        <p><strong>Ngày khám:</strong> {date}</p>
      </div>
      <button onClick={() => navigate('/')}>Đặt lại</button>
    </div>
  );
};

export default BookingSummary;
