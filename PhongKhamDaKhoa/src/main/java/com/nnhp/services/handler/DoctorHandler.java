/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services.handler;

import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.services.BenhVienService;
import com.nnhp.servicesImpl.TaiKhoanServiceImpl;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author namnh
 */
@Component("DOCTOR")
public class DoctorHandler implements RoleHandler {
    @Autowired
    private BenhVienService bvService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Taikhoan handle(Map<String, String> params) {
        Bacsi tk = new Bacsi();
        tk.setHoNguoiDung(params.get("hoNguoiDung"));
        tk.setTenNguoiDung(params.get("tenNguoiDung"));
        tk.setDiaChi(params.get("diaChi"));
        tk.setEmail(params.get("email"));
        tk.setMatKhau(this.passwordEncoder.encode(params.get("matKhau")));
        tk.setGhiChu(params.get("ghiChu"));
        try {
            tk.setNgaySinh(Formatter.DATE_FORMATTER.parse(params.get("ngaySinh")));
            tk.setNgayLamViec(Formatter.DATE_FORMATTER.parse(params.get("ngayLamViec")));
        } catch (ParseException ex) {
            Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        tk.setSoDienThoai(params.get("soDienThoai"));
        tk.setRole("DOCTOR");
        tk.setChuyenTri(params.get("chuyenTri"));
        tk.setNgayNghiViec(null);
        tk.setTrangThai(Boolean.TRUE);
        tk.setBenhvienId(this.bvService.getBenhVienById(Integer.valueOf(params.get("benhVienId"))));
        return tk;
    }

    @Override
    public Taikhoan handle(Taikhoan tk) {
        Bacsi bs = new Bacsi();
        if(tk.getId()!=null)
            bs.setId(tk.getId());
        bs.setHoNguoiDung(tk.getHoNguoiDung());
        bs.setTenNguoiDung(tk.getTenNguoiDung());
        bs.setDiaChi(tk.getDiaChi());
        bs.setEmail(tk.getEmail());
        bs.setMatKhau(this.passwordEncoder.encode(tk.getMatKhau()));
        bs.setGhiChu(tk.getGhiChu());
        bs.setNgaySinh(tk.getNgaySinh());
        bs.setSoDienThoai(tk.getSoDienThoai());
        bs.setRole("DOCTOR");
        return bs; 
    }
}
