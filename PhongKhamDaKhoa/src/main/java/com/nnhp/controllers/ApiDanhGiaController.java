/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.DanhGiaChiTietDTO;
import com.nnhp.dto.DanhGiaRequestDTO;
import com.nnhp.pojo.Danhgia;
import com.nnhp.services.DanhGiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
public class ApiDanhGiaController {
    @Autowired
    private DanhGiaService dgService;
    
    @GetMapping("/danh-gias/{id}")
    @CrossOrigin
    public ResponseEntity<List<DanhGiaChiTietDTO>> getDanhGiaList(@PathVariable(name = "id") int id) {
        List<Danhgia> dgList = this.dgService.getDanhGiaByBacSiId(id);
        return new ResponseEntity<>(DanhGiaChiTietDTO.convertToDTOList(dgList), HttpStatus.OK);
    }
    
    @PostMapping("/them-danh-gia")
    @CrossOrigin
    public ResponseEntity<DanhGiaRequestDTO> themDanhGia(@RequestBody DanhGiaRequestDTO dgRequestDTO) {
        return new ResponseEntity<>(DanhGiaRequestDTO.convertToDTO(this.dgService.addOrUpdateDanhGiaFromDTO(dgRequestDTO)), HttpStatus.CREATED);
    }
    
}
