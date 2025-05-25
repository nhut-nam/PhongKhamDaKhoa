/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.pojo.Lichkham;
import com.nnhp.pojo.Lichsukhambenh;
import com.nnhp.repositories.ThongKeBacSiRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
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
public class ThongKeBacSiRepositoryImpl implements ThongKeBacSiRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Object[]> thongKeSoBenhNhanDaKham(String loaiThongKe, int nam, Integer bacSiId){
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Lichkham> root = q.from(Lichkham.class);

        Expression<Integer> groupExpression = b.function(loaiThongKe, Integer.class, root.get("ngayHen"));

        q.multiselect(
            groupExpression,           // Nhóm theo tháng, quý, năm
            b.countDistinct(root.get("id"))// Số lượng lịch khám (bệnh nhân đã khám)     
        );

        //Năm phải đúng và trạng thái hoàn thành (0) và bác sĩ tương ứng
        Predicate namPredicate = b.equal(b.function("YEAR", Integer.class, root.get("ngayHen")), nam);
        Predicate trangThaiPredicate = b.equal(root.get("trangThai"), TrangThaiLichKham.DA_KHAM_XONG);
        Predicate bacSiPredicate = b.equal(root.get("bacsiId").get("id"), bacSiId);

        q.where(b.and(namPredicate, trangThaiPredicate, bacSiPredicate));

        q.groupBy(groupExpression);
        q.orderBy(b.asc(groupExpression));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> thongKeLoaiBenhPhoBien(String loaiThongKe, int nam, Integer bacSiId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Lichsukhambenh> root = q.from(Lichsukhambenh.class);

        Path<String> chuanDoanGroup = root.get("chanDoan"); 
        Expression<Integer> groupTime = b.function(loaiThongKe, Integer.class, root.get("ngayKham"));
    
        
        q.multiselect(
            groupTime,           // Nhóm theo tháng, quý, năm
            b.count(root.get("id")),// Số lượng lịch khám (bệnh nhân đã khám)     
            root.get("chanDoan")
            );

        //Năm phải đúng và trạng thái hoàn thành (0) và bác sĩ tương ứng
        Predicate namPredicate = b.equal(b.function("YEAR", Integer.class, root.get("ngayKham")), nam);
        Predicate bacSiPredicate = b.equal(root.get("bacsiId").get("id"), bacSiId);

        q.where(b.and(namPredicate, bacSiPredicate));

        q.groupBy(chuanDoanGroup, groupTime);
        q.orderBy(b.desc(b.count(root.get("id"))));

        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
}
