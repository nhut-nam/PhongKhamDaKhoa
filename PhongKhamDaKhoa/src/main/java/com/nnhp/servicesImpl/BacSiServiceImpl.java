/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Bacsi;
import com.nnhp.repositories.BacSiRepository;
import com.nnhp.services.BacSiService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class BacSiServiceImpl implements BacSiService{
    
    @Autowired
    private BacSiRepository bacSiRepo;
    
    @Override
    public List<Bacsi> getDsBacSi(Map<String, String> params) {
       return this.bacSiRepo.getDsBacSi(params);
    }

    @Override
    public Bacsi getBacSiById(int id) {
        return this.bacSiRepo.getBacSiById(id);
    }

    @Override
    public Bacsi addOrUpdateBacSi(Bacsi b) {
        return this.bacSiRepo.addOrUpdateBacSi(b);
    }

    @Override
    public void deleteBacSi(int id) {
        this.bacSiRepo.deleteBacSi(id);
    }
    
}
