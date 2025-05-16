import React, { useContext } from 'react';
import '../Styles/Home.css';
import { MyDispatcherContext, MyUserContext } from '../Configs/MyContexts';
import { Link } from 'react-router-dom';

const Home = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatcherContext);
    const [searchTerm, setSearchTerm] = React.useState('');

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        // Xử lý tìm kiếm ở đây, ví dụ: chuyển hướng hoặc gọi API
        alert(`Tìm kiếm: ${searchTerm}`);
    };

    return (
        <main>
            <section className="hero-banner">
                <div className="container">
                    <div className="hero-content">
                        <h1>Dịch vụ y tế chất lượng cao</h1>
                        <p>
                            MedPro cung cấp các giải pháp y tế toàn diện với đội ngũ bác sĩ chuyên môn cao và trang thiết bị hiện đại
                        </p>
                        <form className="search-bar" onSubmit={handleSearchSubmit}>
                            <input
                                type="text"
                                className="search-input"
                                placeholder="Tìm kiếm bác sĩ, chuyên khoa, dịch vụ..."
                                value={searchTerm}
                                onChange={handleSearchChange}
                            />
                            <button type="submit" className="search-button">
                                Tìm kiếm
                            </button>
                        </form>
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
                            <i className="fas fa-hospital-alt"></i>
                            <h3>Đặt lịch khám theo cơ sở</h3>
                        </div>
                        <div className="service-card">
                            <i className="fas fa-stethoscope"></i>
                            <h3>Đặt lịch khám theo chuyên khoa</h3>
                        </div>
                        <div className="service-card">
                            <i className="fas fa-user-md"></i>
                            <h3>Đặt lịch khám theo bác sĩ</h3>
                        </div>
                        <div className="service-card">
                            <i className="fas fa-video"></i>
                            <h3>Gọi video với bác sĩ</h3>
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