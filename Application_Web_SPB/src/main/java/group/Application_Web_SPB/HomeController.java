package group.Application_Web_SPB;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Cette méthode répondra à la requête GET à l'URL "/"
    @GetMapping("/")
    public String home() {
        return "Bienvenue sur la page d'accueil !";
    }
}
