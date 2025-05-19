import React, { createContext, use, useContext, useEffect, useState } from 'react';
import '../Styles/AddPatientRecord.css';
import { MyUserContext } from '../Configs/MyContexts';
import Apis, { endpoints } from '../Configs/Apis';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { BiArrowBack } from 'react-icons/bi';

function AddPatientRecord() {
    const user = useContext(MyUserContext)
    const [p] = useSearchParams();
    const [form, setForm] = useState({
        gioi_tinh: '',
        id: '',
        ngay_sinh: '',
        dia_chi: '',
        email: '',
        ho_ten: '',
        so_dien_thoai: '',
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
                    ho_ten: response.data.hoTen,
                    so_dien_thoai: response.data.soDienThoai,
                    gioi_tinh: response.data.gioiTinh === false ? 0 : 1,
                    ngay_sinh: response.data.ngaySinh,
                    dia_chi: response.data.diaChi,
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

            <h2 className="form-title">Tạo hồ sơ bệnh nhân</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Họ tên:
                    <input type="text" name="ho_ten" placeholder='Nhập họ tên' value={form.ho_ten} onChange={handleChange} required />
                </label>

                <label>
                    Email:
                    <input type="email" name="email" placeholder='Nhập email để nhận thông báo' value={form.email} onChange={handleChange} />
                </label>

                <label>
                    Số điện thoại:
                    <input type="text" name="so_dien_thoai" placeholder='Nhập số điện thoại' value={form.so_dien_thoai} onChange={handleChange} />
                </label>

                <label>
                    Giới tính:
                    <select name="gioi_tinh" value={form.gioi_tinh} onChange={handleChange} required>
                        <option value="">Chọn</option>
                        <option value="0">Nam</option>
                        <option value="1">Nữ</option>
                    </select>
                </label>

                <label>
                    Ngày sinh:
                    <input
                        type="date"
                        name="ngay_sinh"
                        placeholder='Nhập ngày sinh'
                        value={p.get("id") ? toDateInputValue(form.ngay_sinh) : form.ngay_sinh}
                        onChange={handleChange}
                    />
                </label>

                <label>
                    Địa chỉ:
                    <input type="text" name="dia_chi" placeholder='Nhập địa chỉ' value={form.dia_chi} onChange={handleChange} />
                </label>

                <button type="submit">Tạo hồ sơ</button>
            </form>
        </div>
    );
}

export default AddPatientRecord;
