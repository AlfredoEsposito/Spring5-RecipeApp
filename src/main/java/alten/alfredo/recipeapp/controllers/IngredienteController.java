package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.services.IngredienteService;
import alten.alfredo.recipeapp.services.RicettaService;
import alten.alfredo.recipeapp.services.UnitaDiMisuraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredienteController {

    private final RicettaService ricettaService;
    private final IngredienteService ingredienteService;
    private final UnitaDiMisuraService udmService;

    public IngredienteController(RicettaService ricettaService, IngredienteService ingredienteService, UnitaDiMisuraService udmService) {
        this.ricettaService = ricettaService;
        this.ingredienteService = ingredienteService;
        this.udmService = udmService;
    }

    @GetMapping
    @RequestMapping("/ricetta/{idRicetta}/ingredienti")
    public String listaIngredientiRicetta(@PathVariable String idRicetta, Model model){
        log.debug("Recuperando la lista degli ingredienti della ricetta in base al suo id. Ricetta: "+idRicetta);

        //uso command object per eviyare gli errori del caricamento lazy in thymeleaf
        model.addAttribute("ricetta", ricettaService.getCommandById(Long.valueOf(idRicetta)));
        return "ricetta/ingrediente/list";
    }

    @GetMapping
    @RequestMapping("ricetta/{idRicetta}/ingrediente/{idIngrediente}/show")
    public String showIngredientiRicetta(@PathVariable String idRicetta, @PathVariable String idIngrediente, Model model){
        model.addAttribute("ingrediente", ingredienteService.getByIdRicettaAndIdIngrediente(Long.valueOf(idRicetta),Long.valueOf(idIngrediente)));
        return "ricetta/ingrediente/show";
    }

    @GetMapping
    @RequestMapping("ricetta/{idRicetta}/ingrediente/{idIngrediente}/update")
    public String updateIngredienteRicetta(@PathVariable String idRicetta, @PathVariable String idIngrediente, Model model){
        model.addAttribute("ingrediente", ingredienteService.getByIdRicettaAndIdIngrediente(Long.valueOf(idRicetta),Long.valueOf(idIngrediente)));
        model.addAttribute("unitaDiMisuraList", udmService.listAllUdm());
        return "ricetta/ingrediente/ingredienteform";
    }

    @PostMapping
    @RequestMapping("ricetta/{idRicetta}/ingrediente")
    public String saveOrUpdateIngrediente(@ModelAttribute IngredienteCommand ingredienteCommand){
        IngredienteCommand ingredienteCommandToSaveOrUpdate = ingredienteService.saveIngredienteCommand(ingredienteCommand);
        log.debug("Id ingrediente salvato: "+ingredienteCommandToSaveOrUpdate.getId());
        log.debug("Id ricetta dell'ingrediente salvato: "+ingredienteCommandToSaveOrUpdate.getIdRicetta());
        return "redirect:/ricetta/"+ingredienteCommandToSaveOrUpdate.getIdRicetta()+"/ingrediente/"+ingredienteCommandToSaveOrUpdate.getId()+"/show";
    }

}
