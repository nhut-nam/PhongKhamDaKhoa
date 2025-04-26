/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author namnh
 */
@Entity
public class HoSo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tieu_su")
    private String tieuSu;
    @Column(name = "ket_qua_xet_nghiem")
    private String ketQuaXetNghiem;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @ManyToOne(optional = false)
    @JoinColumn(name = "benhnhan_id", referencedColumnName = "id", nullable = false)
    private BenhNhan benhNhan_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hoSo_id")
    private List<LichSuKhamBenh> lichSuKhamBenhList;

    public HoSo(int id, String tieuSu, String ketQuaXetNghiem, Date ngayTao) {
        this.id = id;
        this.tieuSu = tieuSu;
        this.ketQuaXetNghiem = ketQuaXetNghiem;
        this.ngayTao = ngayTao;
    }

    public HoSo() {
    }

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
     * @return the tieuSu
     */
    public String getTieuSu() {
        return tieuSu;
    }

    /**
     * @param tieuSu the tieuSu to set
     */
    public void setTieuSu(String tieuSu) {
        this.tieuSu = tieuSu;
    }

    /**
     * @return the ketQuaXetNghiem
     */
    public String getKetQuaXetNghiem() {
        return ketQuaXetNghiem;
    }

    /**
     * @param ketQuaXetNghiem the ketQuaXetNghiem to set
     */
    public void setKetQuaXetNghiem(String ketQuaXetNghiem) {
        this.ketQuaXetNghiem = ketQuaXetNghiem;
    }

    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    
}
