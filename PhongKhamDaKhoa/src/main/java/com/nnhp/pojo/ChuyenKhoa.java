/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author namnh
 */
@Entity
public class ChuyenKhoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_chuyen_khoa")
    private String tenChuyenKhoa;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chuyenKhoa_id")
    private List<BacSiThuocChuyenKhoa> bacSiThuocChuyenKhoaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chuyenKhoa_id")
    private List<BenhVienChuyenKhoa> benhVienChuyenKhoaList;

    public ChuyenKhoa(int id, String tenChuyenKhoa) {
        this.id = id;
        this.tenChuyenKhoa = tenChuyenKhoa;
    }

    public ChuyenKhoa() {
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
     * @return the tenChuyenKhoa
     */
    public String getTenChuyenKhoa() {
        return tenChuyenKhoa;
    }

    /**
     * @param tenChuyenKhoa the tenChuyenKhoa to set
     */
    public void setTenChuyenKhoa(String tenChuyenKhoa) {
        this.tenChuyenKhoa = tenChuyenKhoa;
    }
    
    
}
