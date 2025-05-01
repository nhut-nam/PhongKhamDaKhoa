/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.Role;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Table(name = "taikhoan")
@NamedQueries({
    @NamedQuery(name = "Taikhoan.findAll", query = "SELECT t FROM Taikhoan t"),
    @NamedQuery(name = "Taikhoan.findById", query = "SELECT t FROM Taikhoan t WHERE t.id = :id"),
    @NamedQuery(name = "Taikhoan.findByRole", query = "SELECT t FROM Taikhoan t WHERE t.role = :role"),
    @NamedQuery(name = "Taikhoan.findByNgaySinh", query = "SELECT t FROM Taikhoan t WHERE t.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "Taikhoan.findByAvatar", query = "SELECT t FROM Taikhoan t WHERE t.avatar = :avatar"),
    @NamedQuery(name = "Taikhoan.findByDiaChi", query = "SELECT t FROM Taikhoan t WHERE t.diaChi = :diaChi"),
    @NamedQuery(name = "Taikhoan.findByEmail", query = "SELECT t FROM Taikhoan t WHERE t.email = :email"),
    @NamedQuery(name = "Taikhoan.findByGhiChu", query = "SELECT t FROM Taikhoan t WHERE t.ghiChu = :ghiChu"),
    @NamedQuery(name = "Taikhoan.findByHoNguoiDung", query = "SELECT t FROM Taikhoan t WHERE t.hoNguoiDung = :hoNguoiDung"),
    @NamedQuery(name = "Taikhoan.findByMatKhau", query = "SELECT t FROM Taikhoan t WHERE t.matKhau = :matKhau"),
    @NamedQuery(name = "Taikhoan.findBySoDienThoai", query = "SELECT t FROM Taikhoan t WHERE t.soDienThoai = :soDienThoai"),
    @NamedQuery(name = "Taikhoan.findByTenNguoiDung", query = "SELECT t FROM Taikhoan t WHERE t.tenNguoiDung = :tenNguoiDung")})
@Inheritance(strategy = InheritanceType.JOINED)
public class Taikhoan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    protected Integer id;
    @Size(max = 50)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngay_sinh")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaySinh;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 255)
    @Column(name = "dia_chi")
    private String diaChi;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "ghi_chu")
    private String ghiChu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ho_nguoi_dung")
    private String hoNguoiDung;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mat_khau")
    private String matKhau;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ten_nguoi_dung")
    private String tenNguoiDung;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taikhoanId")
    private Collection<Danhgia> danhgiaCollection;

    public Taikhoan() {
    }

    public Taikhoan(Integer id) {
        this.id = id;
    }

    public Taikhoan(Integer id, Date ngaySinh, String email, String hoNguoiDung, String matKhau, String soDienThoai, String tenNguoiDung) {
        this.id = id;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.hoNguoiDung = hoNguoiDung;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.tenNguoiDung = tenNguoiDung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }
    
    public Role getRoleEnum() {
        return Role.valueOf(this.role);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getHoNguoiDung() {
        return hoNguoiDung;
    }

    public void setHoNguoiDung(String hoNguoiDung) {
        this.hoNguoiDung = hoNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public Collection<Danhgia> getDanhgiaCollection() {
        return danhgiaCollection;
    }

    public void setDanhgiaCollection(Collection<Danhgia> danhgiaCollection) {
        this.danhgiaCollection = danhgiaCollection;
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
        if (!(object instanceof Taikhoan)) {
            return false;
        }
        Taikhoan other = (Taikhoan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Taikhoan[ id=" + id + " ]";
    }
    
}
