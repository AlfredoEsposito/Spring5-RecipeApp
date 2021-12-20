package alten.alfredo.recipeapp.commands;

import alten.alfredo.recipeapp.enumeration.Difficolta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RicettaCommand {

    private Long id;
    private String nomeRicetta;
    private Integer tempoPreparazione;
    private Integer tempoCottura;
    private Integer porzioni;
    private String url;
    private String procedimento;
    private NoteCommand note;
    private Set<IngredienteCommand> ingredienti = new HashSet<>();
    private Difficolta difficolta;
    private Set<CategoriaCommand> categorie = new HashSet<>();




}
