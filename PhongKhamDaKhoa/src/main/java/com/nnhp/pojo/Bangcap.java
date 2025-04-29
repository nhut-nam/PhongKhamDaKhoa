/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "bangcap")
@NamedQueries({
    @NamedQuery(name = "Bangcap.findAll", query = "SELECT b FROM Bangcap b"),
    @NamedQuery(name = "Bangcap.findById", query = "SELECT b FROM Bangcap b WHERE b.id = :id"),
    @NamedQuery(name = "Bangcap.findByTrangThai", query = "SELECT b FROM Bangcap b WHERE b.trangThai = :trangThai"),
    @NamedQuery(name = "Bangcap.findByNgayCap", query = "SELECT b FROM Bangcap b WHERE b.ngayCap = :ngayCap"),
    @NamedQuery(name = "Bangcap.findByNgayHetHan", query = "SELECT b FROM Bangcap b WHERE b.ngayHetHan = :ngayHetHan"),
    @NamedQuery(name = "Bangcap.findByCoQuanCap", query = "SELECT b FROM Bangcap b WHERE b.coQuanCap = :coQuanCap")})
public class Bangcap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trang_thai")
    private Short trangThai;
    @Column(name = "ngay_cap")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCap;
    @Column(name = "ngay_het_han")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayHetHan;
    @Size(max = 255)
    @Column(name = "co_quan_cap")
    private String coQuanCap;
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bacsi bacsiId;

    public Bangcap() {
    }

    public Bangcap(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Short trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getCoQuanCap() {
        return coQuanCap;
    }

    public void setCoQuanCap(String coQuanCap) {
        this.coQuanCap = coQuanCap;
    }

    public Bacsi getBacsiId() {
        return bacsiId;
    }

    public void setBacsiId(Bacsi bacsiId) {
        this.bacsiId = bacsiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bangcap)) {
            return false;
        }
        Bangcap other = (Bangcap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Bangcap[ id=" + id + " ]";
    }
    
}
