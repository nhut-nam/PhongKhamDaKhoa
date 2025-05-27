/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.TaiKhoanTinTuc;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.Tintuc;
import com.nnhp.repositories.TaiKhoanTinTucRepository;
import com.nnhp.services.TaiKhoanTinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author namnh
 */
@Service
public class TaiKhoanTinTucServiceImpl implements TaiKhoanTinTucService {
    @Autowired
    private TaiKhoanTinTucRepository tkttRepo;

    @Override
    public TaiKhoanTinTuc addOrUpdateTaiKhoanTinTuc(Taikhoan tk, Tintuc tt) {
        TaiKhoanTinTuc tttk = new TaiKhoanTinTuc();
        tttk.setTaikhoanId(tk);
        tttk.setTintucId(tt);
        tttk.setTrangThai(false);
        return this.tkttRepo.addOrUpdateTaiKhoanTinTuc(tttk);
    }
}
