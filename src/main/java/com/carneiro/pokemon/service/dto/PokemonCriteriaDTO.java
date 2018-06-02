package com.carneiro.pokemon.service.dto;

import java.math.BigInteger;
import java.util.Objects;

public class PokemonCriteriaDTO {

    private String name;

    private Long size;

    public PokemonCriteriaDTO() {
    }

    public PokemonCriteriaDTO(String name) {
        this.name = name;
    }

    public PokemonCriteriaDTO(String name, Long size) {
        this.name = name;
        this.size = size;
    }

    public PokemonCriteriaDTO(String name, BigInteger size) {
        this.name = name;
        this.size = size.longValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonCriteriaDTO that = (PokemonCriteriaDTO) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
