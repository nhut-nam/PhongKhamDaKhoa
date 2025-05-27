import React, { useContext, useState } from 'react';
import LichKham from './DoctorSchedule';
import '../Styles/DoctorDashboard.css';
import ThongKeBenhNhan from './DoctorStats';
import HoSoBenhNhan from './DoctorProfileBenhNhan';
import ChatPageShared from './ChatPageShare';
import { MyUserContext } from '../Configs/MyContexts';

const DoctorDashboard = () => {
  const user = useContext(MyUserContext)
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
        case 'chat':
          return (
          <div className="section-box">
            <h2>Chat với bệnh nhân</h2>
            <ChatPageShared 
            currentUserId={user.user.id}
            userRole="ROLE_DOCTOR" // hoặc "doctor"
            userName={user.user.hoNguoiDung+ ' ' + user.user.tenNguoiDung}
            userAvatar={user.user.avatar}
            />
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
          <li onClick={() => setSelectedSection('chat')}>Chat với bệnh nhân</li>
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
