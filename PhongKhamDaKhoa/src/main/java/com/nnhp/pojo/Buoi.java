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
@Table(name = "buoi")
@NamedQueries({
    @NamedQuery(name = "Buoi.findAll", query = "SELECT b FROM Buoi b"),
    @NamedQuery(name = "Buoi.findById", query = "SELECT b FROM Buoi b WHERE b.id = :id"),
    @NamedQuery(name = "Buoi.findByThoiGian", query = "SELECT b FROM Buoi b WHERE b.thoiGian = :thoiGian")})
public class Buoi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "thoi_gian")
    private String thoiGian;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buoiId")
    private Collection<Ngaylamviec> ngaylamviecCollection;

    public Buoi() {
    }

    public Buoi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Collection<Ngaylamviec> getNgaylamviecCollection() {
        return ngaylamviecCollection;
    }

    public void setNgaylamviecCollection(Collection<Ngaylamviec> ngaylamviecCollection) {
        this.ngaylamviecCollection = ngaylamviecCollection;
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
        if (!(object instanceof Buoi)) {
            return false;
        }
        Buoi other = (Buoi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Buoi[ id=" + id + " ]";
    }
    
}
