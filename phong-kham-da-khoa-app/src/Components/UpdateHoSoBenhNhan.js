import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { authApis, endpoints } from '../Configs/Apis';
import HoSoEditForm from './FormEditHoSo';
import '../Styles/CapNhatHoSo.css';

function CapNhatHoSo() {
  const navigate = useNavigate();
  const { hoSoId } = useParams();
  const [hoSo, setHoSo] = useState([]);
  const [error, setError] = useState(null);
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    const fetchHoSo = async () => {
      try {
        const url = endpoints.lichSuKhamBenh.replace('{id}', hoSoId);
        const res = await authApis().get(url);
        const lichSuList = res.data;

        if (lichSuList.length === 0) {
          setError("Không có lịch sử khám bệnh.");
        } else {
          setHoSo(lichSuList);
        }
      } catch (err) {
        setError("Không thể tải thông tin hồ sơ.");
      }
    };

    fetchHoSo();
  }, [hoSoId]);

  const handleEditClick = (item) => {
    setEditingId(item.id);
  };

  const handleCancelEdit = () => {
    setEditingId(null);
  };

  const handleSaveEdit = async (updatedData) => {
    try {
      const url = endpoints.lichSuKhamBenh.replace('{id}', editingId);
      const res = await authApis().put(url, updatedData);

      setHoSo(prev =>
        prev.map(item =>
          item.id === editingId ? { ...item, ...res.data } : item    
        )
      );

      setEditingId(null);
    } catch (err) {
      alert("Cập nhật thất bại, vui lòng thử lại.");
    }
  };

  if (error) return <div className="error-message">{error}</div>;
  if (!hoSo || hoSo.length === 0) return <div className="loading-message">Đang tải dữ liệu...</div>;

  return (
    <div className="capnhat-hoso-container">
      <header className="header">
        <h2 className="page-title">📝 Lịch sử khám bệnh</h2>
        <button className="btn-back" onClick={() => navigate(-1)}>← Quay lại danh sách</button>
      </header>

      <div className="hoso-list">
        {hoSo.map((hoSoItem, idx) => (
          <div key={hoSoItem.id || idx} className="hoso-card">
            <h5 className="hoso-date">🗓️ Khám ngày: {new Date(hoSoItem.ngayKham).toLocaleDateString()}</h5>
            <InfoRow label="👤 Họ tên bệnh nhân:" value={hoSoItem.hosoId?.hoTen || "Không rõ"} />

            {editingId === hoSoItem.id ? (
                  <>
            {console.log("hoSoItem khi mở form:", hoSoItem)} 
              <HoSoEditForm
                initialData={{
                  id:hoSoItem.id,
                  chuanDoan: hoSoItem.chanDoan,
                  ketQuaXetNghiem: hoSoItem.ketQuaXetNghiem,

                }}
                onSave={handleSaveEdit}
                onCancel={handleCancelEdit}
              />
              </>
            ) : (
              <>
                <InfoRow label="🩺 Chẩn đoán:" value={hoSoItem.chanDoan || "Chưa có"} />
                <InfoRow label="🔬 Kết quả xét nghiệm:" value={hoSoItem.ketQuaXetNghiem || "Chưa có"} />
                <button className="btn-edit" onClick={() => handleEditClick(hoSoItem)}>Cập nhật</button>
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

function InfoRow({ label, value }) {
  return (
    <div className="info-row">
      <div className="info-label">{label}</div>
      <div className="info-value">{value}</div>
    </div>
  );
}

export default CapNhatHoSo;
