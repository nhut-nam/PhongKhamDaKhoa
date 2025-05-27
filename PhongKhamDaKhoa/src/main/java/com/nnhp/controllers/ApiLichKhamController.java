/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Bacsi;
import com.nnhp.dto.LichKhamBacSiDTO;
import com.nnhp.dto.LichKhamDTO;
import com.nnhp.dto.LichKhamPatchDTO;
import com.nnhp.dto.LichKhamRequestDTO;
import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import com.nnhp.pojo.Hoso;
import com.nnhp.pojo.Lichkham;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.ThongBao;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BenhVienChuyenKhoaDichVuService;
import com.nnhp.services.HoSoService;
import com.nnhp.services.LichKhamService;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.utils.JwtUtils;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
public class ApiLichKhamController {

    @Autowired
    private LichKhamService lichKhamService;
    @Autowired
    private BacSiService bacSiService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private BenhVienChuyenKhoaDichVuService bvckdvService;
    @Autowired
    private HoSoService hsService;

    @DeleteMapping("/secure/users/lich-kham/{id}")
    public ResponseEntity<Void> deleteLichKham(@PathVariable(value = "id") int id) {
        this.lichKhamService.deleteLichKham(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/secure/doctors/lich-kham/bac-si")
    public ResponseEntity<List<LichKhamBacSiDTO>> getLichKhamBacSi(@RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Map<String, String> params) {

        try {
            // 1. Lấy JWT token từ header
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String token = authHeader.replace("Bearer ", "");

            // 2. Giải mã JWT để lấy username
            String email = JwtUtils.validateTokenAndGetUsername(token);

            // 3. Lấy thông tin bác sĩ từ userId
            Taikhoan taiKhoan = taiKhoanService.getUserByEmail(email);
            Bacsi bacSi = bacSiService.getBacSiById(taiKhoan.getId());
            if (bacSi == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            // 4. Gọi service để lấy lịch khám theo bác sĩ
            List<LichKhamBacSiDTO> lichKhamList = lichKhamService.getLichKhamByBacSi(bacSi.getId(), date, params);

//        if (lichKhamList == null || lichKhamList.isEmpty())
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(lichKhamList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path = "/secure/users/lich-kham")
    @CrossOrigin
    public ResponseEntity<?> getLichKham(@RequestParam Map<String, String> params) {
        List<Lichkham> lks = this.lichKhamService.getDsLichKham(params);
        return new ResponseEntity<>(LichKhamDTO.convertToDTOList(lks), HttpStatus.OK);
    }
    
    @PostMapping("/secure/users/tao-lich-kham")
    @CrossOrigin
    public ResponseEntity<?> taoLichKham(@RequestBody LichKhamRequestDTO lichKhamRequestDTO) {
        Lichkham lk = this.lichKhamService.createLichKhamFromDTO(lichKhamRequestDTO);
        if (lk == null) {
            return new ResponseEntity<>(new ThongBao("Hôm nay đã đủ lịch hẹn. Xin vui lòng chọn ngày hẹn khác", false), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(LichKhamDTO.convertToDTO(lk), HttpStatus.CREATED);
    }
    
    @GetMapping("/secure/users/lich-kham/{id}")
    @CrossOrigin
    public ResponseEntity<List<LichKhamDTO>> getLichKhamList(@PathVariable(name = "id") int userId) {
        return new ResponseEntity<>(LichKhamDTO.convertToDTOList(this.lichKhamService.getLichKhamListByUserId(userId)), HttpStatus.OK);
    }
    
    @PatchMapping("/secure/users/sua-lich-kham/{id}")
    @CrossOrigin
    public ResponseEntity<LichKhamDTO> suaLichKham(@PathVariable(name = "id") int id, @RequestBody LichKhamPatchDTO lkPatch) {
        Lichkham lk = this.lichKhamService.getLichKhamById(id);
        lk = lkPatch.updateAtrribute(lk, lkPatch);
        return new ResponseEntity<>(LichKhamDTO.convertToDTO(this.lichKhamService.addOrUpdateLichKham(lk)), HttpStatus.ACCEPTED);
    }
}
