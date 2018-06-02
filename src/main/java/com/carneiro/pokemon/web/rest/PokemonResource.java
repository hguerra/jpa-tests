package com.carneiro.pokemon.web.rest;

import com.carneiro.pokemon.service.PokemonQueryService;
import com.carneiro.pokemon.service.PokemonService;
import com.carneiro.pokemon.service.dto.PokemonCriteria;
import com.carneiro.pokemon.service.dto.PokemonCriteriaDTO;
import com.carneiro.pokemon.service.dto.PokemonDTO;
import com.carneiro.pokemon.web.rest.errors.BadRequestAlertException;
import com.carneiro.pokemon.web.rest.util.HeaderUtil;
import com.carneiro.pokemon.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Pokemon.
 */
@RestController
@RequestMapping("/api")
public class PokemonResource {

    private final Logger log = LoggerFactory.getLogger(PokemonResource.class);

    private static final String ENTITY_NAME = "pokemon";

    private final PokemonService pokemonService;

    private final PokemonQueryService pokemonQueryService;

    public PokemonResource(PokemonService pokemonService, PokemonQueryService pokemonQueryService) {
        this.pokemonService = pokemonService;
        this.pokemonQueryService = pokemonQueryService;
    }

    /**
     * POST  /pokemons : Create a new pokemon.
     *
     * @param pokemonDTO the pokemonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pokemonDTO, or with status 400 (Bad Request) if the pokemon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pokemons")
    @Timed
    public ResponseEntity<PokemonDTO> createPokemon(@Valid @RequestBody PokemonDTO pokemonDTO) throws URISyntaxException {
        log.debug("REST request to save Pokemon : {}", pokemonDTO);
        if (pokemonDTO.getId() != null) {
            throw new BadRequestAlertException("A new pokemon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PokemonDTO result = pokemonService.save(pokemonDTO);
        return ResponseEntity.created(new URI("/api/pokemons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pokemons : Updates an existing pokemon.
     *
     * @param pokemonDTO the pokemonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pokemonDTO,
     * or with status 400 (Bad Request) if the pokemonDTO is not valid,
     * or with status 500 (Internal Server Error) if the pokemonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pokemons")
    @Timed
    public ResponseEntity<PokemonDTO> updatePokemon(@Valid @RequestBody PokemonDTO pokemonDTO) throws URISyntaxException {
        log.debug("REST request to update Pokemon : {}", pokemonDTO);
        if (pokemonDTO.getId() == null) {
            return createPokemon(pokemonDTO);
        }
        PokemonDTO result = pokemonService.save(pokemonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pokemonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pokemons : get all the pokemons.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of pokemons in body
     */
    @GetMapping("/pokemons")
    @Timed
    public ResponseEntity<List<PokemonDTO>> getAllPokemons(PokemonCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Pokemons by criteria: {}", criteria);
        Page<PokemonDTO> page = pokemonQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pokemons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pokemons/:id : get the "id" pokemon.
     *
     * @param id the id of the pokemonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pokemonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pokemons/{id}")
    @Timed
    public ResponseEntity<PokemonDTO> getPokemon(@PathVariable Long id) {
        log.debug("REST request to get Pokemon : {}", id);
        PokemonDTO pokemonDTO = pokemonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pokemonDTO));
    }

    /**
     * DELETE  /pokemons/:id : delete the "id" pokemon.
     *
     * @param id the id of the pokemonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pokemons/{id}")
    @Timed
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        log.debug("REST request to delete Pokemon : {}", id);
        pokemonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /pokemons/sql
     *
     * @param criteria the pokemonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body String SQL
     */
    @GetMapping("/pokemons/sql")
    @Timed
    public ResponseEntity<List<PokemonCriteriaDTO>> getByNameLike(PokemonCriteriaDTO criteria) {
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pokemonService.getByNameLike(criteria)));
    }
}
