package com.carneiro.pokemon.repository;

import com.carneiro.pokemon.domain.Pokemon;
import com.carneiro.pokemon.domain.Pokemon_;
import com.carneiro.pokemon.repository.util.NativeQueryResultsMapper;
import com.carneiro.pokemon.service.dto.PokemonCriteriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepositoryCriteria {

    @PersistenceContext
    private EntityManager entityManager;

    private final PostgreSQLRepository pgRepository;

    @Autowired
    public PokemonRepositoryCriteria(PostgreSQLRepository pgRepository) {
        this.pgRepository = pgRepository;
    }

    public List<PokemonCriteriaDTO> findByCriteriaName(PokemonCriteriaDTO criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PokemonCriteriaDTO> query = cb.createQuery(PokemonCriteriaDTO.class);

        Root<Pokemon> from = query.from(Pokemon.class);
        Path<String> selectName = from.get(Pokemon_.name);

        query.select(cb.construct(PokemonCriteriaDTO.class, selectName));

        List<Object> params = new ArrayList<>();
        if (criteria.getName() != null) {
            String name = criteria.getName();
            name = "%" + name + "%";
            query.where(cb.like(cb.lower(selectName), name));

            params.add(name);
        }

        query.groupBy(selectName);
        query.orderBy(cb.asc(selectName));

        TypedQuery<PokemonCriteriaDTO> typedQuery = entityManager.createQuery(query);
        String sqlWithRowCount = pgRepository.getSQLWithRowCount(typedQuery, params);

        Query nativeQuery = entityManager.createNativeQuery(sqlWithRowCount);
        return NativeQueryResultsMapper.getResultList(nativeQuery, PokemonCriteriaDTO.class);
    }
}
