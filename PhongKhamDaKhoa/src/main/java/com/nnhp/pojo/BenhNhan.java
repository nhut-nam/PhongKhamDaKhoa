/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author namnh
 */
@Entity
public class BenhNhan extends TaiKhoan implements Serializable {
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhNhan_id")
    private List<HoSo> danhSachHoSo;

    public BenhNhan(int id) {
        super(id);
    }

    public BenhNhan() {
    }
    
}
