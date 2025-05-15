import React, { useContext } from 'react';
import '../Styles/Home.css';
import { MyDispatcherContext, MyUserContext } from '../Configs/MyContexts';
import { Link } from 'react-router-dom';

const Home = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatcherContext);
    return (
        <main>
            <section className="hero-banner">
                <div className="container">
                    <div className="hero-content">
                        <h1>Dịch vụ y tế chất lượng cao</h1>
                        <p>
                            MedPro cung cấp các giải pháp y tế toàn diện với đội ngũ bác sĩ chuyên môn cao và trang thiết bị hiện đại
                        </p>
                        <div className="hero-buttons">
                            {user !== null && (user.user.role !== "DOCTOR" && <Link to="#" className="btn-primary">Đặt lịch khám</Link>)}
                            <a href="#" className="btn-secondary">Tìm hiểu thêm</a>
                        </div>
                    </div>
                </div>
            </section>

            <section className="services-section">
                <div className="container">
                    <h2 className="section-title">Dịch vụ của chúng tôi</h2>
                    <div className="services-grid">
                        <div className="service-card">
                            <i className="fas fa-heartbeat"></i>
                            <h3>Khám tổng quát</h3>
                            <p>Kiểm tra sức khỏe toàn diện với các xét nghiệm cơ bản và chuyên sâu</p>
                        </div>
                        <div className="service-card">
                            <i className="fas fa-procedures"></i>
                            <h3>Phẫu thuật</h3>
                            <p>Thực hiện các ca phẫu thuật với công nghệ tiên tiến nhất</p>
                        </div>
                        <div className="service-card">
                            <i className="fas fa-tooth"></i>
                            <h3>Nha khoa</h3>
                            <p>Chăm sóc răng miệng toàn diện cho cả gia đình</p>
                        </div>
                    </div>
                </div>
            </section>

            <section className="about-section">
                <div className="container">
                    <div className="about-content">
                        <h2 className="section-title">Về MedPro</h2>
                        <p>
                            MedPro là hệ thống y tế chất lượng cao với hơn 10 năm kinh nghiệm trong lĩnh vực chăm sóc sức khỏe. Chúng tôi tự hào mang đến dịch vụ y tế tốt nhất với đội ngũ bác sĩ giàu kinh nghiệm và hệ thống trang thiết bị hiện đại.
                        </p>
                        <ul className="about-features">
                            <li><i className="fas fa-check"></i> Đội ngũ bác sĩ chuyên môn cao</li>
                            <li><i className="fas fa-check"></i> Trang thiết bị hiện đại</li>
                            <li><i className="fas fa-check"></i> Quy trình khám chữa bệnh tiêu chuẩn</li>
                            <li><i className="fas fa-check"></i> Bảo hiểm y tế liên kết</li>
                        </ul>
                    </div>
                    <div className="about-image">
                        <img src="https://medpro.vn/images/about.jpg" alt="Về MedPro" />
                    </div>
                </div>
            </section>
        </main>
    );
};

export default Home;