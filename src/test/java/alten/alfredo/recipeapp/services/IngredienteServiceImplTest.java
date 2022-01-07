package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.converters.IngredienteCommandToIngrediente;
import alten.alfredo.recipeapp.converters.IngredienteToIngredienteCommand;
import alten.alfredo.recipeapp.converters.UnitaDiMisuraCommandToUnitaDiMisura;
import alten.alfredo.recipeapp.converters.UnitaDiMisuraToUnitaDiMisuraCommand;
import alten.alfredo.recipeapp.model.Ingrediente;
import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.repositories.RicettaRepository;
import alten.alfredo.recipeapp.repositories.UnitaDiMisuraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class IngredienteServiceImplTest {

    IngredienteService ingredienteService;

    @Mock
    RicettaRepository ricettaRepository;

    @Mock
    UnitaDiMisuraRepository udmRepository;

    private final IngredienteToIngredienteCommand ingredienteToIngredienteCommand;
    private final IngredienteCommandToIngrediente ingredienteCommandToIngrediente;


    public IngredienteServiceImplTest() {
        this.ingredienteToIngredienteCommand = new IngredienteToIngredienteCommand(new UnitaDiMisuraToUnitaDiMisuraCommand());
        this.ingredienteCommandToIngrediente = new IngredienteCommandToIngrediente(new UnitaDiMisuraCommandToUnitaDiMisura());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredienteService = new IngredienteServiceImpl(ingredienteToIngredienteCommand, ingredienteCommandToIngrediente, ricettaRepository, udmRepository);
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

    @Test
    void saveIngredienteCommandTest() {
        IngredienteCommand ingredienteCommand = new IngredienteCommand();
        ingredienteCommand.setId(2L);
        ingredienteCommand.setIdRicetta(2L);

        Optional<Ricetta> ricettaOptional = Optional.of(new Ricetta());

        Ricetta savedRicetta = new Ricetta();
        savedRicetta.addIngredienti(new Ingrediente());
        savedRicetta.getIngredienti().stream().iterator().next().setId(2L);

        when(ricettaRepository.findById(anyLong())).thenReturn(ricettaOptional);
        when(ricettaRepository.save(any())).thenReturn(savedRicetta);

        IngredienteCommand savedIngredienteCommand = ingredienteService.saveIngredienteCommand(ingredienteCommand);

        assertEquals(Long.valueOf(2L), savedIngredienteCommand.getId());
    }

    @Test
    void deleteIngredienteByIdTest() {
    }
}