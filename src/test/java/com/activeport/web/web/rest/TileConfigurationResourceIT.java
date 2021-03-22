package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.TileConfiguration;
import com.activeport.web.repository.TileConfigurationRepository;
import com.activeport.web.service.TileConfigurationService;
import com.activeport.web.service.dto.TileConfigurationDTO;
import com.activeport.web.service.mapper.TileConfigurationMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link TileConfigurationResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TileConfigurationResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMAND = "AAAAAAAAAA";
    private static final String UPDATED_COMMAND = "BBBBBBBBBB";

    @Autowired
    private TileConfigurationRepository tileConfigurationRepository;

    @Mock
    private TileConfigurationRepository tileConfigurationRepositoryMock;

    @Autowired
    private TileConfigurationMapper tileConfigurationMapper;

    @Mock
    private TileConfigurationService tileConfigurationServiceMock;

    @Autowired
    private TileConfigurationService tileConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTileConfigurationMockMvc;

    private TileConfiguration tileConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TileConfiguration createEntity(EntityManager em) {
        TileConfiguration tileConfiguration = new TileConfiguration()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .command(DEFAULT_COMMAND);
        return tileConfiguration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TileConfiguration createUpdatedEntity(EntityManager em) {
        TileConfiguration tileConfiguration = new TileConfiguration()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .command(UPDATED_COMMAND);
        return tileConfiguration;
    }

    @BeforeEach
    public void initTest() {
        tileConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createTileConfiguration() throws Exception {
        int databaseSizeBeforeCreate = tileConfigurationRepository.findAll().size();
        // Create the TileConfiguration
        TileConfigurationDTO tileConfigurationDTO = tileConfigurationMapper.toDto(tileConfiguration);
        restTileConfigurationMockMvc
            .perform(
                post("/api/tile-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileConfigurationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TileConfiguration in the database
        List<TileConfiguration> tileConfigurationList = tileConfigurationRepository.findAll();
        assertThat(tileConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        TileConfiguration testTileConfiguration = tileConfigurationList.get(tileConfigurationList.size() - 1);
        assertThat(testTileConfiguration.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTileConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTileConfiguration.getCommand()).isEqualTo(DEFAULT_COMMAND);
    }

    @Test
    @Transactional
    public void createTileConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tileConfigurationRepository.findAll().size();

        // Create the TileConfiguration with an existing ID
        tileConfiguration.setId(1L);
        TileConfigurationDTO tileConfigurationDTO = tileConfigurationMapper.toDto(tileConfiguration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTileConfigurationMockMvc
            .perform(
                post("/api/tile-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TileConfiguration in the database
        List<TileConfiguration> tileConfigurationList = tileConfigurationRepository.findAll();
        assertThat(tileConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTileConfigurations() throws Exception {
        // Initialize the database
        tileConfigurationRepository.saveAndFlush(tileConfiguration);

        // Get all the tileConfigurationList
        restTileConfigurationMockMvc
            .perform(get("/api/tile-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tileConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].command").value(hasItem(DEFAULT_COMMAND.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllTileConfigurationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(tileConfigurationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTileConfigurationMockMvc.perform(get("/api/tile-configurations?eagerload=true")).andExpect(status().isOk());

        verify(tileConfigurationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllTileConfigurationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(tileConfigurationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTileConfigurationMockMvc.perform(get("/api/tile-configurations?eagerload=true")).andExpect(status().isOk());

        verify(tileConfigurationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getTileConfiguration() throws Exception {
        // Initialize the database
        tileConfigurationRepository.saveAndFlush(tileConfiguration);

        // Get the tileConfiguration
        restTileConfigurationMockMvc
            .perform(get("/api/tile-configurations/{id}", tileConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tileConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.command").value(DEFAULT_COMMAND.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTileConfiguration() throws Exception {
        // Get the tileConfiguration
        restTileConfigurationMockMvc.perform(get("/api/tile-configurations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTileConfiguration() throws Exception {
        // Initialize the database
        tileConfigurationRepository.saveAndFlush(tileConfiguration);

        int databaseSizeBeforeUpdate = tileConfigurationRepository.findAll().size();

        // Update the tileConfiguration
        TileConfiguration updatedTileConfiguration = tileConfigurationRepository.findById(tileConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedTileConfiguration are not directly saved in db
        em.detach(updatedTileConfiguration);
        updatedTileConfiguration.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).command(UPDATED_COMMAND);
        TileConfigurationDTO tileConfigurationDTO = tileConfigurationMapper.toDto(updatedTileConfiguration);

        restTileConfigurationMockMvc
            .perform(
                put("/api/tile-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileConfigurationDTO))
            )
            .andExpect(status().isOk());

        // Validate the TileConfiguration in the database
        List<TileConfiguration> tileConfigurationList = tileConfigurationRepository.findAll();
        assertThat(tileConfigurationList).hasSize(databaseSizeBeforeUpdate);
        TileConfiguration testTileConfiguration = tileConfigurationList.get(tileConfigurationList.size() - 1);
        assertThat(testTileConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTileConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTileConfiguration.getCommand()).isEqualTo(UPDATED_COMMAND);
    }

    @Test
    @Transactional
    public void updateNonExistingTileConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = tileConfigurationRepository.findAll().size();

        // Create the TileConfiguration
        TileConfigurationDTO tileConfigurationDTO = tileConfigurationMapper.toDto(tileConfiguration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTileConfigurationMockMvc
            .perform(
                put("/api/tile-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tileConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TileConfiguration in the database
        List<TileConfiguration> tileConfigurationList = tileConfigurationRepository.findAll();
        assertThat(tileConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTileConfiguration() throws Exception {
        // Initialize the database
        tileConfigurationRepository.saveAndFlush(tileConfiguration);

        int databaseSizeBeforeDelete = tileConfigurationRepository.findAll().size();

        // Delete the tileConfiguration
        restTileConfigurationMockMvc
            .perform(delete("/api/tile-configurations/{id}", tileConfiguration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TileConfiguration> tileConfigurationList = tileConfigurationRepository.findAll();
        assertThat(tileConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
