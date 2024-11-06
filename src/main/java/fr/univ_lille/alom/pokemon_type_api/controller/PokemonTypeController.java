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

    /**
     * @param pokemonTypeService
     */
    PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/{id}")
    public PokemonType getPokemonTypeFromId(@PathVariable int id) {
        return pokemonTypeService.getPokemonType(id);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping(value = "/", params = "types")
    public List<PokemonType> getPokemonTypesByTypes(@RequestParam String types) {
        return pokemonTypeService.getPokemonTypesByTypes(types.split(","));
    }

    @GetMapping(value = "/", params = "orderBy")
    public List<PokemonType> getAllPokemonTypesSorted(@RequestParam String orderBy) {
        return pokemonTypeService.getAllPokemonTypesSorted(orderBy);
    }

    @GetMapping(value = "/", params = "name")
    public PokemonType getPokemonTypeFromName(@RequestParam String name) {
        return pokemonTypeService.getPokemonTypeByName(name);
    }
}