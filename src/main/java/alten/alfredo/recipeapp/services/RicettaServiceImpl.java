package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.converters.RicettaCommandToRicetta;
import alten.alfredo.recipeapp.converters.RicettaToRicettaCommand;
import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RicettaServiceImpl implements RicettaService{

    private final RicettaRepository ricettaRepository;
    private final RicettaToRicettaCommand ricettaToRicettaCommand;
    private final RicettaCommandToRicetta ricettaCommandToRicetta;

    public RicettaServiceImpl(RicettaRepository ricettaRepository, RicettaToRicettaCommand ricettaToRicettaCommand, RicettaCommandToRicetta ricettaCommandToRicetta) {
        this.ricettaRepository = ricettaRepository;
        this.ricettaToRicettaCommand = ricettaToRicettaCommand;
        this.ricettaCommandToRicetta = ricettaCommandToRicetta;
    }

    @Override
    public Set<Ricetta> getRicette() {
        log.debug("I'm in the service'");
        Set<Ricetta> ricette = new HashSet<>();
        ricettaRepository.findAll().iterator().forEachRemaining(ricette::add);
        return ricette;
    }

    @Override
    public Ricetta findById(Long l) {
        Optional<Ricetta> ricetta = ricettaRepository.findById(l);
        if(!ricetta.isPresent()){
            throw new RuntimeException("Ricetta non trovata!");
        }
        return ricetta.get();
    }

    @Override
    @Transactional
    public RicettaCommand saveRicettaCommand(RicettaCommand ricettaCommand) {
        Ricetta ricetta = ricettaCommandToRicetta.convert(ricettaCommand);
        Ricetta ricettaSaved = ricettaRepository.save(ricetta);
        log.debug("id ricetta slavata : " +ricettaSaved.getId());
        return ricettaToRicettaCommand.convert(ricettaSaved);
    }
}
