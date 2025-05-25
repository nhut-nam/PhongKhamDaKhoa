/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.pojo.Lichkham;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author namnh
 */
public class LichKhamRequestDTO {
    private int bacSiId;
    private int benhVienChuyenKhoaDichVuId;
    private String buoi;
    private int hoSoId;
    private Date ngayHen;
    private BigDecimal soTienNhan;
    private TrangThaiLichKham trangThai;

    public LichKhamRequestDTO() {
    }

    public LichKhamRequestDTO(int bacSiId, int benhVienChuyenKhoaDichVuId, String buoi, int hoSoId, Date ngayHen, BigDecimal soTienNhan, TrangThaiLichKham trangThai) {
        this.bacSiId = bacSiId;
        this.benhVienChuyenKhoaDichVuId = benhVienChuyenKhoaDichVuId;
        this.buoi = buoi;
        this.hoSoId = hoSoId;
        this.ngayHen = ngayHen;
        this.soTienNhan = soTienNhan;
        this.trangThai = trangThai;
    }

    public Date getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }
    
//    public static Lichkham convertToEntity(Lich) {
//        
//    }

    public int getBacSiId() {
        return bacSiId;
    }

    public void setBacSiId(int bacSiId) {
        this.bacSiId = bacSiId;
    }

    public int getBenhVienChuyenKhoaDichVuId() {
        return benhVienChuyenKhoaDichVuId;
    }

    public void setBenhVienChuyenKhoaDichVuId(int benhVienChuyenKhoaDichVuId) {
        this.benhVienChuyenKhoaDichVuId = benhVienChuyenKhoaDichVuId;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    public int getHoSoId() {
        return hoSoId;
    }

    public void setHoSoId(int hoSoId) {
        this.hoSoId = hoSoId;
    }

    public BigDecimal getSoTienNhan() {
        return soTienNhan;
    }

    public void setSoTienNhan(BigDecimal soTienNhan) {
        this.soTienNhan = soTienNhan;
    }

    public TrangThaiLichKham getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiLichKham trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
