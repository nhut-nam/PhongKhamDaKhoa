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
public class BacSiThuocChuyenKhoaDTO {

    private int id;
    private int bsId;
    private int ckId;

    public BacSiThuocChuyenKhoaDTO(int id, int bsId, int ckId) {
        this.id = id;
        this.bsId = bsId;
        this.ckId = ckId;
    }

    public static BacSiThuocChuyenKhoaDTO convertToDTO(Bacsithuocchuyenkhoa bsck) {
        return new BacSiThuocChuyenKhoaDTO(bsck.getId(), bsck.getBacsiId().getId(), bsck.getChuyenkhoaId().getId());
    }

    public static List<BacSiThuocChuyenKhoaDTO> convertToDTOList(List<Bacsithuocchuyenkhoa> entities) {
        return entities.stream()
                .map(BacSiThuocChuyenKhoaDTO::convertToDTO)
                .collect(Collectors.toList());
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
     * @return the bsId
     */
    public int getBsId() {
        return bsId;
    }

    /**
     * @param bsId the bsId to set
     */
    public void setBsId(int bsId) {
        this.bsId = bsId;
    }

    /**
     * @return the ckId
     */
    public int getCkId() {
        return ckId;
    }

    /**
     * @param ckId the ckId to set
     */
    public void setCkId(int ckId) {
        this.ckId = ckId;
    }

}
