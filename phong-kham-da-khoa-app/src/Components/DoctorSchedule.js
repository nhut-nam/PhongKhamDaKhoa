import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Apis, { authApis, endpoints } from '../Configs/Apis';
import dayjs from 'dayjs';
import "../Styles/DoctorSchedule.css";

const LichKham = () => {
  const [lichKham, setLichKham] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchLichKham = async () => {
    try {
      const res = await authApis().get(endpoints['getLichKhamBacSi'])
      console.log(authApis().get(endpoints['getLichKhamBacSi']))
      setLichKham(res.data)
      }
    catch (error) {
      console.error('Lỗi khi lấy lịch khám:', error.response?.data || error.message);

    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchLichKham();
  }, []);

  return (
   <div className="lichkham-container">
  <h2 className="lichkham-title">📅 Lịch khám của bác sĩ</h2>
  {loading ? (
    <p>Đang tải dữ liệu...</p>
  ) : lichKham.length === 0 ? (
    <p>Không có lịch khám nào.</p>
  ) : (
    <div className="card-container">
      {lichKham.map((item, index) => (
        <div key={item.id} className="card">
          <h3>{item.hoTen}</h3>
          <p><strong>Email:</strong> {item.email}</p>
          <p><strong>Điện thoại:</strong> {item.soDienThoai}</p>
          <p><strong>Giới tính:</strong> {item.gioiTinh ? 'Nữ' : 'Nam'}</p>
          <p><strong>Ngày hẹn:</strong> {dayjs(item.ngayHen).format('DD/MM/YYYY HH:mm')}</p>
          <p>
            <strong>Trạng thái:</strong>{' '}
            <span className={item.trangThai === 'DA_THANH_TOAN' ? 'status-true' : 'status-false'}>
             {item.trangThai === 'DA_THANH_TOAN' ? 'Chờ khám' : item.trangThai === 'DA_KHAM_XONG' ? 'Đã khám' : 'Khác'}

            </span>
          </p>
          <p><strong>Buổi:</strong> {item.buoi}</p>
        </div>
      ))}
    </div>
  )}
</div>
  );
};

export default LichKham;
