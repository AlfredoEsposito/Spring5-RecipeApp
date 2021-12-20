package alten.alfredo.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredienteCommand {

    private Long id;
    private Long idRicetta;
    private String descrizione;
    private BigDecimal quantita;
    private UnitaDiMisuraCommand unitaDiMisura;

}
