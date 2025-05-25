/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "hoso")
@NamedQueries({
    @NamedQuery(name = "Hoso.findAll", query = "SELECT h FROM Hoso h"),
    @NamedQuery(name = "Hoso.findById", query = "SELECT h FROM Hoso h WHERE h.id = :id"),
    @NamedQuery(name = "Hoso.findByNgayTao", query = "SELECT h FROM Hoso h WHERE h.ngayTao = :ngayTao")})
public class Hoso implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ngay_tao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngay_sinh")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaySinh;
    @Size(max = 255)
    @Column(name = "dia_chi")
    private String diaChi;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Column(name = "gioi_tinh")
    private boolean gioiTinh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ho_ten")
    private String hoTen;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hosoId")
    private Collection<Lichsukhambenh> lichsukhambenhCollection;
    @JsonIgnore
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

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoso)) {
            return false;
        }
        Hoso other = (Hoso) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Hoso[ id=" + getId() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
}
