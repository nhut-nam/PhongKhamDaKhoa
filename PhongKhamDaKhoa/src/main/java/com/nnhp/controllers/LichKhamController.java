/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.pojo.Lichkham;
import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Hoso;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BenhVienService;
import com.nnhp.services.BenhVienChuyenKhoaDichVuService;
import com.nnhp.services.HoSoService;
import com.nnhp.services.LichKhamService;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class LichKhamController {
    @Autowired
    private LichKhamService lichKhamService;
    
    @Autowired
    private BacSiService bacSiService;
    
    @Autowired
    private BenhVienService benhVienService;
    
    @Autowired
    private BenhVienChuyenKhoaDichVuService benhVienChuyenKhoaDichVuService;
    
    @Autowired
    private HoSoService hoSoService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Chuyển đổi ID thành đối tượng BenhVienChuyenKhoaDichVu
        binder.registerCustomEditor(BenhVienChuyenKhoaDichVu.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        int id = Integer.parseInt(text);
                        BenhVienChuyenKhoaDichVu bvckdv = new BenhVienChuyenKhoaDichVu();
                        bvckdv.setId(id);
                        setValue(bvckdv);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
        
        // Chuyển đổi ID thành đối tượng Bacsi
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
        
        // Chuyển đổi ID thành đối tượng Hoso
        binder.registerCustomEditor(Hoso.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        int id = Integer.parseInt(text);
                        Hoso hoso = new Hoso(id);
                        setValue(hoso);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
    }
    
    @GetMapping("/lich-kham")
    public String lichKhamList(Model model, @RequestParam Map<String, String> params) {
        List<Lichkham> dsLichKham = lichKhamService.getDsLichKham(params);
        model.addAttribute("dsLichKham", dsLichKham);
        model.addAttribute("dsTrangThai", TrangThaiLichKham.values());
        model.addAttribute("dsBacSi", bacSiService.getDsBacSi(null));
        return "lichkham";
    }
    
    @GetMapping("/lichkhamchange")
    public String addView(Model model) {
        try {
            model.addAttribute("lichKham", new Lichkham());
            model.addAttribute("dsTrangThai", TrangThaiLichKham.values());
            model.addAttribute("dsBacSi", bacSiService.getDsBacSi(null));
            model.addAttribute("dsBenhVien", benhVienService.getDsBenhVien(null));
            model.addAttribute("dsHoSo", hoSoService.getDsHoSo(null));
            
            List<BenhVienChuyenKhoaDichVu> dsDichVu = new ArrayList<>();
            try {
                dsDichVu = benhVienChuyenKhoaDichVuService.getDsBenhVienChuyenKhoaDichVu();
            } catch (Exception ex) {
                // Log lỗi và tiếp tục với danh sách trống
                ex.printStackTrace();
            }
            
            model.addAttribute("dsDichVu", dsDichVu);
            return "lichkhamchange";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/lich-kham?error=true";
        }
    }
    
    @GetMapping("/lich-kham/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id) {
        try {
            model.addAttribute("lichKham", this.lichKhamService.getLichKhamById(id));
            model.addAttribute("dsTrangThai", TrangThaiLichKham.values());
            model.addAttribute("dsBacSi", bacSiService.getDsBacSi(null));
            model.addAttribute("dsBenhVien", benhVienService.getDsBenhVien(null));
            model.addAttribute("dsHoSo", hoSoService.getDsHoSo(null));
            
            List<BenhVienChuyenKhoaDichVu> dsDichVu = new ArrayList<>();
            try {
                dsDichVu = benhVienChuyenKhoaDichVuService.getDsBenhVienChuyenKhoaDichVu();
            } catch (Exception ex) {
                // Log lỗi và tiếp tục với danh sách trống
                ex.printStackTrace();
            }
            
            model.addAttribute("dsDichVu", dsDichVu);
            return "lichkhamchange";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/lich-kham?error=true";
        }
    }
    
    @PostMapping("/lich-kham/add")
    public String addLichKham(@ModelAttribute(value = "lichKham") Lichkham lichKham, 
                             @RequestParam("ngayHenStr") String ngayHenStr) {
        try {
            // Xử lý ngày hẹn
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date ngayHen = formatter.parse(ngayHenStr);
                lichKham.setNgayHen(ngayHen);
            } catch (ParseException e) {
                e.printStackTrace();
                return "redirect:/lichkhamchange?error=date";
            }
            
            // Kiểm tra các thuộc tính bắt buộc
            if (lichKham.getBacsiId() == null) {
                return "redirect:/lichkhamchange?error=bacsi";
            }
            
            if (lichKham.getBenhvienchuyenkhoadichvuId() == null) {
                return "redirect:/lichkhamchange?error=dichvu";
            }
            
            // Kiểm tra hồ sơ
            if (lichKham.getHosoId() == null) {
                return "redirect:/lichkhamchange?error=hoso";
            }
            
            // Kiểm tra xem hồ sơ có tồn tại trong cơ sở dữ liệu không
            if (!hoSoService.kiemTraHoSoTonTai(lichKham.getHosoId().getId())) {
                return "redirect:/lichkhamchange?error=hoso_not_exist";
            }
            
            // Lưu lịch khám
            Lichkham savedLichKham = this.lichKhamService.addOrUpdateLichKham(lichKham);
            if (savedLichKham != null) {
                return "redirect:/lich-kham";
            } else {
                return "redirect:/lichkhamchange?error=save";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/lichkhamchange?error=unknown";
        }
    }
} 