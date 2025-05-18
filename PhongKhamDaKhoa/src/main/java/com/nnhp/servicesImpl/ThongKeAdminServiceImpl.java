/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.repositories.ThongKeAdminRepository;
import com.nnhp.services.ThongKeAdminService;
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
public class ThongKeAdminServiceImpl implements ThongKeAdminService {
    @Autowired
    private ThongKeAdminRepository thongKeRepo;
    
    @Override
    public List<Object[]> thongKeLichKhamTheoTrangThai() {
        return this.thongKeRepo.thongKeLichKhamTheoTrangThai();
    }
    
    @Override
    public List<Object[]> thongKeLichKhamTheoBacSi() {
        return this.thongKeRepo.thongKeLichKhamTheoBacSi();
    }
    
    @Override
    public List<Object[]> thongKeLichKhamTheoBenhVien() {
        return this.thongKeRepo.thongKeLichKhamTheoBenhVien();
    }
    
    @Override
    public List<Object[]> thongKeLichKhamTheoThoiGian(Date tuNgay, Date denNgay) {
        return this.thongKeRepo.thongKeLichKhamTheoThoiGian(tuNgay, denNgay);
    }
    
    @Override
    public Map<String, Long> thongKeTongQuat() {
        return this.thongKeRepo.thongKeTongQuat();
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoThoiGian(Date tuNgay, Date denNgay) {
        return this.thongKeRepo.thongKeDoanhThuTheoThoiGian(tuNgay, denNgay);
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoBenhVien(Date tuNgay, Date denNgay) {
        return this.thongKeRepo.thongKeDoanhThuTheoBenhVien(tuNgay, denNgay);
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoBacSi(Date tuNgay, Date denNgay) {
        return this.thongKeRepo.thongKeDoanhThuTheoBacSi(tuNgay, denNgay);
    }
    
    @Override
    public double thongKeTongDoanhThu(Date tuNgay, Date denNgay) {
        return this.thongKeRepo.thongKeTongDoanhThu(tuNgay, denNgay);
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoThang(String loaiThongKe, int nam) {
        return this.thongKeRepo.thongKeDoanhThuTheoThang(loaiThongKe, nam);
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoNam() {
        return this.thongKeRepo.thongKeDoanhThuTheoNam();
    }
} 