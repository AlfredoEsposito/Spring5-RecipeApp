package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.commands.IngredienteCommand;
import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.services.IngredienteService;
import alten.alfredo.recipeapp.services.RicettaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredienteControllerTest {

    IngredienteController ingredienteController;

    @Mock
    RicettaService ricettaService;

    @Mock
    IngredienteService ingredienteService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredienteController = new IngredienteController(ricettaService, ingredienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredienteController).build();
    }

    @Test
    void listaIngredientiRicettaTest() throws Exception {
        RicettaCommand ricettaCommand = new RicettaCommand();
        ricettaCommand.setId(1L);

        when(ricettaService.getCommandById(anyLong())).thenReturn(ricettaCommand);

        mockMvc.perform(get("/ricetta/1/ingredienti"))
                .andExpect(status().isOk())
                .andExpect(view().name("ricetta/ingrediente/list"))
                .andExpect(model().attributeExists("ricetta"));
    }

    @Test
    void showIngredientiRicettaTest() throws Exception{
        IngredienteCommand ingredienteCommand = new IngredienteCommand();
        when(ingredienteService.getByIdRicettaAndIdIngrediente(anyLong(), anyLong())).thenReturn(ingredienteCommand);
        mockMvc.perform(get("/ricetta/1/ingrediente/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("ricetta/ingrediente/show"))
                .andExpect(model().attributeExists("ingrediente"));
    }
}