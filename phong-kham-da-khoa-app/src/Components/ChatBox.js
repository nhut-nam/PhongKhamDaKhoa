// components/Chat/ChatWindow.jsx
import React from "react";
import MessageItem from "./MessageItem";

export default function ChatWindow({ messages, currentUserId, recipient }) {
  return (
    <div className="chat-window">
      <div className="chat-header">
        <img src={recipient.avatar} alt="avatar" className="avatar" />
        <span>{recipient.name}</span>
      </div>

      <div className="chat-messages">
        {messages.map((msg, index) => (
          <MessageItem key={index} message={msg} currentUserId={currentUserId} />
        ))}
      </div>
    </div>
  );
}
