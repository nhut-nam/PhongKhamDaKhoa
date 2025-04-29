/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "benhnhan")
@NamedQueries({
    @NamedQuery(name = "Benhnhan.findAll", query = "SELECT b FROM Benhnhan b"),
    @NamedQuery(name = "Benhnhan.findById", query = "SELECT b FROM Benhnhan b WHERE b.id = :id")})
public class Benhnhan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Taikhoan taikhoan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhnhanId")
    private Collection<Hoso> hosoCollection;

    public Benhnhan() {
    }

    public Benhnhan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Taikhoan getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(Taikhoan taikhoan) {
        this.taikhoan = taikhoan;
    }

    public Collection<Hoso> getHosoCollection() {
        return hosoCollection;
    }

    public void setHosoCollection(Collection<Hoso> hosoCollection) {
        this.hosoCollection = hosoCollection;
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
        if (!(object instanceof Benhnhan)) {
            return false;
        }
        Benhnhan other = (Benhnhan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Benhnhan[ id=" + id + " ]";
    }
    
}
