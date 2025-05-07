/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Benhvien;
import com.nnhp.repositories.BenhVienRepository;
import com.nnhp.services.BenhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author namnh
 */
@Service
public class BenhVienServiceImpl implements BenhVienService {
    @Autowired
    private BenhVienRepository bvRepo;
    
    @Override
    public Benhvien getBenhVienById(Integer id) {
        return this.bvRepo.getBenhVienById(id);
    }
    
}
