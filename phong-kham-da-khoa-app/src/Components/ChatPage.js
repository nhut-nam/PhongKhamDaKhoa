// // pages/ChatPage.jsx
// import React, { useEffect, useState } from "react";
// import ChatWindow from "../components/Chat/ChatWindow";
// import MessageInput from "../components/Chat/MessageInput";
// import { onSnapshot, collection, query, orderBy } from "firebase/firestore";
// import { db } from "../Firebase/FirebaseConfig";
// import { sendMessage } from "../Firebase/services";

// export default function ChatPage({ userId, recipient }) {
//   const [messages, setMessages] = useState([]);
//   const conversationId = [userId, recipient.id].sort().join("_");

//   useEffect(() => {
//     const q = query(collection(db, "conversations", conversationId, "messages"), orderBy("timestamp"));
//     const unsubscribe = onSnapshot(q, (snapshot) => {
//       const msgs = snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));
//       setMessages(msgs);
//     });

//     return () => unsubscribe();
//   }, [conversationId]);

//   const handleSend = (text) => {
//     sendMessage(conversationId, userId, text);
//   };

//   return (
//     <div className="chat-page">
//       <ChatWindow messages={messages} currentUserId={userId} recipient={recipient} />
//       <MessageInput onSend={handleSend} />
//     </div>
//   );
// }
