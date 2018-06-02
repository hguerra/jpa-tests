package com.carneiro.pokemon.repository;

import com.carneiro.pokemon.domain.Pokemon;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Pokemon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>, JpaSpecificationExecutor<Pokemon> {

}
