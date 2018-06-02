package com.carneiro.pokemon.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Pokemon entity. This class is used in PokemonResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /pokemons?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PokemonCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter abilities;

    private FloatFilter againstBug;

    private FloatFilter againstDark;

    private FloatFilter againstDragon;

    private FloatFilter againstElectric;

    private FloatFilter againstFairy;

    private FloatFilter againstFight;

    private FloatFilter againstFire;

    private FloatFilter againstFlying;

    private FloatFilter againstGhost;

    private FloatFilter againstGrass;

    private FloatFilter againstGround;

    private FloatFilter againstIce;

    private FloatFilter againstNormal;

    private FloatFilter againstPoison;

    private FloatFilter againstPsychic;

    private FloatFilter againstRock;

    private FloatFilter againstSteel;

    private FloatFilter againstWater;

    private IntegerFilter attack;

    private IntegerFilter baseEggSteps;

    private IntegerFilter baseHappiness;

    private IntegerFilter baseTotal;

    private StringFilter captureRate;

    private StringFilter classfication;

    private IntegerFilter defense;

    private IntegerFilter experienceGrowth;

    private FloatFilter heightM;

    private IntegerFilter hp;

    private StringFilter japaneseName;

    private StringFilter name;

    private FloatFilter percentageMale;

    private IntegerFilter pokedexNumber;

    private IntegerFilter spAttack;

    private IntegerFilter spDefense;

    private IntegerFilter speed;

    private StringFilter type1;

    private StringFilter type2;

    private FloatFilter weightKg;

    private IntegerFilter generation;

    private IntegerFilter isLegendary;

    public PokemonCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAbilities() {
        return abilities;
    }

    public void setAbilities(StringFilter abilities) {
        this.abilities = abilities;
    }

    public FloatFilter getAgainstBug() {
        return againstBug;
    }

    public void setAgainstBug(FloatFilter againstBug) {
        this.againstBug = againstBug;
    }

    public FloatFilter getAgainstDark() {
        return againstDark;
    }

    public void setAgainstDark(FloatFilter againstDark) {
        this.againstDark = againstDark;
    }

    public FloatFilter getAgainstDragon() {
        return againstDragon;
    }

    public void setAgainstDragon(FloatFilter againstDragon) {
        this.againstDragon = againstDragon;
    }

    public FloatFilter getAgainstElectric() {
        return againstElectric;
    }

    public void setAgainstElectric(FloatFilter againstElectric) {
        this.againstElectric = againstElectric;
    }

    public FloatFilter getAgainstFairy() {
        return againstFairy;
    }

    public void setAgainstFairy(FloatFilter againstFairy) {
        this.againstFairy = againstFairy;
    }

    public FloatFilter getAgainstFight() {
        return againstFight;
    }

    public void setAgainstFight(FloatFilter againstFight) {
        this.againstFight = againstFight;
    }

    public FloatFilter getAgainstFire() {
        return againstFire;
    }

    public void setAgainstFire(FloatFilter againstFire) {
        this.againstFire = againstFire;
    }

    public FloatFilter getAgainstFlying() {
        return againstFlying;
    }

    public void setAgainstFlying(FloatFilter againstFlying) {
        this.againstFlying = againstFlying;
    }

    public FloatFilter getAgainstGhost() {
        return againstGhost;
    }

    public void setAgainstGhost(FloatFilter againstGhost) {
        this.againstGhost = againstGhost;
    }

    public FloatFilter getAgainstGrass() {
        return againstGrass;
    }

    public void setAgainstGrass(FloatFilter againstGrass) {
        this.againstGrass = againstGrass;
    }

    public FloatFilter getAgainstGround() {
        return againstGround;
    }

    public void setAgainstGround(FloatFilter againstGround) {
        this.againstGround = againstGround;
    }

    public FloatFilter getAgainstIce() {
        return againstIce;
    }

    public void setAgainstIce(FloatFilter againstIce) {
        this.againstIce = againstIce;
    }

    public FloatFilter getAgainstNormal() {
        return againstNormal;
    }

    public void setAgainstNormal(FloatFilter againstNormal) {
        this.againstNormal = againstNormal;
    }

    public FloatFilter getAgainstPoison() {
        return againstPoison;
    }

    public void setAgainstPoison(FloatFilter againstPoison) {
        this.againstPoison = againstPoison;
    }

    public FloatFilter getAgainstPsychic() {
        return againstPsychic;
    }

    public void setAgainstPsychic(FloatFilter againstPsychic) {
        this.againstPsychic = againstPsychic;
    }

    public FloatFilter getAgainstRock() {
        return againstRock;
    }

    public void setAgainstRock(FloatFilter againstRock) {
        this.againstRock = againstRock;
    }

    public FloatFilter getAgainstSteel() {
        return againstSteel;
    }

    public void setAgainstSteel(FloatFilter againstSteel) {
        this.againstSteel = againstSteel;
    }

    public FloatFilter getAgainstWater() {
        return againstWater;
    }

    public void setAgainstWater(FloatFilter againstWater) {
        this.againstWater = againstWater;
    }

    public IntegerFilter getAttack() {
        return attack;
    }

    public void setAttack(IntegerFilter attack) {
        this.attack = attack;
    }

    public IntegerFilter getBaseEggSteps() {
        return baseEggSteps;
    }

    public void setBaseEggSteps(IntegerFilter baseEggSteps) {
        this.baseEggSteps = baseEggSteps;
    }

    public IntegerFilter getBaseHappiness() {
        return baseHappiness;
    }

    public void setBaseHappiness(IntegerFilter baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public IntegerFilter getBaseTotal() {
        return baseTotal;
    }

    public void setBaseTotal(IntegerFilter baseTotal) {
        this.baseTotal = baseTotal;
    }

    public StringFilter getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(StringFilter captureRate) {
        this.captureRate = captureRate;
    }

    public StringFilter getClassfication() {
        return classfication;
    }

    public void setClassfication(StringFilter classfication) {
        this.classfication = classfication;
    }

    public IntegerFilter getDefense() {
        return defense;
    }

    public void setDefense(IntegerFilter defense) {
        this.defense = defense;
    }

    public IntegerFilter getExperienceGrowth() {
        return experienceGrowth;
    }

    public void setExperienceGrowth(IntegerFilter experienceGrowth) {
        this.experienceGrowth = experienceGrowth;
    }

    public FloatFilter getHeightM() {
        return heightM;
    }

    public void setHeightM(FloatFilter heightM) {
        this.heightM = heightM;
    }

    public IntegerFilter getHp() {
        return hp;
    }

    public void setHp(IntegerFilter hp) {
        this.hp = hp;
    }

    public StringFilter getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(StringFilter japaneseName) {
        this.japaneseName = japaneseName;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public FloatFilter getPercentageMale() {
        return percentageMale;
    }

    public void setPercentageMale(FloatFilter percentageMale) {
        this.percentageMale = percentageMale;
    }

    public IntegerFilter getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(IntegerFilter pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public IntegerFilter getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(IntegerFilter spAttack) {
        this.spAttack = spAttack;
    }

    public IntegerFilter getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(IntegerFilter spDefense) {
        this.spDefense = spDefense;
    }

    public IntegerFilter getSpeed() {
        return speed;
    }

    public void setSpeed(IntegerFilter speed) {
        this.speed = speed;
    }

    public StringFilter getType1() {
        return type1;
    }

    public void setType1(StringFilter type1) {
        this.type1 = type1;
    }

    public StringFilter getType2() {
        return type2;
    }

    public void setType2(StringFilter type2) {
        this.type2 = type2;
    }

    public FloatFilter getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(FloatFilter weightKg) {
        this.weightKg = weightKg;
    }

    public IntegerFilter getGeneration() {
        return generation;
    }

    public void setGeneration(IntegerFilter generation) {
        this.generation = generation;
    }

    public IntegerFilter getIsLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(IntegerFilter isLegendary) {
        this.isLegendary = isLegendary;
    }

    @Override
    public String toString() {
        return "PokemonCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (abilities != null ? "abilities=" + abilities + ", " : "") +
                (againstBug != null ? "againstBug=" + againstBug + ", " : "") +
                (againstDark != null ? "againstDark=" + againstDark + ", " : "") +
                (againstDragon != null ? "againstDragon=" + againstDragon + ", " : "") +
                (againstElectric != null ? "againstElectric=" + againstElectric + ", " : "") +
                (againstFairy != null ? "againstFairy=" + againstFairy + ", " : "") +
                (againstFight != null ? "againstFight=" + againstFight + ", " : "") +
                (againstFire != null ? "againstFire=" + againstFire + ", " : "") +
                (againstFlying != null ? "againstFlying=" + againstFlying + ", " : "") +
                (againstGhost != null ? "againstGhost=" + againstGhost + ", " : "") +
                (againstGrass != null ? "againstGrass=" + againstGrass + ", " : "") +
                (againstGround != null ? "againstGround=" + againstGround + ", " : "") +
                (againstIce != null ? "againstIce=" + againstIce + ", " : "") +
                (againstNormal != null ? "againstNormal=" + againstNormal + ", " : "") +
                (againstPoison != null ? "againstPoison=" + againstPoison + ", " : "") +
                (againstPsychic != null ? "againstPsychic=" + againstPsychic + ", " : "") +
                (againstRock != null ? "againstRock=" + againstRock + ", " : "") +
                (againstSteel != null ? "againstSteel=" + againstSteel + ", " : "") +
                (againstWater != null ? "againstWater=" + againstWater + ", " : "") +
                (attack != null ? "attack=" + attack + ", " : "") +
                (baseEggSteps != null ? "baseEggSteps=" + baseEggSteps + ", " : "") +
                (baseHappiness != null ? "baseHappiness=" + baseHappiness + ", " : "") +
                (baseTotal != null ? "baseTotal=" + baseTotal + ", " : "") +
                (captureRate != null ? "captureRate=" + captureRate + ", " : "") +
                (classfication != null ? "classfication=" + classfication + ", " : "") +
                (defense != null ? "defense=" + defense + ", " : "") +
                (experienceGrowth != null ? "experienceGrowth=" + experienceGrowth + ", " : "") +
                (heightM != null ? "heightM=" + heightM + ", " : "") +
                (hp != null ? "hp=" + hp + ", " : "") +
                (japaneseName != null ? "japaneseName=" + japaneseName + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (percentageMale != null ? "percentageMale=" + percentageMale + ", " : "") +
                (pokedexNumber != null ? "pokedexNumber=" + pokedexNumber + ", " : "") +
                (spAttack != null ? "spAttack=" + spAttack + ", " : "") +
                (spDefense != null ? "spDefense=" + spDefense + ", " : "") +
                (speed != null ? "speed=" + speed + ", " : "") +
                (type1 != null ? "type1=" + type1 + ", " : "") +
                (type2 != null ? "type2=" + type2 + ", " : "") +
                (weightKg != null ? "weightKg=" + weightKg + ", " : "") +
                (generation != null ? "generation=" + generation + ", " : "") +
                (isLegendary != null ? "isLegendary=" + isLegendary + ", " : "") +
            "}";
    }

}
