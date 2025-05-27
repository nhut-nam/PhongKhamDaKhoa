/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.TaiKhoanTinTuc;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.Tintuc;

/**
 *
 * @author namnh
 */
public interface TaiKhoanTinTucService {
    TaiKhoanTinTuc addOrUpdateTaiKhoanTinTuc(Taikhoan tk, Tintuc tt);
}
