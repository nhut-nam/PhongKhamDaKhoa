/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.LoaiDichVu;
import com.nnhp.enums.LoaiThanhToan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "benhvien_chuyenkhoa_dichvu")
@NamedQueries({
    @NamedQuery(name = "BenhVienChuyenKhoaDichVu.findAll",
            query = "SELECT b FROM BenhVienChuyenKhoaDichVu b"),

    @NamedQuery(name = "BenhVienChuyenKhoaDichVu.findByBenhVienChuyenKhoa",
            query = "SELECT b FROM BenhVienChuyenKhoaDichVu b WHERE b.benhvienchuyenkhoaId.id = :benhVienChuyenKhoaId"),})
public class BenhVienChuyenKhoaDichVu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "gia_tien")
    private double giaTien;
    @ManyToOne
    @JoinColumn(name = "benhvienchuyenkhoaiId")
    private Benhvienchuyenkhoa benhvienchuyenkhoaId;
    @Column(name = "loai_dich_vu")
    @Enumerated(EnumType.STRING)
    private LoaiDichVu loaiDichVu;
    @Column(name = "loai_thanh_toan")
    @Enumerated(EnumType.STRING)
    private LoaiThanhToan loaiThanhToan;
    @Size(max = 255)
    @Column(name = "ten_dich_vu")
    private String tenDichVu;

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public Benhvienchuyenkhoa getBenhVienChuyenKhoa() {
        return benhvienchuyenkhoaId;
    }

    public void setBenhVienChuyenKhoa(Benhvienchuyenkhoa benhvienchuyenkhoaId) {
        this.benhvienchuyenkhoaId = benhvienchuyenkhoaId;
    }

    public LoaiThanhToan getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(LoaiThanhToan loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public Benhvienchuyenkhoa getBenhvienchuyenkhoaId() {
        return benhvienchuyenkhoaId;
    }

    public void setBenhvienchuyenkhoaId(Benhvienchuyenkhoa benhvienchuyenkhoaId) {
        this.benhvienchuyenkhoaId = benhvienchuyenkhoaId;
    }

    public LoaiDichVu getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BenhVienChuyenKhoaDichVu)) {
            return false;
        }
        BenhVienChuyenKhoaDichVu that = (BenhVienChuyenKhoaDichVu) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}
