package alten.alfredo.recipeapp.model;

import alten.alfredo.recipeapp.enumeration.Difficolta;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Ricetta {

    //Il database genererà automaticamente l'id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;
    private Integer tempoPreparazione;
    private Integer tempoCottura;
    private Integer dosi;
    private String url;
    @Lob
    private String directions;
    private String source;
    //L'annotation @Lob indica un attributo di un'entità di grandi dimensioni. Ha due varianti : CLOB e BLOB
    @Lob
    private Byte[] immagine;

    //Aggiungere l'annotation @OneToOne sull'attributo della classe concorrente per stabilire la relazione 1:1
    //Con il cascade type se andremo a cancellare una ricetta, verrà cancellata anche l'entità in relazione ad essa, in questo caso la Nota
    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    //l'attributo mappedby indica che la chiave esterna è nell'entità concorrente
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ricetta")
    private Set<Ingrediente> ingredienti = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Difficolta difficolta;

    //relazione n:n bidirezionale
    //andiamo a creare una join table definendo gli id
    @ManyToMany
    @JoinTable(name = "Ricetta_Categoria",
        joinColumns = @JoinColumn(name = "id_ricetta"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private Set<Categoria> categorie;

}
