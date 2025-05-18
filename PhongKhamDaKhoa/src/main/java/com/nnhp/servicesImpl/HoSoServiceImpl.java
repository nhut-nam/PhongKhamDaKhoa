/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Hoso;
import com.nnhp.repositories.HoSoRepository;
import com.nnhp.services.HoSoService;
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
public class HoSoServiceImpl implements HoSoService {
    @Autowired
    private HoSoRepository hoSoRepo;
    
    @Override
    public List<Hoso> getDsHoSo(Map<String, String> params) {
        try {
            return this.hoSoRepo.getDsHoSo(params);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Hoso getHoSoById(int id) {
        try {
            return this.hoSoRepo.getHoSoById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Hoso addOrUpdateHoSo(Hoso hoSo) {
        try {
            return this.hoSoRepo.addOrUpdateHoSo(hoSo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteHoSo(int id) {
        try {
            this.hoSoRepo.deleteHoSo(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean kiemTraHoSoTonTai(int id) {
        try {
            return this.hoSoRepo.kiemTraHoSoTonTai(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
} 