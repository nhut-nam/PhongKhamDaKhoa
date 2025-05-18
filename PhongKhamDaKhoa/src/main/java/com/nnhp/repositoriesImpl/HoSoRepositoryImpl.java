/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Hoso;
import com.nnhp.repositories.HoSoRepository;
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
 * @author namnh
 */
@Repository
@Transactional
public class HoSoRepositoryImpl implements HoSoRepository {
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Hoso addHoSo(Hoso hs) {
        Session s = this.factory.getObject().getCurrentSession();

        s.persist(hs);

        s.refresh(hs);
        return hs;
    }

    @Override
    public List<Hoso> getHoSoList(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Hoso> q = b.createQuery(Hoso.class);
        Root<Hoso> root = q.from(Hoso.class);
        q.select(root).where(b.equal(root.get("benhnhanId").get("id"), id));
        return s.createQuery(q).getResultList();
    }

    @Override
    public void deleteHoSo(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Hoso hs = this.getHoSoById(id);
        s.remove(hs);
    }

    @Override
    public Hoso getHoSoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Hoso.class, id);
    }
}
