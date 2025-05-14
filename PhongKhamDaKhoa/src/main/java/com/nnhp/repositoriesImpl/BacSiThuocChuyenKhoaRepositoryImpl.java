/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Bacsithuocchuyenkhoa;
import com.nnhp.repositories.BacSiThuocChuyenKhoaRepository;
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
public class BacSiThuocChuyenKhoaRepositoryImpl implements BacSiThuocChuyenKhoaRepository {
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Bacsithuocchuyenkhoa addBacSiThuocChuyenKhoa(Bacsithuocchuyenkhoa bsck) {
        if (bsck == null) return null;
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(bsck);
        
        s.refresh(bsck);
        
        return bsck;
    }
    
}
