/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Lichkham;
import com.nnhp.services.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hoang
 */
@RestController
@RequestMapping("/api")
public class ApiLichKhamController {
    @Autowired
    private LichKhamService lichKhamService;
    
    @DeleteMapping("/lich-kham/{id}")
    public ResponseEntity<Void> deleteLichKham(@PathVariable(value = "id") int id) {
        this.lichKhamService.deleteLichKham(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 