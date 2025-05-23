/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.pojo.Hoso;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class HoSoDTO {
    private Integer id;
    private String email;
    private Date ngayTao;
    private Date ngaySinh;
    private String soDienThoai;
    private String hoTen;
    private String diaChi;
    private boolean gioiTinh;

    public HoSoDTO() {
    }
    

    public HoSoDTO(Integer id, String email, Date ngayTao, Date ngaySinh, String soDienThoai, String hoTen, String diaChi, boolean gioiTinh) {
        this.id = id;
        this.email = email;
        this.ngayTao = ngayTao;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public static HoSoDTO convertToDTO(Hoso hs) {
        return new HoSoDTO(hs.getId(), hs.getEmail(), hs.getNgayTao(), hs.getNgaySinh(), hs.getSoDienThoai(), hs.getHoTen(), hs.getDiaChi(), hs.isGioiTinh());
    }
    
    public static List<HoSoDTO> convertToDTOList(List<Hoso> hss) {
        return hss.stream().map(HoSoDTO::convertToDTO).collect(Collectors.toList());
    }
    
    public static Hoso updateAttribute(Hoso hsOld, HoSoDTO hsDTO) {
        hsOld.setId(hsDTO.getId());
        hsOld.setEmail(hsDTO.getEmail());
        hsOld.setDiaChi(hsDTO.getDiaChi());
        hsOld.setGioiTinh(hsDTO.isGioiTinh());
        hsOld.setHoTen(hsDTO.getHoTen());
        hsOld.setSoDienThoai(hsDTO.getSoDienThoai());
        hsOld.setNgaySinh(hsDTO.getNgaySinh());
        hsOld.setNgayTao(hsDTO.getNgayTao());
        return hsOld;
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

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    
}
