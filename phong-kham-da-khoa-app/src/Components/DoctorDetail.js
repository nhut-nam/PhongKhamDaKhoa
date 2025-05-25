import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import '../Styles/DoctorDetail.css'
import Apis, { endpoints } from '../Configs/Apis';

const DoctorDetail = () => {
    const [q] = useSearchParams()
    const [rating, setRating] = useState(0);
    const [comment, setComment] = useState('');
    const [doctorDetail, setDoctorDetail] = useState([])

    useEffect(() => {
        const fetchDoctorDetail = async () => {
            try {
                const res = await Apis.get(endpoints['getBacSiChiTiet'] + `/${q.get("doctorId")}`)
                setDoctorDetail(res.data)
                console.log(res.data)
            } catch {
                alert("Error")
            }
        }
        fetchDoctorDetail()
    }, [])

    const handleStarClick = (star) => {
        setRating(star);
    };

    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    const handleSubmit = () => {
        alert(`Đánh giá: ${rating} sao\nBình luận: ${comment}`);
        // const addComment = async () => {
        //     try {
        //         const res = await Apis.post()
        //     }
        // }
        setComment('');
        setRating(0);
    };

    return (
        <div className="dt-container">
            <div className="profile-card">
                <div className="profile-image">
                    <img src={doctorDetail?.avatar} alt="Doctor Icon" />
                </div>
                <div className="profile-info">
                    <h2>{doctorDetail?.hoNguoiDung + " " + doctorDetail?.tenNguoiDung}</h2>
                    <p><strong>Tên bệnh viện:</strong>{doctorDetail?.bvDTO?.tenBenhVien} </p>
                    <p></p>
                </div>
            </div>

            <div className="comment-section">
                <h3>Đánh giá và bình luận</h3>
                <div className="rating">
                    {[1, 2, 3, 4, 5].map((star) => (
                        <span
                            key={star}
                            className={`star ${star <= rating ? 'filled' : ''}`}
                            onClick={() => handleStarClick(star)}
                        >
                            ★
                        </span>
                    ))}
                </div>
                <textarea
                    placeholder="Viết bình luận của bạn..."
                    value={comment}
                    onChange={handleCommentChange}
                ></textarea>
                <button onClick={handleSubmit}>Gửi bình luận</button>
            </div>
        </div>
    );
};

export default DoctorDetail