package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ProviderLog;
import com.activeport.web.repository.ProviderLogRepository;
import com.activeport.web.service.ProviderLogService;
import com.activeport.web.service.dto.ProviderLogDTO;
import com.activeport.web.service.mapper.ProviderLogMapper;
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
 * Integration tests for the {@link ProviderLogResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProviderLogResourceIT {
    private static final String DEFAULT_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_SERVICE_ID = 1L;
    private static final Long UPDATED_SERVICE_ID = 2L;

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_UID = "AAAAAAAAAA";
    private static final String UPDATED_JOB_UID = "BBBBBBBBBB";

    private static final Long DEFAULT_NTU_ID = 1L;
    private static final Long UPDATED_NTU_ID = 2L;

    private static final String DEFAULT_REQUEST = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_LOG = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_LOG = "BBBBBBBBBB";

    @Autowired
    private ProviderLogRepository providerLogRepository;

    @Autowired
    private ProviderLogMapper providerLogMapper;

    @Autowired
    private ProviderLogService providerLogService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProviderLogMockMvc;

    private ProviderLog providerLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProviderLog createEntity(EntityManager em) {
        ProviderLog providerLog = new ProviderLog()
            .orgId(DEFAULT_ORG_ID)
            .tenantId(DEFAULT_TENANT_ID)
            .serviceId(DEFAULT_SERVICE_ID)
            .log(DEFAULT_LOG)
            .type(DEFAULT_TYPE)
            .jobUid(DEFAULT_JOB_UID)
            .ntuId(DEFAULT_NTU_ID)
            .request(DEFAULT_REQUEST)
            .responseLog(DEFAULT_RESPONSE_LOG);
        return providerLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProviderLog createUpdatedEntity(EntityManager em) {
        ProviderLog providerLog = new ProviderLog()
            .orgId(UPDATED_ORG_ID)
            .tenantId(UPDATED_TENANT_ID)
            .serviceId(UPDATED_SERVICE_ID)
            .log(UPDATED_LOG)
            .type(UPDATED_TYPE)
            .jobUid(UPDATED_JOB_UID)
            .ntuId(UPDATED_NTU_ID)
            .request(UPDATED_REQUEST)
            .responseLog(UPDATED_RESPONSE_LOG);
        return providerLog;
    }

    @BeforeEach
    public void initTest() {
        providerLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createProviderLog() throws Exception {
        int databaseSizeBeforeCreate = providerLogRepository.findAll().size();
        // Create the ProviderLog
        ProviderLogDTO providerLogDTO = providerLogMapper.toDto(providerLog);
        restProviderLogMockMvc
            .perform(
                post("/api/provider-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProviderLog in the database
        List<ProviderLog> providerLogList = providerLogRepository.findAll();
        assertThat(providerLogList).hasSize(databaseSizeBeforeCreate + 1);
        ProviderLog testProviderLog = providerLogList.get(providerLogList.size() - 1);
        assertThat(testProviderLog.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testProviderLog.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testProviderLog.getServiceId()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testProviderLog.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testProviderLog.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProviderLog.getJobUid()).isEqualTo(DEFAULT_JOB_UID);
        assertThat(testProviderLog.getNtuId()).isEqualTo(DEFAULT_NTU_ID);
        assertThat(testProviderLog.getRequest()).isEqualTo(DEFAULT_REQUEST);
        assertThat(testProviderLog.getResponseLog()).isEqualTo(DEFAULT_RESPONSE_LOG);
    }

    @Test
    @Transactional
    public void createProviderLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providerLogRepository.findAll().size();

        // Create the ProviderLog with an existing ID
        providerLog.setId(1L);
        ProviderLogDTO providerLogDTO = providerLogMapper.toDto(providerLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProviderLogMockMvc
            .perform(
                post("/api/provider-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProviderLog in the database
        List<ProviderLog> providerLogList = providerLogRepository.findAll();
        assertThat(providerLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProviderLogs() throws Exception {
        // Initialize the database
        providerLogRepository.saveAndFlush(providerLog);

        // Get all the providerLogList
        restProviderLogMockMvc
            .perform(get("/api/provider-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(providerLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].orgId").value(hasItem(DEFAULT_ORG_ID)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)))
            .andExpect(jsonPath("$.[*].serviceId").value(hasItem(DEFAULT_SERVICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].jobUid").value(hasItem(DEFAULT_JOB_UID)))
            .andExpect(jsonPath("$.[*].ntuId").value(hasItem(DEFAULT_NTU_ID.intValue())))
            .andExpect(jsonPath("$.[*].request").value(hasItem(DEFAULT_REQUEST.toString())))
            .andExpect(jsonPath("$.[*].responseLog").value(hasItem(DEFAULT_RESPONSE_LOG.toString())));
    }

    @Test
    @Transactional
    public void getProviderLog() throws Exception {
        // Initialize the database
        providerLogRepository.saveAndFlush(providerLog);

        // Get the providerLog
        restProviderLogMockMvc
            .perform(get("/api/provider-logs/{id}", providerLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(providerLog.getId().intValue()))
            .andExpect(jsonPath("$.orgId").value(DEFAULT_ORG_ID))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID))
            .andExpect(jsonPath("$.serviceId").value(DEFAULT_SERVICE_ID.intValue()))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.jobUid").value(DEFAULT_JOB_UID))
            .andExpect(jsonPath("$.ntuId").value(DEFAULT_NTU_ID.intValue()))
            .andExpect(jsonPath("$.request").value(DEFAULT_REQUEST.toString()))
            .andExpect(jsonPath("$.responseLog").value(DEFAULT_RESPONSE_LOG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProviderLog() throws Exception {
        // Get the providerLog
        restProviderLogMockMvc.perform(get("/api/provider-logs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProviderLog() throws Exception {
        // Initialize the database
        providerLogRepository.saveAndFlush(providerLog);

        int databaseSizeBeforeUpdate = providerLogRepository.findAll().size();

        // Update the providerLog
        ProviderLog updatedProviderLog = providerLogRepository.findById(providerLog.getId()).get();
        // Disconnect from session so that the updates on updatedProviderLog are not directly saved in db
        em.detach(updatedProviderLog);
        updatedProviderLog
            .orgId(UPDATED_ORG_ID)
            .tenantId(UPDATED_TENANT_ID)
            .serviceId(UPDATED_SERVICE_ID)
            .log(UPDATED_LOG)
            .type(UPDATED_TYPE)
            .jobUid(UPDATED_JOB_UID)
            .ntuId(UPDATED_NTU_ID)
            .request(UPDATED_REQUEST)
            .responseLog(UPDATED_RESPONSE_LOG);
        ProviderLogDTO providerLogDTO = providerLogMapper.toDto(updatedProviderLog);

        restProviderLogMockMvc
            .perform(
                put("/api/provider-logs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(providerLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProviderLog in the database
        List<ProviderLog> providerLogList = providerLogRepository.findAll();
        assertThat(providerLogList).hasSize(databaseSizeBeforeUpdate);
        ProviderLog testProviderLog = providerLogList.get(providerLogList.size() - 1);
        assertThat(testProviderLog.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testProviderLog.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testProviderLog.getServiceId()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testProviderLog.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testProviderLog.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProviderLog.getJobUid()).isEqualTo(UPDATED_JOB_UID);
        assertThat(testProviderLog.getNtuId()).isEqualTo(UPDATED_NTU_ID);
        assertThat(testProviderLog.getRequest()).isEqualTo(UPDATED_REQUEST);
        assertThat(testProviderLog.getResponseLog()).isEqualTo(UPDATED_RESPONSE_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingProviderLog() throws Exception {
        int databaseSizeBeforeUpdate = providerLogRepository.findAll().size();

        // Create the ProviderLog
        ProviderLogDTO providerLogDTO = providerLogMapper.toDto(providerLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProviderLogMockMvc
            .perform(
                put("/api/provider-logs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(providerLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProviderLog in the database
        List<ProviderLog> providerLogList = providerLogRepository.findAll();
        assertThat(providerLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProviderLog() throws Exception {
        // Initialize the database
        providerLogRepository.saveAndFlush(providerLog);

        int databaseSizeBeforeDelete = providerLogRepository.findAll().size();

        // Delete the providerLog
        restProviderLogMockMvc
            .perform(delete("/api/provider-logs/{id}", providerLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProviderLog> providerLogList = providerLogRepository.findAll();
        assertThat(providerLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
