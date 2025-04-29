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
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "thongtinthanhtoan")
@NamedQueries({
    @NamedQuery(name = "Thongtinthanhtoan.findAll", query = "SELECT t FROM Thongtinthanhtoan t"),
    @NamedQuery(name = "Thongtinthanhtoan.findById", query = "SELECT t FROM Thongtinthanhtoan t WHERE t.id = :id"),
    @NamedQuery(name = "Thongtinthanhtoan.findBySoTaiKhoan", query = "SELECT t FROM Thongtinthanhtoan t WHERE t.soTaiKhoan = :soTaiKhoan")})
public class Thongtinthanhtoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "so_tai_khoan")
    private String soTaiKhoan;
    @JoinColumn(name = "lichkham_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lichkham lichkhamId;

    public Thongtinthanhtoan() {
    }

    public Thongtinthanhtoan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public Lichkham getLichkhamId() {
        return lichkhamId;
    }

    public void setLichkhamId(Lichkham lichkhamId) {
        this.lichkhamId = lichkhamId;
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
        if (!(object instanceof Thongtinthanhtoan)) {
            return false;
        }
        Thongtinthanhtoan other = (Thongtinthanhtoan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Thongtinthanhtoan[ id=" + id + " ]";
    }
    
}
