/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author namnh
 */
@Entity
@Table(name = "quantri")
public class QuanTri extends TaiKhoan implements Serializable {

    public QuanTri(int id) {
        super(id);
    }

    public QuanTri() {
    }
    
}
