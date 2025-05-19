import React, { useEffect, useState } from 'react';
import MedicalFacilityCard from './MedicalFacilityCard';
import '../Styles/MedicalFacilityList.css';
import Apis, { endpoints } from '../Configs/Apis';

const MedicalFacilityList = () => {
  const [search, setSearch] = useState('');
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await Apis.get(endpoints['getBenhVien']);
        setData(response.data);
        console.log('Kết quả:', response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);

  const filtered = data.filter(item =>
    item.tenBenhVien && item.tenBenhVien.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="facility-container">
      <h2 className="title">Danh sách cơ sở y tế</h2>
      <input
        className="search-input-faculity"
        placeholder="🔍 Tìm kiếm cơ sở y tế..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <div className="facility-list">
        {filtered.length === 0 ? (
            <div className="no-data">
            <p>Không có cơ sở y tế nào phù hợp với tìm kiếm của bạn.</p>
          </div>
        ): (
          filtered.map((item, index) => (
            <MedicalFacilityCard key={index} {...item} />
          ))
        )}
      </div>
    </div>
  );
};

export default MedicalFacilityList;

