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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "tintuc")
@NamedQueries({
    @NamedQuery(name = "Tintuc.findAll", 
                query = "SELECT t FROM Tintuc t"),
    @NamedQuery(name = "Tintuc.findById", 
                query = "SELECT t FROM Tintuc t WHERE t.id = :id"),
    @NamedQuery(name = "Tintuc.findByTieuDe", 
                query = "SELECT t FROM Tintuc t WHERE t.tieuDe = :tieuDe"),
    @NamedQuery(name = "Tintuc.findByNoiDung", 
                query = "SELECT t FROM Tintuc t WHERE t.noiDung LIKE :noiDung"),
    @NamedQuery(name = "Tintuc.findByNgayDang", 
                query = "SELECT t FROM Tintuc t WHERE t.ngayDang = :ngayDang"),
    @NamedQuery(name = "Tintuc.findByTieuDeContaining", 
                query = "SELECT t FROM Tintuc t WHERE t.tieuDe LIKE :tieuDe"),
    @NamedQuery(name = "Tintuc.findLatest", 
                query = "SELECT t FROM Tintuc t ORDER BY t.ngayDang DESC")
})
public class Tintuc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tieu_de", nullable = false)
    private String tieuDe;
    @Column(name = "noi_dung", nullable = false)
    private String noiDung;
    @Column(name = "ngay_dang", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngayDang;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tintucId")
    private Collection<TaiKhoanTinTuc> taikhoantintucCollection;

    public Tintuc() {
    }

    public Tintuc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public void setTaikhoantintucCollection(Collection<TaiKhoanTinTuc> taikhoantintucCollection) {
        this.taikhoantintucCollection = taikhoantintucCollection;
    }

    
}
