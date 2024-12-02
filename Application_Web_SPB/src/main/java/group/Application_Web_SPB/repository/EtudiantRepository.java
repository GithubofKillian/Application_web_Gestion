package group.Application_Web_SPB.repository;

import group.Application_Web_SPB.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    // Recherche des étudiants dont le nom contient la chaîne donnée, sans tenir compte de la casse
    List<Etudiant> findByNomContainingIgnoreCase(String nom);
    Etudiant findByContactAndPassword(String contact, String password);
}
