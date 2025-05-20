/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.TrangThaiBangCap;
import java.util.Date;

/**
 *
 * @author namnh
 */
public class BangCapDTO {

    private Integer id;
    private TrangThaiBangCap trangThai;
    private Date ngayCap;
    private Date ngayHetHan;
    private String coQuanCap;
    private String hinhMatTruoc;

    // Constructor
    public BangCapDTO(Integer id, TrangThaiBangCap trangThai, Date ngayCap,
            Date ngayHetHan, String coQuanCap, String hinhMatTruoc) {
        this.id = id;
        this.trangThai = trangThai;
        this.ngayCap = ngayCap;
        this.ngayHetHan = ngayHetHan;
        this.coQuanCap = coQuanCap;
        this.hinhMatTruoc = hinhMatTruoc;
    }

    // Getters và Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TrangThaiBangCap getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiBangCap trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getCoQuanCap() {
        return coQuanCap;
    }

    public void setCoQuanCap(String coQuanCap) {
        this.coQuanCap = coQuanCap;
    }

    public String getHinhMatTruoc() {
        return hinhMatTruoc;
    }

    public void setHinhMatTruoc(String hinhMatTruoc) {
        this.hinhMatTruoc = hinhMatTruoc;
    }

    // Hàm chuyển từ entity sang DTO
    public static BangCapDTO convertToDTO(Bangcap bc) {
        return new BangCapDTO(
                bc.getId(),
                bc.getTrangThai(),
                bc.getNgayCap(),
                bc.getNgayHetHan(),
                bc.getCoQuanCap(),
                bc.getHinhMatTruoc()
        );
    }

    public static Bangcap convertToEntity(BangCapDTO dto) {
        Bangcap entity = new Bangcap();
        entity.setId(dto.getId()); // Nếu bạn dùng IDENTITY thì có thể bỏ nếu tạo mới
        entity.setTrangThai(dto.getTrangThai());
        entity.setNgayCap(dto.getNgayCap());
        entity.setNgayHetHan(dto.getNgayHetHan());
        entity.setCoQuanCap(dto.getCoQuanCap());
        entity.setHinhMatTruoc(dto.getHinhMatTruoc());
        return entity;
    }

}
