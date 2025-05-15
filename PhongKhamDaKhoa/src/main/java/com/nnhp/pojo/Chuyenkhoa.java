/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "chuyenkhoa")
@NamedQueries({
    @NamedQuery(name = "Chuyenkhoa.findAll", query = "SELECT c FROM Chuyenkhoa c"),
    @NamedQuery(name = "Chuyenkhoa.findById", query = "SELECT c FROM Chuyenkhoa c WHERE c.id = :id"),
    @NamedQuery(name = "Chuyenkhoa.findByTenChuyenKhoa", query = "SELECT c FROM Chuyenkhoa c WHERE c.tenChuyenKhoa = :tenChuyenKhoa")})
public class Chuyenkhoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "ten_chuyen_khoa")
    private String tenChuyenKhoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chuyenkhoaId")
    private Collection<Bacsithuocchuyenkhoa> bacsithuocchuyenkhoaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chuyenkhoaId")
    private Collection<Benhvienchuyenkhoa> benhvienchuyenkhoaCollection;

    public Chuyenkhoa() {
    }

    public Chuyenkhoa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenChuyenKhoa() {
        return tenChuyenKhoa;
    }

    public void setTenChuyenKhoa(String tenChuyenKhoa) {
        this.tenChuyenKhoa = tenChuyenKhoa;
    }

    public Collection<Bacsithuocchuyenkhoa> getBacsithuocchuyenkhoaCollection() {
        return bacsithuocchuyenkhoaCollection;
    }

    public void setBacsithuocchuyenkhoaCollection(Collection<Bacsithuocchuyenkhoa> bacsithuocchuyenkhoaCollection) {
        this.bacsithuocchuyenkhoaCollection = bacsithuocchuyenkhoaCollection;
    }

    public Collection<Benhvienchuyenkhoa> getBenhvienchuyenkhoaCollection() {
        return benhvienchuyenkhoaCollection;
    }

    public void setBenhvienchuyenkhoaCollection(Collection<Benhvienchuyenkhoa> benhvienchuyenkhoaCollection) {
        this.benhvienchuyenkhoaCollection = benhvienchuyenkhoaCollection;
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
        if (!(object instanceof Chuyenkhoa)) {
            return false;
        }
        Chuyenkhoa other = (Chuyenkhoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Chuyenkhoa[ id=" + id + " ]";
    }
    
}
