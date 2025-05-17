import React, { useState } from 'react';
import '../Styles/AddPatientRecord.css';

function AddPatientRecord() {
  const [form, setForm] = useState({
    gioi_tinh: '',
    id: '',
    ngay_sinh: '',
    dia_chi: '',
    email: '',
    ho_ten: '',
    so_dien_thoai: '',
  });

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
    // gọi API ở đây
  };

  return (
    <div className="form-container">
      <h2>Tạo hồ sơ bệnh nhân</h2>
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
          <select name="gioi_tinh" value={form.gioi_tinh} onChange={handleChange}>
            <option value="">Chọn</option>
            <option value="Nam">Nam</option>
            <option value="Nữ">Nữ</option>
          </select>
        </label>

        <label>
          Ngày sinh:
          <input type="date" name="ngay_sinh" placeholder='Nhập ngày sinh' value={form.ngay_sinh} onChange={handleChange} />
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
