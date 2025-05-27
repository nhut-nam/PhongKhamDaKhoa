/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.repositories.ThongKeBacSiRepository;
import com.nnhp.services.ThongKeBacSiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class ThongKeBacSiServiceImpl implements ThongKeBacSiService {

    @Autowired
    private ThongKeBacSiRepository thongKeRepo;

    @Override
    public List<Object[]> thongKeSoBenhNhanDaKham(String loaiThongKe, Integer nam, Integer bacSiId) {

        if (loaiThongKe == null || loaiThongKe.isEmpty()) {
            loaiThongKe = "YEAR";
        }
        if (nam == null) {
            nam = 2025;
        }

        return this.thongKeRepo.thongKeSoBenhNhanDaKham(loaiThongKe, nam, bacSiId);
    }

    @Override
    public List<Object[]> thongKeLoaiBenhPhoBien(String loaiThongKe, Integer nam, Integer bacSiId) {

        if (loaiThongKe == null || loaiThongKe.isEmpty()) {
            loaiThongKe = "YEAR";
        }
        if (nam == null) {
            nam = 2025;
        }

        return this.thongKeRepo.thongKeLoaiBenhPhoBien(loaiThongKe, nam, bacSiId);
    }

}
