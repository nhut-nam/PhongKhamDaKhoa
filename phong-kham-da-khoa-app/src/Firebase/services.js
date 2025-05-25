// // import { doc, setDoc, Timestamp } from "firebase/firestore";
// import { collection, addDoc, Timestamp, setDoc, doc } from "firebase/firestore";
// import { db } from "./FirebaseConfig";


// // Tao chat firebase
// export async function createConversation(userId1, userId2) {
//   const conversationId = [userId1, userId2].sort().join("_");
  
//   await setDoc(doc(db, "conversations", conversationId), {
//     participants: [userId1, userId2],
//     lastMessage: "",
//     lastSenderId: "",
//     timestamp: Timestamp.now(),
//   });
//   return conversationId;
// }


// // Gui tin nhan
// export async function sendMessage(conversationId, senderId, text) {
    
//   const messagesRef = collection(db, "conversations", conversationId, "messages");
 
//   await addDoc(messagesRef, {
//     senderId,
//     text,
//     timestamp: Timestamp.now(),
//   });

//   // Cập nhật thông tin cuộc trò chuyện
//   await setDoc(doc(db, "conversations", conversationId), {
//     lastMessage: text,
//     lastSenderId: senderId,
//     timestamp: Timestamp.now(),
//   }, { merge: true });
// }


