/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.repositoriesImpl;

import com.nnhp.pojo.TaiKhoanTinTuc;
import com.nnhp.pojo.Taikhoan;
import com.nnhp.pojo.Tintuc;
import com.nnhp.repositories.TaiKhoanTinTucRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author namnh
 */
@Repository
@Transactional
public class TaiKhoanTinTucRepositoryImpl implements TaiKhoanTinTucRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public TaiKhoanTinTuc addOrUpdateTaiKhoanTinTuc(TaiKhoanTinTuc tktt) {
        Session s = this.factory.getObject().getCurrentSession();
        if (tktt.getId() == null) {
            s.persist(tktt);
        } else {
            s.merge(tktt);
        }
        return tktt;
    }

}
