package fr.univ_lille.alom.pokemon_type_api.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univ_lille.alom.pokemon_type_api.model.PokemonType;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

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
                .filter(p -> p.name().equals(name))
                .findFirst();
        return pokemon.orElse(null);
    }

    @Override
    public List<PokemonType> findAllPokemonTypes() {
        return pokemons;
    }

    @Override
    public List<PokemonType> findPokemonTypesByTypes(String[] types) {
        System.out.println("Loading Pokemon information for Pokemon types " + Arrays.toString(types));
        return pokemons.stream()
                .filter(p -> Arrays.asList(types).containsAll(p.types()))
                .toList();
    }

    @Override
    public List<PokemonType> findAllPokemonTypesSorted(String orderBy) {
        System.out.println("Loading Pokemon information for Pokemon sorted by " + orderBy);
        return pokemons.stream()
                .sorted((p1, p2) -> {
                    switch (orderBy) {
                        case "id":
                            return Integer.compare(p1.id(), p2.id());
                        case "name":
                            return p1.name().compareTo(p2.name());
                        default:
                            return 0;
                    }
                })
                .toList();
    }
}