import React, { useContext, useEffect, useState } from 'react';
import axios from 'axios';
import Apis, { authApis, endpoints } from '../Configs/Apis';
import dayjs from 'dayjs';
import "../Styles/DoctorSchedule.css";
import { MyUserContext } from '../Configs/MyContexts';
import cookie from 'react-cookies';

const PAGE_SIZE = 8;

const LichKham = () => {
  const user = useContext(MyUserContext)
  const [lichKham, setLichKham] = useState([]);
  const [loading, setLoading] = useState(true);
  const [params, setParams] = useState({
    date: '',
    trangThai: '',
    orderBy: '',
    sort: '',
    page: 1,
  });
  const token = cookie.load('token');

  const fetchLichKham = async () => {
    try {
      const queryParams = new URLSearchParams();
      if (params.date) queryParams.append('date', params.date);
      if (params.trangThai) queryParams.append('trangThai', params.trangThai);
      if (params.orderBy) queryParams.append('orderBy', params.orderBy);
      if (params.sort) queryParams.append('sort', params.sort);
      if (params.page) queryParams.append('page', params.page);
      const res = await authApis(token).get(`${endpoints['getLichKhamBacSi'](user.user.id)}?${queryParams.toString()}`);
      setLichKham(res.data);
    } catch (error) {
      console.error('Lỗi khi lấy lịch khám:', error.response?.data || error.message);
    } finally {
      setLoading(false);
    }
  };

  const handleUpdateTrangThai = async (id, newStatus) => {
  if (!window.confirm(`Bạn có chắc muốn cập nhật trạng thái thành "${newStatus}" không?`)) return;

  try {
    await authApis().put(`${endpoints['updateTrangThaiLichKham']}/${id}/trang-thai?trangThai=${newStatus}`);
    alert("Cập nhật thành công!");
    setLichKham(prev =>
      prev.map(item =>
        item.id === id ? { ...item, trangThai: newStatus } : item
      )
    );
  } catch (err) {
    console.error("Lỗi khi cập nhật trạng thái:", err);
    alert("Lỗi khi cập nhật trạng thái");
  }
};


  useEffect(() => {
    setLoading(true);
    fetchLichKham();
  }, [params]);

  return (
    <div className="lichkham-container">
      <h2 className="lichkham-title">📅 Lịch khám của bác sĩ</h2>

      <div className="filters">
        <input
    type="date"
    value={params.date}
    onChange={(e) => setParams(prev => ({ ...prev, date: e.target.value, page: 1 }))}
  />

  <select
    value={params.trangThai}
    onChange={(e) => setParams(prev => ({ ...prev, trangThai: e.target.value, page: 1 }))}
  >
    <option value="">-- Trạng thái --</option>
    <option value="DA_THANH_TOAN">Chờ khám</option>
    <option value="DA_KHAM_XONG">Đã khám</option>
  </select>

  <select
    value={params.orderBy}
    onChange={(e) => setParams(prev => ({ ...prev, orderBy: e.target.value, page: 1 }))}
  >
    <option value="">-- Sắp xếp theo --</option>
    <option value="ngayHen">Ngày hẹn</option>
    <option value="hoTen">Họ tên</option>
  </select>

  <select
    value={params.sort}
    onChange={(e) => setParams(prev => ({ ...prev, sort: e.target.value, page: 1 }))}
  >
    <option value="asc">Tăng dần</option>
    <option value="desc">Giảm dần</option>
  </select>

  <button
    className="btn-reset"
    onClick={() => setParams({ date: '', trangThai: '', orderBy: '', sort: '', page: 1 })}
  >
     Reset bộ lọc
  </button>
      </div>

      {loading ? (
        <p>Đang tải dữ liệu...</p>
      ) : lichKham.length === 0 ? (
        <p>Không có lịch khám nào.</p>
      ) : (
        <>
          <div className="card-container">
            {lichKham&&lichKham.map((item) => (
              <div key={item.id} className="card">
                <h3>{item.hoTen}</h3>
                <p><strong>Email:</strong> {item.email}</p>
                <p><strong>Điện thoại:</strong> {item.soDienThoai}</p>
                <p><strong>Giới tính:</strong> {item.gioiTinh ? 'Nam' : 'Nữ'}</p>
                <p><strong>Ngày hẹn:</strong> {dayjs(item.ngayHen).format('DD/MM/YYYY')}</p>
                <p>
                  <strong>Trạng thái:</strong>{' '}
                  <span className={item.trangThai === 'DA_THANH_TOAN' ? 'status-true' : 'status-false'}>
                    {item.trangThai === 'DA_THANH_TOAN' ? 'Chờ khám' :
                      item.trangThai === 'DA_KHAM_XONG' ? 'Đã khám' : 'Khác'}
                  </span>
                </p>
                <p><strong>Buổi:</strong> {item.buoi}</p>
                {item.trangThai !== "DA_KHAM_XONG" && (
                  <div className="btn-group">
                    <button
                      className="btn btn-success"
                      onClick={() => handleUpdateTrangThai(item.id, "DA_KHAM_XONG")}
                    >
                      ✅ Đã khám
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleUpdateTrangThai(item.id, "VANG_MAT")}
                    >
                      ❌ Vắng mặt
                    </button>
                  </div>
                )}

              </div>
            ))}
          </div>

          <div className="pagination">
            <button
              disabled={params.page === 1}
              onClick={() => setParams({ ...params, page: params.page - 1 })}
            >
              Trang trước
            </button>
            <span>Trang {params.page}</span>
            <button
              disabled={lichKham.length < PAGE_SIZE}
              onClick={() => setParams({ ...params, page: params.page + 1 })}
            >
              Trang sau
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default LichKham;
