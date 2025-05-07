/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.enums.Role;
import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.TaiKhoanDTO;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.services.BenhVienService;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.utils.JwtUtils;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiUserController {
    @Autowired
    private TaiKhoanService userDetailsService;
    
    @PostMapping(path = "/benh-nhan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<TaiKhoanDTO> createUser(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(this.userDetailsService.addTaiKhoan(params, Role.USER)), HttpStatus.CREATED);
    }
    
    @PostMapping(path = "/quan-tri", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<TaiKhoanDTO> createAdmin(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(this.userDetailsService.addTaiKhoan(params, Role.ADMIN)), HttpStatus.CREATED);
    }
    
    @PostMapping(path = "/bac-si", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<TaiKhoanDTO> createDoctor(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(this.userDetailsService.addTaiKhoan(params, Role.DOCTOR)), HttpStatus.CREATED);
    }
    
    @PostMapping("/users")
    @CrossOrigin
    public ResponseEntity<TaiKhoanDTO> authenticate(@RequestParam("email") String email, @RequestParam("matKhau") String matKhau) {
        Taikhoan tk = this.userDetailsService.getUserByEmail(email);
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(tk), HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestBody Taikhoan tk) {
        if (this.userDetailsService.authenticate(tk.getEmail(), tk.getMatKhau())) {
            try {
                String token = JwtUtils.generateToken(tk.getEmail());
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
    }
    
    @RequestMapping("/secure/profile")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<?> getProfile(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(500).body("lỗi");
        }
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(this.userDetailsService.getUserByEmail(principal.getName())), HttpStatus.OK);
    }
}
