/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Danhgia;
import java.util.List;

/**
 *
 * @author namnh
 */
public class BacSiDanhGiaDTO {
    private String hoNguoiDung;
    private String tenNguoiDung;
    private String avatar;
    private BenhVienDTO bvDTO;
    private Double soSao;
    private List<DanhGiaChiTietDTO> danhGiasDTO;

    public BacSiDanhGiaDTO(String hoNguoiDung, String tenNguoiDung, BenhVienDTO bvDTO, Double soSao, List<DanhGiaChiTietDTO> danhGiasDTO) {
        this.hoNguoiDung = hoNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.bvDTO = bvDTO;
        this.soSao = soSao;
        this.danhGiasDTO = danhGiasDTO;
    }

    public BacSiDanhGiaDTO(String hoNguoiDung, String tenNguoiDung, String avatar, BenhVienDTO bvDTO, Double soSao) {
        this.hoNguoiDung = hoNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.avatar = avatar;
        this.bvDTO = bvDTO;
        this.soSao = soSao;
    }

    
    
    public static BacSiDanhGiaDTO convertToDTO(Bacsi bs, Double soSao) {
        return new BacSiDanhGiaDTO(bs.getHoNguoiDung(), bs.getTenNguoiDung(), bs.getAvatar(), BenhVienDTO.convertToBenhVienDTO(bs.getBenhvienId()), 
                soSao);
    }

    public String getHoNguoiDung() {
        return hoNguoiDung;
    }

    public void setHoNguoiDung(String hoNguoiDung) {
        this.hoNguoiDung = hoNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public BenhVienDTO getBvDTO() {
        return bvDTO;
    }

    public void setBvDTO(BenhVienDTO bvDTO) {
        this.bvDTO = bvDTO;
    }

    public Double getSoSao() {
        return soSao;
    }

    public void setSoSao(Double soSao) {
        this.soSao = soSao;
    }

    public List<DanhGiaChiTietDTO> getDanhGiasDTO() {
        return danhGiasDTO;
    }

    public void setDanhGiasDTO(List<DanhGiaChiTietDTO> danhGiasDTO) {
        this.danhGiasDTO = danhGiasDTO;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    
}
