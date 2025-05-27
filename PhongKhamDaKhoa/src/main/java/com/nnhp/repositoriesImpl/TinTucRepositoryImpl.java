/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.TaiKhoanTinTuc;
import com.nnhp.pojo.Tintuc;
import com.nnhp.repositories.TinTucRepository;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author namnh
 */
@Repository
@Transactional
public class TinTucRepositoryImpl implements TinTucRepository {

    private final static int LIMIT = 10;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Tintuc getTinTucById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("TinTuc.findById", Tintuc.class);
        q.setParameter("id", id);
        return (Tintuc) q.getSingleResult();
    }

    @Override
    public List<Tintuc> getTinTucList(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Tintuc> q = b.createQuery(Tintuc.class);
        Root<Tintuc> root = q.from(Tintuc.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            if (params.containsKey("tieuDe")) {
                String tieuDe = params.get("tieuDe");
                if (tieuDe != null && !tieuDe.isEmpty()) {
                    predicates.add(b.like(root.get("tieuDe"), "%" + tieuDe + "%"));
                }
            }

            if (params.containsKey("noiDung")) {
                String noiDung = params.get("noiDung");
                if (noiDung != null && !noiDung.isEmpty()) {
                    predicates.add(b.like(root.get("noiDung"), "%" + noiDung + "%"));
                }
            }

            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(new Predicate[0]));
            }
        }

        q.orderBy(b.desc(root.get("ngayDang")));

        return s.createQuery(q)
                .setMaxResults(LIMIT)
                .getResultList();
    }

    @Override
    public List<Tintuc> findTop10TinTucChuaGanUser(int userId) {
        Session session = factory.getObject().getCurrentSession();

        try {
            String sql = "SELECT tt.* "
                    + "FROM (SELECT * FROM tintuc ORDER BY ngay_dang DESC LIMIT 10) AS tt "
                    + "LEFT JOIN taikhoantintuc tkt ON tt.id = tkt.tintuc_id AND tkt.taikhoan_id = :userId "
                    + "WHERE tkt.tintuc_id IS NULL";

            NativeQuery<Tintuc> query = session.createNativeQuery(sql, Tintuc.class);
            query.setParameter("userId", userId);

            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error executing native query: " + e.getMessage());
            throw new RuntimeException("Failed to fetch latest news not assigned to user", e);
        }
    }

    @Override
    public List<Tintuc> getTinTucByUserId(int userId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Tintuc> cq = cb.createQuery(Tintuc.class);

        Root<TaiKhoanTinTuc> root = cq.from(TaiKhoanTinTuc.class);
        Join<TaiKhoanTinTuc, Tintuc> ttJoin = root.join("tintucId");
        Predicate userCondition = cb.equal(root.get("taikhoanId").get("id"), userId);
        cq.where(userCondition);
        cq.select(ttJoin);
        cq.orderBy(cb.desc(ttJoin.get("ngayDang")));

        List<Tintuc> result = s.createQuery(cq)
                .setMaxResults(LIMIT)
                .getResultList();

        return result;
    }

    @Override
    public Tintuc addOrUpdateTinTuc(Tintuc tt) {
        Session s = this.factory.getObject().getCurrentSession();
        if (tt.getId() == null) {
            s.persist(tt);
        } else {
            s.merge(tt);
        }
        return tt;
    }

    @Override
    public List<Tintuc> getTop10TintucMoiNhat() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Tintuc> cq = cb.createQuery(Tintuc.class);
        Root<Tintuc> root = cq.from(Tintuc.class);

        cq.orderBy(cb.desc(root.get("ngayDang")));

        TypedQuery<Tintuc> query = s.createQuery(cq);
        query.setMaxResults(LIMIT);

        return query.getResultList();
    }

}
