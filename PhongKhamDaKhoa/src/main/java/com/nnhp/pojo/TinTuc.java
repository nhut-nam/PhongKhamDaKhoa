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

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "tintuc")
public class TinTuc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tieu_de", nullable = false)
    private String tieuDe;
    @Column(name = "noi_dung", nullable = false)
    private String noiDung;
    @Column(name = "ngay_dang", nullable = false)
    private String ngayDang;
    @Column(name = "trang_thai")
    private boolean trangThai;
    @ManyToOne(optional = false)
    @JoinColumn(name = "quantri_id", referencedColumnName = "id")
    private Quantri quantriId;

    public TinTuc() {
    }

    public TinTuc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Quantri getQuantriId() {
        return quantriId;
    }

    public void setQuantriId(Quantri quantriId) {
        this.quantriId = quantriId;
    }
    
    
}
