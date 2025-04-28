/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.LoaiDichVu;
import com.nnhp.enums.LoaiThanhToan;
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

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "dichvu")
public class DichVu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_dich_vu")
    private String tenDichVu;
    @Column(name = "loai_dich_vu")
    private LoaiDichVu loaiDichVu;
    @Column(name = "loai_thanh_toan")
    private LoaiThanhToan loaiThanhToan;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "benhvienchuyenkhoa_id", referencedColumnName = "id")
    private BenhVienChuyenKhoa benhVienChuyenKhoa_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dichVu_id")
    private List<BacSiDichVu> bacSiDichVuList;

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
     * @return the tenDichVu
     */
    public String getTenDichVu() {
        return tenDichVu;
    }

    /**
     * @param tenDichVu the tenDichVu to set
     */
    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    /**
     * @return the loaiDichVu
     */
    public LoaiDichVu getLoaiDichVu() {
        return loaiDichVu;
    }

    /**
     * @param loaiDichVu the loaiDichVu to set
     */
    public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    /**
     * @return the loaiThanhToan
     */
    public LoaiThanhToan getLoaiThanhToan() {
        return loaiThanhToan;
    }

    /**
     * @param loaiThanhToan the loaiThanhToan to set
     */
    public void setLoaiThanhToan(LoaiThanhToan loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    /**
     * @return the benhVienChuyenKhoa_id
     */
    public BenhVienChuyenKhoa getBenhVienChuyenKhoa_id() {
        return benhVienChuyenKhoa_id;
    }

    /**
     * @param benhVienChuyenKhoa_id the benhVienChuyenKhoa_id to set
     */
    public void setBenhVienChuyenKhoa_id(BenhVienChuyenKhoa benhVienChuyenKhoa_id) {
        this.benhVienChuyenKhoa_id = benhVienChuyenKhoa_id;
    }

    /**
     * @return the bacSiDichVuList
     */
    public List<BacSiDichVu> getBacSiDichVuList() {
        return bacSiDichVuList;
    }

    /**
     * @param bacSiDichVuList the bacSiDichVuList to set
     */
    public void setBacSiDichVuList(List<BacSiDichVu> bacSiDichVuList) {
        this.bacSiDichVuList = bacSiDichVuList;
    }
}
