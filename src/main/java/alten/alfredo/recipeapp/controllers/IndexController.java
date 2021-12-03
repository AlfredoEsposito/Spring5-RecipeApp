package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.services.RicettaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RicettaService ricettaService;

    public IndexController(RicettaService ricettaService) {
        this.ricettaService = ricettaService;
    }

    @RequestMapping({"","/", "/index", "/index.html"})
    public String getIndexPage(Model model){
        log.debug("Getting index page");
        model.addAttribute("ricette", ricettaService.getRicette());
        return "index";
    }
}
