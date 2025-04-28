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
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "danhgia")
public class DanhGia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "binh_luan")
    private String binhLuan;
    @Column(name = "so_sao")
    private short soSao;
    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;
    @Column(name = "chinh_sua")
    private boolean chinhSua;
    @Column(name = "phan_hoi")
    private String phanHoi;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    private BacSi bacSi_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
    private TaiKhoan taiKhoan_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "danhGia_id")
    private Set<LichSuBinhLuan> lichSuBinhLuanSet;

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
     * @return the binhLuan
     */
    public String getBinhLuan() {
        return binhLuan;
    }

    /**
     * @param binhLuan the binhLuan to set
     */
    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    /**
     * @return the soSao
     */
    public short getSoSao() {
        return soSao;
    }

    /**
     * @param soSao the soSao to set
     */
    public void setSoSao(short soSao) {
        this.soSao = soSao;
    }

    /**
     * @return the ngayTao
     */
    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    /**
     * @return the chinhSua
     */
    public boolean isChinhSua() {
        return chinhSua;
    }

    /**
     * @param chinhSua the chinhSua to set
     */
    public void setChinhSua(boolean chinhSua) {
        this.chinhSua = chinhSua;
    }

    /**
     * @return the phanHoi
     */
    public String getPhanHoi() {
        return phanHoi;
    }

    /**
     * @param phanHoi the phanHoi to set
     */
    public void setPhanHoi(String phanHoi) {
        this.phanHoi = phanHoi;
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
     * @return the taiKhoan_id
     */
    public TaiKhoan getTaiKhoan_id() {
        return taiKhoan_id;
    }

    /**
     * @param taiKhoan_id the taiKhoan_id to set
     */
    public void setTaiKhoan_id(TaiKhoan taiKhoan_id) {
        this.taiKhoan_id = taiKhoan_id;
    }

    /**
     * @return the lichSuBinhLuanSet
     */
    public Set<LichSuBinhLuan> getLichSuBinhLuanSet() {
        return lichSuBinhLuanSet;
    }

    /**
     * @param lichSuBinhLuanSet the lichSuBinhLuanSet to set
     */
    public void setLichSuBinhLuanSet(Set<LichSuBinhLuan> lichSuBinhLuanSet) {
        this.lichSuBinhLuanSet = lichSuBinhLuanSet;
    }
}
