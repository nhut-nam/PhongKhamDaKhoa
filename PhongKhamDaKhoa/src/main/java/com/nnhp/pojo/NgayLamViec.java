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
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
public class NgayLamViec implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "trang_thai")
    private boolean trangThai;
    @Column(name = "ghi_chu")
    private String ghiChu;
    @Column(name = "ngay")
    private Date ngay;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacSi_id", referencedColumnName = "id")
    private BacSi bacSi_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "buoi_id", referencedColumnName = "id")
    private Buoi buoi_id;
}
