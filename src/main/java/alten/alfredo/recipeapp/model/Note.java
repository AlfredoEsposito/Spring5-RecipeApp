package alten.alfredo.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "ricetta")
@Entity
public class Note {

    //Il database genererà automaticamente l'id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //L'annotation @Lob indica un attributo di un'entità di grandi dimensioni. Ha due varianti : CLOB e BLOB
    @Lob
    private String noteRicetta;

    //Aggiungere l'annotation @OneToOne sull'attributo della classe concorrente per stabilire la relazione 1:1
    //Nel nostri caso d'uso non è necessario specificare un tipo di cascade
    @OneToOne
    private Ricetta ricetta;

}
