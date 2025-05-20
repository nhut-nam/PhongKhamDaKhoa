import React, { use, useEffect, useRef, useState } from 'react';
import '../Styles/Search.css';
import Specialty from './Specialty';
import { useNavigate, useSearchParams } from 'react-router-dom';
import Apis, { endpoints } from '../Configs/Apis';
import MedicalFacilityCard from './MedicalFacilityCard';

const Search = () => {
    const nav = useNavigate();
    const [q] = useSearchParams();
    const [doctor, setDoctor] = useState([]);
    const [facility, setFacility] = useState([]);
    const [specialty, setSpecialty] = useState([]);
    const [page, setPage] = useState(1);
    const [kw, setKw] = useState('');
    const timeoutRef = useRef(null);
    const changeTab = (type) => {
        nav(`/tim-kiem?type=${type}`);
    }
    useEffect(() => {
        const fetchAllData = async () => {
            try {
                const resDoctor = await Apis.get(endpoints['getDoctorList'], {
                    params: {
                        kw: q.get('kw')
                    }
                });
                setDoctor(resDoctor.data);
                let url = `${endpoints['getBenhVien']}?page=${page}&tenBenhVien=${kw}`;
                const resFacility = await Apis.get(url);
                setFacility(resFacility.data);
                // if (q.get('type') === 'doctor') {
                //     setDoctor(resDoctor.data);
                // } else if (q.get('type') === 'facility') {
                //     setFacility(res.data);
                // } else if (q.get('type') === 'specialty') {
                //     setSpecialty(res.data);
                // }
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };
        fetchAllData();
    }, []);

    const handleChange = (e) => {
        const value = e.target.value;

        if (timeoutRef.current) {
            clearTimeout(timeoutRef.current);
        }

        timeoutRef.current = setTimeout(() => {
            setKw(value);
            setPage(1);
        }, 300);
    }
    return (
        <div className="search-page">
            <h1 className="title">Kết quả tìm kiếm</h1>

            <div className="search-bar-result">
                <input onChange={handleChange} type="text" placeholder="Tìm kiếm theo bác sĩ, chuyên khoa và bệnh viện" />
            </div>
            <div className="filters">
                <button onClick={() => changeTab('facility')} className={(q.get('type') === 'facility' || q.get("type") == null) ? 'active' : ''}>Cơ sở y tế (60)</button>
                <button onClick={() => changeTab('doctor')} className={q.get('type') === 'doctor' ? 'active' : ''}>Bác sĩ (108)</button>
                <button onClick={() => changeTab('specialty')} className={q.get('type') === 'specialty' ? 'active' : ''}>Chuyên khoa (36)</button>
            </div>
            <div className="search-results">
                {(q.get('type') === 'facility' || q.get("type") === null) && (
                    facility.map((item, index) => (
                        <MedicalFacilityCard key={index} {...item} />
                    ))
                )}
                {q.get('type') === 'specialty' && <Specialty />}
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

export default Search;
