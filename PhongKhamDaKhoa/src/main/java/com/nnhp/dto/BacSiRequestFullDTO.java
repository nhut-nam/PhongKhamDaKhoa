/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author namnh
 */

public class BacSiRequestFullDTO {
    private BenhVienDTO benhVien;
    private String chuyenTri;
    private Date ngayLamViec;
    private Date ngayNghiViec;
    private List<Integer> chuyenKhoaId;
    private int benhVienId;
    private BangCapDTO bangCap;

    public BacSiRequestFullDTO() {
    }

    public BacSiRequestFullDTO(BenhVienDTO benhVien, String chuyenTri,
                               Date ngayLamViec, Date ngayNghiViec, List<Integer> chuyenKhoaId, int benhVienId, BangCapDTO bangCap) {
        this.benhVien = benhVien;
        this.chuyenTri = chuyenTri;
        this.ngayLamViec = ngayLamViec;
        this.ngayNghiViec = ngayNghiViec;
        this.chuyenKhoaId = chuyenKhoaId;
        this.benhVienId = benhVienId;
        this.bangCap = bangCap;
    }

    public BenhVienDTO getBenhVien() {
        return benhVien;
    }

    public void setBenhVien(BenhVienDTO benhVien) {
        this.benhVien = benhVien;
    }

    public String getChuyenTri() {
        return chuyenTri;
    }

    public void setChuyenTri(String chuyenTri) {
        this.chuyenTri = chuyenTri;
    }

    public Date getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(Date ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public Date getNgayNghiViec() {
        return ngayNghiViec;
    }

    public void setNgayNghiViec(Date ngayNghiViec) {
        this.ngayNghiViec = ngayNghiViec;
    }

    public List<Integer> getChuyenKhoaId() {
        return chuyenKhoaId;
    }

    public void setChuyenKhoaId(List<Integer> chuyenKhoaId) {
        this.chuyenKhoaId = chuyenKhoaId;
    }

    public int getBenhVienId() {
        return benhVienId;
    }

    public void setBenhVienId(int benhVienId) {
        this.benhVienId = benhVienId;
    }

    public BangCapDTO getBangCap() {
        return bangCap;
    }

    public void setBangCap(BangCapDTO bangCap) {
        this.bangCap = bangCap;
    }
}

