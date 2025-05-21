/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhvien;
import com.nnhp.repositories.BenhVienRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import jakarta.persistence.Query;
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
public class BenhVienRepositoryImpl implements BenhVienRepository{
    private static final int PAGE_SIZE = 8;
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Benhvien> getDsBenhVien(Map<String, String> params) {
      Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Benhvien> q = b.createQuery(Benhvien.class);
        Root root = q.from(Benhvien.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("tenBenhVien");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tenBenhVien"), String.format("%%%s%%", kw)));
            }
            
            String diaChi = params.get("diaChi");
            if (diaChi != null && !diaChi.isEmpty()) {
                predicates.add(b.like(root.get("diaChi"), String.format("%%%s%%", diaChi)));
            }

            q.where(predicates.toArray(Predicate[]::new));

            String orderBy = params.get("orderBy");
            if (orderBy != null && !orderBy.isEmpty()) {
                String sort = params.get("sort");
                if (sort != null && sort.equalsIgnoreCase("desc")) {
                    q.orderBy(b.desc(root.get(orderBy)));
                } else {
                    q.orderBy(b.asc(root.get(orderBy)));
                }
            }
        }
        

        Query query = s.createQuery(q);
        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            int start = (page - 1) * PAGE_SIZE;

            query.setMaxResults(PAGE_SIZE);
            query.setFirstResult(start);
        }
        return query.getResultList();
    }

    @Override
    public Benhvien getBenhVienById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Benhvien.class, id);
    }

    @Override
    public List<Benhvien> getAllBenhVien() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Benhvien.findAll", Benhvien.class);
        return q.getResultList();
    }
    
    @Override
    public Benhvien addOrUpdateBenhVien(Benhvien bv) {
        Session s = this.factory.getObject().getCurrentSession();
        if (bv.getId() == null) {
            s.persist(bv);
        } else {
            s.merge(bv);
        }
        return bv;
    }
    
    @Override
    public void deleteBenhVien(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Benhvien bv = this.getBenhVienById(id);
        s.remove(bv);
    }
}
