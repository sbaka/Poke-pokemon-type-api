package fr.univ_lille.alom.pokemon_type_api;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);

    List<PokemonType> getAllPokemonTypes();

    PokemonType getPokemonTypeByName(String name);
}