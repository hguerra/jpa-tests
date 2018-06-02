package com.carneiro.pokemon.service;

import com.carneiro.pokemon.domain.Pokemon;
import com.carneiro.pokemon.repository.PokemonRepository;
import com.carneiro.pokemon.repository.PokemonRepositoryCriteria;
import com.carneiro.pokemon.service.dto.PokemonCriteriaDTO;
import com.carneiro.pokemon.service.dto.PokemonDTO;
import com.carneiro.pokemon.service.mapper.PokemonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service Implementation for managing Pokemon.
 */
@Service
@Transactional
public class PokemonService {

    private final Logger log = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonRepository pokemonRepository;

    private final PokemonRepositoryCriteria pokemonRepositoryCriteria;

    private final PokemonMapper pokemonMapper;

    public PokemonService(PokemonRepository pokemonRepository, PokemonRepositoryCriteria pokemonRepositoryCriteria, PokemonMapper pokemonMapper) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonRepositoryCriteria = pokemonRepositoryCriteria;
        this.pokemonMapper = pokemonMapper;
    }

    /**
     * Save a pokemon.
     *
     * @param pokemonDTO the entity to save
     * @return the persisted entity
     */
    public PokemonDTO save(PokemonDTO pokemonDTO) {
        log.debug("Request to save Pokemon : {}", pokemonDTO);
        Pokemon pokemon = pokemonMapper.toEntity(pokemonDTO);
        pokemon = pokemonRepository.save(pokemon);
        return pokemonMapper.toDto(pokemon);
    }

    /**
     * Get all the pokemons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PokemonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pokemons");
        return pokemonRepository.findAll(pageable)
            .map(pokemonMapper::toDto);
    }

    /**
     * Get one pokemon by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public PokemonDTO findOne(Long id) {
        log.debug("Request to get Pokemon : {}", id);
        Pokemon pokemon = pokemonRepository.findOne(id);
        return pokemonMapper.toDto(pokemon);
    }

    /**
     * Delete the pokemon by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Pokemon : {}", id);
        pokemonRepository.delete(id);
    }

    /**
     * Raw sql
     * @param criteria PokemonCriteria
     */
    public List<PokemonCriteriaDTO> getByNameLike(PokemonCriteriaDTO criteria) {
        return pokemonRepositoryCriteria.findByCriteriaName(criteria);
    }
}
