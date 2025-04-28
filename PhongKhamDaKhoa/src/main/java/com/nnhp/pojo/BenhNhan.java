/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "benhnhan")
public class BenhNhan extends TaiKhoan implements Serializable {
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhNhan_id")
    private List<HoSo> danhSachHoSo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhNhan_id")
    private Set<LichKham> lichKhamSet;
    
    public BenhNhan(int id) {
        super(id);
    }

    public BenhNhan() {
    }

    /**
     * @return the danhSachHoSo
     */
    public List<HoSo> getDanhSachHoSo() {
        return danhSachHoSo;
    }

    /**
     * @param danhSachHoSo the danhSachHoSo to set
     */
    public void setDanhSachHoSo(List<HoSo> danhSachHoSo) {
        this.danhSachHoSo = danhSachHoSo;
    }

    /**
     * @return the lichKhamSet
     */
    public Set<LichKham> getLichKhamSet() {
        return lichKhamSet;
    }

    /**
     * @param lichKhamSet the lichKhamSet to set
     */
    public void setLichKhamSet(Set<LichKham> lichKhamSet) {
        this.lichKhamSet = lichKhamSet;
    }

}
