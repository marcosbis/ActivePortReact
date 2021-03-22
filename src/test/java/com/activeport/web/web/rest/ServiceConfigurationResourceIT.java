package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ServiceConfiguration;
import com.activeport.web.domain.enumeration.TenantTypeEnum;
import com.activeport.web.repository.ServiceConfigurationRepository;
import com.activeport.web.service.ServiceConfigurationService;
import com.activeport.web.service.dto.ServiceConfigurationDTO;
import com.activeport.web.service.mapper.ServiceConfigurationMapper;
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
 * Integration tests for the {@link ServiceConfigurationResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServiceConfigurationResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final TenantTypeEnum DEFAULT_TENANT_TYPE = TenantTypeEnum.ALL;
    private static final TenantTypeEnum UPDATED_TENANT_TYPE = TenantTypeEnum.SINGLE;

    private static final String DEFAULT_COMMAND = "AAAAAAAAAA";
    private static final String UPDATED_COMMAND = "BBBBBBBBBB";

    private static final String DEFAULT_TEST = "AAAAAAAAAA";
    private static final String UPDATED_TEST = "BBBBBBBBBB";

    private static final Boolean DEFAULT_USE_DEFAULT_COMMANDS = false;
    private static final Boolean UPDATED_USE_DEFAULT_COMMANDS = true;

    @Autowired
    private ServiceConfigurationRepository serviceConfigurationRepository;

    @Mock
    private ServiceConfigurationRepository serviceConfigurationRepositoryMock;

    @Autowired
    private ServiceConfigurationMapper serviceConfigurationMapper;

    @Mock
    private ServiceConfigurationService serviceConfigurationServiceMock;

    @Autowired
    private ServiceConfigurationService serviceConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceConfigurationMockMvc;

    private ServiceConfiguration serviceConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceConfiguration createEntity(EntityManager em) {
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .tenantType(DEFAULT_TENANT_TYPE)
            .command(DEFAULT_COMMAND)
            .test(DEFAULT_TEST)
            .useDefaultCommands(DEFAULT_USE_DEFAULT_COMMANDS);
        return serviceConfiguration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceConfiguration createUpdatedEntity(EntityManager em) {
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tenantType(UPDATED_TENANT_TYPE)
            .command(UPDATED_COMMAND)
            .test(UPDATED_TEST)
            .useDefaultCommands(UPDATED_USE_DEFAULT_COMMANDS);
        return serviceConfiguration;
    }

    @BeforeEach
    public void initTest() {
        serviceConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceConfiguration() throws Exception {
        int databaseSizeBeforeCreate = serviceConfigurationRepository.findAll().size();
        // Create the ServiceConfiguration
        ServiceConfigurationDTO serviceConfigurationDTO = serviceConfigurationMapper.toDto(serviceConfiguration);
        restServiceConfigurationMockMvc
            .perform(
                post("/api/service-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceConfigurationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceConfiguration in the database
        List<ServiceConfiguration> serviceConfigurationList = serviceConfigurationRepository.findAll();
        assertThat(serviceConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceConfiguration testServiceConfiguration = serviceConfigurationList.get(serviceConfigurationList.size() - 1);
        assertThat(testServiceConfiguration.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServiceConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testServiceConfiguration.getTenantType()).isEqualTo(DEFAULT_TENANT_TYPE);
        assertThat(testServiceConfiguration.getCommand()).isEqualTo(DEFAULT_COMMAND);
        assertThat(testServiceConfiguration.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testServiceConfiguration.isUseDefaultCommands()).isEqualTo(DEFAULT_USE_DEFAULT_COMMANDS);
    }

    @Test
    @Transactional
    public void createServiceConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceConfigurationRepository.findAll().size();

        // Create the ServiceConfiguration with an existing ID
        serviceConfiguration.setId(1L);
        ServiceConfigurationDTO serviceConfigurationDTO = serviceConfigurationMapper.toDto(serviceConfiguration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceConfigurationMockMvc
            .perform(
                post("/api/service-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceConfiguration in the database
        List<ServiceConfiguration> serviceConfigurationList = serviceConfigurationRepository.findAll();
        assertThat(serviceConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllServiceConfigurations() throws Exception {
        // Initialize the database
        serviceConfigurationRepository.saveAndFlush(serviceConfiguration);

        // Get all the serviceConfigurationList
        restServiceConfigurationMockMvc
            .perform(get("/api/service-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tenantType").value(hasItem(DEFAULT_TENANT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].command").value(hasItem(DEFAULT_COMMAND.toString())))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST)))
            .andExpect(jsonPath("$.[*].useDefaultCommands").value(hasItem(DEFAULT_USE_DEFAULT_COMMANDS.booleanValue())));
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllServiceConfigurationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(serviceConfigurationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restServiceConfigurationMockMvc.perform(get("/api/service-configurations?eagerload=true")).andExpect(status().isOk());

        verify(serviceConfigurationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllServiceConfigurationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(serviceConfigurationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restServiceConfigurationMockMvc.perform(get("/api/service-configurations?eagerload=true")).andExpect(status().isOk());

        verify(serviceConfigurationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getServiceConfiguration() throws Exception {
        // Initialize the database
        serviceConfigurationRepository.saveAndFlush(serviceConfiguration);

        // Get the serviceConfiguration
        restServiceConfigurationMockMvc
            .perform(get("/api/service-configurations/{id}", serviceConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tenantType").value(DEFAULT_TENANT_TYPE.toString()))
            .andExpect(jsonPath("$.command").value(DEFAULT_COMMAND.toString()))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST))
            .andExpect(jsonPath("$.useDefaultCommands").value(DEFAULT_USE_DEFAULT_COMMANDS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingServiceConfiguration() throws Exception {
        // Get the serviceConfiguration
        restServiceConfigurationMockMvc.perform(get("/api/service-configurations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceConfiguration() throws Exception {
        // Initialize the database
        serviceConfigurationRepository.saveAndFlush(serviceConfiguration);

        int databaseSizeBeforeUpdate = serviceConfigurationRepository.findAll().size();

        // Update the serviceConfiguration
        ServiceConfiguration updatedServiceConfiguration = serviceConfigurationRepository.findById(serviceConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedServiceConfiguration are not directly saved in db
        em.detach(updatedServiceConfiguration);
        updatedServiceConfiguration
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tenantType(UPDATED_TENANT_TYPE)
            .command(UPDATED_COMMAND)
            .test(UPDATED_TEST)
            .useDefaultCommands(UPDATED_USE_DEFAULT_COMMANDS);
        ServiceConfigurationDTO serviceConfigurationDTO = serviceConfigurationMapper.toDto(updatedServiceConfiguration);

        restServiceConfigurationMockMvc
            .perform(
                put("/api/service-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceConfigurationDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceConfiguration in the database
        List<ServiceConfiguration> serviceConfigurationList = serviceConfigurationRepository.findAll();
        assertThat(serviceConfigurationList).hasSize(databaseSizeBeforeUpdate);
        ServiceConfiguration testServiceConfiguration = serviceConfigurationList.get(serviceConfigurationList.size() - 1);
        assertThat(testServiceConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServiceConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testServiceConfiguration.getTenantType()).isEqualTo(UPDATED_TENANT_TYPE);
        assertThat(testServiceConfiguration.getCommand()).isEqualTo(UPDATED_COMMAND);
        assertThat(testServiceConfiguration.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testServiceConfiguration.isUseDefaultCommands()).isEqualTo(UPDATED_USE_DEFAULT_COMMANDS);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = serviceConfigurationRepository.findAll().size();

        // Create the ServiceConfiguration
        ServiceConfigurationDTO serviceConfigurationDTO = serviceConfigurationMapper.toDto(serviceConfiguration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceConfigurationMockMvc
            .perform(
                put("/api/service-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceConfiguration in the database
        List<ServiceConfiguration> serviceConfigurationList = serviceConfigurationRepository.findAll();
        assertThat(serviceConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceConfiguration() throws Exception {
        // Initialize the database
        serviceConfigurationRepository.saveAndFlush(serviceConfiguration);

        int databaseSizeBeforeDelete = serviceConfigurationRepository.findAll().size();

        // Delete the serviceConfiguration
        restServiceConfigurationMockMvc
            .perform(delete("/api/service-configurations/{id}", serviceConfiguration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceConfiguration> serviceConfigurationList = serviceConfigurationRepository.findAll();
        assertThat(serviceConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
