/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.enums.Role;
import com.nnhp.formaters.Formatter;
import static com.nnhp.formaters.Formatter.DATE_FORMATTER;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.servicesImpl.TaiKhoanServiceImpl;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hoang
 */
@Controller
public class UserController {
    @Autowired
    private TaiKhoanService taiKhoanService;
       
    @GetMapping("/admin-tai-khoan")
    public String addListView(Model model){
        List<Taikhoan> dsTaiKhoan = taiKhoanService.getTaiKhoanList();
        model.addAttribute("dsTaiKhoan", dsTaiKhoan);
        return "taikhoan";
    }
    
    @GetMapping("/taikhoanchange")
    public String addView(Model model){
        model.addAttribute("taiKhoan", new Taikhoan());
        return "taikhoanchange";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("ngaySinh");
    }
    
    @PostMapping("/tai-khoan/add")
    public String addBacSi(@ModelAttribute(value = "taiKhoan") Taikhoan tk,  @RequestParam("ngaySinh") String ngaySinh) {
        try {
                tk.setNgaySinh(Formatter.DATE_FORMATTER.parse(ngaySinh));
            } catch (ParseException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        tk.setRole("USER");
        this.taiKhoanService.addTaiKhoan(tk);
        return "redirect:/admin-tai-khoan";
    }
    
    @GetMapping("/tai-khoan/{taiKhoanEmail}")
    public String updateView(Model model, @PathVariable(value = "taiKhoanEmail") String email) {
        String role = this.taiKhoanService.getUserByEmail(email).getRole();
       
        if(role.equalsIgnoreCase("USER")){
             model.addAttribute("taiKhoan", this.taiKhoanService.getUserByEmail(email));
        return "taikhoanchange";
        }
        
        model.addAttribute("bacSi", this.taiKhoanService.getUserByEmail(email));
        return "bacsichange";
    }
}
