import axios from "axios";
import cookie from 'react-cookies';

const BASE_URL = "http://localhost:8080/PhongKhamDaKhoa/api/";

export const endpoints = {
  'login': "/login",
  'current-user': "/secure/profile",
  'user-register': "/benh-nhan",
  'doctor-register': "/bac-si",
  'getBenhVien': "ds-benh-vien",
  'getChuyenKhoa': "ds-chuyen-khoa",
  'getChuyenKhoaByBenhVien': "benh-vien/%s/chuyen-khoa",
  'addBacSiChuyenKhoa': "/addBacSiChuyenKhoa",
  'addPatientRecord': "/secure/users/tao-ho-so",
  'getHoSoList': "/secure/users/get-ds-ho-so",
  'getHoSo': "/secure/users/get-ho-so",
  'deleteHoSo': "/secure/users/xoa-ho-so",
  'updateHoSo': "/secure/users/sua-ho-so",
  'getDoctorList': "/ds-bac-si",
  'addBangCap': "/tao-bang-cap",
  'getBenhVienChuyenKhoa': "/benh-vien-chuyen-khoa",
  'getBenhVienChuyenKhoaByBenhVienId': "/benh-vien/%s/benh-vien-chuyen-khoa",
  'getDichVu': "/benh-vien-chuyen-khoa-dich-vu",
  'getLichKhamBacSi': (bacSiId) =>`secure/doctors/lich-kham/bac-si/${bacSiId}`,
  'getThongKeBenhNhan': (bacSiId)=>`secure/doctors/bac-si/${bacSiId}/thong-ke-so-luong`,
  'getThongKeLoaiBenh': (bacSiId)=>`secure/doctors/bac-si/${bacSiId}/thong-ke-loai-benh`,
  "getHoSoBenhNhan": (bacSiId)=>`/secure/doctors/get-list-ho-so-benh-nhan/${bacSiId}`,
  "lichSuKhamBenh": "/secure/doctors/lich-su-kham-benh/{id}",
  'getBacSiDichVu': "/bac-si-dich-vu",
  'taoLichKham': "/secure/users/tao-lich-kham",
  'getLichKham': "/secure/users/lich-kham",
  'suaLichKham': "/secure/users/sua-lich-kham",
  'getBacSiChiTiet': "/bac-si-chi-tiet",
  'themDanhGia': "/secure/users/them-danh-gia",
  'updateTrangThaiLichKham':"/lich-kham",
  'getRoom': "/web-rtc/room-name",
  'phanHoiDanhGia': "/secure/users/phan-hoi-danh-gia",
  'addTinTuc': "/secure/tin-tuc",
  'getTinTuc': "/secure/tin-tuc/",
  'chatUser' : (userId)=>`/secure/chat/users/${userId}`,
  'chatDoctor' : (doctorId)=>`/secure/chat/doctors/${doctorId}`
};

export const authApis = () => {
  return axios.create({
      baseURL: BASE_URL,
      headers: {
          'Authorization': `Bearer ${cookie.load('token')}`,
      }
  })
}

export default axios.create({
  baseURL: BASE_URL
});