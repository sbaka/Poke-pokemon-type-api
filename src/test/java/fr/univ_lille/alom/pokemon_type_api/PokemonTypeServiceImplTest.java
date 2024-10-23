package fr.univ_lille.alom.pokemon_type_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Locale;

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

    @Test
    void pokemonNames_shouldBeTranslated_usingLocaleResolver() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);

        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        when(pokemonTypeRepository.findPokemonTypeById(25)).thenReturn(new PokemonType(25, "Toto", null, null));

        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pikachu = pokemonTypeService.getPokemonType(25);

        assertEquals("Pikachu-FRENCH", pikachu.name());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
    }

    @Test
    void allPokemonNames_shouldBeTranslated_usingLocaleResolver() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);

        var pikachu = new PokemonType(25, null, null, null);
        var raichu = new PokemonType(26, null, null, null);
        when(pokemonTypeRepository.findAllPokemonTypes()).thenReturn(List.of(pikachu, raichu));

        // on simule le repository de traduction
        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");
        when(translationRepository.getPokemonName(26, Locale.FRENCH)).thenReturn("Raichu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pokemonTypes = pokemonTypeService.getAllPokemonTypes();

        assertEquals("Pikachu-FRENCH", pokemonTypes.get(0).name());
        assertEquals("Raichu-FRENCH", pokemonTypes.get(1).name());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
        verify(translationRepository).getPokemonName(26, Locale.FRENCH);
    }
}