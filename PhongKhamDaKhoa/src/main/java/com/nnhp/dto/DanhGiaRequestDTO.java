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
public class DanhGiaRequestDTO {
    private String binhLuan;
    private Boolean chinhSua;
    private Short soSao;
    private Date ngayTao;
    private String phanHoi;
    private int tkId;
    private int bsId;

    public DanhGiaRequestDTO(String binhLuan, Boolean chinhSua, Short soSao, Date ngayTao, String phanHoi, int tkId, int bsId) {
        this.binhLuan = binhLuan;
        this.chinhSua = chinhSua;
        this.soSao = soSao;
        this.ngayTao = ngayTao;
        this.phanHoi = phanHoi;
        this.tkId = tkId;
        this.bsId = bsId;
    }

    public static DanhGiaRequestDTO convertToDTO(Danhgia dg) {
        return new DanhGiaRequestDTO(dg.getBinhLuan(), dg.getChinhSua(), dg.getSoSao(), 
                dg.getNgayTao(), dg.getPhanHoi(), dg.getTaikhoanId().getId(), dg.getBacsiId().getId());
    }
    
    public static List<DanhGiaRequestDTO> convertToDTOList(Collection<Danhgia> dgs) {
        return dgs.stream().map(DanhGiaRequestDTO::convertToDTO).collect(Collectors.toList());
    }

    public DanhGiaRequestDTO() {
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

    public int getTkId() {
        return tkId;
    }

    public void setTkId(int tkId) {
        this.tkId = tkId;
    }

    public int getBsId() {
        return bsId;
    }

    public void setBsId(int bsId) {
        this.bsId = bsId;
    }

    
}
