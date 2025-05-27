/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Benhvienchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.services.BenhVienChuyenKhoaService;
import com.nnhp.services.BenhVienService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author hoang
 */
@Controller
@RequestMapping("/admin")
public class ChuyenKhoaController {
    @Autowired
    private ChuyenKhoaService chuyenKhoaService;
    
    @Autowired
    private BenhVienService benhVienService;
    
    @Autowired
    private BenhVienChuyenKhoaService benhVienChuyenKhoaService;
   
    
    @GetMapping("/chuyen-khoa")
    public String addListView(Model model, @RequestParam Map<String, String> params){
        List<Chuyenkhoa> dsChuyenKhoa = chuyenKhoaService.getDsChuyenKhoa(params);
        List<Benhvien> dsBenhVien = benhVienService.getAllBenhVien();
        List<Benhvienchuyenkhoa> dsBVCK = benhVienChuyenKhoaService.getAll();
        
        model.addAttribute("dsChuyenKhoa", dsChuyenKhoa);
        model.addAttribute("dsBenhVien", dsBenhVien);
        model.addAttribute("dsBVCK", dsBVCK);
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
        return "redirect:/admin/chuyen-khoa";
    }
    
    @GetMapping("/chuyen-khoa/{chuyenkhoaId}")
    public String updateView(Model model, @PathVariable(value = "chuyenkhoaId") int id) {
        model.addAttribute("chuyenKhoa", this.chuyenKhoaService.getChuyenKhoaById(id));
        return "chuyenkhoachange";
    }
    
    @GetMapping("/benhvien-chuyenkhoa")
    public String quanLyBenhVienChuyenKhoa(Model model) {
        List<Benhvien> dsBenhVien = benhVienService.getAllBenhVien();
        List<Chuyenkhoa> dsChuyenKhoa = chuyenKhoaService.getDsChuyenKhoa(null);
        List<Benhvienchuyenkhoa> dsBVCK = benhVienChuyenKhoaService.getAll();
        
        model.addAttribute("dsBenhVien", dsBenhVien);
        model.addAttribute("dsChuyenKhoa", dsChuyenKhoa);
        model.addAttribute("dsBVCK", dsBVCK);
        model.addAttribute("benhVienChuyenKhoa", new Benhvienchuyenkhoa());
        
        return "benhvienchuyenkhoa";
    }
    
    @PostMapping("/benhvien-chuyenkhoa/add")
    public String addBenhVienChuyenKhoa(@ModelAttribute(value = "benhVienChuyenKhoa") Benhvienchuyenkhoa bvck,
                                        @RequestParam("benhVienId") int benhVienId,
                                        @RequestParam("chuyenKhoaId") int chuyenKhoaId,
                                        RedirectAttributes redirectAttributes) {
       
        if(benhVienChuyenKhoaService.tonTaiBenhVienIdChuyenKhoaId(benhVienId, chuyenKhoaId))
        {  redirectAttributes.addFlashAttribute("errorMessage", "Liên kết giữa bệnh viện và chuyên khoa đã tồn tại!");
        return "redirect:/admin/benhvien-chuyenkhoa";
        }
        
        Benhvien bv = benhVienService.getBenhVienById(benhVienId);
        Chuyenkhoa ck = chuyenKhoaService.getChuyenKhoaById(chuyenKhoaId);
        bvck.setBenhvienId(bv);
        bvck.setChuyenkhoaId(ck);
        
        benhVienChuyenKhoaService.addOrUpdate(bvck);
        
        return "redirect:/admin/benhvien-chuyenkhoa";
    }
    
    @GetMapping("/benhvien-chuyenkhoa/delete/{id}")
    public String deleteBenhVienChuyenKhoa(@PathVariable(value = "id") int id) {
        benhVienChuyenKhoaService.delete(id);
        return "redirect:/admin/benhvien-chuyenkhoa";
    }
}
