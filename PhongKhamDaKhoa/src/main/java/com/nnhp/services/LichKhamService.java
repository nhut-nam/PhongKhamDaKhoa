/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.dto.LichKhamBacSiDTO;
import com.nnhp.dto.LichKhamRequestDTO;
import com.nnhp.pojo.Lichkham;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface LichKhamService {
    List<Lichkham> getDsLichKham(Map<String, String> params);
    Lichkham getLichKhamById(int id);
    Lichkham addOrUpdateLichKham(Lichkham lichKham);
    void deleteLichKham(int id);
    List<LichKhamBacSiDTO> getLichKhamByBacSi(Integer bacSiId, LocalDate date, Map<String, String> params);
    Lichkham createLichKhamFromDTO(LichKhamRequestDTO dto);
    List<Lichkham> getLichKhamListByUserId(int userId);
} 