package fr.univ_lille.alom.pokemon_type_api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTypeRepositoryImplTest {

    private PokemonTypeRepositoryImpl repository = new PokemonTypeRepositoryImpl();

    @Test
    void findPokemonTypeById_with25_shouldReturnPikachu() {
        var pikachu = repository.findPokemonTypeById(25);
        assertNotNull(pikachu);
        assertEquals("pikachu", pikachu.name());
        assertEquals(25, pikachu.id());
    }

    @Test
    void findPokemonTypeById_with145_shouldReturnZapdos() {
        var zapdos = repository.findPokemonTypeById(145);
        assertNotNull(zapdos);
        assertEquals("zapdos", zapdos.name());
        assertEquals(145, zapdos.id());
    }

    @Test
    void findPokemonTypeByName_withEevee_shouldReturnEevee() {
        var eevee = repository.findPokemonTypeByName("eevee");
        assertNotNull(eevee);
        assertEquals("eevee", eevee.name());
        assertEquals(133, eevee.id());
    }

    @Test
    void findPokemonTypeByName_withMewTwo_shouldReturnMewTwo() {
        var mewtwo = repository.findPokemonTypeByName("mewtwo");
        assertNotNull(mewtwo);
        assertEquals("mewtwo", mewtwo.name());
        assertEquals(150, mewtwo.id());
    }

    @Test
    void findAllPokemonTypes_shouldReturn151Pokemons() {
        var pokemons = repository.findAllPokemonTypes();
        assertNotNull(pokemons);
        assertEquals(151, pokemons.size());
    }

    @Test
    void applicationContext_shouldLoadPokemonRepository() {

        try (var context = new AnnotationConfigApplicationContext("fr.univ_lille.alom.pokemon_type_api")) {
            var repoByName = context.getBean("pokemonTypeRepositoryImpl");
            var repoByClass = context.getBean(PokemonTypeRepository.class);

            assertEquals(repoByName, repoByClass);
            assertNotNull(repoByName);
            assertNotNull(repoByClass);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

}