/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.pojo.Tintuc;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class TinTucDTO {
    private String tieuDe;
    private String noiDung;
    private Date ngayDang;

    public TinTucDTO(String tieuDe, String noiDung, Date ngayDang) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
    }
    
    public static TinTucDTO convertToDTO(Tintuc tt) {
        return new TinTucDTO(tt.getTieuDe(), tt.getNoiDung(), tt.getNgayDang());
    }
    
    public static List<TinTucDTO> convertToDTOList(List<Tintuc> tt) {
        return tt.stream()
                .map(TinTucDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }
    
    
}
