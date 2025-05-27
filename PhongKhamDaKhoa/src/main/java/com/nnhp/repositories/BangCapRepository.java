/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Bangcap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author namnh
 */
public interface BangCapRepository {
    Bangcap addBangCap(Bangcap bc);
    List<Bangcap> getListBangCap(Map<String, String> params);
    List<Bangcap> getListBangCapByBacSiId(int bacSiId);
    Bangcap getBangCapById(int id);
    Bangcap addOrUpdateBangCap(Bangcap bc);
}
