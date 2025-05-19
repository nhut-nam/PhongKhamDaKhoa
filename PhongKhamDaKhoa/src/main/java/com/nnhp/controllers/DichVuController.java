/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Dichvu;
import com.nnhp.pojo.Benhvienchuyenkhoa;
import com.nnhp.services.BenhVienChuyenKhoaService;
import com.nnhp.services.DichVuService;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hoang
 */
@Controller
public class DichVuController {
    @Autowired
    private DichVuService dichVuService;
    
    @Autowired
    private BenhVienChuyenKhoaService benhVienChuyenKhoaService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Benhvienchuyenkhoa.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        int id = Integer.parseInt(text);
                        Benhvienchuyenkhoa bvck = new Benhvienchuyenkhoa(id);
                        setValue(bvck);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
    }
    
    @GetMapping("/dich-vu")
    public String dichVuList(Model model, @RequestParam Map<String, String> params) {
        try {
            List<Dichvu> dsDichVu = dichVuService.getDsDichVu(params);
            model.addAttribute("dsDichVu", dsDichVu);
            return "dichvu";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải danh sách dịch vụ");
            return "error";
        }
    }
    
    @GetMapping("/dichvuchange")
    public String addView(Model model) {
        try {
            model.addAttribute("dichVu", new Dichvu());
            
            List<Benhvienchuyenkhoa> dsBenhVienChuyenKhoa = new ArrayList<>();
            try {
                dsBenhVienChuyenKhoa = benhVienChuyenKhoaService.getAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            model.addAttribute("dsBenhVienChuyenKhoa", dsBenhVienChuyenKhoa);
            return "dichvuchange";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải trang thêm dịch vụ");
            return "error";
        }
    }
    
    @GetMapping("/dich-vu/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id) {
        try {
            model.addAttribute("dichVu", this.dichVuService.getDichVuById(id));
            
            List<Benhvienchuyenkhoa> dsBenhVienChuyenKhoa = new ArrayList<>();
            try {
                dsBenhVienChuyenKhoa = benhVienChuyenKhoaService.getAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            model.addAttribute("dsBenhVienChuyenKhoa", dsBenhVienChuyenKhoa);
            return "dichvuchange";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải trang cập nhật dịch vụ");
            return "error";
        }
    }
    
    @PostMapping("/dich-vu/add")
    public String addDichVu(@ModelAttribute(value = "dichVu") Dichvu dichVu) {
        try {
            Dichvu savedDichVu = this.dichVuService.addOrUpdateDichVu(dichVu);
            if (savedDichVu != null) {
                return "redirect:/dich-vu";
            } else {
                return "redirect:/dichvuchange?error=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/dichvuchange?error=true";
        }
    }
} 