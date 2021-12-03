package alten.alfredo.recipeapp.bootstrap;

import alten.alfredo.recipeapp.enumeration.Difficolta;
import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RicettaRepository ricettaRepository;

    public DataLoader(RicettaRepository ricettaRepository) {
        this.ricettaRepository = ricettaRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ricettaRepository.saveAll(listaRicette());
        log.debug("Loading bootstrap data");
    }

    private List<Ricetta> listaRicette(){
        List<Ricetta> ricette = new ArrayList<>();

        Ricetta pizzafritta = new Ricetta();
        pizzafritta.setDescrizione("Pizza Fritta");
        pizzafritta.setDifficolta(Difficolta.MEDIA);
        pizzafritta.setTempoPreparazione(20);
        pizzafritta.setTempoCottura(7);
        ricette.add(pizzafritta);

        Ricetta spaghettiVongole = new Ricetta();
        spaghettiVongole.setDescrizione("Spaghetti alle vongole");
        spaghettiVongole.setDifficolta(Difficolta.FACILE);
        spaghettiVongole.setTempoPreparazione(30);
        spaghettiVongole.setTempoCottura(15);
        ricette.add(spaghettiVongole);

        return ricette;
    }
}
