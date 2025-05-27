/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Hoso;
import com.nnhp.dto.LichKhamBacSiDTO;
import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Lichkham;
import com.nnhp.repositories.BacSiRepository;
import com.nnhp.repositories.LichKhamRepository;
import jakarta.data.page.Pageable;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.query.Page;
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
    private LichKhamRepository lichKhamRepo;
    private BacSiRepository bacSiRepo;

    private static final int PAGE_SIZE = 8;

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
                predicates.add(b.equal(root.get("trangThai"), TrangThaiLichKham.valueOf(trangThai)));
            }

            // Tìm theo buổi (sáng/chiều)
            String buoi = params.get("buoi");
            if (buoi != null && !buoi.isEmpty()) {
                predicates.add(b.like(root.get("buoi"), buoi));
            }

            // Tìm theo bác sĩ
            String bacSiId = params.get("bacsiId");
            if (bacSiId != null && !bacSiId.isEmpty()) {
                predicates.add(b.equal(root.get("bacsiId").get("id"), Integer.valueOf(bacSiId)));
            }

            // Tìm theo bệnh viện chuyên khoa dịch vụ
            String bvckdvId = params.get("bvckdvId");
            if (bvckdvId != null && !bvckdvId.isEmpty()) {
                predicates.add(b.equal(root.get("benhvienchuyenkhoadichvuId").get("id"), Integer.valueOf(bvckdvId)));
            }

            String ngayHen = params.get("ngayHen");
            if (ngayHen != null) {
                try {
                    java.util.Date utilDate = Formatter.DATE_FORMATTER.parse(ngayHen);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    predicates.add(b.equal(root.get("ngayHen"), sqlDate));
                } catch (ParseException ex) {
                    Logger.getLogger(LichKhamRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    @Override
    public List<LichKhamBacSiDTO> getLichKhamByBacSi(Integer bacSiId, LocalDate date, Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<LichKhamBacSiDTO> q = b.createQuery(LichKhamBacSiDTO.class);
        Root<Lichkham> root = q.from(Lichkham.class);
        Join<Lichkham, Hoso> benhNhanJoin = root.join("hosoId");

        q.select(b.construct(
                LichKhamBacSiDTO.class,
                root.get("id"),
                benhNhanJoin.get("hoTen"),
                benhNhanJoin.get("email"),
                benhNhanJoin.get("soDienThoai"),
                benhNhanJoin.get("gioiTinh"),
                root.get("ngayHen"),
                root.get("ngayTao"),
                root.get("trangThai"),
                root.get("buoi")
        ));

        List<Predicate> predicates = new ArrayList<>();

        // Lọc theo bác sĩ
        predicates.add(b.equal(root.get("bacsiId").get("id"), bacSiId));

        // Lọc theo ngày khám
        if (date != null) {
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            predicates.add(b.between(root.get("ngayHen"), start, end));
        }

        if (params != null) {
            // Lọc theo trạng thái
            String trangThaiStr = params.get("trangThai");
            if (trangThaiStr != null && !trangThaiStr.isEmpty()) {
                try {
                    TrangThaiLichKham trangThai = TrangThaiLichKham.valueOf(trangThaiStr);
                    predicates.add(b.equal(root.get("trangThai"), trangThai));
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            }

            // Sắp xếp kết quả
            String orderBy = params.get("orderBy");
            if (orderBy != null && !orderBy.isEmpty()) {
                String sort = params.get("sort");
                Path<?> sortPath;
                if (orderBy.equals("hoTen") || orderBy.equals("email") || orderBy.equals("soDienThoai")) {
                    sortPath = benhNhanJoin.get(orderBy);
                } else {
                    sortPath = root.get(orderBy);
                }

                if (sort != null && sort.equalsIgnoreCase("desc")) {
                    q.orderBy(b.desc(sortPath));
                } else {
                    q.orderBy(b.asc(sortPath));
                }
            }
        }

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            int start = (page - 1) * PAGE_SIZE;

            query.setMaxResults(PAGE_SIZE);
            query.setFirstResult(start);
        }

        return query.getResultList();

    }

    @Override
    public List<Lichkham> getLichKhamListByUserId(int userId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lichkham> q = b.createQuery(Lichkham.class);

        Root<Lichkham> root = q.from(Lichkham.class);
        Join<Lichkham, Hoso> joinLichKham = root.join("hosoId");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(joinLichKham.get("benhnhanId").get("id"), userId));

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Lichkham> getLichKhamListByBacSiIdAndNgayKhamAndBuoi(int id, java.util.Date ngayKham, String buoi) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lichkham> q = b.createQuery(Lichkham.class);

        Root<Lichkham> root = q.from(Lichkham.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("bacsiId").get("id"), id));
        predicates.add(b.equal(root.get("ngayHen"), ngayKham));
        predicates.add(b.equal(root.get("buoi"), buoi));
//        predicates.add(b.equal(root.get("trangThai"), TrangThaiLichKham.DA_THANH_TOAN));

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Lichkham> getLichKhamListByHoSoId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Lichkham> q = builder.createQuery(Lichkham.class);
        Root<Lichkham> root = q.from(Lichkham.class);

        q.where(builder.equal(root.get("hosoId").get("id"), id));

        Query query = s.createQuery(q);
        return query.getResultList();
    }
}
