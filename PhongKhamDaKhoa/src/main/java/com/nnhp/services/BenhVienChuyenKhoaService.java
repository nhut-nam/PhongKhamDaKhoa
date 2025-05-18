/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Benhvienchuyenkhoa;
import java.util.List;

/**
 *
 * @author hoang
 */
public interface BenhVienChuyenKhoaService {
    List<Benhvienchuyenkhoa> getAll();
    Benhvienchuyenkhoa getById(int id);
    Benhvienchuyenkhoa addOrUpdate(Benhvienchuyenkhoa bvck);
    boolean delete(int id);
    List<Benhvienchuyenkhoa> getByBenhVienId(int benhVienId);
    List<Benhvienchuyenkhoa> getByChuyenKhoaId(int chuyenKhoaId);
    List<Benhvienchuyenkhoa> getDsBenhVienChuyenKhoa();
    Boolean tonTaiBenhVienIdChuyenKhoaId(int benhVienId, int chuyenKhoaId);
} 