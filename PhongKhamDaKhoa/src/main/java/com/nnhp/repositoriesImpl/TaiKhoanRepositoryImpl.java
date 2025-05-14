/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Quantri;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositories.TaiKhoanRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author namnh
 */
@Transactional
@Repository
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public Taikhoan getUserByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findByEmail", Taikhoan.class);
        q.setParameter("email", email);
        Taikhoan account = (Taikhoan) q.getSingleResult();
        return account;
    }
    
    @Override
    public Taikhoan getTaikhoanTest(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findById", Taikhoan.class);
        q.setParameter("id", 1);
        return (Taikhoan) q.getSingleResult();
    }

    @Override
    public List<Taikhoan> getTaiKhoanList() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findAll", Taikhoan.class);
        return q.getResultList();
    }

    @Override
    public Taikhoan addTaiKhoan(Taikhoan tk) {  
        Session s = this.factory.getObject().getCurrentSession();
        
        if (tk.getId() == null) {
            s.persist(tk);
        } else {
            s.merge(tk);
        }
        s.refresh(tk);
        
        return tk;
    }

    @Override
    public boolean authenticate(String email, String matKhau) {
        Taikhoan tk = this.getUserByEmail(email);
        return this.passwordEncoder.matches(matKhau, tk.getMatKhau());
    }

    @Override
    public void deleteUser(int id) {
       Session s = this.factory.getObject().getCurrentSession();
       Taikhoan tk = this.getUserById(id);
       s.remove(tk);
    }

    @Override
    public Taikhoan getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findById", Taikhoan.class);
        q.setParameter("id", id);
        return (Taikhoan)q.getSingleResult();
    }
}
