/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Hoso;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface HoSoService {
    List<Hoso> getDsHoSo(Map<String, String> params);
    Hoso getHoSoById(int id);
    Hoso addOrUpdateHoSo(Hoso hoSo);
    void deleteHoSo(int id);
    boolean kiemTraHoSoTonTai(int id);
} 