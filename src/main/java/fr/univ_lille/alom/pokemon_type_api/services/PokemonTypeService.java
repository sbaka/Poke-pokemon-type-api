package fr.univ_lille.alom.pokemon_type_api.services;

import java.util.List;

import fr.univ_lille.alom.pokemon_type_api.model.PokemonType;
import fr.univ_lille.alom.pokemon_type_api.repositories.PokemonTypeRepository;
import fr.univ_lille.alom.pokemon_type_api.repositories.TranslationRepository;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);

    List<PokemonType> getAllPokemonTypes();

    PokemonType getPokemonTypeByName(String name);

    List<PokemonType> getPokemonTypesByTypes(String[] split);

    List<PokemonType> getAllPokemonTypesSorted(String orderBy);

    void setTranslationRepository(TranslationRepository translationRepository);

    void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository);
}