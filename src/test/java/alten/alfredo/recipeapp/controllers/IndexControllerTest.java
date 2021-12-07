package alten.alfredo.recipeapp.controllers;

import alten.alfredo.recipeapp.services.RicettaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void getIndexPage() {
        String viewPage = indexController.getIndexPage(model);
        assertEquals("index", viewPage);
        verify(ricettaService, times(1)).getRicette();
        verify(model, times(1)).addAttribute(eq("ricette"), anySet());
    }
}