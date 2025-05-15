/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.repositories.ChuyenKhoaRepository;
import com.nnhp.services.ChuyenKhoaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class ChuyenKhoaServiceImpl implements ChuyenKhoaService{
    @Autowired
    private ChuyenKhoaRepository chuyenKhoaRepo;
    
    @Override
    public List<Chuyenkhoa> getDsChuyenKhoa(Map<String, String> params) {
        return this.chuyenKhoaRepo.getDsChuyenKhoa(params);
    }

    @Override
    public Chuyenkhoa getChuyenKhoaById(int id) {
        return this.chuyenKhoaRepo.getChuyenKhoaById(id);
    }

    @Override
    public Chuyenkhoa addOrUpdateChuyenKhoa(Chuyenkhoa c) {
        return this.chuyenKhoaRepo.addOrUpdateChuyenKhoa(c);
    }

    @Override
    public void deleteChuyenKhoa(int id) {
        this.chuyenKhoaRepo.deleteChuyenKhoa(id);
    }

    @Override
    public List<Chuyenkhoa> getChuyenKhoaByBenhVien(int bvId) {
        return this.chuyenKhoaRepo.getChuyenKhoaByBenhVien(bvId);
    }
}
