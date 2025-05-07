import React, { useState } from "react";
import "../Styles/RegisterUser.css";

export default function RegisterUser() {
    const [err_msg, setErrMsg] = useState("");
    const [user, setUser] = useState({
        email: "",
        hoNguoiDung: "",
        tenNguoiDung: "",
        ngaySinh: "",
        soDienThoai: "",
        ghiChu: "",
        diaChi: "",
        avatar: "",
        matKhau: "",
        confirmPassword: "",
        role: "USER",
    });

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Dữ liệu đăng ký:", user);
        if (user.matKhau !== user.confirmPassword) {
            setErrMsg("Mật khẩu không khớp. Vui lòng kiểm tra lại.");
            return;
        }

        if (user.matKhau.length < 6) {
            setErrMsg("Mật khẩu phải có ít nhất 6 ký tự.");
            return;
        }
        if (user.soDienThoai.length < 10) {
            setErrMsg("Số điện thoại không hợp lệ.");
            return;
        }
        if (user.hoNguoiDung.length < 2 || user.tenNguoiDung.length < 2) {
            setErrMsg("Họ và tên không hợp lệ.");
            return;
        }
    };

    return (
        <div className="register-wrapper">
            <div className="register-card">
                <h1 className="register-title">Đăng ký tài khoản Medpro</h1>
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
                        <label>Link ảnh đại diện</label>
                        <input type="file" name="avatar" placeholder="URL ảnh (tuỳ chọn)" value={user.avatar} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" name="matKhau" placeholder="Nhập mật khẩu" value={user.matKhau} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label>Xác nhận mật khẩu</label>
                        <input
                            type="password"
                            name="confirmPassword"
                            placeholder="Nhập lại mật khẩu"
                            value={user.confirmPassword}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    {err_msg && <p className="error-message" style={{ color: "red" }}>{err_msg}</p>}
                    <button type="submit" className="register-button">Tạo tài khoản</button>
                </form>
            </div>
        </div>
    );
}
