/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Chuyenkhoa;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface ChuyenKhoaRepository {
    List<Chuyenkhoa> getDsChuyenKhoa(Map<String, String> params);
    Chuyenkhoa getChuyenKhoaById(int id);
    Chuyenkhoa addOrUpdateChuyenKhoa(Chuyenkhoa c);
    void deleteChuyenKhoa(int id);
}
