package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.model.Categoria;
import alten.alfredo.recipeapp.model.UnitaDiMisura;
import alten.alfredo.recipeapp.repositories.CategoriaRepository;
import alten.alfredo.recipeapp.repositories.UnitaDiMisuraRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoriaRepository categoriaRepository;
    private UnitaDiMisuraRepository unitaDiMisuraRepository;

    public IndexController(CategoriaRepository categoriaRepository, UnitaDiMisuraRepository unitaDiMisuraRepository) {
        this.categoriaRepository = categoriaRepository;
        this.unitaDiMisuraRepository = unitaDiMisuraRepository;
    }

    @RequestMapping({"","/", "/index", "/index.html"})
    public String getIndexPage(){

        Optional<Categoria> idCategoria = categoriaRepository.findByNome("American");
        Optional<UnitaDiMisura> idUdm = unitaDiMisuraRepository.findByUdm("Ounce");

        System.out.println(idCategoria.get().getId());
        System.out.println(idUdm.get().getId());

        return "index";
    }
}
