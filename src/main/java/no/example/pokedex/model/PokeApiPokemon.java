package no.example.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiPokemon(int id, String name, int height, int weight,
                             List<TypeSlot> types, Sprites sprites) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TypeSlot(int slot, TypeRef type) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TypeRef(String name) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Sprites(@JsonProperty("front_default") String frontDefault) {}
}
