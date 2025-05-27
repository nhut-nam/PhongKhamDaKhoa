/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.enums.TrangThaiBangCap;
import com.nnhp.pojo.Bangcap;
import com.nnhp.repositories.BangCapRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public List<Bangcap> getListBangCapByBacSiId(int bacSiId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Bangcap> cq = cb.createQuery(Bangcap.class);
        Root<Bangcap> root = cq.from(Bangcap.class);

        cq.select(root)
                .where(cb.equal(root.get("bacsiId").get("id"), bacSiId));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public Bangcap getBangCapById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Bangcap> cq = cb.createQuery(Bangcap.class);
        Root<Bangcap> root = cq.from(Bangcap.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        return session.createQuery(cq).getSingleResult();
    }

    @Override
    public Bangcap addOrUpdateBangCap(Bangcap bc) {
        Session s = this.factory.getObject().getCurrentSession();
        if (bc.getId() == null) {
            s.persist(bc);
        } else {
            s.merge(bc);
        }
        return bc;
    }

    @Override
    public List<Bangcap> getListBangCap(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Bangcap> cq = cb.createQuery(Bangcap.class);
        Root<Bangcap> root = cq.from(Bangcap.class);

        List<Predicate> predicates = new ArrayList<>();

        // Lọc theo ngày cấp
        if (params != null) {

            if (params.get("ngayCap") != null) {
                try {
                    Date ngayCap = new SimpleDateFormat("yyyy-MM-dd").parse(params.get("ngayCap"));
                    predicates.add(cb.equal(root.get("ngayCap"), ngayCap));
                } catch (ParseException e) {
                    e.printStackTrace(); // hoặc log lỗi
                }
            }

            // Lọc theo ngày hết hạn
            if (params.get("ngayHetHan") != null) {
                try {
                    Date ngayHetHan = new SimpleDateFormat("yyyy-MM-dd").parse(params.get("ngayHetHan"));
                    predicates.add(cb.equal(root.get("ngayHetHan"), ngayHetHan));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // Lọc theo trạng thái
            if (params.get("trangThai") != null) {
                try {
                    TrangThaiBangCap trangThai = TrangThaiBangCap.valueOf(params.get("trangThai"));  // enum
                    predicates.add(cb.equal(root.get("trangThai"), trangThai));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }

            // Lọc theo bác sĩ
            if (params.get("bacsiId") != null) {
                try {
                    int bacsiId = Integer.parseInt(params.get("bacsiId"));
                    predicates.add(cb.equal(root.get("bacsiId").get("id"), bacsiId));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        cq.where(predicates.toArray(new Predicate[0]));
        if (params.get("orderBy") != null) {
            String orderBy = params.get("orderBy");
            String sortDirection = params.getOrDefault("sort", "asc");

            Path<?> sortPath = root.get(orderBy);
            if ("desc".equalsIgnoreCase(sortDirection)) {
                cq.orderBy(cb.desc(sortPath));
            } else {
                cq.orderBy(cb.asc(sortPath));
            }
        }

        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

}
