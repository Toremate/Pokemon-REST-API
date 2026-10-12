package no.example.pokedex.service;

import no.example.pokedex.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;

@Service
public class PokemonService {

    private final RestClient client;

    public PokemonService(RestClient.Builder builder,
                          @Value("${pokeapi.base-url}") String baseUrl) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    @Cacheable("pokemon")
    public PokemonDTO getPokemon(String name) {
        try {
            PokeApiPokemon p = client.get()
                    .uri("/pokemon/{name}", name.toLowerCase())
                    .retrieve()
                    .body(PokeApiPokemon.class);

            assert p != null;
            return new PokemonDTO(
                    p.id(),
                    p.name(),
                    p.height() / 10.0,   // decimeter -> meter
                    p.weight() / 10.0,   // hecto -> kg
                    p.types().stream()
                            .sorted(Comparator.comparingInt(PokeApiPokemon.TypeSlot::slot))
                            .map(t -> t.type().name())
                            .toList(),
                    p.sprites() == null ? null : p.sprites().frontDefault());
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon not found: " + name);
        }
    }

    @Cacheable("pokemon-pages")
    public PokemonPage listPokemon(int limit, int offset) {
        PokeApiList list = client.get()
                .uri("/pokemon?limit={limit}&offset={offset}", limit, offset)
                .retrieve()
                .body(PokeApiList.class);

        assert list != null;
        return new PokemonPage(
                list.count(), limit, offset,
                list.results().stream().map(PokeApiList.Entry::name).toList());
    }
}
