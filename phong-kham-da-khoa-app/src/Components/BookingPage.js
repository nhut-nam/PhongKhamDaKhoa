import React, { useContext, useEffect, useState } from 'react';
import '../Styles/BookingPage.css';
import { FaHospitalAlt, FaSearch } from 'react-icons/fa';
import { IoReturnUpBack } from 'react-icons/io5';
import Apis, { endpoints } from '../Configs/Apis';
import { sprintf } from 'sprintf-js';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import BookingCalendar from './BookingCalendar';
import { MyUserContext } from '../Configs/MyContexts';

const loaiDichVu = {
  KHAM_DICH_VU: "Khám dịch vụ",
  KHAM_THUONG: "Khám thường",
  GOI_VIDEO: "Gọi video"
}

const loaiThanhToan = {
  THANH_TOAN_TRUC_TUYEN: "Thanh toán trực tuyến",
  THANH_TOAN_TAI_BENH_VIEN: "Thanh toán tại bệnh viện"
}


const BookingPage = () => {
  const today = new Date();
  const [searchParam, setSearchParam] = useSearchParams();
  const [specializedHospital, setSpecializedHospital] = useState([]);
  const [facility, setFacility] = useState();
  const [services, setServices] = useState([]);
  const [doctors, setDoctors] = useState([]);
  const [selectedSpecialty, setSelectedSpecialty] = useState([]);
  const [selectedService, setSelectedService] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState([]);
  const currentStep = searchParam.get("step") || "specialty";
  const benhVienId = searchParam.get('benhVienId');
  const specializedHospitalId = searchParam.get('specializedHospitalId');
  const serviceId = searchParam.get('serviceId');
  const [selectedDate, setSelectedDate] = useState('');
  const [session, setSession] = useState('');
  const user = useContext(MyUserContext);
  const [profile, setProfile] = useState([])
  const [appointment, setAppointment] = useState({
    "soTienNhan": 0,
    "ngayHen": "",
    "benhVienChuyenKhoaDichVuId": "",
    "buoi": "",
    "bacSiId": "",
    "hoSoId": "",
    "trangThai": "CHUA_THANH_TOAN",
  })
  const nav = useNavigate()

  const goToStep = (stepName) => {
    searchParam.set("step", stepName);
    setSearchParam(searchParam);
  };

  useEffect(() => {
    const fetchFacility = async () => {
      if (benhVienId) {
        const url = sprintf(endpoints['getBenhVienChuyenKhoaByBenhVienId'], benhVienId);
        const res = await Apis.get(url);
        setSpecializedHospital(res.data);
        setFacility(res.data[0]?.benhVien);
      }
    };

    const fetchServices = async () => {
      if (specializedHospitalId) {
        const res = await Apis.get(endpoints['getDichVu'] + `/${specializedHospitalId}`);
        setServices(res.data);
      }
    };

    const fetchDoctors = async () => {
      if (serviceId) {
        const res = await Apis.get(endpoints['getBacSiDichVu'] + `/${serviceId}`);
        setDoctors(res.data);
      }
    };

    const fetchProfile = async () => {
      const res = await Apis.get(endpoints['getHoSoList'] + `/${user.user.id}`);
      setProfile(res.data);
      console.log(res.data)
    };


    if (currentStep === 'specialty') fetchFacility();
    else if (currentStep === 'service') fetchServices();
    else if (currentStep === 'doctor') fetchDoctors();
    else if (currentStep === 'profile') fetchProfile();
  }, [searchParam]);

  const updateSpecialty = (s) => {
    searchParam.set("specializedHospitalId", s.id);
    searchParam.set("step", "service");
    setSelectedSpecialty(s.chuyenKhoa)
    setSearchParam(searchParam);
  };

  const updateService = (s) => {
    searchParam.set("serviceId", s.id);
    searchParam.set("step", "doctor");
    setSearchParam(searchParam);
    setSelectedService(s)
    console.log(s)
    setAppointment(prev => ({
      ...prev,
      "benhVienChuyenKhoaDichVuId": s.id
    }));
  };

  const updateDoctor = (s) => {
    searchParam.set("step", "time");
    setSearchParam(searchParam);
    setSelectedDoctor(s.bsDTO)
    setAppointment(prev => ({
      ...prev,
      "bacSiId": s.bsDTO.id
    }));
  };

  const updateProfile = (s) => {
    searchParam.set("step", "appointment");
    setSearchParam(searchParam);
    setProfile(s.id)
    setAppointment(prev => ({
      ...prev,
      "hoSoId": s.id
    }));
  }

  const handleChange = (e) => {
    const date = new Date(e.target.value);
    const day = date.getDay();

    if (date < today.setHours(0, 0, 0, 0)) {
      alert('Không thể chọn ngày trong quá khứ.');
      return;
    }

    if (day === 0 || day === 6) {
      alert('Không thể đặt lịch vào thứ 7 hoặc Chủ nhật.');
      return;
    }

    setSelectedDate(e.target.value);
    setSession('');
  };

  const addAppointment = async () => {
    try {
      const res = await Apis.post(endpoints['taoLichKham'], appointment);
      if (res.status === 200 || res.status === 201) {
        console.log("Đặt lịch thành công:", res.data);
        alert("Đặt lịch khám thành công!");
        nav("/")
      } else {
        console.warn("Đặt lịch không thành công. Mã:", res.status);
        alert("Có lỗi xảy ra khi đặt lịch. Vui lòng thử lại.");
      }
    } catch (error) {
      console.error("Lỗi khi gửi lịch khám:", error);
      alert("Không thể kết nối tới máy chủ. Vui lòng kiểm tra mạng hoặc thử lại sau.");
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!selectedDate) return alert('Vui lòng chọn ngày hợp lệ!');
    if (!session) return alert('Vui lòng chọn buổi khám!');
    alert(`Bạn đã đặt lịch vào ngày ${selectedDate} - Buổi: ${session}`);
    setAppointment(prev => ({
      ...prev,
      "ngayHen": selectedDate,
      "buoi": session
    }));
    goToStep("profile")
  };

  const minDate = new Date().toISOString().split('T')[0];

  return (
    <div className="select-specialty-container">
      <div className="hospital-info">
        <h3><FaHospitalAlt /> {facility?.tenBenhVien} <span className="verified">✔</span></h3>
        <p>{facility?.diaChi}</p>
      </div>

      <div className="specialty-panel">
        {currentStep === "specialty" && (
          <>
            <div className="panel-header">Vui lòng chọn bác sĩ</div>
            <div className="search-bar-select-specialty">
              <input type="text" placeholder="Tìm nhanh chuyên khoa" />
              <FaSearch className="search-icon" />
            </div>
            <div className="specialty-list">
              {specializedHospital.map((s, i) => (
                <div onClick={() => updateSpecialty(s)} className="specialty-item" key={i}>
                  <strong>{s.chuyenKhoa.tenChuyenKhoa}</strong>
                </div>
              ))}
            </div>
          </>
        )}

        {currentStep === "service" && (
          <>
            <div className="panel-header">Vui lòng chọn dịch vụ</div>
            <div className="specialty-list">
              {services.map((s, i) => (
                <div onClick={() => updateService(s)} className="specialty-item" key={i}>
                  <strong>{s.tenDichVu}</strong>
                  <p>{s.giaTien.toLocaleString()} VNĐ</p>
                  <p>Loại dịch vụ: <span>{loaiDichVu[s.loaiDichVu]}</span></p>
                  <p>Loại thanh toán: <span>{loaiThanhToan[s.loaiThanhToan]}</span></p>
                </div>
              ))}
            </div>
          </>
        )}

        {currentStep === "doctor" && (
          <>
            <div className="panel-header">Vui lòng chọn bác sĩ</div>
            <div className="specialty-list">
              {doctors.map((d, i) => (
                <div onClick={() => updateDoctor(d)} className="specialty-item" key={i}>
                  <div className="doctor-card">
                    <img src={d.bsDTO.avatar} alt="Avatar" className="avatar-doctor" />
                    <div className="doctor-info">
                      <strong className="doctor-name">
                        {d.bsDTO.hoNguoiDung + " " + d.bsDTO.tenNguoiDung}
                      </strong>
                      <p><span>Bệnh viện:</span> {d.bsDTO.benhVien.tenBenhVien}</p>
                      <p><span>Kinh nghiệm:</span> {d.bsDTO.chuyenTri || "Chưa cập nhật"}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </>
        )}

        {currentStep === "time" && (
          <div className="calendar-container">
            <h2>Đặt lịch khám</h2>
            <form onSubmit={handleSubmit}>
              <label htmlFor="date">Chọn ngày:</label>
              <input
                type="date"
                id="date"
                value={selectedDate}
                onChange={handleChange}
                min={minDate}
              />
              <button type="submit">Xác nhận đặt lịch</button>
            </form>
            {selectedDate && (
              <div className="session-options">
                <p>Chọn buổi khám:</p>
                <label>
                  <input
                    type="radio"
                    name="session"
                    value="Sáng"
                    checked={session === 'Sáng'}
                    onChange={(e) => setSession(e.target.value)}
                  />
                  Sáng
                </label>
                <label>
                  <input
                    type="radio"
                    name="session"
                    value="Chiều"
                    checked={session === 'Chiều'}
                    onChange={(e) => setSession(e.target.value)}
                  />
                  Chiều
                </label>
              </div>
            )}
          </div>
        )}

        {currentStep === "profile" && (
          <>
            <div className="panel-header">Chọn hồ sơ</div>
            <div className="specialty-list">
              {profile.map((s, i) => (
                <div onClick={() => updateProfile(s)} className="specialty-item" key={i}>
                  <strong>Họ tên: {s.hoTen}</strong>
                  <p>Giới tính: <span>{s.gioiTinh ? "Nữ" : "Nam"}</span></p>
                  <p>Ngày sinh: {new Date(s.ngaySinh).toLocaleDateString('vi-VN')}</p>
                  <p>Email: <span>{s.email}</span></p>
                  <p>Số điện thoại: <span>{s.soDienThoai}</span></p>
                </div>
              ))}
            </div>
          </>
        )}

        {currentStep === "appointment" && (
          <>
            <div className="summary-container">
              <h2>Thông tin lịch khám của bạn</h2>
              <div className="summary-info">
                <p><strong>Bệnh viện: </strong>{facility?.tenBenhVien} </p>
                <p><strong>Chuyên khoa: </strong>{selectedSpecialty.tenChuyenKhoa} </p>
                <p><strong>Bác sĩ: </strong>{selectedDoctor.hoNguoiDung + " " + selectedDoctor.tenNguoiDung} </p>
                <p><strong>Dịch vụ: </strong>{selectedService.tenDichVu} </p>
                <p><strong>Ngày khám: </strong>{new Date(selectedDate).toLocaleDateString('vi-VN')} </p>
                <p><strong>Giá tiền: </strong>{selectedService.giaTien} VNĐ </p>
              </div>
              <button onClick={() => { addAppointment() }}>Xác nhận đặt lịch</button>
            </div>
          </>
        )}

        <div className="back-button" onClick={() => {
          const steps = ["specialty", "service", "doctor", "time"];
          const currentIdx = steps.indexOf(currentStep);
          if (currentIdx > 0) goToStep(steps[currentIdx - 1]);
        }}>
          <IoReturnUpBack /> Quay lại
        </div>
      </div>
    </div>
  );
};

export default BookingPage;