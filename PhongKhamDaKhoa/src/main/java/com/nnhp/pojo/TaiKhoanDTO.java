/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.pojo;

/**
 *
 * @author namnh
 */
public class TaiKhoanDTO {
    private Integer id;
    private String email;

    public TaiKhoanDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }
    
    public static TaiKhoanDTO convertToDTO(Taikhoan tk) {
        return new TaiKhoanDTO(tk.getId(), tk.getEmail());
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
