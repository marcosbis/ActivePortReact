package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.Ntu;
import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.activeport.web.repository.NtuRepository;
import com.activeport.web.service.NtuService;
import com.activeport.web.service.dto.NtuDTO;
import com.activeport.web.service.mapper.NtuMapper;
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
 * Integration tests for the {@link NtuResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NtuResourceIT {
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

    private static final String DEFAULT_LO_IP = "AAAAAAAAAA";
    private static final String UPDATED_LO_IP = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_ALARM_EMAIL_ADDRESSES = "AAAAAAAAAA";
    private static final String UPDATED_ALARM_EMAIL_ADDRESSES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_METRIC_COLLECTION = false;
    private static final Boolean UPDATED_METRIC_COLLECTION = true;

    private static final Boolean DEFAULT_SECURITY_COLLECTION = false;
    private static final Boolean UPDATED_SECURITY_COLLECTION = true;

    private static final Boolean DEFAULT_ALARM_COLLECTION = false;
    private static final Boolean UPDATED_ALARM_COLLECTION = true;

    private static final Boolean DEFAULT_TREND_COLLECTION = false;
    private static final Boolean UPDATED_TREND_COLLECTION = true;

    private static final Boolean DEFAULT_SYSLOG_COLLECTION = false;
    private static final Boolean UPDATED_SYSLOG_COLLECTION = true;

    private static final Boolean DEFAULT_CONFIG_BACKUP = false;
    private static final Boolean UPDATED_CONFIG_BACKUP = true;

    private static final Boolean DEFAULT_UPDATE_ONECONFIG = false;
    private static final Boolean UPDATED_UPDATE_ONECONFIG = true;

    private static final String DEFAULT_FIRMWARE_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_FIRMWARE_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_RUNNING_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_RUNNING_CONFIG = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_ID = "BBBBBBBBBB";

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

    private static final NtuModeEnum DEFAULT_MODE = NtuModeEnum.DEMO;
    private static final NtuModeEnum UPDATED_MODE = NtuModeEnum.EDGE;

    private static final String DEFAULT_TIME_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_TIME_ZONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_MIN_RATE = 1;
    private static final Integer UPDATED_MIN_RATE = 2;

    private static final Integer DEFAULT_MAX_RATE = 1;
    private static final Integer UPDATED_MAX_RATE = 2;

    private static final Integer DEFAULT_DEFAULT_RATE = 1;
    private static final Integer UPDATED_DEFAULT_RATE = 2;

    private static final Boolean DEFAULT_ENABLE_BOD = false;
    private static final Boolean UPDATED_ENABLE_BOD = true;

    private static final Integer DEFAULT_BURST_TIME = 5;
    private static final Integer UPDATED_BURST_TIME = 6;

    private static final String DEFAULT_SECONDLINK_PORT = "AAAAAAAAAA";
    private static final String UPDATED_SECONDLINK_PORT = "BBBBBBBBBB";

    @Autowired
    private NtuRepository ntuRepository;

    @Autowired
    private NtuMapper ntuMapper;

    @Autowired
    private NtuService ntuService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNtuMockMvc;

    private Ntu ntu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ntu createEntity(EntityManager em) {
        Ntu ntu = new Ntu()
            .name(DEFAULT_NAME)
            .hostId(DEFAULT_HOST_ID)
            .description(DEFAULT_DESCRIPTION)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .companyName(DEFAULT_COMPANY_NAME)
            .hostName(DEFAULT_HOST_NAME)
            .loIp(DEFAULT_LO_IP)
            .category(DEFAULT_CATEGORY)
            .alarmEmailAddresses(DEFAULT_ALARM_EMAIL_ADDRESSES)
            .metricCollection(DEFAULT_METRIC_COLLECTION)
            .securityCollection(DEFAULT_SECURITY_COLLECTION)
            .alarmCollection(DEFAULT_ALARM_COLLECTION)
            .trendCollection(DEFAULT_TREND_COLLECTION)
            .syslogCollection(DEFAULT_SYSLOG_COLLECTION)
            .configBackup(DEFAULT_CONFIG_BACKUP)
            .updateOneconfig(DEFAULT_UPDATE_ONECONFIG)
            .firmwareVersion(DEFAULT_FIRMWARE_VERSION)
            .runningConfig(DEFAULT_RUNNING_CONFIG)
            .configId(DEFAULT_CONFIG_ID)
            .endpoint(DEFAULT_ENDPOINT)
            .restUsername(DEFAULT_REST_USERNAME)
            .restPassword(DEFAULT_REST_PASSWORD)
            .restEnabled(DEFAULT_REST_ENABLED)
            .autoRollback(DEFAULT_AUTO_ROLLBACK)
            .mode(DEFAULT_MODE)
            .timeZone(DEFAULT_TIME_ZONE)
            .minRate(DEFAULT_MIN_RATE)
            .maxRate(DEFAULT_MAX_RATE)
            .defaultRate(DEFAULT_DEFAULT_RATE)
            .enableBod(DEFAULT_ENABLE_BOD)
            .burstTime(DEFAULT_BURST_TIME)
            .secondlinkPort(DEFAULT_SECONDLINK_PORT);
        return ntu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ntu createUpdatedEntity(EntityManager em) {
        Ntu ntu = new Ntu()
            .name(UPDATED_NAME)
            .hostId(UPDATED_HOST_ID)
            .description(UPDATED_DESCRIPTION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .ipAddress(UPDATED_IP_ADDRESS)
            .companyName(UPDATED_COMPANY_NAME)
            .hostName(UPDATED_HOST_NAME)
            .loIp(UPDATED_LO_IP)
            .category(UPDATED_CATEGORY)
            .alarmEmailAddresses(UPDATED_ALARM_EMAIL_ADDRESSES)
            .metricCollection(UPDATED_METRIC_COLLECTION)
            .securityCollection(UPDATED_SECURITY_COLLECTION)
            .alarmCollection(UPDATED_ALARM_COLLECTION)
            .trendCollection(UPDATED_TREND_COLLECTION)
            .syslogCollection(UPDATED_SYSLOG_COLLECTION)
            .configBackup(UPDATED_CONFIG_BACKUP)
            .updateOneconfig(UPDATED_UPDATE_ONECONFIG)
            .firmwareVersion(UPDATED_FIRMWARE_VERSION)
            .runningConfig(UPDATED_RUNNING_CONFIG)
            .configId(UPDATED_CONFIG_ID)
            .endpoint(UPDATED_ENDPOINT)
            .restUsername(UPDATED_REST_USERNAME)
            .restPassword(UPDATED_REST_PASSWORD)
            .restEnabled(UPDATED_REST_ENABLED)
            .autoRollback(UPDATED_AUTO_ROLLBACK)
            .mode(UPDATED_MODE)
            .timeZone(UPDATED_TIME_ZONE)
            .minRate(UPDATED_MIN_RATE)
            .maxRate(UPDATED_MAX_RATE)
            .defaultRate(UPDATED_DEFAULT_RATE)
            .enableBod(UPDATED_ENABLE_BOD)
            .burstTime(UPDATED_BURST_TIME)
            .secondlinkPort(UPDATED_SECONDLINK_PORT);
        return ntu;
    }

    @BeforeEach
    public void initTest() {
        ntu = createEntity(em);
    }

    @Test
    @Transactional
    public void createNtu() throws Exception {
        int databaseSizeBeforeCreate = ntuRepository.findAll().size();
        // Create the Ntu
        NtuDTO ntuDTO = ntuMapper.toDto(ntu);
        restNtuMockMvc
            .perform(post("/api/ntus").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuDTO)))
            .andExpect(status().isCreated());

        // Validate the Ntu in the database
        List<Ntu> ntuList = ntuRepository.findAll();
        assertThat(ntuList).hasSize(databaseSizeBeforeCreate + 1);
        Ntu testNtu = ntuList.get(ntuList.size() - 1);
        assertThat(testNtu.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNtu.getHostId()).isEqualTo(DEFAULT_HOST_ID);
        assertThat(testNtu.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNtu.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testNtu.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testNtu.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testNtu.getHostName()).isEqualTo(DEFAULT_HOST_NAME);
        assertThat(testNtu.getLoIp()).isEqualTo(DEFAULT_LO_IP);
        assertThat(testNtu.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testNtu.getAlarmEmailAddresses()).isEqualTo(DEFAULT_ALARM_EMAIL_ADDRESSES);
        assertThat(testNtu.isMetricCollection()).isEqualTo(DEFAULT_METRIC_COLLECTION);
        assertThat(testNtu.isSecurityCollection()).isEqualTo(DEFAULT_SECURITY_COLLECTION);
        assertThat(testNtu.isAlarmCollection()).isEqualTo(DEFAULT_ALARM_COLLECTION);
        assertThat(testNtu.isTrendCollection()).isEqualTo(DEFAULT_TREND_COLLECTION);
        assertThat(testNtu.isSyslogCollection()).isEqualTo(DEFAULT_SYSLOG_COLLECTION);
        assertThat(testNtu.isConfigBackup()).isEqualTo(DEFAULT_CONFIG_BACKUP);
        assertThat(testNtu.isUpdateOneconfig()).isEqualTo(DEFAULT_UPDATE_ONECONFIG);
        assertThat(testNtu.getFirmwareVersion()).isEqualTo(DEFAULT_FIRMWARE_VERSION);
        assertThat(testNtu.getRunningConfig()).isEqualTo(DEFAULT_RUNNING_CONFIG);
        assertThat(testNtu.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testNtu.getEndpoint()).isEqualTo(DEFAULT_ENDPOINT);
        assertThat(testNtu.getRestUsername()).isEqualTo(DEFAULT_REST_USERNAME);
        assertThat(testNtu.getRestPassword()).isEqualTo(DEFAULT_REST_PASSWORD);
        assertThat(testNtu.isRestEnabled()).isEqualTo(DEFAULT_REST_ENABLED);
        assertThat(testNtu.isAutoRollback()).isEqualTo(DEFAULT_AUTO_ROLLBACK);
        assertThat(testNtu.getMode()).isEqualTo(DEFAULT_MODE);
        assertThat(testNtu.getTimeZone()).isEqualTo(DEFAULT_TIME_ZONE);
        assertThat(testNtu.getMinRate()).isEqualTo(DEFAULT_MIN_RATE);
        assertThat(testNtu.getMaxRate()).isEqualTo(DEFAULT_MAX_RATE);
        assertThat(testNtu.getDefaultRate()).isEqualTo(DEFAULT_DEFAULT_RATE);
        assertThat(testNtu.isEnableBod()).isEqualTo(DEFAULT_ENABLE_BOD);
        assertThat(testNtu.getBurstTime()).isEqualTo(DEFAULT_BURST_TIME);
        assertThat(testNtu.getSecondlinkPort()).isEqualTo(DEFAULT_SECONDLINK_PORT);
    }

    @Test
    @Transactional
    public void createNtuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ntuRepository.findAll().size();

        // Create the Ntu with an existing ID
        ntu.setId(1L);
        NtuDTO ntuDTO = ntuMapper.toDto(ntu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNtuMockMvc
            .perform(post("/api/ntus").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ntu in the database
        List<Ntu> ntuList = ntuRepository.findAll();
        assertThat(ntuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBurstTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = ntuRepository.findAll().size();
        // set the field null
        ntu.setBurstTime(null);

        // Create the Ntu, which fails.
        NtuDTO ntuDTO = ntuMapper.toDto(ntu);

        restNtuMockMvc
            .perform(post("/api/ntus").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuDTO)))
            .andExpect(status().isBadRequest());

        List<Ntu> ntuList = ntuRepository.findAll();
        assertThat(ntuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNtus() throws Exception {
        // Initialize the database
        ntuRepository.saveAndFlush(ntu);

        // Get all the ntuList
        restNtuMockMvc
            .perform(get("/api/ntus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ntu.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].hostId").value(hasItem(DEFAULT_HOST_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].hostName").value(hasItem(DEFAULT_HOST_NAME)))
            .andExpect(jsonPath("$.[*].loIp").value(hasItem(DEFAULT_LO_IP)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].alarmEmailAddresses").value(hasItem(DEFAULT_ALARM_EMAIL_ADDRESSES)))
            .andExpect(jsonPath("$.[*].metricCollection").value(hasItem(DEFAULT_METRIC_COLLECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].securityCollection").value(hasItem(DEFAULT_SECURITY_COLLECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].alarmCollection").value(hasItem(DEFAULT_ALARM_COLLECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].trendCollection").value(hasItem(DEFAULT_TREND_COLLECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].syslogCollection").value(hasItem(DEFAULT_SYSLOG_COLLECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].configBackup").value(hasItem(DEFAULT_CONFIG_BACKUP.booleanValue())))
            .andExpect(jsonPath("$.[*].updateOneconfig").value(hasItem(DEFAULT_UPDATE_ONECONFIG.booleanValue())))
            .andExpect(jsonPath("$.[*].firmwareVersion").value(hasItem(DEFAULT_FIRMWARE_VERSION)))
            .andExpect(jsonPath("$.[*].runningConfig").value(hasItem(DEFAULT_RUNNING_CONFIG)))
            .andExpect(jsonPath("$.[*].configId").value(hasItem(DEFAULT_CONFIG_ID)))
            .andExpect(jsonPath("$.[*].endpoint").value(hasItem(DEFAULT_ENDPOINT)))
            .andExpect(jsonPath("$.[*].restUsername").value(hasItem(DEFAULT_REST_USERNAME)))
            .andExpect(jsonPath("$.[*].restPassword").value(hasItem(DEFAULT_REST_PASSWORD)))
            .andExpect(jsonPath("$.[*].restEnabled").value(hasItem(DEFAULT_REST_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].autoRollback").value(hasItem(DEFAULT_AUTO_ROLLBACK.booleanValue())))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE.toString())))
            .andExpect(jsonPath("$.[*].timeZone").value(hasItem(DEFAULT_TIME_ZONE)))
            .andExpect(jsonPath("$.[*].minRate").value(hasItem(DEFAULT_MIN_RATE)))
            .andExpect(jsonPath("$.[*].maxRate").value(hasItem(DEFAULT_MAX_RATE)))
            .andExpect(jsonPath("$.[*].defaultRate").value(hasItem(DEFAULT_DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].enableBod").value(hasItem(DEFAULT_ENABLE_BOD.booleanValue())))
            .andExpect(jsonPath("$.[*].burstTime").value(hasItem(DEFAULT_BURST_TIME)))
            .andExpect(jsonPath("$.[*].secondlinkPort").value(hasItem(DEFAULT_SECONDLINK_PORT)));
    }

    @Test
    @Transactional
    public void getNtu() throws Exception {
        // Initialize the database
        ntuRepository.saveAndFlush(ntu);

        // Get the ntu
        restNtuMockMvc
            .perform(get("/api/ntus/{id}", ntu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ntu.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.hostId").value(DEFAULT_HOST_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.hostName").value(DEFAULT_HOST_NAME))
            .andExpect(jsonPath("$.loIp").value(DEFAULT_LO_IP))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.alarmEmailAddresses").value(DEFAULT_ALARM_EMAIL_ADDRESSES))
            .andExpect(jsonPath("$.metricCollection").value(DEFAULT_METRIC_COLLECTION.booleanValue()))
            .andExpect(jsonPath("$.securityCollection").value(DEFAULT_SECURITY_COLLECTION.booleanValue()))
            .andExpect(jsonPath("$.alarmCollection").value(DEFAULT_ALARM_COLLECTION.booleanValue()))
            .andExpect(jsonPath("$.trendCollection").value(DEFAULT_TREND_COLLECTION.booleanValue()))
            .andExpect(jsonPath("$.syslogCollection").value(DEFAULT_SYSLOG_COLLECTION.booleanValue()))
            .andExpect(jsonPath("$.configBackup").value(DEFAULT_CONFIG_BACKUP.booleanValue()))
            .andExpect(jsonPath("$.updateOneconfig").value(DEFAULT_UPDATE_ONECONFIG.booleanValue()))
            .andExpect(jsonPath("$.firmwareVersion").value(DEFAULT_FIRMWARE_VERSION))
            .andExpect(jsonPath("$.runningConfig").value(DEFAULT_RUNNING_CONFIG))
            .andExpect(jsonPath("$.configId").value(DEFAULT_CONFIG_ID))
            .andExpect(jsonPath("$.endpoint").value(DEFAULT_ENDPOINT))
            .andExpect(jsonPath("$.restUsername").value(DEFAULT_REST_USERNAME))
            .andExpect(jsonPath("$.restPassword").value(DEFAULT_REST_PASSWORD))
            .andExpect(jsonPath("$.restEnabled").value(DEFAULT_REST_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.autoRollback").value(DEFAULT_AUTO_ROLLBACK.booleanValue()))
            .andExpect(jsonPath("$.mode").value(DEFAULT_MODE.toString()))
            .andExpect(jsonPath("$.timeZone").value(DEFAULT_TIME_ZONE))
            .andExpect(jsonPath("$.minRate").value(DEFAULT_MIN_RATE))
            .andExpect(jsonPath("$.maxRate").value(DEFAULT_MAX_RATE))
            .andExpect(jsonPath("$.defaultRate").value(DEFAULT_DEFAULT_RATE))
            .andExpect(jsonPath("$.enableBod").value(DEFAULT_ENABLE_BOD.booleanValue()))
            .andExpect(jsonPath("$.burstTime").value(DEFAULT_BURST_TIME))
            .andExpect(jsonPath("$.secondlinkPort").value(DEFAULT_SECONDLINK_PORT));
    }

    @Test
    @Transactional
    public void getNonExistingNtu() throws Exception {
        // Get the ntu
        restNtuMockMvc.perform(get("/api/ntus/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNtu() throws Exception {
        // Initialize the database
        ntuRepository.saveAndFlush(ntu);

        int databaseSizeBeforeUpdate = ntuRepository.findAll().size();

        // Update the ntu
        Ntu updatedNtu = ntuRepository.findById(ntu.getId()).get();
        // Disconnect from session so that the updates on updatedNtu are not directly saved in db
        em.detach(updatedNtu);
        updatedNtu
            .name(UPDATED_NAME)
            .hostId(UPDATED_HOST_ID)
            .description(UPDATED_DESCRIPTION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .ipAddress(UPDATED_IP_ADDRESS)
            .companyName(UPDATED_COMPANY_NAME)
            .hostName(UPDATED_HOST_NAME)
            .loIp(UPDATED_LO_IP)
            .category(UPDATED_CATEGORY)
            .alarmEmailAddresses(UPDATED_ALARM_EMAIL_ADDRESSES)
            .metricCollection(UPDATED_METRIC_COLLECTION)
            .securityCollection(UPDATED_SECURITY_COLLECTION)
            .alarmCollection(UPDATED_ALARM_COLLECTION)
            .trendCollection(UPDATED_TREND_COLLECTION)
            .syslogCollection(UPDATED_SYSLOG_COLLECTION)
            .configBackup(UPDATED_CONFIG_BACKUP)
            .updateOneconfig(UPDATED_UPDATE_ONECONFIG)
            .firmwareVersion(UPDATED_FIRMWARE_VERSION)
            .runningConfig(UPDATED_RUNNING_CONFIG)
            .configId(UPDATED_CONFIG_ID)
            .endpoint(UPDATED_ENDPOINT)
            .restUsername(UPDATED_REST_USERNAME)
            .restPassword(UPDATED_REST_PASSWORD)
            .restEnabled(UPDATED_REST_ENABLED)
            .autoRollback(UPDATED_AUTO_ROLLBACK)
            .mode(UPDATED_MODE)
            .timeZone(UPDATED_TIME_ZONE)
            .minRate(UPDATED_MIN_RATE)
            .maxRate(UPDATED_MAX_RATE)
            .defaultRate(UPDATED_DEFAULT_RATE)
            .enableBod(UPDATED_ENABLE_BOD)
            .burstTime(UPDATED_BURST_TIME)
            .secondlinkPort(UPDATED_SECONDLINK_PORT);
        NtuDTO ntuDTO = ntuMapper.toDto(updatedNtu);

        restNtuMockMvc
            .perform(put("/api/ntus").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuDTO)))
            .andExpect(status().isOk());

        // Validate the Ntu in the database
        List<Ntu> ntuList = ntuRepository.findAll();
        assertThat(ntuList).hasSize(databaseSizeBeforeUpdate);
        Ntu testNtu = ntuList.get(ntuList.size() - 1);
        assertThat(testNtu.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNtu.getHostId()).isEqualTo(UPDATED_HOST_ID);
        assertThat(testNtu.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNtu.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testNtu.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testNtu.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testNtu.getHostName()).isEqualTo(UPDATED_HOST_NAME);
        assertThat(testNtu.getLoIp()).isEqualTo(UPDATED_LO_IP);
        assertThat(testNtu.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testNtu.getAlarmEmailAddresses()).isEqualTo(UPDATED_ALARM_EMAIL_ADDRESSES);
        assertThat(testNtu.isMetricCollection()).isEqualTo(UPDATED_METRIC_COLLECTION);
        assertThat(testNtu.isSecurityCollection()).isEqualTo(UPDATED_SECURITY_COLLECTION);
        assertThat(testNtu.isAlarmCollection()).isEqualTo(UPDATED_ALARM_COLLECTION);
        assertThat(testNtu.isTrendCollection()).isEqualTo(UPDATED_TREND_COLLECTION);
        assertThat(testNtu.isSyslogCollection()).isEqualTo(UPDATED_SYSLOG_COLLECTION);
        assertThat(testNtu.isConfigBackup()).isEqualTo(UPDATED_CONFIG_BACKUP);
        assertThat(testNtu.isUpdateOneconfig()).isEqualTo(UPDATED_UPDATE_ONECONFIG);
        assertThat(testNtu.getFirmwareVersion()).isEqualTo(UPDATED_FIRMWARE_VERSION);
        assertThat(testNtu.getRunningConfig()).isEqualTo(UPDATED_RUNNING_CONFIG);
        assertThat(testNtu.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
        assertThat(testNtu.getEndpoint()).isEqualTo(UPDATED_ENDPOINT);
        assertThat(testNtu.getRestUsername()).isEqualTo(UPDATED_REST_USERNAME);
        assertThat(testNtu.getRestPassword()).isEqualTo(UPDATED_REST_PASSWORD);
        assertThat(testNtu.isRestEnabled()).isEqualTo(UPDATED_REST_ENABLED);
        assertThat(testNtu.isAutoRollback()).isEqualTo(UPDATED_AUTO_ROLLBACK);
        assertThat(testNtu.getMode()).isEqualTo(UPDATED_MODE);
        assertThat(testNtu.getTimeZone()).isEqualTo(UPDATED_TIME_ZONE);
        assertThat(testNtu.getMinRate()).isEqualTo(UPDATED_MIN_RATE);
        assertThat(testNtu.getMaxRate()).isEqualTo(UPDATED_MAX_RATE);
        assertThat(testNtu.getDefaultRate()).isEqualTo(UPDATED_DEFAULT_RATE);
        assertThat(testNtu.isEnableBod()).isEqualTo(UPDATED_ENABLE_BOD);
        assertThat(testNtu.getBurstTime()).isEqualTo(UPDATED_BURST_TIME);
        assertThat(testNtu.getSecondlinkPort()).isEqualTo(UPDATED_SECONDLINK_PORT);
    }

    @Test
    @Transactional
    public void updateNonExistingNtu() throws Exception {
        int databaseSizeBeforeUpdate = ntuRepository.findAll().size();

        // Create the Ntu
        NtuDTO ntuDTO = ntuMapper.toDto(ntu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNtuMockMvc
            .perform(put("/api/ntus").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ntu in the database
        List<Ntu> ntuList = ntuRepository.findAll();
        assertThat(ntuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNtu() throws Exception {
        // Initialize the database
        ntuRepository.saveAndFlush(ntu);

        int databaseSizeBeforeDelete = ntuRepository.findAll().size();

        // Delete the ntu
        restNtuMockMvc.perform(delete("/api/ntus/{id}", ntu.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ntu> ntuList = ntuRepository.findAll();
        assertThat(ntuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
