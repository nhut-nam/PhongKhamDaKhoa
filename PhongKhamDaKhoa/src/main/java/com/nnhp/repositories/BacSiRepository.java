/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Bacsi;
import java.util.List;
import java.util.Map;

/**
 *
 * @author namnh
 */
public interface BacSiRepository {
    List<Bacsi> getDsBacSi(Map<String, String> params);
    Bacsi getBacSiById(int id);
    Bacsi addOrUpdateBacSi(Bacsi b);
    void deleteBacSi(int id);
}
