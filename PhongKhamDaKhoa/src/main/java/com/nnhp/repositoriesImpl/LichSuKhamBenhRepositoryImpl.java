/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Lichsukhambenh;
import com.nnhp.repositories.LichSuKhamBenhRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
public class LichSuKhamBenhRepositoryImpl implements LichSuKhamBenhRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Lichsukhambenh> getLichSuKhamBenhByUser(int hoSoId) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Lichsukhambenh> cq = cb.createQuery(Lichsukhambenh.class);
        Root<Lichsukhambenh> root = cq.from(Lichsukhambenh.class);

        cq.select(root).where(cb.equal(root.get("hosoId").get("id"), hoSoId));

        return session.createQuery(cq).getResultList();
    }

    @Override
    public void updateLichSuKhamBenh(Lichsukhambenh lichSu) {
        Session session = this.factory.getObject().getCurrentSession();
        
         session.merge(lichSu);
        
    }

    @Override
    public Lichsukhambenh getLichSuKhamBenhById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Lichsukhambenh.class, id);
    }

}
