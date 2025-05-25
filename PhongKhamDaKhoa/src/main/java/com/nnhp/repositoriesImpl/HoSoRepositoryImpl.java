/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Map;
import com.nnhp.pojo.Hoso;
import com.nnhp.pojo.Lichkham;
import com.nnhp.repositories.HoSoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
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
=======
 * @author namnh
 */
@Repository
@Transactional
public class HoSoRepositoryImpl implements HoSoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Hoso> getDsHoSo(Map<String, String> params) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Hoso> q = b.createQuery(Hoso.class);
            Root root = q.from(Hoso.class);
            q.select(root);

            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();

                // Tìm theo bệnh nhân ID
                String benhnhanId = params.get("benhnhanId");
                if (benhnhanId != null && !benhnhanId.isEmpty()) {
                    predicates.add(b.equal(root.get("benhnhanId").get("id"), Integer.parseInt(benhnhanId)));
                }

                // Tìm theo hoten,email,sdt
                String email = params.get("email");
                String hoTen = params.get("hoTen");
                String sdt = params.get("soDienThoai");
                String diaChi = params.get("diaChi");
                
                 if (email != null && !email.isEmpty()) {
                    predicates.add(b.like(root.get("email"), "%" + email + "%"));
                }
                if (hoTen != null && !hoTen.isEmpty()) {
                    predicates.add(b.like(root.get("hoTen"), "%" + hoTen + "%"));
                }
                if (sdt != null && !sdt.isEmpty()) {
                    predicates.add(b.like(root.get("soDienThoai"), "%" + sdt + "%"));
                }
                if (diaChi != null && !diaChi.isEmpty()) {
                    predicates.add(b.like(root.get("diaChi"), "%" + diaChi + "%"));
                }

                q.where(predicates.toArray(new Predicate[0]));

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
                    // Mặc định sắp xếp theo ngày tạo giảm dần (mới nhất lên đầu)
                    q.orderBy(b.desc(root.get("ngayTao")));
                }
            }

            Query query = s.createQuery(q);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Hoso getHoSoById(int id) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            return s.get(Hoso.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Hoso addOrUpdateHoSo(Hoso hoSo) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            if (hoSo.getId() == null) {
                s.persist(hoSo);
                return hoSo;
            } else {
                return (Hoso) s.merge(hoSo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        }

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
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Hoso hoSo = this.getHoSoById(id);
            if (hoSo != null) {
                s.remove(hoSo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean kiemTraHoSoTonTai(int id) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Hoso hoSo = s.get(Hoso.class, id);
            return hoSo != null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Hoso updateHoSo(Hoso hs) {
        if (hs == null) 
            return null;
        Session s = this.factory.getObject().getCurrentSession();
        s.merge(hs);
        return hs;
    }

    @Override
    public List<Hoso> getHoSoByBacSi(int bacSiId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Hoso> query = builder.createQuery(Hoso.class);
        Root<Lichkham> lichkhamRoot = query.from(Lichkham.class);

        // Join từ Lichkham -> Hoso
        Join<Lichkham, Hoso> hosoJoin = lichkhamRoot.join("hosoId");

        // Thêm điều kiện lọc theo bacSiId
        query.select(hosoJoin).distinct(true)
                .where(builder.equal(lichkhamRoot.get("bacsiId").get("id"), bacSiId));

        return s.createQuery(query).getResultList();
    }
}

