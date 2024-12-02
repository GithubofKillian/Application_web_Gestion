package group.Application_Web_SPB.controller;

import group.Application_Web_SPB.entity.Enseignant;
import group.Application_Web_SPB.service.EnseignantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enseignants")
public class EnseignantController {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping("/list")
    public String listEnseignants(Model model) {
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        model.addAttribute("enseignants", enseignants);
        return "enseignants/liste_enseignants";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "enseignants/ajouter_enseignant";
    }

    @PostMapping("/add")
    public String addEnseignant(@ModelAttribute Enseignant enseignant) {
        enseignantService.ajouterEnseignant(enseignant);
        return "redirect:/enseignants/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Enseignant enseignant = enseignantService.getEnseignant(id);
        model.addAttribute("enseignant", enseignant);
        return "enseignants/modifier_enseignant";
    }

    @PostMapping("/edit")
    public String editEnseignant(@ModelAttribute Enseignant enseignant) {
        enseignantService.modifierEnseignant(enseignant);
        return "redirect:/enseignants/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnseignant(@PathVariable Long id) {
        enseignantService.supprimerEnseignant(id);
        return "redirect:/enseignants/list";
    }
}
