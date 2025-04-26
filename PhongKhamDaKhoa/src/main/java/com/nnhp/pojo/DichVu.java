/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.LoaiDichVu;
import com.nnhp.enums.LoaiThanhToan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author namnh
 */
@Entity
public class DichVu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_dich_vu")
    private String tenDichVu;
    @Column(name = "loai_dich_vu")
    private LoaiDichVu loaiDichVu;
    @Column(name = "loai_thanh_toan")
    private LoaiThanhToan loaiThanhToan;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "benhvienchuyenkhoa_id", referencedColumnName = "id")
    private BenhVienChuyenKhoa benhVienChuyenKhoa_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dichVu_id")
    private List<BacSiDichVu> bacSiDichVuList;
}
