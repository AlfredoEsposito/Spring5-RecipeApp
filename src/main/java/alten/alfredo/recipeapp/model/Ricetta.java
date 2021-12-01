package alten.alfredo.recipeapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public Integer getTempoPreparazione() {
        return tempoPreparazione;
    }

    public void setTempoPreparazione(Integer tempoPreparazione) {
        this.tempoPreparazione = tempoPreparazione;
    }

    public Integer getTempoCottura() {
        return tempoCottura;
    }

    public void setTempoCottura(Integer tempoCottura) {
        this.tempoCottura = tempoCottura;
    }

    public Integer getDosi() {
        return dosi;
    }

    public void setDosi(Integer dosi) {
        this.dosi = dosi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Byte[] getImmagine() {
        return immagine;
    }

    public void setImmagine(Byte[] immagine) {
        this.immagine = immagine;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
