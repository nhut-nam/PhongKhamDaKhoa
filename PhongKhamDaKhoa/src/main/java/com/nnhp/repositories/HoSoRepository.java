/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
=======
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
>>>>>>> 7e14acf64c3e93a4ffa2381092131fdc1bd36eeb
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Hoso;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface HoSoRepository {
    List<Hoso> getDsHoSo(Map<String, String> params);
    Hoso getHoSoById(int id);
    Hoso addOrUpdateHoSo(Hoso hoSo);
    void deleteHoSo(int id);
    boolean kiemTraHoSoTonTai(int id);
    Hoso addHoSo(Hoso hs);
    List<Hoso> getHoSoList(int id);
    Hoso updateHoSo(Hoso hs);
    List<Hoso> getHoSoByBacSi(int bacSiId);
} 
