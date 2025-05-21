import React, { useState } from "react";

export default function MessageInput({ onSend }) {
  const [text, setText] = useState("");

  const handleSend = () => {
    if (text.trim()) {
      onSend(text.trim());
      setText("");
    }
  };

  return (
    <div className="message-input">
      <input value={text} onChange={(e) => setText(e.target.value)} placeholder="Nhập nội dung..." />
      <button onClick={handleSend}>Gửi</button>
    </div>
  );
}
