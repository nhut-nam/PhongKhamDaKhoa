/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Hoso;
import com.nnhp.services.BenhNhanService;
import com.nnhp.services.HoSoService;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class HoSoController {
    @Autowired
    private HoSoService hoSoService;
    
    @Autowired
    private BenhNhanService benhNhanService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Chuyển đổi ID thành đối tượng Benhnhan
        binder.registerCustomEditor(Benhnhan.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        int id = Integer.parseInt(text);
                        Benhnhan benhnhan = benhNhanService.getBenhNhanById(id);
                        setValue(benhnhan);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
        
        // Chuyển đổi chuỗi ngày thành đối tượng Date
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(new Date()); // Mặc định là ngày hiện tại nếu trống
                } else {
                    try {
                        setValue(dateFormat.parse(text));
                    } catch (ParseException e) {
                        setValue(new Date()); // Mặc định là ngày hiện tại nếu không thể parse
                    }
                }
            }
            
            @Override
            public String getAsText() {
                Date date = (Date) getValue();
                return date != null ? dateFormat.format(date) : "";
            }
        });
    }
    
    @GetMapping("/ho-so")
    public String hoSoList(Model model, @RequestParam Map<String, String> params) {
        try {
            List<Hoso> dsHoSo = hoSoService.getDsHoSo(params);
            model.addAttribute("dsHoSo", dsHoSo);
            return "hoso";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải danh sách hồ sơ");
            return "error";
        }
    }
    
    @GetMapping("/hosochange")
    public String addView(Model model) {
        try {
            Hoso hoSo = new Hoso();
            hoSo.setNgayTao(new Date()); // Thiết lập ngày tạo là ngày hiện tại
            
            // Lấy danh sách bệnh nhân để hiển thị trong dropdown
            List<Benhnhan> dsBenhNhan = benhNhanService.getDsBenhNhan();
            model.addAttribute("dsBenhNhan", dsBenhNhan);
            
            if (!dsBenhNhan.isEmpty()) {
                hoSo.setBenhnhanId(dsBenhNhan.get(0)); // Lấy bệnh nhân đầu tiên làm mặc định
            }
            
            model.addAttribute("hoSo", hoSo);
            return "hosochange";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải trang thêm hồ sơ");
            return "error";
        }
    }
    
    @GetMapping("/ho-so/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id) {
        try {
            Hoso hoSo = this.hoSoService.getHoSoById(id);
            if (hoSo == null) {
                model.addAttribute("errorMessage", "Không tìm thấy hồ sơ với ID: " + id);
                return "error";
            }
            
            // Lấy danh sách bệnh nhân để hiển thị trong dropdown
            List<Benhnhan> dsBenhNhan = benhNhanService.getDsBenhNhan();
            model.addAttribute("dsBenhNhan", dsBenhNhan);
            
            model.addAttribute("hoSo", hoSo);
            return "hosochange";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải trang cập nhật hồ sơ");
            return "error";
        }
    }
    
    @PostMapping("/ho-so/add")
    public String addHoSo(@ModelAttribute(value = "hoSo") Hoso hoSo) {
        try {
            // Nếu là hồ sơ mới và chưa có ngày tạo, thiết lập ngày tạo là ngày hiện tại
            if (hoSo.getNgayTao() == null) {
                hoSo.setNgayTao(new Date());
            }
            
            // Kiểm tra xem bệnh nhân có tồn tại không
            if (hoSo.getBenhnhanId() == null) {
                return "redirect:/hosochange?error=no_patient";
            }
            
            // Kiểm tra sự tồn tại của bệnh nhân trong cơ sở dữ liệu
            Benhnhan bn = benhNhanService.getBenhNhanById(hoSo.getBenhnhanId().getId());
            if (bn == null) {
                return "redirect:/hosochange?error=invalid_patient";
            }
            
            Hoso savedHoSo = this.hoSoService.addOrUpdateHoSo(hoSo);
            if (savedHoSo != null) {
                return "redirect:/ho-so";
            } else {
                return "redirect:/hosochange?error=save_failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/hosochange?error=exception";
        }
    }
} 