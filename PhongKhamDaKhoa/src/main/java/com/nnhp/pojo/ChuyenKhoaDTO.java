/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author namnh
 */
public class ChuyenKhoaDTO {
    private Integer id;
    private String tenChuyenKhoa;

    public ChuyenKhoaDTO(Integer id, String tenChuyenKhoa) {
        this.id = id;
        this.tenChuyenKhoa = tenChuyenKhoa;
    }
    
    public static ChuyenKhoaDTO convertToChuyenKhoaDTO(Chuyenkhoa ck) {
        return new ChuyenKhoaDTO(ck.getId(), ck.getTenChuyenKhoa());
    }
    
    public static List<ChuyenKhoaDTO> convertToListChuyenKhoaDTO(List<Chuyenkhoa> cks) {
        return cks.stream().map(ChuyenKhoaDTO::convertToChuyenKhoaDTO).collect(Collectors.toList());
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the tenChuyenKhoa
     */
    public String getTenChuyenKhoa() {
        return tenChuyenKhoa;
    }

    /**
     * @param tenChuyenKhoa the tenChuyenKhoa to set
     */
    public void setTenChuyenKhoa(String tenChuyenKhoa) {
        this.tenChuyenKhoa = tenChuyenKhoa;
    }
    
    
}
