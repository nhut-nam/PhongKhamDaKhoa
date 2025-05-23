/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.BacSiThuocChuyenKhoaDTO;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Bacsithuocchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BacSiThuocChuyenKhoaService;
import com.nnhp.services.ChuyenKhoaService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class ApiBacSiThuocChuyenKhoaController {

    @Autowired
    private BacSiService bsService;
    @Autowired
    private ChuyenKhoaService ckService;
    @Autowired
    private BacSiThuocChuyenKhoaService bsckService;

    @PostMapping(path = "/addBacSiChuyenKhoa", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<BacSiThuocChuyenKhoaDTO>> addBacSiThuocChuyenKhoa(@RequestBody Map<String, Object> params) { 
        Integer bsID = (Integer) params.get("bacsiId");
        List<Integer> chuyenKhoaIds = (List<Integer>) params.get("chuyenKhoaIds");
        Bacsi bacSi = this.bsService.getBacSiById(bsID);
        List<Chuyenkhoa> chuyenKhoas = chuyenKhoaIds.stream()
                .map(ckService::getChuyenKhoaById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new ResponseEntity<>(BacSiThuocChuyenKhoaDTO.convertToDTOList(this.bsckService.addBacSiChuyenKhoaList(bacSi, chuyenKhoas)), HttpStatus.CREATED);
    }
}
