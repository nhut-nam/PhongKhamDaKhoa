/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.dto;

import com.nnhp.pojo.Bacsidichvu;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author namnh
 */
public class BacSiDichVuDTO {
    private int id;
    private BenhVienChuyenKhoaDichVuDTO bvckdvDTO;
    private BacSiDTO bsDTO;

    public BacSiDichVuDTO(int id, BenhVienChuyenKhoaDichVuDTO bvckdvDTO, BacSiDTO bsDTO) {
        this.id = id;
        this.bvckdvDTO = bvckdvDTO;
        this.bsDTO = bsDTO;
    }
    
    public static BacSiDichVuDTO convertToDTO(Bacsidichvu bsdv) {
        return new BacSiDichVuDTO(bsdv.getId(), BenhVienChuyenKhoaDichVuDTO.convertToDTO(bsdv.getBenhVienChuyenKhoaDichVu()), BacSiDTO.convertToDTO(bsdv.getBacSi()));
    }
    
    public static List<BacSiDichVuDTO> convertToDTOList(List<Bacsidichvu> bsdvs) {
        return bsdvs.stream()
                .map(BacSiDichVuDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public BacSiDichVuDTO() {
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
     * @return the bvckdvDTO
     */
    public BenhVienChuyenKhoaDichVuDTO getBvckdvDTO() {
        return bvckdvDTO;
    }

    /**
     * @param bvckdvDTO the bvckdvDTO to set
     */
    public void setBvckdvDTO(BenhVienChuyenKhoaDichVuDTO bvckdvDTO) {
        this.bvckdvDTO = bvckdvDTO;
    }

    /**
     * @return the bsDTO
     */
    public BacSiDTO getBsDTO() {
        return bsDTO;
    }

    /**
     * @param bsDTO the bsDTO to set
     */
    public void setBsDTO(BacSiDTO bsDTO) {
        this.bsDTO = bsDTO;
    }
    
    
}
