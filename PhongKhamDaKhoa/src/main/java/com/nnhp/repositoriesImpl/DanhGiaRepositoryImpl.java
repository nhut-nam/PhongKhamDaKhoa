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
import jakarta.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.Date;
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
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Danhgia> cq = cb.createQuery(Danhgia.class);

        Root<Danhgia> root = cq.from(Danhgia.class);
        Subquery<Date> subquery = cq.subquery(Date.class);
        Root<Danhgia> subRoot = subquery.from(Danhgia.class);
        subquery.select(cb.max(subRoot.get("ngayTao")).as(Date.class))
                .where(cb.equal(subRoot.get("bacsiId").get("id"), id),
                        cb.equal(subRoot.get("taikhoanId").get("id"), root.get("taikhoanId").get("id")));

        cq.select(root)
                .where(cb.equal(root.get("bacsiId").get("id"), id),
                        cb.equal(root.get("ngayTao"), subquery));

        List<Danhgia> result = s.createQuery(cq).getResultList();
        return result;
    }

    @Override
    public Double averageSoSao(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<Danhgia> root = cq.from(Danhgia.class);

        Subquery<Date> subquery = cq.subquery(Date.class);
        Root<Danhgia> subRoot = subquery.from(Danhgia.class);
        subquery.select(cb.max(subRoot.get("ngayTao")).as(Date.class))
                .where(cb.equal(subRoot.get("bacsiId").get("id"), id),
                        cb.equal(subRoot.get("taikhoanId").get("id"), root.get("taikhoanId").get("id")));

        cq.select(cb.avg(root.get("soSao")))
                .where(
                        cb.equal(root.get("bacsiId").get("id"), id),
                        cb.equal(root.get("ngayTao"), subquery)
                );

        return s.createQuery(cq).getSingleResult();
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

    @Override
    public Danhgia getDanhGiaById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Danhgia.findById", Danhgia.class);
        q.setParameter("id", id);
        return (Danhgia) q.getSingleResult();
    }

}
