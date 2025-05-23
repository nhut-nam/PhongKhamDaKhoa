/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.BenhVienChuyenKhoaDichVu;
import com.nnhp.repositories.BenhVienChuyenKhoaDichVuRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
public class BenhVienChuyenKhoaDichVuRepositoryImpl implements BenhVienChuyenKhoaDichVuRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVu(Map<String, String> params) {
        try {
            String jpql = "SELECT b FROM BenhVienChuyenKhoaDichVu b " +
                         "LEFT JOIN FETCH b.dichvuId " +
                         "LEFT JOIN FETCH b.benhvienchuyenkhoaId";
            
            Query query = this.entityManager.createQuery(jpql);
            
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public BenhVienChuyenKhoaDichVu getBenhVienChuyenKhoaDichVuById(int id) {
        try {
            return this.entityManager.find(BenhVienChuyenKhoaDichVu.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BenhVienChuyenKhoaDichVu> getDsBenhVienChuyenKhoaDichVuByBenhVienChuyenKhoaId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("BenhVienChuyenKhoaDichVu.findByBenhVienChuyenKhoa", BenhVienChuyenKhoaDichVu.class);
        q.setParameter("benhVienChuyenKhoaId", id);
        List<BenhVienChuyenKhoaDichVu> results = q.getResultList();
        return results;
    }
} 