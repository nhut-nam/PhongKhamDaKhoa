/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Danhgia;
import com.nnhp.repositories.DanhGiaRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author namnh
 */
@Repository
@Transactional
public class DanhGiaRepositoryImpl implements DanhGiaRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Danhgia> getDanhGiaByBacSiId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Danhgia> q = b.createQuery(Danhgia.class);
        
        Root<Danhgia> root = q.from(Danhgia.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("bacsiId").get("id"), id));
        
        q.where(predicates.toArray(Predicate[]::new));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Double averageSoSao(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Double> q = b.createQuery(Double.class);
        Root<Danhgia> root = q.from(Danhgia.class);
        
        Expression<Double> avgSoSao = b.avg(root.get("soSao"));
        
        q.select(avgSoSao).where(b.equal(root.get("bacsiId").get("id"), id));
        
        Double result = s.createQuery(q).getSingleResult();
        return result;
    }

    @Override
    public Danhgia addOrUpdateDanhGia(Danhgia dg) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            if (dg.getId() == null) {
                s.persist(dg);
                return dg;
            } else {
                return (Danhgia) s.merge(dg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
