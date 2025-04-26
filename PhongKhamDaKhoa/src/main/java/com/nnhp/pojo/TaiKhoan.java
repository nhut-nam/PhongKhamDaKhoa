/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "taikhoan")
@Inheritance(strategy = InheritanceType.JOINED)
public class TaiKhoan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "ho_nguoi_dung", nullable = false)
    private String hoNguoiDung;
    @Column(name = "ten_nguoi_dung", nullable = false)
    private String tenNguoiDung;
    @Column(name = "ngay_sinh", nullable = false)
    private Date ngaySinh;
    @Column(name = "so_dien_thoai", nullable = false)
    private String soDienThoai;
    @Column(name = "ghi_chu")
    private String ghiChu;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "mat_khau", nullable = false)
    private String matKhau;
    @Column(name = "role")
    private Role role;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taiKhoan_id")
    private Set<DanhGia> danhGiaSet;

    public TaiKhoan(int id) {
        this.id = id;
    }

    public TaiKhoan() {
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
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
    
    
}
