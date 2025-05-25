import React, { useState } from 'react';
import { authApis } from '../Configs/Apis';
import { endpoints } from '../Configs/Apis';

function HoSoEditForm({ initialData, onSave, onCancel }) {
    const [formData, setFormData] = useState({
        chuanDoan: initialData.chuanDoan || '',
        ketQuaXetNghiem: initialData.ketQuaXetNghiem || '',
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const api = authApis();
            const endpoint = endpoints.lichSuKhamBenh.replace('{id}', initialData.id);  // Lấy ID từ initialData
            
            const payload = {
            chuanDoan: formData.chuanDoan,
            ketQuaXetNghiem: formData.ketQuaXetNghiem,
            };
            console.log("Dữ liệu gửi đi:", payload);
            const res = await api.put(endpoint,payload, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            console.log('Updated:', res.data);
            onSave(payload); // Gọi callback khi cập nhật thành công
        } catch (err) {
            console.error('Lỗi khi cập nhật hồ sơ:', err);
            alert('Đã xảy ra lỗi khi cập nhật!');
        }
    };

    return (
        <form className="hoso-edit-form" onSubmit={handleSubmit}>
            <div className="info-row">
                <label className="info-label" htmlFor="chuanDoan">🩺 Chẩn đoán:</label>
                <textarea
                    id="chuanDoan"
                    name="chuanDoan"
                    value={formData.chuanDoan}
                    onChange={handleInputChange}
                    rows={2}
                    className="info-input"
                />
            </div>
            <div className="info-row">
                <label className="info-label" htmlFor="ketQuaXetNghiem">🔬 Kết quả xét nghiệm:</label>
                <textarea
                    id="ketQuaXetNghiem"
                    name="ketQuaXetNghiem"
                    value={formData.ketQuaXetNghiem}
                    onChange={handleInputChange}
                    rows={2}
                    className="info-input"
                />
            </div>

            <div className="btn-group">
                <button type="submit" className="btn-save">Lưu</button>
                <button type="button" className="btn-cancel" onClick={onCancel}>Hủy</button>
            </div>
        </form>
    );
}

export default HoSoEditForm;
