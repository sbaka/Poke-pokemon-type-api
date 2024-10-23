package fr.univ_lille.alom.pokemon_type_api;

import java.util.Locale;

public interface TranslationRepository {
    String getPokemonName(int id, Locale locale);
}
