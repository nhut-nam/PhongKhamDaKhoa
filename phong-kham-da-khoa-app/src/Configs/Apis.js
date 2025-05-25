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
  'addPatientRecord': "/secure/tao-ho-so",
  'getHoSoList': "/secure/get-ds-ho-so",
  'getHoSo': "/secure/get-ho-so",
  'deleteHoSo': "/secure/xoa-ho-so",
  'updateHoSo': "/secure/sua-ho-so",
  'getDoctorList': "/ds-bac-si",
  'addBangCap': "/tao-bang-cap",
  'getBenhVienChuyenKhoa': "/benh-vien-chuyen-khoa",
  'getBenhVienChuyenKhoaByBenhVienId': "/benh-vien/%s/benh-vien-chuyen-khoa",
  'getDichVu': "/benh-vien-chuyen-khoa-dich-vu",
  'getLichKhamBacSi': "lich-kham/bac-si",
  'getThongKeBenhNhan': "bac-si/thong-ke-so-luong",
  'getThongKeLoaiBenh': "bac-si/thong-ke-loai-benh",
  "getHoSoBenhNhan": "/secure/get-list-ho-so-benh-nhan/{bacSiId}",
  "lichSuKhamBenh": "/secure/lich-su-kham-benh/{id}"
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