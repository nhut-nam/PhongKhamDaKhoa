/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.dto.HoSoDTO;
import com.nnhp.pojo.Hoso;
import com.nnhp.services.HoSoService;
import com.nnhp.services.TaiKhoanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
public class ApiHoSoController {
    @Autowired
    private HoSoService hsService;
    @Autowired
    private TaiKhoanService tkService;
    
    @PostMapping(path = "/secure/tao-ho-so", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<HoSoDTO> addHoSo(@RequestBody Map<String, Object> params) {
        Integer userId = Integer.valueOf(params.get("user_id").toString());
        Benhnhan bn = (Benhnhan) this.tkService.getUserById(userId);
        return new ResponseEntity<>(HoSoDTO.convertToDTO(this.hsService.addHoSo(params, bn)), HttpStatus.CREATED);
    }
    
    @GetMapping("/secure/get-ds-ho-so/{userId}")
    @CrossOrigin
    public ResponseEntity<List<HoSoDTO>> getHoSoList(@PathVariable(name = "userId") String userId) {
        Integer id = Integer.valueOf(userId);
        return new ResponseEntity<>(HoSoDTO.convertToDTOList(this.hsService.getHoSoList(id)), HttpStatus.OK);
    }
    
    @DeleteMapping("/secure/xoa-ho-so/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void deleteHoSo(@PathVariable(name = "id") Integer id) {
        this.hsService.deleteHoSo(id);
    }
    
    @GetMapping("/secure/get-ho-so/{id}")
    @CrossOrigin
    public ResponseEntity<HoSoDTO> getHoSo(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(HoSoDTO.convertToDTO(this.hsService.getHoSoById(id)), HttpStatus.OK);
    }
    @GetMapping("/secure/get-list-ho-so-benh-nhan/{bacSiId}")
    @CrossOrigin
    public ResponseEntity<?> getListHoSoByBacSi(@PathVariable(name = "bacSiId") int bacSiId) {
        return ResponseEntity.ok(this.hsService.getHoSoByBacSi(bacSiId));
    }
    
    @PutMapping("/secure/sua-ho-so")
    @CrossOrigin
    public ResponseEntity<?> updateHoSo(@RequestBody HoSoDTO hsDTO) {
        Hoso hs = this.hsService.getHoSoById(hsDTO.getId());
        Hoso hsNew = HoSoDTO.updateAttribute(hs, hsDTO);
        return new ResponseEntity<>(HoSoDTO.convertToDTO(this.hsService.updateHoSO(hsNew)), HttpStatus.OK);
    }
}
