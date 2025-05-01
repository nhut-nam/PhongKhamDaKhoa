/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nnhp.enums;

import com.nnhp.pojo.Taikhoan;
import java.lang.Exception;

/**
 *
 * @author namnh
 */
public enum Role {
    ADMIN {
        @Override
        public String toString() {
            return "Quantri";
        }

        @Override
        public Object getObjAccount(Taikhoan account) {
            return null;
        }
    },
    USER {
        @Override
        public String toString() {
            return "Benhnhan";
        }

        @Override
        public Object getObjAccount(Taikhoan account) throws Exception {
            return null;
        }
    },
    DOCTOR {
        @Override
        public String toString() {
            return "Bacsi";
        }

        @Override
        public Object getObjAccount(Taikhoan account) throws Exception {
            return null;
        }
    };

    public abstract String toString();

    public abstract Object getObjAccount(Taikhoan account) throws Exception;
}
