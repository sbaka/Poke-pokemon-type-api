package fr.univ_lille.alom.pokemon_type_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import fr.univ_lille.alom.pokemon_type_api.model.PokemonType;
import fr.univ_lille.alom.pokemon_type_api.repositories.PokemonTypeRepository;
import fr.univ_lille.alom.pokemon_type_api.repositories.TranslationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    public PokemonTypeRepository pokemonTypeRepository;
    public TranslationRepository translationRepository;

    PokemonTypeServiceImpl() {
    }

    @Override
    public PokemonType getPokemonType(int id) {
        Locale locale = LocaleContextHolder.getLocale();
        PokemonType tempPoke = pokemonTypeRepository.findPokemonTypeById(id);
        // if (tempPoke == null) {
        // throw new IllegalArgumentException("No pokemon found with id " + id);
        // }
        String translatedName = translationRepository.getPokemonName(id, locale);

        PokemonType updatedPoke = new PokemonType(tempPoke.id(), translatedName, tempPoke.sprites(), tempPoke.types(),
                tempPoke.baseExperience(), tempPoke.height(), tempPoke.weight(), tempPoke.stats());

        return updatedPoke;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        var listOfPoke = pokemonTypeRepository.findAllPokemonTypes();
        List<PokemonType> finalList = new ArrayList<>();
        for (PokemonType pokemonType : listOfPoke) {
            try {
                Locale locale = LocaleContextHolder.getLocale();
                String translatedName = translationRepository.getPokemonName(pokemonType.id(), locale);
                PokemonType updatedPoke = new PokemonType(
                        pokemonType.id(),
                        translatedName,
                        pokemonType.sprites(),
                        pokemonType.types(),
                        pokemonType.baseExperience(),
                        pokemonType.height(),
                        pokemonType.weight(),
                        pokemonType.stats());
                finalList.add(updatedPoke);
            } catch (IllegalArgumentException e) {
                finalList.add(pokemonType);
            }
        }
        return finalList;
    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getPokemonTypesByTypes(String[] types) {
        return pokemonTypeRepository.findPokemonTypesByTypes(types);
    }

    @Override
    public List<PokemonType> getAllPokemonTypesSorted(String orderBy) {
        return pokemonTypeRepository.findAllPokemonTypesSorted(orderBy);
    }

    @Autowired
    @Override
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Autowired
    @Override
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

}
