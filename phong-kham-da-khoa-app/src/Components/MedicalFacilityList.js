import React, { useEffect, useRef, useState } from 'react';
import MedicalFacilityCard from './MedicalFacilityCard';
import '../Styles/MedicalFacilityList.css';
import Apis, { endpoints } from '../Configs/Apis';

const MedicalFacilityList = () => {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(1);
  const [kw, setKw] = useState('');
  const timeoutRef = useRef(null);

  const fetchData = async () => {
      try {
        let url = `${endpoints['getBenhVien']}?page=${page}&tenBenhVien=${kw}`;
        const response = await Apis.get(url);
        setData(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

  const handleChange = (e) => {
    const value = e.target.value;

    if (timeoutRef.current) {
      clearTimeout(timeoutRef.current);
    }

    timeoutRef.current = setTimeout(() => {
      setKw(value);
      setPage(1);
    }, 300);
  };


  useEffect(() => {
    if (page > 0) {
      fetchData();
    }
  }, [page, kw]);

  return (
    <div className="facility-container">
      <h2 className="title">Danh sách cơ sở y tế</h2>
      <input
        className="search-input-faculity"
        placeholder="🔍 Tìm kiếm cơ sở y tế..."
        // value={kw}
        onChange={handleChange}
      />
      <div className="facility-list">
        {data.length === 0 ? (
          <div className="no-data">
            <p>Không có cơ sở y tế nào phù hợp với tìm kiếm của bạn.</p>
          </div>
        ) : (
          data.map((item, index) => (
            <MedicalFacilityCard key={index} {...item} />
          ))
        )}
      </div>
      <div className="pagination">
        <button
          onClick={() => setPage((prev) => Math.max(prev - 1, 1))}
          disabled={page === 1}
        >
          Trang trước
        </button>
        <span className="page-number">Trang {page}</span>
        <button
          onClick={() => setPage((prev) => prev + 1)}
          disabled={data.length === 0}
        >
          Trang sau
        </button>
      </div>
    </div>
  );
};

export default MedicalFacilityList;

