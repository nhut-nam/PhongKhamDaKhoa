import React from "react";
import "../Styles/MedicalExamination.css";
import { useState } from "react";
import Apis, { endpoints } from "../Configs/Apis";
import { Link } from "react-router-dom";

const tabs = [
    { key: "paid", label: "Đã thanh toán" },
    { key: "unpaid", label: "Chưa thanh toán" },
    { key: "done", label: "Đã khám" },
    { key: "cancelled", label: "Đã huỷ" },
    { key: "absent", label: "Vắng mặt" },
];

const MedicalExamination = () => {
    const [activeTab, setActiveTab] = useState("paid")
    const [medicalExaminationList, setMedicalExaminationList] = useState([
        { id: 1, hoTen: "Nguyễn Văn A", trangThai: "paid" },
        { id: 2, hoTen: "Trần Thị B", trangThai: "unpaid" },
        { id: 3, hoTen: "Lê Văn C", trangThai: "done" },
        { id: 4, hoTen: "Phạm Thị D", trangThai: "cancelled" },
    ]);

    const handleDelete = async (id) => {
        try {
            // Call your API to delete the record
            await Apis.delete(endpoints["deleteHoSo"] + "/" + id);
            // Update the state to remove the deleted record
            setMedicalExaminationList(medicalExaminationList.filter(item => item.id !== id));
            alert("Xóa hồ sơ thành công");
        } catch (error) {
            console.error("Lỗi:", error);
        }
    }
    return (
        <>
            <h2 className="title">Danh sách phiếu khám bệnh</h2>
            <div className="tabs">
                {tabs.map((tab) => (
                    <button
                        key={tab.key}
                        onClick={() => setActiveTab(tab.key)}
                        className={`tab-button ${activeTab === tab.key ? "active" : ""
                            }`}
                    >
                        {tab.label}
                    </button>
                ))}
            </div>

            <div className="no-data">
                {medicalExaminationList.length > 0 ? (
                    medicalExaminationList
                        .filter((item) => item.trangThai === activeTab)
                        .map((item, index) => (
                            <div className="record-list" key={index}>
                                <div className="record-container">
                                    <div className="info-section">
                                        <span className="info-label">Họ và tên:</span>
                                        <span className="highlight">{item.hoTen}</span>
                                    </div>

                                    <div className="info-section">
                                        <span className="info-label">Ngày sinh:</span>
                                        <span>{new Date(item.ngaySinh).toLocaleDateString('vi-VN')}</span>
                                    </div>

                                    <div className="info-section">
                                        <span className="info-label">Số điện thoại:</span>
                                        <span>{item.soDienThoai}</span>
                                    </div>

                                    <div className="info-section">
                                        <span className="info-label">Giới tính:</span>
                                        <span className="highlight">{item.gioiTinh === false ? "Nam" : "Nữ"}</span>
                                    </div>

                                    <div className="info-section">
                                        <span className="info-label">Địa chỉ:</span>
                                        <span>{item.diaChi}</span>
                                    </div>
                                    <div className="record-button">
                                        <button onClick={() => handleDelete(item.id)} className="btn-primary delete-link">Xóa hồ sơ</button>
                                        <Link to={`/tao-ho-so?id=${item.id}`} className="btn-primary">Sửa hồ sơ</Link>
                                    </div>
                                </div>
                            </div>
                        ))
                ) : (
                    <>
                        <p className="no-data-text">Bạn chưa có phiếu khám nào</p>
                        <img
                            src="/no-data-illustration.png"
                            alt="No tickets"
                            className="no-data-image"
                        />
                    </>
                )}
            </div>
        </>
    );
};

export default MedicalExamination;
