package fr.univ_lille.alom.pokemon_type_api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json");

            var objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        Optional<PokemonType> pokemon = pokemons.stream()
                .filter(p -> p.id() == id)
                .findFirst();
        return pokemon.orElse(null);
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        Optional<PokemonType> pokemon = pokemons.stream()
                .filter(p -> p.name().equalsIgnoreCase(name))
                .findFirst();
        return pokemon.orElse(null);
    }

    @Override
    public List<PokemonType> findAllPokemonTypes() {
        return pokemons;
    }
}