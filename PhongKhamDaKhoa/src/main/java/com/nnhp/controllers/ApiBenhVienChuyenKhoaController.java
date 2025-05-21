/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.BenhVienChuyenKhoaDTO;
import com.nnhp.services.BenhVienChuyenKhoaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
public class ApiBenhVienChuyenKhoaController {
    @Autowired
    private BenhVienChuyenKhoaService bcvkService;
    
    @GetMapping("/benh-vien-chuyen-khoa")
    @CrossOrigin
    public ResponseEntity<List<BenhVienChuyenKhoaDTO>> getBenhVienChuyenKhoa(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(BenhVienChuyenKhoaDTO.convertToDTOList(this.bcvkService.getDsBenhVienChuyenKhoa(params)), HttpStatus.OK);
    }
}
