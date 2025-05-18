/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.enums.Role;
import com.nnhp.pojo.Taikhoan;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author namnh
 */
public interface TaiKhoanService extends UserDetailsService {
    Taikhoan getTaiKhoanTest();
    List<Taikhoan> getTaiKhoanList();
    Taikhoan getUserByEmail(String email);
    Taikhoan addTaiKhoan(Map<String, String> params, Role role);
    boolean authenticate(String email, String matKhau);
    Taikhoan addTaiKhoan(Taikhoan tk);
    void deleteUser(int id);
    Taikhoan getUserById(int id);
    Taikhoan addOrUpdateTaiKhoan(Taikhoan tk);
    List<Taikhoan> getDsTaiKhoan(Map<String, String> params, String role) ;
}
