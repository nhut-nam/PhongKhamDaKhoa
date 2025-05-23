/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.BangCapDTO;
import com.nnhp.pojo.Bangcap;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BangCapService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
public class ApiBangCapController {
    @Autowired
    private BangCapService bcService;
    
    @PostMapping("/tao-bang-cap")
    public ResponseEntity<BangCapDTO> taoBangCap(@RequestParam Map<String, String> params, @RequestParam(name = "hinhMatTruoc") MultipartFile hinhMatTruoc) {
        return new ResponseEntity<>(BangCapDTO.convertToDTO(this.bcService.addBangCap(params, hinhMatTruoc)), HttpStatus.CREATED);
    }
}
