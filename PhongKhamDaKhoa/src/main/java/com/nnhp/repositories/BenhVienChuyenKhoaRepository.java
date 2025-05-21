/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Benhvienchuyenkhoa;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface BenhVienChuyenKhoaRepository {
    List<Benhvienchuyenkhoa> getAll();
    Benhvienchuyenkhoa getById(int id);
    Benhvienchuyenkhoa addOrUpdate(Benhvienchuyenkhoa bvck);
    boolean delete(int id);
    List<Benhvienchuyenkhoa> getByBenhVienId(int benhVienId);
    List<Benhvienchuyenkhoa> getByChuyenKhoaId(int chuyenKhoaId);
    Boolean tonTaiBenhVienIdChuyenKhoaId(int benhVienId, int chuyenKhoaId);
    List<Benhvienchuyenkhoa> getDsBenhVien(Map<String, String> params);
} 