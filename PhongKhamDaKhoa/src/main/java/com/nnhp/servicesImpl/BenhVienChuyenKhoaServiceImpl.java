/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.servicesImpl;

import com.nnhp.pojo.Benhvienchuyenkhoa;
import com.nnhp.repositories.BenhVienChuyenKhoaRepository;
import com.nnhp.services.BenhVienChuyenKhoaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hoang
 */
@Service
public class BenhVienChuyenKhoaServiceImpl implements BenhVienChuyenKhoaService {
    @Autowired
    private BenhVienChuyenKhoaRepository bvckRepository;
    
    @Override
    public List<Benhvienchuyenkhoa> getAll() {
        return this.bvckRepository.getAll();
    }

    @Override
    public Benhvienchuyenkhoa getById(int id) {
        return this.bvckRepository.getById(id);
    }

    @Override
    public Benhvienchuyenkhoa addOrUpdate(Benhvienchuyenkhoa bvck) {
        return this.bvckRepository.addOrUpdate(bvck);
    }

    @Override
    public boolean delete(int id) {
        return this.bvckRepository.delete(id);
    }
    
    @Override
    public List<Benhvienchuyenkhoa> getByBenhVienId(int benhVienId) {
        return this.bvckRepository.getByBenhVienId(benhVienId);
    }
    
    @Override
    public List<Benhvienchuyenkhoa> getByChuyenKhoaId(int chuyenKhoaId) {
        return this.bvckRepository.getByChuyenKhoaId(chuyenKhoaId);
    }
    
    @Override
    public List<Benhvienchuyenkhoa> getDsBenhVienChuyenKhoa() {
        return this.getAll();
    }

    @Override
    public Boolean tonTaiBenhVienIdChuyenKhoaId(int benhVienId, int chuyenKhoaId) {
        return this.bvckRepository.tonTaiBenhVienIdChuyenKhoaId(benhVienId, chuyenKhoaId);
    }

    @Override
    public List<Benhvienchuyenkhoa> getDsBenhVienChuyenKhoa(Map<String, String> params) {
        return this.bvckRepository.getDsBenhVien(params);
    }
}