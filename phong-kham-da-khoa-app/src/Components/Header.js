import React from 'react';
import '../Styles/Header.css';

const Header = () => {
    return (
        <header>
        <div className="header-top">
            <div className="container">
                <div className="contact-info">
                    <span><i className="fas fa-phone"></i> 1900 1234</span>
                    <span><i className="fas fa-envelope"></i> info@medpro.vn</span>
                </div>
                <div className="social-icons">
                    <a href="#"><i className="fab fa-facebook-f"></i></a>
                    <a href="#"><i className="fab fa-twitter"></i></a>
                    <a href="#"><i className="fab fa-linkedin-in"></i></a>
                </div>
            </div>
        </div>
        
        <div className="header-main">
            <div className="container">
                <div className="logo">
                    <img src="https://medpro.vn/images/logo.png" alt="MedPro Logo" />
                </div>
                
                <nav className="main-menu">
                    <ul>
                        <li><a href="/" onClick={(e) => e.preventDefault()}>Trang chủ</a></li>
                        <li><a href="#">Giới thiệu</a></li>
                        <li><a href="#">Dịch vụ</a></li>
                        <li><a href="#">Bác sĩ</a></li>
                        <li><a href="#">Tin tức</a></li>
                        <li><a href="#">Liên hệ</a></li>
                        <li><a href="#">Đăng nhập</a></li>
                    </ul>
                </nav>
                
                <div className="mobile-menu-btn">
                    <i className="fas fa-bars"></i>
                </div>
            </div>
        </div>
    </header>
        
        
    );
};

export default Header;