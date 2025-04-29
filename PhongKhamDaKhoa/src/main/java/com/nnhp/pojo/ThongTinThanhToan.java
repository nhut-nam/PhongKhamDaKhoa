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
 * @author hoang
 */
@Entity
//@Table(name = "thongtinthanhtoan")
public class ThongTinThanhToan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "so_tai_khoan")
    private String soTaiKhoan;
    @ManyToOne(optional = false)
    @JoinColumn(name = "lichkham_id", referencedColumnName = "id")
    private LichKham lichKham_id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.ThongTinThanhToan[ id=" + getId() + " ]";
    }

    /**
     * @return the soTaiKhoan
     */
    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    /**
     * @param soTaiKhoan the soTaiKhoan to set
     */
    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }
    
}
