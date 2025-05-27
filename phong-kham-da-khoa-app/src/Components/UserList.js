import React, { useState, useEffect } from "react";
import { authApis } from "../Configs/Apis"; // Đảm bảo bạn đã cấu hình đúng API

function UserList({ apiUrl, onSelect }) {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const res = await authApis().get(apiUrl);
        setUsers(res.data);
      } catch (err) {
        console.error("Lỗi khi load danh sách người dùng:", err.response?.data || err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUsers();
  }, [apiUrl]);

  if (loading) return <p>Đang tải danh sách...</p>;
  if (users.length === 0) return <p>Không tìm thấy kết quả.</p>;

  return (
    <div>
      {users.map((user) => (
        <div
          key={user.id}
          onClick={() => onSelect(user)}
          style={{
            cursor: "pointer",
            padding: "10px",
            borderBottom: "1px solid #eee",
            backgroundColor: "#f9f9f9",
          }}
        >
          {user.hoNguoiDung + " " + user.tenNguoiDung || "Không rõ tên"}
        </div>
      ))}
    </div>
  );
}

export default UserList;
