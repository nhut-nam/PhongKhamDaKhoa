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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "benhvien")
@NamedQueries({
    @NamedQuery(name = "Benhvien.findAll", query = "SELECT b FROM Benhvien b"),
    @NamedQuery(name = "Benhvien.findById", query = "SELECT b FROM Benhvien b WHERE b.id = :id"),
    @NamedQuery(name = "Benhvien.findByDiaChi", query = "SELECT b FROM Benhvien b WHERE b.diaChi = :diaChi"),
    @NamedQuery(name = "Benhvien.findByTenBenhVien", query = "SELECT b FROM Benhvien b WHERE b.tenBenhVien = :tenBenhVien")})
public class Benhvien implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "dia_chi")
    private String diaChi;
    @Size(max = 255)
    @Column(name = "ten_benh_vien")
    private String tenBenhVien;
    @Column(name = "avatar")
    private String avatar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhvienId")
    private Collection<Bacsi> bacsiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhvienId")
    private Collection<Benhvienchuyenkhoa> benhvienchuyenkhoaCollection;
    @Transient
    private MultipartFile file;

    
    public Benhvien() {
    }

    public Benhvien(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenBenhVien() {
        return tenBenhVien;
    }

    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public Collection<Bacsi> getBacsiCollection() {
        return bacsiCollection;
    }

    public void setBacsiCollection(Collection<Bacsi> bacsiCollection) {
        this.bacsiCollection = bacsiCollection;
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
        if (!(object instanceof Benhvien)) {
            return false;
        }
        Benhvien other = (Benhvien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Benhvien[ id=" + id + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
