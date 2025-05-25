/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Lichsukhambenh;
import com.nnhp.repositories.LichSuKhamBenhRepository;
import com.nnhp.services.LichSuKhamBenhService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class LichSuKhamBenhServiceImpl implements LichSuKhamBenhService{
    @Autowired
    private LichSuKhamBenhRepository lichSuKhamRepo;
    @Override
    public List<Lichsukhambenh> getLichSuKhamBenhByUser(int hoSoId) {
        return this.lichSuKhamRepo.getLichSuKhamBenhByUser(hoSoId);
    }

    @Override
    public Lichsukhambenh updateLichSuKhamBenh(int id, Map<String, String> payload) {
        Lichsukhambenh lichSu = this.lichSuKhamRepo.getLichSuKhamBenhById(id);
        String chuanDoan = payload.get("chuanDoan");
        String ketQuaXetNghiem = payload.get("ketQuaXetNghiem");
        
        lichSu.setChanDoan(chuanDoan);
        lichSu.setKetQuaXetNghiem(ketQuaXetNghiem);
        this.lichSuKhamRepo.updateLichSuKhamBenh(lichSu);
        return lichSu;
    }

 
  
}
