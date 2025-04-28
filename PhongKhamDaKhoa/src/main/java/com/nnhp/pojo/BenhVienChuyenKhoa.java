/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "benhvienchuyenkhoa")
public class BenhVienChuyenKhoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "chuyenkhoa_id", referencedColumnName = "id")
    private ChuyenKhoa chuyenKhoa_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "benhvien_id", referencedColumnName = "id")
    private BenhVien benhVien_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhVienChuyenKhoa_id")
    private Set<DichVu> dichVuSet;

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
     * @return the chuyenKhoa_id
     */
    public ChuyenKhoa getChuyenKhoa_id() {
        return chuyenKhoa_id;
    }

    /**
     * @param chuyenKhoa_id the chuyenKhoa_id to set
     */
    public void setChuyenKhoa_id(ChuyenKhoa chuyenKhoa_id) {
        this.chuyenKhoa_id = chuyenKhoa_id;
    }

    /**
     * @return the benhVien_id
     */
    public BenhVien getBenhVien_id() {
        return benhVien_id;
    }

    /**
     * @param benhVien_id the benhVien_id to set
     */
    public void setBenhVien_id(BenhVien benhVien_id) {
        this.benhVien_id = benhVien_id;
    }

    /**
     * @return the dichVuSet
     */
    public Set<DichVu> getDichVuSet() {
        return dichVuSet;
    }

    /**
     * @param dichVuSet the dichVuSet to set
     */
    public void setDichVuSet(Set<DichVu> dichVuSet) {
        this.dichVuSet = dichVuSet;
    }
    
}
