package tn.spring.springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.entities.Contrat;
import tn.spring.springboot.entities.Etudiant;
import tn.spring.springboot.repository.ContratRepository;
import tn.spring.springboot.repository.EtudiantRepository;

import java.util.*;

@Service
@AllArgsConstructor

public class ContratImpl implements IContrat {
    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat addContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public Contrat updateContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public void deleteDetailContrat(Integer id) {
        contratRepository.deleteById(id);
    }

    @Override
    public Contrat getContratById(Integer id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        int nbContratValide = 0;
        Etudiant etudiant = etudiantRepository.findEtudiantByNomEAndPrenomE(nomE, prenomE);
        for (Contrat c : etudiant.getContrat()) {
            if (c.getArchive() == Boolean.TRUE) {
                nbContratValide = nbContratValide + 1;
            }

        }

        if (nbContratValide <= 4) {
            ce.setEtudiant(etudiant);
            contratRepository.save(ce);

        }

        return ce;
    }

    public List<Contrat> getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
        List<Contrat> contrats = contratRepository.ListContratBetweenToDate(startDate, endDate);
        /*
        int ia=0,cloud=0,resau=0,securite=0;
        for (Contrat c: contrats) {

            switch (c.getSpecialite()){
                case IA: ia=ia+1;
                case CLOUD:cloud=cloud+1;
                case RESAU:resau=resau+1;
                case SECURITE:securite=securite+1;
            }
        }
        List<String> result=new ArrayList<String>();
        result.add("le montant de la specialite IA= "+ia*300+"DT");
        result.add("le montant de la specialite cloud= "+cloud*400+"DT");
        result.add("le montant de la specialite Resau= "+resau*350+"DT");
        result.add("le montant de la specialite securite= "+securite*450+"DT");
        return result;


    }
  */
        return contrats;
    }
}


