/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.enums.TrangThaiTaiKhoan;
import com.nnhp.pojo.Bacsi;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class BacSiSimpleDTO extends TaiKhoanDTO {
    private String chuyenTri;
    private Date ngayLamViec;
    private Date ngayNghiViec;
    
    public BacSiSimpleDTO(Integer id, String email, String diaChi, String avatar, String hoNguoiDung,
            String tenNguoiDung, String soDienThoai, Date ngaySinh, String role,
            TrangThaiTaiKhoan trangThai, String chuyenTri, Date ngayLamViec, Date ngayNghiViec) {
        super(id, email, diaChi, avatar, hoNguoiDung, tenNguoiDung, soDienThoai, ngaySinh, role, trangThai);
        this.chuyenTri = chuyenTri;
        this.ngayLamViec = ngayLamViec;
        this.ngayNghiViec = ngayNghiViec;
    }

    public static BacSiSimpleDTO convertToDTO(Bacsi bs) {
        return new BacSiSimpleDTO(bs.getId(), bs.getEmail(), bs.getDiaChi(), bs.getAvatar(), bs.getHoNguoiDung(),
                 bs.getTenNguoiDung(), bs.getSoDienThoai(), bs.getNgaySinh(), bs.getRole(),
                 bs.getTrangThai(), bs.getChuyenTri(), bs.getNgayLamViec(), bs.getNgayNghiViec());
    }

    public static List<BacSiSimpleDTO> convertToDTOList(List<Bacsi> bacsiList) {
        return bacsiList.stream()
                .map(BacSiSimpleDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @return the ngayLamViec
     */
    public Date getNgayLamViec() {
        return ngayLamViec;
    }

    /**
     * @param ngayLamViec the ngayLamViec to set
     */
    public void setNgayLamViec(Date ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    /**
     * @return the ngayNghiViec
     */
    public Date getNgayNghiViec() {
        return ngayNghiViec;
    }

    /**
     * @param ngayNghiViec the ngayNghiViec to set
     */
    public void setNgayNghiViec(Date ngayNghiViec) {
        this.ngayNghiViec = ngayNghiViec;
    }

    public String getChuyenTri() {
        return chuyenTri;
    }

    public void setChuyenTri(String chuyenTri) {
        this.chuyenTri = chuyenTri;
    }
}
