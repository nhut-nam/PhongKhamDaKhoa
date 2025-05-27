/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.dto.LichKhamBacSiDTO;
import com.nnhp.pojo.Lichkham;
import jakarta.data.page.Pageable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface LichKhamRepository {
    List<Lichkham> getDsLichKham(Map<String, String> params);
    Lichkham getLichKhamById(int id);
    Lichkham addOrUpdateLichKham(Lichkham lichKham);
    void deleteLichKham(int id);
    List<LichKhamBacSiDTO> getLichKhamByBacSi(Integer bacSiId, LocalDate date, Map<String, String> params);
    List<Lichkham> getLichKhamListByUserId(int userId);
    List<Lichkham> getLichKhamListByBacSiIdAndNgayKhamAndBuoi(int id, Date ngayKham, String buoi);
} 