package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.UnitaDiMisuraCommand;
import alten.alfredo.recipeapp.model.UnitaDiMisura;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitaDiMisuraToUnitaDiMisuraCommand implements Converter<UnitaDiMisura, UnitaDiMisuraCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitaDiMisuraCommand convert(UnitaDiMisura source) {
        if(source == null){
            return null;
        }
        final UnitaDiMisuraCommand udmc = new UnitaDiMisuraCommand();
        udmc.setId(source.getId());
        udmc.setUdm(source.getUdm());
        return udmc;
    }
}
