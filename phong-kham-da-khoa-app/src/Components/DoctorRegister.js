import React, { use, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../Styles/DoctorRegister.css";
import "../Styles/RegisterUser.css";
import { BiArrowBack } from "react-icons/bi";
import Apis, { endpoints } from "../Configs/Apis";
import { sprintf } from "sprintf-js";
import Select from "react-select";

export default function DoctorRegister() {
    const nav = useNavigate();
    const [benhVienList, setBenhVienList] = useState([]);
    const [benhVien, setBenhVien] = useState({});
    const [chuyenKhoaList, setChuyenKhoaList] = useState([]);
    const [chuyenKhoaSelectedList, setChuyenKhoaSelectedList] = useState([]);
    const [loading, setLoading] = useState(false);

    const [user, setUser] = useState({
        id: -1,
        email: "",
        hoNguoiDung: "",
        tenNguoiDung: "",
        ngaySinh: "",
        soDienThoai: "",
        diaChi: "",
        ghiChu: "",
        avatar: "",
        matKhau: "",
        confirmPassword: "",
        benhVien: "",
        chuyenKhoa: [],
        chuyenTri: "",
        ngayLamViec: "",
        role: "DOCTOR"
    });

    const [err_msg, setErrMsg] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (user.matKhau !== user.confirmPassword) {
            setErrMsg("Mật khẩu và xác nhận không khớp.");
            return;
        }
        // TODO: gửi dữ liệu về backend ở đây
        console.log(user);
        console.log(benhVien);
        let chuyenKhoaString = chuyenKhoaSelectedList.join(",");
        console.log(chuyenKhoaString);
        const addUser = async () => {
            try {
                setLoading(true);
                console.log(user);
                let res = await Apis.post(endpoints['doctor-register'], 
                    JSON.stringify({
                        email: user.email,
                        hoNguoiDung: user.hoNguoiDung,
                        tenNguoiDung: user.tenNguoiDung,
                        ngaySinh: user.ngaySinh,
                        soDienThoai: user.soDienThoai,
                        diaChi: user.diaChi,
                        ghiChu: user.ghiChu,
                        avatar: user.avatar,
                        matKhau: user.matKhau,
                        benhVien: user.benhVien,
                        chuyenKhoa: chuyenKhoaString,
                        chuyenTri: user.chuyenTri,
                        ngayLamViec: user.ngayLamViec
                    }),
                    {
                        headers: {
                            "Content-Type": "application/json"
                        },
                    });
                setUser(res.data);
                console.log(res.data);
                nav("/login");
            } catch (ex) {
                console.error(ex);
            }
        }
        addUser();
    };

    useEffect(() => {
        const fetchBenhVien = async () => {
            let res_benhvien = await Apis.get(endpoints['getBenhVien']);
            setBenhVienList(res_benhvien.data);
            const chuyenKhoaUrl = sprintf(endpoints['getChuyenKhoaByBenhVien'], res_benhvien.data.id);
        }
        fetchBenhVien();
    }, []);

    const handleBenhVienChange = async (e) => {
        const benhVienId = e.target.value;
        setBenhVien(e);
        setChuyenKhoaList([]);
        setChuyenKhoaSelectedList([]);
        if (benhVienId) {
            const res_chuyenkhoa = await Apis.get(sprintf(endpoints['getChuyenKhoaByBenhVien'], benhVienId));
            setChuyenKhoaList(res_chuyenkhoa.data);
            console.log(res_chuyenkhoa.data);
        } else {
            setChuyenKhoaList([]);
        }
    }

    const handleChuyenKhoaChange = (e) => {
        // const chuyenKhoasId = e.target.selectedOptions;
        // const selectedChuyenKhoa = Array.from(chuyenKhoasId).map(option => option.value);
        // setChuyenKhoaSelectedList(selectedChuyenKhoa);
    }

    useEffect(() => {
        handleChuyenKhoaChange();
    }, [benhVien]);

    return (
        <div className="register-wrapper">
            <div className="register-card">
                <button
                    className="back-button-icon"
                    onClick={() => nav("/login")}
                >
                    <BiArrowBack size={22} />
                </button>
                <h1 className="register-title">Đăng ký tài khoản Bác sĩ</h1>
                <form className="register-form" onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Email</label>
                        <input type="email" name="email" placeholder="Nhập email" value={user.email} onChange={handleChange} required />
                    </div>
                    <div className="form-row">
                        <div className="form-group">
                            <label>Họ</label>
                            <input type="text" name="hoNguoiDung" placeholder="Nhập họ" value={user.hoNguoiDung} onChange={handleChange} required />
                        </div>
                        <div className="form-group">
                            <label>Tên</label>
                            <input type="text" name="tenNguoiDung" placeholder="Nhập tên" value={user.tenNguoiDung} onChange={handleChange} required />
                        </div>
                    </div>
                    <div className="form-group">
                        <label>Ngày sinh</label>
                        <input type="date" name="ngaySinh" value={user.ngaySinh} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="soDienThoai" placeholder="Nhập số điện thoại" value={user.soDienThoai} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label>Địa chỉ</label>
                        <input type="text" name="diaChi" placeholder="Nhập địa chỉ" value={user.diaChi} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Ghi chú</label>
                        <input type="text" name="ghiChu" placeholder="Ghi chú (tuỳ chọn)" value={user.ghiChu} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Ảnh đại diện</label>
                        <input type="file" name="avatar" placeholder="URL ảnh (tuỳ chọn)" value={user.avatar} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Bệnh viện</label>
                        <select
                            name="benhVien"
                            value={user.benhVien}
                            onChange={async (e) => {
                                handleBenhVienChange(e);
                                handleChange(e);
                            }}
                            className="input-control"
                            required
                        >
                            <option value="">Chọn bệnh viện</option>
                            {benhVienList.map((benhVien) => (
                                <option key={benhVien.id} value={benhVien.id}>
                                    {benhVien.tenBenhVien}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Chuyên khoa</label>
                        <Select
                            isMulti
                            name="chuyenKhoa"
                            options={chuyenKhoaList.map((chuyenKhoa) => ({
                                value: chuyenKhoa.id,
                                label: chuyenKhoa.tenChuyenKhoa,
                            }))}
                            onChange={(selectedOptions) => {
                                const selectedIds = selectedOptions.map((opt) => opt.value);
                                setChuyenKhoaSelectedList(selectedIds);

                                // Nếu dùng chung với user:
                                setUser((prev) => ({ ...prev, chuyenKhoa: selectedIds }));
                            }}
                            className="basic-multi-select"
                            classNamePrefix="select"
                            placeholder="Chọn chuyên khoa"
                            value={chuyenKhoaSelectedList.map((id) => {
                                const found = chuyenKhoaList.find((item) => item.id === id);
                                return { value: id, label: found?.tenChuyenKhoa };
                            })}
                        />
                    </div>
                    <div className="form-group">
                        <label>Chuyên trị</label>
                        <input type="text" name="chuyenTri" placeholder="Ví dụ: 5 năm tại BV Chợ Rẫy" value={user.chuyenTri} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Ngày đi làm</label>
                        <input type="date" name="ngayLamViec" value={user.ngayLamViec} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" name="matKhau" placeholder="Nhập mật khẩu" value={user.matKhau} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label>Xác nhận mật khẩu</label>
                        <input type="password" name="confirmPassword" placeholder="Nhập lại mật khẩu" value={user.confirmPassword} onChange={handleChange} required />
                    </div>
                    {err_msg && <p className="error-message" style={{ color: "red" }}>{err_msg}</p>}
                    <button type="submit" className="register-button">Tạo tài khoản bác sĩ</button>
                </form>
            </div>
        </div>
    );
}
