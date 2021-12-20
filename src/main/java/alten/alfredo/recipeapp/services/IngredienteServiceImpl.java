package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.converters.IngredienteToIngredienteCommand;
import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredienteServiceImpl implements IngredienteService{

    private final IngredienteToIngredienteCommand ingredienteConverter;
    private final RicettaRepository ricettaRepository;

    public IngredienteServiceImpl(IngredienteToIngredienteCommand ingredienteConverter, RicettaRepository ricettaRepository) {
        this.ingredienteConverter = ingredienteConverter;
        this.ricettaRepository = ricettaRepository;
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
                .map(ingrediente -> ingredienteConverter.convert(ingrediente)).findFirst();
        if(!ingredienteCommandOptional.isPresent()){
            //todo implementare gestione dell'errore
            log.error("Ingrediente con id " +idIngrediente+ " non trovato");
        }
        return ingredienteCommandOptional.get();
    }
}
