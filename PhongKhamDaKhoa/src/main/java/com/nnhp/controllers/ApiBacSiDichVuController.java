/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.BacSiDichVuDTO;
import com.nnhp.services.BacSiDichVuService;
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
public class ApiBacSiDichVuController {
    @Autowired
    private BacSiDichVuService bsdvService;
    
    @GetMapping("/bac-si-dich-vu/{benhVienChuyenKhoaDichVuId}")
    @CrossOrigin
    public ResponseEntity<List<BacSiDichVuDTO>> getBacSiDichVuByBenhVienChuyenKhoaDichVuId(@PathVariable(name = "benhVienChuyenKhoaDichVuId") int id) {
        return new ResponseEntity<>(BacSiDichVuDTO.convertToDTOList(this.bsdvService.getBacSiDichVuByBenhVienChuyenKhoaDichVu(id)), HttpStatus.OK);
    }
}
