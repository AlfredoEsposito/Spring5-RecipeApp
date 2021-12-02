package alten.alfredo.recipeapp.repositories;

import alten.alfredo.recipeapp.model.UnitaDiMisura;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitaDiMisuraRepository extends CrudRepository<UnitaDiMisura, Long> {

    Optional<UnitaDiMisura> findByUdm(String udm);

}
