package com.carneiro.pokemon.service.mapper;

import com.carneiro.pokemon.domain.*;
import com.carneiro.pokemon.service.dto.PokemonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pokemon and its DTO PokemonDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PokemonMapper extends EntityMapper<PokemonDTO, Pokemon> {



    default Pokemon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pokemon pokemon = new Pokemon();
        pokemon.setId(id);
        return pokemon;
    }
}
