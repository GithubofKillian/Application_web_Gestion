package group.Application_Web_SPB.service;

import group.Application_Web_SPB.entity.Resultat;
import group.Application_Web_SPB.repository.ResultatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatService {

    private final ResultatRepository resultatRepository;

    public ResultatService(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    public Resultat ajouterResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public Resultat modifierResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public void supprimerResultat(Long id) {
        resultatRepository.deleteById(id);
    }

    public Resultat getResultat(Long id) {
        return resultatRepository.findById(id).orElse(null);
    }

    public List<Resultat> getAllResultats() {
        return resultatRepository.findAll();
    }

    public double calculerMoyenneParEtudiant(Long etudiantId) {
        List<Resultat> resultats = resultatRepository.findByEtudiantId(etudiantId);
        return resultats.stream().mapToDouble(Resultat::getNote).average().orElse(0.0);
    }
}
