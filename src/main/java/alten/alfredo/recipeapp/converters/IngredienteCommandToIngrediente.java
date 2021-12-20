package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.model.Ingrediente;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredienteCommandToIngrediente implements Converter<IngredienteCommand, Ingrediente> {

    private  final UnitaDiMisuraCommandToUnitaDiMisura udmConverter;

    public IngredienteCommandToIngrediente(UnitaDiMisuraCommandToUnitaDiMisura udmConverter) {
        this.udmConverter = udmConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingrediente convert(IngredienteCommand source) {
        if(source == null){
            return null;
        }
        final Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(source.getId());
        ingrediente.setDescrizione(source.getDescrizione());
        ingrediente.setQuantita(source.getQuantita());
        ingrediente.setUnitaDiMisura(udmConverter.convert(source.getUnitaDiMisura()));
        return ingrediente;
    }
}
