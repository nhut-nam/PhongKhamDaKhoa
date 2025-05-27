import React, { useState } from 'react';
import LichKham from './DoctorSchedule';
import '../Styles/DoctorDashboard.css';
import ThongKeBenhNhan from './DoctorStats';
import HoSoBenhNhan from './DoctorProfileBenhNhan';

const DoctorDashboard = () => {
  const [selectedSection, setSelectedSection] = useState('lichkham');

  const renderSection = () => {
    switch (selectedSection) {
      case 'lichkham':
        return (
          <div className="section-box">
            <h2>Tất cả lịch khám</h2>
            <LichKham />
          </div>
        );
      case 'thongke':
        return (
          <div className="section-box">
            <h2>Thống kê</h2>
            <ThongKeBenhNhan />
          </div>
        );
      case 'hoso':
        return (
          <div className="section-box">
            <h2>Hồ sơ bệnh nhân</h2>
            <HoSoBenhNhan />
          </div>
        );
      default:
        return null;
    }
  };

  return (
    <div className="dashboard-container">
      <div className="sidebar">
        <h2>👨‍⚕️ Bác sĩ</h2>
        <ul>
          <li onClick={() => setSelectedSection('lichkham')}>📅 Lịch khám</li>
          <li onClick={() => setSelectedSection('thongke')}>📊 Thống kê</li>
          <li onClick={() => setSelectedSection('hoso')}>📝 Hồ sơ</li>
        </ul>
      </div>
      <div className="main-content">
        <h1>Dashboard Bác sĩ</h1>
        {renderSection()}
      </div>
    </div>
  );
};

export default DoctorDashboard;
