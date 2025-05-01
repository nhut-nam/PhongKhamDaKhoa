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
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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
    
    @Override
    public Taikhoan getUserByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findByEmail", Taikhoan.class);
        q.setParameter("username", email);
        Taikhoan account = (Taikhoan) q.getSingleResult();
        if (account instanceof Benhnhan) {
            System.out.println("Benh Nhan");
        }

        return null;
    }
    
    @Override
    public Taikhoan getTaikhoanTest(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findById", Taikhoan.class);
        q.setParameter("id", 1);
        return (Taikhoan) q.getSingleResult();
    }
}
