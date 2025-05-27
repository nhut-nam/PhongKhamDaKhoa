/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Tintuc;
import java.util.List;
import java.util.Map;

/**
 *
 * @author namnh
 */
public interface TinTucRepository {
    Tintuc getTinTucById(int id);
    List<Tintuc> getTinTucList(Map<String, String> params);
    List<Tintuc> findTop10TinTucChuaGanUser(int userId);
    List<Tintuc> getTop10TintucMoiNhat();
    List<Tintuc> getTinTucByUserId(int userId);
    Tintuc addOrUpdateTinTuc(Tintuc tt);
}
