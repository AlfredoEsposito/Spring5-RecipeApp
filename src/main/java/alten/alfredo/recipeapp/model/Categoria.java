package alten.alfredo.recipeapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    //essendo questa una relazione bidirezionale, in entrambe le entità si dichara un attributo della classe  concorrente
    //mappedBy serve a non far creare un'ulteriore tabella di join, e a utilizzarne una, cioè quella creata nell'altra classe
    @ManyToMany(mappedBy = "categorie")
    private Set<Ricetta> ricette;

}
