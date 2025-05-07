/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhvien;
import com.nnhp.repositories.BenhVienRepository;
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
public class BenhVienRepositoryImpl implements BenhVienRepository {
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Benhvien getBenhVienById(Integer id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Benhvien.findById", Benhvien.class);
        q.setParameter("id", id);
        return (Benhvien) q.getSingleResult();
    }
    
}
