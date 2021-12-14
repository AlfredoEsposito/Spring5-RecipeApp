package alten.alfredo.recipeapp.commands;

import alten.alfredo.recipeapp.model.Ricetta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaCommand {

    private Long id;
    private String nome;
    private Set<Ricetta> ricette;
}
