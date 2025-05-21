/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nnhp.pojo.Benhvien;
import com.nnhp.repositories.BenhVienRepository;
import com.nnhp.services.BenhVienService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Autowired
    private Cloudinary cloudinary;

    
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
        
        if (!bv.getFile().isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(bv.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                bv.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(BenhVienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return this.benhVienRepo.addOrUpdateBenhVien(bv);
    }
    
    @Override
    public void deleteBenhVien(int id) {
        this.benhVienRepo.deleteBenhVien(id);
    }
}
