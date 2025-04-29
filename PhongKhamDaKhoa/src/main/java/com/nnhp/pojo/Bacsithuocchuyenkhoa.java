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
import java.io.Serializable;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "bacsithuocchuyenkhoa")
@NamedQueries({
    @NamedQuery(name = "Bacsithuocchuyenkhoa.findAll", query = "SELECT b FROM Bacsithuocchuyenkhoa b"),
    @NamedQuery(name = "Bacsithuocchuyenkhoa.findById", query = "SELECT b FROM Bacsithuocchuyenkhoa b WHERE b.id = :id")})
public class Bacsithuocchuyenkhoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bacsi bacsiId;
    @JoinColumn(name = "chuyenkhoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Chuyenkhoa chuyenkhoaId;

    public Bacsithuocchuyenkhoa() {
    }

    public Bacsithuocchuyenkhoa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bacsi getBacsiId() {
        return bacsiId;
    }

    public void setBacsiId(Bacsi bacsiId) {
        this.bacsiId = bacsiId;
    }

    public Chuyenkhoa getChuyenkhoaId() {
        return chuyenkhoaId;
    }

    public void setChuyenkhoaId(Chuyenkhoa chuyenkhoaId) {
        this.chuyenkhoaId = chuyenkhoaId;
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
        if (!(object instanceof Bacsithuocchuyenkhoa)) {
            return false;
        }
        Bacsithuocchuyenkhoa other = (Bacsithuocchuyenkhoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Bacsithuocchuyenkhoa[ id=" + id + " ]";
    }
    
}
