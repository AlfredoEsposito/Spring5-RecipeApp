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
    void findByNomeAmerican() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("American");
        assertEquals("American", categoriaOptional.get().getNome());
    }

    @Test
    void findByNomeItalian() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("Italian");
        assertEquals("Italian", categoriaOptional.get().getNome());
    }

    @Test
    void findByNomeMexican() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("Mexican");
        assertEquals("Mexican", categoriaOptional.get().getNome());
    }

    @Test
    void findByNomeFastFood() {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome("Fast Food");
        assertEquals("Fast Food", categoriaOptional.get().getNome());
    }
}