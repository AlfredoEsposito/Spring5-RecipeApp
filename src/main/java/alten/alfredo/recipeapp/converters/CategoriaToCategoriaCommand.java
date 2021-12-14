package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.CategoriaCommand;
import alten.alfredo.recipeapp.model.Categoria;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoriaToCategoriaCommand implements Converter<Categoria, CategoriaCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoriaCommand convert(Categoria source) {
        if(source == null){
            return null;
        }
        final CategoriaCommand categoriaCommand = new CategoriaCommand();
        categoriaCommand.setId(source.getId());
        categoriaCommand.setNome(source.getNome());
        return categoriaCommand;
    }
}
