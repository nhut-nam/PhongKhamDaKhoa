/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.dto.TaiKhoanDTO;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositoriesImpl.TaiKhoanRepositoryImpl;
import com.nnhp.services.ChuyenKhoaService;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.servicesImpl.TaiKhoanServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private ChuyenKhoaService chuyenKhoaService;
    
    @GetMapping("/tai-khoan")
    public ResponseEntity<TaiKhoanDTO> getTaiKhoan() {
        Taikhoan tk = tkService.getTaiKhoanTest();
        TaiKhoanDTO dto = TaiKhoanDTO.convertToDTO(tk);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/ds-tai-khoan")
    public ResponseEntity<List<TaiKhoanDTO>> getTaiKhoanList() {
        List<TaiKhoanDTO> dsTaiKhoanDTO = new ArrayList<>();
        List<Taikhoan> dsTaiKhoan = tkService.getTaiKhoanList();
        dsTaiKhoan.forEach(tk -> dsTaiKhoanDTO.add(TaiKhoanDTO.convertToDTO(tk)));
        return new ResponseEntity<>(dsTaiKhoanDTO, HttpStatus.OK);

    }
   
}
