package alten.alfredo.recipeapp.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;
    private BigDecimal quantita;

    @ManyToOne
    private Ricetta ricetta;

    //relazione 1:1 unidirezionale, nella classe concorrente non ho dichiarato l'attributo del tipo di questa classe
    @OneToOne(fetch = FetchType.EAGER)
    private UnitaDiMisura unitaDiMisura;

}
