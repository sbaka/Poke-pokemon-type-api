package fr.univ_lille.alom.pokemon_type_api;

import java.util.List;

record PokemonType(
        int id,
        String name,
        Sprites sprites,
        List<String> types) {

}
