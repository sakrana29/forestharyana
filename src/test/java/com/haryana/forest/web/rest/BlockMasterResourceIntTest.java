package com.haryana.forest.web.rest;

import com.haryana.forest.AbstractCassandraTest;
import com.haryana.forest.ForestharyanaApp;

import com.haryana.forest.domain.BlockMaster;
import com.haryana.forest.repository.BlockMasterRepository;
import com.haryana.forest.service.BlockMasterService;
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
 * Test class for the BlockMasterResource REST controller.
 *
 * @see BlockMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestharyanaApp.class)
public class BlockMasterResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_BLOCK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_ID = "BBBBBBBBBB";

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
    private BlockMasterRepository blockMasterRepository;

    @Autowired
    private BlockMasterService blockMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBlockMasterMockMvc;

    private BlockMaster blockMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlockMasterResource blockMasterResource = new BlockMasterResource(blockMasterService);
        this.restBlockMasterMockMvc = MockMvcBuilders.standaloneSetup(blockMasterResource)
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
    public static BlockMaster createEntity() {
        BlockMaster blockMaster = new BlockMaster()
            .blockName(DEFAULT_BLOCK_NAME)
            .rangeName(DEFAULT_RANGE_NAME)
            .rangeId(DEFAULT_RANGE_ID)
            .divisionName(DEFAULT_DIVISION_NAME)
            .divisionId(DEFAULT_DIVISION_ID)
            .circleName(DEFAULT_CIRCLE_NAME)
            .circleId(DEFAULT_CIRCLE_ID)
            .stateName(DEFAULT_STATE_NAME)
            .stateId(DEFAULT_STATE_ID)
            .isActive(DEFAULT_IS_ACTIVE);
        return blockMaster;
    }

    @Before
    public void initTest() {
        blockMasterRepository.deleteAll();
        blockMaster = createEntity();
    }

    @Test
    public void createBlockMaster() throws Exception {
        int databaseSizeBeforeCreate = blockMasterRepository.findAll().size();

        // Create the BlockMaster
        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isCreated());

        // Validate the BlockMaster in the database
        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeCreate + 1);
        BlockMaster testBlockMaster = blockMasterList.get(blockMasterList.size() - 1);
        assertThat(testBlockMaster.getBlockName()).isEqualTo(DEFAULT_BLOCK_NAME);
        assertThat(testBlockMaster.getRangeName()).isEqualTo(DEFAULT_RANGE_NAME);
        assertThat(testBlockMaster.getRangeId()).isEqualTo(DEFAULT_RANGE_ID);
        assertThat(testBlockMaster.getDivisionName()).isEqualTo(DEFAULT_DIVISION_NAME);
        assertThat(testBlockMaster.getDivisionId()).isEqualTo(DEFAULT_DIVISION_ID);
        assertThat(testBlockMaster.getCircleName()).isEqualTo(DEFAULT_CIRCLE_NAME);
        assertThat(testBlockMaster.getCircleId()).isEqualTo(DEFAULT_CIRCLE_ID);
        assertThat(testBlockMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testBlockMaster.getStateId()).isEqualTo(DEFAULT_STATE_ID);
        assertThat(testBlockMaster.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createBlockMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blockMasterRepository.findAll().size();

        // Create the BlockMaster with an existing ID
        blockMaster.setId(UUID.randomUUID());

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        // Validate the BlockMaster in the database
        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkBlockNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setBlockName(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRangeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setRangeName(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRangeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setRangeId(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDivisionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setDivisionName(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDivisionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setDivisionId(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCircleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setCircleName(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCircleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setCircleId(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockMasterRepository.findAll().size();
        // set the field null
        blockMaster.setIsActive(null);

        // Create the BlockMaster, which fails.

        restBlockMasterMockMvc.perform(post("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBlockMasters() throws Exception {
        // Initialize the database
        blockMaster.setId(UUID.randomUUID());
        blockMasterRepository.save(blockMaster);

        // Get all the blockMasterList
        restBlockMasterMockMvc.perform(get("/api/block-masters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blockMaster.getId().toString())))
            .andExpect(jsonPath("$.[*].blockName").value(hasItem(DEFAULT_BLOCK_NAME.toString())))
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
    public void getBlockMaster() throws Exception {
        // Initialize the database
        blockMaster.setId(UUID.randomUUID());
        blockMasterRepository.save(blockMaster);

        // Get the blockMaster
        restBlockMasterMockMvc.perform(get("/api/block-masters/{id}", blockMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blockMaster.getId().toString()))
            .andExpect(jsonPath("$.blockName").value(DEFAULT_BLOCK_NAME.toString()))
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
    public void getNonExistingBlockMaster() throws Exception {
        // Get the blockMaster
        restBlockMasterMockMvc.perform(get("/api/block-masters/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBlockMaster() throws Exception {
        // Initialize the database
        blockMaster.setId(UUID.randomUUID());
        blockMasterService.save(blockMaster);

        int databaseSizeBeforeUpdate = blockMasterRepository.findAll().size();

        // Update the blockMaster
        BlockMaster updatedBlockMaster = blockMasterRepository.findById(blockMaster.getId()).get();
        updatedBlockMaster
            .blockName(UPDATED_BLOCK_NAME)
            .rangeName(UPDATED_RANGE_NAME)
            .rangeId(UPDATED_RANGE_ID)
            .divisionName(UPDATED_DIVISION_NAME)
            .divisionId(UPDATED_DIVISION_ID)
            .circleName(UPDATED_CIRCLE_NAME)
            .circleId(UPDATED_CIRCLE_ID)
            .stateName(UPDATED_STATE_NAME)
            .stateId(UPDATED_STATE_ID)
            .isActive(UPDATED_IS_ACTIVE);

        restBlockMasterMockMvc.perform(put("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlockMaster)))
            .andExpect(status().isOk());

        // Validate the BlockMaster in the database
        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeUpdate);
        BlockMaster testBlockMaster = blockMasterList.get(blockMasterList.size() - 1);
        assertThat(testBlockMaster.getBlockName()).isEqualTo(UPDATED_BLOCK_NAME);
        assertThat(testBlockMaster.getRangeName()).isEqualTo(UPDATED_RANGE_NAME);
        assertThat(testBlockMaster.getRangeId()).isEqualTo(UPDATED_RANGE_ID);
        assertThat(testBlockMaster.getDivisionName()).isEqualTo(UPDATED_DIVISION_NAME);
        assertThat(testBlockMaster.getDivisionId()).isEqualTo(UPDATED_DIVISION_ID);
        assertThat(testBlockMaster.getCircleName()).isEqualTo(UPDATED_CIRCLE_NAME);
        assertThat(testBlockMaster.getCircleId()).isEqualTo(UPDATED_CIRCLE_ID);
        assertThat(testBlockMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testBlockMaster.getStateId()).isEqualTo(UPDATED_STATE_ID);
        assertThat(testBlockMaster.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingBlockMaster() throws Exception {
        int databaseSizeBeforeUpdate = blockMasterRepository.findAll().size();

        // Create the BlockMaster

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlockMasterMockMvc.perform(put("/api/block-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockMaster)))
            .andExpect(status().isBadRequest());

        // Validate the BlockMaster in the database
        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBlockMaster() throws Exception {
        // Initialize the database
        blockMaster.setId(UUID.randomUUID());
        blockMasterService.save(blockMaster);

        int databaseSizeBeforeDelete = blockMasterRepository.findAll().size();

        // Get the blockMaster
        restBlockMasterMockMvc.perform(delete("/api/block-masters/{id}", blockMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BlockMaster> blockMasterList = blockMasterRepository.findAll();
        assertThat(blockMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlockMaster.class);
        BlockMaster blockMaster1 = new BlockMaster();
        blockMaster1.setId(UUID.randomUUID());
        BlockMaster blockMaster2 = new BlockMaster();
        blockMaster2.setId(blockMaster1.getId());
        assertThat(blockMaster1).isEqualTo(blockMaster2);
        blockMaster2.setId(UUID.randomUUID());
        assertThat(blockMaster1).isNotEqualTo(blockMaster2);
        blockMaster1.setId(null);
        assertThat(blockMaster1).isNotEqualTo(blockMaster2);
    }
}
