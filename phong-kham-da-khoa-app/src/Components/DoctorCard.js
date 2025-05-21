import React from 'react';
import '../Styles/DoctorCard.css';

const DoctorCard = ({ hoNguoiDung, tenNguoiDung, chuyenTri, diaChi, avatar, benhVien }) => {
  return (
    <div className="doctor-card">
      <img src={avatar} alt={tenNguoiDung} className="doctor-image" />
      <div className="doctor-info">
        <h2 className="doctor-name">{hoNguoiDung + ' ' + tenNguoiDung}</h2>
        <p><strong>Chuyên trị:</strong> {chuyenTri}</p>
        <p><strong>Địa chỉ:</strong> {diaChi}</p>
        <p><strong>Bệnh viện:</strong> {benhVien.tenBenhVien}</p>
        <button className="book-button">Đặt ngay</button>
      </div>
    </div>
  );
};

export default DoctorCard;
