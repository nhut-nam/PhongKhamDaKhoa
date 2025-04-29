/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import com.nnhp.enums.TrangThaiLichKham;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "lichkham")
public class LichKham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "trang_thai")
    private TrangThaiLichKham trangThai;
    @Column(name = "ngay_hen")
    private Date ngayHen; 
    @Column(name = "buoi")
    private String buoi;
    @Column(name = "so_tien_nhan")
    private BigDecimal soTienNhan;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsidichvu_id", referencedColumnName = "id")
    private BacSiDichVu bacSiDichVu_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "hoso_id", referencedColumnName = "id")
    private HoSo hoSo_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lichKham_id")
    private List<ThongTinThanhToan> thongTinThanhToanList;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the trangThai
     */
    public TrangThaiLichKham getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(TrangThaiLichKham trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the ngayHen
     */
    public Date getNgayHen() {
        return ngayHen;
    }

    /**
     * @param ngayHen the ngayHen to set
     */
    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    /**
     * @return the buoi
     */
    public String getBuoi() {
        return buoi;
    }

    /**
     * @param buoi the buoi to set
     */
    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    /**
     * @return the bacSiDichVu_id
     */
    public BacSiDichVu getBacSiDichVu_id() {
        return bacSiDichVu_id;
    }

    /**
     * @param bacSiDichVu_id the bacSiDichVu_id to set
     */
    public void setBacSiDichVu_id(BacSiDichVu bacSiDichVu_id) {
        this.bacSiDichVu_id = bacSiDichVu_id;
    }
}
