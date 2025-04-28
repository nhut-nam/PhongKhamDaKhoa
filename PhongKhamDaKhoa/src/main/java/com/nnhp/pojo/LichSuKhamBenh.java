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
import java.time.LocalDate;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "lichsukhambenh")
public class LichSuKhamBenh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "chan_doan")
    private String chanDoan;
    @ManyToOne(optional = false)
    @JoinColumn(name = "hoso_id", referencedColumnName = "id", nullable = false)
    private HoSo hoSo_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id", nullable = false)
    private BacSi bacSi_id;
    @Column(name = "don_thuoc")
    private String donThuoc;
    @Column(name = "ngay_kham")
    private LocalDate ngayKham;
    
    public LichSuKhamBenh(int id, String chanDoan, HoSo hoSo, BacSi bacSi, String donThuoc) {
        this.id = id;
        this.chanDoan = chanDoan;
        this.hoSo_id = hoSo;
        this.bacSi_id = bacSi;
        this.donThuoc = donThuoc;
    }

    public LichSuKhamBenh() {
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
     * @return the chanDoan
     */
    public String getChanDoan() {
        return chanDoan;
    }

    /**
     * @param chanDoan the chanDoan to set
     */
    public void setChanDoan(String chanDoan) {
        this.chanDoan = chanDoan;
    }

    /**
     * @return the hoSo
     */
    public HoSo getHoSo() {
        return getHoSo_id();
    }

    /**
     * @param hoSo the hoSo to set
     */
    public void setHoSo(HoSo hoSo) {
        this.setHoSo_id(hoSo);
    }

    /**
     * @return the bacSi
     */
    public BacSi getBacSi() {
        return getBacSi_id();
    }

    /**
     * @param bacSi the bacSi to set
     */
    public void setBacSi(BacSi bacSi) {
        this.setBacSi_id(bacSi);
    }

    /**
     * @return the donThuoc
     */
    public String getDonThuoc() {
        return donThuoc;
    }

    /**
     * @param donThuoc the donThuoc to set
     */
    public void setDonThuoc(String donThuoc) {
        this.donThuoc = donThuoc;
    }

    /**
     * @return the hoSo_id
     */
    public HoSo getHoSo_id() {
        return hoSo_id;
    }

    /**
     * @param hoSo_id the hoSo_id to set
     */
    public void setHoSo_id(HoSo hoSo_id) {
        this.hoSo_id = hoSo_id;
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
     * @return the ngayKham
     */
    public LocalDate getNgayKham() {
        return ngayKham;
    }

    /**
     * @param ngayKham the ngayKham to set
     */
    public void setNgayKham(LocalDate ngayKham) {
        this.ngayKham = ngayKham;
    }
    
    
}
