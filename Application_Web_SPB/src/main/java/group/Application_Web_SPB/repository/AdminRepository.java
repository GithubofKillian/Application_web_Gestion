package group.Application_Web_SPB.repository;

import group.Application_Web_SPB.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Recherche d'un admin par son username et son mot de passe
    Admin findByUsernameAndPassword(String username, String password);
}
