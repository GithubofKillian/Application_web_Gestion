package group.Application_Web_SPB.repository;

import group.Application_Web_SPB.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
