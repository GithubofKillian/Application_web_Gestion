package group.Application_Web_SPB.repository;

import group.Application_Web_SPB.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    // Recherche d'un enseignant par son contact (email, téléphone) et son mot de passe
    Enseignant findByContactAndPassword(String contact, String password);
}
