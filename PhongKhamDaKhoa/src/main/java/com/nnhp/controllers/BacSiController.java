/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Bacsithuocchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BenhVienService;
import com.nnhp.services.ChuyenKhoaService;
import com.nnhp.services.TaiKhoanService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class BacSiController {
    @Autowired
    private BacSiService bacSiService;
    @Autowired   
    private BenhVienService benhVienService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ChuyenKhoaService chuyenKhoaService;
    
    private void benhVienForm(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("dsBenhVien", benhVienService.getDsBenhVien(params));
    }
    
     private void chuyenKhoaForm(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("dsChuyenKhoa", chuyenKhoaService.getDsChuyenKhoa(params));
    }
    
    @GetMapping("/bac-si")
    public String addListView(Model model, @RequestParam Map<String, String> params){
        List<Bacsi> dsBacSi = bacSiService.getDsBacSi(params);
        model.addAttribute("dsBacSi", dsBacSi);
        return "bacsi";
    }
    
    @GetMapping("/bacsichange")
    public String addView(Model model){
        model.addAttribute("bacSi", new Bacsi());
        benhVienForm(model, Collections.emptyMap());
        chuyenKhoaForm(model, Collections.emptyMap());
        return "bacsichange";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("ngaySinh", "ngayLamViec", "ngayNghiViec");
    }
    
    @PostMapping("/bac-si/add")
    public String addBacSi(@ModelAttribute(value = "bacSi") Bacsi b, @RequestParam("ngaySinh") String ngaySinh,
            @RequestParam("ngayLamViec") String ngayLamViec, @RequestParam("ngayNghiViec") String ngayNghiViec,
            @RequestParam(value = "dsChuyenKhoa", required = false) List<Integer> dsChuyenKhoa) throws ParseException{
        
        b.setRole("DOCTOR");
        b.setNgaySinh(Formatter.DATE_FORMATTER.parse(ngaySinh));
        if(!ngayLamViec.isEmpty()){
        b.setNgayLamViec(Formatter.DATE_FORMATTER.parse(ngayLamViec));
        }
        if(!ngayNghiViec.isEmpty()){
        b.setNgayNghiViec(Formatter.DATE_FORMATTER.parse(ngayNghiViec));
        }
        b.setMatKhau(this.passwordEncoder.encode(b.getMatKhau()));
        
        if(dsChuyenKhoa!=null && !dsChuyenKhoa.isEmpty())
        {
            Collection<Bacsithuocchuyenkhoa> dsBsCk = new ArrayList<>();
            for (Integer id : dsChuyenKhoa) {
                Chuyenkhoa ck = chuyenKhoaService.getChuyenKhoaById(id);
            if (ck != null) {
                Bacsithuocchuyenkhoa bsCk = new Bacsithuocchuyenkhoa();
                bsCk.setBacsiId(b);   
                bsCk.setChuyenkhoaId(ck);
                dsBsCk.add(bsCk);
            }
        }
        b.setBacsithuocchuyenkhoaCollection(dsBsCk);
        }
        
        this.bacSiService.addOrUpdateBacSi(b);
        return "redirect:/admin-tai-khoan";
    }
    
    @GetMapping("/bac-si/{bacsiId}")
    public String updateView(Model model, @PathVariable(value = "bacsiId") int id) {
        Bacsi bacSi = this.bacSiService.getBacSiById(id);
        model.addAttribute("bacSi", bacSi);
        benhVienForm(model, Collections.emptyMap());
        chuyenKhoaForm(model, Collections.emptyMap());
        
        List<Integer> dsChuyenKhoaDaChon = this.bacSiService.getChuyenKhoaByBacSiId(id).stream().map(Chuyenkhoa::getId).collect(Collectors.toList());
        model.addAttribute("dsChuyenKhoaDaChon", dsChuyenKhoaDaChon);
        return "bacsichange";
    }
    
}
