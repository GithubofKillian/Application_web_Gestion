package group.Application_Web_SPB.service;

import group.Application_Web_SPB.entity.Resultat;
import group.Application_Web_SPB.repository.ResultatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelevePdfService {

    private final ResultatRepository resultatRepository;

    public RelevePdfService(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    public List<Resultat> getResultatsParEtudiant(Long etudiantId) {
        return resultatRepository.findByEtudiantId(etudiantId);
    }

    public byte[] genererRelevePdf(Long etudiantId) {
        // Implémenter la génération du PDF ici
        return new byte[0];
    }
}
