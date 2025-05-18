/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Lichkham;
import com.nnhp.repositories.LichKhamRepository;
import com.nnhp.services.LichKhamService;
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
public class LichKhamServiceImpl implements LichKhamService {
    @Autowired
    private LichKhamRepository lichKhamRepo;
    
    @Override
    public List<Lichkham> getDsLichKham(Map<String, String> params) {
        try {
            return this.lichKhamRepo.getDsLichKham(params);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Lichkham getLichKhamById(int id) {
        try {
            return this.lichKhamRepo.getLichKhamById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Lichkham addOrUpdateLichKham(Lichkham lichKham) {
        try {
            return this.lichKhamRepo.addOrUpdateLichKham(lichKham);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteLichKham(int id) {
        try {
            this.lichKhamRepo.deleteLichKham(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} 