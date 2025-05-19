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
 * @author namnh
 */
public interface HoSoService {
    Hoso addHoSo(Map<String, Object> params, Benhnhan bn);
    List<Hoso> getHoSoList(int id);
    void deleteHoSo(int id);
    Hoso getHoSoById(int id);
    Hoso updateHoSO(Hoso hs);
}
