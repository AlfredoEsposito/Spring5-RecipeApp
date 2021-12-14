package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.model.Ricetta;

import java.util.Set;

public interface RicettaService {

    Set<Ricetta> getRicette();
    Ricetta findById(Long l);
    RicettaCommand saveRicettaCommand(RicettaCommand ricettaCommand);
}
