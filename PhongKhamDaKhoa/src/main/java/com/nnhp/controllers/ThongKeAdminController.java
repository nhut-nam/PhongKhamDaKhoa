/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.services.ThongKeAdminService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hoang
 */
@Controller
@RequestMapping("/admin")
public class ThongKeAdminController {
    @Autowired
    private ThongKeAdminService thongKeService;
    
    @GetMapping("/thong-ke")
    public String thongKeView(Model model, 
                             @RequestParam(name = "tuNgay", required = false) String tuNgay,
                             @RequestParam(name = "denNgay", required = false) String denNgay,
                             @RequestParam(name = "nam", required = false) Integer nam,
                             @RequestParam(name = "loaiThongKe", required = false) String loaiThongKe) {
        // Thống kê tổng quát
        Map<String, Long> thongKeTongQuat = thongKeService.thongKeTongQuat();
        model.addAttribute("thongKeTongQuat", thongKeTongQuat);
        
        // Thống kê tổng doanh thu
        double tongDoanhThu = thongKeService.thongKeTongDoanhThu(null, null);
        model.addAttribute("tongDoanhThu", tongDoanhThu);
        
        // Thống kê doanh thu theo năm
        if (loaiThongKe == null || loaiThongKe.isEmpty()) {
            loaiThongKe = "month"; // mặc định là theo tháng nếu không có tham số
        }
        if (nam != null) {
            
            List<Object[]> thongKeDoanhThuTheoThang = thongKeService.thongKeDoanhThuTheoThang(loaiThongKe,nam);
            model.addAttribute("thongKeDoanhThuTheoNam", thongKeDoanhThuTheoThang);
            model.addAttribute("nam", nam);
            model.addAttribute("loaiThongKe", loaiThongKe);
        } else {
            List<Object[]> thongKeDoanhThuTheoNam = thongKeService.thongKeDoanhThuTheoNam();
            model.addAttribute("thongKeDoanhThuTheoNam", thongKeDoanhThuTheoNam);
        }
        
        // Thống kê theo trạng thái
        List<Object[]> thongKeTheoTrangThai = thongKeService.thongKeLichKhamTheoTrangThai();
        model.addAttribute("thongKeTheoTrangThai", thongKeTheoTrangThai);
        
        // Thống kê theo bác sĩ
        List<Object[]> thongKeTheoBacSi = thongKeService.thongKeLichKhamTheoBacSi();
        model.addAttribute("thongKeTheoBacSi", thongKeTheoBacSi);
        
        // Thống kê theo bệnh viện
        List<Object[]> thongKeTheoBenhVien = thongKeService.thongKeLichKhamTheoBenhVien();
        model.addAttribute("thongKeTheoBenhVien", thongKeTheoBenhVien);
        
        // Thống kê theo thời gian
        if (tuNgay != null && !tuNgay.isEmpty() && denNgay != null && !denNgay.isEmpty()) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date tuNgayDate = formatter.parse(tuNgay);
                Date denNgayDate = formatter.parse(denNgay);
                
                List<Object[]> thongKeTheoThoiGian = thongKeService.thongKeLichKhamTheoThoiGian(tuNgayDate, denNgayDate);
                model.addAttribute("thongKeTheoThoiGian", thongKeTheoThoiGian);
                
                // Thống kê doanh thu theo thời gian
                List<Object[]> thongKeDoanhThuTheoThoiGian = thongKeService.thongKeDoanhThuTheoThoiGian(tuNgayDate, denNgayDate);
                model.addAttribute("thongKeDoanhThuTheoThoiGian", thongKeDoanhThuTheoThoiGian);
                
                // Thống kê doanh thu theo bệnh viện
                List<Object[]> thongKeDoanhThuTheoBenhVien = thongKeService.thongKeDoanhThuTheoBenhVien(tuNgayDate, denNgayDate);
                model.addAttribute("thongKeDoanhThuTheoBenhVien", thongKeDoanhThuTheoBenhVien);
                
                // Thống kê doanh thu theo bác sĩ
                List<Object[]> thongKeDoanhThuTheoBacSi = thongKeService.thongKeDoanhThuTheoBacSi(tuNgayDate, denNgayDate);
                model.addAttribute("thongKeDoanhThuTheoBacSi", thongKeDoanhThuTheoBacSi);
                
                model.addAttribute("tuNgay", tuNgay);
                model.addAttribute("denNgay", denNgay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            // Mặc định lấy 30 ngày gần nhất
            Date denNgayDate = new Date();
            Date tuNgayDate = new Date(denNgayDate.getTime() - 30L * 24 * 60 * 60 * 1000);
            
            List<Object[]> thongKeTheoThoiGian = thongKeService.thongKeLichKhamTheoThoiGian(tuNgayDate, denNgayDate);
            model.addAttribute("thongKeTheoThoiGian", thongKeTheoThoiGian);
            
            // Thống kê doanh thu theo thời gian
            List<Object[]> thongKeDoanhThuTheoThoiGian = thongKeService.thongKeDoanhThuTheoThoiGian(tuNgayDate, denNgayDate);
            model.addAttribute("thongKeDoanhThuTheoThoiGian", thongKeDoanhThuTheoThoiGian);
            
            // Thống kê doanh thu theo bệnh viện
            List<Object[]> thongKeDoanhThuTheoBenhVien = thongKeService.thongKeDoanhThuTheoBenhVien(tuNgayDate, denNgayDate);
            model.addAttribute("thongKeDoanhThuTheoBenhVien", thongKeDoanhThuTheoBenhVien);
            
            // Thống kê doanh thu theo bác sĩ
            List<Object[]> thongKeDoanhThuTheoBacSi = thongKeService.thongKeDoanhThuTheoBacSi(tuNgayDate, denNgayDate);
            model.addAttribute("thongKeDoanhThuTheoBacSi", thongKeDoanhThuTheoBacSi);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("tuNgay", formatter.format(tuNgayDate));
            model.addAttribute("denNgay", formatter.format(denNgayDate));
        }
        
        return "thongke";
    }
} 