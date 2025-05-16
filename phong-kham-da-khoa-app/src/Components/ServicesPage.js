import React from "react";
import "./../Styles/Services.css";

const services = [
  {
    title: "Đặt lịch khám theo cơ sở",
    icon: "🏥",
  },
  {
    title: "Đặt lịch khám theo chuyên khoa",
    icon: "🩺",
  },
  {
    title: "Đặt lịch khám theo bác sĩ",
    icon: "👨‍⚕️",
  },
  {
    title: "Gọi video với bác sĩ",
    icon: "📹",
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
            <div className="service-card" key={index}>
              <div className="service-icon">{service.icon}</div>
              <h3 className="service-title">{service.title}</h3>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default ServicesPage;
