package alten.alfredo.recipeapp.repositories;

import alten.alfredo.recipeapp.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nomeCategoria);
}
