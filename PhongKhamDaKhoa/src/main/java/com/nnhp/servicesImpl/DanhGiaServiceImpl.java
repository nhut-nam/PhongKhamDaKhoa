/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.dto.DanhGiaRequestDTO;
import com.nnhp.pojo.Danhgia;
import com.nnhp.repositories.BacSiRepository;
import com.nnhp.repositories.DanhGiaRepository;
import com.nnhp.repositories.TaiKhoanRepository;
import com.nnhp.services.DanhGiaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author namnh
 */
@Service
public class DanhGiaServiceImpl implements DanhGiaService {
    @Autowired
    private DanhGiaRepository dgRepo;
    @Autowired
    private TaiKhoanRepository tkRepo;
    @Autowired
    private BacSiRepository bsRepo;

    @Override
    public List<Danhgia> getDanhGiaByBacSiId(int id) {
        return this.dgRepo.getDanhGiaByBacSiId(id);
    }

    @Override
    public Double averageSoSao(int id) {
        return this.dgRepo.averageSoSao(id);
    }

    @Override
    public Danhgia addOrUpdateDanhGia(Danhgia dg) {
        return this.dgRepo.addOrUpdateDanhGia(dg);
    }

    @Override
    public Danhgia addOrUpdateDanhGiaFromDTO(DanhGiaRequestDTO dgDTO) {
        Danhgia dg = new Danhgia();
        dg.setBinhLuan(dgDTO.getBinhLuan());
        dg.setChinhSua(dgDTO.getChinhSua());
        dg.setNgayTao(new Date());
        dg.setPhanHoi(dgDTO.getPhanHoi());
        dg.setSoSao(dgDTO.getSoSao());
        dg.setBacsiId(this.bsRepo.getBacSiById(dgDTO.getBsId()));
        dg.setTaikhoanId(this.tkRepo.getUserById(dgDTO.getTkId()));
        return this.dgRepo.addOrUpdateDanhGia(dg);
    }

    @Override
    public Danhgia getDanhGiaById(int id) {
        return this.dgRepo.getDanhGiaById(id);
    }
    
}
