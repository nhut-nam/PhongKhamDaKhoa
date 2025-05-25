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
  'addPatientRecord': "/tao-ho-so",
  'getHoSoList': "/get-ds-ho-so",
  'getHoSo': "/get-ho-so",
  'deleteHoSo': "/xoa-ho-so",
  'updateHoSo': "/sua-ho-so",
  'getDoctorList': "/ds-bac-si",
  'addBangCap': "/tao-bang-cap",
  'getBenhVienChuyenKhoa': "/benh-vien-chuyen-khoa",
  'getBenhVienChuyenKhoaByBenhVienId': "/benh-vien/%s/benh-vien-chuyen-khoa",
  'getDichVu': "/benh-vien-chuyen-khoa-dich-vu",
  'getBacSiDichVu': "/bac-si-dich-vu",
  'taoLichKham': "/tao-lich-kham",
  'getLichKham': "/lich-kham",
  'suaLichKham': "/sua-lich-kham",
  'getBacSiChiTiet': "/bac-si-chi-tiet",
  'themDanhGia': "/them-danh-gia"
};

export const authApis = () => {
  return axios.create({
      baseURL: BASE_URL,
      headers: {
          'Authorization': `Bearer ${cookie.load('token')}`
      }
  })
}

export default axios.create({
  baseURL: BASE_URL
});