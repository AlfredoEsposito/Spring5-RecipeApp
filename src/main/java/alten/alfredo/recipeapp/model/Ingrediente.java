package alten.alfredo.recipeapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = "ricetta")
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

    public Ingrediente() {
    }

    public Ingrediente(String descrizione, BigDecimal quantita, UnitaDiMisura unitaDiMisura) {
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.unitaDiMisura = unitaDiMisura;
    }

    public Ingrediente(String descrizione, BigDecimal quantita, UnitaDiMisura unitaDiMisura, Ricetta ricetta) {
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.unitaDiMisura = unitaDiMisura;
        this.ricetta = ricetta;
    }

}
