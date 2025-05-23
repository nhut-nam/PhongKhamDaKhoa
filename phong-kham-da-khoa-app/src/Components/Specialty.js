import React from "react";
import "../Styles/Specialty.css";

const Specialty = ({id, benhVien, chuyenKhoa}) => {
    return (
        <div className="specialty">

            <div className="results">
                <div className="result-column">
                    <div className="result-card">
                        <div className="icon ghi">{benhVien.tenBenhVien[0] + chuyenKhoa.tenChuyenKhoa[0]}</div>
                        <div className="info">
                            <h3>{chuyenKhoa.tenChuyenKhoa}</h3>
                            <p>{benhVien.tenBenhVien} <span className="verified">✔</span></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
};

export default Specialty;
