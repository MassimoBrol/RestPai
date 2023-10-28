package com.example.Pai.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pai.service.PaiService;
import com.example.Pai.entity.Certificato;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaiController {

    @Autowired
    PaiService paiService;

    private List<String> getRoles(Authentication auth) {

        List<String> roles = auth.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return roles;

    }

    // check Role

    private String checkRole(List<String> roles) {

        String uffici = "";

        for (int i = 0; i < roles.size(); i++) {

            if (i > 0) {
                uffici = uffici + " or";
            }

            switch (roles.get(i)) {
                case "Trento":
                    uffici = uffici + " ufficioCaa = 1";

                    break;
                case "Cles":
                    uffici = uffici + " ufficioCaa = 3";

                    break;
                case "Mezzolombardo":
                    uffici = uffici + " ufficioCaa = 4";

                    break;
                default:
                    break;
            }

        }

        return uffici;
    }

    // request all certificati

    @GetMapping("/certificati")
    public List<Certificato> getCertificati(Authentication auth) {

        List<String> roles = getRoles(auth);

        String rolesString = checkRole(roles);

        List<Certificato> certificati = paiService.getCertificati(rolesString);

        System.out.println(certificati.size());

        return certificati;

    }

    // request single certificato

    @GetMapping("/certificati/id/{certificatoId}")
    public Certificato getCertificatoById(@PathVariable int certificatoId, Authentication auth) {

        List<String> roles = getRoles(auth);

        String roleString = checkRole(roles);

        Certificato theCert = paiService.getCertificato(certificatoId, roleString);

        if (theCert == null) {
            throw new PaiNotFoundException(
                    "Pai non trovato per id: " + certificatoId + " e ufficio " + roles.toString());
        }

        return theCert;

    }

    // request certificati per cuaa
    @GetMapping("/certificati/cuaa/{cuaa}")
    public List<Certificato> getCertificatoByCuaa(@PathVariable String cuaa, Authentication auth) {

        List<String> roles = getRoles(auth);

        String roleString = checkRole(roles);

        List<Certificato> estrazionePerCuaa = paiService.getCertificatoByCuaa(cuaa, roleString);

        if (estrazionePerCuaa.isEmpty()) {
            throw new PaiNotFoundException(
                    "Nessun PAI trovato per il cuaa: " + cuaa + " e ufficio " + roles.toString());
        }

        System.out.println(estrazionePerCuaa.size());

        return estrazionePerCuaa;

    }

    // add certificato
    @PostMapping("/certificati")
    public Certificato theCertificato(@RequestBody Certificato newCertificato, Authentication auth) {

        List<String> roles = getRoles(auth);
        newCertificato.setId(0);

        return saveOrUpdate(newCertificato, roles);

    }

    // update certificato
    @PutMapping("/certificati")
    public Certificato updateCertificato(@RequestBody Certificato newCertificato, Authentication auth) {

        List<String> roles = getRoles(auth);

        return saveOrUpdate(newCertificato, roles);

    }

    // add delete Certificato
    @DeleteMapping("/certificati/{certificatoId}")
    public String deleteCertificato(@PathVariable int certificatoId, Authentication auth) {

        List<String> roles = getRoles(auth);

        String roleString = checkRole(roles);

        Certificato tempCert = paiService.getCertificato(certificatoId, roleString);

        if (tempCert == null) {
            throw new PaiNotFoundException(
                    "Pai non trovato per id: " + certificatoId + " e ufficio " + roles.toString());
        }

        paiService.deleteCert(certificatoId);

        return "Cancellato PAI con id: " + certificatoId;

    }

    private Certificato saveOrUpdate(Certificato newCertificato, List<String> roles) {

        switch (newCertificato.getUfficioCaa()) {

            case 1:
                if (roles.contains("ROLE_TRENTO")) {

                    paiService.saveCert(newCertificato);

                    return newCertificato;

                } else {
                    throw new PaiNotFoundException(
                            "L'utente non è abilitato all'inserimento di ceritificati per l'ufficio di Trento");

                }
            case 3:
                if (roles.contains("ROLE_CLES")) {

                    paiService.saveCert(newCertificato);

                    return newCertificato;

                } else {
                    throw new PaiNotFoundException(
                            "L'utente non è abilitato all'inserimento di ceritificati per l'ufficio di Cles");

                }
            case 4:
                if (roles.contains("ROLE_MEZZOLOMBARDO")) {

                    paiService.saveCert(newCertificato);

                    return newCertificato;

                } else {
                    throw new PaiNotFoundException(
                            "L'utente non è abilitato all'inserimento di ceritificati per l'ufficio di Mezzolombardo");

                }
            default:
                break;

        }
        return newCertificato;

    }

}