import React from "react";
import "../Styles/MedicalExamination.css";
import { useState } from "react";

const tabs = [
    { key: "paid", label: "Đã thanh toán" },
    { key: "unpaid", label: "Chưa thanh toán" },
    { key: "done", label: "Đã khám" },
    { key: "cancelled", label: "Đã huỷ" },
];

const MedicalExamination = () => {
    const [activeTab, setActiveTab] = useState("paid")
    return (
        <>
            <h2 className="title">Danh sách phiếu khám bệnh</h2>
            <div className="tabs">
                {tabs.map((tab) => (
                    <button
                        key={tab.key}
                        onClick={() => setActiveTab(tab.key)}
                        className={`tab-button ${activeTab === tab.key ? "active" : ""
                            }`}
                    >
                        {tab.label}
                    </button>
                ))}
            </div>

            <div className="no-data">
                <p className="no-data-text">Bạn chưa có phiếu khám nào</p>
                <img
                    src="/no-data-illustration.png"
                    alt="No tickets"
                    className="no-data-image"
                />
            </div>
        </>
    );
};

export default MedicalExamination;
