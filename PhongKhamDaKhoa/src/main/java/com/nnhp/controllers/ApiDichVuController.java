/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.services.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hoang
 */
@RestController
@RequestMapping("/api")
public class ApiDichVuController {
    @Autowired
    private DichVuService dichVuService;
    
    @DeleteMapping("/dich-vu/{id}")
    public ResponseEntity<String> deleteDichVu(@PathVariable(value = "id") int id) {
        try {
            this.dichVuService.deleteDichVu(id);
            return new ResponseEntity<>("Xóa dịch vụ thành công", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Đã xảy ra lỗi: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
} 