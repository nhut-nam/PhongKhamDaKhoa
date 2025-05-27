/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.pojo.Benhvien;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class BenhVienDTO {
    private Integer id;
    private String diaChi;
    private String avatar;
    private String tenBenhVien;

    public BenhVienDTO(Integer id, String diaChi, String avatar, String tenBenhVien) {
        this.id = id;
        this.diaChi = diaChi;
        this.avatar = avatar;
        this.tenBenhVien = tenBenhVien;
    }
    
    public static BenhVienDTO convertToBenhVienDTO(Benhvien bv) {
        return new BenhVienDTO(bv.getId(), bv.getDiaChi(), bv.getAvatar(), bv.getTenBenhVien());
    }
    
    public static List<BenhVienDTO> convertToListBenhVienDTO(List<Benhvien> bvs) {
        return bvs.stream().map(BenhVienDTO::convertToBenhVienDTO).collect(Collectors.toList());
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
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the tenBenhVien
     */
    public String getTenBenhVien() {
        return tenBenhVien;
    }

    /**
     * @param tenBenhVien the tenBenhVien to set
     */
    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
}
