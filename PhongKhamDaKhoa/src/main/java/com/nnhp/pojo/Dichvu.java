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
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "dichvu")
@NamedQueries({
    @NamedQuery(name = "Dichvu.findAll", query = "SELECT d FROM Dichvu d"),
    @NamedQuery(name = "Dichvu.findById", query = "SELECT d FROM Dichvu d WHERE d.id = :id"),
    @NamedQuery(name = "Dichvu.findByLoaiDichVu", query = "SELECT d FROM Dichvu d WHERE d.loaiDichVu = :loaiDichVu"),
    @NamedQuery(name = "Dichvu.findByLoaiThanhToan", query = "SELECT d FROM Dichvu d WHERE d.loaiThanhToan = :loaiThanhToan"),
    @NamedQuery(name = "Dichvu.findByTenDichVu", query = "SELECT d FROM Dichvu d WHERE d.tenDichVu = :tenDichVu")})
public class Dichvu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "loai_dich_vu")
    private Short loaiDichVu;
    @Column(name = "loai_thanh_toan")
    private Short loaiThanhToan;
    @Size(max = 255)
    @Column(name = "ten_dich_vu")
    private String tenDichVu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dichvuId")
    private Collection<Bacsidichvu> bacsidichvuCollection;
    @JoinColumn(name = "benhvienchuyenkhoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Benhvienchuyenkhoa benhvienchuyenkhoaId;

    public Dichvu() {
    }

    public Dichvu(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(Short loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public Short getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(Short loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public Collection<Bacsidichvu> getBacsidichvuCollection() {
        return bacsidichvuCollection;
    }

    public void setBacsidichvuCollection(Collection<Bacsidichvu> bacsidichvuCollection) {
        this.bacsidichvuCollection = bacsidichvuCollection;
    }

    public Benhvienchuyenkhoa getBenhvienchuyenkhoaId() {
        return benhvienchuyenkhoaId;
    }

    public void setBenhvienchuyenkhoaId(Benhvienchuyenkhoa benhvienchuyenkhoaId) {
        this.benhvienchuyenkhoaId = benhvienchuyenkhoaId;
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
        if (!(object instanceof Dichvu)) {
            return false;
        }
        Dichvu other = (Dichvu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Dichvu[ id=" + id + " ]";
    }
    
}
