/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.TinTucDTO;
import com.nnhp.pojo.TaiKhoanTinTuc;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.Tintuc;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.services.TaiKhoanTinTucService;
import com.nnhp.services.TinTucService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiTinTucController {
    @Autowired
    private TinTucService ttService;
    @Autowired
    private TaiKhoanService tkService;
    @Autowired
    private TaiKhoanTinTucService tkttService;
    
    @PostMapping(path = "/secure/users/tin-tuc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findTop10TinTucChuaGanUser(@RequestParam(name = "userId") int id) {
        List<Tintuc> ttList = this.ttService.findTop10TinTucChuaGanUser(id);
        Taikhoan tk = this.tkService.getUserById(id);
        ttList.stream().map(tt -> this.tkttService.addOrUpdateTaiKhoanTinTuc(tk, tt)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
    
    @GetMapping("/secure/users/tin-tuc/{id}")
    public ResponseEntity<List<TinTucDTO>> getTinTucByUser(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(TinTucDTO.convertToDTOList(this.ttService.getTinTucByUserId(id)), HttpStatus.OK);
    }
}
