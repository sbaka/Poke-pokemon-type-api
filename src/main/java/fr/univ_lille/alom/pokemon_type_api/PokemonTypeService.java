package fr.univ_lille.alom.pokemon_type_api;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);

    List<PokemonType> getAllPokemonTypes();

    PokemonType getPokemonTypeByName(String name);

    List<PokemonType> getPokemonTypesByTypes(String[] split);

    List<PokemonType> getAllPokemonTypesSorted(String orderBy);

    void setTranslationRepository(TranslationRepository translationRepository);

    void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository);
}