package fr.univ_lille.alom.pokemon_type_api.model;

import java.util.List;

public record PokemonType(
        int id,
        String name,
        Sprites sprites,
        List<String> types) {

}
