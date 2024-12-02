package group.Application_Web_SPB.service;

import group.Application_Web_SPB.entity.Enseignant;
import group.Application_Web_SPB.repository.EnseignantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {

    private final EnseignantRepository enseignantRepository;

    public EnseignantService(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    public Enseignant ajouterEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public Enseignant modifierEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public void supprimerEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }

    public Enseignant getEnseignant(Long id) {
        return enseignantRepository.findById(id).orElse(null);
    }

    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }
}
