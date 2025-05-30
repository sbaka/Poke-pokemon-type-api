package fr.univ_lille.alom.pokemon_type_api.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univ_lille.alom.pokemon_type_api.model.PokemonType;
import fr.univ_lille.alom.pokemon_type_api.services.PokemonTypeService;

public class PokemonTypeControllerTest {
    @Test
    void getPokemonType_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType(25, "pikachu", null, List.of(), 0, 0, 0, null);
        when(service.getPokemonType(25)).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromId(25);
        assertEquals("pikachu", pokemon.name());

        verify(service).getPokemonType(25);
    }

    @Test
    void getAllPokemonTypes_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        controller.getAllPokemonTypes(null, null);

        verify(service).getAllPokemonTypes();
    }

    @Test
    void getAllPokemonTypes_withTypes_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        controller.getAllPokemonTypes("fire,water", null);

        verify(service).getPokemonTypesByTypes(new String[]{"fire", "water"});
    }

    @Test
    void getAllPokemonTypes_withOrderBy_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        controller.getAllPokemonTypes(null, "name");

        verify(service).getAllPokemonTypesSorted("name");
    }

    @Test
    void pokemonTypeController_shouldBeAnnotated() {
        var controllerAnnotation = PokemonTypeController.class.getAnnotation(RestController.class);
        assertNotNull(controllerAnnotation);

        var requestMappingAnnotation = PokemonTypeController.class.getAnnotation(RequestMapping.class);
        assertArrayEquals(new String[] { "/pokemon-types" }, requestMappingAnnotation.value());
    }

    @Test
    void getPokemonTypeFromId_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromId = PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromId", int.class);
        var getMapping = getPokemonTypeFromId.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[] { "/{id}" }, getMapping.value());
    }

    @Test
    void getAllPokemonTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes = PokemonTypeController.class.getDeclaredMethod("getAllPokemonTypes", String.class, String.class);
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
    }
}
