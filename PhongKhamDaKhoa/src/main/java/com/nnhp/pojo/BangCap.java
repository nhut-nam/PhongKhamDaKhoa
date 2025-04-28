/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.TrangThaiBangCap;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "bangcap")
public class BangCap implements Serializable {

    /**
     * @return the bacSi_id
     */
    public BacSi getBacSi_id() {
        return bacSi_id;
    }

    /**
     * @param bacSi_id the bacSi_id to set
     */
    public void setBacSi_id(BacSi bacSi_id) {
        this.bacSi_id = bacSi_id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ngay_cap")
    private Date ngayCap;
    @Column(name = "ngay_het_han")
    private Date ngayHetHan;
    @Column(name = "co_quan_cap")
    private String coQuanCap;
    @Column(name = "trang_thai")
    private TrangThaiBangCap trangThai;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id", nullable = false)
    private BacSi bacSi_id;

    public BangCap(int id, Date ngayCap, Date ngayHetHan, String coQuanCap, TrangThaiBangCap trangThai) {
        this.id = id;
        this.ngayCap = ngayCap;
        this.ngayHetHan = ngayHetHan;
        this.coQuanCap = coQuanCap;
        this.trangThai = trangThai;
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
     * @return the ngayCap
     */
    public Date getNgayCap() {
        return ngayCap;
    }

    /**
     * @param ngayCap the ngayCap to set
     */
    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    /**
     * @return the ngayHetHan
     */
    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    /**
     * @param ngayHetHan the ngayHetHan to set
     */
    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    /**
     * @return the coQuanCap
     */
    public String getCoQuanCap() {
        return coQuanCap;
    }

    /**
     * @param coQuanCap the coQuanCap to set
     */
    public void setCoQuanCap(String coQuanCap) {
        this.coQuanCap = coQuanCap;
    }

    /**
     * @return the trangThai
     */
    public TrangThaiBangCap getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(TrangThaiBangCap trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
