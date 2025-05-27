/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositories;

import com.nnhp.enums.Role;
import com.nnhp.enums.TrangThaiTaiKhoan;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.ThongBao;
import java.util.List;
import java.util.Map;

/**
 *
 * @author namnh
 */
public interface TaiKhoanRepository {
    Taikhoan getUserByEmailByTrangThai(String email, TrangThaiTaiKhoan trangThai);
    Taikhoan getUserByEmail(String email);
    Taikhoan getUserById(int id);
    Taikhoan getTaikhoanTest(String email);
    List<Taikhoan> getTaiKhoanList();
    Taikhoan addTaiKhoan(Taikhoan tk);
    ThongBao authenticate(String email, String matKhau);
    boolean authenticateRole(String email,String password);

    void deleteUser(int id);
    Taikhoan addOrUpdateTaiKhoan(Taikhoan tk);
    List<Taikhoan> getDsTaiKhoan(Map<String, String> params, String role);
    Taikhoan updateTaiKhoan(Taikhoan tk);
    List<Taikhoan> getListUserByTrangThai(TrangThaiTaiKhoan trangThai);
}
