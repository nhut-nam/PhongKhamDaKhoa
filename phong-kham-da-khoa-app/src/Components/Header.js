import React, { useContext, useEffect } from 'react';
import '../Styles/Header.css';
import { Link } from 'react-router-dom';
import { MyDispatcherContext, MyUserContext } from '../Configs/MyContexts';

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatcherContext);
    const a = useEffect(() => {
        if (user !== null) {
            console.log(user.user); }
    }, [user]);
    return (
        <header>
            <div className="header-top">
                <div className="container">
                    <div className="contact-info">
                        <span><i className="fas fa-phone"></i> 1900 1234</span>
                        <span><i className="fas fa-envelope"></i> namnhut1426@gmail.com</span>
                    </div>
                    <div className="social-icons">
                        <Link to="#"><i className="fab fa-facebook-f"></i></Link>
                        <Link to="#"><i className="fab fa-twitter"></i></Link>
                        <Link to="#"><i className="fab fa-linkedin-in"></i></Link>
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
                            <li><Link to="/">Trang chủ</Link></li>
                            <li><Link to="/about">Giới thiệu</Link></li>
                            <li><Link to="/services">Dịch vụ</Link></li>
                            <li><Link to="/news">Tin tức</Link></li>
                            <li><Link to="/contact">Liên hệ</Link></li>
                            {user === null ? <li><Link to="/login">Đăng nhập</Link></li>
                                :<><li><Link to="/user-profile">{user.user.hoNguoiDung + ' ' + user.user.tenNguoiDung}</Link></li> 
                                <li><Link to="/" onClick={() => dispatch({"type": "logout"})}>Đăng xuất</Link></li></>}
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