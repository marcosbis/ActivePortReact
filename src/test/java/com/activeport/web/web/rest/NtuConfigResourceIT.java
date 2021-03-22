package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.NtuConfig;
import com.activeport.web.repository.NtuConfigRepository;
import com.activeport.web.service.NtuConfigService;
import com.activeport.web.service.dto.NtuConfigDTO;
import com.activeport.web.service.mapper.NtuConfigMapper;
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
 * Integration tests for the {@link NtuConfigResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NtuConfigResourceIT {
    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRMWARE_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_FIRMWARE_VERSION = "BBBBBBBBBB";

    private static final Long DEFAULT_NTU_ID = 1L;
    private static final Long UPDATED_NTU_ID = 2L;

    @Autowired
    private NtuConfigRepository ntuConfigRepository;

    @Autowired
    private NtuConfigMapper ntuConfigMapper;

    @Autowired
    private NtuConfigService ntuConfigService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNtuConfigMockMvc;

    private NtuConfig ntuConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NtuConfig createEntity(EntityManager em) {
        NtuConfig ntuConfig = new NtuConfig()
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .name(DEFAULT_NAME)
            .firmwareVersion(DEFAULT_FIRMWARE_VERSION)
            .ntuId(DEFAULT_NTU_ID);
        return ntuConfig;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NtuConfig createUpdatedEntity(EntityManager em) {
        NtuConfig ntuConfig = new NtuConfig()
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .name(UPDATED_NAME)
            .firmwareVersion(UPDATED_FIRMWARE_VERSION)
            .ntuId(UPDATED_NTU_ID);
        return ntuConfig;
    }

    @BeforeEach
    public void initTest() {
        ntuConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createNtuConfig() throws Exception {
        int databaseSizeBeforeCreate = ntuConfigRepository.findAll().size();
        // Create the NtuConfig
        NtuConfigDTO ntuConfigDTO = ntuConfigMapper.toDto(ntuConfig);
        restNtuConfigMockMvc
            .perform(
                post("/api/ntu-configs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuConfigDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NtuConfig in the database
        List<NtuConfig> ntuConfigList = ntuConfigRepository.findAll();
        assertThat(ntuConfigList).hasSize(databaseSizeBeforeCreate + 1);
        NtuConfig testNtuConfig = ntuConfigList.get(ntuConfigList.size() - 1);
        assertThat(testNtuConfig.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testNtuConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNtuConfig.getFirmwareVersion()).isEqualTo(DEFAULT_FIRMWARE_VERSION);
        assertThat(testNtuConfig.getNtuId()).isEqualTo(DEFAULT_NTU_ID);
    }

    @Test
    @Transactional
    public void createNtuConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ntuConfigRepository.findAll().size();

        // Create the NtuConfig with an existing ID
        ntuConfig.setId(1L);
        NtuConfigDTO ntuConfigDTO = ntuConfigMapper.toDto(ntuConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNtuConfigMockMvc
            .perform(
                post("/api/ntu-configs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuConfigDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NtuConfig in the database
        List<NtuConfig> ntuConfigList = ntuConfigRepository.findAll();
        assertThat(ntuConfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNtuConfigs() throws Exception {
        // Initialize the database
        ntuConfigRepository.saveAndFlush(ntuConfig);

        // Get all the ntuConfigList
        restNtuConfigMockMvc
            .perform(get("/api/ntu-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ntuConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].firmwareVersion").value(hasItem(DEFAULT_FIRMWARE_VERSION)))
            .andExpect(jsonPath("$.[*].ntuId").value(hasItem(DEFAULT_NTU_ID.intValue())));
    }

    @Test
    @Transactional
    public void getNtuConfig() throws Exception {
        // Initialize the database
        ntuConfigRepository.saveAndFlush(ntuConfig);

        // Get the ntuConfig
        restNtuConfigMockMvc
            .perform(get("/api/ntu-configs/{id}", ntuConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ntuConfig.getId().intValue()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.firmwareVersion").value(DEFAULT_FIRMWARE_VERSION))
            .andExpect(jsonPath("$.ntuId").value(DEFAULT_NTU_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNtuConfig() throws Exception {
        // Get the ntuConfig
        restNtuConfigMockMvc.perform(get("/api/ntu-configs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNtuConfig() throws Exception {
        // Initialize the database
        ntuConfigRepository.saveAndFlush(ntuConfig);

        int databaseSizeBeforeUpdate = ntuConfigRepository.findAll().size();

        // Update the ntuConfig
        NtuConfig updatedNtuConfig = ntuConfigRepository.findById(ntuConfig.getId()).get();
        // Disconnect from session so that the updates on updatedNtuConfig are not directly saved in db
        em.detach(updatedNtuConfig);
        updatedNtuConfig
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .name(UPDATED_NAME)
            .firmwareVersion(UPDATED_FIRMWARE_VERSION)
            .ntuId(UPDATED_NTU_ID);
        NtuConfigDTO ntuConfigDTO = ntuConfigMapper.toDto(updatedNtuConfig);

        restNtuConfigMockMvc
            .perform(
                put("/api/ntu-configs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuConfigDTO))
            )
            .andExpect(status().isOk());

        // Validate the NtuConfig in the database
        List<NtuConfig> ntuConfigList = ntuConfigRepository.findAll();
        assertThat(ntuConfigList).hasSize(databaseSizeBeforeUpdate);
        NtuConfig testNtuConfig = ntuConfigList.get(ntuConfigList.size() - 1);
        assertThat(testNtuConfig.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testNtuConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNtuConfig.getFirmwareVersion()).isEqualTo(UPDATED_FIRMWARE_VERSION);
        assertThat(testNtuConfig.getNtuId()).isEqualTo(UPDATED_NTU_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingNtuConfig() throws Exception {
        int databaseSizeBeforeUpdate = ntuConfigRepository.findAll().size();

        // Create the NtuConfig
        NtuConfigDTO ntuConfigDTO = ntuConfigMapper.toDto(ntuConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNtuConfigMockMvc
            .perform(
                put("/api/ntu-configs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuConfigDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NtuConfig in the database
        List<NtuConfig> ntuConfigList = ntuConfigRepository.findAll();
        assertThat(ntuConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNtuConfig() throws Exception {
        // Initialize the database
        ntuConfigRepository.saveAndFlush(ntuConfig);

        int databaseSizeBeforeDelete = ntuConfigRepository.findAll().size();

        // Delete the ntuConfig
        restNtuConfigMockMvc
            .perform(delete("/api/ntu-configs/{id}", ntuConfig.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NtuConfig> ntuConfigList = ntuConfigRepository.findAll();
        assertThat(ntuConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
