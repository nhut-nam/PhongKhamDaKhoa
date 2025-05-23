/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.ChuyenKhoaDTO;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.pojo.ThongBao;
import com.nnhp.services.ChuyenKhoaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hoang
 */
@RestController
@RequestMapping("/api")
public class ApiChuyenKhoaController {
    @Autowired
    private ChuyenKhoaService chuyenKhoaService;
    
    @DeleteMapping("/chuyen-khoa/{chuyenKhoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "chuyenKhoaId") int id) {
        this.chuyenKhoaService.deleteChuyenKhoa(id);
    }
    
    @GetMapping("/benh-vien/{bvId}/chuyen-khoa")
    @CrossOrigin
    public ResponseEntity<?> getChuyenKhoaByBenhVien(@PathVariable(value = "bvId") int bvId) {
        List<Chuyenkhoa> ckList = this.chuyenKhoaService.getChuyenKhoaByBenhVien(bvId);
        if (ckList.isEmpty()) 
            return new ResponseEntity<>(new ThongBao("Không có chuyên khoa thuộc bệnh viện đó", false), HttpStatus.OK);
        return new ResponseEntity<>(ChuyenKhoaDTO.convertToListChuyenKhoaDTO(ckList), HttpStatus.OK);
    }
}
