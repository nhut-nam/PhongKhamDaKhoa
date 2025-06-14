/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services.handler;

import com.cloudinary.Cloudinary;
import com.nnhp.enums.TrangThaiBangCap;
import com.nnhp.enums.TrangThaiTaiKhoan;
import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Bangcap;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositories.BenhVienRepository;
import com.nnhp.repositories.ChuyenKhoaRepository;
import com.nnhp.services.BacSiThuocChuyenKhoaService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author namnh
 */
@Component("ROLE_DOCTOR")
public class DoctorHandler implements RoleHandler {

    @Autowired
    private BenhVienRepository bvRepo;
    @Autowired
    private ChuyenKhoaRepository ckRepo;
    @Autowired
    private BacSiThuocChuyenKhoaService bsckService;
    @Autowired
    private Cloudinary cloudinary;

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
        tk.setAvatar(params.get("avatar"));
        tk.setGhiChu(params.get("ghiChu"));
        try {
            tk.setNgaySinh(Formatter.DATE_FORMATTER.parse(params.get("ngaySinh")));
            tk.setNgayLamViec(Formatter.DATE_FORMATTER.parse(params.get("ngayLamViec")));
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Ngày không đúng định dạng yyyy-MM-dd", ex);
        }
        tk.setSoDienThoai(params.get("soDienThoai"));
        tk.setRole("ROLE_DOCTOR");
        tk.setChuyenTri(params.get("chuyenTri"));
        tk.setNgayNghiViec(null);
        tk.setTrangThai(TrangThaiTaiKhoan.DOI_XAC_NHAN);
        tk.setBenhvienId(this.bvRepo.getBenhVienById(Integer.parseInt(params.get("benhVien"))));
        return tk;
    }

    @Override
    public Taikhoan handle(Taikhoan tk) {
        Bacsi bs = new Bacsi();
        if (tk.getId() != null) {
            bs.setId(tk.getId());
        }
        bs.setHoNguoiDung(tk.getHoNguoiDung());
        bs.setTenNguoiDung(tk.getTenNguoiDung());
        bs.setDiaChi(tk.getDiaChi());
        bs.setEmail(tk.getEmail());
        bs.setMatKhau(this.passwordEncoder.encode(tk.getMatKhau()));
        bs.setGhiChu(tk.getGhiChu());
        bs.setNgaySinh(tk.getNgaySinh());
        bs.setSoDienThoai(tk.getSoDienThoai());
        bs.setRole("ROLE_DOCTOR");
        return bs;
    }
}
