package alten.alfredo.recipeapp.converters;

import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.model.Ricetta;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RicettaCommandToRicetta implements Converter<RicettaCommand, Ricetta> {

    private final CategoriaCommandToCategoria categoriaConverter;
    private final IngredienteCommandToIngrediente ingredienetConverter;
    private final NoteCommandToNote noteConverter;

    public RicettaCommandToRicetta(CategoriaCommandToCategoria categoriaConverter, IngredienteCommandToIngrediente ingredienetConverter,
                                   NoteCommandToNote noteConverter) {
        this.categoriaConverter = categoriaConverter;
        this.ingredienetConverter = ingredienetConverter;
        this.noteConverter = noteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ricetta convert(RicettaCommand source) {
        if(source==null){
            return null;
        }
        final Ricetta ricetta = new Ricetta();
        ricetta.setId(source.getId());
        ricetta.setNomeRicetta(source.getNomeRicetta());
        ricetta.setTempoPreparazione(source.getTempoPreparazione());
        ricetta.setTempoCottura(source.getTempoCottura());
        ricetta.setPorzioni(source.getPorzioni());
        ricetta.setUrl(source.getUrl());
        ricetta.setProcedimento(source.getProcedimento());
        ricetta.setNote(noteConverter.convert(source.getNote()));
        ricetta.setDifficolta(source.getDifficolta());

        if (source.getCategorie() != null && source.getCategorie().size() > 0){
            source.getCategorie()
                    .forEach(categoria -> ricetta.getCategorie().add(categoriaConverter.convert(categoria)));
        }

        if (source.getIngredienti() != null && source.getIngredienti().size() > 0){
            source.getIngredienti()
                    .forEach(ingrediente -> ricetta.getIngredienti().add(ingredienetConverter.convert(ingrediente)));
        }

        return ricetta;
    }
}
