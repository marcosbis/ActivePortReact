package com.activeport.web.web.rest;

import static com.activeport.web.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.PolicerRange;
import com.activeport.web.domain.enumeration.RangeTypeEnum;
import com.activeport.web.repository.PolicerRangeRepository;
import com.activeport.web.service.PolicerRangeService;
import com.activeport.web.service.dto.PolicerRangeDTO;
import com.activeport.web.service.mapper.PolicerRangeMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PolicerRangeResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PolicerRangeResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_BURST = 1;
    private static final Integer UPDATED_BURST = 2;

    private static final Integer DEFAULT_BANDWIDTH = 1;
    private static final Integer UPDATED_BANDWIDTH = 2;

    private static final ZonedDateTime DEFAULT_START_POLICER = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_POLICER = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_POLICER = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_POLICER = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final RangeTypeEnum DEFAULT_RANGE_TYPE = RangeTypeEnum.EVERYDAY;
    private static final RangeTypeEnum UPDATED_RANGE_TYPE = RangeTypeEnum.BETWEEN;

    @Autowired
    private PolicerRangeRepository policerRangeRepository;

    @Autowired
    private PolicerRangeMapper policerRangeMapper;

    @Autowired
    private PolicerRangeService policerRangeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPolicerRangeMockMvc;

    private PolicerRange policerRange;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PolicerRange createEntity(EntityManager em) {
        PolicerRange policerRange = new PolicerRange()
            .name(DEFAULT_NAME)
            .burst(DEFAULT_BURST)
            .bandwidth(DEFAULT_BANDWIDTH)
            .startPolicer(DEFAULT_START_POLICER)
            .endPolicer(DEFAULT_END_POLICER)
            .rangeType(DEFAULT_RANGE_TYPE);
        return policerRange;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PolicerRange createUpdatedEntity(EntityManager em) {
        PolicerRange policerRange = new PolicerRange()
            .name(UPDATED_NAME)
            .burst(UPDATED_BURST)
            .bandwidth(UPDATED_BANDWIDTH)
            .startPolicer(UPDATED_START_POLICER)
            .endPolicer(UPDATED_END_POLICER)
            .rangeType(UPDATED_RANGE_TYPE);
        return policerRange;
    }

    @BeforeEach
    public void initTest() {
        policerRange = createEntity(em);
    }

    @Test
    @Transactional
    public void createPolicerRange() throws Exception {
        int databaseSizeBeforeCreate = policerRangeRepository.findAll().size();
        // Create the PolicerRange
        PolicerRangeDTO policerRangeDTO = policerRangeMapper.toDto(policerRange);
        restPolicerRangeMockMvc
            .perform(
                post("/api/policer-ranges")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerRangeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PolicerRange in the database
        List<PolicerRange> policerRangeList = policerRangeRepository.findAll();
        assertThat(policerRangeList).hasSize(databaseSizeBeforeCreate + 1);
        PolicerRange testPolicerRange = policerRangeList.get(policerRangeList.size() - 1);
        assertThat(testPolicerRange.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPolicerRange.getBurst()).isEqualTo(DEFAULT_BURST);
        assertThat(testPolicerRange.getBandwidth()).isEqualTo(DEFAULT_BANDWIDTH);
        assertThat(testPolicerRange.getStartPolicer()).isEqualTo(DEFAULT_START_POLICER);
        assertThat(testPolicerRange.getEndPolicer()).isEqualTo(DEFAULT_END_POLICER);
        assertThat(testPolicerRange.getRangeType()).isEqualTo(DEFAULT_RANGE_TYPE);
    }

    @Test
    @Transactional
    public void createPolicerRangeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = policerRangeRepository.findAll().size();

        // Create the PolicerRange with an existing ID
        policerRange.setId(1L);
        PolicerRangeDTO policerRangeDTO = policerRangeMapper.toDto(policerRange);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPolicerRangeMockMvc
            .perform(
                post("/api/policer-ranges")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerRangeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PolicerRange in the database
        List<PolicerRange> policerRangeList = policerRangeRepository.findAll();
        assertThat(policerRangeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPolicerRanges() throws Exception {
        // Initialize the database
        policerRangeRepository.saveAndFlush(policerRange);

        // Get all the policerRangeList
        restPolicerRangeMockMvc
            .perform(get("/api/policer-ranges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(policerRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].burst").value(hasItem(DEFAULT_BURST)))
            .andExpect(jsonPath("$.[*].bandwidth").value(hasItem(DEFAULT_BANDWIDTH)))
            .andExpect(jsonPath("$.[*].startPolicer").value(hasItem(sameInstant(DEFAULT_START_POLICER))))
            .andExpect(jsonPath("$.[*].endPolicer").value(hasItem(sameInstant(DEFAULT_END_POLICER))))
            .andExpect(jsonPath("$.[*].rangeType").value(hasItem(DEFAULT_RANGE_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getPolicerRange() throws Exception {
        // Initialize the database
        policerRangeRepository.saveAndFlush(policerRange);

        // Get the policerRange
        restPolicerRangeMockMvc
            .perform(get("/api/policer-ranges/{id}", policerRange.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(policerRange.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.burst").value(DEFAULT_BURST))
            .andExpect(jsonPath("$.bandwidth").value(DEFAULT_BANDWIDTH))
            .andExpect(jsonPath("$.startPolicer").value(sameInstant(DEFAULT_START_POLICER)))
            .andExpect(jsonPath("$.endPolicer").value(sameInstant(DEFAULT_END_POLICER)))
            .andExpect(jsonPath("$.rangeType").value(DEFAULT_RANGE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPolicerRange() throws Exception {
        // Get the policerRange
        restPolicerRangeMockMvc.perform(get("/api/policer-ranges/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePolicerRange() throws Exception {
        // Initialize the database
        policerRangeRepository.saveAndFlush(policerRange);

        int databaseSizeBeforeUpdate = policerRangeRepository.findAll().size();

        // Update the policerRange
        PolicerRange updatedPolicerRange = policerRangeRepository.findById(policerRange.getId()).get();
        // Disconnect from session so that the updates on updatedPolicerRange are not directly saved in db
        em.detach(updatedPolicerRange);
        updatedPolicerRange
            .name(UPDATED_NAME)
            .burst(UPDATED_BURST)
            .bandwidth(UPDATED_BANDWIDTH)
            .startPolicer(UPDATED_START_POLICER)
            .endPolicer(UPDATED_END_POLICER)
            .rangeType(UPDATED_RANGE_TYPE);
        PolicerRangeDTO policerRangeDTO = policerRangeMapper.toDto(updatedPolicerRange);

        restPolicerRangeMockMvc
            .perform(
                put("/api/policer-ranges")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerRangeDTO))
            )
            .andExpect(status().isOk());

        // Validate the PolicerRange in the database
        List<PolicerRange> policerRangeList = policerRangeRepository.findAll();
        assertThat(policerRangeList).hasSize(databaseSizeBeforeUpdate);
        PolicerRange testPolicerRange = policerRangeList.get(policerRangeList.size() - 1);
        assertThat(testPolicerRange.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPolicerRange.getBurst()).isEqualTo(UPDATED_BURST);
        assertThat(testPolicerRange.getBandwidth()).isEqualTo(UPDATED_BANDWIDTH);
        assertThat(testPolicerRange.getStartPolicer()).isEqualTo(UPDATED_START_POLICER);
        assertThat(testPolicerRange.getEndPolicer()).isEqualTo(UPDATED_END_POLICER);
        assertThat(testPolicerRange.getRangeType()).isEqualTo(UPDATED_RANGE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingPolicerRange() throws Exception {
        int databaseSizeBeforeUpdate = policerRangeRepository.findAll().size();

        // Create the PolicerRange
        PolicerRangeDTO policerRangeDTO = policerRangeMapper.toDto(policerRange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPolicerRangeMockMvc
            .perform(
                put("/api/policer-ranges")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerRangeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PolicerRange in the database
        List<PolicerRange> policerRangeList = policerRangeRepository.findAll();
        assertThat(policerRangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePolicerRange() throws Exception {
        // Initialize the database
        policerRangeRepository.saveAndFlush(policerRange);

        int databaseSizeBeforeDelete = policerRangeRepository.findAll().size();

        // Delete the policerRange
        restPolicerRangeMockMvc
            .perform(delete("/api/policer-ranges/{id}", policerRange.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PolicerRange> policerRangeList = policerRangeRepository.findAll();
        assertThat(policerRangeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
