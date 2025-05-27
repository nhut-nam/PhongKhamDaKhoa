import React, { useContext, useEffect } from "react";
import "../Styles/MedicalExamination.css";
import { useState } from "react";
import Apis, { authApis, endpoints } from "../Configs/Apis";
import { Link } from "react-router-dom";
import { addDays, isSaturday, isSunday } from 'date-fns';
import { MyUserContext } from "../Configs/MyContexts";
import DatePicker from "react-datepicker";
import { Modal, Button, Form } from "react-bootstrap";
import cookie from 'react-cookies';

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
    const [showModal, setShowModal] = useState(false);
    const [selectedDate, setSelectedDate] = useState(null);
    const currentDate = new Date('2025-05-27T14:44:00+07:00');
    const minDateTime = addDays(currentDate, 1);
    const user = useContext(MyUserContext);
    const token = cookie.load("token");

    useEffect(() => {
        const fetchMedicalExaminationList = async () => {
            try {
                const res = await authApis(token).get(endpoints['getLichKham'] + `/${user.user.id}`)
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

    const filterDays = (date) => {
        return !isSaturday(date) && !isSunday(date);
    };

    const handleDateChange = (date) => {
        setSelectedDate(date);
        console.log('Ngày đã chọn:', date.toLocaleString());
    };

    const handleSave = () => {
        if (selectedDate) {
            setShowModal(false);
            console.log('Ngày được lưu:', selectedDate.toLocaleString());
            // Thêm logic gửi ngày về backend ở đây nếu cần
        } else {
            alert('Vui lòng chọn ngày trước khi lưu!');
        }
    };

    const handleCancel = async (item) => {
        try {
            if (canCancel(item.ngayTao)) {
                const res = await authApis(token).patch(endpoints["suaLichKham"] + "/" + item.id,
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
                const res = await authApis(token).patch(endpoints["suaLichKham"] + "/" + item.id,
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
                                            <div className="me-info-section">
                                                <span className="me-info-label">Ngày đặt lịch:</span>
                                                <span>{new Date(item.ngayTao).toLocaleDateString('vi-VN')}</span>
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
                                                <span className="me-info-label">Dịch vụ:</span>
                                                <span>{item?.bvckdvDTO?.loaiDichVu || "Không có"}</span>
                                            </div>
                                            <div className="me-info-section">
                                                <span className="me-info-label">Dịch vụ:</span>
                                                <span>{item?.bvckdvDTO?.loaiThanhToan || "Không có"}</span>
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
                                        {item?.bvckdvDTO?.loaiThanhToan === "THANH_TOAN_TRUC_TUYEN" && <button onClick={() => handlePaid(item)} className="me-btn">Thanh toán</button>}
                                        { <button onClick={() => setShowModal(true)} className="me-btn">Đổi lịch hẹn</button>
                                        /*<Modal show={showModal} onHide={() => setShowModal(false)} centered>
                                            <Modal.Header closeButton>
                                                <Modal.Title>Chọn lịch hẹn</Modal.Title>
                                            </Modal.Header>
                                            <Modal.Body>
                                                <Form>
                                                    <Form.Group className="mb-3">
                                                        <Form.Label>Chọn ngày</Form.Label>
                                                        <DatePicker
                                                            selected={selectedDate}
                                                            onChange={handleDateChange}
                                                            minDate={minDateTime}
                                                            filterDate={filterDays}
                                                            dateFormat="dd/MM/yyyy"
                                                            inline
                                                            placeholderText="Chọn ngày"
                                                        />
                                                    </Form.Group>

                                                    <Form.Group className="mb-3">
                                                        <Form.Label>Chọn buổi</Form.Label>
                                                        <div>
                                                            <Form.Check
                                                                inline
                                                                label="Sáng"
                                                                type="radio"
                                                                name="period"
                                                                value="AM"
                                                                checked={selectedPeriod === 'AM'}
                                                                onChange={handlePeriodChange}
                                                            />
                                                            <Form.Check
                                                                inline
                                                                label="Chiều"
                                                                type="radio"
                                                                name="period"
                                                                value="PM"
                                                                checked={selectedPeriod === 'PM'}
                                                                onChange={handlePeriodChange}
                                                            />
                                                        </div>
                                                    </Form.Group>
                                                </Form>
                                            </Modal.Body>
                                            <Modal.Footer>
                                                <Button variant="secondary" onClick={() => setShowModal(false)}>
                                                    Đóng
                                                </Button>
                                                <Button variant="primary" onClick={handleSave}>
                                                    Lưu
                                                </Button>
                                            </Modal.Footer>
                                        </Modal> */}
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
            </div >
        </>
    );
};

export default MedicalExamination;
