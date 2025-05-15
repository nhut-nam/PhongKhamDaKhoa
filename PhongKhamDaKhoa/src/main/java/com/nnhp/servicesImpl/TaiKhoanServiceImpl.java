/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.enums.Role;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositories.TaiKhoanRepository;
import com.nnhp.services.TaiKhoanService;
import com.nnhp.services.handler.RoleHandler;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author namnh
 */
@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository tkRepo;
    private Map<String, RoleHandler> handlerMap;
    
    @Autowired
    public TaiKhoanServiceImpl(List<RoleHandler> handlers) {
        this.handlerMap = handlers.stream().collect(Collectors.toMap(h -> h.getClass().getAnnotation(Component.class).value(), h -> h));
    }

    @Override
    public Taikhoan getTaiKhoanTest() {
        return tkRepo.getTaikhoanTest("");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Taikhoan u = this.getUserByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid username!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));
        
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getMatKhau(), authorities);
    }

    @Override
    public List<Taikhoan> getTaiKhoanList() {
        return tkRepo.getTaiKhoanList();
    }

    @Override
    public Taikhoan getUserByEmail(String email) {
        return this.tkRepo.getUserByEmail(email);
    }

    @Override
    public Taikhoan addTaiKhoan(Map<String, String> params, Role role) {
        String roleName = role.toString();
        RoleHandler handler = handlerMap.get(roleName.toUpperCase());
        
        if (handler == null)
            throw new IllegalArgumentException("Không hỗ trợ role: " + role);
        
        Taikhoan tk = handler.handle(params);
        return this.tkRepo.addTaiKhoan(tk);
    }
    
    @Override
    public Taikhoan addTaiKhoan(Taikhoan tk) {
        String roleName = tk.getRole();
        RoleHandler handler = handlerMap.get(roleName.toUpperCase());
        
        if (handler == null)
            throw new IllegalArgumentException("Không hỗ trợ role: " + tk.getRole());
        
        Taikhoan us = handler.handle(tk);
        return this.tkRepo.addTaiKhoan(us);
    }
    
    @Override
    public boolean authenticate(String email, String matKhau) {
        return this.tkRepo.authenticate(email, matKhau);
    }
    
    @Override
    public void deleteUser(int id) {
        this.tkRepo.deleteUser(id);
    }

    @Override
    public Taikhoan getUserById(int id) {
        return this.tkRepo.getUserById(id);
        
    }

    @Override
    public Taikhoan addOrUpdateTaiKhoan(Taikhoan tk) {
        return this.tkRepo.addOrUpdateTaiKhoan(tk);
    }

}
