package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.services.RicettaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RicettaController {

    private final RicettaService ricettaService;

    public RicettaController(RicettaService ricettaService) {
        this.ricettaService = ricettaService;
    }

    //questo metodo mostra una ricetta per l'id, che va specificato nel @RequestMapping tra parentesi graffe dopo il percorso
    @RequestMapping("/ricetta/show/{id}")
    public String getRicettaById(@PathVariable String id, Model model){
        model.addAttribute("ricetta", ricettaService.findById(new Long(id)));
        return "ricetta/show";
    }
}
