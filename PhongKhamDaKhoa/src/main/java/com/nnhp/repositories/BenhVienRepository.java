/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Benhvien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface BenhVienRepository {
    List<Benhvien> getDsBenhVien(Map<String, String> params);
    Benhvien getBenhVienById(int id);

}
