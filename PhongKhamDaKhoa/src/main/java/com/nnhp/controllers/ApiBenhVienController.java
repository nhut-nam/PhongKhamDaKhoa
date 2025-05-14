/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.BenhVienDTO;
import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.ThongBao;
import com.nnhp.servicesImpl.BenhVienServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
public class ApiBenhVienController {
    @Autowired
    private BenhVienServiceImpl bvService;
    
    @GetMapping("/ds-benh-vien")
    @CrossOrigin
    public ResponseEntity<?> getBenhVienList() {
        List<Benhvien> benhVienList = this.bvService.getAllBenhVien();
        if (benhVienList.isEmpty()) 
            return new ResponseEntity<>(new ThongBao("Không có bệnh viện", false), HttpStatus.OK);
        return new ResponseEntity<>(BenhVienDTO.convertToListBenhVienDTO(benhVienList), HttpStatus.OK);
    }
}
