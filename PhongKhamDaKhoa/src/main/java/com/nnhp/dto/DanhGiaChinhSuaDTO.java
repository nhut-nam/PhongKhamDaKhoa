/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.pojo.Danhgia;
import java.util.Date;

/**
 *
 * @author namnh
 */
public class DanhGiaChinhSuaDTO {
    private int id;
    private String binhLuan;
    private String phanHoi;
    private Short soSao;
    private Date ngayTao;

    public DanhGiaChinhSuaDTO(int id, String binhLuan, Short soSao, Date ngayTao) {
        this.id = id;
        this.binhLuan = binhLuan;
        this.soSao = soSao;
        this.ngayTao = ngayTao;
    }
    
    public static Danhgia updateAtribute(DanhGiaChinhSuaDTO dgNew, Danhgia dgOld) {
        dgOld.setBinhLuan(dgNew.getBinhLuan());
        dgOld.setSoSao(dgNew.getSoSao());
        dgOld.setNgayTao(dgNew.getNgayTao());
        return dgOld;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
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
}
