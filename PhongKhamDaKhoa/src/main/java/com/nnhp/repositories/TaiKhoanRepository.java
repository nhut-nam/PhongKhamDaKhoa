/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.enums.Role;
import com.nnhp.pojo.Taikhoan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author namnh
 */
public interface TaiKhoanRepository {
    Taikhoan getUserByEmail(String email);
    Taikhoan getUserById(int id);
    Taikhoan getTaikhoanTest(String email);
    List<Taikhoan> getTaiKhoanList();
    Taikhoan addTaiKhoan(Taikhoan tk);
    boolean authenticate(String email, String matKhau);
    void deleteUser(int id);
    Taikhoan addOrUpdateTaiKhoan(Taikhoan tk);
    List<Taikhoan> getDsTaiKhoan(Map<String, String> params, String role);
}
