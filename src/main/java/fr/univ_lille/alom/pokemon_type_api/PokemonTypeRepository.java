package fr.univ_lille.alom.pokemon_type_api;

import java.util.List;

interface PokemonTypeRepository {
    PokemonType findPokemonTypeById(int id);

    PokemonType findPokemonTypeByName(String name);

    List<PokemonType> findAllPokemonTypes();
}