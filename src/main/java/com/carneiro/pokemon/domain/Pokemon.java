package com.carneiro.pokemon.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Pokemon.
 */
@Entity
@Table(name = "pokemon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 89)
    @Column(name = "abilities", length = 89, nullable = false)
    private String abilities;

    @NotNull
    @Column(name = "against_bug", nullable = false)
    private Float againstBug;

    @NotNull
    @Column(name = "against_dark", nullable = false)
    private Float againstDark;

    @NotNull
    @Column(name = "against_dragon", nullable = false)
    private Float againstDragon;

    @NotNull
    @Column(name = "against_electric", nullable = false)
    private Float againstElectric;

    @NotNull
    @Column(name = "against_fairy", nullable = false)
    private Float againstFairy;

    @NotNull
    @Column(name = "against_fight", nullable = false)
    private Float againstFight;

    @NotNull
    @Column(name = "against_fire", nullable = false)
    private Float againstFire;

    @NotNull
    @Column(name = "against_flying", nullable = false)
    private Float againstFlying;

    @NotNull
    @Column(name = "against_ghost", nullable = false)
    private Float againstGhost;

    @NotNull
    @Column(name = "against_grass", nullable = false)
    private Float againstGrass;

    @NotNull
    @Column(name = "against_ground", nullable = false)
    private Float againstGround;

    @NotNull
    @Column(name = "against_ice", nullable = false)
    private Float againstIce;

    @NotNull
    @Column(name = "against_normal", nullable = false)
    private Float againstNormal;

    @NotNull
    @Column(name = "against_poison", nullable = false)
    private Float againstPoison;

    @NotNull
    @Column(name = "against_psychic", nullable = false)
    private Float againstPsychic;

    @NotNull
    @Column(name = "against_rock", nullable = false)
    private Float againstRock;

    @NotNull
    @Column(name = "against_steel", nullable = false)
    private Float againstSteel;

    @NotNull
    @Column(name = "against_water", nullable = false)
    private Float againstWater;

    @NotNull
    @Column(name = "attack", nullable = false)
    private Integer attack;

    @NotNull
    @Column(name = "base_egg_steps", nullable = false)
    private Integer baseEggSteps;

    @NotNull
    @Column(name = "base_happiness", nullable = false)
    private Integer baseHappiness;

    @NotNull
    @Column(name = "base_total", nullable = false)
    private Integer baseTotal;

    @NotNull
    @Size(max = 24)
    @Column(name = "capture_rate", length = 24, nullable = false)
    private String captureRate;

    @NotNull
    @Size(max = 51)
    @Column(name = "classfication", length = 51, nullable = false)
    private String classfication;

    @NotNull
    @Column(name = "defense", nullable = false)
    private Integer defense;

    @NotNull
    @Column(name = "experience_growth", nullable = false)
    private Integer experienceGrowth;

    @Column(name = "height_m")
    private Float heightM;

    @NotNull
    @Column(name = "hp", nullable = false)
    private Integer hp;

    @NotNull
    @Size(max = 33)
    @Column(name = "japanese_name", length = 33, nullable = false)
    private String japaneseName;

    @NotNull
    @Size(max = 12)
    @Column(name = "name", length = 12, nullable = false)
    private String name;

    @Column(name = "percentage_male")
    private Float percentageMale;

    @NotNull
    @Column(name = "pokedex_number", nullable = false)
    private Integer pokedexNumber;

    @NotNull
    @Column(name = "sp_attack", nullable = false)
    private Integer spAttack;

    @NotNull
    @Column(name = "sp_defense", nullable = false)
    private Integer spDefense;

    @NotNull
    @Column(name = "speed", nullable = false)
    private Integer speed;

    @NotNull
    @Size(max = 8)
    @Column(name = "type_1", length = 8, nullable = false)
    private String type1;

    @Size(max = 8)
    @Column(name = "type_2", length = 8)
    private String type2;

    @Column(name = "weight_kg")
    private Float weightKg;

    @NotNull
    @Column(name = "generation", nullable = false)
    private Integer generation;

    @NotNull
    @Column(name = "is_legendary", nullable = false)
    private Integer isLegendary;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbilities() {
        return abilities;
    }

    public Pokemon abilities(String abilities) {
        this.abilities = abilities;
        return this;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public Float getAgainstBug() {
        return againstBug;
    }

    public Pokemon againstBug(Float againstBug) {
        this.againstBug = againstBug;
        return this;
    }

    public void setAgainstBug(Float againstBug) {
        this.againstBug = againstBug;
    }

    public Float getAgainstDark() {
        return againstDark;
    }

    public Pokemon againstDark(Float againstDark) {
        this.againstDark = againstDark;
        return this;
    }

    public void setAgainstDark(Float againstDark) {
        this.againstDark = againstDark;
    }

    public Float getAgainstDragon() {
        return againstDragon;
    }

    public Pokemon againstDragon(Float againstDragon) {
        this.againstDragon = againstDragon;
        return this;
    }

    public void setAgainstDragon(Float againstDragon) {
        this.againstDragon = againstDragon;
    }

    public Float getAgainstElectric() {
        return againstElectric;
    }

    public Pokemon againstElectric(Float againstElectric) {
        this.againstElectric = againstElectric;
        return this;
    }

    public void setAgainstElectric(Float againstElectric) {
        this.againstElectric = againstElectric;
    }

    public Float getAgainstFairy() {
        return againstFairy;
    }

    public Pokemon againstFairy(Float againstFairy) {
        this.againstFairy = againstFairy;
        return this;
    }

    public void setAgainstFairy(Float againstFairy) {
        this.againstFairy = againstFairy;
    }

    public Float getAgainstFight() {
        return againstFight;
    }

    public Pokemon againstFight(Float againstFight) {
        this.againstFight = againstFight;
        return this;
    }

    public void setAgainstFight(Float againstFight) {
        this.againstFight = againstFight;
    }

    public Float getAgainstFire() {
        return againstFire;
    }

    public Pokemon againstFire(Float againstFire) {
        this.againstFire = againstFire;
        return this;
    }

    public void setAgainstFire(Float againstFire) {
        this.againstFire = againstFire;
    }

    public Float getAgainstFlying() {
        return againstFlying;
    }

    public Pokemon againstFlying(Float againstFlying) {
        this.againstFlying = againstFlying;
        return this;
    }

    public void setAgainstFlying(Float againstFlying) {
        this.againstFlying = againstFlying;
    }

    public Float getAgainstGhost() {
        return againstGhost;
    }

    public Pokemon againstGhost(Float againstGhost) {
        this.againstGhost = againstGhost;
        return this;
    }

    public void setAgainstGhost(Float againstGhost) {
        this.againstGhost = againstGhost;
    }

    public Float getAgainstGrass() {
        return againstGrass;
    }

    public Pokemon againstGrass(Float againstGrass) {
        this.againstGrass = againstGrass;
        return this;
    }

    public void setAgainstGrass(Float againstGrass) {
        this.againstGrass = againstGrass;
    }

    public Float getAgainstGround() {
        return againstGround;
    }

    public Pokemon againstGround(Float againstGround) {
        this.againstGround = againstGround;
        return this;
    }

    public void setAgainstGround(Float againstGround) {
        this.againstGround = againstGround;
    }

    public Float getAgainstIce() {
        return againstIce;
    }

    public Pokemon againstIce(Float againstIce) {
        this.againstIce = againstIce;
        return this;
    }

    public void setAgainstIce(Float againstIce) {
        this.againstIce = againstIce;
    }

    public Float getAgainstNormal() {
        return againstNormal;
    }

    public Pokemon againstNormal(Float againstNormal) {
        this.againstNormal = againstNormal;
        return this;
    }

    public void setAgainstNormal(Float againstNormal) {
        this.againstNormal = againstNormal;
    }

    public Float getAgainstPoison() {
        return againstPoison;
    }

    public Pokemon againstPoison(Float againstPoison) {
        this.againstPoison = againstPoison;
        return this;
    }

    public void setAgainstPoison(Float againstPoison) {
        this.againstPoison = againstPoison;
    }

    public Float getAgainstPsychic() {
        return againstPsychic;
    }

    public Pokemon againstPsychic(Float againstPsychic) {
        this.againstPsychic = againstPsychic;
        return this;
    }

    public void setAgainstPsychic(Float againstPsychic) {
        this.againstPsychic = againstPsychic;
    }

    public Float getAgainstRock() {
        return againstRock;
    }

    public Pokemon againstRock(Float againstRock) {
        this.againstRock = againstRock;
        return this;
    }

    public void setAgainstRock(Float againstRock) {
        this.againstRock = againstRock;
    }

    public Float getAgainstSteel() {
        return againstSteel;
    }

    public Pokemon againstSteel(Float againstSteel) {
        this.againstSteel = againstSteel;
        return this;
    }

    public void setAgainstSteel(Float againstSteel) {
        this.againstSteel = againstSteel;
    }

    public Float getAgainstWater() {
        return againstWater;
    }

    public Pokemon againstWater(Float againstWater) {
        this.againstWater = againstWater;
        return this;
    }

    public void setAgainstWater(Float againstWater) {
        this.againstWater = againstWater;
    }

    public Integer getAttack() {
        return attack;
    }

    public Pokemon attack(Integer attack) {
        this.attack = attack;
        return this;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getBaseEggSteps() {
        return baseEggSteps;
    }

    public Pokemon baseEggSteps(Integer baseEggSteps) {
        this.baseEggSteps = baseEggSteps;
        return this;
    }

    public void setBaseEggSteps(Integer baseEggSteps) {
        this.baseEggSteps = baseEggSteps;
    }

    public Integer getBaseHappiness() {
        return baseHappiness;
    }

    public Pokemon baseHappiness(Integer baseHappiness) {
        this.baseHappiness = baseHappiness;
        return this;
    }

    public void setBaseHappiness(Integer baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public Integer getBaseTotal() {
        return baseTotal;
    }

    public Pokemon baseTotal(Integer baseTotal) {
        this.baseTotal = baseTotal;
        return this;
    }

    public void setBaseTotal(Integer baseTotal) {
        this.baseTotal = baseTotal;
    }

    public String getCaptureRate() {
        return captureRate;
    }

    public Pokemon captureRate(String captureRate) {
        this.captureRate = captureRate;
        return this;
    }

    public void setCaptureRate(String captureRate) {
        this.captureRate = captureRate;
    }

    public String getClassfication() {
        return classfication;
    }

    public Pokemon classfication(String classfication) {
        this.classfication = classfication;
        return this;
    }

    public void setClassfication(String classfication) {
        this.classfication = classfication;
    }

    public Integer getDefense() {
        return defense;
    }

    public Pokemon defense(Integer defense) {
        this.defense = defense;
        return this;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getExperienceGrowth() {
        return experienceGrowth;
    }

    public Pokemon experienceGrowth(Integer experienceGrowth) {
        this.experienceGrowth = experienceGrowth;
        return this;
    }

    public void setExperienceGrowth(Integer experienceGrowth) {
        this.experienceGrowth = experienceGrowth;
    }

    public Float getHeightM() {
        return heightM;
    }

    public Pokemon heightM(Float heightM) {
        this.heightM = heightM;
        return this;
    }

    public void setHeightM(Float heightM) {
        this.heightM = heightM;
    }

    public Integer getHp() {
        return hp;
    }

    public Pokemon hp(Integer hp) {
        this.hp = hp;
        return this;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public Pokemon japaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
        return this;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getName() {
        return name;
    }

    public Pokemon name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPercentageMale() {
        return percentageMale;
    }

    public Pokemon percentageMale(Float percentageMale) {
        this.percentageMale = percentageMale;
        return this;
    }

    public void setPercentageMale(Float percentageMale) {
        this.percentageMale = percentageMale;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public Pokemon pokedexNumber(Integer pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
        return this;
    }

    public void setPokedexNumber(Integer pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public Integer getSpAttack() {
        return spAttack;
    }

    public Pokemon spAttack(Integer spAttack) {
        this.spAttack = spAttack;
        return this;
    }

    public void setSpAttack(Integer spAttack) {
        this.spAttack = spAttack;
    }

    public Integer getSpDefense() {
        return spDefense;
    }

    public Pokemon spDefense(Integer spDefense) {
        this.spDefense = spDefense;
        return this;
    }

    public void setSpDefense(Integer spDefense) {
        this.spDefense = spDefense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Pokemon speed(Integer speed) {
        this.speed = speed;
        return this;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getType1() {
        return type1;
    }

    public Pokemon type1(String type1) {
        this.type1 = type1;
        return this;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public Pokemon type2(String type2) {
        this.type2 = type2;
        return this;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Float getWeightKg() {
        return weightKg;
    }

    public Pokemon weightKg(Float weightKg) {
        this.weightKg = weightKg;
        return this;
    }

    public void setWeightKg(Float weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getGeneration() {
        return generation;
    }

    public Pokemon generation(Integer generation) {
        this.generation = generation;
        return this;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Integer getIsLegendary() {
        return isLegendary;
    }

    public Pokemon isLegendary(Integer isLegendary) {
        this.isLegendary = isLegendary;
        return this;
    }

    public void setIsLegendary(Integer isLegendary) {
        this.isLegendary = isLegendary;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        if (pokemon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pokemon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pokemon{" +
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
