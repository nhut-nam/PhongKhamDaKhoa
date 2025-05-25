/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import java.util.List;

/**
 *
 * @author hoang
 */
public interface ThongKeBacSiRepository {
     List<Object[]> thongKeSoBenhNhanDaKham(String loaiThongKe, int nam, Integer bacSiId);
     List<Object[]> thongKeLoaiBenhPhoBien(String loaiThongKe, int nam, Integer bacSiId);
}
