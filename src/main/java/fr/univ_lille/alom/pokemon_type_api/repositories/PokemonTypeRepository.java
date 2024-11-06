package fr.univ_lille.alom.pokemon_type_api.repositories;

import java.util.List;
import fr.univ_lille.alom.pokemon_type_api.model.PokemonType;

public interface PokemonTypeRepository {
    PokemonType findPokemonTypeById(int id);

    PokemonType findPokemonTypeByName(String name);

    List<PokemonType> findAllPokemonTypes();

    List<PokemonType> findPokemonTypesByTypes(String[] types);

    List<PokemonType> findAllPokemonTypesSorted(String orderBy);
}