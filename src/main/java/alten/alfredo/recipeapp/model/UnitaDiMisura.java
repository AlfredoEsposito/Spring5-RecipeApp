package alten.alfredo.recipeapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnitaDiMisura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unitàMisura;

    //relazione 1:1 unidirezionale il che significa che non dichiaro nessun attributo della classe concorrente

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitàMisura() {
        return unitàMisura;
    }

    public void setUnitàMisura(String unitàMisura) {
        this.unitàMisura = unitàMisura;
    }

}
