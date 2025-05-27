/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.LichKhamBacSiDTO;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.services.BacSiService;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.services.ThongKeBacSiService;
import com.nnhp.utils.JwtUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author hoang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiThongKeBacSiController {

    @Autowired
    private BacSiService bacSiService;
    @Autowired
    private ThongKeBacSiService thongKeService;
    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/secure/doctors/{id}/thong-ke-so-luong")
    public ResponseEntity<?> getThongKeSoBenhNhan(@PathVariable(name = "id") Integer bacSiId,
            @RequestParam(name = "nam" , required = false) Integer nam,
            @RequestParam(name = "loaiThongKe", required = false) String loaiThongKe) {

        try {
            Bacsi bacSi = bacSiService.getBacSiById(bacSiId);
            if (bacSi == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<Object[]> thongKe = thongKeService.thongKeSoBenhNhanDaKham(loaiThongKe, nam, bacSi.getId());

            List<Map<String, Object>> result = thongKe.stream().map(row -> {
                Map<String, Object> m = new HashMap<>();
                m.put("thoiGian", row[0]);     // Tháng/Quý/Năm
                m.put("soLuong", row[1]);      // Số bệnh nhân đã khám
                return m;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/secure/doctors/{id}/thong-ke-loai-benh")
    public ResponseEntity<?> getThongKeBenhPhoBien(@PathVariable(name = "id") Integer bacSiId,
            @RequestParam(name = "nam" , required = false) Integer nam,
            @RequestParam(name = "loaiThongKe" , required = false) String loaiThongKe) {

        try {
            Bacsi bacSi = bacSiService.getBacSiById(bacSiId);
            if (bacSi == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<Object[]> thongKe = thongKeService.thongKeLoaiBenhPhoBien(loaiThongKe, nam, bacSiId);

            List<Map<String, Object>> result = thongKe.stream().map(row -> {
                Map<String, Object> m = new HashMap<>();
                m.put("thoiGian", row[0]);     // Tháng/Quý/Năm
                m.put("soLuong", row[1]);
                m.put("chuanDoan", row[2]);// Số bệnh nhân đã khám
                return m;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
