package com.example.Pai.service;

import java.util.List;

import com.example.Pai.entity.Certificato;

public interface PaiService {

    public List<Certificato> getCertificati(String roles);

    public Certificato getCertificato(int certificatoId, String roleString);

    public void saveCert(Certificato newCertificato);

    public void deleteCert(int certificatoId);

    public List<Certificato> getCertificatoByCuaa(String cuaa, String roleString);

}
