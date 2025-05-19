import React from "react";
import "./../Styles/Services.css";
import { Link } from "react-router-dom";

const services = [
  {
    title: "Đặt lịch khám theo cơ sở",
    icon: "🏥",
    to: "/dat-kham-theo-co-so"
  },
  {
    title: "Đặt lịch khám theo chuyên khoa",
    icon: "🩺",
    to: "/dat-kham-theo-chuyen-khoa"  
  },
  {
    title: "Đặt lịch khám theo bác sĩ",
    icon: "👨‍⚕️",
    to: "/dat-kham-theo-bac-si"
  },
  {
    title: "Gọi video với bác sĩ",
    icon: "📹",
    to: "/goi-video-voi-bac-si"
  },
];

const ServicesPage = () => {
  return (
    <section className="services-section">
      <div className="services-container">
        <h2 className="services-title">Dịch vụ y tế</h2>
        <p className="services-subtitle">
          MedPro cung cấp các giải pháp y tế toàn diện với đội ngũ bác sĩ chuyên môn cao
        </p>
        <div className="services-grid">
          {services.map((service, index) => (
            <Link to={service.to} className="service-card" key={index}>
              <div className="service-icon">{service.icon}</div>
              <h3 className="service-title">{service.title}</h3>
            </Link>
          ))}
        </div>
      </div>
    </section>
  );
};

export default ServicesPage;
