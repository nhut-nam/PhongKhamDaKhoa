/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.LoaiThanhToan;
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
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "lichkham")
@NamedQueries({
    @NamedQuery(name = "Lichkham.findAll", query = "SELECT l FROM Lichkham l"),
    @NamedQuery(name = "Lichkham.findById", query = "SELECT l FROM Lichkham l WHERE l.id = :id"),
    @NamedQuery(name = "Lichkham.findBySoTienNhan", query = "SELECT l FROM Lichkham l WHERE l.soTienNhan = :soTienNhan"),
    @NamedQuery(name = "Lichkham.findByTrangThai", query = "SELECT l FROM Lichkham l WHERE l.trangThai = :trangThai"),
    @NamedQuery(name = "Lichkham.findByNgayHen", query = "SELECT l FROM Lichkham l WHERE l.ngayHen = :ngayHen"),
    @NamedQuery(name = "Lichkham.findByBuoi", query = "SELECT l FROM Lichkham l WHERE l.buoi = :buoi")})
public class Lichkham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "so_tien_nhan")
    private BigDecimal soTienNhan;
    @Column(name = "trang_thai")
    private Short trangThai;
    @Column(name = "ngay_hen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayHen;
    @Size(max = 255)
    @Column(name = "buoi")
    private String buoi;
    @Column(name = "loai_thanh_toan")
    private LoaiThanhToan loaiThanhToan;
    @Column(name = "ngay_tao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @JoinColumn(name = "benhvienchuyenkhoadichvu_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BenhVienChuyenKhoaDichVu benhvienchuyenkhoadichvuId;
    @JoinColumn(name = "bacsi_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bacsi bacsiId;
    @JoinColumn(name = "hoso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hoso hosoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lichkhamId")
    private Collection<Thongtinthanhtoan> thongtinthanhtoanCollection;

    public Lichkham() {
    }

    public Lichkham(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSoTienNhan() {
        return soTienNhan;
    }

    public void setSoTienNhan(BigDecimal soTienNhan) {
        this.soTienNhan = soTienNhan;
    }

    public Short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Short trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    public LoaiThanhToan getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(LoaiThanhToan loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }
    
    

    public BenhVienChuyenKhoaDichVu getBenhvienchuyenkhoadichvuId() {
        return benhvienchuyenkhoadichvuId;
    }

    public void setBenhvienchuyenkhoadichvuId(BenhVienChuyenKhoaDichVu benhvienchuyenkhoadichvuId) {
        this.benhvienchuyenkhoadichvuId = benhvienchuyenkhoadichvuId;
    }

    public Bacsi getBacsiId() {
        return bacsiId;
    }

    public void setBacsiId(Bacsi bacsiId) {
        this.bacsiId = bacsiId;
    }

    public Hoso getHosoId() {
        return hosoId;
    }

    public void setHosoId(Hoso hosoId) {
        this.hosoId = hosoId;
    }

    public Collection<Thongtinthanhtoan> getThongtinthanhtoanCollection() {
        return thongtinthanhtoanCollection;
    }

    public void setThongtinthanhtoanCollection(Collection<Thongtinthanhtoan> thongtinthanhtoanCollection) {
        this.thongtinthanhtoanCollection = thongtinthanhtoanCollection;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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
        if (!(object instanceof Lichkham)) {
            return false;
        }
        Lichkham other = (Lichkham) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnhp.pojo.Lichkham[ id=" + id + " ]";
    }
    
}
