/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Tintuc;
import com.nnhp.repositories.TinTucRepository;
import com.nnhp.services.TinTucService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author namnh
 */
@Service
public class TinTucServiceImpl implements TinTucService {
    @Autowired
    private TinTucRepository ttRepo;

    @Override
    public Tintuc getTinTucById(int id) {
        return this.ttRepo.getTinTucById(id);
    }

    @Override
    public List<Tintuc> findTop10TinTucChuaGanUser(int userId) {
        return this.ttRepo.findTop10TinTucChuaGanUser(userId);
    }

    @Override
    public List<Tintuc> getTinTucByUserId(int userId) {
        return this.ttRepo.getTinTucByUserId(userId);
    }

    @Override
    public Tintuc addOrUpdateTinTuc(Tintuc tt) {
        return this.ttRepo.addOrUpdateTinTuc(tt);
    }

    @Override
    public List<Tintuc> getTop10TintucMoiNhat() {
        return this.ttRepo.getTop10TintucMoiNhat();
    }
    
}
