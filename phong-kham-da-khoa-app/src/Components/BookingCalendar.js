import React, { useState } from 'react';
import '../Styles/BookingCalendar.css';

const BookingCalendar = () => {
  const today = new Date();
  const [selectedDate, setSelectedDate] = useState('');

  const handleChange = (e) => {
    const date = new Date(e.target.value);
    const day = date.getDay();

    if (date < today.setHours(0, 0, 0, 0)) {
      alert('Không thể chọn ngày trong quá khứ.');
      return;
    }

    if (day === 0 || day === 6) {
      alert('Không thể đặt lịch vào thứ 7 hoặc Chủ nhật.');
      return;
    }

    setSelectedDate(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!selectedDate) return alert('Vui lòng chọn ngày hợp lệ!');
    alert(`Bạn đã đặt lịch vào ngày: ${selectedDate}`);
  };

  const minDate = new Date().toISOString().split('T')[0];

  return (
    <div className="calendar-container">
      <h2>Đặt lịch khám</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="date">Chọn ngày:</label>
        <input
          type="date"
          id="date"
          value={selectedDate}
          onChange={handleChange}
          min={minDate}
        />
        <button type="submit">Xác nhận đặt lịch</button>
      </form>
    </div>
  );
};

export default BookingCalendar;
