package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RicettaServiceImplTest {

    RicettaServiceImpl ricettaServiceImpl;

    @Mock
    RicettaRepository ricettaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ricettaServiceImpl = new RicettaServiceImpl(ricettaRepository);
    }

    @Test
    void getRicette() throws Exception{
        Ricetta ricetta = new Ricetta();
        HashSet ricette = new HashSet<>();
        ricette.add(ricetta);

        when(ricettaRepository.findAll()).thenReturn(ricette);

        Set<Ricetta> ricettee = ricettaServiceImpl.getRicette();

        assertEquals(ricettee.size(), 1);
        verify(ricettaRepository,times(1)).findAll();
    }
}