import React, { use, useContext, useEffect, useState } from "react";
import "../Styles/PatientRecord.css";
import { MyUserContext } from "../Configs/MyContexts";
import Apis, { endpoints } from "../Configs/Apis";
import { Link } from "react-router-dom";

const PatientRecord = () => {
    const user = useContext(MyUserContext);
    const [record, setRecord] = useState([]);

    useEffect(() => {
        const fetchPatientRecords = async () => {
            const form = new FormData();
            form.append("user_id", user.user.id);
            try {
                let response = await Apis.get(endpoints["getHoSoList"] + "/" + user.user.id)
                setRecord(response.data);
                console.log("Kết quả:", response.data);
            } catch (error) {
                console.error("Lỗi:", error);
            }
        };
        fetchPatientRecords();
    }, []);
    const handleDelete = async (id) => {
        try {
            await Apis.delete(endpoints["deleteHoSo"] + "/" + id);
            setRecord(record.filter(item => item.id !== id));
            alert("Xóa hồ sơ thành công");
        } catch (error) {
            console.error("Lỗi:", error);
        }
    };
    return (
        <div className="card-center">
            <h2>📋 Danh sách hồ sơ bệnh nhân</h2>
            {record.length > 0 ? (
                record.map((item, index) => (
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
                    <p className="empty-message">
                        Bạn chưa có hồ sơ bệnh nhân. Vui lòng tạo mới hồ sơ để được đặt khám.
                    </p>
                    <div className="empty-image">
                        <img
                            src="https://res.cloudinary.com/dhsxutqtc/image/upload/v1747529113/a3771dd9-8341-44a2-86ea-285fe703fac2.png"
                            alt="empty"
                        />
                    </div>
                </>
            )}
        </div>
    );
};

export default PatientRecord;