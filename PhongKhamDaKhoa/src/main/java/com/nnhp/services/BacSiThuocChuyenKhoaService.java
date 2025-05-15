/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Bacsithuocchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import java.util.List;

/**
 *
 * @author namnh
 */
public interface BacSiThuocChuyenKhoaService {
    Bacsithuocchuyenkhoa addBacSiChuyenKhoa(Bacsi bs, Chuyenkhoa ck);
    List<Bacsithuocchuyenkhoa> addBacSiChuyenKhoaList(Bacsi bs, List<Chuyenkhoa> cks);
}
