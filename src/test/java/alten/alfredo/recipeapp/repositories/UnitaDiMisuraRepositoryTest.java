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
    void findByUdmGrammi() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("grammi");
        assertEquals("grammi", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmKili() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("kili");
        assertEquals("kili", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmLitri() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("litri");
        assertEquals("litri", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmMillilitri() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("millilitri");
        assertEquals("millilitri", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmCucchiaio() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("cucchiaio");
        assertEquals("cucchiaio", udmOptional.get().getUdm());
    }

    @Test
    void findByUdmCucchiaino() {
        Optional<UnitaDiMisura> udmOptional =  udmRepository.findByUdm("cucchiaino");
        assertEquals("cucchiaino", udmOptional.get().getUdm());
    }

}