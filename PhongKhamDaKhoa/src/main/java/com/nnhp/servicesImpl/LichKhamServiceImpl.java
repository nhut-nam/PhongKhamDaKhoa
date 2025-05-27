/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.dto.LichKhamBacSiDTO;
import com.nnhp.dto.LichKhamRequestDTO;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import com.nnhp.pojo.Hoso;
import com.nnhp.pojo.Lichkham;
import com.nnhp.repositories.BacSiRepository;
import com.nnhp.repositories.BenhVienChuyenKhoaDichVuRepository;
import com.nnhp.repositories.HoSoRepository;
import com.nnhp.repositories.LichKhamRepository;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BenhVienChuyenKhoaDichVuService;
import com.nnhp.services.EmailService;
import com.nnhp.services.HoSoService;
import com.nnhp.services.LichKhamService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    private final static int LIMIT = 10;
    @Autowired
    private LichKhamRepository lichKhamRepo;
    @Autowired
    private EmailService senMail;
    @Autowired
    private BenhVienChuyenKhoaDichVuRepository bvckdvRepo;
    @Autowired
    private HoSoRepository hsRepo;
    @Autowired
    private BacSiRepository bacSiRepo;
    
    
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

    @Override
    public List<LichKhamBacSiDTO> getLichKhamByBacSi(Integer bacSiId, LocalDate date, Map<String, String> params) {
         try {
            return this.lichKhamRepo.getLichKhamByBacSi(bacSiId,date,params);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Lichkham createLichKhamFromDTO(LichKhamRequestDTO dto) {
        BenhVienChuyenKhoaDichVu bvckdv = this.bvckdvRepo.getBenhVienChuyenKhoaDichVuById(dto.getBenhVienChuyenKhoaDichVuId());
        Hoso hs = this.hsRepo.getHoSoById(dto.getHoSoId());
        Bacsi bs = this.bacSiRepo.getBacSiById(dto.getBacSiId());
        Lichkham lk = new Lichkham();
        lk.setBacsiId(bs);
        lk.setBenhvienchuyenkhoadichvuId(bvckdv);
        lk.setHosoId(hs);
        lk.setBuoi(dto.getBuoi());
        lk.setNgayHen(dto.getNgayHen());
        lk.setNgayTao(new Date());
        lk.setTrangThai(dto.getTrangThai());
        lk.setSoTienNhan(dto.getSoTienNhan());
        if (this.lichKhamRepo.getLichKhamListByBacSiIdAndNgayKhamAndBuoi(bs.getId(), dto.getNgayHen(), dto.getBuoi()).size()> LIMIT) {
            return null;
        }
        return this.lichKhamRepo.addOrUpdateLichKham(lk);
    }

    @Override
    public List<Lichkham> getLichKhamListByUserId(int userId) {
        return this.lichKhamRepo.getLichKhamListByUserId(userId);
    }

    @Override
    public List<Lichkham> getLichKhamListByBacSiIdAndNgayKhamAndBuoi(int id, Date ngayKham, String buoi) {
        return this.lichKhamRepo.getLichKhamListByBacSiIdAndNgayKhamAndBuoi(id, ngayKham, buoi);
    }
} 