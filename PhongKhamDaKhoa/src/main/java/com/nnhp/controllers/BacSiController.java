/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Bacsi;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BenhVienService;
import java.util.Collections;
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
public class BacSiController {
    @Autowired
    private BacSiService bacSiService;
    @Autowired   
    private BenhVienService benhVienService;
    
    private void benhVienForm(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("dsBenhVien", benhVienService.getDsBenhVien(params));
    }
    
    @GetMapping("/bac-si")
    public String addListView(Model model, @RequestParam Map<String, String> params){
        List<Bacsi> dsChuyenKhoa = bacSiService.getDsBacSi(params);
        model.addAttribute("dsBacSi", dsChuyenKhoa);
        return "bacsi";
    }
    
    @GetMapping("/bacsichange")
    public String addView(Model model){
        model.addAttribute("bacsi", new Bacsi());
        benhVienForm(model, Collections.emptyMap());
        return "bacsichange";
    }
    
    @PostMapping("/bac-si/add")
    public String addBacSi(@ModelAttribute(value = "chuyenKhoa") Bacsi b) {
        this.bacSiService.addOrUpdateBacSi(b);
        return "redirect:/bac-si";
    }
    
    @GetMapping("/bac-si/{bacsiId}")
    public String updateView(Model model, @PathVariable(value = "bacsiId") int id) {
        model.addAttribute("bacSi", this.bacSiService.getBacSiById(id));
        benhVienForm(model, Collections.emptyMap());
        return "bacsichange";
    }
    
}
