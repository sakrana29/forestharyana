package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.InchargeMasterType;
import com.haryana.forest.repository.InchargeMasterTypeRepository;
import com.haryana.forest.service.InchargeMasterTypeService;
import com.haryana.forest.service.dto.InchargeMasterTypeDTO;
import com.haryana.forest.service.mapper.InchargeMasterTypeMapper;
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
 * Test class for the InchargeMasterTypeResource REST controller.
 *
 * @see InchargeMasterTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class InchargeMasterTypeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_INCHARGE_MASTER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE_MASTER_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private InchargeMasterTypeRepository inchargeMasterTypeRepository;

    @Autowired
    private InchargeMasterTypeMapper inchargeMasterTypeMapper;

    @Autowired
    private InchargeMasterTypeService inchargeMasterTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restInchargeMasterTypeMockMvc;

    private InchargeMasterType inchargeMasterType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InchargeMasterTypeResource inchargeMasterTypeResource = new InchargeMasterTypeResource(inchargeMasterTypeService);
        this.restInchargeMasterTypeMockMvc = MockMvcBuilders.standaloneSetup(inchargeMasterTypeResource)
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
    public static InchargeMasterType createEntity() {
        InchargeMasterType inchargeMasterType = new InchargeMasterType()
            .inchargeMasterType(DEFAULT_INCHARGE_MASTER_TYPE)
            .isActive(DEFAULT_IS_ACTIVE);
        return inchargeMasterType;
    }

    @Before
    public void initTest() {
        inchargeMasterTypeRepository.deleteAll();
        inchargeMasterType = createEntity();
    }

    @Test
    public void createInchargeMasterType() throws Exception {
        int databaseSizeBeforeCreate = inchargeMasterTypeRepository.findAll().size();

        // Create the InchargeMasterType
        InchargeMasterTypeDTO inchargeMasterTypeDTO = inchargeMasterTypeMapper.toDto(inchargeMasterType);
        restInchargeMasterTypeMockMvc.perform(post("/api/incharge-master-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the InchargeMasterType in the database
        List<InchargeMasterType> inchargeMasterTypeList = inchargeMasterTypeRepository.findAll();
        assertThat(inchargeMasterTypeList).hasSize(databaseSizeBeforeCreate + 1);
        InchargeMasterType testInchargeMasterType = inchargeMasterTypeList.get(inchargeMasterTypeList.size() - 1);
        assertThat(testInchargeMasterType.getInchargeMasterType()).isEqualTo(DEFAULT_INCHARGE_MASTER_TYPE);
        assertThat(testInchargeMasterType.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createInchargeMasterTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inchargeMasterTypeRepository.findAll().size();

        // Create the InchargeMasterType with an existing ID
        inchargeMasterType.setId(UUID.randomUUID());
        InchargeMasterTypeDTO inchargeMasterTypeDTO = inchargeMasterTypeMapper.toDto(inchargeMasterType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInchargeMasterTypeMockMvc.perform(post("/api/incharge-master-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InchargeMasterType in the database
        List<InchargeMasterType> inchargeMasterTypeList = inchargeMasterTypeRepository.findAll();
        assertThat(inchargeMasterTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkInchargeMasterTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterTypeRepository.findAll().size();
        // set the field null
        inchargeMasterType.setInchargeMasterType(null);

        // Create the InchargeMasterType, which fails.
        InchargeMasterTypeDTO inchargeMasterTypeDTO = inchargeMasterTypeMapper.toDto(inchargeMasterType);

        restInchargeMasterTypeMockMvc.perform(post("/api/incharge-master-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterTypeDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMasterType> inchargeMasterTypeList = inchargeMasterTypeRepository.findAll();
        assertThat(inchargeMasterTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterTypeRepository.findAll().size();
        // set the field null
        inchargeMasterType.setIsActive(null);

        // Create the InchargeMasterType, which fails.
        InchargeMasterTypeDTO inchargeMasterTypeDTO = inchargeMasterTypeMapper.toDto(inchargeMasterType);

        restInchargeMasterTypeMockMvc.perform(post("/api/incharge-master-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterTypeDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMasterType> inchargeMasterTypeList = inchargeMasterTypeRepository.findAll();
        assertThat(inchargeMasterTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllInchargeMasterTypes() throws Exception {
        // Initialize the database
        inchargeMasterType.setId(UUID.randomUUID());
        inchargeMasterTypeRepository.save(inchargeMasterType);

        // Get all the inchargeMasterTypeList
        restInchargeMasterTypeMockMvc.perform(get("/api/incharge-master-types"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inchargeMasterType.getId().toString())))
            .andExpect(jsonPath("$.[*].inchargeMasterType").value(hasItem(DEFAULT_INCHARGE_MASTER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getInchargeMasterType() throws Exception {
        // Initialize the database
        inchargeMasterType.setId(UUID.randomUUID());
        inchargeMasterTypeRepository.save(inchargeMasterType);

        // Get the inchargeMasterType
        restInchargeMasterTypeMockMvc.perform(get("/api/incharge-master-types/{id}", inchargeMasterType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(inchargeMasterType.getId().toString()))
            .andExpect(jsonPath("$.inchargeMasterType").value(DEFAULT_INCHARGE_MASTER_TYPE.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    public void getNonExistingInchargeMasterType() throws Exception {
        // Get the inchargeMasterType
        restInchargeMasterTypeMockMvc.perform(get("/api/incharge-master-types/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInchargeMasterType() throws Exception {
        // Initialize the database
        inchargeMasterType.setId(UUID.randomUUID());
        inchargeMasterTypeRepository.save(inchargeMasterType);

        int databaseSizeBeforeUpdate = inchargeMasterTypeRepository.findAll().size();

        // Update the inchargeMasterType
        InchargeMasterType updatedInchargeMasterType = inchargeMasterTypeRepository.findById(inchargeMasterType.getId()).get();
        updatedInchargeMasterType
            .inchargeMasterType(UPDATED_INCHARGE_MASTER_TYPE)
            .isActive(UPDATED_IS_ACTIVE);
        InchargeMasterTypeDTO inchargeMasterTypeDTO = inchargeMasterTypeMapper.toDto(updatedInchargeMasterType);

        restInchargeMasterTypeMockMvc.perform(put("/api/incharge-master-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterTypeDTO)))
            .andExpect(status().isOk());

        // Validate the InchargeMasterType in the database
        List<InchargeMasterType> inchargeMasterTypeList = inchargeMasterTypeRepository.findAll();
        assertThat(inchargeMasterTypeList).hasSize(databaseSizeBeforeUpdate);
        InchargeMasterType testInchargeMasterType = inchargeMasterTypeList.get(inchargeMasterTypeList.size() - 1);
        assertThat(testInchargeMasterType.getInchargeMasterType()).isEqualTo(UPDATED_INCHARGE_MASTER_TYPE);
        assertThat(testInchargeMasterType.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingInchargeMasterType() throws Exception {
        int databaseSizeBeforeUpdate = inchargeMasterTypeRepository.findAll().size();

        // Create the InchargeMasterType
        InchargeMasterTypeDTO inchargeMasterTypeDTO = inchargeMasterTypeMapper.toDto(inchargeMasterType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInchargeMasterTypeMockMvc.perform(put("/api/incharge-master-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InchargeMasterType in the database
        List<InchargeMasterType> inchargeMasterTypeList = inchargeMasterTypeRepository.findAll();
        assertThat(inchargeMasterTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteInchargeMasterType() throws Exception {
        // Initialize the database
        inchargeMasterType.setId(UUID.randomUUID());
        inchargeMasterTypeRepository.save(inchargeMasterType);

        int databaseSizeBeforeDelete = inchargeMasterTypeRepository.findAll().size();

        // Get the inchargeMasterType
        restInchargeMasterTypeMockMvc.perform(delete("/api/incharge-master-types/{id}", inchargeMasterType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InchargeMasterType> inchargeMasterTypeList = inchargeMasterTypeRepository.findAll();
        assertThat(inchargeMasterTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InchargeMasterType.class);
        InchargeMasterType inchargeMasterType1 = new InchargeMasterType();
        inchargeMasterType1.setId(UUID.randomUUID());
        InchargeMasterType inchargeMasterType2 = new InchargeMasterType();
        inchargeMasterType2.setId(inchargeMasterType1.getId());
        assertThat(inchargeMasterType1).isEqualTo(inchargeMasterType2);
        inchargeMasterType2.setId(UUID.randomUUID());
        assertThat(inchargeMasterType1).isNotEqualTo(inchargeMasterType2);
        inchargeMasterType1.setId(null);
        assertThat(inchargeMasterType1).isNotEqualTo(inchargeMasterType2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InchargeMasterTypeDTO.class);
        InchargeMasterTypeDTO inchargeMasterTypeDTO1 = new InchargeMasterTypeDTO();
        inchargeMasterTypeDTO1.setId(UUID.randomUUID());
        InchargeMasterTypeDTO inchargeMasterTypeDTO2 = new InchargeMasterTypeDTO();
        assertThat(inchargeMasterTypeDTO1).isNotEqualTo(inchargeMasterTypeDTO2);
        inchargeMasterTypeDTO2.setId(inchargeMasterTypeDTO1.getId());
        assertThat(inchargeMasterTypeDTO1).isEqualTo(inchargeMasterTypeDTO2);
        inchargeMasterTypeDTO2.setId(UUID.randomUUID());
        assertThat(inchargeMasterTypeDTO1).isNotEqualTo(inchargeMasterTypeDTO2);
        inchargeMasterTypeDTO1.setId(null);
        assertThat(inchargeMasterTypeDTO1).isNotEqualTo(inchargeMasterTypeDTO2);
    }
}
