package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.model.Categoria;
import alten.alfredo.recipeapp.model.Ricetta;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RicettaCommandToRicetta implements Converter<RicettaCommand, Ricetta> {

    @Synchronized
    @Nullable
    @Override
    public Ricetta convert(RicettaCommand source) {
        if(source==null){
            return null;
        }
        final Ricetta ricetta = new Ricetta();
        ricetta.setId(source.getId());
        ricetta.setDescrizione(source.getDescrizione());
        ricetta.setTempoPreparazione(source.getTempoPreparazione());
        ricetta.setTempoCottura(source.getTempoCottura());
        ricetta.setPorzioni(source.getPorzioni());
        ricetta.setUrl(source.getUrl());
        ricetta.setDirections(source.getDirections());
        ricetta.setNote(source.getNote());
        ricetta.setDifficolta(source.getDifficolta());

        if (source.getCategorie() != null && source.getCategorie().size() > 0){
            source.getCategorie()
                    .forEach((Categoria categoria) -> ricetta.getCategorie().add(categoria));
        }

        if (source.getIngredienti() != null && source.getIngredienti().size() > 0){
            source.getIngredienti()
                    .forEach(ingrediente -> ricetta.getIngredienti().add(ingrediente));
        }

        return ricetta;
    }
}
