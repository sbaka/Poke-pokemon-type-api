package fr.univ_lille.alom.pokemon_type_api.repositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univ_lille.alom.pokemon_type_api.model.Translation;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    record Key(Locale locale, int pokemonId) {
    }

    private Map<Key, Translation> translations;

    private ObjectMapper objectMapper;

    public TranslationRepositoryImpl() {
        try {
            translations = new java.util.HashMap<Key, Translation>();
            objectMapper = new ObjectMapper();
            InputStream translationFR = new ClassPathResource("translations-fr.json").getInputStream();
            InputStream translationEN = new ClassPathResource("translations-en.json").getInputStream();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Translation> translationsFR = Arrays
                    .asList(objectMapper.readValue(translationFR, Translation[].class));
            List<Translation> translationsEN = Arrays
                    .asList(objectMapper.readValue(translationEN, Translation[].class));

            for (Translation translation : translationsFR) {
                translations.put(new Key(Locale.FRANCE, translation.id()), translation);
                translations.put(new Key(Locale.FRENCH, translation.id()), translation);
                translations.put(new Key(Locale.CANADA_FRENCH, translation.id()), translation);
            }
            for (Translation translation : translationsEN) {
                translations.put(new Key(Locale.ENGLISH, translation.id()), translation);
                translations.put(new Key(Locale.UK, translation.id()), translation);
                translations.put(new Key(Locale.US, translation.id()), translation);
                translations.put(new Key(Locale.CANADA, translation.id()), translation);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPokemonName(int id, Locale locale) {
        Translation translation = translations.get(new Key(locale, id));
        if (translation == null) {
            throw new IllegalArgumentException("No translation found for pokemon id " + id + " in locale " + locale);
        }
        return translation.name();
    }
}