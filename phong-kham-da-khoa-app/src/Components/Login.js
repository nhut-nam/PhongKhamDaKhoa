import React, { useContext, useState } from "react";
import "../Styles/Login.css";
import Apis, { authApis, endpoints } from "../Configs/Apis";
import { MyDispatcherContext } from "../Configs/MyContexts";
import cookie from 'react-cookies';
import { useNavigate } from "react-router-dom";

function LoginPage() {
  const [user, setUser] = useState({ email: "", matKhau: "" });
  const [loading, setLoading] = useState(false);
  const dispatch = useContext(MyDispatcherContext);
  const nav = useNavigate();
  
  
  const login = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      console.log(user);
      let res = await Apis.post(endpoints['login'], {
      ...user
      });
      console.info(res.data);
      cookie.save('token', res.data.token);
      let u = await authApis().get(endpoints['current-user']);
      console.info(u.data);
      dispatch({
        type: "login",
        payload: {
          user: u.data
        },
      });
      nav("/");
    } catch (ex) {
      console.error(ex);
    } finally {
      setLoading(false);
    }
  }
  return (
    <div className="login-wrapper">
      <div className="login-card">
        <h1 className="login-title">
          Đăng nhập Medpro
        </h1>
        <form className="login-form" method="POST" action="/login" onSubmit={login}>
          <div>
            <label className="login-label">
              Email
            </label>
            <input
              type="email"
              placeholder="Nhập số điện thoại"
              className="login-input"
              name="email"
              value={user.email}
              onChange={(e) => setUser({ ...user, email: e.target.value })}
            />
          </div>
          <div>
            <label className="login-label">
              Mật khẩu
            </label>
            <input
              type="password"
              placeholder="Nhập mật khẩu"
              className="login-input"
              name="matKhau"
              value={user.matKhau}
              onChange={(e) => setUser({ ...user, matKhau: e.target.value })}
            />
          </div>
          <button type="submit" className="login-button">
            Đăng nhập
          </button>
          <div className="login-footer">
            <div className="login-links">
              <a href="#" className="login-link">Quên mật khẩu?</a>
              <span> | </span>
              <a href="/user-register" className="login-link">Đăng ký tài khoản</a>
            </div>

            <div className="doctor-login-box">
              <span>Bạn là bác sĩ?</span>
              <a href="/login-doctor" className="doctor-login-link">Đăng nhập tại đây</a>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
