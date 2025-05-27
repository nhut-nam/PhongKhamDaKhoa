import React, { useState } from "react";
import UserList from "./UserList";
import ChatRoom from "./ChatRoom";
import { endpoints } from "../Configs/Apis";

function ChatPageShared({ currentUserId, userRole, userName, userAvatar }) {
  const [selectedUser, setSelectedUser] = useState(null);

  const apiUrl = userRole === "ROLE_USER"
    ? endpoints.chatUser(currentUserId)
    : endpoints.chatDoctor(currentUserId);

  const handleSelectUser = (user) => {
    setSelectedUser(user);
  };

  const getRoomId = () => {
    if (!selectedUser) return null;
    return userRole === "ROLE_USER"
      ? `chat_${currentUserId}_${selectedUser.id}`
      : `chat_${selectedUser.id}_${currentUserId}`;
  };

  return (
    <div style={{ display: "flex", height: "100vh" }}>
      <div style={{ width: "250px", borderRight: "1px solid #ccc", padding: "10px" }}>
        <h3>{userRole === "ROLE_USER" ? "Danh sách bác sĩ" : "Danh sách bệnh nhân"}</h3>
        <UserList apiUrl={apiUrl} onSelect={handleSelectUser} />
      </div>

      <div style={{ flex: 1 }}>
        {selectedUser && (
          <ChatRoom
            roomId={getRoomId()}
            userId={currentUserId}
            userRole={userRole}
            userName={userName}
            userAvatar={userAvatar}  // Truyền avatar của người dùng
            receiverName={selectedUser.hoNguoiDung + ' ' + selectedUser.tenNguoiDung}
            receiverAvatar={selectedUser.avatar || "default_avatar_url"}  // Truyền avatar của người nhận
          />
        )}
      </div>
    </div>
  );
}

export default ChatPageShared;
