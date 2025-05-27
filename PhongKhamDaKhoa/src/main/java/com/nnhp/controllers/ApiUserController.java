/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.enums.Role;
import com.nnhp.enums.TrangThaiTaiKhoan;
import com.nnhp.dto.BacSiDTO;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.dto.TaiKhoanDTO;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.ThongBao;
import com.nnhp.services.BacSiThuocChuyenKhoaService;
import com.nnhp.services.BenhVienService;
import com.nnhp.services.ChuyenKhoaService;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.utils.JwtUtils;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private BacSiThuocChuyenKhoaService bsckService;
    @Autowired
    private ChuyenKhoaService ckService;

    @PostMapping(path = "/benh-nhan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(this.userDetailsService.addTaiKhoan(params, Role.ROLE_USER)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/quan-tri", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAdmin(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(this.userDetailsService.addTaiKhoan(params, Role.ROLE_ADMIN)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/bac-si", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDoctor(@RequestBody Map<String, String> params) {
        Bacsi bs = (Bacsi) this.userDetailsService.addTaiKhoan(params, Role.ROLE_DOCTOR);
        String chuoi = params.get("chuyenKhoa"); // ví dụ: "[1,2]"
        chuoi = chuoi.replaceAll("[\\[\\]\\s]", ""); // bỏ [ ], và khoảng trắng
        String[] ckIds = chuoi.split(",");

        List<Chuyenkhoa> chuyenKhoas = Arrays.stream(ckIds)
                .map(Integer::parseInt)
                .map(ckId -> ckService.getChuyenKhoaById(ckId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        bs.setBacsithuocchuyenkhoaCollection(this.bsckService.addBacSiChuyenKhoaList(bs, chuyenKhoas));
        return new ResponseEntity<>(BacSiDTO.convertToDTO(bs), HttpStatus.CREATED);
    }

    @PostMapping("/users")
    public ResponseEntity<TaiKhoanDTO> authenticate(@RequestParam("email") String email, @RequestParam("matKhau") String matKhau) {
        Taikhoan tk = this.userDetailsService.getUserByEmail(email);
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(tk), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Taikhoan tk) {
        ThongBao tb = this.userDetailsService.authenticate(tk.getEmail(), tk.getMatKhau());
        if (tb.isStatus()) {
             Taikhoan u = userDetailsService.getUserByEmail(tk.getEmail());
            try {
                
                String token = JwtUtils.generateToken(u.getEmail(),u.getRole());
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tb);
//        ThongBao tb = this.userDetailsService.authenticate(tk.getEmail(), tk.getMatKhau());
//        if (this.userDetailsService.authenticateRole(tk.getEmail(), tk.getMatKhau())) {
//            Taikhoan u = userDetailsService.getUserByEmail(tk.getEmail());
//             if (tb.isStatus()) {
//            try {
//                String token = JwtUtils.generateToken(u.getEmail(), u.getRole());
//                System.out.println("TAIKHOAN" + u.getEmail());
//                System.out.println("ROLE" + u.getRole());
//                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
//            } catch (Exception e) {
//                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
    }

    @RequestMapping("/secure/profile")
    @ResponseBody
    public ResponseEntity<?> getProfile(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(500).body("lỗi");
        }
        return new ResponseEntity<>(TaiKhoanDTO.convertToDTO(this.userDetailsService.getUserByEmail(principal.getName())), HttpStatus.OK);
    }

    @DeleteMapping("/tai-khoan/{taiKhoanId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "taiKhoanId") int id) {
        this.userDetailsService.deleteUser(id);
    }
}
