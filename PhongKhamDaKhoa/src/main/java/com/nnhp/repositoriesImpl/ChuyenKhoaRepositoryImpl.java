/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhvienchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.repositories.ChuyenKhoaRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ChuyenKhoaRepositoryImpl implements ChuyenKhoaRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Chuyenkhoa> getDsChuyenKhoa(Map<String, String> params) {
       Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chuyenkhoa> q = b.createQuery(Chuyenkhoa.class);
        Root root = q.from(Chuyenkhoa.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("tenChuyenKhoa");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tenChuyenKhoa"), String.format("%%%s%%", kw)));
            }

            q.where(predicates.toArray(Predicate[]::new));

            String orderBy = params.get("orderBy");
            if (orderBy != null && !orderBy.isEmpty()) {
                q.orderBy(b.asc(root.get(orderBy)));
            }
        }

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Chuyenkhoa getChuyenKhoaById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Chuyenkhoa.class, id);
    }

    @Override
    public Chuyenkhoa addOrUpdateChuyenKhoa(Chuyenkhoa c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() == null) {
            s.persist(c);
        } else {
            s.merge(c);
        }

        return c;
    }

    @Override
    public void deleteChuyenKhoa(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Chuyenkhoa c = this.getChuyenKhoaById(id);
        s.remove(c);
    }

    @Override
    public List<Chuyenkhoa> getChuyenKhoaByBenhVien(int bvId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chuyenkhoa> q = b.createQuery(Chuyenkhoa.class);
        Root<Benhvienchuyenkhoa> root = q.from(Benhvienchuyenkhoa.class);
        Join<Benhvienchuyenkhoa, Chuyenkhoa> ckJoin = root.join("chuyenkhoaId");
        q.select(ckJoin).where(b.equal(root.get("benhvienId").get("id"), bvId)).distinct(true);
        
        return s.createQuery(q).getResultList();
    }
    
}
