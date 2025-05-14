import React from 'react';
import '../Styles/Footer.css'; // Import CSS file for styling
import { Link } from 'react-router-dom';

const Footer = () => {
    return (
        <footer className="site-footer">
            <div className="footer-top">
                <div className="container">
                    <div className="footer-widget">
                        <h3>Về MedPro</h3>
                        <p>MedPro là hệ thống y tế chất lượng cao với hơn 10 năm kinh nghiệm trong lĩnh vực chăm sóc sức khỏe.</p>
                        <div className="footer-social">
                            <Link to="https://www.facebook.com/nhut.nam.522733"><i className="fab fa-facebook-f"></i></Link>
                            <a href="#"><i className="fab fa-twitter"></i></a>
                            <a href="#"><i className="fab fa-linkedin-in"></i></a>
                            <a href="#"><i className="fab fa-youtube"></i></a>
                        </div>
                    </div>

                    <div className="footer-widget">
                        <h3>Liên kết nhanh</h3>
                        <ul>
                            <li><a href="#">Trang chủ</a></li>
                            <li><a href="#">Giới thiệu</a></li>
                            <li><a href="#">Dịch vụ</a></li>
                            <li><a href="#">Bác sĩ</a></li>
                            <li><a href="#">Tin tức</a></li>
                            <li><a href="#">Liên hệ</a></li>
                        </ul>
                    </div>

                    <div className="footer-widget">
                        <h3>Dịch vụ</h3>
                        <ul>
                            <li><a href="#">Khám tổng quát</a></li>
                            <li><a href="#">Phẫu thuật</a></li>
                            <li><a href="#">Nha khoa</a></li>
                            <li><a href="#">Xét nghiệm</a></li>
                            <li><a href="#">Chẩn đoán hình ảnh</a></li>
                        </ul>
                    </div>

                    <div className="footer-widget">
                        <h3>Liên hệ</h3>
                        <ul className="footer-contact">
                            <li><i className="fas fa-map-marker-alt"></i> 123 Đường ABC, Quận 1, TP.HCM</li>
                            <li><i className="fas fa-phone"></i> 1900 1234</li>
                            <li><i className="fas fa-envelope"></i> info@medpro.vn</li>
                            <li><i className="fas fa-clock"></i> Thứ 2 - CN: 7:00 - 19:00</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div className="footer-bottom">
                <div className="container">
                    <p>&copy; 2023 MedPro. Bản quyền thuộc về MedPro.</p>
                </div>
            </div>
        </footer>
    );
};

export default Footer;