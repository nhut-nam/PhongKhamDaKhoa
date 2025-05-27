import React, { use, useEffect, useRef, useState } from 'react';
import '../Styles/Search.css';
import Specialty from './Specialty';
import { data, useNavigate, useSearchParams } from 'react-router-dom';
import Apis, { authApis, endpoints } from '../Configs/Apis';
import MedicalFacilityCard from './MedicalFacilityCard';
import { sprintf } from 'sprintf-js';
import DoctorCard from './DoctorCard';
import cookie from 'react-cookies';

const Search = () => {
    const nav = useNavigate();
    const [q] = useSearchParams();
    const [kwInput, setKwInput] = useState(q.get('kw'));
    const [list, setList] = useState({
        doctor: {
            'endpoint': endpoints['getDoctorList'] +`?kw=%s&page=%s`,
            data: []
        },
        facility: {
            'endpoint': endpoints['getBenhVien'] +`?tenBenhVien=%s&page=%s`,
            data: []
        },
        specialty: {
            'endpoint': endpoints['getBenhVienChuyenKhoa'] +`?kw=%s&page=%s`,
            data: []
        }
    });
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(1);
    const [kw, setKw] = useState(q.get('kw'));
    const timeoutRef = useRef(null);
    const changeTab = (type) => {
        nav(`/tim-kiem?type=${type}&kw=${kw}`);
        setPage(1);
    }

    const fetchData = async (type) => {
        try {
            console.log(sprintf(list[type].endpoint, kw, page));
            const params = { trangThai: 'KICH_HOAT' };
            const token = cookie.load('token');
            const res = await Apis.get(sprintf(list[type].endpoint, kw, page));
            setList((prev) => ({ ...prev, [type]: { ...prev[type], data: res.data } }));
        } catch (error) {
            console.error(`Error fetching ${type} data:`, error);
        }
    }
    useEffect(() => {
        const fetchAllData = async () => {
            try {
                setLoading(true);
                await Promise.all([
                    fetchData('doctor'),
                    fetchData('facility'),
                    fetchData('specialty')
                ]);
            } catch (error) {
                console.error("Error fetching data:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchAllData();
    }, [kw, page]);

    const handleChange = (e) => {
        const value = e.target.value;
        setKwInput(value);

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
            <div className="search-bar-container">
                <div className="search-bar-result">
                    <input onChange={handleChange} value={kwInput} type="text" placeholder="Tìm kiếm theo bác sĩ, chuyên khoa và bệnh viện" />
                </div>
                <div className="filters">
                    <button onClick={() => changeTab('facility')} className={(q.get('type') === 'facility' || q.get("type") == null) ? 'active' : ''}>Cơ sở y tế ({list.facility.data.length})</button>
                    <button onClick={() => changeTab('doctor')} className={q.get('type') === 'doctor' ? 'active' : ''}>Bác sĩ ({list.doctor.data.length})</button>
                    <button onClick={() => changeTab('specialty')} className={q.get('type') === 'specialty' ? 'active' : ''}>Chuyên khoa ({list.specialty.data.length})</button>
                </div>
            </div>
            <div className="search-results">
                {(q.get('type') === 'facility' || q.get("type") === null) && (
                    list.facility.data.map((item, index) => (
                        <MedicalFacilityCard key={index} {...item} />
                    ))
                )}
                {(q.get('type') === 'specialty' || q.get("type") === null) && (
                    list.specialty.data.map((item, index) => (
                        <Specialty key={index} {...item} />
                    ))
                )}
                {(q.get('type') === 'doctor' || q.get("type") === null) && (
                    list.doctor.data.map((item, index) => (
                        <DoctorCard key={index} {...item} />
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
                    disabled={list[q.get('type') != null ? q.get('type') : 'facility'].data.length === 0}
                >
                    Trang sau
                </button>
            </div>
        </div>
    );
};

export default Search;
