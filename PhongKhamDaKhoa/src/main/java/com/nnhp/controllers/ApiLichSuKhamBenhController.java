/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Lichsukhambenh;
import com.nnhp.services.LichSuKhamBenhService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hoang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiLichSuKhamBenhController {

    @Autowired
    private LichSuKhamBenhService service;

    @GetMapping("/secure/lich-su-kham-benh/{id}")
    public ResponseEntity<?> getLichSuKhamBenhByHoSoId(@PathVariable(value = "id") int id) {
        List<Lichsukhambenh> listHoSo = this.service.getLichSuKhamBenhByUser(id);
        return ResponseEntity.ok(listHoSo);
    }

    @PutMapping("/secure/lich-su-kham-benh/{id}")
    public ResponseEntity<?> updateLichSuKhamBenhById(
            @PathVariable("id") int id,
            @RequestBody Map<String, String> payload) {
        try {
            Lichsukhambenh updateLichSu = this.service.updateLichSuKhamBenh(id, payload);
            return ResponseEntity.ok(updateLichSu);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi cập nhật lịch sử khám bệnh: " + e.getMessage());
        }
    }
}
