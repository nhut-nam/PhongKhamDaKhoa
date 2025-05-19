/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.repositories.BenhNhanRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hoang
 */
@Repository
@Transactional
public class BenhNhanRepositoryImpl implements BenhNhanRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Benhnhan> getDsBenhNhan() {
        try (Session s = factory.getObject().openSession()) {
            Query q = s.createQuery("FROM Benhnhan");
            return q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    @Override
    public List<Benhnhan> getDsBenhNhan(Map<String, String> params) {
        try (Session s = factory.getObject().openSession()) {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Benhnhan> q = b.createQuery(Benhnhan.class);
            Root root = q.from(Benhnhan.class);
            q.select(root);
            
            List<Predicate> predicates = new ArrayList<>();
            
            // Thêm điều kiện tìm kiếm
            String kw = params.getOrDefault("kw", "");
            if (!kw.isEmpty()) {
                predicates.add(b.or(
                    b.like(root.get("hoNguoiDung"), String.format("%%%s%%", kw)),
                    b.like(root.get("tenNguoiDung"), String.format("%%%s%%", kw)),
                    b.like(root.get("email"), String.format("%%%s%%", kw))
                ));
            }
            
            // Nếu có điều kiện tìm kiếm, áp dụng vào câu truy vấn
            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(Predicate[]::new));
            }
            
            // Sắp xếp
            String orderBy = params.getOrDefault("orderBy", "id");
            String sort = params.getOrDefault("sort", "asc");
            
            if (sort.equals("asc")) {
                q.orderBy(b.asc(root.get(orderBy)));
            } else {
                q.orderBy(b.desc(root.get(orderBy)));
            }
            
            Query query = s.createQuery(q);
            return query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    @Override
    public Benhnhan getBenhNhanById(int id) {
        try (Session s = factory.getObject().openSession()) {
            try {
                Query q = s.createQuery("FROM Benhnhan WHERE id = :id");
                q.setParameter("id", id);
                return (Benhnhan) q.getSingleResult();
            } catch (NoResultException ex) {
                return null;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Benhnhan addOrUpdateBenhNhan(Benhnhan bn) {
        try (Session s = factory.getObject().openSession()) {
            try {
                s.beginTransaction();
                if (bn.getId() == null) {
                    s.persist(bn);
                } else {
                    s.merge(bn);
                }
                s.getTransaction().commit();
                return bn;
            } catch (Exception ex) {
                s.getTransaction().rollback();
                ex.printStackTrace();
                return null;
            }
        }
    }
} 