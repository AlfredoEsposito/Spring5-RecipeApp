package alten.alfredo.recipeapp.repositories;

import alten.alfredo.recipeapp.model.UnitaDiMisura;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest // fornirà un database integrato per i test del repository
class UnitaDiMisuraRepositoryTest {

    @Autowired //Inietterà la dipendenza in questo integration test
    UnitaDiMisuraRepository udmRepository;

    @Test
    void findByUdmTeaspoon() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("Teaspoon");
        assertEquals("Teaspoon", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmTablespoon() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("Tablespoon");
        assertEquals("Tablespoon", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmCup() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("Cup");
        assertEquals("Cup", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmPinch() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("Pinch");
        assertEquals("Pinch", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmOunce() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("Ounce");
        assertEquals("Ounce", udmOptional.get().getUdm());
    }

}