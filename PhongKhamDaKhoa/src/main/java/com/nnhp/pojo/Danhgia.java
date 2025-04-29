/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.Basic;
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
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "danhgia")
@NamedQueries({
    @NamedQuery(name = "Danhgia.findAll", query = "SELECT d FROM Danhgia d"),
    @NamedQuery(name = "Danhgia.findByChinhSua", query = "SELECT d FROM Danhgia d WHERE d.chinhSua = :chinhSua"),
    @NamedQuery(name = "Danhgia.findById", query = "SELECT d FROM Danhgia d WHERE d.id = :id"),
    @NamedQuery(name = "Danhgia.findBySoSao", query = "SELECT d FROM Danhgia d WHERE d.soSao = :soSao"),
    @NamedQuery(name = "Danhgia.findByNgayTao", query = "SELECT d FROM Danhgia d WHERE d.ngayTao = :ngayTao"),
    @NamedQuery(name = "Danhgia.findByBinhLuan", query = "SELECT d FROM Danhgia d WHERE d.binhLuan = :binhLuan"),
    @NamedQuery(name = "Danhgia.findByPhanHoi", query = "SELECT d FROM Danhgia d WHERE d.phanHoi = :phanHoi")})
public class Danhgia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "chinh_sua")
    private Boolean chinhSua;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "so_sao")
    private Short soSao;
    @Column(name = "ngay_tao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Size(max = 255)
    @Column(name = "binh_luan")
    private String binhLuan;
    @Size(max = 255)
    @Column(name = "phan_hoi")
    private String phanHoi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "danhgiaId")
    private Collection<Lichsubinhluan> lichsubinhluanCollection;
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bacsi bacsiId;
    @JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Taikhoan taikhoanId;

    public Danhgia() {
    }

    public Danhgia(Integer id) {
        this.id = id;
    }

    public Boolean getChinhSua() {
        return chinhSua;
    }

    public void setChinhSua(Boolean chinhSua) {
        this.chinhSua = chinhSua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getSoSao() {
        return soSao;
    }

    public void setSoSao(Short soSao) {
        this.soSao = soSao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public String getPhanHoi() {
        return phanHoi;
    }

    public void setPhanHoi(String phanHoi) {
        this.phanHoi = phanHoi;
    }

    public Collection<Lichsubinhluan> getLichsubinhluanCollection() {
        return lichsubinhluanCollection;
    }

    public void setLichsubinhluanCollection(Collection<Lichsubinhluan> lichsubinhluanCollection) {
        this.lichsubinhluanCollection = lichsubinhluanCollection;
    }

    public Bacsi getBacsiId() {
        return bacsiId;
    }

    public void setBacsiId(Bacsi bacsiId) {
        this.bacsiId = bacsiId;
    }

    public Taikhoan getTaikhoanId() {
        return taikhoanId;
    }

    public void setTaikhoanId(Taikhoan taikhoanId) {
        this.taikhoanId = taikhoanId;
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
        if (!(object instanceof Danhgia)) {
            return false;
        }
        Danhgia other = (Danhgia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Danhgia[ id=" + id + " ]";
    }
    
}
