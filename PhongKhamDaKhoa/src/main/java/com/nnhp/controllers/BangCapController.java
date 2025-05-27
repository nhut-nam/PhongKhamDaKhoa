/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import java.beans.PropertyEditorSupport;
import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Bangcap;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BangCapService;
import com.nnhp.servicesImpl.BangCapServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hoang
 */
@Controller
@RequestMapping("/admin")
public class BangCapController {
    
    @Autowired
    private BangCapService bangCapService;
    @Autowired
    private BacSiService bacSiService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Chuyển đổi ID thành đối tượng Bacsi
        binder.setDisallowedFields("ngayCap", "ngayHetHan");
        binder.registerCustomEditor(Bacsi.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        int id = Integer.parseInt(text);
                        Bacsi bacsi = new Bacsi(id);
                        setValue(bacsi);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
    }
    
    @GetMapping("/bang-cap")
    public String benhVienList(Model model, @RequestParam Map<String, String> params) {
        List<Bangcap> dsBangCap = bangCapService.getListBangCap(params);
        List<Bacsi> dsBacSi = bacSiService.getDsBacSi(null);
        model.addAttribute("dsBangCap", dsBangCap);
        model.addAttribute("dsBacSi", dsBacSi);
        return "bangcap";
    }
    
    @GetMapping("/bangcapchange")
    public String addView(Model model) {
        model.addAttribute("bangCap", new Bangcap());
        model.addAttribute("dsBacSi", bacSiService.getDsBacSi(null));
        return "bangcapchange";
    }
    
    @GetMapping("/bang-cap/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("bangCap", this.bangCapService.getBangCapById(id));
        model.addAttribute("dsBacSi", bacSiService.getDsBacSi(null));
        return "bangcapchange";
    }
    
    @PostMapping("/bang-cap/add")
    public String addBenhVien(@ModelAttribute(value = "bangCap") Bangcap bc,
            @RequestParam("ngayCap") String ngayCap,
            @RequestParam("ngayHetHan") String ngayHetHan) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayCapMoi = formatter.parse(ngayCap);
            Date ngayHetHanMoi = formatter.parse(ngayHetHan);
            bc.setNgayCap(ngayCapMoi);
            bc.setNgayHetHan(ngayHetHanMoi);
            
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/admin/lichkhamchange?error=date";
        }
        this.bangCapService.addOrUpdateBangCap(bc);
        return "redirect:/admin/bang-cap";
    }
}
