/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Hoso;
import java.util.List;

/**
 *
 * @author namnh
 */
public interface HoSoRepository {
    Hoso addHoSo(Hoso hs);
    List<Hoso> getHoSoList(int id);
    void deleteHoSo(int id);
    Hoso getHoSoById(int id);
}
