package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ServiceCommand;
import com.activeport.web.domain.enumeration.EntityTypeEnum;
import com.activeport.web.domain.enumeration.FilterCommandTypeEnum;
import com.activeport.web.domain.enumeration.NtuSerieEnum;
import com.activeport.web.domain.enumeration.OnEventTypeEnum;
import com.activeport.web.domain.enumeration.OsTypeEnum;
import com.activeport.web.domain.enumeration.ServiceTypeEnum;
import com.activeport.web.repository.ServiceCommandRepository;
import com.activeport.web.service.ServiceCommandService;
import com.activeport.web.service.dto.ServiceCommandDTO;
import com.activeport.web.service.mapper.ServiceCommandMapper;
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
 * Integration tests for the {@link ServiceCommandResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServiceCommandResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMAND = "AAAAAAAAAA";
    private static final String UPDATED_COMMAND = "BBBBBBBBBB";

    private static final OnEventTypeEnum DEFAULT_ON_EVENT = OnEventTypeEnum.BEFORE_CREATE_SERVICE;
    private static final OnEventTypeEnum UPDATED_ON_EVENT = OnEventTypeEnum.CREATE_SERVICE;

    private static final ServiceTypeEnum DEFAULT_ON_SERVICE = ServiceTypeEnum.ANY;
    private static final ServiceTypeEnum UPDATED_ON_SERVICE = ServiceTypeEnum.AWS;

    private static final NtuSerieEnum DEFAULT_DEVICE_TYPE = NtuSerieEnum.NTU;
    private static final NtuSerieEnum UPDATED_DEVICE_TYPE = NtuSerieEnum.PE;

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final FilterCommandTypeEnum DEFAULT_CIRCUIT_TYPE = FilterCommandTypeEnum.CIRCUIT;
    private static final FilterCommandTypeEnum UPDATED_CIRCUIT_TYPE = FilterCommandTypeEnum.ROUTING_ROUTE;

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final OsTypeEnum DEFAULT_OS_TYPE = OsTypeEnum.JUNOS;
    private static final OsTypeEnum UPDATED_OS_TYPE = OsTypeEnum.MIKROTIK;

    private static final EntityTypeEnum DEFAULT_ENTRY_TYPE = EntityTypeEnum.SYSTEM;
    private static final EntityTypeEnum UPDATED_ENTRY_TYPE = EntityTypeEnum.SETTING;

    @Autowired
    private ServiceCommandRepository serviceCommandRepository;

    @Autowired
    private ServiceCommandMapper serviceCommandMapper;

    @Autowired
    private ServiceCommandService serviceCommandService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceCommandMockMvc;

    private ServiceCommand serviceCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceCommand createEntity(EntityManager em) {
        ServiceCommand serviceCommand = new ServiceCommand()
            .name(DEFAULT_NAME)
            .command(DEFAULT_COMMAND)
            .onEvent(DEFAULT_ON_EVENT)
            .onService(DEFAULT_ON_SERVICE)
            .deviceType(DEFAULT_DEVICE_TYPE)
            .enabled(DEFAULT_ENABLED)
            .circuitType(DEFAULT_CIRCUIT_TYPE)
            .tag(DEFAULT_TAG)
            .osType(DEFAULT_OS_TYPE)
            .entryType(DEFAULT_ENTRY_TYPE);
        return serviceCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceCommand createUpdatedEntity(EntityManager em) {
        ServiceCommand serviceCommand = new ServiceCommand()
            .name(UPDATED_NAME)
            .command(UPDATED_COMMAND)
            .onEvent(UPDATED_ON_EVENT)
            .onService(UPDATED_ON_SERVICE)
            .deviceType(UPDATED_DEVICE_TYPE)
            .enabled(UPDATED_ENABLED)
            .circuitType(UPDATED_CIRCUIT_TYPE)
            .tag(UPDATED_TAG)
            .osType(UPDATED_OS_TYPE)
            .entryType(UPDATED_ENTRY_TYPE);
        return serviceCommand;
    }

    @BeforeEach
    public void initTest() {
        serviceCommand = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceCommand() throws Exception {
        int databaseSizeBeforeCreate = serviceCommandRepository.findAll().size();
        // Create the ServiceCommand
        ServiceCommandDTO serviceCommandDTO = serviceCommandMapper.toDto(serviceCommand);
        restServiceCommandMockMvc
            .perform(
                post("/api/service-commands")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceCommand in the database
        List<ServiceCommand> serviceCommandList = serviceCommandRepository.findAll();
        assertThat(serviceCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceCommand testServiceCommand = serviceCommandList.get(serviceCommandList.size() - 1);
        assertThat(testServiceCommand.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServiceCommand.getCommand()).isEqualTo(DEFAULT_COMMAND);
        assertThat(testServiceCommand.getOnEvent()).isEqualTo(DEFAULT_ON_EVENT);
        assertThat(testServiceCommand.getOnService()).isEqualTo(DEFAULT_ON_SERVICE);
        assertThat(testServiceCommand.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testServiceCommand.isEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testServiceCommand.getCircuitType()).isEqualTo(DEFAULT_CIRCUIT_TYPE);
        assertThat(testServiceCommand.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testServiceCommand.getOsType()).isEqualTo(DEFAULT_OS_TYPE);
        assertThat(testServiceCommand.getEntryType()).isEqualTo(DEFAULT_ENTRY_TYPE);
    }

    @Test
    @Transactional
    public void createServiceCommandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceCommandRepository.findAll().size();

        // Create the ServiceCommand with an existing ID
        serviceCommand.setId(1L);
        ServiceCommandDTO serviceCommandDTO = serviceCommandMapper.toDto(serviceCommand);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceCommandMockMvc
            .perform(
                post("/api/service-commands")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceCommand in the database
        List<ServiceCommand> serviceCommandList = serviceCommandRepository.findAll();
        assertThat(serviceCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllServiceCommands() throws Exception {
        // Initialize the database
        serviceCommandRepository.saveAndFlush(serviceCommand);

        // Get all the serviceCommandList
        restServiceCommandMockMvc
            .perform(get("/api/service-commands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceCommand.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].command").value(hasItem(DEFAULT_COMMAND.toString())))
            .andExpect(jsonPath("$.[*].onEvent").value(hasItem(DEFAULT_ON_EVENT.toString())))
            .andExpect(jsonPath("$.[*].onService").value(hasItem(DEFAULT_ON_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].circuitType").value(hasItem(DEFAULT_CIRCUIT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].osType").value(hasItem(DEFAULT_OS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].entryType").value(hasItem(DEFAULT_ENTRY_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getServiceCommand() throws Exception {
        // Initialize the database
        serviceCommandRepository.saveAndFlush(serviceCommand);

        // Get the serviceCommand
        restServiceCommandMockMvc
            .perform(get("/api/service-commands/{id}", serviceCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceCommand.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.command").value(DEFAULT_COMMAND.toString()))
            .andExpect(jsonPath("$.onEvent").value(DEFAULT_ON_EVENT.toString()))
            .andExpect(jsonPath("$.onService").value(DEFAULT_ON_SERVICE.toString()))
            .andExpect(jsonPath("$.deviceType").value(DEFAULT_DEVICE_TYPE.toString()))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.circuitType").value(DEFAULT_CIRCUIT_TYPE.toString()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.osType").value(DEFAULT_OS_TYPE.toString()))
            .andExpect(jsonPath("$.entryType").value(DEFAULT_ENTRY_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingServiceCommand() throws Exception {
        // Get the serviceCommand
        restServiceCommandMockMvc.perform(get("/api/service-commands/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceCommand() throws Exception {
        // Initialize the database
        serviceCommandRepository.saveAndFlush(serviceCommand);

        int databaseSizeBeforeUpdate = serviceCommandRepository.findAll().size();

        // Update the serviceCommand
        ServiceCommand updatedServiceCommand = serviceCommandRepository.findById(serviceCommand.getId()).get();
        // Disconnect from session so that the updates on updatedServiceCommand are not directly saved in db
        em.detach(updatedServiceCommand);
        updatedServiceCommand
            .name(UPDATED_NAME)
            .command(UPDATED_COMMAND)
            .onEvent(UPDATED_ON_EVENT)
            .onService(UPDATED_ON_SERVICE)
            .deviceType(UPDATED_DEVICE_TYPE)
            .enabled(UPDATED_ENABLED)
            .circuitType(UPDATED_CIRCUIT_TYPE)
            .tag(UPDATED_TAG)
            .osType(UPDATED_OS_TYPE)
            .entryType(UPDATED_ENTRY_TYPE);
        ServiceCommandDTO serviceCommandDTO = serviceCommandMapper.toDto(updatedServiceCommand);

        restServiceCommandMockMvc
            .perform(
                put("/api/service-commands")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceCommand in the database
        List<ServiceCommand> serviceCommandList = serviceCommandRepository.findAll();
        assertThat(serviceCommandList).hasSize(databaseSizeBeforeUpdate);
        ServiceCommand testServiceCommand = serviceCommandList.get(serviceCommandList.size() - 1);
        assertThat(testServiceCommand.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServiceCommand.getCommand()).isEqualTo(UPDATED_COMMAND);
        assertThat(testServiceCommand.getOnEvent()).isEqualTo(UPDATED_ON_EVENT);
        assertThat(testServiceCommand.getOnService()).isEqualTo(UPDATED_ON_SERVICE);
        assertThat(testServiceCommand.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testServiceCommand.isEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testServiceCommand.getCircuitType()).isEqualTo(UPDATED_CIRCUIT_TYPE);
        assertThat(testServiceCommand.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testServiceCommand.getOsType()).isEqualTo(UPDATED_OS_TYPE);
        assertThat(testServiceCommand.getEntryType()).isEqualTo(UPDATED_ENTRY_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceCommand() throws Exception {
        int databaseSizeBeforeUpdate = serviceCommandRepository.findAll().size();

        // Create the ServiceCommand
        ServiceCommandDTO serviceCommandDTO = serviceCommandMapper.toDto(serviceCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceCommandMockMvc
            .perform(
                put("/api/service-commands")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceCommand in the database
        List<ServiceCommand> serviceCommandList = serviceCommandRepository.findAll();
        assertThat(serviceCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceCommand() throws Exception {
        // Initialize the database
        serviceCommandRepository.saveAndFlush(serviceCommand);

        int databaseSizeBeforeDelete = serviceCommandRepository.findAll().size();

        // Delete the serviceCommand
        restServiceCommandMockMvc
            .perform(delete("/api/service-commands/{id}", serviceCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceCommand> serviceCommandList = serviceCommandRepository.findAll();
        assertThat(serviceCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
