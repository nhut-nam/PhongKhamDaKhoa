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
@Table(name = "hoso")
@NamedQueries({
    @NamedQuery(name = "Hoso.findAll", query = "SELECT h FROM Hoso h"),
    @NamedQuery(name = "Hoso.findById", query = "SELECT h FROM Hoso h WHERE h.id = :id"),
    @NamedQuery(name = "Hoso.findByNgayTao", query = "SELECT h FROM Hoso h WHERE h.ngayTao = :ngayTao"),
    @NamedQuery(name = "Hoso.findByKetQuaXetNghiem", query = "SELECT h FROM Hoso h WHERE h.ketQuaXetNghiem = :ketQuaXetNghiem"),
    @NamedQuery(name = "Hoso.findByTieuSu", query = "SELECT h FROM Hoso h WHERE h.tieuSu = :tieuSu")})
public class Hoso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ngay_tao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Size(max = 255)
    @Column(name = "ket_qua_xet_nghiem")
    private String ketQuaXetNghiem;
    @Size(max = 255)
    @Column(name = "tieu_su")
    private String tieuSu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hosoId")
    private Collection<Lichsukhambenh> lichsukhambenhCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hosoId")
    private Collection<Lichkham> lichkhamCollection;
    @JoinColumn(name = "benhnhan_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Benhnhan benhnhanId;

    public Hoso() {
    }

    public Hoso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getKetQuaXetNghiem() {
        return ketQuaXetNghiem;
    }

    public void setKetQuaXetNghiem(String ketQuaXetNghiem) {
        this.ketQuaXetNghiem = ketQuaXetNghiem;
    }

    public String getTieuSu() {
        return tieuSu;
    }

    public void setTieuSu(String tieuSu) {
        this.tieuSu = tieuSu;
    }

    public Collection<Lichsukhambenh> getLichsukhambenhCollection() {
        return lichsukhambenhCollection;
    }

    public void setLichsukhambenhCollection(Collection<Lichsukhambenh> lichsukhambenhCollection) {
        this.lichsukhambenhCollection = lichsukhambenhCollection;
    }

    public Collection<Lichkham> getLichkhamCollection() {
        return lichkhamCollection;
    }

    public void setLichkhamCollection(Collection<Lichkham> lichkhamCollection) {
        this.lichkhamCollection = lichkhamCollection;
    }

    public Benhnhan getBenhnhanId() {
        return benhnhanId;
    }

    public void setBenhnhanId(Benhnhan benhnhanId) {
        this.benhnhanId = benhnhanId;
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
        if (!(object instanceof Hoso)) {
            return false;
        }
        Hoso other = (Hoso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Hoso[ id=" + id + " ]";
    }
    
}
