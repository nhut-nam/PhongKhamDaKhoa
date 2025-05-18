/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Dichvu;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface DichVuService {
    List<Dichvu> getDsDichVu(Map<String, String> params);
    Dichvu getDichVuById(int id);
    Dichvu addOrUpdateDichVu(Dichvu dichVu);
    void deleteDichVu(int id);
} 