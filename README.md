# pokedex-api

Small Spring Boot REST API that wraps [PokeAPI](https://pokeapi.co)

## Run

```bash
docker compose up --build
```

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/pokemon?limit=20&offset=0` | Paged list of Pokémon names |
| GET | `/api/pokemon/{name}` | Details for one Pokémon |

```bash
curl http://localhost:8080/api/pokemon/pikachu
curl "http://localhost:8080/api/pokemon?limit=5"
curl http://localhost:8080/api/pokemon/notapokemon   # 404
```

## Run without Docker

```bash
mvn spring-boot:run
```

Responses are cached in memory, so repeat requests don't hit PokeAPI again.
