package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.DeviceConfiguration;
import com.activeport.web.domain.enumeration.AddressSetupTypeEnum;
import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import com.activeport.web.repository.DeviceConfigurationRepository;
import com.activeport.web.service.DeviceConfigurationService;
import com.activeport.web.service.dto.DeviceConfigurationDTO;
import com.activeport.web.service.mapper.DeviceConfigurationMapper;
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
 * Integration tests for the {@link DeviceConfigurationResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DeviceConfigurationResourceIT {
    private static final String DEFAULT_UID = "AAAAAAAAAA";
    private static final String UPDATED_UID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_HOST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LO_IP = "AAAAAAAAAA";
    private static final String UPDATED_LO_IP = "BBBBBBBBBB";

    private static final String DEFAULT_FIRMWARE_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_FIRMWARE_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_REST_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_REST_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_REST_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_REST_PASSWORD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REST_ENABLED = false;
    private static final Boolean UPDATED_REST_ENABLED = true;

    private static final NtuModeEnum DEFAULT_MODE = NtuModeEnum.DEMO;
    private static final NtuModeEnum UPDATED_MODE = NtuModeEnum.EDGE;

    private static final Integer DEFAULT_DEFAULT_RATE = 1;
    private static final Integer UPDATED_DEFAULT_RATE = 2;

    private static final String DEFAULT_SUBNET = "AAAAAAAAAA";
    private static final String UPDATED_SUBNET = "BBBBBBBBBB";

    private static final PortServiceTypeEnum DEFAULT_DEVICE_TYPE = PortServiceTypeEnum.EDGE;
    private static final PortServiceTypeEnum UPDATED_DEVICE_TYPE = PortServiceTypeEnum.SWITCH;

    private static final AddressSetupTypeEnum DEFAULT_ADDRESS_SETUP_TYPE = AddressSetupTypeEnum.FIRST;
    private static final AddressSetupTypeEnum UPDATED_ADDRESS_SETUP_TYPE = AddressSetupTypeEnum.LAST;

    @Autowired
    private DeviceConfigurationRepository deviceConfigurationRepository;

    @Autowired
    private DeviceConfigurationMapper deviceConfigurationMapper;

    @Autowired
    private DeviceConfigurationService deviceConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeviceConfigurationMockMvc;

    private DeviceConfiguration deviceConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviceConfiguration createEntity(EntityManager em) {
        DeviceConfiguration deviceConfiguration = new DeviceConfiguration()
            .uid(DEFAULT_UID)
            .description(DEFAULT_DESCRIPTION)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .hostName(DEFAULT_HOST_NAME)
            .loIp(DEFAULT_LO_IP)
            .firmwareVersion(DEFAULT_FIRMWARE_VERSION)
            .endpoint(DEFAULT_ENDPOINT)
            .restUsername(DEFAULT_REST_USERNAME)
            .restPassword(DEFAULT_REST_PASSWORD)
            .restEnabled(DEFAULT_REST_ENABLED)
            .mode(DEFAULT_MODE)
            .defaultRate(DEFAULT_DEFAULT_RATE)
            .subnet(DEFAULT_SUBNET)
            .deviceType(DEFAULT_DEVICE_TYPE)
            .addressSetupType(DEFAULT_ADDRESS_SETUP_TYPE);
        return deviceConfiguration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviceConfiguration createUpdatedEntity(EntityManager em) {
        DeviceConfiguration deviceConfiguration = new DeviceConfiguration()
            .uid(UPDATED_UID)
            .description(UPDATED_DESCRIPTION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .hostName(UPDATED_HOST_NAME)
            .loIp(UPDATED_LO_IP)
            .firmwareVersion(UPDATED_FIRMWARE_VERSION)
            .endpoint(UPDATED_ENDPOINT)
            .restUsername(UPDATED_REST_USERNAME)
            .restPassword(UPDATED_REST_PASSWORD)
            .restEnabled(UPDATED_REST_ENABLED)
            .mode(UPDATED_MODE)
            .defaultRate(UPDATED_DEFAULT_RATE)
            .subnet(UPDATED_SUBNET)
            .deviceType(UPDATED_DEVICE_TYPE)
            .addressSetupType(UPDATED_ADDRESS_SETUP_TYPE);
        return deviceConfiguration;
    }

    @BeforeEach
    public void initTest() {
        deviceConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeviceConfiguration() throws Exception {
        int databaseSizeBeforeCreate = deviceConfigurationRepository.findAll().size();
        // Create the DeviceConfiguration
        DeviceConfigurationDTO deviceConfigurationDTO = deviceConfigurationMapper.toDto(deviceConfiguration);
        restDeviceConfigurationMockMvc
            .perform(
                post("/api/device-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deviceConfigurationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DeviceConfiguration in the database
        List<DeviceConfiguration> deviceConfigurationList = deviceConfigurationRepository.findAll();
        assertThat(deviceConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        DeviceConfiguration testDeviceConfiguration = deviceConfigurationList.get(deviceConfigurationList.size() - 1);
        assertThat(testDeviceConfiguration.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testDeviceConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDeviceConfiguration.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testDeviceConfiguration.getHostName()).isEqualTo(DEFAULT_HOST_NAME);
        assertThat(testDeviceConfiguration.getLoIp()).isEqualTo(DEFAULT_LO_IP);
        assertThat(testDeviceConfiguration.getFirmwareVersion()).isEqualTo(DEFAULT_FIRMWARE_VERSION);
        assertThat(testDeviceConfiguration.getEndpoint()).isEqualTo(DEFAULT_ENDPOINT);
        assertThat(testDeviceConfiguration.getRestUsername()).isEqualTo(DEFAULT_REST_USERNAME);
        assertThat(testDeviceConfiguration.getRestPassword()).isEqualTo(DEFAULT_REST_PASSWORD);
        assertThat(testDeviceConfiguration.isRestEnabled()).isEqualTo(DEFAULT_REST_ENABLED);
        assertThat(testDeviceConfiguration.getMode()).isEqualTo(DEFAULT_MODE);
        assertThat(testDeviceConfiguration.getDefaultRate()).isEqualTo(DEFAULT_DEFAULT_RATE);
        assertThat(testDeviceConfiguration.getSubnet()).isEqualTo(DEFAULT_SUBNET);
        assertThat(testDeviceConfiguration.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testDeviceConfiguration.getAddressSetupType()).isEqualTo(DEFAULT_ADDRESS_SETUP_TYPE);
    }

    @Test
    @Transactional
    public void createDeviceConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceConfigurationRepository.findAll().size();

        // Create the DeviceConfiguration with an existing ID
        deviceConfiguration.setId(1L);
        DeviceConfigurationDTO deviceConfigurationDTO = deviceConfigurationMapper.toDto(deviceConfiguration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceConfigurationMockMvc
            .perform(
                post("/api/device-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deviceConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeviceConfiguration in the database
        List<DeviceConfiguration> deviceConfigurationList = deviceConfigurationRepository.findAll();
        assertThat(deviceConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeviceConfigurations() throws Exception {
        // Initialize the database
        deviceConfigurationRepository.saveAndFlush(deviceConfiguration);

        // Get all the deviceConfigurationList
        restDeviceConfigurationMockMvc
            .perform(get("/api/device-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviceConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].hostName").value(hasItem(DEFAULT_HOST_NAME)))
            .andExpect(jsonPath("$.[*].loIp").value(hasItem(DEFAULT_LO_IP)))
            .andExpect(jsonPath("$.[*].firmwareVersion").value(hasItem(DEFAULT_FIRMWARE_VERSION)))
            .andExpect(jsonPath("$.[*].endpoint").value(hasItem(DEFAULT_ENDPOINT)))
            .andExpect(jsonPath("$.[*].restUsername").value(hasItem(DEFAULT_REST_USERNAME)))
            .andExpect(jsonPath("$.[*].restPassword").value(hasItem(DEFAULT_REST_PASSWORD)))
            .andExpect(jsonPath("$.[*].restEnabled").value(hasItem(DEFAULT_REST_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE.toString())))
            .andExpect(jsonPath("$.[*].defaultRate").value(hasItem(DEFAULT_DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].subnet").value(hasItem(DEFAULT_SUBNET)))
            .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].addressSetupType").value(hasItem(DEFAULT_ADDRESS_SETUP_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getDeviceConfiguration() throws Exception {
        // Initialize the database
        deviceConfigurationRepository.saveAndFlush(deviceConfiguration);

        // Get the deviceConfiguration
        restDeviceConfigurationMockMvc
            .perform(get("/api/device-configurations/{id}", deviceConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deviceConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER))
            .andExpect(jsonPath("$.hostName").value(DEFAULT_HOST_NAME))
            .andExpect(jsonPath("$.loIp").value(DEFAULT_LO_IP))
            .andExpect(jsonPath("$.firmwareVersion").value(DEFAULT_FIRMWARE_VERSION))
            .andExpect(jsonPath("$.endpoint").value(DEFAULT_ENDPOINT))
            .andExpect(jsonPath("$.restUsername").value(DEFAULT_REST_USERNAME))
            .andExpect(jsonPath("$.restPassword").value(DEFAULT_REST_PASSWORD))
            .andExpect(jsonPath("$.restEnabled").value(DEFAULT_REST_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.mode").value(DEFAULT_MODE.toString()))
            .andExpect(jsonPath("$.defaultRate").value(DEFAULT_DEFAULT_RATE))
            .andExpect(jsonPath("$.subnet").value(DEFAULT_SUBNET))
            .andExpect(jsonPath("$.deviceType").value(DEFAULT_DEVICE_TYPE.toString()))
            .andExpect(jsonPath("$.addressSetupType").value(DEFAULT_ADDRESS_SETUP_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeviceConfiguration() throws Exception {
        // Get the deviceConfiguration
        restDeviceConfigurationMockMvc.perform(get("/api/device-configurations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeviceConfiguration() throws Exception {
        // Initialize the database
        deviceConfigurationRepository.saveAndFlush(deviceConfiguration);

        int databaseSizeBeforeUpdate = deviceConfigurationRepository.findAll().size();

        // Update the deviceConfiguration
        DeviceConfiguration updatedDeviceConfiguration = deviceConfigurationRepository.findById(deviceConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedDeviceConfiguration are not directly saved in db
        em.detach(updatedDeviceConfiguration);
        updatedDeviceConfiguration
            .uid(UPDATED_UID)
            .description(UPDATED_DESCRIPTION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .hostName(UPDATED_HOST_NAME)
            .loIp(UPDATED_LO_IP)
            .firmwareVersion(UPDATED_FIRMWARE_VERSION)
            .endpoint(UPDATED_ENDPOINT)
            .restUsername(UPDATED_REST_USERNAME)
            .restPassword(UPDATED_REST_PASSWORD)
            .restEnabled(UPDATED_REST_ENABLED)
            .mode(UPDATED_MODE)
            .defaultRate(UPDATED_DEFAULT_RATE)
            .subnet(UPDATED_SUBNET)
            .deviceType(UPDATED_DEVICE_TYPE)
            .addressSetupType(UPDATED_ADDRESS_SETUP_TYPE);
        DeviceConfigurationDTO deviceConfigurationDTO = deviceConfigurationMapper.toDto(updatedDeviceConfiguration);

        restDeviceConfigurationMockMvc
            .perform(
                put("/api/device-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deviceConfigurationDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeviceConfiguration in the database
        List<DeviceConfiguration> deviceConfigurationList = deviceConfigurationRepository.findAll();
        assertThat(deviceConfigurationList).hasSize(databaseSizeBeforeUpdate);
        DeviceConfiguration testDeviceConfiguration = deviceConfigurationList.get(deviceConfigurationList.size() - 1);
        assertThat(testDeviceConfiguration.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testDeviceConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDeviceConfiguration.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testDeviceConfiguration.getHostName()).isEqualTo(UPDATED_HOST_NAME);
        assertThat(testDeviceConfiguration.getLoIp()).isEqualTo(UPDATED_LO_IP);
        assertThat(testDeviceConfiguration.getFirmwareVersion()).isEqualTo(UPDATED_FIRMWARE_VERSION);
        assertThat(testDeviceConfiguration.getEndpoint()).isEqualTo(UPDATED_ENDPOINT);
        assertThat(testDeviceConfiguration.getRestUsername()).isEqualTo(UPDATED_REST_USERNAME);
        assertThat(testDeviceConfiguration.getRestPassword()).isEqualTo(UPDATED_REST_PASSWORD);
        assertThat(testDeviceConfiguration.isRestEnabled()).isEqualTo(UPDATED_REST_ENABLED);
        assertThat(testDeviceConfiguration.getMode()).isEqualTo(UPDATED_MODE);
        assertThat(testDeviceConfiguration.getDefaultRate()).isEqualTo(UPDATED_DEFAULT_RATE);
        assertThat(testDeviceConfiguration.getSubnet()).isEqualTo(UPDATED_SUBNET);
        assertThat(testDeviceConfiguration.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testDeviceConfiguration.getAddressSetupType()).isEqualTo(UPDATED_ADDRESS_SETUP_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingDeviceConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = deviceConfigurationRepository.findAll().size();

        // Create the DeviceConfiguration
        DeviceConfigurationDTO deviceConfigurationDTO = deviceConfigurationMapper.toDto(deviceConfiguration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceConfigurationMockMvc
            .perform(
                put("/api/device-configurations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deviceConfigurationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeviceConfiguration in the database
        List<DeviceConfiguration> deviceConfigurationList = deviceConfigurationRepository.findAll();
        assertThat(deviceConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeviceConfiguration() throws Exception {
        // Initialize the database
        deviceConfigurationRepository.saveAndFlush(deviceConfiguration);

        int databaseSizeBeforeDelete = deviceConfigurationRepository.findAll().size();

        // Delete the deviceConfiguration
        restDeviceConfigurationMockMvc
            .perform(delete("/api/device-configurations/{id}", deviceConfiguration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeviceConfiguration> deviceConfigurationList = deviceConfigurationRepository.findAll();
        assertThat(deviceConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
