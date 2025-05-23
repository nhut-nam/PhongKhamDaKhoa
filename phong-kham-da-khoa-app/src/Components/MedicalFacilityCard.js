import React from 'react';
import '../Styles/MedicalFacilityCard.css';
import { Link } from 'react-router-dom';

const MedicalFacilityCard = ({id,  logo, tenBenhVien, diaChi }) => {
  return (
    <div className="facility-card">
      <img src={logo} alt={tenBenhVien} className="facility-logo" />
      <div className="facility-info">
        <div className="facility-header">
          <h3 className="facility-name">{tenBenhVien}</h3>
        </div>
        <p className="facility-address">📍 {diaChi}</p>
        <div className="facility-buttons">
          <button className="btn-outline">Xem chi tiết</button>
          <Link to={`/dat-lich-kham`} state={{ chuyenKhoa: { id, logo, tenBenhVien, diaChi }}} className="btn-filled">Đặt khám ngay</Link>
        </div>
      </div>
    </div>
  );
};

export default MedicalFacilityCard;
