/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Dichvu;
import com.nnhp.repositories.DichVuRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hoang
 */
@Repository
@Transactional
public class DichVuRepositoryImpl implements DichVuRepository {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Dichvu> getDsDichVu(Map<String, String> params) {
        try {
            // Sử dụng JPQL với LEFT JOIN FETCH thay vì Criteria API
            String jpql = "SELECT DISTINCT d FROM Dichvu d LEFT JOIN FETCH d.benhvienchuyenkhoaId bvck";
            
            // Thêm điều kiện nếu có tham số tìm kiếm
            if (params != null) {
                String tenDichVu = params.get("tenDichVu");
                if (tenDichVu != null && !tenDichVu.isEmpty()) {
                    jpql += " WHERE LOWER(d.tenDichVu) LIKE LOWER(:tenDichVu)";
                }
                
                String loaiDichVu = params.get("loaiDichVu");
                if (loaiDichVu != null && !loaiDichVu.isEmpty()) {
                    if (jpql.contains("WHERE")) {
                        jpql += " AND d.loaiDichVu = :loaiDichVu";
                    } else {
                        jpql += " WHERE d.loaiDichVu = :loaiDichVu";
                    }
                }
                
                // Thêm sắp xếp mặc định
                String orderBy = params.get("orderBy");
                String sort = params.get("sort");
                
                if (orderBy != null && !orderBy.isEmpty()) {
                    jpql += " ORDER BY d." + orderBy;
                    if (sort != null && sort.equalsIgnoreCase("desc")) {
                        jpql += " DESC";
                    } else {
                        jpql += " ASC";
                    }
                } else {
                    jpql += " ORDER BY d.id DESC";
                }
            } else {
                jpql += " ORDER BY d.id DESC";
            }
            
            Query query = this.entityManager.createQuery(jpql);
            
            // Thiết lập tham số nếu có
            if (params != null) {
                String tenDichVu = params.get("tenDichVu");
                if (tenDichVu != null && !tenDichVu.isEmpty()) {
                    query.setParameter("tenDichVu", "%" + tenDichVu + "%");
                }
                
                String loaiDichVu = params.get("loaiDichVu");
                if (loaiDichVu != null && !loaiDichVu.isEmpty()) {
                    query.setParameter("loaiDichVu", Short.parseShort(loaiDichVu));
                }
            }
            
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Dichvu getDichVuById(int id) {
        try {
            String jpql = "SELECT d FROM Dichvu d LEFT JOIN FETCH d.benhvienchuyenkhoaId WHERE d.id = :id";
            Query query = this.entityManager.createQuery(jpql);
            query.setParameter("id", id);
            
            List<Dichvu> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Dichvu addOrUpdateDichVu(Dichvu dichVu) {
        try {
            if (dichVu.getId() == null) {
                this.entityManager.persist(dichVu);
                return dichVu;
            } else {
                return this.entityManager.merge(dichVu);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteDichVu(int id) {
        try {
            Dichvu dichVu = this.getDichVuById(id);
            if (dichVu != null) {
                this.entityManager.remove(dichVu);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} 