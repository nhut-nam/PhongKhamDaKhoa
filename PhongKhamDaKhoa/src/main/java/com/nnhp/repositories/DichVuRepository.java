/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Dichvu;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface DichVuRepository {
    List<Dichvu> getDsDichVu(Map<String, String> params);
    Dichvu getDichVuById(int id);
    Dichvu addOrUpdateDichVu(Dichvu dichVu);
    void deleteDichVu(int id);
} 