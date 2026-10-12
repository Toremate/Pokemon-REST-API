package no.example.pokedex.model;

import java.util.List;

public record PokemonPage(int total, int limit, int offset, List<String> names) {}
