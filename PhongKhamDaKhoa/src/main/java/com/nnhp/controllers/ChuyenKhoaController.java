/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.services.ChuyenKhoaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hoang
 */
@Controller
public class ChuyenKhoaController {
    @Autowired
    private ChuyenKhoaService chuyenKhoaService;
   
    
    @GetMapping("/chuyen-khoa")
    public String addListView(Model model, @RequestParam Map<String, String> params){
        List<Chuyenkhoa> dsChuyenKhoa = chuyenKhoaService.getDsChuyenKhoa(params);
        model.addAttribute("dsChuyenKhoa", dsChuyenKhoa);
        return "chuyenkhoa";
    }
    
    @GetMapping("/chuyenkhoachange")
    public String addView(Model model){
        model.addAttribute("chuyenKhoa", new Chuyenkhoa());
        return "chuyenkhoachange";
    }
    
    @PostMapping("/chuyen-khoa/add")
    public String addChuyenKhoa(@ModelAttribute(value = "chuyenKhoa") Chuyenkhoa c) {
        this.chuyenKhoaService.addOrUpdateChuyenKhoa(c);
        return "redirect:/chuyen-khoa";
    }
    
    @GetMapping("/chuyen-khoa/{chuyenkhoaId}")
    public String updateView(Model model, @PathVariable(value = "chuyenkhoaId") int id) {
        model.addAttribute("chuyenKhoa", this.chuyenKhoaService.getChuyenKhoaById(id));
        return "chuyenkhoachange";
    }
}
