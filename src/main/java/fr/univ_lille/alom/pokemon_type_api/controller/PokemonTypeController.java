package fr.univ_lille.alom.pokemon_type_api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.univ_lille.alom.pokemon_type_api.model.PokemonType;
import fr.univ_lille.alom.pokemon_type_api.services.PokemonTypeService;

@RestController
@RequestMapping("/pokemon-types")
public class PokemonTypeController {
    private final PokemonTypeService pokemonTypeService;

    PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/{id}")
    public PokemonType getPokemonTypeFromId(@PathVariable int id) {
        return pokemonTypeService.getPokemonType(id);
    }

    @GetMapping
    public List<PokemonType> getAllPokemonTypes(
            @RequestParam(required = false) String types,
            @RequestParam(required = false) String orderBy) {
        
        if (types != null) {
            String[] typeArray = types.split(",");
            return pokemonTypeService.getPokemonTypesByTypes(typeArray);
        }
        
        if (orderBy != null) {
            return pokemonTypeService.getAllPokemonTypesSorted(orderBy);
        }
        
        return pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping(params = "name")
    public PokemonType getPokemonTypeFromName(@RequestParam String name) {
        return pokemonTypeService.getPokemonTypeByName(name);
    }
}
