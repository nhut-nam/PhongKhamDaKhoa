/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Bacsi;
import com.nnhp.services.BacSiService;
import com.nnhp.services.TaiKhoanService;
import java.util.List;
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
 * @author hoang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiChatController {
    @Autowired
    private BacSiService bacSiService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    
    @GetMapping("/secure/chat/users/{userId}")
    public ResponseEntity<?> getListBacSi(@PathVariable(name = "userId") int userId) {
        
        return new ResponseEntity<>(bacSiService.getListBacSiByUserId(userId), HttpStatus.OK);
    }
    
    @GetMapping("/secure/chat/doctors/{doctorId}")
    public ResponseEntity<?> getListBenhNhan(@PathVariable(name = "doctorId") int doctorId) {
        
        return new ResponseEntity<>(taiKhoanService.getListUserByBacSiId(doctorId), HttpStatus.OK);
    }
      
}
