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