/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.dto.DanhGiaRequestDTO;
import com.nnhp.pojo.Danhgia;
import java.util.List;

/**
 *
 * @author namnh
 */
public interface DanhGiaService {
    List<Danhgia> getDanhGiaByBacSiId(int id);
    Double averageSoSao(int id);
    Danhgia addOrUpdateDanhGia(Danhgia dg);
    Danhgia addOrUpdateDanhGiaFromDTO(DanhGiaRequestDTO dgDTO);
    Danhgia getDanhGiaById(int id);
}
