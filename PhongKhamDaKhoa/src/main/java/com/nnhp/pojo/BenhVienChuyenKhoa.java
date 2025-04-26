/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author namnh
 */
@Entity
public class BenhVienChuyenKhoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "chuyenkhoa_id", referencedColumnName = "id")
    private ChuyenKhoa chuyenKhoa_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "benhvien_id", referencedColumnName = "id")
    private BenhVien benhVien_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhVienChuyenKhoa_id")
    private Set<DichVu> dichVuSet;
    
}
