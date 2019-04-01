package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.RangeMaster;
import com.haryana.forest.repository.RangeMasterRepository;
import com.haryana.forest.service.RangeMasterService;
import com.haryana.forest.service.dto.RangeMasterDTO;
import com.haryana.forest.service.mapper.RangeMasterMapper;
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
 * Test class for the RangeMasterResource REST controller.
 *
 * @see RangeMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class RangeMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_RANGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION_NMAE = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_NMAE = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CIRCLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CIRCLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CIRCLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CIRCLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_STATE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STATE_ID = "BBBBBBBBBB";

    @Autowired
    private RangeMasterRepository rangeMasterRepository;

    @Autowired
    private RangeMasterMapper rangeMasterMapper;

    @Autowired
    private RangeMasterService rangeMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restRangeMasterMockMvc;

    private RangeMaster rangeMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RangeMasterResource rangeMasterResource = new RangeMasterResource(rangeMasterService);
        this.restRangeMasterMockMvc = MockMvcBuilders.standaloneSetup(rangeMasterResource)
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
    public static RangeMaster createEntity() {
        RangeMaster rangeMaster = new RangeMaster()
            .rangeName(DEFAULT_RANGE_NAME)
            .divisionNmae(DEFAULT_DIVISION_NMAE)
            .divisionId(DEFAULT_DIVISION_ID)
            .circleName(DEFAULT_CIRCLE_NAME)
            .circleId(DEFAULT_CIRCLE_ID)
            .stateName(DEFAULT_STATE_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .stateId(DEFAULT_STATE_ID);
        return rangeMaster;
    }

    @Before
    public void initTest() {
        rangeMasterRepository.deleteAll();
        rangeMaster = createEntity();
    }

    @Test
    public void createRangeMaster() throws Exception {
        int databaseSizeBeforeCreate = rangeMasterRepository.findAll().size();

        // Create the RangeMaster
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);
        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the RangeMaster in the database
        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        RangeMaster testRangeMaster = rangeMasterList.get(rangeMasterList.size() - 1);
        assertThat(testRangeMaster.getRangeName()).isEqualTo(DEFAULT_RANGE_NAME);
        assertThat(testRangeMaster.getDivisionNmae()).isEqualTo(DEFAULT_DIVISION_NMAE);
        assertThat(testRangeMaster.getDivisionId()).isEqualTo(DEFAULT_DIVISION_ID);
        assertThat(testRangeMaster.getCircleName()).isEqualTo(DEFAULT_CIRCLE_NAME);
        assertThat(testRangeMaster.getCircleId()).isEqualTo(DEFAULT_CIRCLE_ID);
        assertThat(testRangeMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testRangeMaster.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRangeMaster.getStateId()).isEqualTo(DEFAULT_STATE_ID);
    }

    @Test
    public void createRangeMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rangeMasterRepository.findAll().size();

        // Create the RangeMaster with an existing ID
        rangeMaster.setId(UUID.randomUUID());
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RangeMaster in the database
        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkRangeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setRangeName(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDivisionNmaeIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setDivisionNmae(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDivisionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setDivisionId(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCircleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setCircleName(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCircleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setCircleId(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setStateName(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setIsActive(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = rangeMasterRepository.findAll().size();
        // set the field null
        rangeMaster.setStateId(null);

        // Create the RangeMaster, which fails.
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        restRangeMasterMockMvc.perform(post("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllRangeMasters() throws Exception {
        // Initialize the database
        rangeMaster.setId(UUID.randomUUID());
        rangeMasterRepository.save(rangeMaster);

        // Get all the rangeMasterList
        restRangeMasterMockMvc.perform(get("/api/range-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rangeMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].rangeName").value(hasItem(DEFAULT_RANGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].divisionNmae").value(hasItem(DEFAULT_DIVISION_NMAE.toString())))
            .andExpect(jsonPath("$.[*].divisionId").value(hasItem(DEFAULT_DIVISION_ID.toString())))
            .andExpect(jsonPath("$.[*].circleName").value(hasItem(DEFAULT_CIRCLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].circleId").value(hasItem(DEFAULT_CIRCLE_ID.toString())))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID.toString())));
    }
    
    @Test
    public void getRangeMaster() throws Exception {
        // Initialize the database
        rangeMaster.setId(UUID.randomUUID());
        rangeMasterRepository.save(rangeMaster);

        // Get the rangeMaster
        restRangeMasterMockMvc.perform(get("/api/range-masters/{id}", rangeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rangeMaster.getId().toString()))
            .andExpect(jsonPath("$.rangeName").value(DEFAULT_RANGE_NAME.toString()))
            .andExpect(jsonPath("$.divisionNmae").value(DEFAULT_DIVISION_NMAE.toString()))
            .andExpect(jsonPath("$.divisionId").value(DEFAULT_DIVISION_ID.toString()))
            .andExpect(jsonPath("$.circleName").value(DEFAULT_CIRCLE_NAME.toString()))
            .andExpect(jsonPath("$.circleId").value(DEFAULT_CIRCLE_ID.toString()))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID.toString()));
    }

    @Test
    public void getNonExistingRangeMaster() throws Exception {
        // Get the rangeMaster
        restRangeMasterMockMvc.perform(get("/api/range-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRangeMaster() throws Exception {
        // Initialize the database
        rangeMaster.setId(UUID.randomUUID());
        rangeMasterRepository.save(rangeMaster);

        int databaseSizeBeforeUpdate = rangeMasterRepository.findAll().size();

        // Update the rangeMaster
        RangeMaster updatedRangeMaster = rangeMasterRepository.findById(rangeMaster.getId()).get();
        updatedRangeMaster
            .rangeName(UPDATED_RANGE_NAME)
            .divisionNmae(UPDATED_DIVISION_NMAE)
            .divisionId(UPDATED_DIVISION_ID)
            .circleName(UPDATED_CIRCLE_NAME)
            .circleId(UPDATED_CIRCLE_ID)
            .stateName(UPDATED_STATE_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .stateId(UPDATED_STATE_ID);
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(updatedRangeMaster);

        restRangeMasterMockMvc.perform(put("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isOk());

        // Validate the RangeMaster in the database
        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeUpdate);
        RangeMaster testRangeMaster = rangeMasterList.get(rangeMasterList.size() - 1);
        assertThat(testRangeMaster.getRangeName()).isEqualTo(UPDATED_RANGE_NAME);
        assertThat(testRangeMaster.getDivisionNmae()).isEqualTo(UPDATED_DIVISION_NMAE);
        assertThat(testRangeMaster.getDivisionId()).isEqualTo(UPDATED_DIVISION_ID);
        assertThat(testRangeMaster.getCircleName()).isEqualTo(UPDATED_CIRCLE_NAME);
        assertThat(testRangeMaster.getCircleId()).isEqualTo(UPDATED_CIRCLE_ID);
        assertThat(testRangeMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testRangeMaster.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRangeMaster.getStateId()).isEqualTo(UPDATED_STATE_ID);
    }

    @Test
    public void updateNonExistingRangeMaster() throws Exception {
        int databaseSizeBeforeUpdate = rangeMasterRepository.findAll().size();

        // Create the RangeMaster
        RangeMasterDTO rangeMasterDTO = rangeMasterMapper.toDto(rangeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRangeMasterMockMvc.perform(put("/api/range-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RangeMaster in the database
        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRangeMaster() throws Exception {
        // Initialize the database
        rangeMaster.setId(UUID.randomUUID());
        rangeMasterRepository.save(rangeMaster);

        int databaseSizeBeforeDelete = rangeMasterRepository.findAll().size();

        // Get the rangeMaster
        restRangeMasterMockMvc.perform(delete("/api/range-masters/{id}", rangeMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RangeMaster> rangeMasterList = rangeMasterRepository.findAll();
        assertThat(rangeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RangeMaster.class);
        RangeMaster rangeMaster1 = new RangeMaster();
        rangeMaster1.setId(UUID.randomUUID());
        RangeMaster rangeMaster2 = new RangeMaster();
        rangeMaster2.setId(rangeMaster1.getId());
        assertThat(rangeMaster1).isEqualTo(rangeMaster2);
        rangeMaster2.setId(UUID.randomUUID());
        assertThat(rangeMaster1).isNotEqualTo(rangeMaster2);
        rangeMaster1.setId(null);
        assertThat(rangeMaster1).isNotEqualTo(rangeMaster2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RangeMasterDTO.class);
        RangeMasterDTO rangeMasterDTO1 = new RangeMasterDTO();
        rangeMasterDTO1.setId(UUID.randomUUID());
        RangeMasterDTO rangeMasterDTO2 = new RangeMasterDTO();
        assertThat(rangeMasterDTO1).isNotEqualTo(rangeMasterDTO2);
        rangeMasterDTO2.setId(rangeMasterDTO1.getId());
        assertThat(rangeMasterDTO1).isEqualTo(rangeMasterDTO2);
        rangeMasterDTO2.setId(UUID.randomUUID());
        assertThat(rangeMasterDTO1).isNotEqualTo(rangeMasterDTO2);
        rangeMasterDTO1.setId(null);
        assertThat(rangeMasterDTO1).isNotEqualTo(rangeMasterDTO2);
    }
}
