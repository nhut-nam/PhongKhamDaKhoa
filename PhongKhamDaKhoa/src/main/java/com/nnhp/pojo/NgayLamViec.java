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
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "ngaylamviec")
public class NgayLamViec implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "trang_thai")
    private boolean trangThai;
    @Column(name = "ghi_chu")
    private String ghiChu;
    @Column(name = "ngay")
    private Date ngay;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacSi_id", referencedColumnName = "id")
    private BacSi bacSi_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "buoi_id", referencedColumnName = "id")
    private Buoi buoi_id;

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
     * @return the trangThai
     */
    public boolean isTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     * @return the ngay
     */
    public Date getNgay() {
        return ngay;
    }

    /**
     * @param ngay the ngay to set
     */
    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

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

    /**
     * @return the buoi_id
     */
    public Buoi getBuoi_id() {
        return buoi_id;
    }

    /**
     * @param buoi_id the buoi_id to set
     */
    public void setBuoi_id(Buoi buoi_id) {
        this.buoi_id = buoi_id;
    }
}
