package no.example.pokedex.model;

import java.util.List;

public record PokemonDTO(int id, String name, double heightMeters, double weightKg,
                         List<String> types, String sprite) {}
