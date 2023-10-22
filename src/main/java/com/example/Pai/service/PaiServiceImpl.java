package com.example.Pai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pai.dao.PaiDao;
import com.example.Pai.entity.Certificato;

import jakarta.transaction.Transactional;

@Service
public class PaiServiceImpl implements PaiService {

    @Autowired
    private PaiDao paiDao;

    @Override
    @Transactional
    public List<Certificato> getCertificati(String roles) {
        return paiDao.getCertificati(roles);
    }

    @Override
    @Transactional
    public Certificato getCertificato(int certificatoId, String roles) {
        return paiDao.getCertificato(certificatoId, roles);
    }

    @Override
    @Transactional
    public void saveCert(Certificato newCertificato) {
        paiDao.saveCert(newCertificato);
    }

    @Override
    @Transactional
    public void deleteCert(int certificatoId) {
        paiDao.deleteCert(certificatoId);
    }

    @Override
    @Transactional
    public List<Certificato> getCertificatoByCuaa(String cuaa, String roles) {
        return paiDao.getCertificatoByCuaa(cuaa, roles);
    }

}
