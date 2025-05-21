import React, { useState } from 'react';
import '../Styles/BookingPage.css';

const dataDoctors = [
  { name: 'BS. A', specialty: 'Nội tổng quát', hospital: 'BV A' },
  { name: 'BS. B', specialty: 'Da liễu', hospital: 'BV A' },
  { name: 'BS. C', specialty: 'Nội tổng quát', hospital: 'BV B' },
  { name: 'BS. D', specialty: 'Tâm thần', hospital: 'BV C' },
];

const BookingPage = () => {
  const [specialty, setSpecialty] = useState('');
  const [hospital, setHospital] = useState('');
  const [doctor, setDoctor] = useState('');

  const specialties = [...new Set(dataDoctors.map(d => d.specialty))];
  const hospitals = [...new Set(dataDoctors.map(d => d.hospital))];

  const filteredDoctors = dataDoctors.filter(d =>
    (!specialty || d.specialty === specialty) &&
    (!hospital || d.hospital === hospital)
  );

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Đặt khám với ${doctor || '[Chưa chọn bác sĩ]'}\nChuyên khoa: ${specialty}\nBệnh viện: ${hospital}`);
  };

  const change = (e) => {
    
  }

  return (
    <div className="booking-container">
      <h2>Đặt lịch khám</h2>
      <form className="booking-form" onSubmit={handleSubmit}>
        <label>Chuyên khoa</label>
        <select value={specialty} onChange={e => setSpecialty(e.target.value)}>
          <option value="">-- Tất cả --</option>
          {specialties.map((s, i) => (
            <option key={i} value={s}>{s}</option>
          ))}
        </select>

        <label>Bệnh viện</label>
        <select value={hospital} onChange={e => setHospital(e.target.value)}>
          <option value="">-- Tất cả --</option>
          {hospitals.map((h, i) => (
            <option key={i} value={h}>{h}</option>
          ))}
        </select>

        <label>Bác sĩ</label>
        <select value={doctor} onChange={e => setDoctor(e.target.value)}>
          <option value="">-- Chọn bác sĩ --</option>
          {filteredDoctors.map((d, i) => (
            <option key={i} value={d.name}>{d.name}</option>
          ))}
        </select>

        <button type="submit">Đặt lịch</button>
      </form>
    </div>
  );
};

export default BookingPage;
