/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

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
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
public class DanhGia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "binh_luan")
    private String binhLuan;
    @Column(name = "so_sao")
    private short soSao;
    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;
    @Column(name = "chinh_sua")
    private boolean chinhSua;
    @Column(name = "phan_hoi")
    private String phanHoi;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    private BacSi bacSi_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
    private TaiKhoan taiKhoan_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "danhGia_id")
    private Set<LichSuBinhLuan> lichSuBinhLuanSet;
}
