/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Lichsukhambenh;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface LichSuKhamBenhService {
    List<Lichsukhambenh> getLichSuKhamBenhByUser(int hoSoId);
    Lichsukhambenh updateLichSuKhamBenh(int id, Map<String, String> payload);
}
