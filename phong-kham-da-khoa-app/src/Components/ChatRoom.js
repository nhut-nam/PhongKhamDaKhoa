import React, { useState, useEffect, useRef } from "react";
import { database } from "../Firebase/FirebaseConfig";
import { ref, push, onValue, query, orderByChild } from "firebase/database";

function ChatRoom({ roomId, userId, userRole, userName, userAvatar, receiverName, receiverAvatar }) {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const messagesEndRef = useRef(null);

  // Đọc tin nhắn từ Firebase
  useEffect(() => {
    const messagesRef = query(ref(database, `chats/${roomId}/messages`), orderByChild("timestamp"));
    onValue(messagesRef, (snapshot) => {
      const data = snapshot.val();
      const msgs = data ? Object.values(data) : [];
      setMessages(msgs);
      scrollToBottom();
    });
  }, [roomId]);

  const sendMessage = () => {
    if (input.trim() === "") return;

    const messagesRef = ref(database, `chats/${roomId}/messages`);
    push(messagesRef, {
      senderId: userId,
      senderRole: userRole,
      senderName: userName,
      senderAvatar: userAvatar,  // Thêm avatar người gửi
      text: input.trim(),
      timestamp: Date.now(),
    });

    setInput("");
  };

  function scrollToBottom() {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }

  const formatTime = (timestamp) => {
    const date = new Date(timestamp);
    const hours = date.getHours().toString().padStart(2, "0");
    const minutes = date.getMinutes().toString().padStart(2, "0");
    return `${hours}:${minutes}`;
  };

  return (
    <div style={{ display: "flex", height: "600px", border: "1px solid #ccc" }}>
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        {/* Header */}
        <div style={{ padding: "10px", borderBottom: "1px solid #ccc", backgroundColor: "#eee" }}>
          <h3>Chat với {receiverName}</h3>
        </div>

        {/* Tin nhắn */}
        <div style={{ flex: 1, padding: "10px", overflowY: "auto", backgroundColor: "#fafafa" }}>
          {messages.map((msg, i) => {
            const isMine = msg.senderId === userId;
            return (
              <div
                key={i}
                style={{
                  display: "flex",
                  flexDirection: isMine ? "row-reverse" : "row", // Đảo ngược tin nhắn cho người gửi
                  marginBottom: "15px", // Khoảng cách giữa các tin nhắn
                  alignItems: "flex-end", // Đảm bảo avatar và tin nhắn căn đều
                }}
              >
                {/* Avatar */}
                <div
                  style={{
                    marginLeft: isMine ? "0" : "10px", // Khoảng cách của avatar bên trái hoặc bên phải
                    marginRight: isMine ? "10px" : "0",
                  }}
                >
                  <img
                    src={msg.senderAvatar} // Sử dụng avatar từ Firebase
                    alt="avatar"
                    style={{
                      width: "40px", // Kích thước avatar
                      height: "40px",
                      borderRadius: "50%",
                      objectFit: "cover", // Đảm bảo avatar không bị méo
                    }}
                  />
                </div>

                {/* Tin nhắn */}
                <div
                  style={{
                    maxWidth: "70%",
                    backgroundColor: isMine ? "#0084ff" : "#e5e5ea",
                    color: isMine ? "white" : "black",
                    padding: "12px 16px",
                    borderRadius: "20px",
                    borderBottomRightRadius: isMine ? "0" : "20px",
                    borderBottomLeftRadius: isMine ? "20px" : "0",
                    wordBreak: "break-word",
                    boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)", // Thêm shadow cho hộp tin nhắn
                  }}
                >
                  <div style={{ fontSize: "14px", marginBottom: "4px", opacity: 0.7 }}>
                    {isMine ? "Bạn" : msg.senderName}
                  </div>
                  <div>{msg.text}</div>
                  <div style={{ fontSize: "10px", opacity: 0.5, marginTop: "4px" }}>
                    {formatTime(msg.timestamp)} {/* Hiển thị thời gian gửi */}
                  </div>
                </div>
              </div>
            );
          })}
          <div ref={messagesEndRef} />
        </div>

        {/* Input */}
        <div style={{ padding: "10px", borderTop: "1px solid #ccc", display: "flex" }}>
          <input
            style={{
              flex: 1,
              padding: "10px",
              fontSize: "16px",
              borderRadius: "20px",
              border: "1px solid #ccc", // Border mềm mại
            }}
            type="text"
            placeholder="Nhập tin nhắn..."
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter") sendMessage();
            }}
          />
          <button
            onClick={sendMessage}
            style={{
              marginLeft: "10px",
              padding: "10px 20px",
              backgroundColor: "#0084ff",
              color: "white",
              border: "none",
              borderRadius: "20px",
              cursor: "pointer",
            }}
          >
            Gửi
          </button>
        </div>
      </div>
    </div>
  );
}

export default ChatRoom;
