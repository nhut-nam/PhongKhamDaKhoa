/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Bacsi;
import com.nnhp.pojo.Bacsithuocchuyenkhoa;
import com.nnhp.pojo.Chuyenkhoa;
import com.nnhp.repositories.BacSiThuocChuyenKhoaRepository;
import com.nnhp.services.BacSiThuocChuyenKhoaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author namnh
 */
@Service
public class BacSiThuocChuyenKhoaServiceImpl implements BacSiThuocChuyenKhoaService {
    
    @Autowired
    private BacSiThuocChuyenKhoaRepository bsckRepo;

    @Override
    public Bacsithuocchuyenkhoa addBacSiChuyenKhoa(Bacsi bs, Chuyenkhoa ck) {
        Bacsithuocchuyenkhoa bsck = new Bacsithuocchuyenkhoa();
        bsck.setBacsiId(bs);
        bsck.setChuyenkhoaId(ck);
        return this.bsckRepo.addBacSiThuocChuyenKhoa(bsck);
    }
    
    @Override
    public List<Bacsithuocchuyenkhoa> addBacSiChuyenKhoaList(Bacsi bs, List<Chuyenkhoa> cks) {
        List<Bacsithuocchuyenkhoa> bsckList = new ArrayList<>();
        cks.forEach(ck -> {
            bsckList.add(this.addBacSiChuyenKhoa(bs, ck));
        });
        return bsckList;
    }
}
