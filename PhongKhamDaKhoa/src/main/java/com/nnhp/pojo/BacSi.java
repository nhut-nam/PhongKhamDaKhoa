/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
public class BacSi extends TaiKhoan implements Serializable {
    @Column(name = "chuyen_tri")
    private String chuyenTri;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<LichSuKhamBenh> lichSuKhamBenhList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private Set<BangCap> bangCapSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<BacSiThuocChuyenKhoa> bacSiThuocChuyenKhoaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<NgayLamViec> ngayLamViecList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private List<BacSiDichVu> bacSiDichVuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSi_id")
    private Set<DanhGia> danhGiaSet;
    
    public BacSi(String chuyenTri, int id) {
        super(id);
        this.chuyenTri = chuyenTri;
    }

    /**
     * @return the chuyenTri
     */
    public String getChuyenTri() {
        return chuyenTri;
    }

    /**
     * @param chuyenTri the chuyenTri to set
     */
    public void setChuyenTri(String chuyenTri) {
        this.chuyenTri = chuyenTri;
    }
    
    
}
