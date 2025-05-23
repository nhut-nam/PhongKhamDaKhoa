/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.TrangThaiLichKham;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author hoang
 */
public class LichKhamBacSiDTO {
    private String hoTen;
    private String email;
    private String soDienThoai;
    private boolean gioiTinh;
    private Date ngayHen;
    private Date ngayTao;
    private Short trangThai;
    private String buoi;
    
    public LichKhamBacSiDTO(String hoTen, String email, String soDienThoai, boolean gioiTinh, Date ngayHen, Date ngayTao, Short trangThai, String buoi)
    {
        this.hoTen = hoTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.ngayHen = ngayHen;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.buoi = buoi;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the soDienThoai
     */
    public String getSoDienThoai() {
        return soDienThoai;
    }

    /**
     * @param soDienThoai the soDienThoai to set
     */
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    /**
     * @return the gioiTinh
     */
    public boolean isGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the ngayHen
     */
    public Date getNgayHen() {
        return ngayHen;
    }

    /**
     * @param ngayHen the ngayHen to set
     */
    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    /**
     * @return the trangThai
     */
    public Short getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(Short trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the buoi
     */
    public String getBuoi() {
        return buoi;
    }

    /**
     * @param buoi the buoi to set
     */
    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }
}
