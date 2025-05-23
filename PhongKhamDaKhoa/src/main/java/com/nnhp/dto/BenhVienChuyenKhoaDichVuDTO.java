/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.enums.LoaiDichVu;
import com.nnhp.enums.LoaiThanhToan;
import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class BenhVienChuyenKhoaDichVuDTO {
    private int id;
    private String tenDichVu;
    private double giaTien;
    private LoaiDichVu loaiDichVu;
    private LoaiThanhToan loaiThanhToan;

    public BenhVienChuyenKhoaDichVuDTO(int id, String tenDichVu, double giaTien, LoaiDichVu loaiDichVu, LoaiThanhToan loaiThanhToan) {
        this.id = id;
        this.tenDichVu = tenDichVu;
        this.giaTien = giaTien;
        this.loaiDichVu = loaiDichVu;
        this.loaiThanhToan = loaiThanhToan;
    }
    
    public static BenhVienChuyenKhoaDichVuDTO convertToDTO(BenhVienChuyenKhoaDichVu bvckdv) {
        return new BenhVienChuyenKhoaDichVuDTO(bvckdv.getId(), bvckdv.getTenDichVu(), bvckdv.getGiaTien(), bvckdv.getLoaiDichVu(), bvckdv.getLoaiThanhToan());
    }
    
    public static List<BenhVienChuyenKhoaDichVuDTO> convertToDTOList(List<BenhVienChuyenKhoaDichVu> bvckdvs) {
        return bvckdvs.stream().map(BenhVienChuyenKhoaDichVuDTO::convertToDTO).collect(Collectors.toList());
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tenDichVu
     */
    public String getTenDichVu() {
        return tenDichVu;
    }

    /**
     * @param tenDichVu the tenDichVu to set
     */
    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    /**
     * @return the giaTien
     */
    public double getGiaTien() {
        return giaTien;
    }

    /**
     * @param giaTien the giaTien to set
     */
    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    /**
     * @return the loaiDichVu
     */
    public LoaiDichVu getLoaiDichVu() {
        return loaiDichVu;
    }

    /**
     * @param loaiDichVu the loaiDichVu to set
     */
    public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    /**
     * @return the loaiThanhToan
     */
    public LoaiThanhToan getLoaiThanhToan() {
        return loaiThanhToan;
    }

    /**
     * @param loaiThanhToan the loaiThanhToan to set
     */
    public void setLoaiThanhToan(LoaiThanhToan loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }
    
    
}
