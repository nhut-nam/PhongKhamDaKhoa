import React, { useEffect, useState } from 'react';
import '../Styles/SelectSpecialty.css';


const SelectSpecialty = (specialty) => {

  return (
    <div className="specialty-list">
      {/* {specializedHospital.map((s, i) => (
        <div onClick={() => handleSpecialtyClick(s)} className="specialty-item" key={i}>
          <strong>{s.chuyenKhoa.tenChuyenKhoa}</strong>
        </div>
      ))} */}
    </div>
  );
};

export default SelectSpecialty;
