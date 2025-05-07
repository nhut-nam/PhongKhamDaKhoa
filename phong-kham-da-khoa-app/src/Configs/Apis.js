import axios from "axios";
import cookie from 'react-cookies';

const BASE_URL = "http://localhost:8080/PhongKhamDaKhoa/api/";

export const endpoints = {
  'login': "/login",
  'current-user': "/secure/profile",
  'user-register': "/benh-nhan",
  'getUserInfo': "user/info",
  'getAllUsers': "user/all",
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