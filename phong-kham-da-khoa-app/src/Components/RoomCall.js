import React, { useEffect, useState } from 'react';
import VideoCallPage from './VideoCallPage';
import { useParams, useLocation } from 'react-router-dom';

const CallWrapper = () => {
  const { bacsiId, benhNhanId } = useParams();
  const location = useLocation();
  const isCaller = new URLSearchParams(location.search).get("caller") === "true";

  const [roomId, setRoomId] = useState(null);

  useEffect(() => {
    const fetchRoomId = async () => {
      const res = await fetch(`http://localhost:8080/PhongKhamDaKhoa/api/webrtc/room-name?bacSiId=${bacsiId}&benhNhanId=${benhNhanId}`);
      const room = await res.text();
      setRoomId(room);
    };
    fetchRoomId();
  }, [bacsiId, benhNhanId]);

  return roomId ? <VideoCallPage roomId={roomId} isCaller={isCaller} /> : <p>🔄 Đang tạo phòng...</p>;
};

export default CallWrapper;
