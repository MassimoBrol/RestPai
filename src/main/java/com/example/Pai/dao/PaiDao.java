package com.example.Pai.dao;

import java.util.List;

import com.example.Pai.entity.Certificato;

public interface PaiDao {

    public List<Certificato> getCertificati(String roles);

    public Certificato getCertificato(int certificatoId, String roles);

    public void saveCert(Certificato newCertificato);

    public void deleteCert(int certificatoId);

    public List<Certificato> getCertificatoByCuaa(String cuaa, String roles);

}
