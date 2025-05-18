/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.repositories.BenhNhanRepository;
import com.nnhp.services.BenhNhanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class BenhNhanServiceImpl implements BenhNhanService {
    @Autowired
    private BenhNhanRepository benhNhanRepo;
    
    @Override
    public List<Benhnhan> getDsBenhNhan() {
        return this.benhNhanRepo.getDsBenhNhan();
    }
    
    @Override
    public List<Benhnhan> getDsBenhNhan(Map<String, String> params) {
        return this.benhNhanRepo.getDsBenhNhan(params);
    }
    
    @Override
    public Benhnhan getBenhNhanById(int id) {
        return this.benhNhanRepo.getBenhNhanById(id);
    }
    
    @Override
    public Benhnhan addOrUpdateBenhNhan(Benhnhan bn) {
        return this.benhNhanRepo.addOrUpdateBenhNhan(bn);
    }
    
    @Override
    public boolean kiemTraBenhNhanTonTai(int id) {
        return this.benhNhanRepo.getBenhNhanById(id) != null;
    }
} 