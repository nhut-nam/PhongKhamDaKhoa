/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Benhvien;
import com.nnhp.repositories.BenhVienRepository;
import com.nnhp.services.BenhVienService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class BenhVienServiceImpl implements BenhVienService{
    @Autowired
    private BenhVienRepository benhVienRepo;

    @Override
    public List<Benhvien> getDsBenhVien(Map<String, String> params) {
       return this.benhVienRepo.getDsBenhVien(params);
    }

    @Override
    public Benhvien getBenhVienById(int id) {
       return this.benhVienRepo.getBenhVienById(id);
    }

    @Override
    public List<Benhvien> getAllBenhVien() {
        return this.benhVienRepo.getAllBenhVien();
    }
    
    @Override
    public Benhvien addOrUpdateBenhVien(Benhvien bv) {
        return this.benhVienRepo.addOrUpdateBenhVien(bv);
    }
    
    @Override
    public void deleteBenhVien(int id) {
        this.benhVienRepo.deleteBenhVien(id);
    }
}
