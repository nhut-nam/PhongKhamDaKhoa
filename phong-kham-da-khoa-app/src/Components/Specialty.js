import React from "react";

const Specialty = () => {
    return (
        <div className="specialty">

            <div className="results">
                <div className="result-card">
                    <div className="icon ghi">TGA</div>
                    <div className="info">
                        <h3>TIÊU HÓA GAN MẠT</h3>
                        <p>󾍃 Bệnh viện Đại học Y Dược TP.HCM <span className="verified">&#10003;</span></p>
                    </div>
                </div>

                <div className="result-card">
                    <div className="icon nhi">NS</div>
                    <div className="info">
                        <h3>Ngoại tiêu hóa - Gan mật</h3>
                        <p>󾍃 Bệnh viện Nhi Đồng 1 <span className="verified">&#10003;</span></p>
                    </div>
                </div>

                <div className="result-card">
                    <div className="icon pink">+</div>
                    <div className="info">
                        <h3>Nội Tiêu Hóa - Gan Mật</h3>
                        <p>󾍃 Bệnh viện Đa khoa Vạn Hạnh</p>
                    </div>
                </div>

                <div className="result-card">
                    <div className="icon blue">H</div>
                    <div className="info">
                        <h3>TIÊU HÓA, GAN MẠT</h3>
                        <p>󾍃 Bệnh viện Quốc tế Minh Anh</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Specialty;
