package group.Application_Web_SPB.service;

import group.Application_Web_SPB.entity.Admin;
import group.Application_Web_SPB.entity.Enseignant;
import group.Application_Web_SPB.entity.Etudiant;
import group.Application_Web_SPB.repository.AdminRepository;
import group.Application_Web_SPB.repository.EnseignantRepository;
import group.Application_Web_SPB.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AdminRepository adminRepository;
    private final EnseignantRepository enseignantRepository;
    private final EtudiantRepository etudiantRepository;

    public LoginService(AdminRepository adminRepository, EnseignantRepository enseignantRepository,
                        EtudiantRepository etudiantRepository) {
        this.adminRepository = adminRepository;
        this.enseignantRepository = enseignantRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public Object authenticateUser(String contact, String password, String role) {
        switch (role) {
            case "Admin":
                return adminRepository.findByUsernameAndPassword(contact, password);
            case "Enseignant":
                return enseignantRepository.findByContactAndPassword(contact, password);
            case "Etudiant":
                return etudiantRepository.findByContactAndPassword(contact, password);
            default:
                return null;
        }
    }
}
