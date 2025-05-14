/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nnhp.enums;

import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Quantri;
import com.nnhp.pojo.Taikhoan;
import java.lang.Exception;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author namnh
 */
public enum Role {
    ADMIN {
        @Override
        public Object getObjAccount(Taikhoan account) {
            return null;
        }

        @Override
        public Taikhoan objectInitilization(Taikhoan tk, Map<String, String> params) {
            Quantri qt = (Quantri) tk;
            return qt;
        }

        @Override
        public String getClassName() {
            return "Quantri";
        }
    },
    USER {
        @Override
        public Object getObjAccount(Taikhoan account) throws Exception {
            return null;
        }

        @Override
        public Taikhoan objectInitilization(Taikhoan tk, Map<String, String> params) {
            return (Benhnhan) tk;
        }

        @Override
        public String getClassName() {
            return "Benhnhan";
        }
    },
    DOCTOR {
        @Override
        public Object getObjAccount(Taikhoan account) throws Exception {
            return null;
        }

        @Override
        public Taikhoan objectInitilization(Taikhoan tk, Map<String, String> params) {          
            Bacsi bs = (Bacsi) tk;
            bs.setChuyenTri(params.get("chuyenTri"));
            try {
                bs.setNgayLamViec(Formatter.DATE_FORMATTER.parse(params.get("ngayLamViec")));
            } catch (ParseException ex) {
                Logger.getLogger(Role.class.getName()).log(Level.SEVERE, null, ex);
            }
            bs.setNgayNghiViec(null);
            return bs;
        }

        @Override
        public String getClassName() {
            return "Bacsi";
        }
    };
    
    public abstract String getClassName();
    public abstract Object getObjAccount(Taikhoan account) throws Exception;
    public abstract Taikhoan objectInitilization(Taikhoan tk, Map<String, String> params);
}
