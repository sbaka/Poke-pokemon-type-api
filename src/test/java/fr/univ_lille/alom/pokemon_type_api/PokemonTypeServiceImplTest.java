package fr.univ_lille.alom.pokemon_type_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PokemonTypeServiceImplTest {

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindById() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.getPokemonType(25);

        verify(pokemonTypeRepository).findPokemonTypeById(25);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.getAllPokemonTypes();

        verify(pokemonTypeRepository).findAllPokemonTypes();
    }

    @Test
    void applicationContext_shouldLoadPokemonTypeService() {
        try (var context = new AnnotationConfigApplicationContext("fr.univ_lille.alom.tp.pokemon_type_api")) {
            var serviceByName = context.getBean("pokemonTypeServiceImpl");
            var serviceByClass = context.getBean(PokemonTypeService.class);

            assertEquals(serviceByName, serviceByClass);
            assertNotNull(serviceByName);
            assertNotNull(serviceByClass);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    @Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring() {
        try (var context = new AnnotationConfigApplicationContext("fr.univ_lille.alom.tp.pokemon_type_api")) {
            var service = context.getBean(PokemonTypeServiceImpl.class);
            assertNotNull(service.pokemonTypeRepository);
        } catch (BeansException e) {

            e.printStackTrace();
        }
    }

}