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
import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import com.nnhp.pojo.Hoso;
import com.nnhp.pojo.Lichkham;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BenhVienChuyenKhoaDichVuService;
import com.nnhp.services.HoSoService;
import com.nnhp.services.LichKhamService;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.utils.JwtUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @DeleteMapping("/lich-kham/{id}")
    public ResponseEntity<Void> deleteLichKham(@PathVariable(value = "id") int id) {
        this.lichKhamService.deleteLichKham(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/secure/lich-kham/bac-si/{id}")
    public ResponseEntity<List<LichKhamBacSiDTO>> getLichKhamBacSi(@PathVariable(name = "id") int bacSiId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Map<String, String> params) {
        try {
            
            Bacsi bacSi = bacSiService.getBacSiById(bacSiId);
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

    @PostMapping("/tao-lich-kham")
    @CrossOrigin
    public ResponseEntity<LichKhamDTO> taoLichKham(@RequestBody LichKhamRequestDTO lichKhamRequestDTO) {
        return new ResponseEntity<>(LichKhamDTO.convertToDTO(this.lichKhamService.createLichKhamFromDTO(lichKhamRequestDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/lich-kham/{id}")
    @CrossOrigin
    public ResponseEntity<List<LichKhamDTO>> getLichKhamList(@PathVariable(name = "id") int userId) {
        return new ResponseEntity<>(LichKhamDTO.convertToDTOList(this.lichKhamService.getLichKhamListByUserId(userId)), HttpStatus.OK);
    }

    @PatchMapping("/sua-lich-kham/{id}")
    @CrossOrigin
    public ResponseEntity<LichKhamDTO> suaLichKham(@PathVariable(name = "id") int id, @RequestBody LichKhamPatchDTO lkPatch) {
        Lichkham lk = this.lichKhamService.getLichKhamById(id);
        lk = lkPatch.updateAtrribute(lk, lkPatch);
        return new ResponseEntity<>(LichKhamDTO.convertToDTO(this.lichKhamService.addOrUpdateLichKham(lk)), HttpStatus.ACCEPTED);
    }

    @PutMapping("/lich-kham/{id}/trang-thai")
    public ResponseEntity<?> updateTrangThaiLichKham(@PathVariable("id") Integer id,
            @RequestParam("trangThai") TrangThaiLichKham trangThai) {
        try {
            Lichkham lich = lichKhamService.getLichKhamById(id);
            if (lich == null) {
                return ResponseEntity.notFound().build();
            }

            lich.setTrangThai(trangThai);
            lichKhamService.addOrUpdateLichKham(lich);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật trạng thái");
        }
    }
}
