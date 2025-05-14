/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Chuyenkhoa;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface ChuyenKhoaService {
    List<Chuyenkhoa> getDsChuyenKhoa(Map<String, String> params);
    Chuyenkhoa getChuyenKhoaById(int id);
    Chuyenkhoa addOrUpdateChuyenKhoa(Chuyenkhoa c);
    void deleteChuyenKhoa(int id);
    List<Chuyenkhoa> getChuyenKhoaByBenhVien(int bvId);
}
