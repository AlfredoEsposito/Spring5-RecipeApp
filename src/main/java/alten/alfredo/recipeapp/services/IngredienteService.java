package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.IngredienteCommand;

public interface IngredienteService {

    IngredienteCommand getByIdRicettaAndIdIngrediente(Long idRicetta, Long idIngrediente);
    IngredienteCommand saveIngredienteCommand(IngredienteCommand ingredienteCommand);
}
