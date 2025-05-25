import React, { useEffect, useState } from 'react';
import { Bar } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  BarElement,
  CategoryScale,
  LinearScale,
  Title,
  Tooltip,
  Legend
} from 'chart.js';
import { authApis, endpoints } from '../Configs/Apis';

ChartJS.register(
  BarElement,
  CategoryScale,
  LinearScale,
  Title,
  Tooltip,
  Legend
);

const ThongKeBenhNhan = () => {
  const currentYear = new Date().getFullYear();
  const [nam, setNam] = useState(currentYear);
  const [loaiThongKe, setLoaiThongKe] = useState('YEAR');
  const [loaiThongKeChinh, setLoaiThongKeChinh] = useState('BENH_NHAN');
  const [duLieuThongKe, setDuLieuThongKe] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [noData, setNoData] = useState(false);

  const handleThongKe = async () => {
    setLoading(true);
    setError('');
    setNoData(false);
    try {
      let res;
       if (loaiThongKeChinh === 'BENH_NHAN') {
      res = await authApis().get(endpoints['getThongKeBenhNhan'], {
        params: { nam, loaiThongKe }
      });
    } else if (loaiThongKeChinh === 'LOAI_BENH') {
      res = await authApis().get(endpoints['getThongKeLoaiBenh'], {
        params: { nam, loaiThongKe }
      });
    }
      
      const data = res.data;
      console.log("test" +data)
      if (!Array.isArray(data) || data.length === 0) {
        setNoData(true);
      }
      setDuLieuThongKe(data);
    } catch (err) {
      setError('Không thể lấy dữ liệu thống kê. Vui lòng thử lại sau.');
      console.error('Lỗi khi gọi API:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    handleThongKe();
  }, [nam, loaiThongKe, loaiThongKeChinh]);

  const labels = duLieuThongKe.map(item => {
    if (loaiThongKeChinh === 'BENH_NHAN') {
      return item.thoiGian || '';
    } else if (loaiThongKeChinh === 'LOAI_BENH') {
      const tg = item.thoiGian || '';
      const cd = item.chuanDoan || '';
      return tg && cd ? `${tg} - ${cd}` : cd || tg || '';
    }
    return '';
  });

  const values = duLieuThongKe.map(item => item.soLuong || 0);

  const data = {
    labels: labels,
    datasets: [
      {
        label:  loaiThongKeChinh === 'BENH_NHAN'
            ? 'Số lượng bệnh nhân'
            : 'Loại bệnh',
        data: values,
        backgroundColor: 'rgba(75, 192, 192, 0.6)',
        borderRadius: 5
      }
    ]
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top'
      },
      title: {
        display: true,
        text: loaiThongKeChinh === 'BENH_NHAN'
            ? 'Thống kê số lượng bệnh nhân'
            : 'Thống kê loại bệnh phổ biến'
      }
    }
  };

  return (
    <div style={{ padding: '20px', maxHeight: '80vh', overflowY: 'auto' }}>
<div
  style={{
    marginBottom: '16px',
    display: 'flex',
    alignItems: 'center',
    gap: '16px',
    flexWrap: 'wrap'
  }}
>
  <div style={{ display: 'flex', alignItems: 'center' }}>
          <label style={{ minWidth: '60px' }}>Năm:</label>
          <input
            type="number"
            value={nam}
            onChange={e => setNam(Number(e.target.value))}
            style={{ padding: '4px', width: '100px' }}
          />
        </div>

        <div style={{ display: 'flex', alignItems: 'center' }}>
          <label style={{ minWidth: '140px' }}>Loại thống kê thời gian:</label>
          <select
            value={loaiThongKe}
            onChange={e => setLoaiThongKe(e.target.value)}
            style={{ padding: '4px', width: '150px' }}
            disabled={loaiThongKeChinh === 'CHAN_DOAN'} // tắt chọn khi thống kê theo chẩn đoán
          >
            <option value="YEAR">Theo năm</option>
            <option value="QUARTER">Theo quý</option>
            <option value="MONTH">Theo tháng</option>
          </select>
        </div>

        <div style={{ display: 'flex', alignItems: 'center' }}>
          <label style={{ minWidth: '100px' }}>Loại thống kê:</label>
          <select
            value={loaiThongKeChinh}
            onChange={e => setLoaiThongKeChinh(e.target.value)}
            style={{ padding: '4px', width: '220px' }}
          >
            <option value="BENH_NHAN">Số lượng bệnh nhân đã khám</option>
            <option value="LOAI_BENH">Số lượng bệnh nhân theo chẩn đoán</option>
          </select>
        </div>
      </div>

      {loading && <p>⏳ Đang tải dữ liệu thống kê...</p>}

      {error && <p style={{ color: 'red' }}>❌ {error}</p>}

      {noData && !loading && !error && <p>⚠️ Không có dữ liệu thống kê.</p>}

      {!loading && !error && !noData && (
        <div style={{ maxWidth: '100%', overflowX: 'auto' }}>
          <Bar data={data} options={options} />
        </div>
      )}
    </div>
  );
};

export default ThongKeBenhNhan;