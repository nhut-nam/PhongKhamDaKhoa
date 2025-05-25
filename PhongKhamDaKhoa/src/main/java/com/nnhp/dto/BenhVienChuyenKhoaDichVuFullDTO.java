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
public class BenhVienChuyenKhoaDichVuFullDTO {
    private int id;
    private String tenDichVu;
    private double giaTien;
    private LoaiDichVu loaiDichVu;
    private LoaiThanhToan loaiThanhToan;
    private BenhVienChuyenKhoaDTO bvckDTO;

    public BenhVienChuyenKhoaDichVuFullDTO(int id, String tenDichVu, double giaTien, LoaiDichVu loaiDichVu, LoaiThanhToan loaiThanhToan, BenhVienChuyenKhoaDTO bvckDTO) {
        this.id = id;
        this.tenDichVu = tenDichVu;
        this.giaTien = giaTien;
        this.loaiDichVu = loaiDichVu;
        this.loaiThanhToan = loaiThanhToan;
        this.bvckDTO = bvckDTO;
    }

    public BenhVienChuyenKhoaDichVuFullDTO() {
    }
    
    public static BenhVienChuyenKhoaDichVuFullDTO convertToDTO(BenhVienChuyenKhoaDichVu bvckdv) {
        return new BenhVienChuyenKhoaDichVuFullDTO(bvckdv.getId(), bvckdv.getTenDichVu(), bvckdv.getGiaTien(), 
                bvckdv.getLoaiDichVu(), bvckdv.getLoaiThanhToan(), BenhVienChuyenKhoaDTO.convertToDTO(bvckdv.getBenhVienChuyenKhoa()));
    }
    
    public static List<BenhVienChuyenKhoaDichVuFullDTO> convertToDTOList(List<BenhVienChuyenKhoaDichVu> bvckdvs) {
        return bvckdvs.stream().map(BenhVienChuyenKhoaDichVuFullDTO::convertToDTO).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public LoaiDichVu getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public LoaiThanhToan getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(LoaiThanhToan loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public BenhVienChuyenKhoaDTO getCkDTO() {
        return bvckDTO;
    }

    public void setCkDTO(BenhVienChuyenKhoaDTO bvckDTO) {
        this.bvckDTO = bvckDTO;
    }
    
    
}
