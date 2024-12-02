package group.Application_Web_SPB.service;

import group.Application_Web_SPB.entity.Etudiant;
import group.Application_Web_SPB.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant modifierEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    public Etudiant getEtudiant(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public List<Etudiant> rechercherEtudiantsParNom(String nomRecherche) {
        return etudiantRepository.findByNomContainingIgnoreCase(nomRecherche);
    }
}
