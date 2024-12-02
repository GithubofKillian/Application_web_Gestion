package group.Application_Web_SPB.controller;

import group.Application_Web_SPB.entity.Etudiant;
import group.Application_Web_SPB.service.EtudiantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping("/list")
    public String listEtudiants(Model model) {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "etudiants/liste_etudiants";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "etudiants/ajouter_etudiant";
    }

    @PostMapping("/add")
    public String addEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantService.ajouterEtudiant(etudiant);
        return "redirect:/etudiants/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiant(id);
        model.addAttribute("etudiant", etudiant);
        return "etudiants/modifier_etudiant";
    }

    @PostMapping("/edit")
    public String editEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantService.modifierEtudiant(etudiant);
        return "redirect:/etudiants/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantService.supprimerEtudiant(id);
        return "redirect:/etudiants/list";
    }
}
