package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RicettaServiceImpl implements RicettaService{

    private final RicettaRepository ricettaRepository;

    public RicettaServiceImpl(RicettaRepository ricettaRepository) {
        this.ricettaRepository = ricettaRepository;
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
}
