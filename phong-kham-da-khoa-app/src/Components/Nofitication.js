import React, { use, useContext, useEffect, useState } from "react";
import "../Styles/Notification.css";
import Apis, { authApis, endpoints } from "../Configs/Apis";
import { MyUserContext } from "../Configs/MyContexts";
import cookie from 'react-cookies';

const Notification = () => {
  const [notifications, setNotifications] = useState([]);
  const user = useContext(MyUserContext)

  const fetchNotifications = async () => {
    const token = cookie.load("token");
    console.log("Token:", token);
    const newNotifications = await authApis(token).get(endpoints["getTinTuc"] + `${user.user.id}`);
    setNotifications(newNotifications.data);
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  return (
    <>
      <h2>Thông báo</h2>
      {notifications.length > 0 ? (
        notifications.map((notification) => (
          <div key={notification.id} className="notification-item">
            <h1>{notification.tieuDe}</h1>
            <p>{notification.noiDung}</p>
            <p>{new Date(notification.ngayDang).toLocaleString()}</p>
          </div>
        ))
      ) : (
        <p>Không có thông báo nào.</p>
      )}
    </>
  );
};

export default Notification;
