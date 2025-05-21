import React from "react";
import '../Styles/MessageItem.css';

export default function MessageItem({ message, currentUserId }) {
  const isSender = message.senderId === currentUserId;
  return (
    <div className={`message-item ${isSender ? "right" : "left"}`}>
      <div className="message-bubble">
        {message.text}
      </div>
    </div>
  );
}
