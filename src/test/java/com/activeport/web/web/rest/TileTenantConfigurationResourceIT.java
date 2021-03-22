package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.TileTenantConfiguration;
import com.activeport.web.repository.TileTenantConfigurationRepository;
import com.activeport.web.service.TileTenantConfigurationService;
import com.activeport.web.service.dto.TileTenantConfigurationDTO;
import com.activeport.web.service.mapper.TileTenantConfigurationMapper;
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
 * Integration tests for the {@link TileTenantConfigurationResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TileTenantConfigurationResourceIT {
    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ID = "BBBBBBBBBB";

    @Autowired
    private TileTenantConfigurationRepository tileTenantConfigurationRepository;

    @Autowired
    private TileTenantConfigurationMapper tileTenantConfigurationMapper;

    @Autowired
    private TileTenantConfigurationService tileTenantConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTileTenantConfigurationMockMvc;

    private TileTenantConfiguration tileTenantConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TileTenantConfiguration createEntity(EntityManager em) {
        TileTenantConfiguration tileTenantConfiguration = new TileTenantConfiguration().tenantId(DEFAULT_TENANT_ID).orgId(DEFAULT_ORG_ID);
        return tileTenantConfiguration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TileTenantConfiguration createUpdatedEntity(EntityManager em) {
        TileTenantConfiguration tileTenantConfiguration = new TileTenantConfiguration().tenantId(UPDATED_TENANT_ID).orgId(UPDATED_ORG_ID);
        return tileTenantConfiguration;
    }

    @BeforeEach
    public void initTest() {
        tileTenantConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createTileTenantConfiguration() throws Exception {
        int databaseSizeBeforeCreate = tileTenantConfigurationRepository.findAll().size();
        // Create the TileTenantConfiguration
        TileTenantConfigurationDTO tileTenantConfigurationDTO = tileTenantConfigurationMapper.toDto(tileTenantConfiguration);
        restTileTenantConfigurationMockMvc
            .perform(
                post("/api/tile-tenant-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileTenantConfigurationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TileTenantConfiguration in the database
        List<TileTenantConfiguration> tileTenantConfigurationList = tileTenantConfigurationRepository.findAll();
        assertThat(tileTenantConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        TileTenantConfiguration testTileTenantConfiguration = tileTenantConfigurationList.get(tileTenantConfigurationList.size() - 1);
        assertThat(testTileTenantConfiguration.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testTileTenantConfiguration.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
    }

    @Test
    @Transactional
    public void createTileTenantConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tileTenantConfigurationRepository.findAll().size();

        // Create the TileTenantConfiguration with an existing ID
        tileTenantConfiguration.setId(1L);
        TileTenantConfigurationDTO tileTenantConfigurationDTO = tileTenantConfigurationMapper.toDto(tileTenantConfiguration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTileTenantConfigurationMockMvc
            .perform(
                post("/api/tile-tenant-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileTenantConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TileTenantConfiguration in the database
        List<TileTenantConfiguration> tileTenantConfigurationList = tileTenantConfigurationRepository.findAll();
        assertThat(tileTenantConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTileTenantConfigurations() throws Exception {
        // Initialize the database
        tileTenantConfigurationRepository.saveAndFlush(tileTenantConfiguration);

        // Get all the tileTenantConfigurationList
        restTileTenantConfigurationMockMvc
            .perform(get("/api/tile-tenant-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tileTenantConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)))
            .andExpect(jsonPath("$.[*].orgId").value(hasItem(DEFAULT_ORG_ID)));
    }

    @Test
    @Transactional
    public void getTileTenantConfiguration() throws Exception {
        // Initialize the database
        tileTenantConfigurationRepository.saveAndFlush(tileTenantConfiguration);

        // Get the tileTenantConfiguration
        restTileTenantConfigurationMockMvc
            .perform(get("/api/tile-tenant-configurations/{id}", tileTenantConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tileTenantConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID))
            .andExpect(jsonPath("$.orgId").value(DEFAULT_ORG_ID));
    }

    @Test
    @Transactional
    public void getNonExistingTileTenantConfiguration() throws Exception {
        // Get the tileTenantConfiguration
        restTileTenantConfigurationMockMvc
            .perform(get("/api/tile-tenant-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTileTenantConfiguration() throws Exception {
        // Initialize the database
        tileTenantConfigurationRepository.saveAndFlush(tileTenantConfiguration);

        int databaseSizeBeforeUpdate = tileTenantConfigurationRepository.findAll().size();

        // Update the tileTenantConfiguration
        TileTenantConfiguration updatedTileTenantConfiguration = tileTenantConfigurationRepository
            .findById(tileTenantConfiguration.getId())
            .get();
        // Disconnect from session so that the updates on updatedTileTenantConfiguration are not directly saved in db
        em.detach(updatedTileTenantConfiguration);
        updatedTileTenantConfiguration.tenantId(UPDATED_TENANT_ID).orgId(UPDATED_ORG_ID);
        TileTenantConfigurationDTO tileTenantConfigurationDTO = tileTenantConfigurationMapper.toDto(updatedTileTenantConfiguration);

        restTileTenantConfigurationMockMvc
            .perform(
                put("/api/tile-tenant-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileTenantConfigurationDTO))
            )
            .andExpect(status().isOk());

        // Validate the TileTenantConfiguration in the database
        List<TileTenantConfiguration> tileTenantConfigurationList = tileTenantConfigurationRepository.findAll();
        assertThat(tileTenantConfigurationList).hasSize(databaseSizeBeforeUpdate);
        TileTenantConfiguration testTileTenantConfiguration = tileTenantConfigurationList.get(tileTenantConfigurationList.size() - 1);
        assertThat(testTileTenantConfiguration.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testTileTenantConfiguration.getOrgId()).isEqualTo(UPDATED_ORG_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingTileTenantConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = tileTenantConfigurationRepository.findAll().size();

        // Create the TileTenantConfiguration
        TileTenantConfigurationDTO tileTenantConfigurationDTO = tileTenantConfigurationMapper.toDto(tileTenantConfiguration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTileTenantConfigurationMockMvc
            .perform(
                put("/api/tile-tenant-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileTenantConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TileTenantConfiguration in the database
        List<TileTenantConfiguration> tileTenantConfigurationList = tileTenantConfigurationRepository.findAll();
        assertThat(tileTenantConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTileTenantConfiguration() throws Exception {
        // Initialize the database
        tileTenantConfigurationRepository.saveAndFlush(tileTenantConfiguration);

        int databaseSizeBeforeDelete = tileTenantConfigurationRepository.findAll().size();

        // Delete the tileTenantConfiguration
        restTileTenantConfigurationMockMvc
            .perform(delete("/api/tile-tenant-configurations/{id}", tileTenantConfiguration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TileTenantConfiguration> tileTenantConfigurationList = tileTenantConfigurationRepository.findAll();
        assertThat(tileTenantConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
