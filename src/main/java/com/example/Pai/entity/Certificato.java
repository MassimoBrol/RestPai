package com.example.Pai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificati")
public class Certificato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "n_certificato")
    private int n_certificato;

    @Column(name = "denominazione")
    private String denominazione;

    @Column(name = "cuaa")
    private String cuaa;

    @Column(name = "comune")
    private String comune;

    @Column(name = "prodMipaaf")
    private String prodMipaaf;

    @Column(name = "descProd")
    private String descProd;

    @Column(name = "valAssicurato")
    private double valAssicurato;

    @Column(name = "ufficioCaa")
    private int ufficioCaa;

    public Certificato() {
    }

    public Certificato(int id, int n_certificato, String denominazione, String cuaa, String comune, String prodMipaaf,
            String descProd, double valAssicurato, int ufficioCaa) {
        this.id = id;
        this.n_certificato = n_certificato;
        this.denominazione = denominazione;
        this.cuaa = cuaa;
        this.comune = comune;
        this.prodMipaaf = prodMipaaf;
        this.descProd = descProd;
        this.valAssicurato = valAssicurato;
        this.ufficioCaa = ufficioCaa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getN_certificato() {
        return n_certificato;
    }

    public void setN_certificato(int n_certificato) {
        this.n_certificato = n_certificato;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getCuaa() {
        return cuaa;
    }

    public void setCuaa(String cuaa) {
        this.cuaa = cuaa;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProdMipaaf() {
        return prodMipaaf;
    }

    public void setProdMipaaf(String prodMipaaf) {
        this.prodMipaaf = prodMipaaf;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public double getValAssicurato() {
        return valAssicurato;
    }

    public void setValAssicurato(double valAssicurato) {
        this.valAssicurato = valAssicurato;
    }

    public int getUfficioCaa() {
        return ufficioCaa;
    }

    public void setUfficioCaa(int ufficioCaa) {
        this.ufficioCaa = ufficioCaa;
    }

    @Override
    public String toString() {
        return "Certificato [id=" + id + ", n_certificato=" + n_certificato + ", denominazione=" + denominazione
                + ", cuaa=" + cuaa + ", comune=" + comune + ", prodMipaaf=" + prodMipaaf + ", descProd=" + descProd
                + ", valAssicurato=" + valAssicurato + ", ufficioCaa=" + ufficioCaa + "]";
    }

}
