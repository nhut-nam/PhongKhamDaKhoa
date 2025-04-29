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
@Table(name = "lichsubinhluan")
@NamedQueries({
    @NamedQuery(name = "Lichsubinhluan.findAll", query = "SELECT l FROM Lichsubinhluan l"),
    @NamedQuery(name = "Lichsubinhluan.findById", query = "SELECT l FROM Lichsubinhluan l WHERE l.id = :id"),
    @NamedQuery(name = "Lichsubinhluan.findByNgaySua", query = "SELECT l FROM Lichsubinhluan l WHERE l.ngaySua = :ngaySua"),
    @NamedQuery(name = "Lichsubinhluan.findByBinhLuan", query = "SELECT l FROM Lichsubinhluan l WHERE l.binhLuan = :binhLuan"),
    @NamedQuery(name = "Lichsubinhluan.findByBinhLuanCu", query = "SELECT l FROM Lichsubinhluan l WHERE l.binhLuanCu = :binhLuanCu")})
public class Lichsubinhluan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ngay_sua")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaySua;
    @Size(max = 255)
    @Column(name = "binh_luan")
    private String binhLuan;
    @Size(max = 255)
    @Column(name = "binh_luan_cu")
    private String binhLuanCu;
    @JoinColumn(name = "danhgia_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Danhgia danhgiaId;

    public Lichsubinhluan() {
    }

    public Lichsubinhluan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public String getBinhLuanCu() {
        return binhLuanCu;
    }

    public void setBinhLuanCu(String binhLuanCu) {
        this.binhLuanCu = binhLuanCu;
    }

    public Danhgia getDanhgiaId() {
        return danhgiaId;
    }

    public void setDanhgiaId(Danhgia danhgiaId) {
        this.danhgiaId = danhgiaId;
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
        if (!(object instanceof Lichsubinhluan)) {
            return false;
        }
        Lichsubinhluan other = (Lichsubinhluan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Lichsubinhluan[ id=" + id + " ]";
    }
    
}
