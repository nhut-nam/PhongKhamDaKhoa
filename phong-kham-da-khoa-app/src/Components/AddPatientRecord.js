import React, { createContext, use, useContext, useEffect, useState } from 'react';
import '../Styles/AddPatientRecord.css';
import { MyUserContext } from '../Configs/MyContexts';
import Apis, { endpoints } from '../Configs/Apis';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { BiArrowBack } from 'react-icons/bi';

function AddPatientRecord() {
    const user = useContext(MyUserContext);
    // Đổi tên biến trong state cho đồng nhất với backend (camelCase)
    const [p] = useSearchParams();
    const [form, setForm] = useState({
        gioiTinh: '',
        id: '',
        ngaySinh: '',
        diaChi: '',
        email: '',
        hoTen: '',
        soDienThoai: '',
        user_id: user.user.id
    });
    const nav = useNavigate();

    useEffect(() => {
        const getRecord = async (id) => {
            try {
                let response = await Apis.get(endpoints['getHoSo'] + "/" + id);
                setForm({
                    ...form,
                    id: response.data.id,
                    hoTen: response.data.hoTen,
                    soDienThoai: response.data.soDienThoai,
                    gioiTinh: response.data.gioiTinh === false ? 0 : 1,
                    ngaySinh: toDateInputValue(response.data.ngaySinh),
                    diaChi: response.data.diaChi,
                    email: response.data.email
                });
            } catch (error) {
                console.error("Lỗi:", error);
            }
        };
        if (p.get("id") !== null) {
            getRecord(p.get("id"));
            console.log("Kết quả:", form);
        }
    }, []);

    const handleChange = (e) => {
        const { name, value, type, files } = e.target;
        setForm({
            ...form,
            [name]: type === 'file' ? files[0] : value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Dữ liệu gửi:', form);
        const addPatientRecord = async () => {
            try {
                let response = await Apis.post(endpoints['addPatientRecord'],
                    JSON.stringify({
                        ...form
                    })
                    , {
                        headers: {
                            'Content-Type': "application/json",
                        }
                    });
                console.log('Kết quả:', response.data);
                nav("/patient?key=ho-so-benh-nhan");
            } catch (error) {
                console.error('Lỗi:', error);
            }
        };
        const updatePatientRecord = async () => {
            try {
                let response = await Apis.put(endpoints['updateHoSo'],
                    JSON.stringify({
                        ...form
                    })
                    , {
                        headers: {
                            'Content-Type': "application/json",
                        }
                    });
                console.log('Kết quả:', response.data);
                nav("/patient?key=ho-so-benh-nhan");
            } catch (error) {
                console.error('Lỗi:', error);
            }
        }
        if (p.get("id") !== null) {
            updatePatientRecord();
            return;
        }
        addPatientRecord();
    };

    // Hàm chuyển đổi timestamp (ms) sang chuỗi yyyy-MM-dd cho input type="date"
    const toDateInputValue = (timestamp) => {
        if (!timestamp) return '';
        const date = new Date(Number(timestamp));
        // Đảm bảo định dạng yyyy-MM-dd
        const yyyy = date.getFullYear();
        const mm = String(date.getMonth() + 1).padStart(2, '0');
        const dd = String(date.getDate()).padStart(2, '0');
        return `${yyyy}-${mm}-${dd}`;
    };

    return (
        <div className="form-container">
            <button
                className="back-button-icon"
                onClick={() => nav("/patient?key=ho-so-benh-nhan")}
            >
                <BiArrowBack size={22} />
            </button>

            <h2 className="form-title">{p.get("id") ? "Cập nhật hồ sơ bệnh nhân" : "Tạo hồ sơ bệnh nhân"}</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Họ tên:
                    <input
                        type="text"
                        name="hoTen"
                        placeholder="Nhập họ tên"
                        value={form.hoTen}
                        onChange={handleChange}
                        required
                    />
                </label>

                <label>
                    Email:
                    <input
                        type="email"
                        name="email"
                        placeholder="Nhập email để nhận thông báo"
                        value={form.email}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Số điện thoại:
                    <input
                        type="text"
                        name="soDienThoai"
                        placeholder="Nhập số điện thoại"
                        value={form.soDienThoai}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Giới tính:
                    <select
                        name="gioiTinh"
                        value={form.gioiTinh}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Chọn</option>
                        <option value="0">Nam</option>
                        <option value="1">Nữ</option>
                    </select>
                </label>

                <label>
                    Ngày sinh:
                    <input
                        type="date"
                        name="ngaySinh"
                        placeholder="Nhập ngày sinh"
                        value={form.ngaySinh}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Địa chỉ:
                    <input
                        type="text"
                        name="diaChi"
                        placeholder="Nhập địa chỉ"
                        value={form.diaChi}
                        onChange={handleChange}
                    />
                </label>

                <button type="submit">
                    {p.get("id") ? "Cập nhật hồ sơ" : "Tạo hồ sơ"}
                </button>
            </form>
        </div>
    );
}

export default AddPatientRecord;
