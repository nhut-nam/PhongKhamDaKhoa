/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Bacsidichvu;
import com.nnhp.repositories.BacSiDichVuRepository;
import com.nnhp.services.BacSiDichVuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author namnh
 */
@Service
public class BacSiDichVuServiceImpl implements BacSiDichVuService {
    @Autowired
    private BacSiDichVuRepository bsdvRepo;

    @Override
    public List<Bacsidichvu> getBacSiDichVuByBenhVienChuyenKhoaDichVu(int id) {
        return this.bsdvRepo.getBacSiDichVuByBenhVienChuyenKhoaDichVu(id);
    }
    
}
