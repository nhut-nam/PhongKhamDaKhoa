/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

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
@Table(name = "bacsithuocchuyenkhoa")
public class BacSiThuocChuyenKhoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id", nullable = false)
    private BacSi bacSi_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "chuyenkhoa_id", referencedColumnName = "id", nullable = false)
    private ChuyenKhoa chuyenKhoa_id;

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
}
