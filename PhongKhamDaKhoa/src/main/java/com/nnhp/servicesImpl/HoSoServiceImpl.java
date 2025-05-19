/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

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
 * @author namnh
 */
@Service
public class HoSoServiceImpl implements HoSoService {
    @Autowired
    private HoSoRepository hsRepo;

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
        return this.hsRepo.addHoSo(hs);
    }

    @Override
    public List<Hoso> getHoSoList(int id) {
        return this.hsRepo.getHoSoList(id);
    }

    @Override
    public void deleteHoSo(int id) {
        this.hsRepo.deleteHoSo(id);
    }

    @Override
    public Hoso getHoSoById(int id) {
        return this.hsRepo.getHoSoById(id);
    }

    @Override
    public Hoso updateHoSO(Hoso hs) {
        return this.hsRepo.updateHoSo(hs);
    }
    
}
