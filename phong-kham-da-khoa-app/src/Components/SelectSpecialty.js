import React, { useState } from 'react';
import '../Styles/SelectSpecialty.css';
import { FaHospitalAlt, FaSearch } from 'react-icons/fa';
import { IoReturnUpBack } from 'react-icons/io5';

const specialties = [
  {
    name: 'CƠ XƯƠNG KHỚP',
  },
  {
    name: 'DA LIỄU',
  },
  {
    name: 'ĐỘT QUỴ',
  },
  {
    name: 'HÔ HẤP - HỒI SỨC TIM MẠCH',
  }
];

const SelectSpecialty = () => {
  const [searchTerm, setSearchTerm] = useState('');

  const filtered = specialties.filter(s =>
    s.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    s.description.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="select-specialty-container">
      <div className="hospital-info">
        <h3><FaHospitalAlt /> Bệnh viện Nhân Dân 115 <span className="verified">✔</span></h3>
        <p>527 Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh</p>
      </div>

      <div className="specialty-panel">
        <div className="panel-header">Vui lòng chọn chuyên khoa</div>
        <div className="search-bar">
          <input
            type="text"
            placeholder="Tìm nhanh chuyên khoa"
            value={searchTerm}
            onChange={e => setSearchTerm(e.target.value)}
          />
          <FaSearch className="search-icon" />
        </div>

        <div className="specialty-list">
          {filtered.map((s, i) => (
            <div className="specialty-item" key={i}>
              <strong>{s.name}</strong>
            </div>
          ))}
        </div>

        <div className="back-button">
          <IoReturnUpBack /> Quay lại
        </div>
      </div>
    </div>
  );
};

export default SelectSpecialty;
