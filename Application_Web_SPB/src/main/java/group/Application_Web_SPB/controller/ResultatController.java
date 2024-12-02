package group.Application_Web_SPB.controller;

import group.Application_Web_SPB.entity.Resultat;
import group.Application_Web_SPB.service.ResultatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resultats")
public class ResultatController {

    private final ResultatService resultatService;

    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping("/list")
    public String listResultats(Model model) {
        List<Resultat> resultats = resultatService.getAllResultats();
        model.addAttribute("resultats", resultats);
        return "resultats/liste_resultats";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("resultat", new Resultat());
        return "resultats/ajouter_resultat";
    }

    @PostMapping("/add")
    public String addResultat(@ModelAttribute Resultat resultat) {
        resultatService.ajouterResultat(resultat);
        return "redirect:/resultats/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Resultat resultat = resultatService.getResultat(id);
        model.addAttribute("resultat", resultat);
        return "resultats/modifier_resultat";
    }

    @PostMapping("/edit")
    public String editResultat(@ModelAttribute Resultat resultat) {
        resultatService.modifierResultat(resultat);
        return "redirect:/resultats/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteResultat(@PathVariable Long id) {
        resultatService.supprimerResultat(id);
        return "redirect:/resultats/list";
    }
}
