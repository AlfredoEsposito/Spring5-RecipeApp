package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RicettaServiceImpl implements RicettaService{

    private final RicettaRepository ricettaRepository;

    public RicettaServiceImpl(RicettaRepository ricettaRepository) {
        this.ricettaRepository = ricettaRepository;
    }

    @Override
    public Set<Ricetta> getRicette() {
        Set<Ricetta> ricette = new HashSet<>();
        ricettaRepository.findAll().iterator().forEachRemaining(ricette::add);
        return ricette;
    }
}
