/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

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
@Table(name = "bacsidichvu")
@NamedQueries({
    @NamedQuery(name = "Bacsidichvu.findAll", 
                query = "SELECT b FROM Bacsidichvu b"),

    @NamedQuery(name = "Bacsidichvu.findByBacSi", 
                query = "SELECT b FROM Bacsidichvu b WHERE b.bacsiId.id = :bacSiId"),
})
public class Bacsidichvu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bacsi_Id", referencedColumnName = "id")
    private Bacsi bacsiId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "benhvienchuyenkhoadichvu_Id", referencedColumnName = "id")
    private BenhVienChuyenKhoaDichVu benhvienchuyenkhoadichvuId;

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bacsi getBacSi() {
        return bacsiId;
    }

    public void setBacSi(Bacsi bacsiId) {
        this.bacsiId = bacsiId;
    }

    public BenhVienChuyenKhoaDichVu getBenhVienChuyenKhoaDichVu() {
        return benhvienchuyenkhoadichvuId;
    }

    public void setBenhVienChuyenKhoaDichVu(BenhVienChuyenKhoaDichVu benhvienchuyenkhoadichvuId) {
        this.benhvienchuyenkhoadichvuId = benhvienchuyenkhoadichvuId;
    }
}
