package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.model.Ingrediente;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredienteToIngredienteCommand implements Converter<Ingrediente, IngredienteCommand> {

    private final UnitaDiMisuraToUnitaDiMisuraCommand udmConverter;

    public IngredienteToIngredienteCommand(UnitaDiMisuraToUnitaDiMisuraCommand udmConverter) {
        this.udmConverter = udmConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredienteCommand convert(Ingrediente source) {
        if(source == null){
            return null;
        }
        final IngredienteCommand ingredienteCommand = new IngredienteCommand();
        ingredienteCommand.setId(source.getId());
        if(source.getRicetta() != null){
            ingredienteCommand.setIdRicetta(source.getRicetta().getId());
        }
        ingredienteCommand.setDescrizione(source.getDescrizione());
        ingredienteCommand.setQuantita(source.getQuantita());
        ingredienteCommand.setUnitaDiMisura(udmConverter.convert(source.getUnitaDiMisura()));
        return ingredienteCommand;
    }
}
