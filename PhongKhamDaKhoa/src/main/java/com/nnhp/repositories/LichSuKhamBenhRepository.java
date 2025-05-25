/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;
import com.nnhp.pojo.Lichsukhambenh;
import java.util.List;

/**
 *
 * @author hoang
 */
public interface LichSuKhamBenhRepository {
    List<Lichsukhambenh> getLichSuKhamBenhByUser(int hoSoId);
    void updateLichSuKhamBenh(Lichsukhambenh lichSu);
    Lichsukhambenh getLichSuKhamBenhById(int id);
}
