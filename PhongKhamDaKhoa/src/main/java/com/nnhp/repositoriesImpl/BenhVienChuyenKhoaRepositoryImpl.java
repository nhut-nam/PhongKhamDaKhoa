/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Benhvienchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.repositories.BenhVienChuyenKhoaRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
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
} 