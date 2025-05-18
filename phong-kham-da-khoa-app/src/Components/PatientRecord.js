import React from "react";
import "../Styles/PatientRecord.css";
import { Link } from "react-router-dom";

const PatientRecord = () => {
  return (
    <div className="container-home">
      <div className="sidebar">
        <div className="breadcrumb">
          <span className="breadcrumb-item">Trang chủ</span>
          <span className="breadcrumb-separator"> &gt; </span>
          <span className="breadcrumb-current">Hồ sơ bệnh nhân</span>
        </div>
        <Link to="/tao-ho-so" className="add-button">
          <span className="icon">😊</span> Thêm hồ sơ
        </Link>
        <div className="menu">
          <div className="menu-item">Hồ sơ bệnh nhân</div>
          <div className="menu-item">Phiếu khám bệnh</div>
          <div className="menu-item notification">
            Thông báo <span className="badge">99+</span>
          </div>
        </div>
      </div>
      <div className="main">
        <h2>Danh sách hồ sơ bệnh nhân</h2>
        <p className="empty-message">
          Bạn chưa có hồ sơ bệnh nhân. Vui lòng tạo mới hồ sơ để được đặt khám.
        </p>
        <div className="empty-image">
          <img
            src="https://cdn-icons-png.flaticon.com/512/2748/2748558.png"
            alt="empty"
          />
        </div>
      </div>
    </div>
  );
};

export default PatientRecord;
