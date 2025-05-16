/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.TrangThaiTaiKhoan;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "bacsi")
@NamedQueries({
    @NamedQuery(name = "Bacsi.findAll", query = "SELECT b FROM Bacsi b"),
    @NamedQuery(name = "Bacsi.findById", query = "SELECT b FROM Bacsi b WHERE b.id = :id"),
    @NamedQuery(name = "Bacsi.findByTrangThai", query = "SELECT b FROM Bacsi b WHERE b.trangThai = :trangThai"),
    @NamedQuery(name = "Bacsi.findByNgayLamViec", query = "SELECT b FROM Bacsi b WHERE b.ngayLamViec = :ngayLamViec"),
    @NamedQuery(name = "Bacsi.findByNgayNghiViec", query = "SELECT b FROM Bacsi b WHERE b.ngayNghiViec = :ngayNghiViec"),
    @NamedQuery(name = "Bacsi.findByChuyenTri", query = "SELECT b FROM Bacsi b WHERE b.chuyenTri = :chuyenTri")})
public class Bacsi extends Taikhoan implements Serializable {

    @Size(max = 255)
    @Column(name = "chuyen_tri")
    private String chuyenTri;
    @Column(name = "ngay_lam_viec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayLamViec;
    @Column(name = "ngay_nghi_viec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayNghiViec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacsiId")
    private Collection<Lichsukhambenh> lichsukhambenhCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacsiId")
    private Collection<Bacsidichvu> bacsidichvuCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacsiId")
    private Collection<Lichkham> lichkhamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacsiId")
    private Collection<Bacsithuocchuyenkhoa> bacsithuocchuyenkhoaCollection;
    @JoinColumn(name = "benhvien_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Benhvien benhvienId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacSiid")
    private Collection<Ngaylamviec> ngaylamviecCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacsiId")
    private Collection<Danhgia> danhgiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bacsiId")
    private Collection<Bangcap> bangcapCollection;

    public Bacsi() {
    }

    public Bacsi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgayLamViec() {
        return ngayLamViec;
    }

    public Collection<Lichkham> getLichkhamCollection() {
        return lichkhamCollection;
    }

    public void setLichkhamCollection(Collection<Lichkham> lichkhamCollection) {
        this.lichkhamCollection = lichkhamCollection;
    }

    public void setNgayLamViec(Date ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public Date getNgayNghiViec() {
        return ngayNghiViec;
    }

    public void setNgayNghiViec(Date ngayNghiViec) {
        this.ngayNghiViec = ngayNghiViec;
    }

    public String getChuyenTri() {
        return chuyenTri;
    }

    public void setChuyenTri(String chuyenTri) {
        this.chuyenTri = chuyenTri;
    }

    public Collection<Lichsukhambenh> getLichsukhambenhCollection() {
        return lichsukhambenhCollection;
    }

    public void setLichsukhambenhCollection(Collection<Lichsukhambenh> lichsukhambenhCollection) {
        this.lichsukhambenhCollection = lichsukhambenhCollection;
    }

    public Collection<Bacsidichvu> getBacsidichvuCollection() {
        return bacsidichvuCollection;
    }

    public void setBacsidichvuCollection(Collection<Bacsidichvu> bacsidichvuCollection) {
        this.bacsidichvuCollection = bacsidichvuCollection;
    }

    public Collection<Bacsithuocchuyenkhoa> getBacsithuocchuyenkhoaCollection() {
        return bacsithuocchuyenkhoaCollection;
    }

    public void setBacsithuocchuyenkhoaCollection(Collection<Bacsithuocchuyenkhoa> bacsithuocchuyenkhoaCollection) {
        this.bacsithuocchuyenkhoaCollection = bacsithuocchuyenkhoaCollection;
    }

    public Benhvien getBenhvienId() {
        return benhvienId;
    }

    public void setBenhvienId(Benhvien benhvienId) {
        this.benhvienId = benhvienId;
    }

    public Collection<Ngaylamviec> getNgaylamviecCollection() {
        return ngaylamviecCollection;
    }

    public void setNgaylamviecCollection(Collection<Ngaylamviec> ngaylamviecCollection) {
        this.ngaylamviecCollection = ngaylamviecCollection;
    }

    public Collection<Danhgia> getDanhgiaCollection() {
        return danhgiaCollection;
    }

    public void setDanhgiaCollection(Collection<Danhgia> danhgiaCollection) {
        this.danhgiaCollection = danhgiaCollection;
    }

    public Collection<Bangcap> getBangcapCollection() {
        return bangcapCollection;
    }

    public void setBangcapCollection(Collection<Bangcap> bangcapCollection) {
        this.bangcapCollection = bangcapCollection;
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
        if (!(object instanceof Bacsi)) {
            return false;
        }
        Bacsi other = (Bacsi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Bacsi[ id=" + id + " ]";
    }
    
}
