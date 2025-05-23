/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.BenhVienChuyenKhoaDichVuDTO;
import com.nnhp.services.BenhVienChuyenKhoaDichVuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
public class ApiBenhVienChuyenKhoaDichVuController {
    @Autowired
    private BenhVienChuyenKhoaDichVuService bvckdvService;
    
    @GetMapping("/benh-vien-chuyen-khoa-dich-vu/{id}")
    @CrossOrigin
    public ResponseEntity<List<BenhVienChuyenKhoaDichVuDTO>> getBenhVienChuyenKhoaDichVuByBenhVienChuyenKhoaId(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(BenhVienChuyenKhoaDichVuDTO.convertToDTOList(this.bvckdvService.getDsBenhVienChuyenKhoaDichVuByBenhVienChuyenKhoaId(id)), HttpStatus.OK);
    }
}
