import React, { useContext, useState } from "react";
import "../Styles/Login.css";
import Apis, { authApis, endpoints } from "../Configs/Apis";
import { MyDispatcherContext } from "../Configs/MyContexts";
import cookie from 'react-cookies';
import { Form, Link, useNavigate } from "react-router-dom";

function LoginPage() {
  const [user, setUser] = useState({ email: "", matKhau: "" });
  const [loading, setLoading] = useState(false);
  const dispatch = useContext(MyDispatcherContext);
  const [msg, setMsg] = useState("");
  const nav = useNavigate();


  const login = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      let res = await Apis.post(endpoints['login'], {
        ...user
      });
      cookie.save('token', res.data.token);
      let u = await authApis().get(endpoints['current-user']);
      dispatch({
        type: "login",
        payload: {
          user: u.data
        },
      });
      const formData = new FormData();
      formData.append("userId", u.data.id);
      setTimeout(async () => {
        await authApis(res.data.token).post(endpoints['addTinTuc'], formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          }
        });
      }, 100);

      if (u.data.role === "ROLE_DOCTOR") {
        nav("/bac-si/dashboard");
      } else {
        nav(-1);
      }

    } catch (ex) {
      if (ex.response && ex.response.data.status === false) {
        setMsg(ex.response.data.message);
      }
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
              placeholder="Nhập email"
              className="login-input"
              name="email"
              value={user.email}
              onChange={(e) => setUser({ ...user, email: e.target.value })}
              required
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
              required
            />
          </div>
          {msg && <div className="login-error">{msg}</div>}
          <button type="submit" className="login-button">
            Đăng nhập
          </button>
          <div className="login-footer">
            <div className="login-links">
              <Link to="#" className="login-link">Quên mật khẩu?</Link>
              <span> | </span>
              <Link to="/user-register" className="login-link">Đăng ký tài khoản</Link>
            </div>

            <div className="doctor-login-box">
              <span>Đăng ký hành nghề bác sĩ</span>
              <Link to="/doctor-register" className="doctor-login-link">Đăng ký tại đây</Link>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
