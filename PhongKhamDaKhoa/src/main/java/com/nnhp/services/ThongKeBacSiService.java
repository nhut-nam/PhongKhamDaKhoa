/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import java.util.List;

/**
 *
 * @author hoang
 */
public interface ThongKeBacSiService {
    List<Object[]> thongKeSoBenhNhanDaKham(String loaiThongKe, Integer nam, Integer bacSiId);
    List<Object[]> thongKeLoaiBenhPhoBien(String loaiThongKe, Integer nam, Integer bacSiId);
}
