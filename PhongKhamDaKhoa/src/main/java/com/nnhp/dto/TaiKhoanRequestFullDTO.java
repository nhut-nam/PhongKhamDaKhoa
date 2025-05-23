/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.enums.TrangThaiTaiKhoan;
import java.util.Date;

/**
 *
 * @author namnh
 */
public class TaiKhoanRequestFullDTO {
    private Integer id;
    private String email;
    private String diaChi;
    private String avatar;
    private String hoNguoiDung;
    private String tenNguoiDung;
    private String soDienThoai;
    private Date ngaySinh;
    private String role;
    private TrangThaiTaiKhoan trangThai;
    private BacSiRequestFullDTO bacSi;
    private BenhNhanRequestFull benhNhan;
    private QuanTriRequestFull quanTri;

    public TaiKhoanRequestFullDTO(Integer id, String email, String diaChi, String avatar, String hoNguoiDung, String tenNguoiDung, String soDienThoai, Date ngaySinh, String role, TrangThaiTaiKhoan trangThai, BacSiRequestFullDTO bacSi, BenhNhanRequestFull benhNhan, QuanTriRequestFull quanTri) {
        this.id = id;
        this.email = email;
        this.diaChi = diaChi;
        this.avatar = avatar;
        this.hoNguoiDung = hoNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.role = role;
        this.trangThai = trangThai;
        this.bacSi = bacSi;
        this.quanTri = quanTri;
        this.benhNhan = benhNhan;
    }
    
    

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the hoNguoiDung
     */
    public String getHoNguoiDung() {
        return hoNguoiDung;
    }

    /**
     * @param hoNguoiDung the hoNguoiDung to set
     */
    public void setHoNguoiDung(String hoNguoiDung) {
        this.hoNguoiDung = hoNguoiDung;
    }

    /**
     * @return the tenNguoiDung
     */
    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    /**
     * @param tenNguoiDung the tenNguoiDung to set
     */
    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
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
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the trangThai
     */
    public TrangThaiTaiKhoan getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(TrangThaiTaiKhoan trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the bacSi
     */
    public BacSiRequestFullDTO getBacSi() {
        return bacSi;
    }

    /**
     * @param bacSi the bacSi to set
     */
    public void setBacSi(BacSiRequestFullDTO bacSi) {
        this.bacSi = bacSi;
    }

    /**
     * @return the quanTri
     */
    public QuanTriRequestFull getQuanTri() {
        return quanTri;
    }

    /**
     * @param quanTri the quanTri to set
     */
    public void setQuanTri(QuanTriRequestFull quanTri) {
        this.quanTri = quanTri;
    }

    /**
     * @return the benhNhan
     */
    public BenhNhanRequestFull getBenhNhan() {
        return benhNhan;
    }

    /**
     * @param benhNhan the benhNhan to set
     */
    public void setBenhNhan(BenhNhanRequestFull benhNhan) {
        this.benhNhan = benhNhan;
    }
    
    
}
