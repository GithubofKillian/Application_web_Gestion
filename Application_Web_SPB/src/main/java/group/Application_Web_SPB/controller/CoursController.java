package group.Application_Web_SPB.controller;

import group.Application_Web_SPB.entity.Cours;
import group.Application_Web_SPB.service.CoursService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cours")
public class CoursController {

    private final CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @GetMapping("/list")
    public String listCours(Model model) {
        List<Cours> cours = coursService.getAllCours();
        model.addAttribute("cours", cours);
        return "cours/liste_cours";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("cours", new Cours());
        return "cours/ajouter_cours";
    }

    @PostMapping("/add")
    public String addCours(@ModelAttribute Cours cours) {
        coursService.ajouterCours(cours);
        return "redirect:/cours/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Cours cours = coursService.getCours(id);
        model.addAttribute("cours", cours);
        return "cours/modifier_cours";
    }

    @PostMapping("/edit")
    public String editCours(@ModelAttribute Cours cours) {
        coursService.modifierCours(cours);
        return "redirect:/cours/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCours(@PathVariable Long id) {
        coursService.supprimerCours(id);
        return "redirect:/cours/list";
    }
}
