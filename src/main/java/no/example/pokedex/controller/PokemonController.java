package no.example.pokedex.controller;

import no.example.pokedex.model.PokemonDTO;
import no.example.pokedex.model.PokemonPage;
import no.example.pokedex.service.PokemonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public PokemonPage list(@RequestParam(defaultValue = "20") int limit,
                            @RequestParam(defaultValue = "0") int offset) {
        return service.listPokemon(Math.min(Math.max(limit, 1), 100), Math.max(offset, 0));
    }

    @GetMapping("/{name}")
    public PokemonDTO get(@PathVariable String name) {
        return service.getPokemon(name);
    }
}
