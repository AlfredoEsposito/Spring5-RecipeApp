package alten.alfredo.recipeapp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoriaTest {

    Categoria categoria;

    @BeforeEach //Il metodo viene eseguito prima di ogni test viene creato uun nuovo oggetto Categoria.
    void setUp() {
        categoria = new Categoria();
    }

    @Test
    void getId() {
        Long idValue = 4l;
        categoria.setId(idValue);
        assertEquals(idValue, categoria.getId());
    }

    @Test
    void getNome() {
    }

    @Test
    void getRicette() {
    }
}