package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.model.Categoria;
import alten.alfredo.recipeapp.model.Ricetta;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RicettaToRicettaCommand implements Converter<Ricetta, RicettaCommand> {

    @Synchronized
    @Nullable
    @Override
    public RicettaCommand convert(Ricetta source) {
        if(source==null){
            return null;
        }
        final RicettaCommand ricettaCommand = new RicettaCommand();
        ricettaCommand.setId(source.getId());
        ricettaCommand.setDescrizione(source.getDescrizione());
        ricettaCommand.setTempoPreparazione(source.getTempoPreparazione());
        ricettaCommand.setTempoCottura(source.getTempoCottura());
        ricettaCommand.setPorzioni(source.getPorzioni());
        ricettaCommand.setUrl(source.getUrl());
        ricettaCommand.setDirections(source.getDirections());
        ricettaCommand.setNote(source.getNote());
        ricettaCommand.setDifficolta(source.getDifficolta());

        if (source.getCategorie() != null && source.getCategorie().size() > 0){
            source.getCategorie()
                    .forEach((Categoria categoria) -> ricettaCommand.getCategorie().add(categoria));
        }

        if (source.getIngredienti() != null && source.getIngredienti().size() > 0){
            source.getIngredienti()
                    .forEach(ingrediente -> ricettaCommand.getIngredienti().add(ingrediente));
        }

        return ricettaCommand;
    }

}
