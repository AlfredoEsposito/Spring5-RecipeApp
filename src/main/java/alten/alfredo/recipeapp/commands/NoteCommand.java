package alten.alfredo.recipeapp.commands;

import alten.alfredo.recipeapp.model.Ricetta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {

    private Long id;
    private String noteRicetta;
    private Ricetta ricetta;
}
