package alten.alfredo.recipeapp.commands;

import alten.alfredo.recipeapp.model.UnitaDiMisura;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredienteCommand {

    private Long id;
    private String descrizione;
    private BigDecimal quantita;
    private UnitaDiMisura unitaDiMisura;

}
