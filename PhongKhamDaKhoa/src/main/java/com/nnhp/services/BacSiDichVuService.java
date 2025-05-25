/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Bacsidichvu;
import java.util.List;

/**
 *
 * @author namnh
 */
public interface BacSiDichVuService {
    List<Bacsidichvu> getBacSiDichVuByBenhVienChuyenKhoaDichVu(int id);
}
