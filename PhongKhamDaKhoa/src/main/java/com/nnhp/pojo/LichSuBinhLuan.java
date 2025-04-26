/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author namnh
 */
@Entity
public class LichSuBinhLuan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "binh_luan")
    private String binhLuan;
    @Column(name = "binh_luan_cu")
    private String binhLuanCu;
    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "danhgia_id", referencedColumnName = "id")
    private DanhGia danhGia_id;
}
