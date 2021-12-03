package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.services.RicettaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RicettaService ricettaService;

    public IndexController(RicettaService ricettaService) {
        this.ricettaService = ricettaService;
    }

    @RequestMapping({"","/", "/index", "/index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("ricette", ricettaService.getRicette());
        return "index";
    }
}
