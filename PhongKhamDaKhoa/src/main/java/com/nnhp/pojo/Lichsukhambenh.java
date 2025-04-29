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
@Table(name = "lichsukhambenh")
@NamedQueries({
    @NamedQuery(name = "Lichsukhambenh.findAll", query = "SELECT l FROM Lichsukhambenh l"),
    @NamedQuery(name = "Lichsukhambenh.findById", query = "SELECT l FROM Lichsukhambenh l WHERE l.id = :id"),
    @NamedQuery(name = "Lichsukhambenh.findByNgayKham", query = "SELECT l FROM Lichsukhambenh l WHERE l.ngayKham = :ngayKham"),
    @NamedQuery(name = "Lichsukhambenh.findByChanDoan", query = "SELECT l FROM Lichsukhambenh l WHERE l.chanDoan = :chanDoan"),
    @NamedQuery(name = "Lichsukhambenh.findByDonThuoc", query = "SELECT l FROM Lichsukhambenh l WHERE l.donThuoc = :donThuoc")})
public class Lichsukhambenh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ngay_kham")
    @Temporal(TemporalType.DATE)
    private Date ngayKham;
    @Size(max = 255)
    @Column(name = "chan_doan")
    private String chanDoan;
    @Size(max = 255)
    @Column(name = "don_thuoc")
    private String donThuoc;
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bacsi bacsiId;
    @JoinColumn(name = "hoso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hoso hosoId;

    public Lichsukhambenh() {
    }

    public Lichsukhambenh(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getChanDoan() {
        return chanDoan;
    }

    public void setChanDoan(String chanDoan) {
        this.chanDoan = chanDoan;
    }

    public String getDonThuoc() {
        return donThuoc;
    }

    public void setDonThuoc(String donThuoc) {
        this.donThuoc = donThuoc;
    }

    public Bacsi getBacsiId() {
        return bacsiId;
    }

    public void setBacsiId(Bacsi bacsiId) {
        this.bacsiId = bacsiId;
    }

    public Hoso getHosoId() {
        return hosoId;
    }

    public void setHosoId(Hoso hosoId) {
        this.hosoId = hosoId;
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
        if (!(object instanceof Lichsukhambenh)) {
            return false;
        }
        Lichsukhambenh other = (Lichsukhambenh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Lichsukhambenh[ id=" + id + " ]";
    }
    
}
