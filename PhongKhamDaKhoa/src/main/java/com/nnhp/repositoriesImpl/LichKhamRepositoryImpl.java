/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Lichkham;
import com.nnhp.repositories.LichKhamRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
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
public class LichKhamRepositoryImpl implements LichKhamRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Lichkham> getDsLichKham(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lichkham> q = b.createQuery(Lichkham.class);
        Root root = q.from(Lichkham.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            // Tìm theo trạng thái
            String trangThai = params.get("trangThai");
            if (trangThai != null && !trangThai.isEmpty()) {
                predicates.add(b.equal(root.get("trangThai"), Short.parseShort(trangThai)));
            }
            
            // Tìm theo buổi (sáng/chiều)
            String buoi = params.get("buoi");
            if (buoi != null && !buoi.isEmpty()) {
                predicates.add(b.like(root.get("buoi"), buoi));
            }
            
            // Tìm theo bác sĩ
            String bacSiId = params.get("bacsiId");
            if (bacSiId != null && !bacSiId.isEmpty()) {
                predicates.add(b.equal(root.get("bacsiId").get("id"), Integer.parseInt(bacSiId)));
            }
            
            // Tìm theo bệnh viện chuyên khoa dịch vụ
            String bvckdvId = params.get("bvckdvId");
            if (bvckdvId != null && !bvckdvId.isEmpty()) {
                predicates.add(b.equal(root.get("benhvienchuyenkhoadichvuId").get("id"), Integer.parseInt(bvckdvId)));
            }

            q.where(predicates.toArray(Predicate[]::new));

            // Sắp xếp kết quả
            String orderBy = params.get("orderBy");
            if (orderBy != null && !orderBy.isEmpty()) {
                String sort = params.get("sort");
                if (sort != null && sort.equalsIgnoreCase("desc")) {
                    q.orderBy(b.desc(root.get(orderBy)));
                } else {
                    q.orderBy(b.asc(root.get(orderBy)));
                }
            } else {
                // Mặc định sắp xếp theo ngày hẹn giảm dần (mới nhất lên đầu)
                q.orderBy(b.desc(root.get("ngayHen")));
            }
        }

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Lichkham getLichKhamById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Lichkham.class, id);
    }

    @Override
    public Lichkham addOrUpdateLichKham(Lichkham lichKham) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            if (lichKham.getId() == null) {
                s.persist(lichKham);
                return lichKham;
            } else {
                return (Lichkham) s.merge(lichKham);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteLichKham(int id) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Lichkham lichKham = this.getLichKhamById(id);
            if (lichKham != null) {
                s.remove(lichKham);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} 