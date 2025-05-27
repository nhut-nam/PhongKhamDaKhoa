import React, { useContext, useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import '../Styles/DoctorDetail.css';
import Apis, { authApis, endpoints } from '../Configs/Apis';
import { MyUserContext } from '../Configs/MyContexts';
import cookie from 'react-cookies';

const DoctorDetail = () => {
  const [q] = useSearchParams();
  const user = useContext(MyUserContext);
  const nav = useNavigate();

  const [userReview, setUserReview] = useState({
    rating: 0,
    comment: '',
    userComment: null,
    disabled: false,
    showReply: false, 
  });
  const [doctorDetail, setDoctorDetail] = useState(null);

  const fetchDoctorDetail = async () => {
    try {
      const res = await Apis.get(`${endpoints['getBacSiChiTiet']}/${q.get("doctorId")}`);
      const reviews = res.data?.danhGiasDTO || [];
      const currentUserReview = reviews.find(review => review.tkDTO.id === user?.user?.id);

      if (currentUserReview) {
        setUserReview({
          rating: currentUserReview.soSao,
          comment: currentUserReview.binhLuan,
          userComment: currentUserReview,
          disabled: true,
          showReply: false,
        });
        res.data.danhGiasDTO = reviews.filter(review => review.tkDTO.id !== user?.user?.id);
      }
      setDoctorDetail(res.data);
    } catch (error) {
      console.error("Error fetching doctor detail:", error);
      alert("Lỗi khi lấy thông tin bác sĩ");
    }
  };

  useEffect(() => {
    fetchDoctorDetail();
  }, [q, user]);

  const handleSubmit = async () => {
    if (!userReview.rating || !userReview.comment.trim()) {
      alert("Vui lòng chọn số sao và viết bình luận!");
      return;
    }

    try {
      const payload = {
        bsId: q.get("doctorId"),
        soSao: userReview.rating,
        binhLuan: userReview.comment,
        tkId: user?.user?.id,
        ngayTao: new Date().toISOString().split("T")[0],
        chinhSua: userReview.userComment ? true : false,
        phanHoi: userReview.userComment?.phanHoi || null,
      };
      const token = cookie.load('token');
      await authApis(token).post(endpoints['themDanhGia'], payload);
      setUserReview(prev => ({ ...prev, disabled: true, showReply: false }));
      await fetchDoctorDetail();
    } catch (error) {
      console.error("Error submitting review:", error);
      alert("Gửi đánh giá thất bại: " + (error.response?.data?.message || error.message));
    }
  };

  const handleStarClick = (star) => {
    setUserReview(prev => ({ ...prev, rating: star }));
  };

  const handleCommentChange = (e) => {
    setUserReview(prev => ({ ...prev, comment: e.target.value }));
  };

  const toggleUserReply = () => {
    setUserReview(prev => ({ ...prev, showReply: !prev.showReply }));
  };

  return (
    <div className="dt-container">
      <div className="dt-profile-card">
        <div className="dt-profile-image">
          <img src={doctorDetail?.avatar} alt="Doctor Icon" />
        </div>
        <div className="dt-profile-info">
          <h2>{doctorDetail?.hoNguoiDung + " " + doctorDetail?.tenNguoiDung}</h2>
          <p><strong>Tên bệnh viện:</strong>{doctorDetail?.bvDTO?.tenBenhVien} </p>
          <p>
            <strong>Nhận xét: </strong>
            {
              (doctorDetail?.soSao !== undefined && doctorDetail?.soSao !== null)
                ? (Number.isInteger(doctorDetail.soSao)
                  ? doctorDetail.soSao
                  : doctorDetail.soSao.toFixed(2).replace(/\.?0+$/, ''))
                : 0
            }  ★ || {doctorDetail?.danhGiasDTO?.length || 0} đánh giá
          </p>
        </div>
      </div>

      {user?.user?.role === "ROLE_USER" && (
        <div className="dt-comment-section">
          <h3>Đánh giá và bình luận</h3>
          <div className="dt-rating">
            {[1, 2, 3, 4, 5].map((star) => (
              <span
                key={star}
                className={`dt-star ${star <= userReview.rating ? 'dt-filled' : ''}`}
                {...(userReview.disabled ? {} : { onClick: () => handleStarClick(star) })}
              >
                ★
              </span>
            ))}
          </div>
          <textarea
            className="dt-textarea"
            placeholder="Viết bình luận của bạn..."
            value={userReview.comment}
            onChange={handleCommentChange}
            disabled={userReview.disabled}
          ></textarea>
          {userReview.disabled ? (
            <button
              onClick={() => setUserReview(prev => ({ ...prev, disabled: false }))}
              className="dt-submit-button"
            >
              Chỉnh sửa
            </button>
          ) : (
            <button
              className="dt-submit-button"
              onClick={user ? handleSubmit : () => nav("/login")}
            >
              Gửi bình luận
            </button>
          )}
          {userReview.userComment?.phanHoi && userReview.disabled && (
            <>
              <button
                className="dt-reply-toggle"
                onClick={toggleUserReply}
              >
                {userReview.showReply ? "Ẩn phản hồi của bác sĩ" : "Xem phản hồi của bác sĩ"}
              </button>
              {userReview.showReply && (
                <div className="dt-doctor-reply">
                  <strong>Phản hồi của bác sĩ:</strong>
                  <div>{userReview.userComment.phanHoi}</div>
                </div>
              )}
            </>
          )}
        </div>
      )}

      <div className="dt-existing-reviews">
        <h3>Các đánh giá trước đó</h3>
        {doctorDetail?.danhGiasDTO?.map((review, index) => (
          <div key={index}>
            <ReviewWithReply review={review} />
            <hr style={{ border: '2px solid #bbb', margin: '16px 0' }} />
          </div>
        ))}
      </div>
    </div>
  );
};

const ReviewWithReply = ({ review }) => {
  const [showReply, setShowReply] = useState(false);
  const [showReplyDoctor, setShowReplyDoctor] = useState(false);
  const [doctorReply, setDoctorReply] = useState('');
  const user = useContext(MyUserContext);

  const handleDoctorReply = async () => {
    if (!doctorReply.trim()) {
      alert("Vui lòng nhập phản hồi!");
      return;
    }

    try {
      await Apis.put(
        endpoints['phanHoiDanhGia'],
        { id: review.id, phanHoi: doctorReply },
        { headers: { 'Content-Type': 'application/json' } }
      );
      alert("Phản hồi thành công!");
      setShowReplyDoctor(false);
      setDoctorReply('');
    } catch (error) {
      console.error("Error responding to review:", error);
      alert("Đã có lỗi xảy ra: " + (error.response?.data?.message || error.message));
    }
  };

  return (
    <div className="dt-review-item">
      <div className="dt-review-header">
        <img
          src={review.tkDTO?.avatar || '/default-avatar.png'}
          alt="Avatar"
          className="dt-avatar"
        />
        <div>
          <p><strong>Người đánh giá:</strong> {review.tkDTO.hoNguoiDung + " " + review.tkDTO.tenNguoiDung}</p>
          <p><strong>Đánh giá:</strong> {review.soSao} ★</p>
          <p><strong>Bình luận:</strong> {review.binhLuan}</p>
          <p>
            <small>
              {review.chinhSua ? "Đã chỉnh sửa" : ""}
            </small>
            <span> </span>
            <small>
              Ngày: {review.ngayTao ? new Date(review.ngayTao).toLocaleString('vi-VN', { hour12: false }) : ''}
            </small>
          </p>
          {review.phanHoi && (
            <button
              className="dt-reply-toggle"
              onClick={() => setShowReply(!showReply)}
            >
              {showReply ? "Ẩn phản hồi của bác sĩ" : "Xem phản hồi của bác sĩ"}
            </button>
          )}
          {(showReply && review.phanHoi) ? (
            <div className="dt-doctor-reply">
              <strong>Phản hồi của bác sĩ:</strong>
              <div>{review.phanHoi}</div>
            </div>
          ) : (
            user?.user?.role === "ROLE_DOCTOR" && !review.phanHoi && (
              <button onClick={() => setShowReplyDoctor(true)}>Phản hồi</button>
            )
          )}
          {showReplyDoctor && (
            <div className="dt-doctor-reply">
              <strong>Phản hồi của bác sĩ:</strong>
              <textarea
                className="dt-textarea"
                placeholder="Viết phản hồi của bạn..."
                value={doctorReply}
                onChange={(e) => setDoctorReply(e.target.value)}
              ></textarea>
              <button onClick={handleDoctorReply}>Gửi phản hồi</button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default DoctorDetail;