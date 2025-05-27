/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.enums.Role;
import com.nnhp.enums.TrangThaiTaiKhoan;
import com.nnhp.pojo.Benhnhan;
import com.nnhp.pojo.Hoso;
import com.nnhp.pojo.Lichkham;
import com.nnhp.pojo.Quantri;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.ThongBao;
import com.nnhp.repositories.TaiKhoanRepository;
import com.nnhp.services.handler.RoleHandler;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author namnh
 */
@Transactional
@Repository
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private Map<String, RoleHandler> handlerMap;

    @Override
    public Taikhoan getUserByEmailByTrangThai(String email, TrangThaiTaiKhoan trangThai) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findByEmailByTrangThai", Taikhoan.class);
        q.setParameter("email", email);
        q.setParameter("trangThai", trangThai);
        List<Taikhoan> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Taikhoan getUserByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findByEmail", Taikhoan.class);
        q.setParameter("email", email);
        List<Taikhoan> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Taikhoan getTaikhoanTest(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findById", Taikhoan.class);
        q.setParameter("id", 1);
        return (Taikhoan) q.getSingleResult();
    }

    @Override
    public List<Taikhoan> getTaiKhoanList() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findAll", Taikhoan.class);
        return q.getResultList();
    }

    @Override
    public Taikhoan addTaiKhoan(Taikhoan tk) {
        if (tk == null) {
            return null;
        }
        Session s = this.factory.getObject().getCurrentSession();

        s.persist(tk);

        s.refresh(tk);
        return tk;
    }

    @Override
    public ThongBao authenticate(String email, String matKhau) {
        Taikhoan tk = this.getUserByEmail(email);
        if (tk == null) {
            return new ThongBao("Tài khoản hoặc mật khẩu không đúng", false);
        }
        boolean auth = this.passwordEncoder.matches(matKhau, tk.getMatKhau());
        if (tk.getTrangThai() == TrangThaiTaiKhoan.HUY_KICH_HOAT) {
            return new ThongBao("Tài khoản của bạn bị vô hiệu hóa", false);
        }
        if (tk.getTrangThai() == TrangThaiTaiKhoan.DOI_XAC_NHAN) {
            return new ThongBao("Tài khoản của bạn đang trong quá trình xác nhận", false);
        }
        if (auth == false) {
            return new ThongBao("Tài khoản hoặc mật khẩu sai", false);
        }

        return new ThongBao("Đăng nhập thành công", auth);
    }

    @Override
    public void deleteUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Taikhoan tk = this.getUserById(id);
        s.remove(tk);
    }

    @Override
    public Taikhoan getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Taikhoan.findById", Taikhoan.class);
        q.setParameter("id", id);
        return (Taikhoan) q.getSingleResult();
    }

    @Override
    public Taikhoan addOrUpdateTaiKhoan(Taikhoan tk) {
        Session s = this.factory.getObject().getCurrentSession();
        if (tk.getId() == null) {
            s.persist(tk);
        } else {
            s.merge(tk);
        }
        return tk;
    }

    @Override
    public List<Taikhoan> getDsTaiKhoan(Map<String, String> params, String role) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Taikhoan> q = b.createQuery(Taikhoan.class);
        Root<Taikhoan> root = q.from(Taikhoan.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            // Lọc theo từ khóa (email, họ, tên)
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                String pattern = "%" + kw.toLowerCase() + "%";
                List<Predicate> orPredicates = new ArrayList<>();
                orPredicates.add(b.like(b.lower(root.get("email")), pattern));
                orPredicates.add(b.like(b.lower(root.get("tenNguoiDung")), pattern));
                orPredicates.add(b.like(b.lower(root.get("hoNguoiDung")), pattern));
                orPredicates.add(b.like(b.lower(root.get("soDienThoai")), pattern));
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

        if (role != null && !role.isEmpty()) {
            predicates.add(b.equal(root.get("role"), role));
        }

        q.where(predicates.toArray(new Predicate[0]));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Taikhoan updateTaiKhoan(Taikhoan tk) {
        Session session = this.factory.getObject().getCurrentSession();
        Taikhoan updateTaiKhoan = session.merge(tk);
        return updateTaiKhoan;
    }

    @Override
    public List<Taikhoan> getListUserByTrangThai(TrangThaiTaiKhoan trangThai) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Taikhoan> cq = cb.createQuery(Taikhoan.class);
        Root<Taikhoan> root = cq.from(Taikhoan.class);

        // WHERE trangThai = :trangThai
        cq.select(root).where(cb.equal(root.get("trangThai"), trangThai));

        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public boolean authenticateRole(String email, String password) {
        Taikhoan tk = this.getUserByEmail(email);
        return this.passwordEncoder.matches(password, tk.getMatKhau());
    }

    @Override
    public Set<Taikhoan> getListUserByBacSiId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lichkham> q = b.createQuery(Lichkham.class);

        Root<Lichkham> root = q.from(Lichkham.class);
        Join<Lichkham, Hoso> joinHoso = root.join("hosoId");
        Join<Hoso, Benhnhan> joinBenhnhan = joinHoso.join("benhnhanId");

        q.select(root).where(
                b.equal(root.get("bacsiId").get("id"), id)
        );

        List<Lichkham> lichKhamList = s.createQuery(q).getResultList();

        // Lấy ra danh sách Taikhoan không trùng
        Set<Taikhoan> taiKhoanSet = lichKhamList.stream()
                .map(lichkham -> lichkham.getHosoId().getBenhnhanId())
                .collect(Collectors.toSet());

        return taiKhoanSet;
    }
}
