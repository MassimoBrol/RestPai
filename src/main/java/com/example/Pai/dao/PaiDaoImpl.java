package com.example.Pai.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Pai.entity.Certificato;

@Repository
public class PaiDaoImpl implements PaiDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Certificato> getCertificati(String roles) {

        Session currentSession = sessionFactory.getCurrentSession();

        String queryString = "from Certificato where " + roles + " order by cuaa";

        Query<Certificato> theQuery = currentSession
                .createQuery(queryString, Certificato.class);

        List<Certificato> result = theQuery.getResultList();

        return result;

    }

    @Override
    public Certificato getCertificato(int certificatoId, String roles) {

        Session currentSession = sessionFactory.getCurrentSession();

        String queryString = "from Certificato where (" + roles + ") and id=" + certificatoId;

        System.out.println(queryString);

        Query<Certificato> theQuery = currentSession.createQuery(queryString);

        Certificato cert = theQuery.getSingleResultOrNull();

        return cert;
    }

    @Override
    public void saveCert(Certificato newCertificato) {

        Session currentSession = sessionFactory.getCurrentSession();

        if (newCertificato.getId() == 0) {
            currentSession.persist(newCertificato);
        } else {
            currentSession.merge(newCertificato);
        }

    }

    @Override
    public void deleteCert(int certificatoId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<?> theQuery = currentSession.createQuery("delete from Certificato where id=:certificatoId")
                .setParameter("certificatoId", certificatoId);

        theQuery.executeUpdate();

    }

    @Override
    public List<Certificato> getCertificatoByCuaa(String cuaa, String roles) {

        Session currentSession = sessionFactory.getCurrentSession();

        String queryString = "from Certificato where (" + roles + ") and cuaa=:cuaa order by n_certificato";

        Query<Certificato> theQuery = currentSession
                .createQuery(queryString).setParameter("cuaa", cuaa);

        List<Certificato> result = theQuery.getResultList();

        return result;

    }

}
