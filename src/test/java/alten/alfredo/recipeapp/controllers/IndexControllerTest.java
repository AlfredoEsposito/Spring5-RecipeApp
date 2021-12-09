package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.services.RicettaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    Model model;

    @Mock
    RicettaService ricettaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(ricettaService);
    }

    @Test
    void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {
        String viewPage = indexController.getIndexPage(model);
        assertEquals("index", viewPage);
        verify(ricettaService, times(1)).getRicette();
        verify(model, times(1)).addAttribute(eq("ricette"), anySet());
    }
}