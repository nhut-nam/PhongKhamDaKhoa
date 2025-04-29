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
@Table(name = "bacsidichvu")
@NamedQueries({
    @NamedQuery(name = "Bacsidichvu.findAll", query = "SELECT b FROM Bacsidichvu b"),
    @NamedQuery(name = "Bacsidichvu.findByGiaTien", query = "SELECT b FROM Bacsidichvu b WHERE b.giaTien = :giaTien"),
    @NamedQuery(name = "Bacsidichvu.findById", query = "SELECT b FROM Bacsidichvu b WHERE b.id = :id")})
public class Bacsidichvu implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gia_tien")
    private Double giaTien;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacsidichvuId")
    private Collection<Lichkham> lichkhamCollection;
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bacsi bacsiId;
    @JoinColumn(name = "dichvu_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Dichvu dichvuId;

    public Bacsidichvu() {
    }

    public Bacsidichvu(Integer id) {
        this.id = id;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<Lichkham> getLichkhamCollection() {
        return lichkhamCollection;
    }

    public void setLichkhamCollection(Collection<Lichkham> lichkhamCollection) {
        this.lichkhamCollection = lichkhamCollection;
    }

    public Bacsi getBacsiId() {
        return bacsiId;
    }

    public void setBacsiId(Bacsi bacsiId) {
        this.bacsiId = bacsiId;
    }

    public Dichvu getDichvuId() {
        return dichvuId;
    }

    public void setDichvuId(Dichvu dichvuId) {
        this.dichvuId = dichvuId;
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
        if (!(object instanceof Bacsidichvu)) {
            return false;
        }
        Bacsidichvu other = (Bacsidichvu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Bacsidichvu[ id=" + id + " ]";
    }
    
}
