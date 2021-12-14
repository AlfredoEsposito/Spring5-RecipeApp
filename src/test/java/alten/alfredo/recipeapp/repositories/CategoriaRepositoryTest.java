package alten.alfredo.recipeapp.repositories;

import alten.alfredo.recipeapp.model.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    void findByNomeGreca() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("Greca");
        assertEquals("Greca", categoriaOptional.get().getNome());
    }

    @Test
    void findByNomeItaliana() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("Italiana");
        assertEquals("Italiana", categoriaOptional.get().getNome());
    }

    @Test
    void findByNomeMessicana() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("Messicana");
        assertEquals("Messicana", categoriaOptional.get().getNome());
    }

    @Test
    void findByNomeFastFood() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("Fast Food");
        assertEquals("Fast Food", categoriaOptional.get().getNome());
    }
}