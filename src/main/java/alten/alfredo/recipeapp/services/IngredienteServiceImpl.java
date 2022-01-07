package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.converters.IngredienteCommandToIngrediente;
import alten.alfredo.recipeapp.converters.IngredienteToIngredienteCommand;
import alten.alfredo.recipeapp.model.Ingrediente;
import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import alten.alfredo.recipeapp.repositories.UnitaDiMisuraRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredienteServiceImpl implements IngredienteService{

    private final IngredienteToIngredienteCommand ingredienteToIngredienteCommand;
    private final IngredienteCommandToIngrediente ingredienteCommandToIngrediente;
    private final RicettaRepository ricettaRepository;
    private final UnitaDiMisuraRepository udmRepository;

    public IngredienteServiceImpl(IngredienteToIngredienteCommand ingredienteConverter, IngredienteCommandToIngrediente ingredienteCommandToIngrediente, RicettaRepository ricettaRepository, UnitaDiMisuraRepository udmRepository) {
        this.ingredienteToIngredienteCommand = ingredienteConverter;
        this.ingredienteCommandToIngrediente = ingredienteCommandToIngrediente;
        this.ricettaRepository = ricettaRepository;
        this.udmRepository = udmRepository;
    }

    @Override
    public IngredienteCommand getByIdRicettaAndIdIngrediente(Long idRicetta, Long idIngrediente) {
        Optional<Ricetta> ricettaOptional = ricettaRepository.findById(idRicetta);
        if(!ricettaOptional.isPresent()){
            //todo implementare gestione dell'errore
            log.error("Ricetta con id "+idRicetta+ " non trovata");
        }
        Ricetta ricetta = ricettaOptional.get();

        Optional<IngredienteCommand> ingredienteCommandOptional = ricetta.getIngredienti().stream()
                .filter(ingrediente -> ingrediente.getId().equals(idIngrediente))
                .map(ingrediente -> ingredienteToIngredienteCommand.convert(ingrediente)).findFirst();
        if(!ingredienteCommandOptional.isPresent()){
            //todo implementare gestione dell'errore
            log.error("Ingrediente con id " +idIngrediente+ " non trovato");
        }
        return ingredienteCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredienteCommand saveIngredienteCommand(IngredienteCommand ingredienteCommand) {
        Optional<Ricetta> ricettaOptional = ricettaRepository.findById(ingredienteCommand.getIdRicetta());
        if(!ricettaOptional.isPresent()){
            log.error("Ingrediente non trovato");
            return new IngredienteCommand();
        }else{
            Ricetta ricetta = ricettaOptional.get();
            Optional<Ingrediente> ingredienteOptional = ricetta
                    .getIngredienti()
                    .stream()
                    .filter(ingrediente -> ingrediente.getId().equals(ingredienteCommand.getId()))
                    .findFirst();

            if(ingredienteOptional.isPresent()){
                Ingrediente ingredienteFound = ingredienteOptional.get();
                ingredienteFound.setDescrizione(ingredienteCommand.getDescrizione());
                ingredienteFound.setQuantita(ingredienteCommand.getQuantita());
                ingredienteFound.setUnitaDiMisura(udmRepository
                        .findById(ingredienteCommand.getUnitaDiMisura().getId())
                        .orElseThrow(()-> new RuntimeException("Unità di misura non trovata!")));
            }else{
                //add new ingredient
                Ingrediente ingrediente = ingredienteCommandToIngrediente.convert(ingredienteCommand);
                ingrediente.setRicetta(ricetta);
                ricetta.addIngredienti(ingrediente);
            }
            Ricetta ricettaSaved = ricettaRepository.save(ricetta);

            Optional<Ingrediente> ingredienteSaved = ricettaSaved.getIngredienti().stream()
                    .filter(ingredientiRicetta -> ingredientiRicetta.getId().equals(ingredienteCommand.getId())).findFirst();

            //check by nome descrizione
            if(!ingredienteSaved.isPresent()){
                ingredienteSaved = ricettaSaved.getIngredienti().stream()
                        .filter(ingredientiRicetta -> ingredientiRicetta.getDescrizione().equalsIgnoreCase(ingredienteCommand.getDescrizione()))
                        .filter(ingredientiRicetta -> ingredientiRicetta.getQuantita().equals(ingredienteCommand.getQuantita()))
                        .filter(ingredientiRicetta -> ingredientiRicetta.getUnitaDiMisura().getId().equals(ingredienteCommand.getUnitaDiMisura().getId()))
                        .findFirst();
            }
            return ingredienteToIngredienteCommand.convert(ingredienteSaved.get());
        }
    }

    @Override
    public void deleteIngredienteById(Long idIngrediente, Long idRicetta) {
        Optional<Ricetta> ricettaOptional = ricettaRepository.findById(idRicetta);
        if(!ricettaOptional.isPresent()){
            log.error("Ricetta non trovata");
        }else{
            Ricetta ricetta = ricettaOptional.get();
            Optional<Ingrediente> ingredienteOptional = ricetta.getIngredienti().stream()
                    .filter(ingrediente -> ingrediente.getId().equals(idIngrediente)).findFirst();
            if(ingredienteOptional.isPresent()){
                Ingrediente ingredienteToDelete = ingredienteOptional.get();
                ingredienteToDelete.setRicetta(null);
                ricetta.getIngredienti().remove(ingredienteOptional.get());
                ricettaRepository.save(ricetta);
            }else{
                log.error("Ingrediente non trovato");
            }
        }
    }
}
