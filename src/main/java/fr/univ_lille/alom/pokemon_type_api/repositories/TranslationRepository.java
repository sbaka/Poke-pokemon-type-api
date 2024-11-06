package fr.univ_lille.alom.pokemon_type_api.repositories;

import java.util.Locale;

public interface TranslationRepository {
    String getPokemonName(int id, Locale locale);
}
