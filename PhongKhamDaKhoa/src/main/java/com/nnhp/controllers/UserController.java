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
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public String addListView(Model model, @RequestParam Map<String, String> params) {
        String role = params.getOrDefault("role", "");
        List<Taikhoan> dsTaiKhoan = taiKhoanService.getDsTaiKhoan(params, role);
        model.addAttribute("dsTaiKhoan", dsTaiKhoan);
        model.addAttribute("selectedRole", role);
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
    public String addTaiKhoan(@ModelAttribute(value = "taiKhoan") Taikhoan tk, @RequestParam("ngaySinh") String ngaySinh, @RequestParam(value = "role", required = false, defaultValue = "USER") String role) {
        try {
            tk.setNgaySinh(Formatter.DATE_FORMATTER.parse(ngaySinh));
        } catch (ParseException ex) {
            Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Kiểm tra số điện thoại đã tồn tại chưa
        List<Taikhoan> dsTaiKhoan = taiKhoanService.getTaiKhoanList();
        for (Taikhoan t : dsTaiKhoan) {
            if (t.getSoDienThoai() != null && t.getSoDienThoai().equals(tk.getSoDienThoai()) && !t.getId().equals(tk.getId())) {
                // Số điện thoại đã tồn tại
                return "redirect:/taikhoanchange?error=duplicate_phone";
            }
            
            if (t.getEmail() != null && t.getEmail().equals(tk.getEmail()) && !t.getId().equals(tk.getId())) {
                // Email đã tồn tại
                return "redirect:/taikhoanchange?error=duplicate_email";
            }
        }
        
        // Thiết lập role mặc định là USER nếu không được chỉ định
        tk.setRole(role);
        
        try {
            this.taiKhoanService.addTaiKhoan(tk);
            return "redirect:/admin-tai-khoan";
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            // Xác định loại lỗi
            String errorMessage = e.getMessage().toLowerCase();
            if (errorMessage.contains("so_dien_thoai") || errorMessage.contains("phone")) {
                return "redirect:/taikhoanchange?error=duplicate_phone";
            } else if (errorMessage.contains("email")) {
                return "redirect:/taikhoanchange?error=duplicate_email";
            } else {
                return "redirect:/taikhoanchange?error=database";
            }
        } catch (Exception e) {
            return "redirect:/taikhoanchange?error=unknown";
        }
    }
    
    @GetMapping("/tai-khoan/{taiKhoanEmail}")
    public String updateView(Model model, @PathVariable(value = "taiKhoanEmail") String email) {
        Taikhoan tk = this.taiKhoanService.getUserByEmail(email);
        if (tk == null) {
            return "redirect:/admin-tai-khoan?error=not_found";
        }
        model.addAttribute("taiKhoan", tk);
        return "taikhoanchange";
    }
}
