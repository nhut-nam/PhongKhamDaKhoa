import React, { useContext, useEffect, useRef, useState } from 'react';
import '../Styles/Header.css';
import { Link } from 'react-router-dom';
import { MyDispatcherContext, MyUserContext } from '../Configs/MyContexts';

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatcherContext);
    const [open, setOpen] = useState(false);
    const menuRef = useRef();
    const [avatar, setAvatar] = useState(null);

    const handleAvatarChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setAvatar(reader.result); // hiển thị ảnh preview
                // TODO: Gửi ảnh lên server tại đây nếu cần
            };
            reader.readAsDataURL(file);
        }
    };


    useEffect(() => {
        const handleClickOutside = (e) => {
            if (menuRef.current && !menuRef.current.contains(e.target)) {
                setOpen(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

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
                    <Link to="/" className="logo">
                        <img src="https://res.cloudinary.com/dhsxutqtc/image/upload/v1747485304/hospital-building_krz3yt.png" alt="Hospital Logo" />
                        <h1 className='logo-title'>Hospital</h1>
                    </Link>

                    <nav className="main-menu">
                        <ul>
                            <li><Link to="/">Trang chủ</Link></li>
                            <li><Link to="/about">Giới thiệu</Link></li>
                            <li><Link to="/dich-vu">Dịch vụ</Link></li>
                            <li><Link to="/news">Tin tức</Link></li>
                            <li><Link to="/contact">Liên hệ</Link></li>
                            {user === null ? (<li><Link to="/login">Đăng nhập</Link></li>)
                                : (<><li className="user-dropdown" ref={menuRef}>
                                    <div className="user-name" onClick={() => setOpen(!open)}>
                                        👤 {user.user.hoNguoiDung + " " + user.user.tenNguoiDung} ▾
                                    </div>

                                    {open && (
                                        <div className="dropdown-menu">
                                            <div className="user-info">
                                                <div className="avatar-wrapper" onClick={() => document.getElementById("avatarInput").click()}>
                                                    <img
                                                        src={avatar || user.user.avatar || '/default-avatar.png'}
                                                        alt="Avatar"
                                                        className="avatar"
                                                    />
                                                    <input
                                                        type="file"
                                                        id="avatarInput"
                                                        accept="image/*"
                                                        style={{ display: "none" }}
                                                        onChange={(e) => handleAvatarChange(e)}
                                                    />
                                                </div>
                                                <div className="greeting">
                                                    <p className="hello">Xin chào,</p>
                                                    <strong className="username">{user.user.hoNguoiDung + " " + user.user.tenNguoiDung}</strong>
                                                </div>
                                            </div>
                                            <hr />
                                            <Link to="/patient?key=ho-so-benh-nhan">📁 Hồ sơ bệnh nhân</Link>
                                            <Link to="/patient?key=phieu-kham-benh">📄 Phiếu khám bệnh</Link>
                                            <Link to="/patient?key=thong-bao">🔔 Thông báo</Link>
                                            <hr />
                                            <Link to="/" onClick={() => dispatch({ type: "logout" })} style={{ color: "red" }}>
                                                🔴 Đăng xuất
                                            </Link>
                                            <div className="updated-date">Cập nhật mới nhất: {new Date().toLocaleDateString()}</div>
                                        </div>
                                    )}
                                </li></>)}
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