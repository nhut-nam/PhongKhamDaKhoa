/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Bacsidichvu;
import com.nnhp.pojo.Benhvienchuyenkhoa;
import com.nnhp.repositories.BacSiDichVuRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
public class BacSiDichVuRepositoryImpl implements BacSiDichVuRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Bacsidichvu> getBacSiDichVuByBenhVienChuyenKhoaDichVu(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Bacsidichvu> q = b.createQuery(Bacsidichvu.class);
        
        Root<Bacsidichvu> root = q.from(Bacsidichvu.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("benhvienchuyenkhoadichvuId").get("id"), id));
        
        q.where(predicates.toArray(Predicate[]::new));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
}
