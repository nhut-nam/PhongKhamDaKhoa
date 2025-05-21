/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Benhvienchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositories.BenhVienChuyenKhoaRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class BenhVienChuyenKhoaRepositoryImpl implements BenhVienChuyenKhoaRepository {
    private static final int PAGE_SIZE = 8;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Benhvienchuyenkhoa> getAll() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Benhvienchuyenkhoa> q = b.createQuery(Benhvienchuyenkhoa.class);
        
        Root<Benhvienchuyenkhoa> root = q.from(Benhvienchuyenkhoa.class);
        q.select(root);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Benhvienchuyenkhoa getById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Benhvienchuyenkhoa.class, id);
    }

    @Override
    public Benhvienchuyenkhoa addOrUpdate(Benhvienchuyenkhoa bvck) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (bvck.getId() == null)
                s.persist(bvck);
            else
                s.merge(bvck);
            
            return bvck;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Benhvienchuyenkhoa bvck = this.getById(id);
        
        try {
            if (bvck != null) {
                s.remove(bvck);
                return true;
            }
            return false;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Benhvienchuyenkhoa> getByBenhVienId(int benhVienId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Benhvienchuyenkhoa> q = b.createQuery(Benhvienchuyenkhoa.class);
        
        Root<Benhvienchuyenkhoa> root = q.from(Benhvienchuyenkhoa.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("benhvienId").get("id"), benhVienId));
        
        q.where(predicates.toArray(Predicate[]::new));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Benhvienchuyenkhoa> getByChuyenKhoaId(int chuyenKhoaId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Benhvienchuyenkhoa> q = b.createQuery(Benhvienchuyenkhoa.class);
        
        Root<Benhvienchuyenkhoa> root = q.from(Benhvienchuyenkhoa.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("chuyenkhoaId").get("id"), chuyenKhoaId));
        
        q.where(predicates.toArray(Predicate[]::new));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Boolean tonTaiBenhVienIdChuyenKhoaId(int benhVienId, int chuyenKhoaId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        
        Root<Benhvienchuyenkhoa> root = q.from(Benhvienchuyenkhoa.class);
        q.select(b.count(root));
        
        q.where(
        b.equal(root.get("benhvienId").get("id"), benhVienId),
        b.equal(root.get("chuyenkhoaId").get("id"), chuyenKhoaId)
        );
        
        Long count = s.createQuery(q).getSingleResult();
        return count>0;
    }

    @Override
    public List<Benhvienchuyenkhoa> getDsBenhVien(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Benhvienchuyenkhoa> q = b.createQuery(Benhvienchuyenkhoa.class);
        Root<Benhvienchuyenkhoa> root = q.from(Benhvienchuyenkhoa.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            // Lọc theo từ khóa (email, họ, tên)
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                String pattern = "%" + kw.toLowerCase() + "%";
                List<Predicate> orPredicates = new ArrayList<>();
                orPredicates.add(b.like(b.lower(root.get("chuyenkhoaId").get("tenChuyenKhoa")), pattern));
                predicates.add(b.or(orPredicates.toArray(new Predicate[0])));
            }
            
            // Sắp xếp kết quả
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
        q.where(predicates.toArray(new Predicate[0]));
        Query query = s.createQuery(q);
        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            int start = (page - 1) * PAGE_SIZE;

            query.setMaxResults(PAGE_SIZE);
            query.setFirstResult(start);
        }
        return query.getResultList();
    }
} 