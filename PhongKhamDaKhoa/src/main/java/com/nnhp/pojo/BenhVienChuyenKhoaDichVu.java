/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.LoaiThanhToan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
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
    
    @NamedQuery(name = "BenhVienChuyenKhoaDichVu.findByChuyenKhoa", 
                query = "SELECT b FROM BenhVienChuyenKhoaDichVu b WHERE b.benhvienchuyenkhoaId.id = :chuyenKhoaId"),
    
    @NamedQuery(name = "BenhVienChuyenKhoaDichVu.findByDichVu", 
                query = "SELECT b FROM BenhVienChuyenKhoaDichVu b WHERE b.dichvuId.id = :dichVuId")
})
public class BenhVienChuyenKhoaDichVu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "gia_tien")
    private double giaTien;
    @Column(name = "loai_thanh_toan")
    private LoaiThanhToan loaiThanhToan;
    @ManyToOne
    @JoinColumn(name = "benhvienchuyenkhoaiId")
    private Benhvienchuyenkhoa benhvienchuyenkhoaId;
    @ManyToOne
    @JoinColumn(name = "dichvuId")
    private Dichvu dichvuId;

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

    public Dichvu getDichVu() {
        return dichvuId;
    }

    public void setDichVu(Dichvu dichvuId) {
        this.dichvuId = dichvuId;
    }

    public LoaiThanhToan getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(LoaiThanhToan loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }
    
    
}
