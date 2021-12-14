package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.model.Ingrediente;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredienteToIngredienteCommand implements Converter<Ingrediente, IngredienteCommand> {

    @Synchronized
    @Nullable
    @Override
    public IngredienteCommand convert(Ingrediente source) {
        if(source == null){
            return null;
        }
        final IngredienteCommand ingredienteCommand = new IngredienteCommand();
        ingredienteCommand.setId(source.getId());
        ingredienteCommand.setDescrizione(source.getDescrizione());
        ingredienteCommand.setQuantita(source.getQuantita());
        ingredienteCommand.setUnitaDiMisura(source.getUnitaDiMisura());
        return ingredienteCommand;
    }
}
