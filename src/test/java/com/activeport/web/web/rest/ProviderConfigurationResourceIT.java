package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ProviderConfiguration;
import com.activeport.web.domain.enumeration.ApiTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.activeport.web.repository.ProviderConfigurationRepository;
import com.activeport.web.service.ProviderConfigurationService;
import com.activeport.web.service.dto.ProviderConfigurationDTO;
import com.activeport.web.service.mapper.ProviderConfigurationMapper;
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

/**
 * Integration tests for the {@link ProviderConfigurationResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProviderConfigurationResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final PartnerTypeEnum DEFAULT_TYPE = PartnerTypeEnum.MEGAPORT;
    private static final PartnerTypeEnum UPDATED_TYPE = PartnerTypeEnum.ISP;

    private static final ApiTypeEnum DEFAULT_API_TYPE = ApiTypeEnum.NONE;
    private static final ApiTypeEnum UPDATED_API_TYPE = ApiTypeEnum.CLIENT;

    private static final Boolean DEFAULT_HAS_PORT_ID = false;
    private static final Boolean UPDATED_HAS_PORT_ID = true;

    @Autowired
    private ProviderConfigurationRepository providerConfigurationRepository;

    @Mock
    private ProviderConfigurationRepository providerConfigurationRepositoryMock;

    @Autowired
    private ProviderConfigurationMapper providerConfigurationMapper;

    @Mock
    private ProviderConfigurationService providerConfigurationServiceMock;

    @Autowired
    private ProviderConfigurationService providerConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProviderConfigurationMockMvc;

    private ProviderConfiguration providerConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProviderConfiguration createEntity(EntityManager em) {
        ProviderConfiguration providerConfiguration = new ProviderConfiguration()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .apiType(DEFAULT_API_TYPE)
            .hasPortId(DEFAULT_HAS_PORT_ID);
        return providerConfiguration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProviderConfiguration createUpdatedEntity(EntityManager em) {
        ProviderConfiguration providerConfiguration = new ProviderConfiguration()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .apiType(UPDATED_API_TYPE)
            .hasPortId(UPDATED_HAS_PORT_ID);
        return providerConfiguration;
    }

    @BeforeEach
    public void initTest() {
        providerConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createProviderConfiguration() throws Exception {
        int databaseSizeBeforeCreate = providerConfigurationRepository.findAll().size();
        // Create the ProviderConfiguration
        ProviderConfigurationDTO providerConfigurationDTO = providerConfigurationMapper.toDto(providerConfiguration);
        restProviderConfigurationMockMvc
            .perform(
                post("/api/provider-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerConfigurationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProviderConfiguration in the database
        List<ProviderConfiguration> providerConfigurationList = providerConfigurationRepository.findAll();
        assertThat(providerConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        ProviderConfiguration testProviderConfiguration = providerConfigurationList.get(providerConfigurationList.size() - 1);
        assertThat(testProviderConfiguration.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProviderConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProviderConfiguration.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProviderConfiguration.getApiType()).isEqualTo(DEFAULT_API_TYPE);
        assertThat(testProviderConfiguration.isHasPortId()).isEqualTo(DEFAULT_HAS_PORT_ID);
    }

    @Test
    @Transactional
    public void createProviderConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providerConfigurationRepository.findAll().size();

        // Create the ProviderConfiguration with an existing ID
        providerConfiguration.setId(1L);
        ProviderConfigurationDTO providerConfigurationDTO = providerConfigurationMapper.toDto(providerConfiguration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProviderConfigurationMockMvc
            .perform(
                post("/api/provider-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProviderConfiguration in the database
        List<ProviderConfiguration> providerConfigurationList = providerConfigurationRepository.findAll();
        assertThat(providerConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerConfigurationRepository.findAll().size();
        // set the field null
        providerConfiguration.setType(null);

        // Create the ProviderConfiguration, which fails.
        ProviderConfigurationDTO providerConfigurationDTO = providerConfigurationMapper.toDto(providerConfiguration);

        restProviderConfigurationMockMvc
            .perform(
                post("/api/provider-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ProviderConfiguration> providerConfigurationList = providerConfigurationRepository.findAll();
        assertThat(providerConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProviderConfigurations() throws Exception {
        // Initialize the database
        providerConfigurationRepository.saveAndFlush(providerConfiguration);

        // Get all the providerConfigurationList
        restProviderConfigurationMockMvc
            .perform(get("/api/provider-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(providerConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].apiType").value(hasItem(DEFAULT_API_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hasPortId").value(hasItem(DEFAULT_HAS_PORT_ID.booleanValue())));
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllProviderConfigurationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(providerConfigurationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProviderConfigurationMockMvc.perform(get("/api/provider-configurations?eagerload=true")).andExpect(status().isOk());

        verify(providerConfigurationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllProviderConfigurationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(providerConfigurationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProviderConfigurationMockMvc.perform(get("/api/provider-configurations?eagerload=true")).andExpect(status().isOk());

        verify(providerConfigurationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getProviderConfiguration() throws Exception {
        // Initialize the database
        providerConfigurationRepository.saveAndFlush(providerConfiguration);

        // Get the providerConfiguration
        restProviderConfigurationMockMvc
            .perform(get("/api/provider-configurations/{id}", providerConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(providerConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.apiType").value(DEFAULT_API_TYPE.toString()))
            .andExpect(jsonPath("$.hasPortId").value(DEFAULT_HAS_PORT_ID.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProviderConfiguration() throws Exception {
        // Get the providerConfiguration
        restProviderConfigurationMockMvc.perform(get("/api/provider-configurations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProviderConfiguration() throws Exception {
        // Initialize the database
        providerConfigurationRepository.saveAndFlush(providerConfiguration);

        int databaseSizeBeforeUpdate = providerConfigurationRepository.findAll().size();

        // Update the providerConfiguration
        ProviderConfiguration updatedProviderConfiguration = providerConfigurationRepository.findById(providerConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedProviderConfiguration are not directly saved in db
        em.detach(updatedProviderConfiguration);
        updatedProviderConfiguration
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .apiType(UPDATED_API_TYPE)
            .hasPortId(UPDATED_HAS_PORT_ID);
        ProviderConfigurationDTO providerConfigurationDTO = providerConfigurationMapper.toDto(updatedProviderConfiguration);

        restProviderConfigurationMockMvc
            .perform(
                put("/api/provider-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerConfigurationDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProviderConfiguration in the database
        List<ProviderConfiguration> providerConfigurationList = providerConfigurationRepository.findAll();
        assertThat(providerConfigurationList).hasSize(databaseSizeBeforeUpdate);
        ProviderConfiguration testProviderConfiguration = providerConfigurationList.get(providerConfigurationList.size() - 1);
        assertThat(testProviderConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProviderConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProviderConfiguration.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProviderConfiguration.getApiType()).isEqualTo(UPDATED_API_TYPE);
        assertThat(testProviderConfiguration.isHasPortId()).isEqualTo(UPDATED_HAS_PORT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingProviderConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = providerConfigurationRepository.findAll().size();

        // Create the ProviderConfiguration
        ProviderConfigurationDTO providerConfigurationDTO = providerConfigurationMapper.toDto(providerConfiguration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProviderConfigurationMockMvc
            .perform(
                put("/api/provider-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProviderConfiguration in the database
        List<ProviderConfiguration> providerConfigurationList = providerConfigurationRepository.findAll();
        assertThat(providerConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProviderConfiguration() throws Exception {
        // Initialize the database
        providerConfigurationRepository.saveAndFlush(providerConfiguration);

        int databaseSizeBeforeDelete = providerConfigurationRepository.findAll().size();

        // Delete the providerConfiguration
        restProviderConfigurationMockMvc
            .perform(delete("/api/provider-configurations/{id}", providerConfiguration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProviderConfiguration> providerConfigurationList = providerConfigurationRepository.findAll();
        assertThat(providerConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
