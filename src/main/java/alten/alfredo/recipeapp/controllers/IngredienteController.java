package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.services.RicettaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredienteController {

    private final RicettaService ricettaService;

    public IngredienteController(RicettaService ricettaService) {
        this.ricettaService = ricettaService;
    }

    @GetMapping
    @RequestMapping("/ricetta/{id}/ingredienti")
    public String listaIngredientiRicetta(@PathVariable String id, Model model){
        log.debug("Recuperando la lista degli ingredienti ricetta in base al suo id. Ricetta: "+id);

        //uso command object per eviyare gli errori del caricamento lazy in thymeleaf
        model.addAttribute("ricetta", ricettaService.getCommandById(Long.valueOf(id)));
        return "ricetta/ingrediente/list";
    }
}
