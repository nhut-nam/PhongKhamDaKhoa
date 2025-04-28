/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "bacsi")
public class BacSi extends TaiKhoan implements Serializable {
    @Column(name = "chuyen_tri")
    private String chuyenTri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<LichSuKhamBenh> lichSuKhamBenhList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private Set<BangCap> bangCapSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<BacSiThuocChuyenKhoa> bacSiThuocChuyenKhoaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<NgayLamViec> ngayLamViecList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<BacSiDichVu> bacSiDichVuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private Set<DanhGia> danhGiaSet;
    @Column(name = "trang_thai")
    private boolean trangThai;
    @Column(name = "ngay_lam_viec")
    private LocalDateTime ngayLamViec;
    @Column(name = "ngay_nghi_viec")
    private LocalDateTime ngayNghiViec;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    private BenhVien benhVien_id;

    /**
     * @return the chuyenTri
     */
    public String getChuyenTri() {
        return chuyenTri;
    }

    /**
     * @param chuyenTri the chuyenTri to set
     */
    public void setChuyenTri(String chuyenTri) {
        this.chuyenTri = chuyenTri;
    }

    /**
     * @return the lichSuKhamBenhList
     */
    public List<LichSuKhamBenh> getLichSuKhamBenhList() {
        return lichSuKhamBenhList;
    }

    /**
     * @param lichSuKhamBenhList the lichSuKhamBenhList to set
     */
    public void setLichSuKhamBenhList(List<LichSuKhamBenh> lichSuKhamBenhList) {
        this.lichSuKhamBenhList = lichSuKhamBenhList;
    }

    /**
     * @return the bangCapSet
     */
    public Set<BangCap> getBangCapSet() {
        return bangCapSet;
    }

    /**
     * @param bangCapSet the bangCapSet to set
     */
    public void setBangCapSet(Set<BangCap> bangCapSet) {
        this.bangCapSet = bangCapSet;
    }

    /**
     * @return the bacSiThuocChuyenKhoaList
     */
    public List<BacSiThuocChuyenKhoa> getBacSiThuocChuyenKhoaList() {
        return bacSiThuocChuyenKhoaList;
    }

    /**
     * @param bacSiThuocChuyenKhoaList the bacSiThuocChuyenKhoaList to set
     */
    public void setBacSiThuocChuyenKhoaList(List<BacSiThuocChuyenKhoa> bacSiThuocChuyenKhoaList) {
        this.bacSiThuocChuyenKhoaList = bacSiThuocChuyenKhoaList;
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

    /**
     * @return the danhGiaSet
     */
    public Set<DanhGia> getDanhGiaSet() {
        return danhGiaSet;
    }

    /**
     * @param danhGiaSet the danhGiaSet to set
     */
    public void setDanhGiaSet(Set<DanhGia> danhGiaSet) {
        this.danhGiaSet = danhGiaSet;
    }

    /**
     * @return the trangThai
     */
    public boolean isTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the ngayLamViec
     */
    public LocalDateTime getNgayLamViec() {
        return ngayLamViec;
    }

    /**
     * @param ngayLamViec the ngayLamViec to set
     */
    public void setNgayLamViec(LocalDateTime ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    /**
     * @return the ngayNghiViec
     */
    public LocalDateTime getNgayNghiViec() {
        return ngayNghiViec;
    }

    /**
     * @param ngayNghiViec the ngayNghiViec to set
     */
    public void setNgayNghiViec(LocalDateTime ngayNghiViec) {
        this.ngayNghiViec = ngayNghiViec;
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
    
    
    
    
}
