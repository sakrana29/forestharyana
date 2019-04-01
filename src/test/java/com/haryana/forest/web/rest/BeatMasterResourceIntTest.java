package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.BeatMaster;
import com.haryana.forest.repository.BeatMasterRepository;
import com.haryana.forest.service.BeatMasterService;
import com.haryana.forest.service.dto.BeatMasterDTO;
import com.haryana.forest.service.mapper.BeatMasterMapper;
import com.haryana.forest.web.rest.errors.ExceptionTranslator;

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
import org.springframework.validation.Validator;

import java.util.List;

import java.util.UUID;

import static com.haryana.forest.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BeatMasterResource REST controller.
 *
 * @see BeatMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class BeatMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_BEAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BEAT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_ID = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_DIVISION_ID = UUID.randomUUID();
    private static final UUID UPDATED_DIVISION_ID = UUID.randomUUID();

    private static final String DEFAULT_CIRCLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CIRCLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CIRCLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CIRCLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STATE_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private BeatMasterRepository beatMasterRepository;

    @Autowired
    private BeatMasterMapper beatMasterMapper;

    @Autowired
    private BeatMasterService beatMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBeatMasterMockMvc;

    private BeatMaster beatMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BeatMasterResource beatMasterResource = new BeatMasterResource(beatMasterService);
        this.restBeatMasterMockMvc = MockMvcBuilders.standaloneSetup(beatMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BeatMaster createEntity() {
        BeatMaster beatMaster = new BeatMaster()
            .beatName(DEFAULT_BEAT_NAME)
            .blockName(DEFAULT_BLOCK_NAME)
            .blockId(DEFAULT_BLOCK_ID)
            .rangeName(DEFAULT_RANGE_NAME)
            .rangeId(DEFAULT_RANGE_ID)
            .divisionName(DEFAULT_DIVISION_NAME)
            .divisionId(DEFAULT_DIVISION_ID)
            .circleName(DEFAULT_CIRCLE_NAME)
            .circleId(DEFAULT_CIRCLE_ID)
            .stateName(DEFAULT_STATE_NAME)
            .stateId(DEFAULT_STATE_ID)
            .isActive(DEFAULT_IS_ACTIVE);
        return beatMaster;
    }

    @Before
    public void initTest() {
        beatMasterRepository.deleteAll();
        beatMaster = createEntity();
    }

    @Test
    public void createBeatMaster() throws Exception {
        int databaseSizeBeforeCreate = beatMasterRepository.findAll().size();

        // Create the BeatMaster
        BeatMasterDTO beatMasterDTO = beatMasterMapper.toDto(beatMaster);
        restBeatMasterMockMvc.perform(post("/api/beat-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beatMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the BeatMaster in the database
        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeCreate + 1);
        BeatMaster testBeatMaster = beatMasterList.get(beatMasterList.size() - 1);
        assertThat(testBeatMaster.getBeatName()).isEqualTo(DEFAULT_BEAT_NAME);
        assertThat(testBeatMaster.getBlockName()).isEqualTo(DEFAULT_BLOCK_NAME);
        assertThat(testBeatMaster.getBlockId()).isEqualTo(DEFAULT_BLOCK_ID);
        assertThat(testBeatMaster.getRangeName()).isEqualTo(DEFAULT_RANGE_NAME);
        assertThat(testBeatMaster.getRangeId()).isEqualTo(DEFAULT_RANGE_ID);
        assertThat(testBeatMaster.getDivisionName()).isEqualTo(DEFAULT_DIVISION_NAME);
        assertThat(testBeatMaster.getDivisionId()).isEqualTo(DEFAULT_DIVISION_ID);
        assertThat(testBeatMaster.getCircleName()).isEqualTo(DEFAULT_CIRCLE_NAME);
        assertThat(testBeatMaster.getCircleId()).isEqualTo(DEFAULT_CIRCLE_ID);
        assertThat(testBeatMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testBeatMaster.getStateId()).isEqualTo(DEFAULT_STATE_ID);
        assertThat(testBeatMaster.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createBeatMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = beatMasterRepository.findAll().size();

        // Create the BeatMaster with an existing ID
        beatMaster.setId(UUID.randomUUID());
        BeatMasterDTO beatMasterDTO = beatMasterMapper.toDto(beatMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBeatMasterMockMvc.perform(post("/api/beat-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beatMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BeatMaster in the database
        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkBeatNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = beatMasterRepository.findAll().size();
        // set the field null
        beatMaster.setBeatName(null);

        // Create the BeatMaster, which fails.
        BeatMasterDTO beatMasterDTO = beatMasterMapper.toDto(beatMaster);

        restBeatMasterMockMvc.perform(post("/api/beat-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beatMasterDTO)))
            .andExpect(status().isBadRequest());

        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBlockNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = beatMasterRepository.findAll().size();
        // set the field null
        beatMaster.setBlockName(null);

        // Create the BeatMaster, which fails.
        BeatMasterDTO beatMasterDTO = beatMasterMapper.toDto(beatMaster);

        restBeatMasterMockMvc.perform(post("/api/beat-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beatMasterDTO)))
            .andExpect(status().isBadRequest());

        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBlockIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = beatMasterRepository.findAll().size();
        // set the field null
        beatMaster.setBlockId(null);

        // Create the BeatMaster, which fails.
        BeatMasterDTO beatMasterDTO = beatMasterMapper.toDto(beatMaster);

        restBeatMasterMockMvc.perform(post("/api/beat-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beatMasterDTO)))
            .andExpect(status().isBadRequest());

        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBeatMasters() throws Exception {
        // Initialize the database
        beatMaster.setId(UUID.randomUUID());
        beatMasterRepository.save(beatMaster);

        // Get all the beatMasterList
        restBeatMasterMockMvc.perform(get("/api/beat-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beatMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].beatName").value(hasItem(DEFAULT_BEAT_NAME.toString())))
            .andExpect(jsonPath("$.[*].blockName").value(hasItem(DEFAULT_BLOCK_NAME.toString())))
            .andExpect(jsonPath("$.[*].blockId").value(hasItem(DEFAULT_BLOCK_ID.toString())))
            .andExpect(jsonPath("$.[*].rangeName").value(hasItem(DEFAULT_RANGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].rangeId").value(hasItem(DEFAULT_RANGE_ID.toString())))
            .andExpect(jsonPath("$.[*].divisionName").value(hasItem(DEFAULT_DIVISION_NAME.toString())))
            .andExpect(jsonPath("$.[*].divisionId").value(hasItem(DEFAULT_DIVISION_ID.toString())))
            .andExpect(jsonPath("$.[*].circleName").value(hasItem(DEFAULT_CIRCLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].circleId").value(hasItem(DEFAULT_CIRCLE_ID.toString())))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getBeatMaster() throws Exception {
        // Initialize the database
        beatMaster.setId(UUID.randomUUID());
        beatMasterRepository.save(beatMaster);

        // Get the beatMaster
        restBeatMasterMockMvc.perform(get("/api/beat-masters/{id}", beatMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(beatMaster.getId().toString()))
            .andExpect(jsonPath("$.beatName").value(DEFAULT_BEAT_NAME.toString()))
            .andExpect(jsonPath("$.blockName").value(DEFAULT_BLOCK_NAME.toString()))
            .andExpect(jsonPath("$.blockId").value(DEFAULT_BLOCK_ID.toString()))
            .andExpect(jsonPath("$.rangeName").value(DEFAULT_RANGE_NAME.toString()))
            .andExpect(jsonPath("$.rangeId").value(DEFAULT_RANGE_ID.toString()))
            .andExpect(jsonPath("$.divisionName").value(DEFAULT_DIVISION_NAME.toString()))
            .andExpect(jsonPath("$.divisionId").value(DEFAULT_DIVISION_ID.toString()))
            .andExpect(jsonPath("$.circleName").value(DEFAULT_CIRCLE_NAME.toString()))
            .andExpect(jsonPath("$.circleId").value(DEFAULT_CIRCLE_ID.toString()))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME.toString()))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    public void getNonExistingBeatMaster() throws Exception {
        // Get the beatMaster
        restBeatMasterMockMvc.perform(get("/api/beat-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBeatMaster() throws Exception {
        // Initialize the database
        beatMaster.setId(UUID.randomUUID());
        beatMasterRepository.save(beatMaster);

        int databaseSizeBeforeUpdate = beatMasterRepository.findAll().size();

        // Update the beatMaster
        BeatMaster updatedBeatMaster = beatMasterRepository.findById(beatMaster.getId()).get();
        updatedBeatMaster
            .beatName(UPDATED_BEAT_NAME)
            .blockName(UPDATED_BLOCK_NAME)
            .blockId(UPDATED_BLOCK_ID)
            .rangeName(UPDATED_RANGE_NAME)
            .rangeId(UPDATED_RANGE_ID)
            .divisionName(UPDATED_DIVISION_NAME)
            .divisionId(UPDATED_DIVISION_ID)
            .circleName(UPDATED_CIRCLE_NAME)
            .circleId(UPDATED_CIRCLE_ID)
            .stateName(UPDATED_STATE_NAME)
            .stateId(UPDATED_STATE_ID)
            .isActive(UPDATED_IS_ACTIVE);
        BeatMasterDTO beatMasterDTO = beatMasterMapper.toDto(updatedBeatMaster);

        restBeatMasterMockMvc.perform(put("/api/beat-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beatMasterDTO)))
            .andExpect(status().isOk());

        // Validate the BeatMaster in the database
        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeUpdate);
        BeatMaster testBeatMaster = beatMasterList.get(beatMasterList.size() - 1);
        assertThat(testBeatMaster.getBeatName()).isEqualTo(UPDATED_BEAT_NAME);
        assertThat(testBeatMaster.getBlockName()).isEqualTo(UPDATED_BLOCK_NAME);
        assertThat(testBeatMaster.getBlockId()).isEqualTo(UPDATED_BLOCK_ID);
        assertThat(testBeatMaster.getRangeName()).isEqualTo(UPDATED_RANGE_NAME);
        assertThat(testBeatMaster.getRangeId()).isEqualTo(UPDATED_RANGE_ID);
        assertThat(testBeatMaster.getDivisionName()).isEqualTo(UPDATED_DIVISION_NAME);
        assertThat(testBeatMaster.getDivisionId()).isEqualTo(UPDATED_DIVISION_ID);
        assertThat(testBeatMaster.getCircleName()).isEqualTo(UPDATED_CIRCLE_NAME);
        assertThat(testBeatMaster.getCircleId()).isEqualTo(UPDATED_CIRCLE_ID);
        assertThat(testBeatMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testBeatMaster.getStateId()).isEqualTo(UPDATED_STATE_ID);
        assertThat(testBeatMaster.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingBeatMaster() throws Exception {
        int databaseSizeBeforeUpdate = beatMasterRepository.findAll().size();

        // Create the BeatMaster
        BeatMasterDTO beatMasterDTO = beatMasterMapper.toDto(beatMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeatMasterMockMvc.perform(put("/api/beat-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(beatMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BeatMaster in the database
        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBeatMaster() throws Exception {
        // Initialize the database
        beatMaster.setId(UUID.randomUUID());
        beatMasterRepository.save(beatMaster);

        int databaseSizeBeforeDelete = beatMasterRepository.findAll().size();

        // Get the beatMaster
        restBeatMasterMockMvc.perform(delete("/api/beat-masters/{id}", beatMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BeatMaster> beatMasterList = beatMasterRepository.findAll();
        assertThat(beatMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeatMaster.class);
        BeatMaster beatMaster1 = new BeatMaster();
        beatMaster1.setId(UUID.randomUUID());
        BeatMaster beatMaster2 = new BeatMaster();
        beatMaster2.setId(beatMaster1.getId());
        assertThat(beatMaster1).isEqualTo(beatMaster2);
        beatMaster2.setId(UUID.randomUUID());
        assertThat(beatMaster1).isNotEqualTo(beatMaster2);
        beatMaster1.setId(null);
        assertThat(beatMaster1).isNotEqualTo(beatMaster2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeatMasterDTO.class);
        BeatMasterDTO beatMasterDTO1 = new BeatMasterDTO();
        beatMasterDTO1.setId(UUID.randomUUID());
        BeatMasterDTO beatMasterDTO2 = new BeatMasterDTO();
        assertThat(beatMasterDTO1).isNotEqualTo(beatMasterDTO2);
        beatMasterDTO2.setId(beatMasterDTO1.getId());
        assertThat(beatMasterDTO1).isEqualTo(beatMasterDTO2);
        beatMasterDTO2.setId(UUID.randomUUID());
        assertThat(beatMasterDTO1).isNotEqualTo(beatMasterDTO2);
        beatMasterDTO1.setId(null);
        assertThat(beatMasterDTO1).isNotEqualTo(beatMasterDTO2);
    }
}
