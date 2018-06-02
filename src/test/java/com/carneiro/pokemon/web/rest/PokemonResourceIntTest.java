package com.carneiro.pokemon.web.rest;

import com.carneiro.pokemon.PokemonApp;

import com.carneiro.pokemon.domain.Pokemon;
import com.carneiro.pokemon.repository.PokemonRepository;
import com.carneiro.pokemon.service.PokemonService;
import com.carneiro.pokemon.service.dto.PokemonDTO;
import com.carneiro.pokemon.service.mapper.PokemonMapper;
import com.carneiro.pokemon.web.rest.errors.ExceptionTranslator;
import com.carneiro.pokemon.service.dto.PokemonCriteria;
import com.carneiro.pokemon.service.PokemonQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.carneiro.pokemon.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PokemonResource REST controller.
 *
 * @see PokemonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokemonApp.class)
public class PokemonResourceIntTest {

    private static final String DEFAULT_ABILITIES = "AAAAAAAAAA";
    private static final String UPDATED_ABILITIES = "BBBBBBBBBB";

    private static final Float DEFAULT_AGAINST_BUG = 1F;
    private static final Float UPDATED_AGAINST_BUG = 2F;

    private static final Float DEFAULT_AGAINST_DARK = 1F;
    private static final Float UPDATED_AGAINST_DARK = 2F;

    private static final Float DEFAULT_AGAINST_DRAGON = 1F;
    private static final Float UPDATED_AGAINST_DRAGON = 2F;

    private static final Float DEFAULT_AGAINST_ELECTRIC = 1F;
    private static final Float UPDATED_AGAINST_ELECTRIC = 2F;

    private static final Float DEFAULT_AGAINST_FAIRY = 1F;
    private static final Float UPDATED_AGAINST_FAIRY = 2F;

    private static final Float DEFAULT_AGAINST_FIGHT = 1F;
    private static final Float UPDATED_AGAINST_FIGHT = 2F;

    private static final Float DEFAULT_AGAINST_FIRE = 1F;
    private static final Float UPDATED_AGAINST_FIRE = 2F;

    private static final Float DEFAULT_AGAINST_FLYING = 1F;
    private static final Float UPDATED_AGAINST_FLYING = 2F;

    private static final Float DEFAULT_AGAINST_GHOST = 1F;
    private static final Float UPDATED_AGAINST_GHOST = 2F;

    private static final Float DEFAULT_AGAINST_GRASS = 1F;
    private static final Float UPDATED_AGAINST_GRASS = 2F;

    private static final Float DEFAULT_AGAINST_GROUND = 1F;
    private static final Float UPDATED_AGAINST_GROUND = 2F;

    private static final Float DEFAULT_AGAINST_ICE = 1F;
    private static final Float UPDATED_AGAINST_ICE = 2F;

    private static final Float DEFAULT_AGAINST_NORMAL = 1F;
    private static final Float UPDATED_AGAINST_NORMAL = 2F;

    private static final Float DEFAULT_AGAINST_POISON = 1F;
    private static final Float UPDATED_AGAINST_POISON = 2F;

    private static final Float DEFAULT_AGAINST_PSYCHIC = 1F;
    private static final Float UPDATED_AGAINST_PSYCHIC = 2F;

    private static final Float DEFAULT_AGAINST_ROCK = 1F;
    private static final Float UPDATED_AGAINST_ROCK = 2F;

    private static final Float DEFAULT_AGAINST_STEEL = 1F;
    private static final Float UPDATED_AGAINST_STEEL = 2F;

    private static final Float DEFAULT_AGAINST_WATER = 1F;
    private static final Float UPDATED_AGAINST_WATER = 2F;

    private static final Integer DEFAULT_ATTACK = 1;
    private static final Integer UPDATED_ATTACK = 2;

    private static final Integer DEFAULT_BASE_EGG_STEPS = 1;
    private static final Integer UPDATED_BASE_EGG_STEPS = 2;

    private static final Integer DEFAULT_BASE_HAPPINESS = 1;
    private static final Integer UPDATED_BASE_HAPPINESS = 2;

    private static final Integer DEFAULT_BASE_TOTAL = 1;
    private static final Integer UPDATED_BASE_TOTAL = 2;

    private static final String DEFAULT_CAPTURE_RATE = "AAAAAAAAAA";
    private static final String UPDATED_CAPTURE_RATE = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSFICATION = "AAAAAAAAAA";
    private static final String UPDATED_CLASSFICATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEFENSE = 1;
    private static final Integer UPDATED_DEFENSE = 2;

    private static final Integer DEFAULT_EXPERIENCE_GROWTH = 1;
    private static final Integer UPDATED_EXPERIENCE_GROWTH = 2;

    private static final Float DEFAULT_HEIGHT_M = 1F;
    private static final Float UPDATED_HEIGHT_M = 2F;

    private static final Integer DEFAULT_HP = 1;
    private static final Integer UPDATED_HP = 2;

    private static final String DEFAULT_JAPANESE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_JAPANESE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_PERCENTAGE_MALE = 1F;
    private static final Float UPDATED_PERCENTAGE_MALE = 2F;

    private static final Integer DEFAULT_POKEDEX_NUMBER = 1;
    private static final Integer UPDATED_POKEDEX_NUMBER = 2;

    private static final Integer DEFAULT_SP_ATTACK = 1;
    private static final Integer UPDATED_SP_ATTACK = 2;

    private static final Integer DEFAULT_SP_DEFENSE = 1;
    private static final Integer UPDATED_SP_DEFENSE = 2;

    private static final Integer DEFAULT_SPEED = 1;
    private static final Integer UPDATED_SPEED = 2;

    private static final String DEFAULT_TYPE_1 = "AAAAAAAA";
    private static final String UPDATED_TYPE_1 = "BBBBBBBB";

    private static final String DEFAULT_TYPE_2 = "AAAAAAAA";
    private static final String UPDATED_TYPE_2 = "BBBBBBBB";

    private static final Float DEFAULT_WEIGHT_KG = 1F;
    private static final Float UPDATED_WEIGHT_KG = 2F;

    private static final Integer DEFAULT_GENERATION = 1;
    private static final Integer UPDATED_GENERATION = 2;

    private static final Integer DEFAULT_IS_LEGENDARY = 1;
    private static final Integer UPDATED_IS_LEGENDARY = 2;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonMapper pokemonMapper;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonQueryService pokemonQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPokemonMockMvc;

    private Pokemon pokemon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PokemonResource pokemonResource = new PokemonResource(pokemonService, pokemonQueryService);
        this.restPokemonMockMvc = MockMvcBuilders.standaloneSetup(pokemonResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pokemon createEntity(EntityManager em) {
        Pokemon pokemon = new Pokemon()
            .abilities(DEFAULT_ABILITIES)
            .againstBug(DEFAULT_AGAINST_BUG)
            .againstDark(DEFAULT_AGAINST_DARK)
            .againstDragon(DEFAULT_AGAINST_DRAGON)
            .againstElectric(DEFAULT_AGAINST_ELECTRIC)
            .againstFairy(DEFAULT_AGAINST_FAIRY)
            .againstFight(DEFAULT_AGAINST_FIGHT)
            .againstFire(DEFAULT_AGAINST_FIRE)
            .againstFlying(DEFAULT_AGAINST_FLYING)
            .againstGhost(DEFAULT_AGAINST_GHOST)
            .againstGrass(DEFAULT_AGAINST_GRASS)
            .againstGround(DEFAULT_AGAINST_GROUND)
            .againstIce(DEFAULT_AGAINST_ICE)
            .againstNormal(DEFAULT_AGAINST_NORMAL)
            .againstPoison(DEFAULT_AGAINST_POISON)
            .againstPsychic(DEFAULT_AGAINST_PSYCHIC)
            .againstRock(DEFAULT_AGAINST_ROCK)
            .againstSteel(DEFAULT_AGAINST_STEEL)
            .againstWater(DEFAULT_AGAINST_WATER)
            .attack(DEFAULT_ATTACK)
            .baseEggSteps(DEFAULT_BASE_EGG_STEPS)
            .baseHappiness(DEFAULT_BASE_HAPPINESS)
            .baseTotal(DEFAULT_BASE_TOTAL)
            .captureRate(DEFAULT_CAPTURE_RATE)
            .classfication(DEFAULT_CLASSFICATION)
            .defense(DEFAULT_DEFENSE)
            .experienceGrowth(DEFAULT_EXPERIENCE_GROWTH)
            .heightM(DEFAULT_HEIGHT_M)
            .hp(DEFAULT_HP)
            .japaneseName(DEFAULT_JAPANESE_NAME)
            .name(DEFAULT_NAME)
            .percentageMale(DEFAULT_PERCENTAGE_MALE)
            .pokedexNumber(DEFAULT_POKEDEX_NUMBER)
            .spAttack(DEFAULT_SP_ATTACK)
            .spDefense(DEFAULT_SP_DEFENSE)
            .speed(DEFAULT_SPEED)
            .type1(DEFAULT_TYPE_1)
            .type2(DEFAULT_TYPE_2)
            .weightKg(DEFAULT_WEIGHT_KG)
            .generation(DEFAULT_GENERATION)
            .isLegendary(DEFAULT_IS_LEGENDARY);
        return pokemon;
    }

    @Before
    public void initTest() {
        pokemon = createEntity(em);
    }

    @Test
    @Transactional
    public void createPokemon() throws Exception {
        int databaseSizeBeforeCreate = pokemonRepository.findAll().size();

        // Create the Pokemon
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);
        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isCreated());

