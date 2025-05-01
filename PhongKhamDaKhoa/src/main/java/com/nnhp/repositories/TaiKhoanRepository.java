/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.pojo.Taikhoan;

/**
 *
 * @author namnh
 */
public interface TaiKhoanRepository {
    Taikhoan getUserByEmail(String email);
    Taikhoan getTaikhoanTest(String email);
}
