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
@Table(name = "bacsidichvu")
public class BacSiDichVu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "gia_tien")
    private double giaTien;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    private BacSi bacSi_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "dichvu_id", referencedColumnName = "id")
    private DichVu dichVu_id;

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
     * @return the giaTien
     */
    public double getGiaTien() {
        return giaTien;
    }

    /**
     * @param giaTien the giaTien to set
     */
    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
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
     * @return the dichVu_id
     */
    public DichVu getDichVu_id() {
        return dichVu_id;
    }

    /**
     * @param dichVu_id the dichVu_id to set
     */
    public void setDichVu_id(DichVu dichVu_id) {
        this.dichVu_id = dichVu_id;
    }
}
