/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.services;

import com.nnhp.pojo.Bangcap;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author namnh
 */
public interface BangCapService {
    Bangcap addBangCap(Map<String, String> params, MultipartFile hinhMatTruoc);
    Bangcap addBangCap(Bangcap bc, MultipartFile hinhMatTruoc);
}
