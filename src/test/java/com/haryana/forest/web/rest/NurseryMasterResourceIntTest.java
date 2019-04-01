package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.NurseryMaster;
import com.haryana.forest.repository.NurseryMasterRepository;
import com.haryana.forest.service.NurseryMasterService;
import com.haryana.forest.service.dto.NurseryMasterDTO;
import com.haryana.forest.service.mapper.NurseryMasterMapper;
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
 * Test class for the NurseryMasterResource REST controller.
 *
 * @see NurseryMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class NurseryMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_CIRCLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CIRCLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CIRCLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CIRCLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCKNAME = "AAAAAAAAAA";
    private static final String UPDATED_BLOCKNAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_BLOCK_ID = UUID.randomUUID();
    private static final UUID UPDATED_BLOCK_ID = UUID.randomUUID();

    private static final String DEFAULT_BEAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BEAT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BEAT_ID = "AAAAAAAAAA";
    private static final String UPDATED_BEAT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NURSERY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NURSERY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_ADDRESS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NURSERY_ESTABLISHMENT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NURSERY_ESTABLISHMENT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_NURSERY_AREA = 1D;
    private static final Double UPDATED_NURSERY_AREA = 2D;

    private static final String DEFAULT_SOURCE_OF_IRRIGATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_OF_IRRIGATION = "BBBBBBBBBB";

    private static final String DEFAULT_SOIL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOIL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_DETAIL = "BBBBBBBBBB";

    @Autowired
    private NurseryMasterRepository nurseryMasterRepository;

    @Autowired
    private NurseryMasterMapper nurseryMasterMapper;

    @Autowired
    private NurseryMasterService nurseryMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNurseryMasterMockMvc;

    private NurseryMaster nurseryMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryMasterResource nurseryMasterResource = new NurseryMasterResource(nurseryMasterService);
        this.restNurseryMasterMockMvc = MockMvcBuilders.standaloneSetup(nurseryMasterResource)
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
    public static NurseryMaster createEntity() {
        NurseryMaster nurseryMaster = new NurseryMaster()
            .circleName(DEFAULT_CIRCLE_NAME)
            .circleId(DEFAULT_CIRCLE_ID)
            .divisionName(DEFAULT_DIVISION_NAME)
            .divisionId(DEFAULT_DIVISION_ID)
            .rangeName(DEFAULT_RANGE_NAME)
            .rangeId(DEFAULT_RANGE_ID)
            .blockname(DEFAULT_BLOCKNAME)
            .blockId(DEFAULT_BLOCK_ID)
            .beatName(DEFAULT_BEAT_NAME)
            .beatId(DEFAULT_BEAT_ID)
            .nurseryName(DEFAULT_NURSERY_NAME)
            .nurseryAddress(DEFAULT_NURSERY_ADDRESS)
            .nurseryEstablishment(DEFAULT_NURSERY_ESTABLISHMENT)
            .nurseryArea(DEFAULT_NURSERY_AREA)
            .sourceOfIrrigation(DEFAULT_SOURCE_OF_IRRIGATION)
            .soilType(DEFAULT_SOIL_TYPE)
            .otherDetail(DEFAULT_OTHER_DETAIL);
        return nurseryMaster;
    }

    @Before
    public void initTest() {
        nurseryMasterRepository.deleteAll();
        nurseryMaster = createEntity();
    }

    @Test
    public void createNurseryMaster() throws Exception {
        int databaseSizeBeforeCreate = nurseryMasterRepository.findAll().size();

        // Create the NurseryMaster
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);
        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryMaster in the database
        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryMaster testNurseryMaster = nurseryMasterList.get(nurseryMasterList.size() - 1);
        assertThat(testNurseryMaster.getCircleName()).isEqualTo(DEFAULT_CIRCLE_NAME);
        assertThat(testNurseryMaster.getCircleId()).isEqualTo(DEFAULT_CIRCLE_ID);
        assertThat(testNurseryMaster.getDivisionName()).isEqualTo(DEFAULT_DIVISION_NAME);
        assertThat(testNurseryMaster.getDivisionId()).isEqualTo(DEFAULT_DIVISION_ID);
        assertThat(testNurseryMaster.getRangeName()).isEqualTo(DEFAULT_RANGE_NAME);
        assertThat(testNurseryMaster.getRangeId()).isEqualTo(DEFAULT_RANGE_ID);
        assertThat(testNurseryMaster.getBlockname()).isEqualTo(DEFAULT_BLOCKNAME);
        assertThat(testNurseryMaster.getBlockId()).isEqualTo(DEFAULT_BLOCK_ID);
        assertThat(testNurseryMaster.getBeatName()).isEqualTo(DEFAULT_BEAT_NAME);
        assertThat(testNurseryMaster.getBeatId()).isEqualTo(DEFAULT_BEAT_ID);
        assertThat(testNurseryMaster.getNurseryName()).isEqualTo(DEFAULT_NURSERY_NAME);
        assertThat(testNurseryMaster.getNurseryAddress()).isEqualTo(DEFAULT_NURSERY_ADDRESS);
        assertThat(testNurseryMaster.getNurseryEstablishment()).isEqualTo(DEFAULT_NURSERY_ESTABLISHMENT);
        assertThat(testNurseryMaster.getNurseryArea()).isEqualTo(DEFAULT_NURSERY_AREA);
        assertThat(testNurseryMaster.getSourceOfIrrigation()).isEqualTo(DEFAULT_SOURCE_OF_IRRIGATION);
        assertThat(testNurseryMaster.getSoilType()).isEqualTo(DEFAULT_SOIL_TYPE);
        assertThat(testNurseryMaster.getOtherDetail()).isEqualTo(DEFAULT_OTHER_DETAIL);
    }

    @Test
    public void createNurseryMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryMasterRepository.findAll().size();

        // Create the NurseryMaster with an existing ID
        nurseryMaster.setId(UUID.randomUUID());
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryMaster in the database
        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkDivisionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setDivisionName(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDivisionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setDivisionId(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRangeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setRangeName(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRangeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setRangeId(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBlockIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setBlockId(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBeatNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setBeatName(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBeatIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setBeatId(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNurseryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setNurseryName(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNurseryAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryMasterRepository.findAll().size();
        // set the field null
        nurseryMaster.setNurseryAddress(null);

        // Create the NurseryMaster, which fails.
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        restNurseryMasterMockMvc.perform(post("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNurseryMasters() throws Exception {
        // Initialize the database
        nurseryMaster.setId(UUID.randomUUID());
        nurseryMasterRepository.save(nurseryMaster);

        // Get all the nurseryMasterList
        restNurseryMasterMockMvc.perform(get("/api/nursery-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].circleName").value(hasItem(DEFAULT_CIRCLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].circleId").value(hasItem(DEFAULT_CIRCLE_ID.toString())))
            .andExpect(jsonPath("$.[*].divisionName").value(hasItem(DEFAULT_DIVISION_NAME.toString())))
            .andExpect(jsonPath("$.[*].divisionId").value(hasItem(DEFAULT_DIVISION_ID.toString())))
            .andExpect(jsonPath("$.[*].rangeName").value(hasItem(DEFAULT_RANGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].rangeId").value(hasItem(DEFAULT_RANGE_ID.toString())))
            .andExpect(jsonPath("$.[*].blockname").value(hasItem(DEFAULT_BLOCKNAME.toString())))
            .andExpect(jsonPath("$.[*].blockId").value(hasItem(DEFAULT_BLOCK_ID.toString())))
            .andExpect(jsonPath("$.[*].beatName").value(hasItem(DEFAULT_BEAT_NAME.toString())))
            .andExpect(jsonPath("$.[*].beatId").value(hasItem(DEFAULT_BEAT_ID.toString())))
            .andExpect(jsonPath("$.[*].nurseryName").value(hasItem(DEFAULT_NURSERY_NAME.toString())))
            .andExpect(jsonPath("$.[*].nurseryAddress").value(hasItem(DEFAULT_NURSERY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].nurseryEstablishment").value(hasItem(sameInstant(DEFAULT_NURSERY_ESTABLISHMENT))))
            .andExpect(jsonPath("$.[*].nurseryArea").value(hasItem(DEFAULT_NURSERY_AREA.doubleValue())))
            .andExpect(jsonPath("$.[*].sourceOfIrrigation").value(hasItem(DEFAULT_SOURCE_OF_IRRIGATION.toString())))
            .andExpect(jsonPath("$.[*].soilType").value(hasItem(DEFAULT_SOIL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].otherDetail").value(hasItem(DEFAULT_OTHER_DETAIL.toString())));
    }
    
    @Test
    public void getNurseryMaster() throws Exception {
        // Initialize the database
        nurseryMaster.setId(UUID.randomUUID());
        nurseryMasterRepository.save(nurseryMaster);

        // Get the nurseryMaster
        restNurseryMasterMockMvc.perform(get("/api/nursery-masters/{id}", nurseryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryMaster.getId().toString()))
            .andExpect(jsonPath("$.circleName").value(DEFAULT_CIRCLE_NAME.toString()))
            .andExpect(jsonPath("$.circleId").value(DEFAULT_CIRCLE_ID.toString()))
            .andExpect(jsonPath("$.divisionName").value(DEFAULT_DIVISION_NAME.toString()))
            .andExpect(jsonPath("$.divisionId").value(DEFAULT_DIVISION_ID.toString()))
            .andExpect(jsonPath("$.rangeName").value(DEFAULT_RANGE_NAME.toString()))
            .andExpect(jsonPath("$.rangeId").value(DEFAULT_RANGE_ID.toString()))
            .andExpect(jsonPath("$.blockname").value(DEFAULT_BLOCKNAME.toString()))
            .andExpect(jsonPath("$.blockId").value(DEFAULT_BLOCK_ID.toString()))
            .andExpect(jsonPath("$.beatName").value(DEFAULT_BEAT_NAME.toString()))
            .andExpect(jsonPath("$.beatId").value(DEFAULT_BEAT_ID.toString()))
            .andExpect(jsonPath("$.nurseryName").value(DEFAULT_NURSERY_NAME.toString()))
            .andExpect(jsonPath("$.nurseryAddress").value(DEFAULT_NURSERY_ADDRESS.toString()))
            .andExpect(jsonPath("$.nurseryEstablishment").value(sameInstant(DEFAULT_NURSERY_ESTABLISHMENT)))
            .andExpect(jsonPath("$.nurseryArea").value(DEFAULT_NURSERY_AREA.doubleValue()))
            .andExpect(jsonPath("$.sourceOfIrrigation").value(DEFAULT_SOURCE_OF_IRRIGATION.toString()))
            .andExpect(jsonPath("$.soilType").value(DEFAULT_SOIL_TYPE.toString()))
            .andExpect(jsonPath("$.otherDetail").value(DEFAULT_OTHER_DETAIL.toString()));
    }

    @Test
    public void getNonExistingNurseryMaster() throws Exception {
        // Get the nurseryMaster
        restNurseryMasterMockMvc.perform(get("/api/nursery-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNurseryMaster() throws Exception {
        // Initialize the database
        nurseryMaster.setId(UUID.randomUUID());
        nurseryMasterRepository.save(nurseryMaster);

        int databaseSizeBeforeUpdate = nurseryMasterRepository.findAll().size();

        // Update the nurseryMaster
        NurseryMaster updatedNurseryMaster = nurseryMasterRepository.findById(nurseryMaster.getId()).get();
        updatedNurseryMaster
            .circleName(UPDATED_CIRCLE_NAME)
            .circleId(UPDATED_CIRCLE_ID)
            .divisionName(UPDATED_DIVISION_NAME)
            .divisionId(UPDATED_DIVISION_ID)
            .rangeName(UPDATED_RANGE_NAME)
            .rangeId(UPDATED_RANGE_ID)
            .blockname(UPDATED_BLOCKNAME)
            .blockId(UPDATED_BLOCK_ID)
            .beatName(UPDATED_BEAT_NAME)
            .beatId(UPDATED_BEAT_ID)
            .nurseryName(UPDATED_NURSERY_NAME)
            .nurseryAddress(UPDATED_NURSERY_ADDRESS)
            .nurseryEstablishment(UPDATED_NURSERY_ESTABLISHMENT)
            .nurseryArea(UPDATED_NURSERY_AREA)
            .sourceOfIrrigation(UPDATED_SOURCE_OF_IRRIGATION)
            .soilType(UPDATED_SOIL_TYPE)
            .otherDetail(UPDATED_OTHER_DETAIL);
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(updatedNurseryMaster);

        restNurseryMasterMockMvc.perform(put("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryMaster in the database
        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeUpdate);
        NurseryMaster testNurseryMaster = nurseryMasterList.get(nurseryMasterList.size() - 1);
        assertThat(testNurseryMaster.getCircleName()).isEqualTo(UPDATED_CIRCLE_NAME);
        assertThat(testNurseryMaster.getCircleId()).isEqualTo(UPDATED_CIRCLE_ID);
        assertThat(testNurseryMaster.getDivisionName()).isEqualTo(UPDATED_DIVISION_NAME);
        assertThat(testNurseryMaster.getDivisionId()).isEqualTo(UPDATED_DIVISION_ID);
        assertThat(testNurseryMaster.getRangeName()).isEqualTo(UPDATED_RANGE_NAME);
        assertThat(testNurseryMaster.getRangeId()).isEqualTo(UPDATED_RANGE_ID);
        assertThat(testNurseryMaster.getBlockname()).isEqualTo(UPDATED_BLOCKNAME);
        assertThat(testNurseryMaster.getBlockId()).isEqualTo(UPDATED_BLOCK_ID);
        assertThat(testNurseryMaster.getBeatName()).isEqualTo(UPDATED_BEAT_NAME);
        assertThat(testNurseryMaster.getBeatId()).isEqualTo(UPDATED_BEAT_ID);
        assertThat(testNurseryMaster.getNurseryName()).isEqualTo(UPDATED_NURSERY_NAME);
        assertThat(testNurseryMaster.getNurseryAddress()).isEqualTo(UPDATED_NURSERY_ADDRESS);
        assertThat(testNurseryMaster.getNurseryEstablishment()).isEqualTo(UPDATED_NURSERY_ESTABLISHMENT);
        assertThat(testNurseryMaster.getNurseryArea()).isEqualTo(UPDATED_NURSERY_AREA);
        assertThat(testNurseryMaster.getSourceOfIrrigation()).isEqualTo(UPDATED_SOURCE_OF_IRRIGATION);
        assertThat(testNurseryMaster.getSoilType()).isEqualTo(UPDATED_SOIL_TYPE);
        assertThat(testNurseryMaster.getOtherDetail()).isEqualTo(UPDATED_OTHER_DETAIL);
    }

    @Test
    public void updateNonExistingNurseryMaster() throws Exception {
        int databaseSizeBeforeUpdate = nurseryMasterRepository.findAll().size();

        // Create the NurseryMaster
        NurseryMasterDTO nurseryMasterDTO = nurseryMasterMapper.toDto(nurseryMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNurseryMasterMockMvc.perform(put("/api/nursery-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryMaster in the database
        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteNurseryMaster() throws Exception {
        // Initialize the database
        nurseryMaster.setId(UUID.randomUUID());
        nurseryMasterRepository.save(nurseryMaster);

        int databaseSizeBeforeDelete = nurseryMasterRepository.findAll().size();

        // Get the nurseryMaster
        restNurseryMasterMockMvc.perform(delete("/api/nursery-masters/{id}", nurseryMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryMaster> nurseryMasterList = nurseryMasterRepository.findAll();
        assertThat(nurseryMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryMaster.class);
        NurseryMaster nurseryMaster1 = new NurseryMaster();
        nurseryMaster1.setId(UUID.randomUUID());
        NurseryMaster nurseryMaster2 = new NurseryMaster();
        nurseryMaster2.setId(nurseryMaster1.getId());
        assertThat(nurseryMaster1).isEqualTo(nurseryMaster2);
        nurseryMaster2.setId(UUID.randomUUID());
        assertThat(nurseryMaster1).isNotEqualTo(nurseryMaster2);
        nurseryMaster1.setId(null);
        assertThat(nurseryMaster1).isNotEqualTo(nurseryMaster2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryMasterDTO.class);
        NurseryMasterDTO nurseryMasterDTO1 = new NurseryMasterDTO();
        nurseryMasterDTO1.setId(UUID.randomUUID());
        NurseryMasterDTO nurseryMasterDTO2 = new NurseryMasterDTO();
        assertThat(nurseryMasterDTO1).isNotEqualTo(nurseryMasterDTO2);
        nurseryMasterDTO2.setId(nurseryMasterDTO1.getId());
        assertThat(nurseryMasterDTO1).isEqualTo(nurseryMasterDTO2);
        nurseryMasterDTO2.setId(UUID.randomUUID());
        assertThat(nurseryMasterDTO1).isNotEqualTo(nurseryMasterDTO2);
        nurseryMasterDTO1.setId(null);
        assertThat(nurseryMasterDTO1).isNotEqualTo(nurseryMasterDTO2);
    }
}
