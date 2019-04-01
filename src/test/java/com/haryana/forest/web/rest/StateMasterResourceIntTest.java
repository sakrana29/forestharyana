package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.StateMaster;
import com.haryana.forest.repository.StateMasterRepository;
import com.haryana.forest.service.StateMasterService;
import com.haryana.forest.service.dto.StateMasterDTO;
import com.haryana.forest.service.mapper.StateMasterMapper;
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
 * Test class for the StateMasterResource REST controller.
 *
 * @see StateMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class StateMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private StateMasterRepository stateMasterRepository;

    @Autowired
    private StateMasterMapper stateMasterMapper;

    @Autowired
    private StateMasterService stateMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restStateMasterMockMvc;

    private StateMaster stateMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StateMasterResource stateMasterResource = new StateMasterResource(stateMasterService);
        this.restStateMasterMockMvc = MockMvcBuilders.standaloneSetup(stateMasterResource)
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
    public static StateMaster createEntity() {
        StateMaster stateMaster = new StateMaster()
            .stateName(DEFAULT_STATE_NAME)
            .isActive(DEFAULT_IS_ACTIVE);
        return stateMaster;
    }

    @Before
    public void initTest() {
        stateMasterRepository.deleteAll();
        stateMaster = createEntity();
    }

    @Test
    public void createStateMaster() throws Exception {
        int databaseSizeBeforeCreate = stateMasterRepository.findAll().size();

        // Create the StateMaster
        StateMasterDTO stateMasterDTO = stateMasterMapper.toDto(stateMaster);
        restStateMasterMockMvc.perform(post("/api/state-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stateMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeCreate + 1);
        StateMaster testStateMaster = stateMasterList.get(stateMasterList.size() - 1);
        assertThat(testStateMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testStateMaster.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createStateMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stateMasterRepository.findAll().size();

        // Create the StateMaster with an existing ID
        stateMaster.setId(UUID.randomUUID());
        StateMasterDTO stateMasterDTO = stateMasterMapper.toDto(stateMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStateMasterMockMvc.perform(post("/api/state-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stateMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkStateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = stateMasterRepository.findAll().size();
        // set the field null
        stateMaster.setStateName(null);

        // Create the StateMaster, which fails.
        StateMasterDTO stateMasterDTO = stateMasterMapper.toDto(stateMaster);

        restStateMasterMockMvc.perform(post("/api/state-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stateMasterDTO)))
            .andExpect(status().isBadRequest());

        List<StateMaster> stateMasterList = stateMasterRepository.findAll();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = stateMasterRepository.findAll().size();
        // set the field null
        stateMaster.setIsActive(null);

        // Create the StateMaster, which fails.
        StateMasterDTO stateMasterDTO = stateMasterMapper.toDto(stateMaster);

        restStateMasterMockMvc.perform(post("/api/state-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stateMasterDTO)))
            .andExpect(status().isBadRequest());

        List<StateMaster> stateMasterList = stateMasterRepository.findAll();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllStateMasters() throws Exception {
        // Initialize the database
        stateMaster.setId(UUID.randomUUID());
        stateMasterRepository.save(stateMaster);

        // Get all the stateMasterList
        restStateMasterMockMvc.perform(get("/api/state-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stateMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getStateMaster() throws Exception {
        // Initialize the database
        stateMaster.setId(UUID.randomUUID());
        stateMasterRepository.save(stateMaster);

        // Get the stateMaster
        restStateMasterMockMvc.perform(get("/api/state-masters/{id}", stateMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stateMaster.getId().toString()))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    public void getNonExistingStateMaster() throws Exception {
        // Get the stateMaster
        restStateMasterMockMvc.perform(get("/api/state-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateStateMaster() throws Exception {
        // Initialize the database
        stateMaster.setId(UUID.randomUUID());
        stateMasterRepository.save(stateMaster);

        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().size();

        // Update the stateMaster
        StateMaster updatedStateMaster = stateMasterRepository.findById(stateMaster.getId()).get();
        updatedStateMaster
            .stateName(UPDATED_STATE_NAME)
            .isActive(UPDATED_IS_ACTIVE);
        StateMasterDTO stateMasterDTO = stateMasterMapper.toDto(updatedStateMaster);

        restStateMasterMockMvc.perform(put("/api/state-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stateMasterDTO)))
            .andExpect(status().isOk());

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
        StateMaster testStateMaster = stateMasterList.get(stateMasterList.size() - 1);
        assertThat(testStateMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testStateMaster.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingStateMaster() throws Exception {
        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().size();

        // Create the StateMaster
        StateMasterDTO stateMasterDTO = stateMasterMapper.toDto(stateMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStateMasterMockMvc.perform(put("/api/state-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stateMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteStateMaster() throws Exception {
        // Initialize the database
        stateMaster.setId(UUID.randomUUID());
        stateMasterRepository.save(stateMaster);

        int databaseSizeBeforeDelete = stateMasterRepository.findAll().size();

        // Get the stateMaster
        restStateMasterMockMvc.perform(delete("/api/state-masters/{id}", stateMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StateMaster> stateMasterList = stateMasterRepository.findAll();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StateMaster.class);
        StateMaster stateMaster1 = new StateMaster();
        stateMaster1.setId(UUID.randomUUID());
        StateMaster stateMaster2 = new StateMaster();
        stateMaster2.setId(stateMaster1.getId());
        assertThat(stateMaster1).isEqualTo(stateMaster2);
        stateMaster2.setId(UUID.randomUUID());
        assertThat(stateMaster1).isNotEqualTo(stateMaster2);
        stateMaster1.setId(null);
        assertThat(stateMaster1).isNotEqualTo(stateMaster2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StateMasterDTO.class);
        StateMasterDTO stateMasterDTO1 = new StateMasterDTO();
        stateMasterDTO1.setId(UUID.randomUUID());
        StateMasterDTO stateMasterDTO2 = new StateMasterDTO();
        assertThat(stateMasterDTO1).isNotEqualTo(stateMasterDTO2);
        stateMasterDTO2.setId(stateMasterDTO1.getId());
        assertThat(stateMasterDTO1).isEqualTo(stateMasterDTO2);
        stateMasterDTO2.setId(UUID.randomUUID());
        assertThat(stateMasterDTO1).isNotEqualTo(stateMasterDTO2);
        stateMasterDTO1.setId(null);
        assertThat(stateMasterDTO1).isNotEqualTo(stateMasterDTO2);
    }
}
