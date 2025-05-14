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
  'getUserById': "user/",
  'updateUser': "user/update",
  'deleteUser': "user/delete/",
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