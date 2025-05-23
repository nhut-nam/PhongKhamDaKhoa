/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import com.nnhp.repositories.BenhVienChuyenKhoaDichVuRepository;
import com.nnhp.services.BenhVienChuyenKhoaDichVuService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class BenhVienChuyenKhoaDichVuServiceImpl implements BenhVienChuyenKhoaDichVuService {
    @Autowired
    private BenhVienChuyenKhoaDichVuRepository benhVienChuyenKhoaDichVuRepo;
    
    @Override
    public List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVu() {
        return this.getDsBenhVienChuyenKhoaDichVu(null);
    }

    @Override
    public List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVu(Map<String, String> params) {
        try {
            return this.benhVienChuyenKhoaDichVuRepo.getDsBenhVienChuyenKhoaDichVu(params);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Trả về danh sách trống nếu có lỗi
            return new ArrayList<>();
        }
    }

    @Override
    public BenhVienChuyenKhoaDichVu getBenhVienChuyenKhoaDichVuById(int id) {
        try {
            return this.benhVienChuyenKhoaDichVuRepo.getBenhVienChuyenKhoaDichVuById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVuByBenhVienChuyenKhoaId(int id) {
        return this.benhVienChuyenKhoaDichVuRepo.getDsBenhVienChuyenKhoaDichVuByBenhVienChuyenKhoaId(id);
    }
} 