package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.commands.UnitaDiMisuraCommand;
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

    @GetMapping
    @RequestMapping("ricetta/{idRicetta}/ingrediente/new")
    public String newIngrediente(@PathVariable String idRicetta, Model model){
        //assicuriamoci di avere un id valido
        RicettaCommand ricettaCommand = ricettaService.getCommandById(Long.valueOf(idRicetta));

        //nuovo ingrediente
        IngredienteCommand ingredienteCommand = new IngredienteCommand();
        ingredienteCommand.setIdRicetta(ricettaCommand.getId());
        model.addAttribute("ingrediente", ingredienteService.saveIngredienteCommand(ingredienteCommand));

        //init udm
        ingredienteCommand.setUnitaDiMisura(new UnitaDiMisuraCommand());
        model.addAttribute("udmLIst", udmService.listAllUdm());

        return "ricetta/ingrediente/ingredienteform";
    }

    @GetMapping
    @RequestMapping("/ricetta/{idRicetta}/ingrediente/{idIngrediente}/delete")
    public String deleteIngrediente(@PathVariable String idRicetta, @PathVariable String idIngrediente){
        ingredienteService.deleteIngredienteById(Long.valueOf(idIngrediente), Long.valueOf(idRicetta));
        return "redirect:/ricetta/"+idRicetta+"/ingredienti";
    }

}
