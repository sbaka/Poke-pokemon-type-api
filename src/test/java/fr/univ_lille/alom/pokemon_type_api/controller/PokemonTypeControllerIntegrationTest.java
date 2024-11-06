package fr.univ_lille.alom.pokemon_type_api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import fr.univ_lille.alom.pokemon_type_api.model.PokemonType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonTypeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PokemonTypeController controller;

    @Test
    void pokemonTypeController_shouldBeInstanciated() {
        assertNotNull(controller);
    }

    @Test
    void getPokemon_withId25_ShouldReturnPikachu() throws Exception {
        var url = "http://localhost:" + port + "/pokemon-types/25";

        var pikachu = this.restTemplate.getForObject(url, PokemonType.class);

        assertNotNull(pikachu);
        assertEquals(25, pikachu.id());
        assertEquals("pikachu", pikachu.name());
    }

    @Test
    void getPokemon_withId1_shouldReturnBulbasaur() {
        var bulbasaur = this.restTemplate.getForObject("http://localhost:" + port + "/pokemon-types/1",
                PokemonType.class);
        assertNotNull(bulbasaur);
        assertEquals(1, bulbasaur.id());
        assertEquals("Bulbasaur", bulbasaur.name());
    }

    @Test
    void getPokemon_withId1AndFrenchAcceptLanguage_shouldReturnBulbizarre() {
        var headers = new HttpHeaders();
        headers.setAcceptLanguageAsLocales(List.of(Locale.FRENCH));

        var httpRequest = new HttpEntity<>(headers);

        var bulbizarreResponseEntity = this.restTemplate.exchange("http://localhost:" + port + "/pokemon-types/1",
                HttpMethod.GET, httpRequest, PokemonType.class);
        var bulbizarre = bulbizarreResponseEntity.getBody();

        assertNotNull(bulbizarre);
        assertEquals(1, bulbizarre.id());
        assertEquals("Bulbizarre", bulbizarre.name());
    }
}