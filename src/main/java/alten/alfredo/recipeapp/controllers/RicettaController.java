package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.services.RicettaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("ricetta", ricettaService.findById(Long.valueOf(id)));
        return "ricetta/show";
    }

    @RequestMapping("ricetta/nuovaRicetta")
    public String newRicetta(Model model){
        model.addAttribute("ricetta", new RicettaCommand());
        return "ricetta/ricettaform";
    }

    @PostMapping //il verbo è Post, perchè si invia una form
    @RequestMapping("ricetta")
    public String saveOrUpdateRicetta(@ModelAttribute RicettaCommand ricettaCommand){
        RicettaCommand ricettaCommandToSaveOrUpdate = ricettaService.saveRicettaCommand(ricettaCommand);
        return "redirect:/ricetta/show/" + ricettaCommandToSaveOrUpdate.getId();
        // redirect è un comando che indica a spring mvc di reindirizzare a un url specifico. in questo caso avremo un redirect alla pagina di view "show" della ricetta salvata
    }


}
