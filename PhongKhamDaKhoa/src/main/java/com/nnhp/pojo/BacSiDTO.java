/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.TrangThaiTaiKhoan;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class BacSiDTO extends TaiKhoanDTO {
    private int benhVienId;
    private List<Integer> chuyenKhoasId;
    private Date ngayLamViec;
    private Date ngayNghiViec;

    public BacSiDTO(Integer id, String email, String diaChi, String avatar, String hoNguoiDung, 
            String tenNguoiDung, String soDienThoai, Date ngaySinh, String role,
            int benhVienId, List<Integer> chuyenKhoasId, TrangThaiTaiKhoan trangThai, Date ngayLamViec, Date ngayNghiViec) {
        super(id, email, diaChi, avatar, hoNguoiDung, tenNguoiDung, soDienThoai, ngaySinh, role, trangThai);
        this.benhVienId = benhVienId;
        this.chuyenKhoasId = chuyenKhoasId;
        this.ngayLamViec = ngayLamViec;
        this.ngayNghiViec = ngayNghiViec;
    }
    
    public static BacSiDTO convertToDTO(Bacsi bs) {
        List<Integer> cks = bs.getBacsithuocchuyenkhoaCollection().stream().map(bsck -> bsck.getChuyenkhoaId().getId()).collect(Collectors.toList());
        return new BacSiDTO(bs.getId(), bs.getEmail(), bs.getDiaChi(), bs.getAvatar(), bs.getHoNguoiDung()
                , bs.getTenNguoiDung(), bs.getSoDienThoai(), bs.getNgaySinh(), bs.getRole(), bs.getBenhvienId().getId()
                , cks, bs.getTrangThai(), bs.getNgayLamViec(), bs.getNgayNghiViec());
    }

    /**
     * @return the benhVienId
     */
    public int getBenhVienId() {
        return benhVienId;
    }

    /**
     * @param benhVienId the benhVienId to set
     */
    public void setBenhVienId(int benhVienId) {
        this.benhVienId = benhVienId;
    }

    /**
     * @return the chuyenKhoasId
     */
    public List<Integer> getChuyenKhoasId() {
        return chuyenKhoasId;
    }

    /**
     * @param chuyenKhoasId the chuyenKhoasId to set
     */
    public void setChuyenKhoasId(List<Integer> chuyenKhoasId) {
        this.chuyenKhoasId = chuyenKhoasId;
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
    
    
}
