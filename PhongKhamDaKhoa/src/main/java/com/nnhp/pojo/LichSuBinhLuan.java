/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "lichsubinhluan")
public class LichSuBinhLuan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "binh_luan")
    private String binhLuan;
    @Column(name = "binh_luan_cu")
    private String binhLuanCu;
    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "danhgia_id", referencedColumnName = "id")
    private DanhGia danhGia_id;

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
     * @return the binhLuan
     */
    public String getBinhLuan() {
        return binhLuan;
    }

    /**
     * @param binhLuan the binhLuan to set
     */
    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    /**
     * @return the binhLuanCu
     */
    public String getBinhLuanCu() {
        return binhLuanCu;
    }

    /**
     * @param binhLuanCu the binhLuanCu to set
     */
    public void setBinhLuanCu(String binhLuanCu) {
        this.binhLuanCu = binhLuanCu;
    }

    /**
     * @return the ngaySua
     */
    public LocalDateTime getNgaySua() {
        return ngaySua;
    }

    /**
     * @param ngaySua the ngaySua to set
     */
    public void setNgaySua(LocalDateTime ngaySua) {
        this.ngaySua = ngaySua;
    }

    /**
     * @return the danhGia_id
     */
    public DanhGia getDanhGia_id() {
        return danhGia_id;
    }

    /**
     * @param danhGia_id the danhGia_id to set
     */
    public void setDanhGia_id(DanhGia danhGia_id) {
        this.danhGia_id = danhGia_id;
    }
}
