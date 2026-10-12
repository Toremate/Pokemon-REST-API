package no.example.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiList(int count, List<Entry> results) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Entry(String name, String url) {}
}
