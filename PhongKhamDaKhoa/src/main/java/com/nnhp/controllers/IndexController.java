/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.TaiKhoanDTO;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositoriesImpl.TaiKhoanRepositoryImpl;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.servicesImpl.TaiKhoanServiceImpl;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@Controller
@ControllerAdvice
public class IndexController {
    
    @Autowired
    private TaiKhoanService tkService;
    
    @GetMapping("/tai-khoan")
    public ResponseEntity<TaiKhoanDTO> index() {
        Taikhoan tk = tkService.getTaiKhoanTest();
        TaiKhoanDTO dto = TaiKhoanDTO.convertToDTO(tk);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
