package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.UnitaDiMisuraCommand;
import alten.alfredo.recipeapp.model.UnitaDiMisura;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitaDiMisuraCommandToUnitaDiMisura implements Converter<UnitaDiMisuraCommand, UnitaDiMisura> {

    @Synchronized
    @Nullable
    @Override
    public UnitaDiMisura convert(UnitaDiMisuraCommand source) {
        if(source == null){
            return null;
        }
        final UnitaDiMisura udm = new UnitaDiMisura();
        udm.setId(source.getId());
        udm.setUdm(source.getUdm());
        return udm;
    }
}
