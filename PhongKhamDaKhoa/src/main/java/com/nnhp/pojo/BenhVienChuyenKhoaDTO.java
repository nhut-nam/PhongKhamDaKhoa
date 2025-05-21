/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class BenhVienChuyenKhoaDTO {

    private int id;
    private BenhVienDTO benhVien;
    private ChuyenKhoaDTO chuyenKhoa;

    public BenhVienChuyenKhoaDTO(int id, BenhVienDTO benhVien, ChuyenKhoaDTO chuyenKhoa) {
        this.id = id;
        this.benhVien = benhVien;
        this.chuyenKhoa = chuyenKhoa;
    }

    public BenhVienChuyenKhoaDTO() {
    }

    public static BenhVienChuyenKhoaDTO convertToDTO(Benhvienchuyenkhoa bvck) {
        Benhvien bv = bvck.getBenhvienId();
        Chuyenkhoa ck = bvck.getChuyenkhoaId();
        return new BenhVienChuyenKhoaDTO(bvck.getId(), BenhVienDTO.convertToBenhVienDTO(bv), ChuyenKhoaDTO.convertToChuyenKhoaDTO(ck));
    }

    public static List<BenhVienChuyenKhoaDTO> convertToDTOList(List<Benhvienchuyenkhoa> entities) {
        return entities.stream()
                .map(BenhVienChuyenKhoaDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BenhVienDTO getBenhVien() {
        return benhVien;
    }

    public void setBenhVien(BenhVienDTO benhVien) {
        this.benhVien = benhVien;
    }

    public ChuyenKhoaDTO getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(ChuyenKhoaDTO chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

}
