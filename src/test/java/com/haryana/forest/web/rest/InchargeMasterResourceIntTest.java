package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.InchargeMaster;
import com.haryana.forest.repository.InchargeMasterRepository;
import com.haryana.forest.service.InchargeMasterService;
import com.haryana.forest.service.dto.InchargeMasterDTO;
import com.haryana.forest.service.mapper.InchargeMasterMapper;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import java.util.UUID;

import static com.haryana.forest.web.rest.TestUtil.sameInstant;
import static com.haryana.forest.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InchargeMasterResource REST controller.
 *
 * @see InchargeMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class InchargeMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_INCHARGE_MASTER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE_MASTER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_INCHARGE_MASTER_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE_MASTER_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NURSERY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NURSERY_ID = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INCHARGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INCHARGE_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_INCHARGE_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE_MOBILE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CHARGE_TAKEN_FROM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CHARGE_TAKEN_FROM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CHARGE_RELEAVE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CHARGE_RELEAVE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private InchargeMasterRepository inchargeMasterRepository;

    @Autowired
    private InchargeMasterMapper inchargeMasterMapper;

    @Autowired
    private InchargeMasterService inchargeMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restInchargeMasterMockMvc;

    private InchargeMaster inchargeMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InchargeMasterResource inchargeMasterResource = new InchargeMasterResource(inchargeMasterService);
        this.restInchargeMasterMockMvc = MockMvcBuilders.standaloneSetup(inchargeMasterResource)
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
    public static InchargeMaster createEntity() {
        InchargeMaster inchargeMaster = new InchargeMaster()
            .inchargeMasterType(DEFAULT_INCHARGE_MASTER_TYPE)
            .inchargeMasterTypeId(DEFAULT_INCHARGE_MASTER_TYPE_ID)
            .nurseryName(DEFAULT_NURSERY_NAME)
            .nurseryId(DEFAULT_NURSERY_ID)
            .inchargeName(DEFAULT_INCHARGE_NAME)
            .inchargeDesignation(DEFAULT_INCHARGE_DESIGNATION)
            .inchargeMobile(DEFAULT_INCHARGE_MOBILE)
            .chargeTakenFrom(DEFAULT_CHARGE_TAKEN_FROM)
            .chargeReleaveDate(DEFAULT_CHARGE_RELEAVE_DATE)
            .isActive(DEFAULT_IS_ACTIVE);
        return inchargeMaster;
    }

    @Before
    public void initTest() {
        inchargeMasterRepository.deleteAll();
        inchargeMaster = createEntity();
    }

    @Test
    public void createInchargeMaster() throws Exception {
        int databaseSizeBeforeCreate = inchargeMasterRepository.findAll().size();

        // Create the InchargeMaster
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);
        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the InchargeMaster in the database
        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        InchargeMaster testInchargeMaster = inchargeMasterList.get(inchargeMasterList.size() - 1);
        assertThat(testInchargeMaster.getInchargeMasterType()).isEqualTo(DEFAULT_INCHARGE_MASTER_TYPE);
        assertThat(testInchargeMaster.getInchargeMasterTypeId()).isEqualTo(DEFAULT_INCHARGE_MASTER_TYPE_ID);
        assertThat(testInchargeMaster.getNurseryName()).isEqualTo(DEFAULT_NURSERY_NAME);
        assertThat(testInchargeMaster.getNurseryId()).isEqualTo(DEFAULT_NURSERY_ID);
        assertThat(testInchargeMaster.getInchargeName()).isEqualTo(DEFAULT_INCHARGE_NAME);
        assertThat(testInchargeMaster.getInchargeDesignation()).isEqualTo(DEFAULT_INCHARGE_DESIGNATION);
        assertThat(testInchargeMaster.getInchargeMobile()).isEqualTo(DEFAULT_INCHARGE_MOBILE);
        assertThat(testInchargeMaster.getChargeTakenFrom()).isEqualTo(DEFAULT_CHARGE_TAKEN_FROM);
        assertThat(testInchargeMaster.getChargeReleaveDate()).isEqualTo(DEFAULT_CHARGE_RELEAVE_DATE);
        assertThat(testInchargeMaster.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createInchargeMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inchargeMasterRepository.findAll().size();

        // Create the InchargeMaster with an existing ID
        inchargeMaster.setId(UUID.randomUUID());
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InchargeMaster in the database
        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkInchargeMasterTypeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterRepository.findAll().size();
        // set the field null
        inchargeMaster.setInchargeMasterTypeId(null);

        // Create the InchargeMaster, which fails.
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNurseryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterRepository.findAll().size();
        // set the field null
        inchargeMaster.setNurseryName(null);

        // Create the InchargeMaster, which fails.
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNurseryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterRepository.findAll().size();
        // set the field null
        inchargeMaster.setNurseryId(null);

        // Create the InchargeMaster, which fails.
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkInchargeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterRepository.findAll().size();
        // set the field null
        inchargeMaster.setInchargeName(null);

        // Create the InchargeMaster, which fails.
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkInchargeDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterRepository.findAll().size();
        // set the field null
        inchargeMaster.setInchargeDesignation(null);

        // Create the InchargeMaster, which fails.
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkInchargeMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterRepository.findAll().size();
        // set the field null
        inchargeMaster.setInchargeMobile(null);

        // Create the InchargeMaster, which fails.
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = inchargeMasterRepository.findAll().size();
        // set the field null
        inchargeMaster.setIsActive(null);

        // Create the InchargeMaster, which fails.
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        restInchargeMasterMockMvc.perform(post("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllInchargeMasters() throws Exception {
        // Initialize the database
        inchargeMaster.setId(UUID.randomUUID());
        inchargeMasterRepository.save(inchargeMaster);

        // Get all the inchargeMasterList
        restInchargeMasterMockMvc.perform(get("/api/incharge-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inchargeMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].inchargeMasterType").value(hasItem(DEFAULT_INCHARGE_MASTER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].inchargeMasterTypeId").value(hasItem(DEFAULT_INCHARGE_MASTER_TYPE_ID.toString())))
            .andExpect(jsonPath("$.[*].nurseryName").value(hasItem(DEFAULT_NURSERY_NAME.toString())))
            .andExpect(jsonPath("$.[*].nurseryId").value(hasItem(DEFAULT_NURSERY_ID.toString())))
            .andExpect(jsonPath("$.[*].inchargeName").value(hasItem(DEFAULT_INCHARGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].inchargeDesignation").value(hasItem(DEFAULT_INCHARGE_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].inchargeMobile").value(hasItem(DEFAULT_INCHARGE_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].chargeTakenFrom").value(hasItem(sameInstant(DEFAULT_CHARGE_TAKEN_FROM))))
            .andExpect(jsonPath("$.[*].chargeReleaveDate").value(hasItem(sameInstant(DEFAULT_CHARGE_RELEAVE_DATE))))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getInchargeMaster() throws Exception {
        // Initialize the database
        inchargeMaster.setId(UUID.randomUUID());
        inchargeMasterRepository.save(inchargeMaster);

        // Get the inchargeMaster
        restInchargeMasterMockMvc.perform(get("/api/incharge-masters/{id}", inchargeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(inchargeMaster.getId().toString()))
            .andExpect(jsonPath("$.inchargeMasterType").value(DEFAULT_INCHARGE_MASTER_TYPE.toString()))
            .andExpect(jsonPath("$.inchargeMasterTypeId").value(DEFAULT_INCHARGE_MASTER_TYPE_ID.toString()))
            .andExpect(jsonPath("$.nurseryName").value(DEFAULT_NURSERY_NAME.toString()))
            .andExpect(jsonPath("$.nurseryId").value(DEFAULT_NURSERY_ID.toString()))
            .andExpect(jsonPath("$.inchargeName").value(DEFAULT_INCHARGE_NAME.toString()))
            .andExpect(jsonPath("$.inchargeDesignation").value(DEFAULT_INCHARGE_DESIGNATION.toString()))
            .andExpect(jsonPath("$.inchargeMobile").value(DEFAULT_INCHARGE_MOBILE.toString()))
            .andExpect(jsonPath("$.chargeTakenFrom").value(sameInstant(DEFAULT_CHARGE_TAKEN_FROM)))
            .andExpect(jsonPath("$.chargeReleaveDate").value(sameInstant(DEFAULT_CHARGE_RELEAVE_DATE)))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    public void getNonExistingInchargeMaster() throws Exception {
        // Get the inchargeMaster
        restInchargeMasterMockMvc.perform(get("/api/incharge-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInchargeMaster() throws Exception {
        // Initialize the database
        inchargeMaster.setId(UUID.randomUUID());
        inchargeMasterRepository.save(inchargeMaster);

        int databaseSizeBeforeUpdate = inchargeMasterRepository.findAll().size();

        // Update the inchargeMaster
        InchargeMaster updatedInchargeMaster = inchargeMasterRepository.findById(inchargeMaster.getId()).get();
        updatedInchargeMaster
            .inchargeMasterType(UPDATED_INCHARGE_MASTER_TYPE)
            .inchargeMasterTypeId(UPDATED_INCHARGE_MASTER_TYPE_ID)
            .nurseryName(UPDATED_NURSERY_NAME)
            .nurseryId(UPDATED_NURSERY_ID)
            .inchargeName(UPDATED_INCHARGE_NAME)
            .inchargeDesignation(UPDATED_INCHARGE_DESIGNATION)
            .inchargeMobile(UPDATED_INCHARGE_MOBILE)
            .chargeTakenFrom(UPDATED_CHARGE_TAKEN_FROM)
            .chargeReleaveDate(UPDATED_CHARGE_RELEAVE_DATE)
            .isActive(UPDATED_IS_ACTIVE);
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(updatedInchargeMaster);

        restInchargeMasterMockMvc.perform(put("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isOk());

        // Validate the InchargeMaster in the database
        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeUpdate);
        InchargeMaster testInchargeMaster = inchargeMasterList.get(inchargeMasterList.size() - 1);
        assertThat(testInchargeMaster.getInchargeMasterType()).isEqualTo(UPDATED_INCHARGE_MASTER_TYPE);
        assertThat(testInchargeMaster.getInchargeMasterTypeId()).isEqualTo(UPDATED_INCHARGE_MASTER_TYPE_ID);
        assertThat(testInchargeMaster.getNurseryName()).isEqualTo(UPDATED_NURSERY_NAME);
        assertThat(testInchargeMaster.getNurseryId()).isEqualTo(UPDATED_NURSERY_ID);
        assertThat(testInchargeMaster.getInchargeName()).isEqualTo(UPDATED_INCHARGE_NAME);
        assertThat(testInchargeMaster.getInchargeDesignation()).isEqualTo(UPDATED_INCHARGE_DESIGNATION);
        assertThat(testInchargeMaster.getInchargeMobile()).isEqualTo(UPDATED_INCHARGE_MOBILE);
        assertThat(testInchargeMaster.getChargeTakenFrom()).isEqualTo(UPDATED_CHARGE_TAKEN_FROM);
        assertThat(testInchargeMaster.getChargeReleaveDate()).isEqualTo(UPDATED_CHARGE_RELEAVE_DATE);
        assertThat(testInchargeMaster.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingInchargeMaster() throws Exception {
        int databaseSizeBeforeUpdate = inchargeMasterRepository.findAll().size();

        // Create the InchargeMaster
        InchargeMasterDTO inchargeMasterDTO = inchargeMasterMapper.toDto(inchargeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInchargeMasterMockMvc.perform(put("/api/incharge-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inchargeMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InchargeMaster in the database
        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteInchargeMaster() throws Exception {
        // Initialize the database
        inchargeMaster.setId(UUID.randomUUID());
        inchargeMasterRepository.save(inchargeMaster);

        int databaseSizeBeforeDelete = inchargeMasterRepository.findAll().size();

        // Get the inchargeMaster
        restInchargeMasterMockMvc.perform(delete("/api/incharge-masters/{id}", inchargeMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InchargeMaster> inchargeMasterList = inchargeMasterRepository.findAll();
        assertThat(inchargeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InchargeMaster.class);
        InchargeMaster inchargeMaster1 = new InchargeMaster();
        inchargeMaster1.setId(UUID.randomUUID());
        InchargeMaster inchargeMaster2 = new InchargeMaster();
        inchargeMaster2.setId(inchargeMaster1.getId());
        assertThat(inchargeMaster1).isEqualTo(inchargeMaster2);
        inchargeMaster2.setId(UUID.randomUUID());
        assertThat(inchargeMaster1).isNotEqualTo(inchargeMaster2);
        inchargeMaster1.setId(null);
        assertThat(inchargeMaster1).isNotEqualTo(inchargeMaster2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InchargeMasterDTO.class);
        InchargeMasterDTO inchargeMasterDTO1 = new InchargeMasterDTO();
        inchargeMasterDTO1.setId(UUID.randomUUID());
        InchargeMasterDTO inchargeMasterDTO2 = new InchargeMasterDTO();
        assertThat(inchargeMasterDTO1).isNotEqualTo(inchargeMasterDTO2);
        inchargeMasterDTO2.setId(inchargeMasterDTO1.getId());
        assertThat(inchargeMasterDTO1).isEqualTo(inchargeMasterDTO2);
        inchargeMasterDTO2.setId(UUID.randomUUID());
        assertThat(inchargeMasterDTO1).isNotEqualTo(inchargeMasterDTO2);
        inchargeMasterDTO1.setId(null);
        assertThat(inchargeMasterDTO1).isNotEqualTo(inchargeMasterDTO2);
    }
}
