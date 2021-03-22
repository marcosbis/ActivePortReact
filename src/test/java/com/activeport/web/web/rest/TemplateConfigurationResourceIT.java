package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.TemplateConfiguration;
import com.activeport.web.repository.TemplateConfigurationRepository;
import com.activeport.web.service.TemplateConfigurationService;
import com.activeport.web.service.dto.TemplateConfigurationDTO;
import com.activeport.web.service.mapper.TemplateConfigurationMapper;
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
 * Integration tests for the {@link TemplateConfigurationResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TemplateConfigurationResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIGURATION = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGURATION = "BBBBBBBBBB";

    @Autowired
    private TemplateConfigurationRepository templateConfigurationRepository;

    @Autowired
    private TemplateConfigurationMapper templateConfigurationMapper;

    @Autowired
    private TemplateConfigurationService templateConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTemplateConfigurationMockMvc;

    private TemplateConfiguration templateConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TemplateConfiguration createEntity(EntityManager em) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .configuration(DEFAULT_CONFIGURATION);
        return templateConfiguration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TemplateConfiguration createUpdatedEntity(EntityManager em) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .configuration(UPDATED_CONFIGURATION);
        return templateConfiguration;
    }

    @BeforeEach
    public void initTest() {
        templateConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createTemplateConfiguration() throws Exception {
        int databaseSizeBeforeCreate = templateConfigurationRepository.findAll().size();
        // Create the TemplateConfiguration
        TemplateConfigurationDTO templateConfigurationDTO = templateConfigurationMapper.toDto(templateConfiguration);
        restTemplateConfigurationMockMvc
            .perform(
                post("/api/template-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(templateConfigurationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TemplateConfiguration in the database
        List<TemplateConfiguration> templateConfigurationList = templateConfigurationRepository.findAll();
        assertThat(templateConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        TemplateConfiguration testTemplateConfiguration = templateConfigurationList.get(templateConfigurationList.size() - 1);
        assertThat(testTemplateConfiguration.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTemplateConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTemplateConfiguration.getConfiguration()).isEqualTo(DEFAULT_CONFIGURATION);
    }

    @Test
    @Transactional
    public void createTemplateConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = templateConfigurationRepository.findAll().size();

        // Create the TemplateConfiguration with an existing ID
        templateConfiguration.setId(1L);
        TemplateConfigurationDTO templateConfigurationDTO = templateConfigurationMapper.toDto(templateConfiguration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTemplateConfigurationMockMvc
            .perform(
                post("/api/template-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(templateConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TemplateConfiguration in the database
        List<TemplateConfiguration> templateConfigurationList = templateConfigurationRepository.findAll();
        assertThat(templateConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTemplateConfigurations() throws Exception {
        // Initialize the database
        templateConfigurationRepository.saveAndFlush(templateConfiguration);

        // Get all the templateConfigurationList
        restTemplateConfigurationMockMvc
            .perform(get("/api/template-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(templateConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].configuration").value(hasItem(DEFAULT_CONFIGURATION.toString())));
    }

    @Test
    @Transactional
    public void getTemplateConfiguration() throws Exception {
        // Initialize the database
        templateConfigurationRepository.saveAndFlush(templateConfiguration);

        // Get the templateConfiguration
        restTemplateConfigurationMockMvc
            .perform(get("/api/template-configurations/{id}", templateConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(templateConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.configuration").value(DEFAULT_CONFIGURATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTemplateConfiguration() throws Exception {
        // Get the templateConfiguration
        restTemplateConfigurationMockMvc.perform(get("/api/template-configurations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemplateConfiguration() throws Exception {
        // Initialize the database
        templateConfigurationRepository.saveAndFlush(templateConfiguration);

        int databaseSizeBeforeUpdate = templateConfigurationRepository.findAll().size();

        // Update the templateConfiguration
        TemplateConfiguration updatedTemplateConfiguration = templateConfigurationRepository.findById(templateConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedTemplateConfiguration are not directly saved in db
        em.detach(updatedTemplateConfiguration);
        updatedTemplateConfiguration.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).configuration(UPDATED_CONFIGURATION);
        TemplateConfigurationDTO templateConfigurationDTO = templateConfigurationMapper.toDto(updatedTemplateConfiguration);

        restTemplateConfigurationMockMvc
            .perform(
                put("/api/template-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(templateConfigurationDTO))
            )
            .andExpect(status().isOk());

        // Validate the TemplateConfiguration in the database
        List<TemplateConfiguration> templateConfigurationList = templateConfigurationRepository.findAll();
        assertThat(templateConfigurationList).hasSize(databaseSizeBeforeUpdate);
        TemplateConfiguration testTemplateConfiguration = templateConfigurationList.get(templateConfigurationList.size() - 1);
        assertThat(testTemplateConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTemplateConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTemplateConfiguration.getConfiguration()).isEqualTo(UPDATED_CONFIGURATION);
    }

    @Test
    @Transactional
    public void updateNonExistingTemplateConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = templateConfigurationRepository.findAll().size();

        // Create the TemplateConfiguration
        TemplateConfigurationDTO templateConfigurationDTO = templateConfigurationMapper.toDto(templateConfiguration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTemplateConfigurationMockMvc
            .perform(
                put("/api/template-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(templateConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TemplateConfiguration in the database
        List<TemplateConfiguration> templateConfigurationList = templateConfigurationRepository.findAll();
        assertThat(templateConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTemplateConfiguration() throws Exception {
        // Initialize the database
        templateConfigurationRepository.saveAndFlush(templateConfiguration);

        int databaseSizeBeforeDelete = templateConfigurationRepository.findAll().size();

        // Delete the templateConfiguration
        restTemplateConfigurationMockMvc
            .perform(delete("/api/template-configurations/{id}", templateConfiguration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TemplateConfiguration> templateConfigurationList = templateConfigurationRepository.findAll();
        assertThat(templateConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
