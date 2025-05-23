/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.BenhVienDTO;
import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.ThongBao;
import com.nnhp.servicesImpl.BenhVienServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> getBenhVienList(@RequestParam Map<String, String> params) {
        List<Benhvien> benhVienList = this.bvService.getDsBenhVien(params);
        return new ResponseEntity<>(BenhVienDTO.convertToListBenhVienDTO(benhVienList), HttpStatus.OK);
    }
    
    @DeleteMapping("/benh-vien/{id}")
    public ResponseEntity<Void> deleteBenhVien(@PathVariable(value = "id") int id) {
        this.bvService.deleteBenhVien(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/benh-vien{id}")
    public ResponseEntity<BenhVienDTO> getBenhVien(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(BenhVienDTO.convertToBenhVienDTO(this.bvService.getBenhVienById(id)), HttpStatus.OK);
    }
    
}
