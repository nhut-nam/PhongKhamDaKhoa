import React, { useEffect, useRef } from 'react';

const SIGNALING_URL = 'http://localhost:8080/PhongKhamDaKhoa/api/webrtc';

const VideoCallPage = ({ roomId, isCaller }) => {
  const localVideo = useRef();
  const remoteVideo = useRef();
  const pc = useRef(null);
  const polling = useRef(null);
  const pendingCandidates = useRef([]);
  const remoteDescSet = useRef(false);

  const fetchJSON = async (url) => {
    const res = await fetch(url);
    return await res.json();
  };

  const send = async (path, body) => {
    try {
      await fetch(`${SIGNALING_URL}/${path}?roomId=${roomId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
      });
    } catch (err) {
      console.error('❌ Send error:', err);
    }
  };

  useEffect(() => {
    const peer = new RTCPeerConnection();
    pc.current = peer;

    peer.ontrack = (e) => {
      if (remoteVideo.current && e.streams[0]) {
        remoteVideo.current.srcObject = e.streams[0];
      }
    };

    peer.onicecandidate = (e) => {
      if (e.candidate) send('candidate', e.candidate.toJSON());
    };

    const setupConnection = async () => {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
        localVideo.current.srcObject = stream;

        stream.getTracks().forEach(track => {
          if (peer.signalingState !== 'closed') {
            peer.addTrack(track, stream);
          }
        });

        if (isCaller && peer.signalingState !== 'closed') {
          const offer = await peer.createOffer();
          await peer.setLocalDescription(offer);
          await send('offer', offer);
        }

        if (!isCaller) {
          const offer = await fetchJSON(`${SIGNALING_URL}/offer?roomId=${roomId}`);
          if (offer?.sdp && peer.signalingState !== 'closed') {
            await peer.setRemoteDescription(new RTCSessionDescription(offer));
            remoteDescSet.current = true;

            for (const c of pendingCandidates.current) {
              try {
                await peer.addIceCandidate(new RTCIceCandidate(c));
              } catch (err) {
                console.warn("⚠️ Failed to apply ICE:", err);
              }
            }
            pendingCandidates.current = [];

            const answer = await peer.createAnswer();
            await peer.setLocalDescription(answer);
            await send('answer', answer);
          }
        }

        polling.current = setInterval(async () => {
          if (!pc.current || pc.current.signalingState === 'closed') return;

          const candidates = await fetchJSON(`${SIGNALING_URL}/candidates?roomId=${roomId}`);
          for (const c of candidates) {
            if (c?.candidate) {
              if (remoteDescSet.current) {
                try {
                  await peer.addIceCandidate(new RTCIceCandidate(c));
                } catch (err) {
                  console.error("❌ ICE Candidate error:", err);
                }
              } else {
                pendingCandidates.current.push(c);
              }
            }
          }

          if (isCaller && !peer.currentRemoteDescription) {
            const answer = await fetchJSON(`${SIGNALING_URL}/answer?roomId=${roomId}`);
            if (answer?.sdp && peer.signalingState !== 'closed') {
              await peer.setRemoteDescription(new RTCSessionDescription(answer));
              remoteDescSet.current = true;
            }
          }
        }, 1000);
      } catch (err) {
        console.error("❌ Setup error:", err);
      }
    };

    setupConnection();

    return () => {
      clearInterval(polling.current);
      if (pc.current) {
        pc.current.close();
        pc.current = null;
      }
    };
  }, [roomId, isCaller]);

  return (
    <div style={{ textAlign: 'center', padding: '20px' }}>
      <h2>📹 Cuộc gọi Video</h2>
      <div style={{ display: 'flex', justifyContent: 'center', gap: '20px', flexWrap: 'wrap' }}>
        <div>
          <h4>Bạn</h4>
          <video ref={localVideo} autoPlay playsInline muted width="320" height="240" style={{ borderRadius: '10px', border: '2px solid #ccc' }} />
        </div>
        <div>
          <h4>Đối phương</h4>
          <video ref={remoteVideo} autoPlay playsInline width="320" height="240" style={{ borderRadius: '10px', border: '2px solid #ccc' }} />
        </div>
      </div>
    </div>
  );
};

export default VideoCallPage;