        // Validate the Pokemon in the database
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeCreate + 1);
        Pokemon testPokemon = pokemonList.get(pokemonList.size() - 1);
        assertThat(testPokemon.getAbilities()).isEqualTo(DEFAULT_ABILITIES);
        assertThat(testPokemon.getAgainstBug()).isEqualTo(DEFAULT_AGAINST_BUG);
        assertThat(testPokemon.getAgainstDark()).isEqualTo(DEFAULT_AGAINST_DARK);
        assertThat(testPokemon.getAgainstDragon()).isEqualTo(DEFAULT_AGAINST_DRAGON);
        assertThat(testPokemon.getAgainstElectric()).isEqualTo(DEFAULT_AGAINST_ELECTRIC);
        assertThat(testPokemon.getAgainstFairy()).isEqualTo(DEFAULT_AGAINST_FAIRY);
        assertThat(testPokemon.getAgainstFight()).isEqualTo(DEFAULT_AGAINST_FIGHT);
        assertThat(testPokemon.getAgainstFire()).isEqualTo(DEFAULT_AGAINST_FIRE);
        assertThat(testPokemon.getAgainstFlying()).isEqualTo(DEFAULT_AGAINST_FLYING);
        assertThat(testPokemon.getAgainstGhost()).isEqualTo(DEFAULT_AGAINST_GHOST);
        assertThat(testPokemon.getAgainstGrass()).isEqualTo(DEFAULT_AGAINST_GRASS);
        assertThat(testPokemon.getAgainstGround()).isEqualTo(DEFAULT_AGAINST_GROUND);
        assertThat(testPokemon.getAgainstIce()).isEqualTo(DEFAULT_AGAINST_ICE);
        assertThat(testPokemon.getAgainstNormal()).isEqualTo(DEFAULT_AGAINST_NORMAL);
        assertThat(testPokemon.getAgainstPoison()).isEqualTo(DEFAULT_AGAINST_POISON);
        assertThat(testPokemon.getAgainstPsychic()).isEqualTo(DEFAULT_AGAINST_PSYCHIC);
        assertThat(testPokemon.getAgainstRock()).isEqualTo(DEFAULT_AGAINST_ROCK);
        assertThat(testPokemon.getAgainstSteel()).isEqualTo(DEFAULT_AGAINST_STEEL);
        assertThat(testPokemon.getAgainstWater()).isEqualTo(DEFAULT_AGAINST_WATER);
        assertThat(testPokemon.getAttack()).isEqualTo(DEFAULT_ATTACK);
        assertThat(testPokemon.getBaseEggSteps()).isEqualTo(DEFAULT_BASE_EGG_STEPS);
        assertThat(testPokemon.getBaseHappiness()).isEqualTo(DEFAULT_BASE_HAPPINESS);
        assertThat(testPokemon.getBaseTotal()).isEqualTo(DEFAULT_BASE_TOTAL);
        assertThat(testPokemon.getCaptureRate()).isEqualTo(DEFAULT_CAPTURE_RATE);
        assertThat(testPokemon.getClassfication()).isEqualTo(DEFAULT_CLASSFICATION);
        assertThat(testPokemon.getDefense()).isEqualTo(DEFAULT_DEFENSE);
        assertThat(testPokemon.getExperienceGrowth()).isEqualTo(DEFAULT_EXPERIENCE_GROWTH);
        assertThat(testPokemon.getHeightM()).isEqualTo(DEFAULT_HEIGHT_M);
        assertThat(testPokemon.getHp()).isEqualTo(DEFAULT_HP);
        assertThat(testPokemon.getJapaneseName()).isEqualTo(DEFAULT_JAPANESE_NAME);
        assertThat(testPokemon.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPokemon.getPercentageMale()).isEqualTo(DEFAULT_PERCENTAGE_MALE);
        assertThat(testPokemon.getPokedexNumber()).isEqualTo(DEFAULT_POKEDEX_NUMBER);
        assertThat(testPokemon.getSpAttack()).isEqualTo(DEFAULT_SP_ATTACK);
        assertThat(testPokemon.getSpDefense()).isEqualTo(DEFAULT_SP_DEFENSE);
        assertThat(testPokemon.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testPokemon.getType1()).isEqualTo(DEFAULT_TYPE_1);
        assertThat(testPokemon.getType2()).isEqualTo(DEFAULT_TYPE_2);
        assertThat(testPokemon.getWeightKg()).isEqualTo(DEFAULT_WEIGHT_KG);
        assertThat(testPokemon.getGeneration()).isEqualTo(DEFAULT_GENERATION);
        assertThat(testPokemon.getIsLegendary()).isEqualTo(DEFAULT_IS_LEGENDARY);
    }

    @Test
    @Transactional
    public void createPokemonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pokemonRepository.findAll().size();

        // Create the Pokemon with an existing ID
        pokemon.setId(1L);
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pokemon in the database
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAbilitiesIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAbilities(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstBugIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstBug(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstDarkIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstDark(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstDragonIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstDragon(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstElectricIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstElectric(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstFairyIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstFairy(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstFightIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstFight(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstFireIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstFire(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstFlyingIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstFlying(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstGhostIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstGhost(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstGrassIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstGrass(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstGroundIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstGround(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstIceIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstIce(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstNormalIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstNormal(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstPoisonIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstPoison(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstPsychicIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstPsychic(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstRockIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstRock(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstSteelIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstSteel(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgainstWaterIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAgainstWater(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttackIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setAttack(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBaseEggStepsIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setBaseEggSteps(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBaseHappinessIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setBaseHappiness(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBaseTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setBaseTotal(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCaptureRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setCaptureRate(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClassficationIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setClassfication(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefenseIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setDefense(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExperienceGrowthIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setExperienceGrowth(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHpIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setHp(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJapaneseNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setJapaneseName(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setName(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPokedexNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setPokedexNumber(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpAttackIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setSpAttack(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpDefenseIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setSpDefense(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setSpeed(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkType1IsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setType1(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenerationIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setGeneration(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsLegendaryIsRequired() throws Exception {
        int databaseSizeBeforeTest = pokemonRepository.findAll().size();
        // set the field null
        pokemon.setIsLegendary(null);

        // Create the Pokemon, which fails.
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        restPokemonMockMvc.perform(post("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isBadRequest());

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPokemons() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList
        restPokemonMockMvc.perform(get("/api/pokemons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pokemon.getId().intValue())))
            .andExpect(jsonPath("$.[*].abilities").value(hasItem(DEFAULT_ABILITIES.toString())))
            .andExpect(jsonPath("$.[*].againstBug").value(hasItem(DEFAULT_AGAINST_BUG.doubleValue())))
            .andExpect(jsonPath("$.[*].againstDark").value(hasItem(DEFAULT_AGAINST_DARK.doubleValue())))
            .andExpect(jsonPath("$.[*].againstDragon").value(hasItem(DEFAULT_AGAINST_DRAGON.doubleValue())))
            .andExpect(jsonPath("$.[*].againstElectric").value(hasItem(DEFAULT_AGAINST_ELECTRIC.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFairy").value(hasItem(DEFAULT_AGAINST_FAIRY.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFight").value(hasItem(DEFAULT_AGAINST_FIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFire").value(hasItem(DEFAULT_AGAINST_FIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFlying").value(hasItem(DEFAULT_AGAINST_FLYING.doubleValue())))
            .andExpect(jsonPath("$.[*].againstGhost").value(hasItem(DEFAULT_AGAINST_GHOST.doubleValue())))
            .andExpect(jsonPath("$.[*].againstGrass").value(hasItem(DEFAULT_AGAINST_GRASS.doubleValue())))
            .andExpect(jsonPath("$.[*].againstGround").value(hasItem(DEFAULT_AGAINST_GROUND.doubleValue())))
            .andExpect(jsonPath("$.[*].againstIce").value(hasItem(DEFAULT_AGAINST_ICE.doubleValue())))
            .andExpect(jsonPath("$.[*].againstNormal").value(hasItem(DEFAULT_AGAINST_NORMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].againstPoison").value(hasItem(DEFAULT_AGAINST_POISON.doubleValue())))
            .andExpect(jsonPath("$.[*].againstPsychic").value(hasItem(DEFAULT_AGAINST_PSYCHIC.doubleValue())))
            .andExpect(jsonPath("$.[*].againstRock").value(hasItem(DEFAULT_AGAINST_ROCK.doubleValue())))
            .andExpect(jsonPath("$.[*].againstSteel").value(hasItem(DEFAULT_AGAINST_STEEL.doubleValue())))
            .andExpect(jsonPath("$.[*].againstWater").value(hasItem(DEFAULT_AGAINST_WATER.doubleValue())))
            .andExpect(jsonPath("$.[*].attack").value(hasItem(DEFAULT_ATTACK)))
            .andExpect(jsonPath("$.[*].baseEggSteps").value(hasItem(DEFAULT_BASE_EGG_STEPS)))
            .andExpect(jsonPath("$.[*].baseHappiness").value(hasItem(DEFAULT_BASE_HAPPINESS)))
            .andExpect(jsonPath("$.[*].baseTotal").value(hasItem(DEFAULT_BASE_TOTAL)))
            .andExpect(jsonPath("$.[*].captureRate").value(hasItem(DEFAULT_CAPTURE_RATE.toString())))
            .andExpect(jsonPath("$.[*].classfication").value(hasItem(DEFAULT_CLASSFICATION.toString())))
            .andExpect(jsonPath("$.[*].defense").value(hasItem(DEFAULT_DEFENSE)))
            .andExpect(jsonPath("$.[*].experienceGrowth").value(hasItem(DEFAULT_EXPERIENCE_GROWTH)))
            .andExpect(jsonPath("$.[*].heightM").value(hasItem(DEFAULT_HEIGHT_M.doubleValue())))
            .andExpect(jsonPath("$.[*].hp").value(hasItem(DEFAULT_HP)))
            .andExpect(jsonPath("$.[*].japaneseName").value(hasItem(DEFAULT_JAPANESE_NAME.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].percentageMale").value(hasItem(DEFAULT_PERCENTAGE_MALE.doubleValue())))
            .andExpect(jsonPath("$.[*].pokedexNumber").value(hasItem(DEFAULT_POKEDEX_NUMBER)))
            .andExpect(jsonPath("$.[*].spAttack").value(hasItem(DEFAULT_SP_ATTACK)))
            .andExpect(jsonPath("$.[*].spDefense").value(hasItem(DEFAULT_SP_DEFENSE)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].type1").value(hasItem(DEFAULT_TYPE_1.toString())))
            .andExpect(jsonPath("$.[*].type2").value(hasItem(DEFAULT_TYPE_2.toString())))
            .andExpect(jsonPath("$.[*].weightKg").value(hasItem(DEFAULT_WEIGHT_KG.doubleValue())))
            .andExpect(jsonPath("$.[*].generation").value(hasItem(DEFAULT_GENERATION)))
            .andExpect(jsonPath("$.[*].isLegendary").value(hasItem(DEFAULT_IS_LEGENDARY)));
    }

    @Test
    @Transactional
    public void getPokemon() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get the pokemon
        restPokemonMockMvc.perform(get("/api/pokemons/{id}", pokemon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pokemon.getId().intValue()))
            .andExpect(jsonPath("$.abilities").value(DEFAULT_ABILITIES.toString()))
            .andExpect(jsonPath("$.againstBug").value(DEFAULT_AGAINST_BUG.doubleValue()))
            .andExpect(jsonPath("$.againstDark").value(DEFAULT_AGAINST_DARK.doubleValue()))
            .andExpect(jsonPath("$.againstDragon").value(DEFAULT_AGAINST_DRAGON.doubleValue()))
            .andExpect(jsonPath("$.againstElectric").value(DEFAULT_AGAINST_ELECTRIC.doubleValue()))
            .andExpect(jsonPath("$.againstFairy").value(DEFAULT_AGAINST_FAIRY.doubleValue()))
            .andExpect(jsonPath("$.againstFight").value(DEFAULT_AGAINST_FIGHT.doubleValue()))
            .andExpect(jsonPath("$.againstFire").value(DEFAULT_AGAINST_FIRE.doubleValue()))
            .andExpect(jsonPath("$.againstFlying").value(DEFAULT_AGAINST_FLYING.doubleValue()))
            .andExpect(jsonPath("$.againstGhost").value(DEFAULT_AGAINST_GHOST.doubleValue()))
            .andExpect(jsonPath("$.againstGrass").value(DEFAULT_AGAINST_GRASS.doubleValue()))
            .andExpect(jsonPath("$.againstGround").value(DEFAULT_AGAINST_GROUND.doubleValue()))
            .andExpect(jsonPath("$.againstIce").value(DEFAULT_AGAINST_ICE.doubleValue()))
            .andExpect(jsonPath("$.againstNormal").value(DEFAULT_AGAINST_NORMAL.doubleValue()))
            .andExpect(jsonPath("$.againstPoison").value(DEFAULT_AGAINST_POISON.doubleValue()))
            .andExpect(jsonPath("$.againstPsychic").value(DEFAULT_AGAINST_PSYCHIC.doubleValue()))
            .andExpect(jsonPath("$.againstRock").value(DEFAULT_AGAINST_ROCK.doubleValue()))
            .andExpect(jsonPath("$.againstSteel").value(DEFAULT_AGAINST_STEEL.doubleValue()))
            .andExpect(jsonPath("$.againstWater").value(DEFAULT_AGAINST_WATER.doubleValue()))
            .andExpect(jsonPath("$.attack").value(DEFAULT_ATTACK))
            .andExpect(jsonPath("$.baseEggSteps").value(DEFAULT_BASE_EGG_STEPS))
            .andExpect(jsonPath("$.baseHappiness").value(DEFAULT_BASE_HAPPINESS))
            .andExpect(jsonPath("$.baseTotal").value(DEFAULT_BASE_TOTAL))
            .andExpect(jsonPath("$.captureRate").value(DEFAULT_CAPTURE_RATE.toString()))
            .andExpect(jsonPath("$.classfication").value(DEFAULT_CLASSFICATION.toString()))
            .andExpect(jsonPath("$.defense").value(DEFAULT_DEFENSE))
            .andExpect(jsonPath("$.experienceGrowth").value(DEFAULT_EXPERIENCE_GROWTH))
            .andExpect(jsonPath("$.heightM").value(DEFAULT_HEIGHT_M.doubleValue()))
            .andExpect(jsonPath("$.hp").value(DEFAULT_HP))
            .andExpect(jsonPath("$.japaneseName").value(DEFAULT_JAPANESE_NAME.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.percentageMale").value(DEFAULT_PERCENTAGE_MALE.doubleValue()))
            .andExpect(jsonPath("$.pokedexNumber").value(DEFAULT_POKEDEX_NUMBER))
            .andExpect(jsonPath("$.spAttack").value(DEFAULT_SP_ATTACK))
            .andExpect(jsonPath("$.spDefense").value(DEFAULT_SP_DEFENSE))
            .andExpect(jsonPath("$.speed").value(DEFAULT_SPEED))
            .andExpect(jsonPath("$.type1").value(DEFAULT_TYPE_1.toString()))
            .andExpect(jsonPath("$.type2").value(DEFAULT_TYPE_2.toString()))
            .andExpect(jsonPath("$.weightKg").value(DEFAULT_WEIGHT_KG.doubleValue()))
            .andExpect(jsonPath("$.generation").value(DEFAULT_GENERATION))
            .andExpect(jsonPath("$.isLegendary").value(DEFAULT_IS_LEGENDARY));
    }

    @Test
    @Transactional
    public void getAllPokemonsByAbilitiesIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where abilities equals to DEFAULT_ABILITIES
        defaultPokemonShouldBeFound("abilities.equals=" + DEFAULT_ABILITIES);

        // Get all the pokemonList where abilities equals to UPDATED_ABILITIES
        defaultPokemonShouldNotBeFound("abilities.equals=" + UPDATED_ABILITIES);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAbilitiesIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where abilities in DEFAULT_ABILITIES or UPDATED_ABILITIES
        defaultPokemonShouldBeFound("abilities.in=" + DEFAULT_ABILITIES + "," + UPDATED_ABILITIES);

        // Get all the pokemonList where abilities equals to UPDATED_ABILITIES
        defaultPokemonShouldNotBeFound("abilities.in=" + UPDATED_ABILITIES);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAbilitiesIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where abilities is not null
        defaultPokemonShouldBeFound("abilities.specified=true");

        // Get all the pokemonList where abilities is null
        defaultPokemonShouldNotBeFound("abilities.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstBugIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstBug equals to DEFAULT_AGAINST_BUG
        defaultPokemonShouldBeFound("againstBug.equals=" + DEFAULT_AGAINST_BUG);

        // Get all the pokemonList where againstBug equals to UPDATED_AGAINST_BUG
        defaultPokemonShouldNotBeFound("againstBug.equals=" + UPDATED_AGAINST_BUG);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstBugIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstBug in DEFAULT_AGAINST_BUG or UPDATED_AGAINST_BUG
        defaultPokemonShouldBeFound("againstBug.in=" + DEFAULT_AGAINST_BUG + "," + UPDATED_AGAINST_BUG);

        // Get all the pokemonList where againstBug equals to UPDATED_AGAINST_BUG
        defaultPokemonShouldNotBeFound("againstBug.in=" + UPDATED_AGAINST_BUG);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstBugIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstBug is not null
        defaultPokemonShouldBeFound("againstBug.specified=true");

        // Get all the pokemonList where againstBug is null
        defaultPokemonShouldNotBeFound("againstBug.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstDarkIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstDark equals to DEFAULT_AGAINST_DARK
        defaultPokemonShouldBeFound("againstDark.equals=" + DEFAULT_AGAINST_DARK);

        // Get all the pokemonList where againstDark equals to UPDATED_AGAINST_DARK
        defaultPokemonShouldNotBeFound("againstDark.equals=" + UPDATED_AGAINST_DARK);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstDarkIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstDark in DEFAULT_AGAINST_DARK or UPDATED_AGAINST_DARK
        defaultPokemonShouldBeFound("againstDark.in=" + DEFAULT_AGAINST_DARK + "," + UPDATED_AGAINST_DARK);

        // Get all the pokemonList where againstDark equals to UPDATED_AGAINST_DARK
        defaultPokemonShouldNotBeFound("againstDark.in=" + UPDATED_AGAINST_DARK);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstDarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstDark is not null
        defaultPokemonShouldBeFound("againstDark.specified=true");

        // Get all the pokemonList where againstDark is null
        defaultPokemonShouldNotBeFound("againstDark.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstDragonIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstDragon equals to DEFAULT_AGAINST_DRAGON
        defaultPokemonShouldBeFound("againstDragon.equals=" + DEFAULT_AGAINST_DRAGON);

        // Get all the pokemonList where againstDragon equals to UPDATED_AGAINST_DRAGON
        defaultPokemonShouldNotBeFound("againstDragon.equals=" + UPDATED_AGAINST_DRAGON);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstDragonIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstDragon in DEFAULT_AGAINST_DRAGON or UPDATED_AGAINST_DRAGON
        defaultPokemonShouldBeFound("againstDragon.in=" + DEFAULT_AGAINST_DRAGON + "," + UPDATED_AGAINST_DRAGON);

        // Get all the pokemonList where againstDragon equals to UPDATED_AGAINST_DRAGON
        defaultPokemonShouldNotBeFound("againstDragon.in=" + UPDATED_AGAINST_DRAGON);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstDragonIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstDragon is not null
        defaultPokemonShouldBeFound("againstDragon.specified=true");

        // Get all the pokemonList where againstDragon is null
        defaultPokemonShouldNotBeFound("againstDragon.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstElectricIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstElectric equals to DEFAULT_AGAINST_ELECTRIC
        defaultPokemonShouldBeFound("againstElectric.equals=" + DEFAULT_AGAINST_ELECTRIC);

        // Get all the pokemonList where againstElectric equals to UPDATED_AGAINST_ELECTRIC
        defaultPokemonShouldNotBeFound("againstElectric.equals=" + UPDATED_AGAINST_ELECTRIC);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstElectricIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstElectric in DEFAULT_AGAINST_ELECTRIC or UPDATED_AGAINST_ELECTRIC
        defaultPokemonShouldBeFound("againstElectric.in=" + DEFAULT_AGAINST_ELECTRIC + "," + UPDATED_AGAINST_ELECTRIC);

        // Get all the pokemonList where againstElectric equals to UPDATED_AGAINST_ELECTRIC
        defaultPokemonShouldNotBeFound("againstElectric.in=" + UPDATED_AGAINST_ELECTRIC);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstElectricIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstElectric is not null
        defaultPokemonShouldBeFound("againstElectric.specified=true");

        // Get all the pokemonList where againstElectric is null
        defaultPokemonShouldNotBeFound("againstElectric.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFairyIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFairy equals to DEFAULT_AGAINST_FAIRY
        defaultPokemonShouldBeFound("againstFairy.equals=" + DEFAULT_AGAINST_FAIRY);

        // Get all the pokemonList where againstFairy equals to UPDATED_AGAINST_FAIRY
        defaultPokemonShouldNotBeFound("againstFairy.equals=" + UPDATED_AGAINST_FAIRY);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFairyIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFairy in DEFAULT_AGAINST_FAIRY or UPDATED_AGAINST_FAIRY
        defaultPokemonShouldBeFound("againstFairy.in=" + DEFAULT_AGAINST_FAIRY + "," + UPDATED_AGAINST_FAIRY);

        // Get all the pokemonList where againstFairy equals to UPDATED_AGAINST_FAIRY
        defaultPokemonShouldNotBeFound("againstFairy.in=" + UPDATED_AGAINST_FAIRY);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFairyIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFairy is not null
        defaultPokemonShouldBeFound("againstFairy.specified=true");

        // Get all the pokemonList where againstFairy is null
        defaultPokemonShouldNotBeFound("againstFairy.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFightIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFight equals to DEFAULT_AGAINST_FIGHT
        defaultPokemonShouldBeFound("againstFight.equals=" + DEFAULT_AGAINST_FIGHT);

        // Get all the pokemonList where againstFight equals to UPDATED_AGAINST_FIGHT
        defaultPokemonShouldNotBeFound("againstFight.equals=" + UPDATED_AGAINST_FIGHT);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFightIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFight in DEFAULT_AGAINST_FIGHT or UPDATED_AGAINST_FIGHT
        defaultPokemonShouldBeFound("againstFight.in=" + DEFAULT_AGAINST_FIGHT + "," + UPDATED_AGAINST_FIGHT);

        // Get all the pokemonList where againstFight equals to UPDATED_AGAINST_FIGHT
        defaultPokemonShouldNotBeFound("againstFight.in=" + UPDATED_AGAINST_FIGHT);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFightIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFight is not null
        defaultPokemonShouldBeFound("againstFight.specified=true");

        // Get all the pokemonList where againstFight is null
        defaultPokemonShouldNotBeFound("againstFight.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFireIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFire equals to DEFAULT_AGAINST_FIRE
        defaultPokemonShouldBeFound("againstFire.equals=" + DEFAULT_AGAINST_FIRE);

        // Get all the pokemonList where againstFire equals to UPDATED_AGAINST_FIRE
        defaultPokemonShouldNotBeFound("againstFire.equals=" + UPDATED_AGAINST_FIRE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFireIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFire in DEFAULT_AGAINST_FIRE or UPDATED_AGAINST_FIRE
        defaultPokemonShouldBeFound("againstFire.in=" + DEFAULT_AGAINST_FIRE + "," + UPDATED_AGAINST_FIRE);

        // Get all the pokemonList where againstFire equals to UPDATED_AGAINST_FIRE
        defaultPokemonShouldNotBeFound("againstFire.in=" + UPDATED_AGAINST_FIRE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFireIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFire is not null
        defaultPokemonShouldBeFound("againstFire.specified=true");

        // Get all the pokemonList where againstFire is null
        defaultPokemonShouldNotBeFound("againstFire.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFlyingIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFlying equals to DEFAULT_AGAINST_FLYING
        defaultPokemonShouldBeFound("againstFlying.equals=" + DEFAULT_AGAINST_FLYING);

        // Get all the pokemonList where againstFlying equals to UPDATED_AGAINST_FLYING
        defaultPokemonShouldNotBeFound("againstFlying.equals=" + UPDATED_AGAINST_FLYING);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFlyingIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFlying in DEFAULT_AGAINST_FLYING or UPDATED_AGAINST_FLYING
        defaultPokemonShouldBeFound("againstFlying.in=" + DEFAULT_AGAINST_FLYING + "," + UPDATED_AGAINST_FLYING);

        // Get all the pokemonList where againstFlying equals to UPDATED_AGAINST_FLYING
        defaultPokemonShouldNotBeFound("againstFlying.in=" + UPDATED_AGAINST_FLYING);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstFlyingIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstFlying is not null
        defaultPokemonShouldBeFound("againstFlying.specified=true");

        // Get all the pokemonList where againstFlying is null
        defaultPokemonShouldNotBeFound("againstFlying.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGhostIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGhost equals to DEFAULT_AGAINST_GHOST
        defaultPokemonShouldBeFound("againstGhost.equals=" + DEFAULT_AGAINST_GHOST);

        // Get all the pokemonList where againstGhost equals to UPDATED_AGAINST_GHOST
        defaultPokemonShouldNotBeFound("againstGhost.equals=" + UPDATED_AGAINST_GHOST);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGhostIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGhost in DEFAULT_AGAINST_GHOST or UPDATED_AGAINST_GHOST
        defaultPokemonShouldBeFound("againstGhost.in=" + DEFAULT_AGAINST_GHOST + "," + UPDATED_AGAINST_GHOST);

        // Get all the pokemonList where againstGhost equals to UPDATED_AGAINST_GHOST
        defaultPokemonShouldNotBeFound("againstGhost.in=" + UPDATED_AGAINST_GHOST);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGhostIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGhost is not null
        defaultPokemonShouldBeFound("againstGhost.specified=true");

        // Get all the pokemonList where againstGhost is null
        defaultPokemonShouldNotBeFound("againstGhost.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGrassIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGrass equals to DEFAULT_AGAINST_GRASS
        defaultPokemonShouldBeFound("againstGrass.equals=" + DEFAULT_AGAINST_GRASS);

        // Get all the pokemonList where againstGrass equals to UPDATED_AGAINST_GRASS
        defaultPokemonShouldNotBeFound("againstGrass.equals=" + UPDATED_AGAINST_GRASS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGrassIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGrass in DEFAULT_AGAINST_GRASS or UPDATED_AGAINST_GRASS
        defaultPokemonShouldBeFound("againstGrass.in=" + DEFAULT_AGAINST_GRASS + "," + UPDATED_AGAINST_GRASS);

        // Get all the pokemonList where againstGrass equals to UPDATED_AGAINST_GRASS
        defaultPokemonShouldNotBeFound("againstGrass.in=" + UPDATED_AGAINST_GRASS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGrassIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGrass is not null
        defaultPokemonShouldBeFound("againstGrass.specified=true");

        // Get all the pokemonList where againstGrass is null
        defaultPokemonShouldNotBeFound("againstGrass.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGroundIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGround equals to DEFAULT_AGAINST_GROUND
        defaultPokemonShouldBeFound("againstGround.equals=" + DEFAULT_AGAINST_GROUND);

        // Get all the pokemonList where againstGround equals to UPDATED_AGAINST_GROUND
        defaultPokemonShouldNotBeFound("againstGround.equals=" + UPDATED_AGAINST_GROUND);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGroundIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGround in DEFAULT_AGAINST_GROUND or UPDATED_AGAINST_GROUND
        defaultPokemonShouldBeFound("againstGround.in=" + DEFAULT_AGAINST_GROUND + "," + UPDATED_AGAINST_GROUND);

        // Get all the pokemonList where againstGround equals to UPDATED_AGAINST_GROUND
        defaultPokemonShouldNotBeFound("againstGround.in=" + UPDATED_AGAINST_GROUND);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstGroundIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstGround is not null
        defaultPokemonShouldBeFound("againstGround.specified=true");

        // Get all the pokemonList where againstGround is null
        defaultPokemonShouldNotBeFound("againstGround.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstIceIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstIce equals to DEFAULT_AGAINST_ICE
        defaultPokemonShouldBeFound("againstIce.equals=" + DEFAULT_AGAINST_ICE);

        // Get all the pokemonList where againstIce equals to UPDATED_AGAINST_ICE
        defaultPokemonShouldNotBeFound("againstIce.equals=" + UPDATED_AGAINST_ICE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstIceIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstIce in DEFAULT_AGAINST_ICE or UPDATED_AGAINST_ICE
        defaultPokemonShouldBeFound("againstIce.in=" + DEFAULT_AGAINST_ICE + "," + UPDATED_AGAINST_ICE);

        // Get all the pokemonList where againstIce equals to UPDATED_AGAINST_ICE
        defaultPokemonShouldNotBeFound("againstIce.in=" + UPDATED_AGAINST_ICE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstIceIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstIce is not null
        defaultPokemonShouldBeFound("againstIce.specified=true");

        // Get all the pokemonList where againstIce is null
        defaultPokemonShouldNotBeFound("againstIce.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstNormalIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstNormal equals to DEFAULT_AGAINST_NORMAL
        defaultPokemonShouldBeFound("againstNormal.equals=" + DEFAULT_AGAINST_NORMAL);

        // Get all the pokemonList where againstNormal equals to UPDATED_AGAINST_NORMAL
        defaultPokemonShouldNotBeFound("againstNormal.equals=" + UPDATED_AGAINST_NORMAL);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstNormalIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstNormal in DEFAULT_AGAINST_NORMAL or UPDATED_AGAINST_NORMAL
        defaultPokemonShouldBeFound("againstNormal.in=" + DEFAULT_AGAINST_NORMAL + "," + UPDATED_AGAINST_NORMAL);

        // Get all the pokemonList where againstNormal equals to UPDATED_AGAINST_NORMAL
        defaultPokemonShouldNotBeFound("againstNormal.in=" + UPDATED_AGAINST_NORMAL);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstNormalIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstNormal is not null
        defaultPokemonShouldBeFound("againstNormal.specified=true");

        // Get all the pokemonList where againstNormal is null
        defaultPokemonShouldNotBeFound("againstNormal.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstPoisonIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstPoison equals to DEFAULT_AGAINST_POISON
        defaultPokemonShouldBeFound("againstPoison.equals=" + DEFAULT_AGAINST_POISON);

        // Get all the pokemonList where againstPoison equals to UPDATED_AGAINST_POISON
        defaultPokemonShouldNotBeFound("againstPoison.equals=" + UPDATED_AGAINST_POISON);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstPoisonIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstPoison in DEFAULT_AGAINST_POISON or UPDATED_AGAINST_POISON
        defaultPokemonShouldBeFound("againstPoison.in=" + DEFAULT_AGAINST_POISON + "," + UPDATED_AGAINST_POISON);

        // Get all the pokemonList where againstPoison equals to UPDATED_AGAINST_POISON
        defaultPokemonShouldNotBeFound("againstPoison.in=" + UPDATED_AGAINST_POISON);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstPoisonIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstPoison is not null
        defaultPokemonShouldBeFound("againstPoison.specified=true");

        // Get all the pokemonList where againstPoison is null
        defaultPokemonShouldNotBeFound("againstPoison.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstPsychicIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstPsychic equals to DEFAULT_AGAINST_PSYCHIC
        defaultPokemonShouldBeFound("againstPsychic.equals=" + DEFAULT_AGAINST_PSYCHIC);

        // Get all the pokemonList where againstPsychic equals to UPDATED_AGAINST_PSYCHIC
        defaultPokemonShouldNotBeFound("againstPsychic.equals=" + UPDATED_AGAINST_PSYCHIC);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstPsychicIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstPsychic in DEFAULT_AGAINST_PSYCHIC or UPDATED_AGAINST_PSYCHIC
        defaultPokemonShouldBeFound("againstPsychic.in=" + DEFAULT_AGAINST_PSYCHIC + "," + UPDATED_AGAINST_PSYCHIC);

        // Get all the pokemonList where againstPsychic equals to UPDATED_AGAINST_PSYCHIC
        defaultPokemonShouldNotBeFound("againstPsychic.in=" + UPDATED_AGAINST_PSYCHIC);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstPsychicIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstPsychic is not null
        defaultPokemonShouldBeFound("againstPsychic.specified=true");

        // Get all the pokemonList where againstPsychic is null
        defaultPokemonShouldNotBeFound("againstPsychic.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstRockIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstRock equals to DEFAULT_AGAINST_ROCK
        defaultPokemonShouldBeFound("againstRock.equals=" + DEFAULT_AGAINST_ROCK);

        // Get all the pokemonList where againstRock equals to UPDATED_AGAINST_ROCK
        defaultPokemonShouldNotBeFound("againstRock.equals=" + UPDATED_AGAINST_ROCK);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstRockIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstRock in DEFAULT_AGAINST_ROCK or UPDATED_AGAINST_ROCK
        defaultPokemonShouldBeFound("againstRock.in=" + DEFAULT_AGAINST_ROCK + "," + UPDATED_AGAINST_ROCK);

        // Get all the pokemonList where againstRock equals to UPDATED_AGAINST_ROCK
        defaultPokemonShouldNotBeFound("againstRock.in=" + UPDATED_AGAINST_ROCK);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstRockIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstRock is not null
        defaultPokemonShouldBeFound("againstRock.specified=true");

        // Get all the pokemonList where againstRock is null
        defaultPokemonShouldNotBeFound("againstRock.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstSteelIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstSteel equals to DEFAULT_AGAINST_STEEL
        defaultPokemonShouldBeFound("againstSteel.equals=" + DEFAULT_AGAINST_STEEL);

        // Get all the pokemonList where againstSteel equals to UPDATED_AGAINST_STEEL
        defaultPokemonShouldNotBeFound("againstSteel.equals=" + UPDATED_AGAINST_STEEL);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstSteelIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstSteel in DEFAULT_AGAINST_STEEL or UPDATED_AGAINST_STEEL
        defaultPokemonShouldBeFound("againstSteel.in=" + DEFAULT_AGAINST_STEEL + "," + UPDATED_AGAINST_STEEL);

        // Get all the pokemonList where againstSteel equals to UPDATED_AGAINST_STEEL
        defaultPokemonShouldNotBeFound("againstSteel.in=" + UPDATED_AGAINST_STEEL);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstSteelIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstSteel is not null
        defaultPokemonShouldBeFound("againstSteel.specified=true");

        // Get all the pokemonList where againstSteel is null
        defaultPokemonShouldNotBeFound("againstSteel.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstWaterIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstWater equals to DEFAULT_AGAINST_WATER
        defaultPokemonShouldBeFound("againstWater.equals=" + DEFAULT_AGAINST_WATER);

        // Get all the pokemonList where againstWater equals to UPDATED_AGAINST_WATER
        defaultPokemonShouldNotBeFound("againstWater.equals=" + UPDATED_AGAINST_WATER);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstWaterIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstWater in DEFAULT_AGAINST_WATER or UPDATED_AGAINST_WATER
        defaultPokemonShouldBeFound("againstWater.in=" + DEFAULT_AGAINST_WATER + "," + UPDATED_AGAINST_WATER);

        // Get all the pokemonList where againstWater equals to UPDATED_AGAINST_WATER
        defaultPokemonShouldNotBeFound("againstWater.in=" + UPDATED_AGAINST_WATER);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAgainstWaterIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where againstWater is not null
        defaultPokemonShouldBeFound("againstWater.specified=true");

        // Get all the pokemonList where againstWater is null
        defaultPokemonShouldNotBeFound("againstWater.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAttackIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where attack equals to DEFAULT_ATTACK
        defaultPokemonShouldBeFound("attack.equals=" + DEFAULT_ATTACK);

        // Get all the pokemonList where attack equals to UPDATED_ATTACK
        defaultPokemonShouldNotBeFound("attack.equals=" + UPDATED_ATTACK);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAttackIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where attack in DEFAULT_ATTACK or UPDATED_ATTACK
        defaultPokemonShouldBeFound("attack.in=" + DEFAULT_ATTACK + "," + UPDATED_ATTACK);

        // Get all the pokemonList where attack equals to UPDATED_ATTACK
        defaultPokemonShouldNotBeFound("attack.in=" + UPDATED_ATTACK);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAttackIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where attack is not null
        defaultPokemonShouldBeFound("attack.specified=true");

        // Get all the pokemonList where attack is null
        defaultPokemonShouldNotBeFound("attack.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByAttackIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where attack greater than or equals to DEFAULT_ATTACK
        defaultPokemonShouldBeFound("attack.greaterOrEqualThan=" + DEFAULT_ATTACK);

        // Get all the pokemonList where attack greater than or equals to UPDATED_ATTACK
        defaultPokemonShouldNotBeFound("attack.greaterOrEqualThan=" + UPDATED_ATTACK);
    }

    @Test
    @Transactional
    public void getAllPokemonsByAttackIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where attack less than or equals to DEFAULT_ATTACK
        defaultPokemonShouldNotBeFound("attack.lessThan=" + DEFAULT_ATTACK);

        // Get all the pokemonList where attack less than or equals to UPDATED_ATTACK
        defaultPokemonShouldBeFound("attack.lessThan=" + UPDATED_ATTACK);
    }


    @Test
    @Transactional
    public void getAllPokemonsByBaseEggStepsIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseEggSteps equals to DEFAULT_BASE_EGG_STEPS
        defaultPokemonShouldBeFound("baseEggSteps.equals=" + DEFAULT_BASE_EGG_STEPS);

        // Get all the pokemonList where baseEggSteps equals to UPDATED_BASE_EGG_STEPS
        defaultPokemonShouldNotBeFound("baseEggSteps.equals=" + UPDATED_BASE_EGG_STEPS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseEggStepsIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseEggSteps in DEFAULT_BASE_EGG_STEPS or UPDATED_BASE_EGG_STEPS
        defaultPokemonShouldBeFound("baseEggSteps.in=" + DEFAULT_BASE_EGG_STEPS + "," + UPDATED_BASE_EGG_STEPS);

        // Get all the pokemonList where baseEggSteps equals to UPDATED_BASE_EGG_STEPS
        defaultPokemonShouldNotBeFound("baseEggSteps.in=" + UPDATED_BASE_EGG_STEPS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseEggStepsIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseEggSteps is not null
        defaultPokemonShouldBeFound("baseEggSteps.specified=true");

        // Get all the pokemonList where baseEggSteps is null
        defaultPokemonShouldNotBeFound("baseEggSteps.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseEggStepsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseEggSteps greater than or equals to DEFAULT_BASE_EGG_STEPS
        defaultPokemonShouldBeFound("baseEggSteps.greaterOrEqualThan=" + DEFAULT_BASE_EGG_STEPS);

        // Get all the pokemonList where baseEggSteps greater than or equals to UPDATED_BASE_EGG_STEPS
        defaultPokemonShouldNotBeFound("baseEggSteps.greaterOrEqualThan=" + UPDATED_BASE_EGG_STEPS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseEggStepsIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseEggSteps less than or equals to DEFAULT_BASE_EGG_STEPS
        defaultPokemonShouldNotBeFound("baseEggSteps.lessThan=" + DEFAULT_BASE_EGG_STEPS);

        // Get all the pokemonList where baseEggSteps less than or equals to UPDATED_BASE_EGG_STEPS
        defaultPokemonShouldBeFound("baseEggSteps.lessThan=" + UPDATED_BASE_EGG_STEPS);
    }


    @Test
    @Transactional
    public void getAllPokemonsByBaseHappinessIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseHappiness equals to DEFAULT_BASE_HAPPINESS
        defaultPokemonShouldBeFound("baseHappiness.equals=" + DEFAULT_BASE_HAPPINESS);

        // Get all the pokemonList where baseHappiness equals to UPDATED_BASE_HAPPINESS
        defaultPokemonShouldNotBeFound("baseHappiness.equals=" + UPDATED_BASE_HAPPINESS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseHappinessIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseHappiness in DEFAULT_BASE_HAPPINESS or UPDATED_BASE_HAPPINESS
        defaultPokemonShouldBeFound("baseHappiness.in=" + DEFAULT_BASE_HAPPINESS + "," + UPDATED_BASE_HAPPINESS);

        // Get all the pokemonList where baseHappiness equals to UPDATED_BASE_HAPPINESS
        defaultPokemonShouldNotBeFound("baseHappiness.in=" + UPDATED_BASE_HAPPINESS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseHappinessIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseHappiness is not null
        defaultPokemonShouldBeFound("baseHappiness.specified=true");

        // Get all the pokemonList where baseHappiness is null
        defaultPokemonShouldNotBeFound("baseHappiness.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseHappinessIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseHappiness greater than or equals to DEFAULT_BASE_HAPPINESS
        defaultPokemonShouldBeFound("baseHappiness.greaterOrEqualThan=" + DEFAULT_BASE_HAPPINESS);

        // Get all the pokemonList where baseHappiness greater than or equals to UPDATED_BASE_HAPPINESS
        defaultPokemonShouldNotBeFound("baseHappiness.greaterOrEqualThan=" + UPDATED_BASE_HAPPINESS);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseHappinessIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseHappiness less than or equals to DEFAULT_BASE_HAPPINESS
        defaultPokemonShouldNotBeFound("baseHappiness.lessThan=" + DEFAULT_BASE_HAPPINESS);

        // Get all the pokemonList where baseHappiness less than or equals to UPDATED_BASE_HAPPINESS
        defaultPokemonShouldBeFound("baseHappiness.lessThan=" + UPDATED_BASE_HAPPINESS);
    }


    @Test
    @Transactional
    public void getAllPokemonsByBaseTotalIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseTotal equals to DEFAULT_BASE_TOTAL
        defaultPokemonShouldBeFound("baseTotal.equals=" + DEFAULT_BASE_TOTAL);

        // Get all the pokemonList where baseTotal equals to UPDATED_BASE_TOTAL
        defaultPokemonShouldNotBeFound("baseTotal.equals=" + UPDATED_BASE_TOTAL);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseTotalIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseTotal in DEFAULT_BASE_TOTAL or UPDATED_BASE_TOTAL
        defaultPokemonShouldBeFound("baseTotal.in=" + DEFAULT_BASE_TOTAL + "," + UPDATED_BASE_TOTAL);

        // Get all the pokemonList where baseTotal equals to UPDATED_BASE_TOTAL
        defaultPokemonShouldNotBeFound("baseTotal.in=" + UPDATED_BASE_TOTAL);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseTotalIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseTotal is not null
        defaultPokemonShouldBeFound("baseTotal.specified=true");

        // Get all the pokemonList where baseTotal is null
        defaultPokemonShouldNotBeFound("baseTotal.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseTotalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseTotal greater than or equals to DEFAULT_BASE_TOTAL
        defaultPokemonShouldBeFound("baseTotal.greaterOrEqualThan=" + DEFAULT_BASE_TOTAL);

        // Get all the pokemonList where baseTotal greater than or equals to UPDATED_BASE_TOTAL
        defaultPokemonShouldNotBeFound("baseTotal.greaterOrEqualThan=" + UPDATED_BASE_TOTAL);
    }

    @Test
    @Transactional
    public void getAllPokemonsByBaseTotalIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where baseTotal less than or equals to DEFAULT_BASE_TOTAL
        defaultPokemonShouldNotBeFound("baseTotal.lessThan=" + DEFAULT_BASE_TOTAL);

        // Get all the pokemonList where baseTotal less than or equals to UPDATED_BASE_TOTAL
        defaultPokemonShouldBeFound("baseTotal.lessThan=" + UPDATED_BASE_TOTAL);
    }


    @Test
    @Transactional
    public void getAllPokemonsByCaptureRateIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where captureRate equals to DEFAULT_CAPTURE_RATE
        defaultPokemonShouldBeFound("captureRate.equals=" + DEFAULT_CAPTURE_RATE);

        // Get all the pokemonList where captureRate equals to UPDATED_CAPTURE_RATE
        defaultPokemonShouldNotBeFound("captureRate.equals=" + UPDATED_CAPTURE_RATE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByCaptureRateIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where captureRate in DEFAULT_CAPTURE_RATE or UPDATED_CAPTURE_RATE
        defaultPokemonShouldBeFound("captureRate.in=" + DEFAULT_CAPTURE_RATE + "," + UPDATED_CAPTURE_RATE);

        // Get all the pokemonList where captureRate equals to UPDATED_CAPTURE_RATE
        defaultPokemonShouldNotBeFound("captureRate.in=" + UPDATED_CAPTURE_RATE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByCaptureRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where captureRate is not null
        defaultPokemonShouldBeFound("captureRate.specified=true");

        // Get all the pokemonList where captureRate is null
        defaultPokemonShouldNotBeFound("captureRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByClassficationIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where classfication equals to DEFAULT_CLASSFICATION
        defaultPokemonShouldBeFound("classfication.equals=" + DEFAULT_CLASSFICATION);

        // Get all the pokemonList where classfication equals to UPDATED_CLASSFICATION
        defaultPokemonShouldNotBeFound("classfication.equals=" + UPDATED_CLASSFICATION);
    }

    @Test
    @Transactional
    public void getAllPokemonsByClassficationIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where classfication in DEFAULT_CLASSFICATION or UPDATED_CLASSFICATION
        defaultPokemonShouldBeFound("classfication.in=" + DEFAULT_CLASSFICATION + "," + UPDATED_CLASSFICATION);

        // Get all the pokemonList where classfication equals to UPDATED_CLASSFICATION
        defaultPokemonShouldNotBeFound("classfication.in=" + UPDATED_CLASSFICATION);
    }

    @Test
    @Transactional
    public void getAllPokemonsByClassficationIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where classfication is not null
        defaultPokemonShouldBeFound("classfication.specified=true");

        // Get all the pokemonList where classfication is null
        defaultPokemonShouldNotBeFound("classfication.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByDefenseIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where defense equals to DEFAULT_DEFENSE
        defaultPokemonShouldBeFound("defense.equals=" + DEFAULT_DEFENSE);

        // Get all the pokemonList where defense equals to UPDATED_DEFENSE
        defaultPokemonShouldNotBeFound("defense.equals=" + UPDATED_DEFENSE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByDefenseIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where defense in DEFAULT_DEFENSE or UPDATED_DEFENSE
        defaultPokemonShouldBeFound("defense.in=" + DEFAULT_DEFENSE + "," + UPDATED_DEFENSE);

        // Get all the pokemonList where defense equals to UPDATED_DEFENSE
        defaultPokemonShouldNotBeFound("defense.in=" + UPDATED_DEFENSE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByDefenseIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where defense is not null
        defaultPokemonShouldBeFound("defense.specified=true");

        // Get all the pokemonList where defense is null
        defaultPokemonShouldNotBeFound("defense.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByDefenseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where defense greater than or equals to DEFAULT_DEFENSE
        defaultPokemonShouldBeFound("defense.greaterOrEqualThan=" + DEFAULT_DEFENSE);

        // Get all the pokemonList where defense greater than or equals to UPDATED_DEFENSE
        defaultPokemonShouldNotBeFound("defense.greaterOrEqualThan=" + UPDATED_DEFENSE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByDefenseIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where defense less than or equals to DEFAULT_DEFENSE
        defaultPokemonShouldNotBeFound("defense.lessThan=" + DEFAULT_DEFENSE);

        // Get all the pokemonList where defense less than or equals to UPDATED_DEFENSE
        defaultPokemonShouldBeFound("defense.lessThan=" + UPDATED_DEFENSE);
    }


    @Test
    @Transactional
    public void getAllPokemonsByExperienceGrowthIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where experienceGrowth equals to DEFAULT_EXPERIENCE_GROWTH
        defaultPokemonShouldBeFound("experienceGrowth.equals=" + DEFAULT_EXPERIENCE_GROWTH);

        // Get all the pokemonList where experienceGrowth equals to UPDATED_EXPERIENCE_GROWTH
        defaultPokemonShouldNotBeFound("experienceGrowth.equals=" + UPDATED_EXPERIENCE_GROWTH);
    }

    @Test
    @Transactional
    public void getAllPokemonsByExperienceGrowthIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where experienceGrowth in DEFAULT_EXPERIENCE_GROWTH or UPDATED_EXPERIENCE_GROWTH
        defaultPokemonShouldBeFound("experienceGrowth.in=" + DEFAULT_EXPERIENCE_GROWTH + "," + UPDATED_EXPERIENCE_GROWTH);

        // Get all the pokemonList where experienceGrowth equals to UPDATED_EXPERIENCE_GROWTH
        defaultPokemonShouldNotBeFound("experienceGrowth.in=" + UPDATED_EXPERIENCE_GROWTH);
    }

    @Test
    @Transactional
    public void getAllPokemonsByExperienceGrowthIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where experienceGrowth is not null
        defaultPokemonShouldBeFound("experienceGrowth.specified=true");

        // Get all the pokemonList where experienceGrowth is null
        defaultPokemonShouldNotBeFound("experienceGrowth.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByExperienceGrowthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where experienceGrowth greater than or equals to DEFAULT_EXPERIENCE_GROWTH
        defaultPokemonShouldBeFound("experienceGrowth.greaterOrEqualThan=" + DEFAULT_EXPERIENCE_GROWTH);

        // Get all the pokemonList where experienceGrowth greater than or equals to UPDATED_EXPERIENCE_GROWTH
        defaultPokemonShouldNotBeFound("experienceGrowth.greaterOrEqualThan=" + UPDATED_EXPERIENCE_GROWTH);
    }

    @Test
    @Transactional
    public void getAllPokemonsByExperienceGrowthIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where experienceGrowth less than or equals to DEFAULT_EXPERIENCE_GROWTH
        defaultPokemonShouldNotBeFound("experienceGrowth.lessThan=" + DEFAULT_EXPERIENCE_GROWTH);

        // Get all the pokemonList where experienceGrowth less than or equals to UPDATED_EXPERIENCE_GROWTH
        defaultPokemonShouldBeFound("experienceGrowth.lessThan=" + UPDATED_EXPERIENCE_GROWTH);
    }


    @Test
    @Transactional
    public void getAllPokemonsByHeightMIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where heightM equals to DEFAULT_HEIGHT_M
        defaultPokemonShouldBeFound("heightM.equals=" + DEFAULT_HEIGHT_M);

        // Get all the pokemonList where heightM equals to UPDATED_HEIGHT_M
        defaultPokemonShouldNotBeFound("heightM.equals=" + UPDATED_HEIGHT_M);
    }

    @Test
    @Transactional
    public void getAllPokemonsByHeightMIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where heightM in DEFAULT_HEIGHT_M or UPDATED_HEIGHT_M
        defaultPokemonShouldBeFound("heightM.in=" + DEFAULT_HEIGHT_M + "," + UPDATED_HEIGHT_M);

        // Get all the pokemonList where heightM equals to UPDATED_HEIGHT_M
        defaultPokemonShouldNotBeFound("heightM.in=" + UPDATED_HEIGHT_M);
    }

    @Test
    @Transactional
    public void getAllPokemonsByHeightMIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where heightM is not null
        defaultPokemonShouldBeFound("heightM.specified=true");

        // Get all the pokemonList where heightM is null
        defaultPokemonShouldNotBeFound("heightM.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByHpIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where hp equals to DEFAULT_HP
        defaultPokemonShouldBeFound("hp.equals=" + DEFAULT_HP);

        // Get all the pokemonList where hp equals to UPDATED_HP
        defaultPokemonShouldNotBeFound("hp.equals=" + UPDATED_HP);
    }

    @Test
    @Transactional
    public void getAllPokemonsByHpIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where hp in DEFAULT_HP or UPDATED_HP
        defaultPokemonShouldBeFound("hp.in=" + DEFAULT_HP + "," + UPDATED_HP);

        // Get all the pokemonList where hp equals to UPDATED_HP
        defaultPokemonShouldNotBeFound("hp.in=" + UPDATED_HP);
    }

    @Test
    @Transactional
    public void getAllPokemonsByHpIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where hp is not null
        defaultPokemonShouldBeFound("hp.specified=true");

        // Get all the pokemonList where hp is null
        defaultPokemonShouldNotBeFound("hp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByHpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where hp greater than or equals to DEFAULT_HP
        defaultPokemonShouldBeFound("hp.greaterOrEqualThan=" + DEFAULT_HP);

        // Get all the pokemonList where hp greater than or equals to UPDATED_HP
        defaultPokemonShouldNotBeFound("hp.greaterOrEqualThan=" + UPDATED_HP);
    }

    @Test
    @Transactional
    public void getAllPokemonsByHpIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where hp less than or equals to DEFAULT_HP
        defaultPokemonShouldNotBeFound("hp.lessThan=" + DEFAULT_HP);

        // Get all the pokemonList where hp less than or equals to UPDATED_HP
        defaultPokemonShouldBeFound("hp.lessThan=" + UPDATED_HP);
    }


    @Test
    @Transactional
    public void getAllPokemonsByJapaneseNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where japaneseName equals to DEFAULT_JAPANESE_NAME
        defaultPokemonShouldBeFound("japaneseName.equals=" + DEFAULT_JAPANESE_NAME);

        // Get all the pokemonList where japaneseName equals to UPDATED_JAPANESE_NAME
        defaultPokemonShouldNotBeFound("japaneseName.equals=" + UPDATED_JAPANESE_NAME);
    }

    @Test
    @Transactional
    public void getAllPokemonsByJapaneseNameIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where japaneseName in DEFAULT_JAPANESE_NAME or UPDATED_JAPANESE_NAME
        defaultPokemonShouldBeFound("japaneseName.in=" + DEFAULT_JAPANESE_NAME + "," + UPDATED_JAPANESE_NAME);

        // Get all the pokemonList where japaneseName equals to UPDATED_JAPANESE_NAME
        defaultPokemonShouldNotBeFound("japaneseName.in=" + UPDATED_JAPANESE_NAME);
    }

    @Test
    @Transactional
    public void getAllPokemonsByJapaneseNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where japaneseName is not null
        defaultPokemonShouldBeFound("japaneseName.specified=true");

        // Get all the pokemonList where japaneseName is null
        defaultPokemonShouldNotBeFound("japaneseName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where name equals to DEFAULT_NAME
        defaultPokemonShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the pokemonList where name equals to UPDATED_NAME
        defaultPokemonShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPokemonsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPokemonShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the pokemonList where name equals to UPDATED_NAME
        defaultPokemonShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPokemonsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where name is not null
        defaultPokemonShouldBeFound("name.specified=true");

        // Get all the pokemonList where name is null
        defaultPokemonShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByPercentageMaleIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where percentageMale equals to DEFAULT_PERCENTAGE_MALE
        defaultPokemonShouldBeFound("percentageMale.equals=" + DEFAULT_PERCENTAGE_MALE);

        // Get all the pokemonList where percentageMale equals to UPDATED_PERCENTAGE_MALE
        defaultPokemonShouldNotBeFound("percentageMale.equals=" + UPDATED_PERCENTAGE_MALE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByPercentageMaleIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where percentageMale in DEFAULT_PERCENTAGE_MALE or UPDATED_PERCENTAGE_MALE
        defaultPokemonShouldBeFound("percentageMale.in=" + DEFAULT_PERCENTAGE_MALE + "," + UPDATED_PERCENTAGE_MALE);

        // Get all the pokemonList where percentageMale equals to UPDATED_PERCENTAGE_MALE
        defaultPokemonShouldNotBeFound("percentageMale.in=" + UPDATED_PERCENTAGE_MALE);
    }

    @Test
    @Transactional
    public void getAllPokemonsByPercentageMaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where percentageMale is not null
        defaultPokemonShouldBeFound("percentageMale.specified=true");

        // Get all the pokemonList where percentageMale is null
        defaultPokemonShouldNotBeFound("percentageMale.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByPokedexNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where pokedexNumber equals to DEFAULT_POKEDEX_NUMBER
        defaultPokemonShouldBeFound("pokedexNumber.equals=" + DEFAULT_POKEDEX_NUMBER);

        // Get all the pokemonList where pokedexNumber equals to UPDATED_POKEDEX_NUMBER
        defaultPokemonShouldNotBeFound("pokedexNumber.equals=" + UPDATED_POKEDEX_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPokemonsByPokedexNumberIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where pokedexNumber in DEFAULT_POKEDEX_NUMBER or UPDATED_POKEDEX_NUMBER
        defaultPokemonShouldBeFound("pokedexNumber.in=" + DEFAULT_POKEDEX_NUMBER + "," + UPDATED_POKEDEX_NUMBER);

        // Get all the pokemonList where pokedexNumber equals to UPDATED_POKEDEX_NUMBER
        defaultPokemonShouldNotBeFound("pokedexNumber.in=" + UPDATED_POKEDEX_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPokemonsByPokedexNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where pokedexNumber is not null
        defaultPokemonShouldBeFound("pokedexNumber.specified=true");

        // Get all the pokemonList where pokedexNumber is null
        defaultPokemonShouldNotBeFound("pokedexNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByPokedexNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where pokedexNumber greater than or equals to DEFAULT_POKEDEX_NUMBER
        defaultPokemonShouldBeFound("pokedexNumber.greaterOrEqualThan=" + DEFAULT_POKEDEX_NUMBER);

        // Get all the pokemonList where pokedexNumber greater than or equals to UPDATED_POKEDEX_NUMBER
        defaultPokemonShouldNotBeFound("pokedexNumber.greaterOrEqualThan=" + UPDATED_POKEDEX_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPokemonsByPokedexNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where pokedexNumber less than or equals to DEFAULT_POKEDEX_NUMBER
        defaultPokemonShouldNotBeFound("pokedexNumber.lessThan=" + DEFAULT_POKEDEX_NUMBER);

        // Get all the pokemonList where pokedexNumber less than or equals to UPDATED_POKEDEX_NUMBER
        defaultPokemonShouldBeFound("pokedexNumber.lessThan=" + UPDATED_POKEDEX_NUMBER);
    }


    @Test
    @Transactional
    public void getAllPokemonsBySpAttackIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spAttack equals to DEFAULT_SP_ATTACK
        defaultPokemonShouldBeFound("spAttack.equals=" + DEFAULT_SP_ATTACK);

        // Get all the pokemonList where spAttack equals to UPDATED_SP_ATTACK
        defaultPokemonShouldNotBeFound("spAttack.equals=" + UPDATED_SP_ATTACK);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpAttackIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spAttack in DEFAULT_SP_ATTACK or UPDATED_SP_ATTACK
        defaultPokemonShouldBeFound("spAttack.in=" + DEFAULT_SP_ATTACK + "," + UPDATED_SP_ATTACK);

        // Get all the pokemonList where spAttack equals to UPDATED_SP_ATTACK
        defaultPokemonShouldNotBeFound("spAttack.in=" + UPDATED_SP_ATTACK);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpAttackIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spAttack is not null
        defaultPokemonShouldBeFound("spAttack.specified=true");

        // Get all the pokemonList where spAttack is null
        defaultPokemonShouldNotBeFound("spAttack.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpAttackIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spAttack greater than or equals to DEFAULT_SP_ATTACK
        defaultPokemonShouldBeFound("spAttack.greaterOrEqualThan=" + DEFAULT_SP_ATTACK);

        // Get all the pokemonList where spAttack greater than or equals to UPDATED_SP_ATTACK
        defaultPokemonShouldNotBeFound("spAttack.greaterOrEqualThan=" + UPDATED_SP_ATTACK);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpAttackIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spAttack less than or equals to DEFAULT_SP_ATTACK
        defaultPokemonShouldNotBeFound("spAttack.lessThan=" + DEFAULT_SP_ATTACK);

        // Get all the pokemonList where spAttack less than or equals to UPDATED_SP_ATTACK
        defaultPokemonShouldBeFound("spAttack.lessThan=" + UPDATED_SP_ATTACK);
    }


    @Test
    @Transactional
    public void getAllPokemonsBySpDefenseIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spDefense equals to DEFAULT_SP_DEFENSE
        defaultPokemonShouldBeFound("spDefense.equals=" + DEFAULT_SP_DEFENSE);

        // Get all the pokemonList where spDefense equals to UPDATED_SP_DEFENSE
        defaultPokemonShouldNotBeFound("spDefense.equals=" + UPDATED_SP_DEFENSE);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpDefenseIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spDefense in DEFAULT_SP_DEFENSE or UPDATED_SP_DEFENSE
        defaultPokemonShouldBeFound("spDefense.in=" + DEFAULT_SP_DEFENSE + "," + UPDATED_SP_DEFENSE);

        // Get all the pokemonList where spDefense equals to UPDATED_SP_DEFENSE
        defaultPokemonShouldNotBeFound("spDefense.in=" + UPDATED_SP_DEFENSE);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpDefenseIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spDefense is not null
        defaultPokemonShouldBeFound("spDefense.specified=true");

        // Get all the pokemonList where spDefense is null
        defaultPokemonShouldNotBeFound("spDefense.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpDefenseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spDefense greater than or equals to DEFAULT_SP_DEFENSE
        defaultPokemonShouldBeFound("spDefense.greaterOrEqualThan=" + DEFAULT_SP_DEFENSE);

        // Get all the pokemonList where spDefense greater than or equals to UPDATED_SP_DEFENSE
        defaultPokemonShouldNotBeFound("spDefense.greaterOrEqualThan=" + UPDATED_SP_DEFENSE);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpDefenseIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where spDefense less than or equals to DEFAULT_SP_DEFENSE
        defaultPokemonShouldNotBeFound("spDefense.lessThan=" + DEFAULT_SP_DEFENSE);

        // Get all the pokemonList where spDefense less than or equals to UPDATED_SP_DEFENSE
        defaultPokemonShouldBeFound("spDefense.lessThan=" + UPDATED_SP_DEFENSE);
    }


    @Test
    @Transactional
    public void getAllPokemonsBySpeedIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where speed equals to DEFAULT_SPEED
        defaultPokemonShouldBeFound("speed.equals=" + DEFAULT_SPEED);

        // Get all the pokemonList where speed equals to UPDATED_SPEED
        defaultPokemonShouldNotBeFound("speed.equals=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpeedIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where speed in DEFAULT_SPEED or UPDATED_SPEED
        defaultPokemonShouldBeFound("speed.in=" + DEFAULT_SPEED + "," + UPDATED_SPEED);

        // Get all the pokemonList where speed equals to UPDATED_SPEED
        defaultPokemonShouldNotBeFound("speed.in=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where speed is not null
        defaultPokemonShouldBeFound("speed.specified=true");

        // Get all the pokemonList where speed is null
        defaultPokemonShouldNotBeFound("speed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpeedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where speed greater than or equals to DEFAULT_SPEED
        defaultPokemonShouldBeFound("speed.greaterOrEqualThan=" + DEFAULT_SPEED);

        // Get all the pokemonList where speed greater than or equals to UPDATED_SPEED
        defaultPokemonShouldNotBeFound("speed.greaterOrEqualThan=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    public void getAllPokemonsBySpeedIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where speed less than or equals to DEFAULT_SPEED
        defaultPokemonShouldNotBeFound("speed.lessThan=" + DEFAULT_SPEED);

        // Get all the pokemonList where speed less than or equals to UPDATED_SPEED
        defaultPokemonShouldBeFound("speed.lessThan=" + UPDATED_SPEED);
    }


    @Test
    @Transactional
    public void getAllPokemonsByType1IsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where type1 equals to DEFAULT_TYPE_1
        defaultPokemonShouldBeFound("type1.equals=" + DEFAULT_TYPE_1);

        // Get all the pokemonList where type1 equals to UPDATED_TYPE_1
        defaultPokemonShouldNotBeFound("type1.equals=" + UPDATED_TYPE_1);
    }

    @Test
    @Transactional
    public void getAllPokemonsByType1IsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where type1 in DEFAULT_TYPE_1 or UPDATED_TYPE_1
        defaultPokemonShouldBeFound("type1.in=" + DEFAULT_TYPE_1 + "," + UPDATED_TYPE_1);

        // Get all the pokemonList where type1 equals to UPDATED_TYPE_1
        defaultPokemonShouldNotBeFound("type1.in=" + UPDATED_TYPE_1);
    }

    @Test
    @Transactional
    public void getAllPokemonsByType1IsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where type1 is not null
        defaultPokemonShouldBeFound("type1.specified=true");

        // Get all the pokemonList where type1 is null
        defaultPokemonShouldNotBeFound("type1.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByType2IsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where type2 equals to DEFAULT_TYPE_2
        defaultPokemonShouldBeFound("type2.equals=" + DEFAULT_TYPE_2);

        // Get all the pokemonList where type2 equals to UPDATED_TYPE_2
        defaultPokemonShouldNotBeFound("type2.equals=" + UPDATED_TYPE_2);
    }

    @Test
    @Transactional
    public void getAllPokemonsByType2IsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where type2 in DEFAULT_TYPE_2 or UPDATED_TYPE_2
        defaultPokemonShouldBeFound("type2.in=" + DEFAULT_TYPE_2 + "," + UPDATED_TYPE_2);

        // Get all the pokemonList where type2 equals to UPDATED_TYPE_2
        defaultPokemonShouldNotBeFound("type2.in=" + UPDATED_TYPE_2);
    }

    @Test
    @Transactional
    public void getAllPokemonsByType2IsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where type2 is not null
        defaultPokemonShouldBeFound("type2.specified=true");

        // Get all the pokemonList where type2 is null
        defaultPokemonShouldNotBeFound("type2.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByWeightKgIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where weightKg equals to DEFAULT_WEIGHT_KG
        defaultPokemonShouldBeFound("weightKg.equals=" + DEFAULT_WEIGHT_KG);

        // Get all the pokemonList where weightKg equals to UPDATED_WEIGHT_KG
        defaultPokemonShouldNotBeFound("weightKg.equals=" + UPDATED_WEIGHT_KG);
    }

    @Test
    @Transactional
    public void getAllPokemonsByWeightKgIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where weightKg in DEFAULT_WEIGHT_KG or UPDATED_WEIGHT_KG
        defaultPokemonShouldBeFound("weightKg.in=" + DEFAULT_WEIGHT_KG + "," + UPDATED_WEIGHT_KG);

        // Get all the pokemonList where weightKg equals to UPDATED_WEIGHT_KG
        defaultPokemonShouldNotBeFound("weightKg.in=" + UPDATED_WEIGHT_KG);
    }

    @Test
    @Transactional
    public void getAllPokemonsByWeightKgIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where weightKg is not null
        defaultPokemonShouldBeFound("weightKg.specified=true");

        // Get all the pokemonList where weightKg is null
        defaultPokemonShouldNotBeFound("weightKg.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByGenerationIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where generation equals to DEFAULT_GENERATION
        defaultPokemonShouldBeFound("generation.equals=" + DEFAULT_GENERATION);

        // Get all the pokemonList where generation equals to UPDATED_GENERATION
        defaultPokemonShouldNotBeFound("generation.equals=" + UPDATED_GENERATION);
    }

    @Test
    @Transactional
    public void getAllPokemonsByGenerationIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where generation in DEFAULT_GENERATION or UPDATED_GENERATION
        defaultPokemonShouldBeFound("generation.in=" + DEFAULT_GENERATION + "," + UPDATED_GENERATION);

        // Get all the pokemonList where generation equals to UPDATED_GENERATION
        defaultPokemonShouldNotBeFound("generation.in=" + UPDATED_GENERATION);
    }

    @Test
    @Transactional
    public void getAllPokemonsByGenerationIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where generation is not null
        defaultPokemonShouldBeFound("generation.specified=true");

        // Get all the pokemonList where generation is null
        defaultPokemonShouldNotBeFound("generation.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByGenerationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where generation greater than or equals to DEFAULT_GENERATION
        defaultPokemonShouldBeFound("generation.greaterOrEqualThan=" + DEFAULT_GENERATION);

        // Get all the pokemonList where generation greater than or equals to UPDATED_GENERATION
        defaultPokemonShouldNotBeFound("generation.greaterOrEqualThan=" + UPDATED_GENERATION);
    }

    @Test
    @Transactional
    public void getAllPokemonsByGenerationIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where generation less than or equals to DEFAULT_GENERATION
        defaultPokemonShouldNotBeFound("generation.lessThan=" + DEFAULT_GENERATION);

        // Get all the pokemonList where generation less than or equals to UPDATED_GENERATION
        defaultPokemonShouldBeFound("generation.lessThan=" + UPDATED_GENERATION);
    }


    @Test
    @Transactional
    public void getAllPokemonsByIsLegendaryIsEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where isLegendary equals to DEFAULT_IS_LEGENDARY
        defaultPokemonShouldBeFound("isLegendary.equals=" + DEFAULT_IS_LEGENDARY);

        // Get all the pokemonList where isLegendary equals to UPDATED_IS_LEGENDARY
        defaultPokemonShouldNotBeFound("isLegendary.equals=" + UPDATED_IS_LEGENDARY);
    }

    @Test
    @Transactional
    public void getAllPokemonsByIsLegendaryIsInShouldWork() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where isLegendary in DEFAULT_IS_LEGENDARY or UPDATED_IS_LEGENDARY
        defaultPokemonShouldBeFound("isLegendary.in=" + DEFAULT_IS_LEGENDARY + "," + UPDATED_IS_LEGENDARY);

        // Get all the pokemonList where isLegendary equals to UPDATED_IS_LEGENDARY
        defaultPokemonShouldNotBeFound("isLegendary.in=" + UPDATED_IS_LEGENDARY);
    }

    @Test
    @Transactional
    public void getAllPokemonsByIsLegendaryIsNullOrNotNull() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where isLegendary is not null
        defaultPokemonShouldBeFound("isLegendary.specified=true");

        // Get all the pokemonList where isLegendary is null
        defaultPokemonShouldNotBeFound("isLegendary.specified=false");
    }

    @Test
    @Transactional
    public void getAllPokemonsByIsLegendaryIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where isLegendary greater than or equals to DEFAULT_IS_LEGENDARY
        defaultPokemonShouldBeFound("isLegendary.greaterOrEqualThan=" + DEFAULT_IS_LEGENDARY);

        // Get all the pokemonList where isLegendary greater than or equals to UPDATED_IS_LEGENDARY
        defaultPokemonShouldNotBeFound("isLegendary.greaterOrEqualThan=" + UPDATED_IS_LEGENDARY);
    }

    @Test
    @Transactional
    public void getAllPokemonsByIsLegendaryIsLessThanSomething() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);

        // Get all the pokemonList where isLegendary less than or equals to DEFAULT_IS_LEGENDARY
        defaultPokemonShouldNotBeFound("isLegendary.lessThan=" + DEFAULT_IS_LEGENDARY);

        // Get all the pokemonList where isLegendary less than or equals to UPDATED_IS_LEGENDARY
        defaultPokemonShouldBeFound("isLegendary.lessThan=" + UPDATED_IS_LEGENDARY);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPokemonShouldBeFound(String filter) throws Exception {
        restPokemonMockMvc.perform(get("/api/pokemons?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pokemon.getId().intValue())))
            .andExpect(jsonPath("$.[*].abilities").value(hasItem(DEFAULT_ABILITIES.toString())))
            .andExpect(jsonPath("$.[*].againstBug").value(hasItem(DEFAULT_AGAINST_BUG.doubleValue())))
            .andExpect(jsonPath("$.[*].againstDark").value(hasItem(DEFAULT_AGAINST_DARK.doubleValue())))
            .andExpect(jsonPath("$.[*].againstDragon").value(hasItem(DEFAULT_AGAINST_DRAGON.doubleValue())))
            .andExpect(jsonPath("$.[*].againstElectric").value(hasItem(DEFAULT_AGAINST_ELECTRIC.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFairy").value(hasItem(DEFAULT_AGAINST_FAIRY.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFight").value(hasItem(DEFAULT_AGAINST_FIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFire").value(hasItem(DEFAULT_AGAINST_FIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].againstFlying").value(hasItem(DEFAULT_AGAINST_FLYING.doubleValue())))
            .andExpect(jsonPath("$.[*].againstGhost").value(hasItem(DEFAULT_AGAINST_GHOST.doubleValue())))
            .andExpect(jsonPath("$.[*].againstGrass").value(hasItem(DEFAULT_AGAINST_GRASS.doubleValue())))
            .andExpect(jsonPath("$.[*].againstGround").value(hasItem(DEFAULT_AGAINST_GROUND.doubleValue())))
            .andExpect(jsonPath("$.[*].againstIce").value(hasItem(DEFAULT_AGAINST_ICE.doubleValue())))
            .andExpect(jsonPath("$.[*].againstNormal").value(hasItem(DEFAULT_AGAINST_NORMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].againstPoison").value(hasItem(DEFAULT_AGAINST_POISON.doubleValue())))
            .andExpect(jsonPath("$.[*].againstPsychic").value(hasItem(DEFAULT_AGAINST_PSYCHIC.doubleValue())))
            .andExpect(jsonPath("$.[*].againstRock").value(hasItem(DEFAULT_AGAINST_ROCK.doubleValue())))
            .andExpect(jsonPath("$.[*].againstSteel").value(hasItem(DEFAULT_AGAINST_STEEL.doubleValue())))
            .andExpect(jsonPath("$.[*].againstWater").value(hasItem(DEFAULT_AGAINST_WATER.doubleValue())))
            .andExpect(jsonPath("$.[*].attack").value(hasItem(DEFAULT_ATTACK)))
            .andExpect(jsonPath("$.[*].baseEggSteps").value(hasItem(DEFAULT_BASE_EGG_STEPS)))
            .andExpect(jsonPath("$.[*].baseHappiness").value(hasItem(DEFAULT_BASE_HAPPINESS)))
            .andExpect(jsonPath("$.[*].baseTotal").value(hasItem(DEFAULT_BASE_TOTAL)))
            .andExpect(jsonPath("$.[*].captureRate").value(hasItem(DEFAULT_CAPTURE_RATE.toString())))
            .andExpect(jsonPath("$.[*].classfication").value(hasItem(DEFAULT_CLASSFICATION.toString())))
            .andExpect(jsonPath("$.[*].defense").value(hasItem(DEFAULT_DEFENSE)))
            .andExpect(jsonPath("$.[*].experienceGrowth").value(hasItem(DEFAULT_EXPERIENCE_GROWTH)))
            .andExpect(jsonPath("$.[*].heightM").value(hasItem(DEFAULT_HEIGHT_M.doubleValue())))
            .andExpect(jsonPath("$.[*].hp").value(hasItem(DEFAULT_HP)))
            .andExpect(jsonPath("$.[*].japaneseName").value(hasItem(DEFAULT_JAPANESE_NAME.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].percentageMale").value(hasItem(DEFAULT_PERCENTAGE_MALE.doubleValue())))
            .andExpect(jsonPath("$.[*].pokedexNumber").value(hasItem(DEFAULT_POKEDEX_NUMBER)))
            .andExpect(jsonPath("$.[*].spAttack").value(hasItem(DEFAULT_SP_ATTACK)))
            .andExpect(jsonPath("$.[*].spDefense").value(hasItem(DEFAULT_SP_DEFENSE)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].type1").value(hasItem(DEFAULT_TYPE_1.toString())))
            .andExpect(jsonPath("$.[*].type2").value(hasItem(DEFAULT_TYPE_2.toString())))
            .andExpect(jsonPath("$.[*].weightKg").value(hasItem(DEFAULT_WEIGHT_KG.doubleValue())))
            .andExpect(jsonPath("$.[*].generation").value(hasItem(DEFAULT_GENERATION)))
            .andExpect(jsonPath("$.[*].isLegendary").value(hasItem(DEFAULT_IS_LEGENDARY)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPokemonShouldNotBeFound(String filter) throws Exception {
        restPokemonMockMvc.perform(get("/api/pokemons?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingPokemon() throws Exception {
        // Get the pokemon
        restPokemonMockMvc.perform(get("/api/pokemons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePokemon() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);
        int databaseSizeBeforeUpdate = pokemonRepository.findAll().size();

        // Update the pokemon
        Pokemon updatedPokemon = pokemonRepository.findOne(pokemon.getId());
        // Disconnect from session so that the updates on updatedPokemon are not directly saved in db
        em.detach(updatedPokemon);
        updatedPokemon
            .abilities(UPDATED_ABILITIES)
            .againstBug(UPDATED_AGAINST_BUG)
            .againstDark(UPDATED_AGAINST_DARK)
            .againstDragon(UPDATED_AGAINST_DRAGON)
            .againstElectric(UPDATED_AGAINST_ELECTRIC)
            .againstFairy(UPDATED_AGAINST_FAIRY)
            .againstFight(UPDATED_AGAINST_FIGHT)
            .againstFire(UPDATED_AGAINST_FIRE)
            .againstFlying(UPDATED_AGAINST_FLYING)
            .againstGhost(UPDATED_AGAINST_GHOST)
            .againstGrass(UPDATED_AGAINST_GRASS)
            .againstGround(UPDATED_AGAINST_GROUND)
            .againstIce(UPDATED_AGAINST_ICE)
            .againstNormal(UPDATED_AGAINST_NORMAL)
            .againstPoison(UPDATED_AGAINST_POISON)
            .againstPsychic(UPDATED_AGAINST_PSYCHIC)
            .againstRock(UPDATED_AGAINST_ROCK)
            .againstSteel(UPDATED_AGAINST_STEEL)
            .againstWater(UPDATED_AGAINST_WATER)
            .attack(UPDATED_ATTACK)
            .baseEggSteps(UPDATED_BASE_EGG_STEPS)
            .baseHappiness(UPDATED_BASE_HAPPINESS)
            .baseTotal(UPDATED_BASE_TOTAL)
            .captureRate(UPDATED_CAPTURE_RATE)
            .classfication(UPDATED_CLASSFICATION)
            .defense(UPDATED_DEFENSE)
            .experienceGrowth(UPDATED_EXPERIENCE_GROWTH)
            .heightM(UPDATED_HEIGHT_M)
            .hp(UPDATED_HP)
            .japaneseName(UPDATED_JAPANESE_NAME)
            .name(UPDATED_NAME)
            .percentageMale(UPDATED_PERCENTAGE_MALE)
            .pokedexNumber(UPDATED_POKEDEX_NUMBER)
            .spAttack(UPDATED_SP_ATTACK)
            .spDefense(UPDATED_SP_DEFENSE)
            .speed(UPDATED_SPEED)
            .type1(UPDATED_TYPE_1)
            .type2(UPDATED_TYPE_2)
            .weightKg(UPDATED_WEIGHT_KG)
            .generation(UPDATED_GENERATION)
            .isLegendary(UPDATED_IS_LEGENDARY);
        PokemonDTO pokemonDTO = pokemonMapper.toDto(updatedPokemon);

        restPokemonMockMvc.perform(put("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isOk());

        // Validate the Pokemon in the database
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeUpdate);
        Pokemon testPokemon = pokemonList.get(pokemonList.size() - 1);
        assertThat(testPokemon.getAbilities()).isEqualTo(UPDATED_ABILITIES);
        assertThat(testPokemon.getAgainstBug()).isEqualTo(UPDATED_AGAINST_BUG);
        assertThat(testPokemon.getAgainstDark()).isEqualTo(UPDATED_AGAINST_DARK);
        assertThat(testPokemon.getAgainstDragon()).isEqualTo(UPDATED_AGAINST_DRAGON);
        assertThat(testPokemon.getAgainstElectric()).isEqualTo(UPDATED_AGAINST_ELECTRIC);
        assertThat(testPokemon.getAgainstFairy()).isEqualTo(UPDATED_AGAINST_FAIRY);
        assertThat(testPokemon.getAgainstFight()).isEqualTo(UPDATED_AGAINST_FIGHT);
        assertThat(testPokemon.getAgainstFire()).isEqualTo(UPDATED_AGAINST_FIRE);
        assertThat(testPokemon.getAgainstFlying()).isEqualTo(UPDATED_AGAINST_FLYING);
        assertThat(testPokemon.getAgainstGhost()).isEqualTo(UPDATED_AGAINST_GHOST);
        assertThat(testPokemon.getAgainstGrass()).isEqualTo(UPDATED_AGAINST_GRASS);
        assertThat(testPokemon.getAgainstGround()).isEqualTo(UPDATED_AGAINST_GROUND);
        assertThat(testPokemon.getAgainstIce()).isEqualTo(UPDATED_AGAINST_ICE);
        assertThat(testPokemon.getAgainstNormal()).isEqualTo(UPDATED_AGAINST_NORMAL);
        assertThat(testPokemon.getAgainstPoison()).isEqualTo(UPDATED_AGAINST_POISON);
        assertThat(testPokemon.getAgainstPsychic()).isEqualTo(UPDATED_AGAINST_PSYCHIC);
        assertThat(testPokemon.getAgainstRock()).isEqualTo(UPDATED_AGAINST_ROCK);
        assertThat(testPokemon.getAgainstSteel()).isEqualTo(UPDATED_AGAINST_STEEL);
        assertThat(testPokemon.getAgainstWater()).isEqualTo(UPDATED_AGAINST_WATER);
        assertThat(testPokemon.getAttack()).isEqualTo(UPDATED_ATTACK);
        assertThat(testPokemon.getBaseEggSteps()).isEqualTo(UPDATED_BASE_EGG_STEPS);
        assertThat(testPokemon.getBaseHappiness()).isEqualTo(UPDATED_BASE_HAPPINESS);
        assertThat(testPokemon.getBaseTotal()).isEqualTo(UPDATED_BASE_TOTAL);
        assertThat(testPokemon.getCaptureRate()).isEqualTo(UPDATED_CAPTURE_RATE);
        assertThat(testPokemon.getClassfication()).isEqualTo(UPDATED_CLASSFICATION);
        assertThat(testPokemon.getDefense()).isEqualTo(UPDATED_DEFENSE);
        assertThat(testPokemon.getExperienceGrowth()).isEqualTo(UPDATED_EXPERIENCE_GROWTH);
        assertThat(testPokemon.getHeightM()).isEqualTo(UPDATED_HEIGHT_M);
        assertThat(testPokemon.getHp()).isEqualTo(UPDATED_HP);
        assertThat(testPokemon.getJapaneseName()).isEqualTo(UPDATED_JAPANESE_NAME);
        assertThat(testPokemon.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPokemon.getPercentageMale()).isEqualTo(UPDATED_PERCENTAGE_MALE);
        assertThat(testPokemon.getPokedexNumber()).isEqualTo(UPDATED_POKEDEX_NUMBER);
        assertThat(testPokemon.getSpAttack()).isEqualTo(UPDATED_SP_ATTACK);
        assertThat(testPokemon.getSpDefense()).isEqualTo(UPDATED_SP_DEFENSE);
        assertThat(testPokemon.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testPokemon.getType1()).isEqualTo(UPDATED_TYPE_1);
        assertThat(testPokemon.getType2()).isEqualTo(UPDATED_TYPE_2);
        assertThat(testPokemon.getWeightKg()).isEqualTo(UPDATED_WEIGHT_KG);
        assertThat(testPokemon.getGeneration()).isEqualTo(UPDATED_GENERATION);
        assertThat(testPokemon.getIsLegendary()).isEqualTo(UPDATED_IS_LEGENDARY);
    }

    @Test
    @Transactional
    public void updateNonExistingPokemon() throws Exception {
        int databaseSizeBeforeUpdate = pokemonRepository.findAll().size();

        // Create the Pokemon
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPokemonMockMvc.perform(put("/api/pokemons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pokemonDTO)))
            .andExpect(status().isCreated());

        // Validate the Pokemon in the database
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePokemon() throws Exception {
        // Initialize the database
        pokemonRepository.saveAndFlush(pokemon);
        int databaseSizeBeforeDelete = pokemonRepository.findAll().size();

        // Get the pokemon
        restPokemonMockMvc.perform(delete("/api/pokemons/{id}", pokemon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertThat(pokemonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pokemon.class);
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setId(1L);
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setId(pokemon1.getId());
        assertThat(pokemon1).isEqualTo(pokemon2);
        pokemon2.setId(2L);
        assertThat(pokemon1).isNotEqualTo(pokemon2);
        pokemon1.setId(null);
        assertThat(pokemon1).isNotEqualTo(pokemon2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PokemonDTO.class);
        PokemonDTO pokemonDTO1 = new PokemonDTO();
        pokemonDTO1.setId(1L);
        PokemonDTO pokemonDTO2 = new PokemonDTO();
        assertThat(pokemonDTO1).isNotEqualTo(pokemonDTO2);
        pokemonDTO2.setId(pokemonDTO1.getId());
        assertThat(pokemonDTO1).isEqualTo(pokemonDTO2);
        pokemonDTO2.setId(2L);
        assertThat(pokemonDTO1).isNotEqualTo(pokemonDTO2);
        pokemonDTO1.setId(null);
        assertThat(pokemonDTO1).isNotEqualTo(pokemonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pokemonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pokemonMapper.fromId(null)).isNull();
    }
}
