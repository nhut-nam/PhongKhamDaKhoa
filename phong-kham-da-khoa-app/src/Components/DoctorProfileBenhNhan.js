import React, { useState, useEffect, useContext } from 'react';
import { authApis, endpoints } from '../Configs/Apis';
import { useNavigate } from 'react-router-dom';
import { MyUserContext } from '../Configs/MyContexts';

function HoSoBenhNhan() {
  const user = useContext(MyUserContext)
  const navigate = useNavigate();
  const [bacSiId, setBacSiId] = useState(null);
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Hàm lấy thông tin user hiện tại để lấy bacSiId
    async function fetchUser() {
      try {
        const res = await authApis().get(endpoints['current-user']);
        setBacSiId(res.data.id);
      } catch (err) {
        setError("Không lấy được thông tin user hiện tại.");
        setLoading(false);
      }
    }
    fetchUser();
  }, []);

  useEffect(() => {
    if (!bacSiId) return;

    async function fetchData() {
      setLoading(true);
      setError(null);
      try {
        const res = await authApis().get(`${endpoints['getHoSoBenhNhan'](user.user.id)}`);
        setData(res.data);
      } catch (err) {
        setError(err.message || "Lỗi khi gọi API");
      } finally {
        setLoading(false);
      }
    }

    fetchData();
  }, [bacSiId]);

  if (loading) return <p>Đang tải dữ liệu...</p>;
  if (error) return <p>Lỗi: {error}</p>;
  if (!bacSiId) return <p>Chưa đăng nhập hoặc chưa xác định bác sĩ.</p>;
  if (!data || data.length === 0) return <p>Không có hồ sơ bệnh nhân.</p>;

  const formatDate = (ms) => {
    if (!ms) return '';
    return new Date(ms).toLocaleDateString();
  };

  return (
    <div className="section-box">
      <h2>Danh sách hồ sơ bệnh nhân</h2>
      {data.map((hs) => (
        <div key={hs.id} style={{ borderBottom: '1px solid #ccc', marginBottom: 10, paddingBottom: 10 }}>
          <p><b>Họ tên:</b> {hs.hoTen}</p>
          <p><b>Ngày sinh:</b> {formatDate(hs.ngaySinh)}</p>
          <p><b>Địa chỉ:</b> {hs.diaChi}</p>
          <p><b>Email:</b> {hs.email}</p>
          <p><b>Số điện thoại:</b> {hs.soDienThoai}</p>
          <p><b>Giới tính:</b> {hs.gioiTinh ? 'Nam' : 'Nữ'}</p>

          {hs.benhnhanId && (
            <>
              <h4>Thông tin người dùng liên quan:</h4>
              <p><b>Tên người dùng:</b> {hs.benhnhanId.tenNguoiDung}</p>
              <p><b>Địa chỉ:</b> {hs.benhnhanId.diaChi}</p>
              <p><b>Email:</b> {hs.benhnhanId.email}</p>
              <p><b>Trạng thái:</b> {hs.benhnhanId.trangThai}</p>
              {hs.benhnhanId.avatar && (
                <img
                  src={hs.benhnhanId.avatar}
                  alt="Avatar"
                  style={{ width: 100, height: 100, borderRadius: '50%' }}
                />
              )}
            </>
          )}
           <button onClick={() => navigate(`/bac-si/lich-su-kham-benh/${hs.id}`)} style={{ marginTop: 10 }}>
      Cập nhật hồ sơ
    </button>
        </div>
      ))}
      
    </div>
  );
}

export default HoSoBenhNhan;
