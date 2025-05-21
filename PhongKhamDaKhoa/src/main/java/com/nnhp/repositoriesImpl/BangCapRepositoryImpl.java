/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Bangcap;
import com.nnhp.repositories.BangCapRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author namnh
 */
@Repository
@Transactional
public class BangCapRepositoryImpl implements BangCapRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Bangcap addBangCap(Bangcap bc) {
        if (bc == null) {
            return null;
        }
        Session s = this.factory.getObject().getCurrentSession();

        s.persist(bc);

        s.refresh(bc);
        return bc;
    }
    
}
