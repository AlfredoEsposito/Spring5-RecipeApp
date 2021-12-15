package alten.alfredo.recipeapp.bootstrap;

import alten.alfredo.recipeapp.enumeration.Difficolta;
import alten.alfredo.recipeapp.model.Note;
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
        pizzafritta.setNomeRicetta("Pizza Fritta");
        pizzafritta.setDifficolta(Difficolta.MEDIA);
        pizzafritta.setTempoPreparazione(20);
        pizzafritta.setTempoCottura(7);
        pizzafritta.setNote(new Note());
        ricette.add(pizzafritta);

        Ricetta spaghettiVongole = new Ricetta();
        spaghettiVongole.setNomeRicetta("Spaghetti alle vongole");
        spaghettiVongole.setDifficolta(Difficolta.FACILE);
        spaghettiVongole.setTempoPreparazione(30);
        spaghettiVongole.setTempoCottura(15);
        spaghettiVongole.setNote(new Note());
        ricette.add(spaghettiVongole);

        return ricette;
    }
}
