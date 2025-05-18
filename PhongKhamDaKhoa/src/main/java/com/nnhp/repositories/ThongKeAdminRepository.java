/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.repositories;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface ThongKeAdminRepository {
    List<Object[]> thongKeLichKhamTheoTrangThai();
    List<Object[]> thongKeLichKhamTheoBacSi();
    List<Object[]> thongKeLichKhamTheoBenhVien();
    List<Object[]> thongKeLichKhamTheoThoiGian(Date tuNgay, Date denNgay);
    Map<String, Long> thongKeTongQuat();
    List<Object[]> thongKeDoanhThuTheoThoiGian(Date tuNgay, Date denNgay);
    List<Object[]> thongKeDoanhThuTheoBenhVien(Date tuNgay, Date denNgay);
    List<Object[]> thongKeDoanhThuTheoBacSi(Date tuNgay, Date denNgay);
    
    // Thống kê tổng doanh thu
    double thongKeTongDoanhThu(Date tuNgay, Date denNgay);
    
    // Thống kê doanh thu theo tháng
    List<Object[]> thongKeDoanhThuTheoThang(String loaiThongKe, int nam);
    
    // Thống kê doanh thu theo năm
    List<Object[]> thongKeDoanhThuTheoNam();
} 