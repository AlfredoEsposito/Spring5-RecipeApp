package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.commands.RicettaCommand;
import alten.alfredo.recipeapp.model.Ricetta;
import alten.alfredo.recipeapp.services.RicettaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class RicettaControllerTest {

    RicettaController ricettaController;

    @Mock
    RicettaService ricettaService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ricettaController = new RicettaController(ricettaService);
        mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();
    }

    @Test
    void showRicettaByIdTest() throws Exception{
        Ricetta ricetta = new Ricetta();
        ricetta.setId(1L);

        when(ricettaService.getRicettaById(anyLong())).thenReturn(ricetta);

        //get() in perform() = @RequestMapping del controller
        mockMvc.perform(get("/ricetta/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("ricetta/show"))
                .andExpect(model().attributeExists("ricetta"));
    }

    @Test
    void newRicettaTest() throws Exception{
        RicettaCommand ricettaCommand = new RicettaCommand();
        mockMvc.perform(get("/ricetta/nuovaRicetta")).andExpect(status().isOk())
                .andExpect(view().name("ricetta/ricettaform"))
                .andExpect(model().attributeExists("ricetta"));
    }

    @Test
    void saveOrUpdateRicettaTest() throws Exception{
        RicettaCommand ricettaCommand = new RicettaCommand();
        ricettaCommand.setId(2L);
        when(ricettaService.saveRicettaCommand(any())).thenReturn(ricettaCommand);
        mockMvc.perform(post("/ricetta")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("nomeRicetta", "stringa qualsiasi"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ricetta/show/2"));
    }

    @Test
    void updateRicettaTest() throws Exception{
        RicettaCommand ricettaCommand = new RicettaCommand();
        ricettaCommand.setId(1L);
        when(ricettaService.getCommandById(anyLong())).thenReturn(ricettaCommand);
        mockMvc.perform(get("/ricetta/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("ricetta/ricettaform"))
                .andExpect(model().attributeExists("ricetta"));
    }
}