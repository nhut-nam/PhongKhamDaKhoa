/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services.handler;

import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Taikhoan;
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
@Component("USER")
public class UserHandler implements RoleHandler {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Taikhoan handle(Map<String, String> params) {
        Benhnhan tk = new Benhnhan();
        tk.setHoNguoiDung(params.get("hoNguoiDung"));
        tk.setTenNguoiDung(params.get("tenNguoiDung"));
        tk.setDiaChi(params.get("diaChi"));
        tk.setEmail(params.get("email"));
        tk.setMatKhau(this.passwordEncoder.encode(params.get("matKhau")));
        tk.setGhiChu(params.get("ghiChu"));
        try {
                tk.setNgaySinh(Formatter.DATE_FORMATTER.parse(params.get("ngaySinh")));
            } catch (ParseException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        tk.setSoDienThoai(params.get("soDienThoai"));
        tk.setRole("USER");
        return tk;
    }
    
}
