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
@Table(name = "ngaylamviec")
@NamedQueries({
    @NamedQuery(name = "Ngaylamviec.findAll", query = "SELECT n FROM Ngaylamviec n"),
    @NamedQuery(name = "Ngaylamviec.findById", query = "SELECT n FROM Ngaylamviec n WHERE n.id = :id"),
    @NamedQuery(name = "Ngaylamviec.findByTrangThai", query = "SELECT n FROM Ngaylamviec n WHERE n.trangThai = :trangThai"),
    @NamedQuery(name = "Ngaylamviec.findByNgay", query = "SELECT n FROM Ngaylamviec n WHERE n.ngay = :ngay"),
    @NamedQuery(name = "Ngaylamviec.findByGhiChu", query = "SELECT n FROM Ngaylamviec n WHERE n.ghiChu = :ghiChu")})
public class Ngaylamviec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trang_thai")
    private Boolean trangThai;
    @Column(name = "ngay")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngay;
    @Size(max = 255)
    @Column(name = "ghi_chu")
    private String ghiChu;
    @JoinColumn(name = "bacSi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bacsi bacSiid;
    @JoinColumn(name = "buoi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Buoi buoiId;

    public Ngaylamviec() {
    }

    public Ngaylamviec(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Bacsi getBacSiid() {
        return bacSiid;
    }

    public void setBacSiid(Bacsi bacSiid) {
        this.bacSiid = bacSiid;
    }

    public Buoi getBuoiId() {
        return buoiId;
    }

    public void setBuoiId(Buoi buoiId) {
        this.buoiId = buoiId;
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
        if (!(object instanceof Ngaylamviec)) {
            return false;
        }
        Ngaylamviec other = (Ngaylamviec) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Ngaylamviec[ id=" + id + " ]";
    }
    
}
