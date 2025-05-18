/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Dichvu;
import com.nnhp.repositories.DichVuRepository;
import com.nnhp.services.DichVuService;
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
public class DichVuServiceImpl implements DichVuService {
    @Autowired
    private DichVuRepository dichVuRepo;
    
    @Override
    public List<Dichvu> getDsDichVu(Map<String, String> params) {
        try {
            return this.dichVuRepo.getDsDichVu(params);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Dichvu getDichVuById(int id) {
        try {
            return this.dichVuRepo.getDichVuById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Dichvu addOrUpdateDichVu(Dichvu dichVu) {
        try {
            return this.dichVuRepo.addOrUpdateDichVu(dichVu);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteDichVu(int id) {
        try {
            this.dichVuRepo.deleteDichVu(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} 