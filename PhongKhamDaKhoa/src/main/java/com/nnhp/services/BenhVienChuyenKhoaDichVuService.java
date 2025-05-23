/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface BenhVienChuyenKhoaDichVuService {
    List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVu();
    List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVu(Map<String, String> params);
    BenhVienChuyenKhoaDichVu getBenhVienChuyenKhoaDichVuById(int id);
    List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVuByBenhVienChuyenKhoaId(int id);
} 