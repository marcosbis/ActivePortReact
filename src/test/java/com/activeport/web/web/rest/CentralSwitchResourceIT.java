package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.CentralSwitch;
import com.activeport.web.repository.CentralSwitchRepository;
import com.activeport.web.service.CentralSwitchService;
import com.activeport.web.service.dto.CentralSwitchDTO;
import com.activeport.web.service.mapper.CentralSwitchMapper;
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
 * Integration tests for the {@link CentralSwitchResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CentralSwitchResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOST_ID = "AAAAAAAAAA";
    private static final String UPDATED_HOST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOST_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONFIG_BACKUP = false;
    private static final Boolean UPDATED_CONFIG_BACKUP = true;

    private static final Integer DEFAULT_POOL_VLAN_START = 1;
    private static final Integer UPDATED_POOL_VLAN_START = 2;

    private static final Integer DEFAULT_POOL_VLAN_END = 1;
    private static final Integer UPDATED_POOL_VLAN_END = 2;

    private static final String DEFAULT_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_REST_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_REST_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_REST_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_REST_PASSWORD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REST_ENABLED = false;
    private static final Boolean UPDATED_REST_ENABLED = true;

    private static final Boolean DEFAULT_AUTO_ROLLBACK = false;
    private static final Boolean UPDATED_AUTO_ROLLBACK = true;

    private static final Boolean DEFAULT_FEIGN = false;
    private static final Boolean UPDATED_FEIGN = true;

    @Autowired
    private CentralSwitchRepository centralSwitchRepository;

    @Autowired
    private CentralSwitchMapper centralSwitchMapper;

    @Autowired
    private CentralSwitchService centralSwitchService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCentralSwitchMockMvc;

    private CentralSwitch centralSwitch;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentralSwitch createEntity(EntityManager em) {
        CentralSwitch centralSwitch = new CentralSwitch()
            .name(DEFAULT_NAME)
            .hostId(DEFAULT_HOST_ID)
            .description(DEFAULT_DESCRIPTION)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .companyName(DEFAULT_COMPANY_NAME)
            .hostName(DEFAULT_HOST_NAME)
            .configBackup(DEFAULT_CONFIG_BACKUP)
            .poolVlanStart(DEFAULT_POOL_VLAN_START)
            .poolVlanEnd(DEFAULT_POOL_VLAN_END)
            .endpoint(DEFAULT_ENDPOINT)
            .restUsername(DEFAULT_REST_USERNAME)
            .restPassword(DEFAULT_REST_PASSWORD)
            .restEnabled(DEFAULT_REST_ENABLED)
            .autoRollback(DEFAULT_AUTO_ROLLBACK)
            .feign(DEFAULT_FEIGN);
        return centralSwitch;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentralSwitch createUpdatedEntity(EntityManager em) {
        CentralSwitch centralSwitch = new CentralSwitch()
            .name(UPDATED_NAME)
            .hostId(UPDATED_HOST_ID)
            .description(UPDATED_DESCRIPTION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .ipAddress(UPDATED_IP_ADDRESS)
            .companyName(UPDATED_COMPANY_NAME)
            .hostName(UPDATED_HOST_NAME)
            .configBackup(UPDATED_CONFIG_BACKUP)
            .poolVlanStart(UPDATED_POOL_VLAN_START)
            .poolVlanEnd(UPDATED_POOL_VLAN_END)
            .endpoint(UPDATED_ENDPOINT)
            .restUsername(UPDATED_REST_USERNAME)
            .restPassword(UPDATED_REST_PASSWORD)
            .restEnabled(UPDATED_REST_ENABLED)
            .autoRollback(UPDATED_AUTO_ROLLBACK)
            .feign(UPDATED_FEIGN);
        return centralSwitch;
    }

    @BeforeEach
    public void initTest() {
        centralSwitch = createEntity(em);
    }

    @Test
    @Transactional
    public void createCentralSwitch() throws Exception {
        int databaseSizeBeforeCreate = centralSwitchRepository.findAll().size();
        // Create the CentralSwitch
        CentralSwitchDTO centralSwitchDTO = centralSwitchMapper.toDto(centralSwitch);
        restCentralSwitchMockMvc
            .perform(
                post("/api/central-switches")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centralSwitchDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CentralSwitch in the database
        List<CentralSwitch> centralSwitchList = centralSwitchRepository.findAll();
        assertThat(centralSwitchList).hasSize(databaseSizeBeforeCreate + 1);
        CentralSwitch testCentralSwitch = centralSwitchList.get(centralSwitchList.size() - 1);
        assertThat(testCentralSwitch.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCentralSwitch.getHostId()).isEqualTo(DEFAULT_HOST_ID);
        assertThat(testCentralSwitch.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCentralSwitch.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testCentralSwitch.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testCentralSwitch.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testCentralSwitch.getHostName()).isEqualTo(DEFAULT_HOST_NAME);
        assertThat(testCentralSwitch.isConfigBackup()).isEqualTo(DEFAULT_CONFIG_BACKUP);
        assertThat(testCentralSwitch.getPoolVlanStart()).isEqualTo(DEFAULT_POOL_VLAN_START);
        assertThat(testCentralSwitch.getPoolVlanEnd()).isEqualTo(DEFAULT_POOL_VLAN_END);
        assertThat(testCentralSwitch.getEndpoint()).isEqualTo(DEFAULT_ENDPOINT);
        assertThat(testCentralSwitch.getRestUsername()).isEqualTo(DEFAULT_REST_USERNAME);
        assertThat(testCentralSwitch.getRestPassword()).isEqualTo(DEFAULT_REST_PASSWORD);
        assertThat(testCentralSwitch.isRestEnabled()).isEqualTo(DEFAULT_REST_ENABLED);
        assertThat(testCentralSwitch.isAutoRollback()).isEqualTo(DEFAULT_AUTO_ROLLBACK);
        assertThat(testCentralSwitch.isFeign()).isEqualTo(DEFAULT_FEIGN);
    }

    @Test
    @Transactional
    public void createCentralSwitchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = centralSwitchRepository.findAll().size();

        // Create the CentralSwitch with an existing ID
        centralSwitch.setId(1L);
        CentralSwitchDTO centralSwitchDTO = centralSwitchMapper.toDto(centralSwitch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCentralSwitchMockMvc
            .perform(
                post("/api/central-switches")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centralSwitchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CentralSwitch in the database
        List<CentralSwitch> centralSwitchList = centralSwitchRepository.findAll();
        assertThat(centralSwitchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCentralSwitches() throws Exception {
        // Initialize the database
        centralSwitchRepository.saveAndFlush(centralSwitch);

        // Get all the centralSwitchList
        restCentralSwitchMockMvc
            .perform(get("/api/central-switches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(centralSwitch.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].hostId").value(hasItem(DEFAULT_HOST_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].hostName").value(hasItem(DEFAULT_HOST_NAME)))
            .andExpect(jsonPath("$.[*].configBackup").value(hasItem(DEFAULT_CONFIG_BACKUP.booleanValue())))
            .andExpect(jsonPath("$.[*].poolVlanStart").value(hasItem(DEFAULT_POOL_VLAN_START)))
            .andExpect(jsonPath("$.[*].poolVlanEnd").value(hasItem(DEFAULT_POOL_VLAN_END)))
            .andExpect(jsonPath("$.[*].endpoint").value(hasItem(DEFAULT_ENDPOINT)))
            .andExpect(jsonPath("$.[*].restUsername").value(hasItem(DEFAULT_REST_USERNAME)))
            .andExpect(jsonPath("$.[*].restPassword").value(hasItem(DEFAULT_REST_PASSWORD)))
            .andExpect(jsonPath("$.[*].restEnabled").value(hasItem(DEFAULT_REST_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].autoRollback").value(hasItem(DEFAULT_AUTO_ROLLBACK.booleanValue())))
            .andExpect(jsonPath("$.[*].feign").value(hasItem(DEFAULT_FEIGN.booleanValue())));
    }

    @Test
    @Transactional
    public void getCentralSwitch() throws Exception {
        // Initialize the database
        centralSwitchRepository.saveAndFlush(centralSwitch);

        // Get the centralSwitch
        restCentralSwitchMockMvc
            .perform(get("/api/central-switches/{id}", centralSwitch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(centralSwitch.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.hostId").value(DEFAULT_HOST_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.hostName").value(DEFAULT_HOST_NAME))
            .andExpect(jsonPath("$.configBackup").value(DEFAULT_CONFIG_BACKUP.booleanValue()))
            .andExpect(jsonPath("$.poolVlanStart").value(DEFAULT_POOL_VLAN_START))
            .andExpect(jsonPath("$.poolVlanEnd").value(DEFAULT_POOL_VLAN_END))
            .andExpect(jsonPath("$.endpoint").value(DEFAULT_ENDPOINT))
            .andExpect(jsonPath("$.restUsername").value(DEFAULT_REST_USERNAME))
            .andExpect(jsonPath("$.restPassword").value(DEFAULT_REST_PASSWORD))
            .andExpect(jsonPath("$.restEnabled").value(DEFAULT_REST_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.autoRollback").value(DEFAULT_AUTO_ROLLBACK.booleanValue()))
            .andExpect(jsonPath("$.feign").value(DEFAULT_FEIGN.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCentralSwitch() throws Exception {
        // Get the centralSwitch
        restCentralSwitchMockMvc.perform(get("/api/central-switches/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCentralSwitch() throws Exception {
        // Initialize the database
        centralSwitchRepository.saveAndFlush(centralSwitch);

        int databaseSizeBeforeUpdate = centralSwitchRepository.findAll().size();

        // Update the centralSwitch
        CentralSwitch updatedCentralSwitch = centralSwitchRepository.findById(centralSwitch.getId()).get();
        // Disconnect from session so that the updates on updatedCentralSwitch are not directly saved in db
        em.detach(updatedCentralSwitch);
        updatedCentralSwitch
            .name(UPDATED_NAME)
            .hostId(UPDATED_HOST_ID)
            .description(UPDATED_DESCRIPTION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .ipAddress(UPDATED_IP_ADDRESS)
            .companyName(UPDATED_COMPANY_NAME)
            .hostName(UPDATED_HOST_NAME)
            .configBackup(UPDATED_CONFIG_BACKUP)
            .poolVlanStart(UPDATED_POOL_VLAN_START)
            .poolVlanEnd(UPDATED_POOL_VLAN_END)
            .endpoint(UPDATED_ENDPOINT)
            .restUsername(UPDATED_REST_USERNAME)
            .restPassword(UPDATED_REST_PASSWORD)
            .restEnabled(UPDATED_REST_ENABLED)
            .autoRollback(UPDATED_AUTO_ROLLBACK)
            .feign(UPDATED_FEIGN);
        CentralSwitchDTO centralSwitchDTO = centralSwitchMapper.toDto(updatedCentralSwitch);

        restCentralSwitchMockMvc
            .perform(
                put("/api/central-switches")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centralSwitchDTO))
            )
            .andExpect(status().isOk());

        // Validate the CentralSwitch in the database
        List<CentralSwitch> centralSwitchList = centralSwitchRepository.findAll();
        assertThat(centralSwitchList).hasSize(databaseSizeBeforeUpdate);
        CentralSwitch testCentralSwitch = centralSwitchList.get(centralSwitchList.size() - 1);
        assertThat(testCentralSwitch.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCentralSwitch.getHostId()).isEqualTo(UPDATED_HOST_ID);
        assertThat(testCentralSwitch.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCentralSwitch.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testCentralSwitch.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testCentralSwitch.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testCentralSwitch.getHostName()).isEqualTo(UPDATED_HOST_NAME);
        assertThat(testCentralSwitch.isConfigBackup()).isEqualTo(UPDATED_CONFIG_BACKUP);
        assertThat(testCentralSwitch.getPoolVlanStart()).isEqualTo(UPDATED_POOL_VLAN_START);
        assertThat(testCentralSwitch.getPoolVlanEnd()).isEqualTo(UPDATED_POOL_VLAN_END);
        assertThat(testCentralSwitch.getEndpoint()).isEqualTo(UPDATED_ENDPOINT);
        assertThat(testCentralSwitch.getRestUsername()).isEqualTo(UPDATED_REST_USERNAME);
        assertThat(testCentralSwitch.getRestPassword()).isEqualTo(UPDATED_REST_PASSWORD);
        assertThat(testCentralSwitch.isRestEnabled()).isEqualTo(UPDATED_REST_ENABLED);
        assertThat(testCentralSwitch.isAutoRollback()).isEqualTo(UPDATED_AUTO_ROLLBACK);
        assertThat(testCentralSwitch.isFeign()).isEqualTo(UPDATED_FEIGN);
    }

    @Test
    @Transactional
    public void updateNonExistingCentralSwitch() throws Exception {
        int databaseSizeBeforeUpdate = centralSwitchRepository.findAll().size();

        // Create the CentralSwitch
        CentralSwitchDTO centralSwitchDTO = centralSwitchMapper.toDto(centralSwitch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCentralSwitchMockMvc
            .perform(
                put("/api/central-switches")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centralSwitchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CentralSwitch in the database
        List<CentralSwitch> centralSwitchList = centralSwitchRepository.findAll();
        assertThat(centralSwitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCentralSwitch() throws Exception {
        // Initialize the database
        centralSwitchRepository.saveAndFlush(centralSwitch);

        int databaseSizeBeforeDelete = centralSwitchRepository.findAll().size();

        // Delete the centralSwitch
        restCentralSwitchMockMvc
            .perform(delete("/api/central-switches/{id}", centralSwitch.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CentralSwitch> centralSwitchList = centralSwitchRepository.findAll();
        assertThat(centralSwitchList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
