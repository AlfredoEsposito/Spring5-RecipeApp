package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.UnitaDiMisuraCommand;
import alten.alfredo.recipeapp.converters.UnitaDiMisuraToUnitaDiMisuraCommand;
import alten.alfredo.recipeapp.model.UnitaDiMisura;
import alten.alfredo.recipeapp.repositories.UnitaDiMisuraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UnitaDiMisuraServiceImplTest {

    UnitaDiMisuraService udmService;
    UnitaDiMisuraToUnitaDiMisuraCommand udmConverter = new UnitaDiMisuraToUnitaDiMisuraCommand();

    @Mock
    UnitaDiMisuraRepository udmRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        udmService = new UnitaDiMisuraServiceImpl(udmRepository, udmConverter);
    }

    @Test
    void listAllUdm() {
        Set<UnitaDiMisura> udmSet= new HashSet<>();
        UnitaDiMisura udm1 = new UnitaDiMisura();
        udm1.setId(1L);
        UnitaDiMisura udm2 = new UnitaDiMisura();
        udm2.setId(2L);
        udmSet.add(udm1);
        udmSet.add(udm2);

        when(udmRepository.findAll()).thenReturn(udmSet);

        Set<UnitaDiMisuraCommand> udmCommandSet = udmService.listAllUdm();

        assertEquals(udmCommandSet.size(),2);
        verify(udmRepository, times(1)).findAll();
    }
}