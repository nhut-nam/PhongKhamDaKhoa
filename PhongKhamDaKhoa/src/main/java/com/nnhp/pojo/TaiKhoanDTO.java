/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import java.util.Date;


/**
 *
 * @author namnh
 */
public class TaiKhoanDTO {
    private Integer id;
    private String email;
    private String diaChi;
    private String avatar;
    private String hoNguoiDung;
    private String tenNguoiDung;
    private String soDienThoai;
    private Date ngaySinh;

    public TaiKhoanDTO(Integer id, String email, String diaChi, String avatar, String hoNguoiDung, String tenNguoiDung, String soDienThoai, Date ngaySinh) {
        this.id = id;
        this.email = email;
        this.diaChi = diaChi;
        this.avatar = avatar;
        this.hoNguoiDung = hoNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
    }
    
    
    
    public static TaiKhoanDTO convertToDTO(Taikhoan tk) {
        return new TaiKhoanDTO(tk.getId(), tk.getEmail(), tk.getDiaChi(), tk.getAvatar(), 
                tk.getHoNguoiDung(), tk.getTenNguoiDung(), tk.getSoDienThoai(), tk.getNgaySinh());
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHoNguoiDung() {
        return hoNguoiDung;
    }

    public void setHoNguoiDung(String hoNguoiDung) {
        this.hoNguoiDung = hoNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    
}
