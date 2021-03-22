package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.RateChangeLog;
import com.activeport.web.repository.RateChangeLogRepository;
import com.activeport.web.service.RateChangeLogService;
import com.activeport.web.service.dto.RateChangeLogDTO;
import com.activeport.web.service.mapper.RateChangeLogMapper;
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
 * Integration tests for the {@link RateChangeLogResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RateChangeLogResourceIT {
    private static final Integer DEFAULT_PREVIUOS_RATE = 1;
    private static final Integer UPDATED_PREVIUOS_RATE = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    @Autowired
    private RateChangeLogRepository rateChangeLogRepository;

    @Autowired
    private RateChangeLogMapper rateChangeLogMapper;

    @Autowired
    private RateChangeLogService rateChangeLogService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRateChangeLogMockMvc;

    private RateChangeLog rateChangeLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RateChangeLog createEntity(EntityManager em) {
        RateChangeLog rateChangeLog = new RateChangeLog().previuosRate(DEFAULT_PREVIUOS_RATE).rate(DEFAULT_RATE);
        return rateChangeLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RateChangeLog createUpdatedEntity(EntityManager em) {
        RateChangeLog rateChangeLog = new RateChangeLog().previuosRate(UPDATED_PREVIUOS_RATE).rate(UPDATED_RATE);
        return rateChangeLog;
    }

    @BeforeEach
    public void initTest() {
        rateChangeLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createRateChangeLog() throws Exception {
        int databaseSizeBeforeCreate = rateChangeLogRepository.findAll().size();
        // Create the RateChangeLog
        RateChangeLogDTO rateChangeLogDTO = rateChangeLogMapper.toDto(rateChangeLog);
        restRateChangeLogMockMvc
            .perform(
                post("/api/rate-change-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rateChangeLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RateChangeLog in the database
        List<RateChangeLog> rateChangeLogList = rateChangeLogRepository.findAll();
        assertThat(rateChangeLogList).hasSize(databaseSizeBeforeCreate + 1);
        RateChangeLog testRateChangeLog = rateChangeLogList.get(rateChangeLogList.size() - 1);
        assertThat(testRateChangeLog.getPreviuosRate()).isEqualTo(DEFAULT_PREVIUOS_RATE);
        assertThat(testRateChangeLog.getRate()).isEqualTo(DEFAULT_RATE);
    }

    @Test
    @Transactional
    public void createRateChangeLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rateChangeLogRepository.findAll().size();

        // Create the RateChangeLog with an existing ID
        rateChangeLog.setId(1L);
        RateChangeLogDTO rateChangeLogDTO = rateChangeLogMapper.toDto(rateChangeLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRateChangeLogMockMvc
            .perform(
                post("/api/rate-change-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rateChangeLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RateChangeLog in the database
        List<RateChangeLog> rateChangeLogList = rateChangeLogRepository.findAll();
        assertThat(rateChangeLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRateChangeLogs() throws Exception {
        // Initialize the database
        rateChangeLogRepository.saveAndFlush(rateChangeLog);

        // Get all the rateChangeLogList
        restRateChangeLogMockMvc
            .perform(get("/api/rate-change-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rateChangeLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].previuosRate").value(hasItem(DEFAULT_PREVIUOS_RATE)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));
    }

    @Test
    @Transactional
    public void getRateChangeLog() throws Exception {
        // Initialize the database
        rateChangeLogRepository.saveAndFlush(rateChangeLog);

        // Get the rateChangeLog
        restRateChangeLogMockMvc
            .perform(get("/api/rate-change-logs/{id}", rateChangeLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rateChangeLog.getId().intValue()))
            .andExpect(jsonPath("$.previuosRate").value(DEFAULT_PREVIUOS_RATE))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE));
    }

    @Test
    @Transactional
    public void getNonExistingRateChangeLog() throws Exception {
        // Get the rateChangeLog
        restRateChangeLogMockMvc.perform(get("/api/rate-change-logs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRateChangeLog() throws Exception {
        // Initialize the database
        rateChangeLogRepository.saveAndFlush(rateChangeLog);

        int databaseSizeBeforeUpdate = rateChangeLogRepository.findAll().size();

        // Update the rateChangeLog
        RateChangeLog updatedRateChangeLog = rateChangeLogRepository.findById(rateChangeLog.getId()).get();
        // Disconnect from session so that the updates on updatedRateChangeLog are not directly saved in db
        em.detach(updatedRateChangeLog);
        updatedRateChangeLog.previuosRate(UPDATED_PREVIUOS_RATE).rate(UPDATED_RATE);
        RateChangeLogDTO rateChangeLogDTO = rateChangeLogMapper.toDto(updatedRateChangeLog);

        restRateChangeLogMockMvc
            .perform(
                put("/api/rate-change-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rateChangeLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the RateChangeLog in the database
        List<RateChangeLog> rateChangeLogList = rateChangeLogRepository.findAll();
        assertThat(rateChangeLogList).hasSize(databaseSizeBeforeUpdate);
        RateChangeLog testRateChangeLog = rateChangeLogList.get(rateChangeLogList.size() - 1);
        assertThat(testRateChangeLog.getPreviuosRate()).isEqualTo(UPDATED_PREVIUOS_RATE);
        assertThat(testRateChangeLog.getRate()).isEqualTo(UPDATED_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingRateChangeLog() throws Exception {
        int databaseSizeBeforeUpdate = rateChangeLogRepository.findAll().size();

        // Create the RateChangeLog
        RateChangeLogDTO rateChangeLogDTO = rateChangeLogMapper.toDto(rateChangeLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRateChangeLogMockMvc
            .perform(
                put("/api/rate-change-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rateChangeLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RateChangeLog in the database
        List<RateChangeLog> rateChangeLogList = rateChangeLogRepository.findAll();
        assertThat(rateChangeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRateChangeLog() throws Exception {
        // Initialize the database
        rateChangeLogRepository.saveAndFlush(rateChangeLog);

        int databaseSizeBeforeDelete = rateChangeLogRepository.findAll().size();

        // Delete the rateChangeLog
        restRateChangeLogMockMvc
            .perform(delete("/api/rate-change-logs/{id}", rateChangeLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RateChangeLog> rateChangeLogList = rateChangeLogRepository.findAll();
        assertThat(rateChangeLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
