import React, { useEffect, useState } from 'react';
import '../Styles/SelectSpecialty.css';
import { FaHospitalAlt, FaSearch } from 'react-icons/fa';
import { IoReturnUpBack } from 'react-icons/io5';
import Apis, { endpoints } from '../Configs/Apis';
import { sprintf } from 'sprintf-js';
import { Link, useLocation, useNavigate, useSearchParams } from 'react-router-dom';
import BookingCalendar from './BookingCalendar';

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
  const [searchParam, setSearchParam] = useSearchParams();
  const [specializedHospital, setSpecializedHospital] = useState([])
  const [facility, setFacility] = useState();
  const [serviceId, setServiceId] = useState()
  const [services, setServices] = useState([])
  const nav = useNavigate();

  useEffect(() => {
    const fetchFacility = async () => {
      const benhVienId = searchParam.get('benhVienId');
      const chuyenKhoaId = searchParam.get('chuyenKhoaId');
      if (benhVienId) {
        const url = sprintf(endpoints['getBenhVienChuyenKhoaByBenhVienId'], benhVienId)
        const res = await Apis.get(url);
        setSpecializedHospital(res.data);
        setFacility(res.data[0].benhVien)
        console.log(res.data)
      }
      if (serviceId) {
        const res = await Apis.get(endpoints['getDichVu']+`/${serviceId}`);
        setServices(res.data)
        console.log(res.data)
      }
    };
    fetchFacility();
  }, [searchParam]);

  const updateParam = (s) => {
    searchParam.set("chuyenKhoaId", s.chuyenKhoa.id)
    setSearchParam(searchParam)
    setServiceId(s.id)
  }

  return (
    <div className="select-specialty-container">
      <div className="hospital-info">
        <h3><FaHospitalAlt /> {facility && facility.tenBenhVien} <span className="verified">✔</span></h3>
        <p>{facility && facility.diaChi}</p>
      </div>

      <div className="specialty-panel">
        <div className="panel-header">Vui lòng chọn chuyên khoa</div>
        <div className="search-bar-select-specialty">
          <input
            type="text"
            placeholder="Tìm nhanh chuyên khoa"
          // value={searchTerm}
          // onChange={e => setSearchTerm(e.target.value)}
          />
          <FaSearch className="search-icon" />
        </div>
        {serviceId ? <div className="service-list">
          {services.map((s, i) => (
            <div className="service-item" key={i}>
              <strong>{s.tenDichVu}</strong>
            </div>
          ))}
        </div> : <div className="specialty-list">
          {specializedHospital && specializedHospital.map((s, i) => (
            <div onClick={() => updateParam(s)} className="specialty-item" key={i}>
              <strong>{s.chuyenKhoa.tenChuyenKhoa}</strong>
            </div>
          ))}
        </div>}

        <div className="back-button">
          <IoReturnUpBack /> Quay lại
        </div>
      </div>
    </div>
  );
};

export default SelectSpecialty;
