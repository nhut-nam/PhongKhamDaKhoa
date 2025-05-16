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
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "benhvienchuyenkhoa")
@NamedQueries({
    @NamedQuery(name = "Benhvienchuyenkhoa.findAll", query = "SELECT b FROM Benhvienchuyenkhoa b"),
    @NamedQuery(name = "Benhvienchuyenkhoa.findById", query = "SELECT b FROM Benhvienchuyenkhoa b WHERE b.id = :id")})
public class Benhvienchuyenkhoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "benhvien_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Benhvien benhvienId;
    @JoinColumn(name = "chuyenkhoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Chuyenkhoa chuyenkhoaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhvienchuyenkhoaId")
    private Collection<BenhVienChuyenKhoaDichVu> benhvienchuyenkhoadichvuCollection;

    public Benhvienchuyenkhoa() {
    }

    public Benhvienchuyenkhoa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Benhvien getBenhvienId() {
        return benhvienId;
    }

    public void setBenhvienId(Benhvien benhvienId) {
        this.benhvienId = benhvienId;
    }

    public Chuyenkhoa getChuyenkhoaId() {
        return chuyenkhoaId;
    }

    public void setChuyenkhoaId(Chuyenkhoa chuyenkhoaId) {
        this.chuyenkhoaId = chuyenkhoaId;
    }

    public Collection<BenhVienChuyenKhoaDichVu> getDichvuCollection() {
        return benhvienchuyenkhoadichvuCollection;
    }

    public void setDichvuCollection(Collection<BenhVienChuyenKhoaDichVu> benhvienchuyenkhoadichvuCollection) {
        this.benhvienchuyenkhoadichvuCollection = benhvienchuyenkhoadichvuCollection;
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
        if (!(object instanceof Benhvienchuyenkhoa)) {
            return false;
        }
        Benhvienchuyenkhoa other = (Benhvienchuyenkhoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Benhvienchuyenkhoa[ id=" + id + " ]";
    }
    
}
