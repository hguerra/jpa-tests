package com.carneiro.pokemon.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.carneiro.pokemon.domain.Pokemon;
import com.carneiro.pokemon.domain.*; // for static metamodels
import com.carneiro.pokemon.repository.PokemonRepository;
import com.carneiro.pokemon.service.dto.PokemonCriteria;

import com.carneiro.pokemon.service.dto.PokemonDTO;
import com.carneiro.pokemon.service.mapper.PokemonMapper;

/**
 * Service for executing complex queries for Pokemon entities in the database.
 * The main input is a {@link PokemonCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PokemonDTO} or a {@link Page} of {@link PokemonDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PokemonQueryService extends QueryService<Pokemon> {

    private final Logger log = LoggerFactory.getLogger(PokemonQueryService.class);


    private final PokemonRepository pokemonRepository;

    private final PokemonMapper pokemonMapper;

    public PokemonQueryService(PokemonRepository pokemonRepository, PokemonMapper pokemonMapper) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonMapper = pokemonMapper;
    }

    /**
     * Return a {@link List} of {@link PokemonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PokemonDTO> findByCriteria(PokemonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Pokemon> specification = createSpecification(criteria);
        return pokemonMapper.toDto(pokemonRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PokemonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PokemonDTO> findByCriteria(PokemonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Pokemon> specification = createSpecification(criteria);
        final Page<Pokemon> result = pokemonRepository.findAll(specification, page);
        return result.map(pokemonMapper::toDto);
    }

    /**
     * Function to convert PokemonCriteria to a {@link Specifications}
     */
    private Specifications<Pokemon> createSpecification(PokemonCriteria criteria) {
        Specifications<Pokemon> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Pokemon_.id));
            }
            if (criteria.getAbilities() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAbilities(), Pokemon_.abilities));
            }
            if (criteria.getAgainstBug() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstBug(), Pokemon_.againstBug));
            }
            if (criteria.getAgainstDark() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstDark(), Pokemon_.againstDark));
            }
            if (criteria.getAgainstDragon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstDragon(), Pokemon_.againstDragon));
            }
            if (criteria.getAgainstElectric() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstElectric(), Pokemon_.againstElectric));
            }
            if (criteria.getAgainstFairy() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstFairy(), Pokemon_.againstFairy));
            }
            if (criteria.getAgainstFight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstFight(), Pokemon_.againstFight));
            }
            if (criteria.getAgainstFire() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstFire(), Pokemon_.againstFire));
            }
            if (criteria.getAgainstFlying() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstFlying(), Pokemon_.againstFlying));
            }
            if (criteria.getAgainstGhost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstGhost(), Pokemon_.againstGhost));
            }
            if (criteria.getAgainstGrass() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstGrass(), Pokemon_.againstGrass));
            }
            if (criteria.getAgainstGround() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstGround(), Pokemon_.againstGround));
            }
            if (criteria.getAgainstIce() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstIce(), Pokemon_.againstIce));
            }
            if (criteria.getAgainstNormal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstNormal(), Pokemon_.againstNormal));
            }
            if (criteria.getAgainstPoison() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstPoison(), Pokemon_.againstPoison));
            }
            if (criteria.getAgainstPsychic() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstPsychic(), Pokemon_.againstPsychic));
            }
            if (criteria.getAgainstRock() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstRock(), Pokemon_.againstRock));
            }
            if (criteria.getAgainstSteel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstSteel(), Pokemon_.againstSteel));
            }
            if (criteria.getAgainstWater() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgainstWater(), Pokemon_.againstWater));
            }
            if (criteria.getAttack() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAttack(), Pokemon_.attack));
            }
            if (criteria.getBaseEggSteps() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBaseEggSteps(), Pokemon_.baseEggSteps));
            }
            if (criteria.getBaseHappiness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBaseHappiness(), Pokemon_.baseHappiness));
            }
            if (criteria.getBaseTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBaseTotal(), Pokemon_.baseTotal));
            }
            if (criteria.getCaptureRate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaptureRate(), Pokemon_.captureRate));
            }
            if (criteria.getClassfication() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClassfication(), Pokemon_.classfication));
            }
            if (criteria.getDefense() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDefense(), Pokemon_.defense));
            }
            if (criteria.getExperienceGrowth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExperienceGrowth(), Pokemon_.experienceGrowth));
            }
            if (criteria.getHeightM() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeightM(), Pokemon_.heightM));
            }
            if (criteria.getHp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHp(), Pokemon_.hp));
            }
            if (criteria.getJapaneseName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJapaneseName(), Pokemon_.japaneseName));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Pokemon_.name));
            }
            if (criteria.getPercentageMale() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPercentageMale(), Pokemon_.percentageMale));
            }
            if (criteria.getPokedexNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPokedexNumber(), Pokemon_.pokedexNumber));
            }
            if (criteria.getSpAttack() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpAttack(), Pokemon_.spAttack));
            }
            if (criteria.getSpDefense() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpDefense(), Pokemon_.spDefense));
            }
            if (criteria.getSpeed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpeed(), Pokemon_.speed));
            }
            if (criteria.getType1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType1(), Pokemon_.type1));
            }
            if (criteria.getType2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType2(), Pokemon_.type2));
            }
            if (criteria.getWeightKg() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeightKg(), Pokemon_.weightKg));
            }
            if (criteria.getGeneration() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGeneration(), Pokemon_.generation));
            }
            if (criteria.getIsLegendary() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsLegendary(), Pokemon_.isLegendary));
            }
        }
        return specification;
    }

}
