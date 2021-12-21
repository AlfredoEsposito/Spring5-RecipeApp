package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.UnitaDiMisuraCommand;

import java.util.Set;

public interface UnitaDiMisuraService {

    Set<UnitaDiMisuraCommand> listAllUdm();
}
