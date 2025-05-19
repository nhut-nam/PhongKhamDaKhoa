/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import java.util.ArrayList;
import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Hoso;
import com.nnhp.repositories.HoSoRepository;
import com.nnhp.services.HoSoService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public Hoso addHoSo(Map<String, Object> params, Benhnhan bn) {
        Hoso hs = new Hoso();
        boolean gioiTinh = Integer.parseInt((String)params.get("gioi_tinh")) == 1;
        hs.setGioiTinh(gioiTinh);
        try {
                hs.setNgaySinh(Formatter.DATE_FORMATTER.parse((String)params.get("ngay_sinh")));
            } catch (ParseException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        hs.setNgayTao(new Date());
        hs.setDiaChi((String)params.get("dia_chi"));
        hs.setEmail((String)params.get("email"));
        hs.setHoTen((String)params.get("ho_ten"));
        hs.setSoDienThoai((String)params.get("so_dien_thoai"));
        hs.setBenhnhanId(bn);
        return this.hoSoRepo.addHoSo(hs);
    }

    @Override
    public List<Hoso> getHoSoList(int id) {
        return this.hoSoRepo.getHoSoList(id);
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
