/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services.handler;

import com.nnhp.pojo.Taikhoan;
import java.util.Map;

/**
 *
 * @author namnh
 */
public interface RoleHandler {
    Taikhoan handle(Map<String, String> params);
    Taikhoan handle(Taikhoan tk);
}
