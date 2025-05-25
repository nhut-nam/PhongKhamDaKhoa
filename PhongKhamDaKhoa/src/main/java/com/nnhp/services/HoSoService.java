/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Hoso;
import java.util.List;
import java.util.Map;

/**
 *
<<<<<<< HEAD
 * @author hoang
 */
public interface HoSoService {
    List<Hoso> getDsHoSo(Map<String, String> params);
    Hoso getHoSoById(int id);
    Hoso addOrUpdateHoSo(Hoso hoSo);
    void deleteHoSo(int id);
    boolean kiemTraHoSoTonTai(int id);
    List<Hoso> getHoSoList(int id);
    Hoso addHoSo(Map<String, Object> params, Benhnhan bn);
    Hoso updateHoSO(Hoso hs);
    List<Hoso> getHoSoByBacSi(int bacSiId);
} 

