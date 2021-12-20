package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.converters.IngredienteToIngredienteCommand;
import alten.alfredo.recipeapp.converters.UnitaDiMisuraToUnitaDiMisuraCommand;
import alten.alfredo.recipeapp.model.Ingrediente;
import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class IngredienteServiceImplTest {

    IngredienteService ingredienteService;

    @Mock
    RicettaRepository ricettaRepository;

    private final IngredienteToIngredienteCommand ingredienteConverter;

    public IngredienteServiceImplTest() {
        this.ingredienteConverter = new IngredienteToIngredienteCommand(new UnitaDiMisuraToUnitaDiMisuraCommand());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredienteService = new IngredienteServiceImpl(ingredienteConverter,ricettaRepository);
    }

    @Test
    void getByIdRicettaAndIdIngredienteTest() throws Exception{
        Ricetta ricetta = new Ricetta();
        ricetta.setId(1L);

        Ingrediente i1 = new Ingrediente();
        i1.setId(1L);
        Ingrediente i2 = new Ingrediente();
        i1.setId(2L);
        Ingrediente i3 = new Ingrediente();
        i1.setId(3L);

        ricetta.addIngredienti(i1);
        ricetta.addIngredienti(i2);
        ricetta.addIngredienti(i3);

        Optional<Ricetta> ricettaOptional = Optional.of(ricetta);
        when(ricettaRepository.findById(anyLong())).thenReturn(ricettaOptional);

        IngredienteCommand ingredienteCommand = ingredienteService.getByIdRicettaAndIdIngrediente(1L,3L);
        assertEquals(Long.valueOf(3L), ingredienteCommand.getId());
        assertEquals(Long.valueOf(1L), ingredienteCommand.getIdRicetta());

    }
}