/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Tintuc;
import java.util.List;

/**
 *
 * @author namnh
 */
public interface TinTucService {
    Tintuc getTinTucById(int id);
    List<Tintuc> findTop10TinTucChuaGanUser(int userId);
    List<Tintuc> getTinTucByUserId(int userId);
    Tintuc addOrUpdateTinTuc(Tintuc tt);
    List<Tintuc> getTop10TintucMoiNhat();
}
