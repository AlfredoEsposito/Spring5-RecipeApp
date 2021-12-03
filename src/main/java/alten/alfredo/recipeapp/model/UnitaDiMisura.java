package alten.alfredo.recipeapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UnitaDiMisura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String udm;

    //relazione 1:1 unidirezionale il che significa che non dichiaro nessun attributo della classe concorrente

}
