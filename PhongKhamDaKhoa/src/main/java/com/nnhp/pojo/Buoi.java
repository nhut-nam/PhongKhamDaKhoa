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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "buoi")
public class Buoi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "thoi_gian")
    private String thoiGian;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buoi_id")
    private List<NgayLamViec> ngayLamViecList;

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
     * @return the thoiGian
     */
    public String getThoiGian() {
        return thoiGian;
    }

    /**
     * @param thoiGian the thoiGian to set
     */
    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    /**
     * @return the ngayLamViecList
     */
    public List<NgayLamViec> getNgayLamViecList() {
        return ngayLamViecList;
    }

    /**
     * @param ngayLamViecList the ngayLamViecList to set
     */
    public void setNgayLamViecList(List<NgayLamViec> ngayLamViecList) {
        this.ngayLamViecList = ngayLamViecList;
    }
}
