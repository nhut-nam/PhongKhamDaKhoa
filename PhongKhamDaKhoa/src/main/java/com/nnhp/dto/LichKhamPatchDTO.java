/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.pojo.Lichkham;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Date;

/**
 *
 * @author namnh
 */
public class LichKhamPatchDTO {
    private Date ngayHen;
    @Enumerated(EnumType.STRING)
    private TrangThaiLichKham trangThai;

    public LichKhamPatchDTO(Date ngayHen, TrangThaiLichKham trangThai) {
        this.ngayHen = ngayHen;
        this.trangThai = trangThai;
    }

    public LichKhamPatchDTO() {
    }
    
    
    
    public Lichkham updateAtrribute(Lichkham lk, LichKhamPatchDTO lkPatch) {
        if (lkPatch.getNgayHen() != null) {
            lk.setNgayHen(lkPatch.getNgayHen());
        }
        if (lkPatch.getTrangThai() != null) {
            lk.setTrangThai(lkPatch.getTrangThai());
        }
        return lk;
    }

    public Date getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    public TrangThaiLichKham getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiLichKham trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
