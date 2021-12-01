package alten.alfredo.recipeapp.model;

import javax.persistence.*;
import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getQuantita() {
        return quantita;
    }

    public void setQuantita(BigDecimal quantita) {
        this.quantita = quantita;
    }

    public Ricetta getRicetta() {
        return ricetta;
    }

    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }

    public UnitaDiMisura getUnitaDiMisura() {
        return unitaDiMisura;
    }

    public void setUnitaDiMisura(UnitaDiMisura unitaDiMisura) {
        this.unitaDiMisura = unitaDiMisura;
    }
}
