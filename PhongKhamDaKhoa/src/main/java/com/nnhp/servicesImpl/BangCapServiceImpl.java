/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nnhp.enums.TrangThaiBangCap;
import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Bangcap;
import com.nnhp.repositories.BangCapRepository;
import com.nnhp.services.BacSiService;
import com.nnhp.services.BangCapService;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author namnh
 */
@Service
public class BangCapServiceImpl implements BangCapService {

    @Autowired
    private BangCapRepository bcRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BacSiService bsService;

    @Override
    public Bangcap addBangCap(Map<String, String> params, MultipartFile hinhMatTruoc) {
        Bangcap bc = new Bangcap();
        bc.setCoQuanCap(params.get("coQuanCap"));
        try {
            bc.setNgayCap(Formatter.DATE_FORMATTER.parse(params.get("ngayCap")));
            bc.setNgayHetHan(Formatter.DATE_FORMATTER.parse(params.get("ngayHetHan")));
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Ngày không đúng định dạng yyyy-MM-dd", ex);
        }
        try {
            Map res = cloudinary.uploader().upload(hinhMatTruoc.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            bc.setHinhMatTruoc(res.get("secure_url").toString());
        } catch (IOException ex) {
            Logger.getLogger(BangCapServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        bc.setTrangThai(TrangThaiBangCap.DOI_DUYET);
        if (params.containsKey("userId")) {
            bc.setBacsiId(this.bsService.getBacSiById(Integer.parseInt(params.get("userId"))));
        }
        return this.bcRepo.addBangCap(bc);
    }

    @Override
    public Bangcap addBangCap(Bangcap bc, MultipartFile hinhMatTruoc) {
        try {
            Map res = cloudinary.uploader().upload(hinhMatTruoc.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            bc.setHinhMatTruoc(res.get("secure_url").toString());
        } catch (IOException ex) {
            Logger.getLogger(BangCapServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.bcRepo.addBangCap(bc);
    }

}
