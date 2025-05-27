/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.dto.BacSiDanhGiaDTO;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Chuyenkhoa;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author namnh
 */
public interface BacSiService {
    List<Bacsi> getDsBacSi(Map<String, String> params);
    Bacsi getBacSiById(int id);
    Bacsi addOrUpdateBacSi(Bacsi b);
    void deleteBacSi(int id);
    List<Chuyenkhoa> getChuyenKhoaByBacSiId(int id);
    BacSiDanhGiaDTO getBacSiWithDanhGiaById(int id);
    Set<Bacsi> getListBacSiByUserId(int id);
}
