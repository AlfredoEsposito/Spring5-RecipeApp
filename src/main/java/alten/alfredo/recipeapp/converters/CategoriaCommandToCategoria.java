package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.CategoriaCommand;
import alten.alfredo.recipeapp.model.Categoria;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoriaCommandToCategoria implements Converter<CategoriaCommand, Categoria> {

    @Synchronized
    @Nullable
    @Override
    public Categoria convert(CategoriaCommand source) {
        if(source == null){
            return null;
        }
        final Categoria categoria = new Categoria();
        categoria.setId(source.getId());
        categoria.setNome(source.getNome());
        return categoria;
    }
}
