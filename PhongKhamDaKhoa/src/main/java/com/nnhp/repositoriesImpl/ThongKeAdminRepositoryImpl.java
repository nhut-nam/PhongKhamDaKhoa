/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.enums.TrangThaiLichKham;
import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Benhvien;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.pojo.Lichkham;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.repositories.ThongKeAdminRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Date;
import java.util.HashMap;
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
public class ThongKeAdminRepositoryImpl implements ThongKeAdminRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Object[]> thongKeLichKhamTheoTrangThai() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root root = q.from(Lichkham.class);
        q.multiselect(root.get("trangThai"), b.count(root.get("id")));
        q.groupBy(root.get("trangThai"));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> thongKeLichKhamTheoBacSi() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        q.multiselect(
            root.get("bacsiId").get("id"),
            root.get("bacsiId").get("hoNguoiDung"),
            root.get("bacsiId").get("tenNguoiDung"),
            b.count(root.get("id"))
        );
        q.groupBy(
            root.get("bacsiId").get("id"),
            root.get("bacsiId").get("hoNguoiDung"),
            root.get("bacsiId").get("tenNguoiDung")
        );
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> thongKeLichKhamTheoBenhVien() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        q.multiselect(
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("id"),
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("tenBenhVien"),
            b.count(root.get("id"))
        );
        q.groupBy(
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("id"),
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("tenBenhVien")
        );
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> thongKeLichKhamTheoThoiGian(Date tuNgay, Date denNgay) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        
        q.multiselect(
            b.function("date", Date.class, root.get("ngayHen")),
            b.count(root.get("id"))
        );
        
        if (tuNgay != null && denNgay != null) {
            q.where(
                b.between(root.get("ngayHen"), tuNgay, denNgay)
            );
        }
        
        q.groupBy(b.function("date", Date.class, root.get("ngayHen")));
        q.orderBy(b.asc(b.function("date", Date.class, root.get("ngayHen"))));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public Map<String, Long> thongKeTongQuat() {
        Session s = this.factory.getObject().getCurrentSession();
        Map<String, Long> result = new HashMap<>();
        
        // Tổng số tài khoản
        Query qTaiKhoan = s.createQuery("SELECT COUNT(t) FROM Taikhoan t");
        result.put("soLuongTaiKhoan", (Long) qTaiKhoan.getSingleResult());
        
        // Tổng số bác sĩ
        Query qBacSi = s.createQuery("SELECT COUNT(b) FROM Bacsi b");
        result.put("soLuongBacSi", (Long) qBacSi.getSingleResult());
        
        // Tổng số bệnh viện
        Query qBenhVien = s.createQuery("SELECT COUNT(bv) FROM Benhvien bv");
        result.put("soLuongBenhVien", (Long) qBenhVien.getSingleResult());
        
        // Tổng số chuyên khoa
        Query qChuyenKhoa = s.createQuery("SELECT COUNT(ck) FROM Chuyenkhoa ck");
        result.put("soLuongChuyenKhoa", (Long) qChuyenKhoa.getSingleResult());
        
        // Tổng số lịch khám
        Query qLichKham = s.createQuery("SELECT COUNT(lk) FROM Lichkham lk");
        result.put("soLuongLichKham", (Long) qLichKham.getSingleResult());
        
        return result;
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoThoiGian(Date tuNgay, Date denNgay) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        
        q.multiselect(
            b.function("date", Date.class, root.get("ngayHen")),
            b.sum(root.get("benhvienchuyenkhoadichvuId").get("giaTien"))
        );
        
        if (tuNgay != null && denNgay != null) {
            q.where(
                b.and(
                    b.between(root.get("ngayHen"), tuNgay, denNgay),
                    b.equal(root.get("trangThai"),TrangThaiLichKham.DA_KHAM_XONG) // Chỉ tính các lịch khám đã hoàn thành
                )
            );
        }
        
        q.groupBy(b.function("date", Date.class, root.get("ngayHen")));
        q.orderBy(b.asc(b.function("date", Date.class, root.get("ngayHen"))));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoBenhVien(Date tuNgay, Date denNgay) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        
        q.multiselect(
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("id"),
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("tenBenhVien"),
            b.sum(root.get("benhvienchuyenkhoadichvuId").get("giaTien"))
        );
        
        if (tuNgay != null && denNgay != null) {
            q.where(
                b.and(
                    b.between(root.get("ngayHen"), tuNgay, denNgay),
                    b.equal(root.get("trangThai"), TrangThaiLichKham.DA_KHAM_XONG) // Chỉ tính các lịch khám đã hoàn thành
                )
            );
        }
        
        q.groupBy(
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("id"),
            root.get("benhvienchuyenkhoadichvuId").get("benhvienchuyenkhoaId").get("benhvienId").get("tenBenhVien")
        );
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoBacSi(Date tuNgay, Date denNgay) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        
        q.multiselect(
            root.get("bacsiId").get("id"),
            root.get("bacsiId").get("hoNguoiDung"),
            root.get("bacsiId").get("tenNguoiDung"),
            b.sum(root.get("benhvienchuyenkhoadichvuId").get("giaTien"))
        );
        
        if (tuNgay != null && denNgay != null) {
            q.where(
                b.and(
                    b.between(root.get("ngayHen"), tuNgay, denNgay),
                    b.equal(root.get("trangThai"), TrangThaiLichKham.DA_KHAM_XONG) // Chỉ tính các lịch khám đã hoàn thành
                )
            );
        }
        
        q.groupBy(
            root.get("bacsiId").get("id"),
            root.get("bacsiId").get("hoNguoiDung"),
            root.get("bacsiId").get("tenNguoiDung")
        );
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public double thongKeTongDoanhThu(Date tuNgay, Date denNgay) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Double> q = b.createQuery(Double.class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        
        q.select(b.sum(root.get("benhvienchuyenkhoadichvuId").get("giaTien")));
        
        if (tuNgay != null && denNgay != null) {
            q.where(
                b.and(
                    b.between(root.get("ngayHen"), tuNgay, denNgay),
                    b.equal(root.get("trangThai"), TrangThaiLichKham.DA_KHAM_XONG) // Chỉ tính các lịch khám đã hoàn thành
                )
            );
        }
        
        Query query = s.createQuery(q);
        Double result = (Double) query.getSingleResult();
        return result != null ? result : 0.0;
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoThang(String loaiThongKe, int nam) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        
        q.multiselect(
            b.function(loaiThongKe, Integer.class, root.get("ngayHen")),
            b.count(root.get("id")),
            b.sum(root.get("benhvienchuyenkhoadichvuId").get("giaTien"))
        );
        
        q.where(
            b.and(
                b.equal(b.function("YEAR", Integer.class, root.get("ngayHen")), nam),
                b.equal(root.get("trangThai"), TrangThaiLichKham.DA_KHAM_XONG) // Chỉ tính các lịch khám đã hoàn thành
            )
        );
        
        q.groupBy(b.function(loaiThongKe, Integer.class, root.get("ngayHen")));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> thongKeDoanhThuTheoNam() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Lichkham> root = q.from(Lichkham.class);
        
        q.multiselect(
            b.function("year", Integer.class, root.get("ngayHen")),
            b.count(root.get("id")),
            b.sum(root.get("benhvienchuyenkhoadichvuId").get("giaTien"))
        );
        
        q.where(b.equal(root.get("trangThai"), TrangThaiLichKham.DA_KHAM_XONG)); // Chỉ tính các lịch khám đã hoàn thành
        
        q.groupBy(b.function("year", Integer.class, root.get("ngayHen")));
        q.orderBy(b.asc(b.function("year", Integer.class, root.get("ngayHen"))));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
} 