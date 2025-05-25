/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.pojo.Lichkham;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class LichKhamDTO {
    private int id;
    private BacSiSimpleDTO bsDTO;
    private BenhVienChuyenKhoaDichVuFullDTO bvckdvDTO;
    private Date ngayHen;
    private Date ngayTao;
    private String buoi;
    private HoSoDTO hsDTO;
    private BigDecimal soTienNhan;
    private TrangThaiLichKham trangThai;

    public LichKhamDTO(int id, BacSiSimpleDTO bsDTO, BenhVienChuyenKhoaDichVuFullDTO bvckdvDTO, Date ngayHen, Date ngayTao, String buoi, HoSoDTO hsDTO, BigDecimal soTienNhan, TrangThaiLichKham trangThai) {
        this.id = id;
        this.bsDTO = bsDTO;
        this.bvckdvDTO = bvckdvDTO;
        this.ngayHen = ngayHen;
        this.ngayTao = ngayTao;
        this.buoi = buoi;
        this.hsDTO = hsDTO;
        this.soTienNhan = soTienNhan;
        this.trangThai = trangThai;
    }


    public LichKhamDTO() {
    }
    
    public static LichKhamDTO convertToDTO(Lichkham lk) {
        return new LichKhamDTO(lk.getId(), BacSiSimpleDTO.convertToDTO(lk.getBacsiId()), 
                BenhVienChuyenKhoaDichVuFullDTO.convertToDTO(lk.getBenhvienchuyenkhoadichvuId()), lk.getNgayHen(), lk.getNgayTao(), lk.getBuoi(), 
                HoSoDTO.convertToDTO(lk.getHosoId()), lk.getSoTienNhan(), lk.getTrangThai()); 
    }
    
    public static List<LichKhamDTO> convertToDTOList(List<Lichkham> lks) {
        return lks.stream().map(LichKhamDTO::convertToDTO).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BacSiSimpleDTO getBsDTO() {
        return bsDTO;
    }

    public void setBsDTO(BacSiSimpleDTO bsDTO) {
        this.bsDTO = bsDTO;
    }

    public BenhVienChuyenKhoaDichVuFullDTO getBvckdvDTO() {
        return bvckdvDTO;
    }

    public void setBvckdvDTO(BenhVienChuyenKhoaDichVuFullDTO bvckdvDTO) {
        this.bvckdvDTO = bvckdvDTO;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    public HoSoDTO getHsDTO() {
        return hsDTO;
    }

    public void setHsDTO(HoSoDTO hsDTO) {
        this.hsDTO = hsDTO;
    }

    public BigDecimal getSoTienNhan() {
        return soTienNhan;
    }

    public void setSoTienNhan(BigDecimal soTienNhan) {
        this.soTienNhan = soTienNhan;
    }

    public TrangThaiLichKham getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiLichKham trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    
}
