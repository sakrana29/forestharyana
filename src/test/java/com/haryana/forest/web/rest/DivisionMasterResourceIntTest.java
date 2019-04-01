package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.DivisionMaster;
import com.haryana.forest.repository.DivisionMasterRepository;
import com.haryana.forest.service.DivisionMasterService;
import com.haryana.forest.service.dto.DivisionMasterDTO;
import com.haryana.forest.service.mapper.DivisionMasterMapper;
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
 * Test class for the DivisionMasterResource REST controller.
 *
 * @see DivisionMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class DivisionMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_DIVISION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_NAME = "BBBBBBBBBB";

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
    private DivisionMasterRepository divisionMasterRepository;

    @Autowired
    private DivisionMasterMapper divisionMasterMapper;

    @Autowired
    private DivisionMasterService divisionMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDivisionMasterMockMvc;

    private DivisionMaster divisionMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DivisionMasterResource divisionMasterResource = new DivisionMasterResource(divisionMasterService);
        this.restDivisionMasterMockMvc = MockMvcBuilders.standaloneSetup(divisionMasterResource)
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
    public static DivisionMaster createEntity() {
        DivisionMaster divisionMaster = new DivisionMaster()
            .divisionName(DEFAULT_DIVISION_NAME)
            .circleName(DEFAULT_CIRCLE_NAME)
            .circleId(DEFAULT_CIRCLE_ID)
            .stateName(DEFAULT_STATE_NAME)
            .stateId(DEFAULT_STATE_ID)
            .isActive(DEFAULT_IS_ACTIVE);
        return divisionMaster;
    }

    @Before
    public void initTest() {
        divisionMasterRepository.deleteAll();
        divisionMaster = createEntity();
    }

    @Test
    public void createDivisionMaster() throws Exception {
        int databaseSizeBeforeCreate = divisionMasterRepository.findAll().size();

        // Create the DivisionMaster
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);
        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the DivisionMaster in the database
        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeCreate + 1);
        DivisionMaster testDivisionMaster = divisionMasterList.get(divisionMasterList.size() - 1);
        assertThat(testDivisionMaster.getDivisionName()).isEqualTo(DEFAULT_DIVISION_NAME);
        assertThat(testDivisionMaster.getCircleName()).isEqualTo(DEFAULT_CIRCLE_NAME);
        assertThat(testDivisionMaster.getCircleId()).isEqualTo(DEFAULT_CIRCLE_ID);
        assertThat(testDivisionMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testDivisionMaster.getStateId()).isEqualTo(DEFAULT_STATE_ID);
        assertThat(testDivisionMaster.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createDivisionMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = divisionMasterRepository.findAll().size();

        // Create the DivisionMaster with an existing ID
        divisionMaster.setId(UUID.randomUUID());
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DivisionMaster in the database
        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkDivisionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionMasterRepository.findAll().size();
        // set the field null
        divisionMaster.setDivisionName(null);

        // Create the DivisionMaster, which fails.
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCircleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionMasterRepository.findAll().size();
        // set the field null
        divisionMaster.setCircleName(null);

        // Create the DivisionMaster, which fails.
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCircleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionMasterRepository.findAll().size();
        // set the field null
        divisionMaster.setCircleId(null);

        // Create the DivisionMaster, which fails.
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionMasterRepository.findAll().size();
        // set the field null
        divisionMaster.setStateName(null);

        // Create the DivisionMaster, which fails.
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionMasterRepository.findAll().size();
        // set the field null
        divisionMaster.setStateId(null);

        // Create the DivisionMaster, which fails.
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionMasterRepository.findAll().size();
        // set the field null
        divisionMaster.setIsActive(null);

        // Create the DivisionMaster, which fails.
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        restDivisionMasterMockMvc.perform(post("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDivisionMasters() throws Exception {
        // Initialize the database
        divisionMaster.setId(UUID.randomUUID());
        divisionMasterRepository.save(divisionMaster);

        // Get all the divisionMasterList
        restDivisionMasterMockMvc.perform(get("/api/division-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(divisionMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].divisionName").value(hasItem(DEFAULT_DIVISION_NAME.toString())))
            .andExpect(jsonPath("$.[*].circleName").value(hasItem(DEFAULT_CIRCLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].circleId").value(hasItem(DEFAULT_CIRCLE_ID.toString())))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getDivisionMaster() throws Exception {
        // Initialize the database
        divisionMaster.setId(UUID.randomUUID());
        divisionMasterRepository.save(divisionMaster);

        // Get the divisionMaster
        restDivisionMasterMockMvc.perform(get("/api/division-masters/{id}", divisionMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(divisionMaster.getId().toString()))
            .andExpect(jsonPath("$.divisionName").value(DEFAULT_DIVISION_NAME.toString()))
            .andExpect(jsonPath("$.circleName").value(DEFAULT_CIRCLE_NAME.toString()))
            .andExpect(jsonPath("$.circleId").value(DEFAULT_CIRCLE_ID.toString()))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME.toString()))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    public void getNonExistingDivisionMaster() throws Exception {
        // Get the divisionMaster
        restDivisionMasterMockMvc.perform(get("/api/division-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDivisionMaster() throws Exception {
        // Initialize the database
        divisionMaster.setId(UUID.randomUUID());
        divisionMasterRepository.save(divisionMaster);

        int databaseSizeBeforeUpdate = divisionMasterRepository.findAll().size();

        // Update the divisionMaster
        DivisionMaster updatedDivisionMaster = divisionMasterRepository.findById(divisionMaster.getId()).get();
        updatedDivisionMaster
            .divisionName(UPDATED_DIVISION_NAME)
            .circleName(UPDATED_CIRCLE_NAME)
            .circleId(UPDATED_CIRCLE_ID)
            .stateName(UPDATED_STATE_NAME)
            .stateId(UPDATED_STATE_ID)
            .isActive(UPDATED_IS_ACTIVE);
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(updatedDivisionMaster);

        restDivisionMasterMockMvc.perform(put("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isOk());

        // Validate the DivisionMaster in the database
        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeUpdate);
        DivisionMaster testDivisionMaster = divisionMasterList.get(divisionMasterList.size() - 1);
        assertThat(testDivisionMaster.getDivisionName()).isEqualTo(UPDATED_DIVISION_NAME);
        assertThat(testDivisionMaster.getCircleName()).isEqualTo(UPDATED_CIRCLE_NAME);
        assertThat(testDivisionMaster.getCircleId()).isEqualTo(UPDATED_CIRCLE_ID);
        assertThat(testDivisionMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testDivisionMaster.getStateId()).isEqualTo(UPDATED_STATE_ID);
        assertThat(testDivisionMaster.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingDivisionMaster() throws Exception {
        int databaseSizeBeforeUpdate = divisionMasterRepository.findAll().size();

        // Create the DivisionMaster
        DivisionMasterDTO divisionMasterDTO = divisionMasterMapper.toDto(divisionMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDivisionMasterMockMvc.perform(put("/api/division-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(divisionMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DivisionMaster in the database
        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDivisionMaster() throws Exception {
        // Initialize the database
        divisionMaster.setId(UUID.randomUUID());
        divisionMasterRepository.save(divisionMaster);

        int databaseSizeBeforeDelete = divisionMasterRepository.findAll().size();

        // Get the divisionMaster
        restDivisionMasterMockMvc.perform(delete("/api/division-masters/{id}", divisionMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DivisionMaster> divisionMasterList = divisionMasterRepository.findAll();
        assertThat(divisionMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DivisionMaster.class);
        DivisionMaster divisionMaster1 = new DivisionMaster();
        divisionMaster1.setId(UUID.randomUUID());
        DivisionMaster divisionMaster2 = new DivisionMaster();
        divisionMaster2.setId(divisionMaster1.getId());
        assertThat(divisionMaster1).isEqualTo(divisionMaster2);
        divisionMaster2.setId(UUID.randomUUID());
        assertThat(divisionMaster1).isNotEqualTo(divisionMaster2);
        divisionMaster1.setId(null);
        assertThat(divisionMaster1).isNotEqualTo(divisionMaster2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DivisionMasterDTO.class);
        DivisionMasterDTO divisionMasterDTO1 = new DivisionMasterDTO();
        divisionMasterDTO1.setId(UUID.randomUUID());
        DivisionMasterDTO divisionMasterDTO2 = new DivisionMasterDTO();
        assertThat(divisionMasterDTO1).isNotEqualTo(divisionMasterDTO2);
        divisionMasterDTO2.setId(divisionMasterDTO1.getId());
        assertThat(divisionMasterDTO1).isEqualTo(divisionMasterDTO2);
        divisionMasterDTO2.setId(UUID.randomUUID());
        assertThat(divisionMasterDTO1).isNotEqualTo(divisionMasterDTO2);
        divisionMasterDTO1.setId(null);
        assertThat(divisionMasterDTO1).isNotEqualTo(divisionMasterDTO2);
    }
}
