package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.CircleMaster;
import com.haryana.forest.repository.CircleMasterRepository;
import com.haryana.forest.service.CircleMasterService;
import com.haryana.forest.service.dto.CircleMasterDTO;
import com.haryana.forest.service.mapper.CircleMasterMapper;
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
 * Test class for the CircleMasterResource REST controller.
 *
 * @see CircleMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class CircleMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_CIRCLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CIRCLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STATE_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private CircleMasterRepository circleMasterRepository;

    @Autowired
    private CircleMasterMapper circleMasterMapper;

    @Autowired
    private CircleMasterService circleMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCircleMasterMockMvc;

    private CircleMaster circleMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CircleMasterResource circleMasterResource = new CircleMasterResource(circleMasterService);
        this.restCircleMasterMockMvc = MockMvcBuilders.standaloneSetup(circleMasterResource)
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
    public static CircleMaster createEntity() {
        CircleMaster circleMaster = new CircleMaster()
            .circleName(DEFAULT_CIRCLE_NAME)
            .stateName(DEFAULT_STATE_NAME)
            .stateId(DEFAULT_STATE_ID)
            .isActive(DEFAULT_IS_ACTIVE);
        return circleMaster;
    }

    @Before
    public void initTest() {
        circleMasterRepository.deleteAll();
        circleMaster = createEntity();
    }

    @Test
    public void createCircleMaster() throws Exception {
        int databaseSizeBeforeCreate = circleMasterRepository.findAll().size();

        // Create the CircleMaster
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(circleMaster);
        restCircleMasterMockMvc.perform(post("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the CircleMaster in the database
        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeCreate + 1);
        CircleMaster testCircleMaster = circleMasterList.get(circleMasterList.size() - 1);
        assertThat(testCircleMaster.getCircleName()).isEqualTo(DEFAULT_CIRCLE_NAME);
        assertThat(testCircleMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testCircleMaster.getStateId()).isEqualTo(DEFAULT_STATE_ID);
        assertThat(testCircleMaster.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createCircleMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = circleMasterRepository.findAll().size();

        // Create the CircleMaster with an existing ID
        circleMaster.setId(UUID.randomUUID());
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(circleMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCircleMasterMockMvc.perform(post("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CircleMaster in the database
        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkCircleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = circleMasterRepository.findAll().size();
        // set the field null
        circleMaster.setCircleName(null);

        // Create the CircleMaster, which fails.
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(circleMaster);

        restCircleMasterMockMvc.perform(post("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isBadRequest());

        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = circleMasterRepository.findAll().size();
        // set the field null
        circleMaster.setStateName(null);

        // Create the CircleMaster, which fails.
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(circleMaster);

        restCircleMasterMockMvc.perform(post("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isBadRequest());

        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = circleMasterRepository.findAll().size();
        // set the field null
        circleMaster.setStateId(null);

        // Create the CircleMaster, which fails.
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(circleMaster);

        restCircleMasterMockMvc.perform(post("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isBadRequest());

        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = circleMasterRepository.findAll().size();
        // set the field null
        circleMaster.setIsActive(null);

        // Create the CircleMaster, which fails.
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(circleMaster);

        restCircleMasterMockMvc.perform(post("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isBadRequest());

        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCircleMasters() throws Exception {
        // Initialize the database
        circleMaster.setId(UUID.randomUUID());
        circleMasterRepository.save(circleMaster);

        // Get all the circleMasterList
        restCircleMasterMockMvc.perform(get("/api/circle-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(circleMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].circleName").value(hasItem(DEFAULT_CIRCLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getCircleMaster() throws Exception {
        // Initialize the database
        circleMaster.setId(UUID.randomUUID());
        circleMasterRepository.save(circleMaster);

        // Get the circleMaster
        restCircleMasterMockMvc.perform(get("/api/circle-masters/{id}", circleMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(circleMaster.getId().toString()))
            .andExpect(jsonPath("$.circleName").value(DEFAULT_CIRCLE_NAME.toString()))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME.toString()))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    public void getNonExistingCircleMaster() throws Exception {
        // Get the circleMaster
        restCircleMasterMockMvc.perform(get("/api/circle-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCircleMaster() throws Exception {
        // Initialize the database
        circleMaster.setId(UUID.randomUUID());
        circleMasterRepository.save(circleMaster);

        int databaseSizeBeforeUpdate = circleMasterRepository.findAll().size();

        // Update the circleMaster
        CircleMaster updatedCircleMaster = circleMasterRepository.findById(circleMaster.getId()).get();
        updatedCircleMaster
            .circleName(UPDATED_CIRCLE_NAME)
            .stateName(UPDATED_STATE_NAME)
            .stateId(UPDATED_STATE_ID)
            .isActive(UPDATED_IS_ACTIVE);
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(updatedCircleMaster);

        restCircleMasterMockMvc.perform(put("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isOk());

        // Validate the CircleMaster in the database
        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeUpdate);
        CircleMaster testCircleMaster = circleMasterList.get(circleMasterList.size() - 1);
        assertThat(testCircleMaster.getCircleName()).isEqualTo(UPDATED_CIRCLE_NAME);
        assertThat(testCircleMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testCircleMaster.getStateId()).isEqualTo(UPDATED_STATE_ID);
        assertThat(testCircleMaster.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingCircleMaster() throws Exception {
        int databaseSizeBeforeUpdate = circleMasterRepository.findAll().size();

        // Create the CircleMaster
        CircleMasterDTO circleMasterDTO = circleMasterMapper.toDto(circleMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCircleMasterMockMvc.perform(put("/api/circle-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(circleMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CircleMaster in the database
        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCircleMaster() throws Exception {
        // Initialize the database
        circleMaster.setId(UUID.randomUUID());
        circleMasterRepository.save(circleMaster);

        int databaseSizeBeforeDelete = circleMasterRepository.findAll().size();

        // Get the circleMaster
        restCircleMasterMockMvc.perform(delete("/api/circle-masters/{id}", circleMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CircleMaster> circleMasterList = circleMasterRepository.findAll();
        assertThat(circleMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CircleMaster.class);
        CircleMaster circleMaster1 = new CircleMaster();
        circleMaster1.setId(UUID.randomUUID());
        CircleMaster circleMaster2 = new CircleMaster();
        circleMaster2.setId(circleMaster1.getId());
        assertThat(circleMaster1).isEqualTo(circleMaster2);
        circleMaster2.setId(UUID.randomUUID());
        assertThat(circleMaster1).isNotEqualTo(circleMaster2);
        circleMaster1.setId(null);
        assertThat(circleMaster1).isNotEqualTo(circleMaster2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CircleMasterDTO.class);
        CircleMasterDTO circleMasterDTO1 = new CircleMasterDTO();
        circleMasterDTO1.setId(UUID.randomUUID());
        CircleMasterDTO circleMasterDTO2 = new CircleMasterDTO();
        assertThat(circleMasterDTO1).isNotEqualTo(circleMasterDTO2);
        circleMasterDTO2.setId(circleMasterDTO1.getId());
        assertThat(circleMasterDTO1).isEqualTo(circleMasterDTO2);
        circleMasterDTO2.setId(UUID.randomUUID());
        assertThat(circleMasterDTO1).isNotEqualTo(circleMasterDTO2);
        circleMasterDTO1.setId(null);
        assertThat(circleMasterDTO1).isNotEqualTo(circleMasterDTO2);
    }
}
