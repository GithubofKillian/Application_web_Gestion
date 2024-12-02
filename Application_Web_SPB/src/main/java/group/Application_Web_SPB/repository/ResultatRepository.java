package group.Application_Web_SPB.repository;

import group.Application_Web_SPB.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultatRepository extends JpaRepository<Resultat, Long> {

    // Recherche des résultats par l'ID de l'étudiant
    List<Resultat> findByEtudiantId(Long etudiantId);
}
