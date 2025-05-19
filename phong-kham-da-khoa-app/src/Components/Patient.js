import React, { use, useEffect, useState } from "react";
import "../Styles/Patient.css";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import PatientRecord from "./PatientRecord";
import MedicalExamination from "./MedicalExamination";

const Patient = () => {
  const [q] = useSearchParams();
  const [url, setUrl] = useState("");
  const nav = useNavigate();

  useEffect(() => {
    const key = q.get("key");
    const urlMap = "/patient" + key;
  }, [q]);
  
  const changeUrl = (key) => {
    setUrl("?key=" + key);
    nav("/patient" + "?key=" + key);
  }
  return (
    <div className="container-home">
      <div className="sidebar">

        <Link to="/tao-ho-so" className="add-button">
          <span className="icon">😊</span> Thêm hồ sơ
        </Link>
        <div className="menu">
          <div className={q.get("key") === "ho-so-benh-nhan" ? "menu-item active" : "menu-item"} onClick={() => changeUrl("ho-so-benh-nhan")}>📁 Hồ sơ bệnh nhân</div>
          <div className={q.get("key") === "phieu-kham-benh" ? "menu-item active" : "menu-item"} onClick={() => changeUrl("phieu-kham-benh")}>📄 Phiếu khám bệnh</div>
          <div className={q.get("key") === "thong-bao" ? "menu-item active" : "menu-item notification"} onClick={() => changeUrl("thong-bao")}>🔔 Thông báo <span className="badge">99+</span></div>
        </div>
      </div>

      <div className="main">
        {q.get("key") === "ho-so-benh-nhan" ? <PatientRecord /> : null}
        {q.get("key") === "phieu-kham-benh" ? <MedicalExamination /> : null}
      </div>
    </div>

  );
};

export default Patient;
