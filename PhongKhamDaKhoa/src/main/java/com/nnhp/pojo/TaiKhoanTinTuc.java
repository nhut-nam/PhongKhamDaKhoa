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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "taikhoantintuc")
@NamedQueries({
    @NamedQuery(name = "TaiKhoanTinTuc.findAll", 
                query = "SELECT t FROM TaiKhoanTinTuc t"),
    @NamedQuery(name = "TaiKhoanTinTuc.findById", 
                query = "SELECT t FROM TaiKhoanTinTuc t WHERE t.id = :id"),
    @NamedQuery(name = "TaiKhoanTinTuc.findByTrangThai", 
                query = "SELECT t FROM TaiKhoanTinTuc t WHERE t.trangThai = :trangThai"),
    @NamedQuery(name = "TaiKhoanTinTuc.findByTaikhoanId", 
                query = "SELECT t FROM TaiKhoanTinTuc t WHERE t.taikhoanId.id = :taikhoanId"),
    @NamedQuery(name = "TaiKhoanTinTuc.findByTintucId", 
                query = "SELECT t FROM TaiKhoanTinTuc t WHERE t.tintucId.id = :tintucId"),
    @NamedQuery(name = "TaiKhoanTinTuc.findByTaikhoanAndTintuc", 
                query = "SELECT t FROM TaiKhoanTinTuc t WHERE t.taikhoanId.id = :taikhoanId AND t.tintucId.id = :tintucId")})
public class TaiKhoanTinTuc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Taikhoan taikhoanId;
    @JoinColumn(name = "tintuc_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tintuc tintucId;
    @Column(name = "trang_thai")
    private boolean trangThai;

    public TaiKhoanTinTuc() {
    }

    public TaiKhoanTinTuc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Taikhoan getTaikhoanId() {
        return taikhoanId;
    }

    public void setTaikhoanId(Taikhoan taikhoanId) {
        this.taikhoanId = taikhoanId;
    }

    public Tintuc getTintucId() {
        return tintucId;
    }

    public void setTintucId(Tintuc tintucId) {
        this.tintucId = tintucId;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
