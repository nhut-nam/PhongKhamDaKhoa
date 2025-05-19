/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Benhnhan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public interface BenhNhanService {
    List<Benhnhan> getDsBenhNhan();
    List<Benhnhan> getDsBenhNhan(Map<String, String> params);
    Benhnhan getBenhNhanById(int id);
    Benhnhan addOrUpdateBenhNhan(Benhnhan bn);
    boolean kiemTraBenhNhanTonTai(int id);
} 