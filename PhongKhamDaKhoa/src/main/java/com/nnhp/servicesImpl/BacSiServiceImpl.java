/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.dto.BacSiDanhGiaDTO;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.pojo.Lichkham;
import com.nnhp.repositories.BacSiRepository;
import com.nnhp.repositories.LichKhamRepository;
import com.nnhp.services.BacSiService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
    @Autowired
    private LichKhamRepository lichKhamRepo;
    
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

    @Override
    public List<Chuyenkhoa> getChuyenKhoaByBacSiId(int id) {
        return this.bacSiRepo.getChuyenKhoaByBacSiId(id);
    }

    @Override
    public BacSiDanhGiaDTO getBacSiWithDanhGiaById(int id) {
        return this.bacSiRepo.getBacSiWithDanhGiaById(id);
    }

    @Override
    public Set<Bacsi> getListBacSiByUserId(int id) {
        return this.bacSiRepo.getListBacSiByUserId(id);
    }

}
