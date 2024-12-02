package group.Application_Web_SPB.service;

import group.Application_Web_SPB.entity.Cours;
import group.Application_Web_SPB.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {

    private final CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public Cours ajouterCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public Cours modifierCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public void supprimerCours(Long id) {
        coursRepository.deleteById(id);
    }

    public Cours getCours(Long id) {
        return coursRepository.findById(id).orElse(null);
    }

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }
}
