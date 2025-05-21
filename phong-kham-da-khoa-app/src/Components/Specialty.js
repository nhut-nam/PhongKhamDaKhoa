import React from "react";
import "../Styles/Specialty.css";

const Specialty = ({id, benhVien, chuyenKhoa}) => {
    return (
        <div className="specialty">

            <div class="results">
                <div class="result-column">
                    <div class="result-card">
                        <div class="icon ghi">{benhVien.tenBenhVien[0] + chuyenKhoa.tenChuyenKhoa[0]}</div>
                        <div class="info">
                            <h3>{chuyenKhoa.tenChuyenKhoa}</h3>
                            <p>{benhVien.tenBenhVien} <span class="verified">✔</span></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
};

export default Specialty;
