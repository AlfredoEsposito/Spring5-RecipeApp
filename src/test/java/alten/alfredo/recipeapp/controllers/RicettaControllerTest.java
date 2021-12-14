package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.model.Ricetta;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


class RicettaControllerTest {

    RicettaController ricettaController;

    @Mock
    RicettaService ricettaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ricettaController = new RicettaController(ricettaService);
    }

    @Test
    void getRicettaByIdTest() throws Exception{
        Ricetta ricetta = new Ricetta();
        ricetta.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();

        when(ricettaService.findById(anyLong())).thenReturn(ricetta);

        //get() in perform() = @RequestMapping del controller
        mockMvc.perform(get("/ricetta/show/1")).andExpect(status().isOk()).andExpect(view().name("ricetta/show"));
    }
}