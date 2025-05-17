import React, { useRef, useState } from "react";
import "../Styles/UserProfile.css";

export default function UserProfile({ user }) {
  const fileInputRef = useRef(null);
  const [avatarPreview, setAvatarPreview] = useState(user.avatar);

  const handleAvatarClick = () => {
    fileInputRef.current.click();
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const previewURL = URL.createObjectURL(file);
      setAvatarPreview(previewURL);
    }
  };
  if (!user) {
    return <div className="profile-wrapper">Loading...</div>;
  }

  return (
    <div className="profile-wrapper">
      <div className="profile-card">
        <div className="profile-header">
          <div className="avatar-wrapper" onClick={handleAvatarClick}>
            <img
              className="profile-avatar"
              src={
                avatarPreview
              }
              alt="Ảnh đại diện"
            />
            <div className="camera-icon">📷</div>
          </div>
          <input
            type="file"
            accept="image/*"
            ref={fileInputRef}
            onChange={handleFileChange}
            style={{ display: "none" }}
          />
          <div className="profile-info">
            <h2 className="profile-name">{user.tenNguoiDung} {user.hoNguoiDung}</h2>
            <p className="profile-email">{user.email}</p>
            <p className="profile-phone">{user.soDienThoai}</p>
          </div>
        </div>

        <div className="profile-details">
          <div className="detail-item">
            <span className="label">Ngày sinh:</span>
            <span>{new Date(user.ngaySinh).toLocaleDateString('vi-VN')}</span>
          </div>
          <div className="detail-item">
            <span className="label">Địa chỉ:</span>
            <span>{user.diaChi || "Chưa cập nhật"}</span>
          </div>
          <div className="detail-item">
            <span className="label">Ghi chú:</span>
            <span>{user.ghiChu || "Không có"}</span>
          </div>
        </div>

        <button className="edit-profile-btn">Chỉnh sửa thông tin</button>
      </div>
    </div>
  );
}
