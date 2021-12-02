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
    private String udm;

    //relazione 1:1 unidirezionale il che significa che non dichiaro nessun attributo della classe concorrente

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String unitàMisura) {
        this.udm = unitàMisura;
    }

}
