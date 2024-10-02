package fr.univ_lille.alom.pokemon_type_api;

import java.util.List;

import org.springframework.stereotype.Service;

// import java.util.ArrayList;

@Service
class PokemonTypeServiceImpl implements PokemonTypeService {

    // private List<PokemonType> pokemonTypes;
    public PokemonTypeRepository pokemonTypeRepository;

    PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository) {
        // this.pokemonTypes = new ArrayList<>();
        // Initialize with some data if needed
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return pokemonTypeRepository.findPokemonTypeById(id);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeRepository.findAllPokemonTypes();
    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }
}
