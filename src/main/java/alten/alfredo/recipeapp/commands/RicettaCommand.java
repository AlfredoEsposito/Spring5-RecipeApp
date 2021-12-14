package alten.alfredo.recipeapp.commands;

import alten.alfredo.recipeapp.enumeration.Difficolta;
import alten.alfredo.recipeapp.model.Categoria;
import alten.alfredo.recipeapp.model.Ingrediente;
import alten.alfredo.recipeapp.model.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RicettaCommand {

    private Long id;
    private String descrizione;
    private Integer tempoPreparazione;
    private Integer tempoCottura;
    private Integer porzioni;
    private String url;
    private String directions;
    private Note note;
    private Set<Ingrediente> ingredienti;
    private Difficolta difficolta;
    private Set<Categoria> categorie;




}
