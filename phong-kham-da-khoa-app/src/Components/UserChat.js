import { useContext } from "react";
import { MyUserContext } from "../Configs/MyContexts";
import ChatPageShared from "./ChatPageShare";

const USERCHAT = () => {
  const user = useContext(MyUserContext)
          return (
          <div className="section-box">
            <h2>Chat với bác sĩ</h2>
            <ChatPageShared 
            currentUserId={user.user.id}
            userRole="ROLE_USER" // hoặc "doctor"
            userName={user.user.hoNguoiDung + ' ' + user.user.tenNguoiDung}
            userAvatar={user.user.avatar}
            />
          </div>
        );
  };
export default USERCHAT;