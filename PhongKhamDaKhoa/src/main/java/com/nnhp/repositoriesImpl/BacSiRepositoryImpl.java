/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Bacsithuocchuyenkhoa;
import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositories.BacSiRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class BacSiRepositoryImpl implements BacSiRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Bacsi> getDsBacSi(Map<String, String> params) {
       Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Bacsi> q = b.createQuery(Bacsi.class);
        Root root = q.from(Bacsi.class);
        root.fetch("bacsithuocchuyenkhoaCollection", JoinType.LEFT);
        q.select(root).distinct(true);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            Join<Bacsi, Benhvien> bvJoin = root.join("benhvienId");
            Expression<String> fullName = b.concat(b.concat(root.get("hoNguoiDung")," "),root.get("tenNguoiDung"));
            String kw = params.get("kw");
            
            if (kw != null && !kw.isEmpty()) {
               Predicate tenLike = b.like(fullName, String.format("%%%s%%", kw));
               Predicate bvLike = b.like(bvJoin.get("tenBenhVien"), String.format("%%%s%%", kw));
               predicates.add(b.or(tenLike,bvLike));
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
    public Bacsi getBacSiById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Bacsi.class, id);
    }

    @Override
    public Bacsi addOrUpdateBacSi(Bacsi b) {
        Session s = this.factory.getObject().getCurrentSession();
        
        if (b.getBenhvienId()!= null && b.getBenhvienId().getId() != null) {
            Benhvien benhVien = s.get(Benhvien.class, b.getBenhvienId().getId());
            b.setBenhvienId(benhVien);
        }

        // Thêm hoặc cập nhật
        if (b.getId() == null) {
            s.persist(b);
        } else {
            s.merge(b);
        }

        return b;
        
//        if (b.getId() == null) {
//            s.persist(b);
//        } else {
//            s.merge(b);
//        }
//
//        return b;
    }

    @Override
    public void deleteBacSi(int id) {
    Session s = this.factory.getObject().getCurrentSession();
        Bacsi b = this.getBacSiById(id);
        s.remove(b);
    }

    @Override
    public List<Chuyenkhoa> getChuyenKhoaByBacSiId(int id) {
        Bacsi bacSi = this.getBacSiById(id);
        List<Chuyenkhoa> dsChuyenKhoa = new ArrayList<>();

        for (Bacsithuocchuyenkhoa bck : bacSi.getBacsithuocchuyenkhoaCollection()) {
            dsChuyenKhoa.add(bck.getChuyenkhoaId());
        }

    return dsChuyenKhoa;
    }
}
