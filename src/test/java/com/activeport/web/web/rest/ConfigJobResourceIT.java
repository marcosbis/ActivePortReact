package com.activeport.web.web.rest;

import static com.activeport.web.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ConfigJob;
import com.activeport.web.domain.enumeration.EventTypeEnum;
import com.activeport.web.repository.ConfigJobRepository;
import com.activeport.web.service.ConfigJobService;
import com.activeport.web.service.dto.ConfigJobDTO;
import com.activeport.web.service.mapper.ConfigJobMapper;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link ConfigJobResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConfigJobResourceIT {
    private static final String DEFAULT_HOST_ID = "AAAAAAAAAA";
    private static final String UPDATED_HOST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMAND = "AAAAAAAAAA";
    private static final String UPDATED_COMMAND = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EXECUTED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EXECUTED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_EXECUTED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTED_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTED_MESSAGE = "BBBBBBBBBB";

    private static final Long DEFAULT_NTU_ID = 1L;
    private static final Long UPDATED_NTU_ID = 2L;

    private static final EventTypeEnum DEFAULT_EVENT_TYPE = EventTypeEnum.FIREWALL_ACTIVATE;
    private static final EventTypeEnum UPDATED_EVENT_TYPE = EventTypeEnum.FIREWALL_DEACTIVATE;

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CALLBACK_URL = "AAAAAAAAAA";
    private static final String UPDATED_CALLBACK_URL = "BBBBBBBBBB";

    @Autowired
    private ConfigJobRepository configJobRepository;

    @Autowired
    private ConfigJobMapper configJobMapper;

    @Autowired
    private ConfigJobService configJobService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConfigJobMockMvc;

    private ConfigJob configJob;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConfigJob createEntity(EntityManager em) {
        ConfigJob configJob = new ConfigJob()
            .hostId(DEFAULT_HOST_ID)
            .status(DEFAULT_STATUS)
            .uuid(DEFAULT_UUID)
            .message(DEFAULT_MESSAGE)
            .command(DEFAULT_COMMAND)
            .executed(DEFAULT_EXECUTED)
            .executedStatus(DEFAULT_EXECUTED_STATUS)
            .executedMessage(DEFAULT_EXECUTED_MESSAGE)
            .ntuId(DEFAULT_NTU_ID)
            .eventType(DEFAULT_EVENT_TYPE)
            .user(DEFAULT_USER)
            .errorMessage(DEFAULT_ERROR_MESSAGE)
            .callbackUrl(DEFAULT_CALLBACK_URL);
        return configJob;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConfigJob createUpdatedEntity(EntityManager em) {
        ConfigJob configJob = new ConfigJob()
            .hostId(UPDATED_HOST_ID)
            .status(UPDATED_STATUS)
            .uuid(UPDATED_UUID)
            .message(UPDATED_MESSAGE)
            .command(UPDATED_COMMAND)
            .executed(UPDATED_EXECUTED)
            .executedStatus(UPDATED_EXECUTED_STATUS)
            .executedMessage(UPDATED_EXECUTED_MESSAGE)
            .ntuId(UPDATED_NTU_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .user(UPDATED_USER)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .callbackUrl(UPDATED_CALLBACK_URL);
        return configJob;
    }

    @BeforeEach
    public void initTest() {
        configJob = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfigJob() throws Exception {
        int databaseSizeBeforeCreate = configJobRepository.findAll().size();
        // Create the ConfigJob
        ConfigJobDTO configJobDTO = configJobMapper.toDto(configJob);
        restConfigJobMockMvc
            .perform(
                post("/api/config-jobs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(configJobDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ConfigJob in the database
        List<ConfigJob> configJobList = configJobRepository.findAll();
        assertThat(configJobList).hasSize(databaseSizeBeforeCreate + 1);
        ConfigJob testConfigJob = configJobList.get(configJobList.size() - 1);
        assertThat(testConfigJob.getHostId()).isEqualTo(DEFAULT_HOST_ID);
        assertThat(testConfigJob.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testConfigJob.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testConfigJob.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testConfigJob.getCommand()).isEqualTo(DEFAULT_COMMAND);
        assertThat(testConfigJob.getExecuted()).isEqualTo(DEFAULT_EXECUTED);
        assertThat(testConfigJob.getExecutedStatus()).isEqualTo(DEFAULT_EXECUTED_STATUS);
        assertThat(testConfigJob.getExecutedMessage()).isEqualTo(DEFAULT_EXECUTED_MESSAGE);
        assertThat(testConfigJob.getNtuId()).isEqualTo(DEFAULT_NTU_ID);
        assertThat(testConfigJob.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
        assertThat(testConfigJob.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testConfigJob.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testConfigJob.getCallbackUrl()).isEqualTo(DEFAULT_CALLBACK_URL);
    }

    @Test
    @Transactional
    public void createConfigJobWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configJobRepository.findAll().size();

        // Create the ConfigJob with an existing ID
        configJob.setId(1L);
        ConfigJobDTO configJobDTO = configJobMapper.toDto(configJob);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigJobMockMvc
            .perform(
                post("/api/config-jobs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(configJobDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConfigJob in the database
        List<ConfigJob> configJobList = configJobRepository.findAll();
        assertThat(configJobList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfigJobs() throws Exception {
        // Initialize the database
        configJobRepository.saveAndFlush(configJob);

        // Get all the configJobList
        restConfigJobMockMvc
            .perform(get("/api/config-jobs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configJob.getId().intValue())))
            .andExpect(jsonPath("$.[*].hostId").value(hasItem(DEFAULT_HOST_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].command").value(hasItem(DEFAULT_COMMAND.toString())))
            .andExpect(jsonPath("$.[*].executed").value(hasItem(sameInstant(DEFAULT_EXECUTED))))
            .andExpect(jsonPath("$.[*].executedStatus").value(hasItem(DEFAULT_EXECUTED_STATUS)))
            .andExpect(jsonPath("$.[*].executedMessage").value(hasItem(DEFAULT_EXECUTED_MESSAGE)))
            .andExpect(jsonPath("$.[*].ntuId").value(hasItem(DEFAULT_NTU_ID.intValue())))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].errorMessage").value(hasItem(DEFAULT_ERROR_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].callbackUrl").value(hasItem(DEFAULT_CALLBACK_URL)));
    }

    @Test
    @Transactional
    public void getConfigJob() throws Exception {
        // Initialize the database
        configJobRepository.saveAndFlush(configJob);

        // Get the configJob
        restConfigJobMockMvc
            .perform(get("/api/config-jobs/{id}", configJob.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(configJob.getId().intValue()))
            .andExpect(jsonPath("$.hostId").value(DEFAULT_HOST_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.command").value(DEFAULT_COMMAND.toString()))
            .andExpect(jsonPath("$.executed").value(sameInstant(DEFAULT_EXECUTED)))
            .andExpect(jsonPath("$.executedStatus").value(DEFAULT_EXECUTED_STATUS))
            .andExpect(jsonPath("$.executedMessage").value(DEFAULT_EXECUTED_MESSAGE))
            .andExpect(jsonPath("$.ntuId").value(DEFAULT_NTU_ID.intValue()))
            .andExpect(jsonPath("$.eventType").value(DEFAULT_EVENT_TYPE.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.errorMessage").value(DEFAULT_ERROR_MESSAGE.toString()))
            .andExpect(jsonPath("$.callbackUrl").value(DEFAULT_CALLBACK_URL));
    }

    @Test
    @Transactional
    public void getNonExistingConfigJob() throws Exception {
        // Get the configJob
        restConfigJobMockMvc.perform(get("/api/config-jobs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfigJob() throws Exception {
        // Initialize the database
        configJobRepository.saveAndFlush(configJob);

        int databaseSizeBeforeUpdate = configJobRepository.findAll().size();

        // Update the configJob
        ConfigJob updatedConfigJob = configJobRepository.findById(configJob.getId()).get();
        // Disconnect from session so that the updates on updatedConfigJob are not directly saved in db
        em.detach(updatedConfigJob);
        updatedConfigJob
            .hostId(UPDATED_HOST_ID)
            .status(UPDATED_STATUS)
            .uuid(UPDATED_UUID)
            .message(UPDATED_MESSAGE)
            .command(UPDATED_COMMAND)
            .executed(UPDATED_EXECUTED)
            .executedStatus(UPDATED_EXECUTED_STATUS)
            .executedMessage(UPDATED_EXECUTED_MESSAGE)
            .ntuId(UPDATED_NTU_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .user(UPDATED_USER)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .callbackUrl(UPDATED_CALLBACK_URL);
        ConfigJobDTO configJobDTO = configJobMapper.toDto(updatedConfigJob);

        restConfigJobMockMvc
            .perform(
                put("/api/config-jobs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(configJobDTO))
            )
            .andExpect(status().isOk());

        // Validate the ConfigJob in the database
        List<ConfigJob> configJobList = configJobRepository.findAll();
        assertThat(configJobList).hasSize(databaseSizeBeforeUpdate);
        ConfigJob testConfigJob = configJobList.get(configJobList.size() - 1);
        assertThat(testConfigJob.getHostId()).isEqualTo(UPDATED_HOST_ID);
        assertThat(testConfigJob.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testConfigJob.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testConfigJob.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testConfigJob.getCommand()).isEqualTo(UPDATED_COMMAND);
        assertThat(testConfigJob.getExecuted()).isEqualTo(UPDATED_EXECUTED);
        assertThat(testConfigJob.getExecutedStatus()).isEqualTo(UPDATED_EXECUTED_STATUS);
        assertThat(testConfigJob.getExecutedMessage()).isEqualTo(UPDATED_EXECUTED_MESSAGE);
        assertThat(testConfigJob.getNtuId()).isEqualTo(UPDATED_NTU_ID);
        assertThat(testConfigJob.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
        assertThat(testConfigJob.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testConfigJob.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testConfigJob.getCallbackUrl()).isEqualTo(UPDATED_CALLBACK_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingConfigJob() throws Exception {
        int databaseSizeBeforeUpdate = configJobRepository.findAll().size();

        // Create the ConfigJob
        ConfigJobDTO configJobDTO = configJobMapper.toDto(configJob);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfigJobMockMvc
            .perform(
                put("/api/config-jobs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(configJobDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConfigJob in the database
        List<ConfigJob> configJobList = configJobRepository.findAll();
        assertThat(configJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfigJob() throws Exception {
        // Initialize the database
        configJobRepository.saveAndFlush(configJob);

        int databaseSizeBeforeDelete = configJobRepository.findAll().size();

        // Delete the configJob
        restConfigJobMockMvc
            .perform(delete("/api/config-jobs/{id}", configJob.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConfigJob> configJobList = configJobRepository.findAll();
        assertThat(configJobList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
