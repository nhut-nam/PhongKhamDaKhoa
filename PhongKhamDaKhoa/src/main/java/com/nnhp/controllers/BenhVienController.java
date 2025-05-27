/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Benhvien;
import com.nnhp.services.BenhVienService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hoang
 */
@Controller
@RequestMapping("/admin")
public class BenhVienController {
    @Autowired
    private BenhVienService benhVienService;
    
    @GetMapping("/benh-vien")
    public String benhVienList(Model model, @RequestParam Map<String, String> params) {
        List<Benhvien> dsBenhVien = benhVienService.getDsBenhVien(params);
        model.addAttribute("dsBenhVien", dsBenhVien);
        return "benhvien";
    }
    
    @GetMapping("/benhvienchange")
    public String addView(Model model) {
        model.addAttribute("benhVien", new Benhvien());
        return "benhvienchange";
    }
    
    @GetMapping("/benh-vien/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("benhVien", this.benhVienService.getBenhVienById(id));
        return "benhvienchange";
    }
    
    @PostMapping("/benh-vien/add")
    public String addBenhVien(@ModelAttribute(value = "benhVien") Benhvien bv) {
        this.benhVienService.addOrUpdateBenhVien(bv);
        return "redirect:/admin/benh-vien";
    }
} 