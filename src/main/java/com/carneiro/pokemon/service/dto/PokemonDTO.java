package com.carneiro.pokemon.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Pokemon entity.
 */
public class PokemonDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 89)
    private String abilities;

    @NotNull
    private Float againstBug;

    @NotNull
    private Float againstDark;

    @NotNull
    private Float againstDragon;

    @NotNull
    private Float againstElectric;

    @NotNull
    private Float againstFairy;

    @NotNull
    private Float againstFight;

    @NotNull
    private Float againstFire;

    @NotNull
    private Float againstFlying;

    @NotNull
    private Float againstGhost;

    @NotNull
    private Float againstGrass;

    @NotNull
    private Float againstGround;

    @NotNull
    private Float againstIce;

    @NotNull
    private Float againstNormal;

    @NotNull
    private Float againstPoison;

    @NotNull
    private Float againstPsychic;

    @NotNull
    private Float againstRock;

    @NotNull
    private Float againstSteel;

    @NotNull
    private Float againstWater;

    @NotNull
    private Integer attack;

    @NotNull
    private Integer baseEggSteps;

    @NotNull
    private Integer baseHappiness;

    @NotNull
    private Integer baseTotal;

    @NotNull
    @Size(max = 24)
    private String captureRate;

    @NotNull
    @Size(max = 51)
    private String classfication;

    @NotNull
    private Integer defense;

    @NotNull
    private Integer experienceGrowth;

    private Float heightM;

    @NotNull
    private Integer hp;

    @NotNull
    @Size(max = 33)
    private String japaneseName;

    @NotNull
    @Size(max = 12)
    private String name;

    private Float percentageMale;

    @NotNull
    private Integer pokedexNumber;

    @NotNull
    private Integer spAttack;

    @NotNull
    private Integer spDefense;

    @NotNull
    private Integer speed;

    @NotNull
    @Size(max = 8)
    private String type1;

    @Size(max = 8)
    private String type2;

    private Float weightKg;

    @NotNull
    private Integer generation;

    @NotNull
    private Integer isLegendary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public Float getAgainstBug() {
        return againstBug;
    }

    public void setAgainstBug(Float againstBug) {
        this.againstBug = againstBug;
    }

    public Float getAgainstDark() {
        return againstDark;
    }

    public void setAgainstDark(Float againstDark) {
        this.againstDark = againstDark;
    }

    public Float getAgainstDragon() {
        return againstDragon;
    }

    public void setAgainstDragon(Float againstDragon) {
        this.againstDragon = againstDragon;
    }

    public Float getAgainstElectric() {
        return againstElectric;
    }

    public void setAgainstElectric(Float againstElectric) {
        this.againstElectric = againstElectric;
    }

    public Float getAgainstFairy() {
        return againstFairy;
    }

    public void setAgainstFairy(Float againstFairy) {
        this.againstFairy = againstFairy;
    }

    public Float getAgainstFight() {
        return againstFight;
    }

    public void setAgainstFight(Float againstFight) {
        this.againstFight = againstFight;
    }

    public Float getAgainstFire() {
        return againstFire;
    }

    public void setAgainstFire(Float againstFire) {
        this.againstFire = againstFire;
    }

    public Float getAgainstFlying() {
        return againstFlying;
    }

    public void setAgainstFlying(Float againstFlying) {
        this.againstFlying = againstFlying;
    }

    public Float getAgainstGhost() {
        return againstGhost;
    }

    public void setAgainstGhost(Float againstGhost) {
        this.againstGhost = againstGhost;
    }

    public Float getAgainstGrass() {
        return againstGrass;
    }

    public void setAgainstGrass(Float againstGrass) {
        this.againstGrass = againstGrass;
    }

    public Float getAgainstGround() {
        return againstGround;
    }

    public void setAgainstGround(Float againstGround) {
        this.againstGround = againstGround;
    }

    public Float getAgainstIce() {
        return againstIce;
    }

    public void setAgainstIce(Float againstIce) {
        this.againstIce = againstIce;
    }

    public Float getAgainstNormal() {
        return againstNormal;
    }

    public void setAgainstNormal(Float againstNormal) {
        this.againstNormal = againstNormal;
    }

    public Float getAgainstPoison() {
        return againstPoison;
    }

    public void setAgainstPoison(Float againstPoison) {
        this.againstPoison = againstPoison;
    }

    public Float getAgainstPsychic() {
        return againstPsychic;
    }

    public void setAgainstPsychic(Float againstPsychic) {
        this.againstPsychic = againstPsychic;
    }

    public Float getAgainstRock() {
        return againstRock;
    }

    public void setAgainstRock(Float againstRock) {
        this.againstRock = againstRock;
    }

    public Float getAgainstSteel() {
        return againstSteel;
    }

    public void setAgainstSteel(Float againstSteel) {
        this.againstSteel = againstSteel;
    }

    public Float getAgainstWater() {
        return againstWater;
    }

    public void setAgainstWater(Float againstWater) {
        this.againstWater = againstWater;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getBaseEggSteps() {
        return baseEggSteps;
    }

    public void setBaseEggSteps(Integer baseEggSteps) {
        this.baseEggSteps = baseEggSteps;
    }

    public Integer getBaseHappiness() {
        return baseHappiness;
    }

    public void setBaseHappiness(Integer baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public Integer getBaseTotal() {
        return baseTotal;
    }

    public void setBaseTotal(Integer baseTotal) {
        this.baseTotal = baseTotal;
    }

    public String getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(String captureRate) {
        this.captureRate = captureRate;
    }

    public String getClassfication() {
        return classfication;
    }

    public void setClassfication(String classfication) {
        this.classfication = classfication;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getExperienceGrowth() {
        return experienceGrowth;
    }

    public void setExperienceGrowth(Integer experienceGrowth) {
        this.experienceGrowth = experienceGrowth;
    }

    public Float getHeightM() {
        return heightM;
    }

    public void setHeightM(Float heightM) {
        this.heightM = heightM;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPercentageMale() {
        return percentageMale;
    }

    public void setPercentageMale(Float percentageMale) {
        this.percentageMale = percentageMale;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(Integer pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public Integer getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(Integer spAttack) {
        this.spAttack = spAttack;
    }

    public Integer getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(Integer spDefense) {
        this.spDefense = spDefense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Float getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Float weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Integer getIsLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(Integer isLegendary) {
        this.isLegendary = isLegendary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PokemonDTO pokemonDTO = (PokemonDTO) o;
        if(pokemonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pokemonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PokemonDTO{" +
            "id=" + getId() +
            ", abilities='" + getAbilities() + "'" +
            ", againstBug=" + getAgainstBug() +
            ", againstDark=" + getAgainstDark() +
            ", againstDragon=" + getAgainstDragon() +
            ", againstElectric=" + getAgainstElectric() +
            ", againstFairy=" + getAgainstFairy() +
            ", againstFight=" + getAgainstFight() +
            ", againstFire=" + getAgainstFire() +
            ", againstFlying=" + getAgainstFlying() +
            ", againstGhost=" + getAgainstGhost() +
            ", againstGrass=" + getAgainstGrass() +
            ", againstGround=" + getAgainstGround() +
            ", againstIce=" + getAgainstIce() +
            ", againstNormal=" + getAgainstNormal() +
            ", againstPoison=" + getAgainstPoison() +
            ", againstPsychic=" + getAgainstPsychic() +
            ", againstRock=" + getAgainstRock() +
            ", againstSteel=" + getAgainstSteel() +
            ", againstWater=" + getAgainstWater() +
            ", attack=" + getAttack() +
            ", baseEggSteps=" + getBaseEggSteps() +
            ", baseHappiness=" + getBaseHappiness() +
            ", baseTotal=" + getBaseTotal() +
            ", captureRate='" + getCaptureRate() + "'" +
            ", classfication='" + getClassfication() + "'" +
            ", defense=" + getDefense() +
            ", experienceGrowth=" + getExperienceGrowth() +
            ", heightM=" + getHeightM() +
            ", hp=" + getHp() +
            ", japaneseName='" + getJapaneseName() + "'" +
            ", name='" + getName() + "'" +
            ", percentageMale=" + getPercentageMale() +
            ", pokedexNumber=" + getPokedexNumber() +
            ", spAttack=" + getSpAttack() +
            ", spDefense=" + getSpDefense() +
            ", speed=" + getSpeed() +
            ", type1='" + getType1() + "'" +
            ", type2='" + getType2() + "'" +
            ", weightKg=" + getWeightKg() +
            ", generation=" + getGeneration() +
            ", isLegendary=" + getIsLegendary() +
            "}";
    }
}
