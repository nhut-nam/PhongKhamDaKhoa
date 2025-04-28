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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "benhvien")
public class BenhVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_benh_vien")
    private String tenBenhVien;
    @Column(name = "dia_chi")
    private String diaChi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhVien_id")
    private Set<BacSi> bacSiSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhVien_id")
    private List<BenhVienChuyenKhoa> benhVienChuyenKhoaList;
   
    public BenhVien(int id, String tenBenhVien, String diaChi) {
        this.id = id;
        this.tenBenhVien = tenBenhVien;
        this.diaChi = diaChi;
    }

    public BenhVien() {
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
     * @return the tenBenhVien
     */
    public String getTenBenhVien() {
        return tenBenhVien;
    }

    /**
     * @param tenBenhVien the tenBenhVien to set
     */
    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the bacSiSet
     */
    public Set<BacSi> getBacSiSet() {
        return bacSiSet;
    }

    /**
     * @param bacSiSet the bacSiSet to set
     */
    public void setBacSiSet(Set<BacSi> bacSiSet) {
        this.bacSiSet = bacSiSet;
    }

    /**
     * @return the benhVienChuyenKhoaList
     */
    public List<BenhVienChuyenKhoa> getBenhVienChuyenKhoaList() {
        return benhVienChuyenKhoaList;
    }

    /**
     * @param benhVienChuyenKhoaList the benhVienChuyenKhoaList to set
     */
    public void setBenhVienChuyenKhoaList(List<BenhVienChuyenKhoa> benhVienChuyenKhoaList) {
        this.benhVienChuyenKhoaList = benhVienChuyenKhoaList;
    }
    
    
}
