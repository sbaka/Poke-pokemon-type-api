package fr.univ_lille.alom.pokemon_type_api.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univ_lille.alom.pokemon_type_api.model.Translation;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    record Key(Locale locale, int pokemonId) {
    }

    private Map<Key, Translation> translations;

    private ObjectMapper objectMapper = new ObjectMapper();

    public TranslationRepositoryImpl() {
        try {
            loadTranslations("translations-en.json", Locale.ENGLISH);
            loadTranslations("translations-fr.json", Locale.FRENCH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTranslations(String fileName, Locale locale) throws IOException {
        var resource = new ClassPathResource(fileName);
        var translationsArray = objectMapper.readValue(resource.getInputStream(), Translation[].class);
        for (Translation translation : translationsArray) {
            translations.put(new Key(locale, translation.id()), translation);
        }
        // (1, fr) Translation(1,Bulbizarre)
        // (1, en) Translation(1,Bulbasaur)
    }

    @Override
    public String getPokemonName(int id, Locale locale) {
        var key = new Key(locale, id);
        var translation = translations.get(key);
        return translation != null ? translation.name() : null;
    }
}