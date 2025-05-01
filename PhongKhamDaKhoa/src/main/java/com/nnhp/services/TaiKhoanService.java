/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Taikhoan;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author namnh
 */
public interface TaiKhoanService extends UserDetailsService {
    Taikhoan getTaiKhoanTest();
}
