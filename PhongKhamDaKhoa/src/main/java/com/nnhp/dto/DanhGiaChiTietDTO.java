/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.pojo.Danhgia;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class DanhGiaChiTietDTO {
    private int id;
    private String binhLuan;
    private Boolean chinhSua;
    private Short soSao;
    private Date ngayTao;
    private String phanHoi;
    private TaiKhoanDTO tkDTO;

    public DanhGiaChiTietDTO(int id, String binhLuan, Boolean chinhSua, Short soSao, Date ngayTao, String phanHoi, TaiKhoanDTO tkDTO) {
        this.id = id;
        this.binhLuan = binhLuan;
        this.chinhSua = chinhSua;
        this.soSao = soSao;
        this.ngayTao = ngayTao;
        this.phanHoi = phanHoi;
        this.tkDTO = tkDTO;
    }

    public static DanhGiaChiTietDTO convertToDTO(Danhgia dg) {
        return new DanhGiaChiTietDTO(dg.getId(), dg.getBinhLuan(), dg.getChinhSua(), dg.getSoSao(), 
                dg.getNgayTao(), dg.getPhanHoi(), TaiKhoanDTO.convertToDTO(dg.getTaikhoanId()));
    }
    
    public static List<DanhGiaChiTietDTO> convertToDTOList(Collection<Danhgia> dgs) {
        return dgs.stream().map(DanhGiaChiTietDTO::convertToDTO).collect(Collectors.toList());
    }


    public DanhGiaChiTietDTO() {
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public Boolean getChinhSua() {
        return chinhSua;
    }

    public void setChinhSua(Boolean chinhSua) {
        this.chinhSua = chinhSua;
    }

    public Short getSoSao() {
        return soSao;
    }

    public void setSoSao(Short soSao) {
        this.soSao = soSao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getPhanHoi() {
        return phanHoi;
    }

    public void setPhanHoi(String phanHoi) {
        this.phanHoi = phanHoi;
    }

    public TaiKhoanDTO getTkDTO() {
        return tkDTO;
    }

    public void setTkDTO(TaiKhoanDTO tkDTO) {
        this.tkDTO = tkDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
