/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.BacSiChiTietDTO;
import com.nnhp.dto.BacSiDTO;
import com.nnhp.pojo.Bacsi;
import com.nnhp.services.BacSiService;
import com.nnhp.services.DanhGiaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class ApiBacSiController {
    @Autowired
    private BacSiService bsService;
    @Autowired
    private DanhGiaService dgService;
    
    @GetMapping("/ds-bac-si")
    @CrossOrigin
    public ResponseEntity<List<BacSiDTO>> getDsBacSi(@RequestParam Map<String, String> params) {
        List<Bacsi> bsList = this.bsService.getDsBacSi(params);
        return new ResponseEntity<>(BacSiDTO.convertToDTOList(bsList), HttpStatus.OK);
    }
    
    @GetMapping("/bac-si-chi-tiet/{id}")
    @CrossOrigin
    public ResponseEntity<BacSiChiTietDTO> getBacSiChiTiet(@PathVariable(name = "id") int id) {
        BacSiChiTietDTO bs = this.bsService.getBacSiWithDanhGiaById(id);
        Double soSao = this.dgService.averageSoSao(id);
        bs.setSoSao(soSao);
        return new ResponseEntity<>(bs, HttpStatus.OK);
    } 
}
