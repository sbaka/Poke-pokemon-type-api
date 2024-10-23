package fr.univ_lille.alom.pokemon_type_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.context.i18n.LocaleContextHolder;

@Service
class PokemonTypeServiceImpl implements PokemonTypeService {

    public PokemonTypeRepository pokemonTypeRepository;
    public TranslationRepository translationRepository;

    PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        Locale locale = LocaleContextHolder.getLocale();
        var tempPoke = pokemonTypeRepository.findPokemonTypeById(id);
        String translatedName = translationRepository.getPokemonName(id, locale);

        PokemonType updatedPoke = new PokemonType(tempPoke.id(), translatedName, tempPoke.sprites(), tempPoke.types());

        return updatedPoke;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        var listOfPoke = pokemonTypeRepository.findAllPokemonTypes();
        List<PokemonType> finalList = new ArrayList<>();
        for (PokemonType pokemonType : listOfPoke) {
            finalList.add(getPokemonType(pokemonType.id()));
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

    @Override
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

}
