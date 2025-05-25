import React, { useContext, useEffect } from "react";
import "../Styles/MedicalExamination.css";
import { useState } from "react";
import Apis, { endpoints } from "../Configs/Apis";
import { Link } from "react-router-dom";
import { MyUserContext } from "../Configs/MyContexts";

const tabs = [
    { key: "DA_THANH_TOAN", label: "Đã thanh toán" },
    { key: "CHUA_THANH_TOAN", label: "Chưa thanh toán" },
    { key: "DA_KHAM_XONG", label: "Đã khám" },
    { key: "DA_HUY", label: "Đã huỷ" },
    { key: "VANG_MAT", label: "Vắng mặt" },
];

const MedicalExamination = () => {
    const [activeTab, setActiveTab] = useState("DA_THANH_TOAN")
    const [medicalExaminationList, setMedicalExaminationList] = useState([]);
    const user = useContext(MyUserContext);

    useEffect(() => {
        const fetchMedicalExaminationList = async () => {
            try {
                const res = await Apis.get(endpoints['getLichKham'] + `/${user.user.id}`)
                setMedicalExaminationList(res.data)
            } catch (error) {
                console.error("Lỗi: ", error);
            }
        }
        fetchMedicalExaminationList()
    }, [medicalExaminationList])

    const canCancel = (ngayTao) => {
        if (!ngayTao) return false;
        const now = new Date();
        const created = new Date(ngayTao);
        const diffHours = (now - created) / (1000 * 60 * 60);
        return diffHours <= 48;
    };

    const handleCancel = async (item) => {
        try {
            if (canCancel(item.ngayTao)) {
                const res = await Apis.patch(endpoints["suaLichKham"] + "/" + item.id,
                    {
                        "trangThai": "DA_HUY"
                    }
                );
                alert("Hủy lịch khám thành công");
            } else {
                alert("Không thể hủy lịch khám vì đã quá 48 tiếng");
            }
        } catch (error) {
            console.error("Lỗi:", error);
        }
    }

    const handlePaid = async (item) => {
        try {
            if (canCancel(item.ngayTao)) {
                const res = await Apis.patch(endpoints["suaLichKham"] + "/" + item.id,
                    {
                        "trangThai": "DA_THANH_TOAN"
                    }
                );
                alert("Đã thanh toán thành công");
            } else {
                alert("Không thể hủy lịch khám vì đã quá 48 tiếng");
            }
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
                            <div className="me-record-list" key={index}>
                                <div className="me-record-container">
                                    <div className="me-section">
                                        <div className="me-info-section">
                                            <span className="me-info-label">Mã lịch khám:</span>
                                            <span className="me-highlight">{item.id}</span>
                                        </div>
                                        <div className="me-info-row">
                                            <div className="me-info-section">
                                                <span className="me-info-label">Họ và tên:</span>
                                                <span className="me-highlight">{item.hsDTO.hoTen}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Giới tính:</span>
                                                <span className="me-highlight">{item.hsDTO.gioiTinh === false ? "Nam" : "Nữ"}</span>
                                            </div>
                                        </div>
                                        <div className="me-info-row">
                                            <div className="me-info-section">
                                                <span className="me-info-label">Ngày sinh:</span>
                                                <span>{new Date(item.hsDTO.ngaySinh).toLocaleDateString('vi-VN')}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Số điện thoại:</span>
                                                <span>{item.hsDTO.soDienThoai}</span>
                                            </div>
                                        </div>
                                        <div className="me-info-row">
                                            <div className="me-info-section">
                                                <span className="me-info-label">Địa chỉ:</span>
                                                <span>{item.hsDTO.diaChi}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Email:</span>
                                                <span>{item.hsDTO.email}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Ngày khám:</span>
                                                <span>{new Date(item.ngayHen).toLocaleDateString('vi-VN')}</span>
                                            </div>
                                        </div>
                                        <div className="me-divider" />
                                        <div className="me-info-row">
                                            <div className="me-info-section">
                                                <span className="me-info-label">Bệnh viện:</span>
                                                <span>{item?.bvckdvDTO?.ckDTO?.benhVien?.tenBenhVien || "Không có"}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Chuyên khoa:</span>
                                                <span>{item?.bvckdvDTO?.ckDTO?.chuyenKhoa?.tenChuyenKhoa || "Không có"}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Dịch vụ:</span>
                                                <span>{item?.bvckdvDTO?.tenDichVu || "Không có"}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Giá tiền:</span>
                                                <span>{item?.bvckdvDTO?.giaTien || "0"} VNĐ</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="me-divider" />
                                    {activeTab === "CHUA_THANH_TOAN" && <div className="me-button-group">
                                        <button onClick={() => handleCancel(item)} className="me-btn delete">Hủy lịch khám</button>
                                        <button onClick={() => handlePaid(item)} className="me-btn">Thanh toán</button>
                                        <button className="me-btn">Đổi lịch hẹn</button>
                                    </div>}
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
