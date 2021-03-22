package com.activeport.web.web.rest;

import static com.activeport.web.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.JunoCommandLog;
import com.activeport.web.domain.enumeration.DeviceTargetTypeEnum;
import com.activeport.web.domain.enumeration.EventTypeEnum;
import com.activeport.web.repository.JunoCommandLogRepository;
import com.activeport.web.service.JunoCommandLogService;
import com.activeport.web.service.dto.JunoCommandLogDTO;
import com.activeport.web.service.mapper.JunoCommandLogMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link JunoCommandLogResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class JunoCommandLogResourceIT {
    private static final Long DEFAULT_NTU_ID = 1L;
    private static final Long UPDATED_NTU_ID = 2L;

    private static final Long DEFAULT_SWITCH_ID = 1L;
    private static final Long UPDATED_SWITCH_ID = 2L;

    private static final Long DEFAULT_SERVICE_ID = 1L;
    private static final Long UPDATED_SERVICE_ID = 2L;

    private static final Long DEFAULT_VXC_ID = 1L;
    private static final Long UPDATED_VXC_ID = 2L;

    private static final String DEFAULT_DEVICE_URL = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_COMMAND = "AAAAAAAAAA";
    private static final String UPDATED_COMMAND = "BBBBBBBBBB";

    private static final String DEFAULT_CMD_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_CMD_RESPONSE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EXECUTED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EXECUTED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final EventTypeEnum DEFAULT_EVENT_TYPE = EventTypeEnum.FIREWALL_ACTIVATE;
    private static final EventTypeEnum UPDATED_EVENT_TYPE = EventTypeEnum.FIREWALL_DEACTIVATE;

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_NAME = "BBBBBBBBBB";

    private static final DeviceTargetTypeEnum DEFAULT_TARGET_TYPE = DeviceTargetTypeEnum.NTU;
    private static final DeviceTargetTypeEnum UPDATED_TARGET_TYPE = DeviceTargetTypeEnum.FIREWALL;

    private static final Boolean DEFAULT_HAS_ERRORS = false;
    private static final Boolean UPDATED_HAS_ERRORS = true;

    @Autowired
    private JunoCommandLogRepository junoCommandLogRepository;

    @Autowired
    private JunoCommandLogMapper junoCommandLogMapper;

    @Autowired
    private JunoCommandLogService junoCommandLogService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJunoCommandLogMockMvc;

    private JunoCommandLog junoCommandLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JunoCommandLog createEntity(EntityManager em) {
        JunoCommandLog junoCommandLog = new JunoCommandLog()
            .ntuId(DEFAULT_NTU_ID)
            .switchId(DEFAULT_SWITCH_ID)
            .serviceId(DEFAULT_SERVICE_ID)
            .vxcId(DEFAULT_VXC_ID)
            .deviceUrl(DEFAULT_DEVICE_URL)
            .uuid(DEFAULT_UUID)
            .command(DEFAULT_COMMAND)
            .cmdResponse(DEFAULT_CMD_RESPONSE)
            .executedDate(DEFAULT_EXECUTED_DATE)
            .eventType(DEFAULT_EVENT_TYPE)
            .user(DEFAULT_USER)
            .deviceName(DEFAULT_DEVICE_NAME)
            .targetType(DEFAULT_TARGET_TYPE)
            .hasErrors(DEFAULT_HAS_ERRORS);
        return junoCommandLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JunoCommandLog createUpdatedEntity(EntityManager em) {
        JunoCommandLog junoCommandLog = new JunoCommandLog()
            .ntuId(UPDATED_NTU_ID)
            .switchId(UPDATED_SWITCH_ID)
            .serviceId(UPDATED_SERVICE_ID)
            .vxcId(UPDATED_VXC_ID)
            .deviceUrl(UPDATED_DEVICE_URL)
            .uuid(UPDATED_UUID)
            .command(UPDATED_COMMAND)
            .cmdResponse(UPDATED_CMD_RESPONSE)
            .executedDate(UPDATED_EXECUTED_DATE)
            .eventType(UPDATED_EVENT_TYPE)
            .user(UPDATED_USER)
            .deviceName(UPDATED_DEVICE_NAME)
            .targetType(UPDATED_TARGET_TYPE)
            .hasErrors(UPDATED_HAS_ERRORS);
        return junoCommandLog;
    }

    @BeforeEach
    public void initTest() {
        junoCommandLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createJunoCommandLog() throws Exception {
        int databaseSizeBeforeCreate = junoCommandLogRepository.findAll().size();
        // Create the JunoCommandLog
        JunoCommandLogDTO junoCommandLogDTO = junoCommandLogMapper.toDto(junoCommandLog);
        restJunoCommandLogMockMvc
            .perform(
                post("/api/juno-command-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(junoCommandLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the JunoCommandLog in the database
        List<JunoCommandLog> junoCommandLogList = junoCommandLogRepository.findAll();
        assertThat(junoCommandLogList).hasSize(databaseSizeBeforeCreate + 1);
        JunoCommandLog testJunoCommandLog = junoCommandLogList.get(junoCommandLogList.size() - 1);
        assertThat(testJunoCommandLog.getNtuId()).isEqualTo(DEFAULT_NTU_ID);
        assertThat(testJunoCommandLog.getSwitchId()).isEqualTo(DEFAULT_SWITCH_ID);
        assertThat(testJunoCommandLog.getServiceId()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testJunoCommandLog.getVxcId()).isEqualTo(DEFAULT_VXC_ID);
        assertThat(testJunoCommandLog.getDeviceUrl()).isEqualTo(DEFAULT_DEVICE_URL);
        assertThat(testJunoCommandLog.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testJunoCommandLog.getCommand()).isEqualTo(DEFAULT_COMMAND);
        assertThat(testJunoCommandLog.getCmdResponse()).isEqualTo(DEFAULT_CMD_RESPONSE);
        assertThat(testJunoCommandLog.getExecutedDate()).isEqualTo(DEFAULT_EXECUTED_DATE);
        assertThat(testJunoCommandLog.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
        assertThat(testJunoCommandLog.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testJunoCommandLog.getDeviceName()).isEqualTo(DEFAULT_DEVICE_NAME);
        assertThat(testJunoCommandLog.getTargetType()).isEqualTo(DEFAULT_TARGET_TYPE);
        assertThat(testJunoCommandLog.isHasErrors()).isEqualTo(DEFAULT_HAS_ERRORS);
    }

    @Test
    @Transactional
    public void createJunoCommandLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = junoCommandLogRepository.findAll().size();

        // Create the JunoCommandLog with an existing ID
        junoCommandLog.setId(1L);
        JunoCommandLogDTO junoCommandLogDTO = junoCommandLogMapper.toDto(junoCommandLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJunoCommandLogMockMvc
            .perform(
                post("/api/juno-command-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(junoCommandLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JunoCommandLog in the database
        List<JunoCommandLog> junoCommandLogList = junoCommandLogRepository.findAll();
        assertThat(junoCommandLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllJunoCommandLogs() throws Exception {
        // Initialize the database
        junoCommandLogRepository.saveAndFlush(junoCommandLog);

        // Get all the junoCommandLogList
        restJunoCommandLogMockMvc
            .perform(get("/api/juno-command-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(junoCommandLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].ntuId").value(hasItem(DEFAULT_NTU_ID.intValue())))
            .andExpect(jsonPath("$.[*].switchId").value(hasItem(DEFAULT_SWITCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].serviceId").value(hasItem(DEFAULT_SERVICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].vxcId").value(hasItem(DEFAULT_VXC_ID.intValue())))
            .andExpect(jsonPath("$.[*].deviceUrl").value(hasItem(DEFAULT_DEVICE_URL)))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].command").value(hasItem(DEFAULT_COMMAND)))
            .andExpect(jsonPath("$.[*].cmdResponse").value(hasItem(DEFAULT_CMD_RESPONSE)))
            .andExpect(jsonPath("$.[*].executedDate").value(hasItem(sameInstant(DEFAULT_EXECUTED_DATE))))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].deviceName").value(hasItem(DEFAULT_DEVICE_NAME)))
            .andExpect(jsonPath("$.[*].targetType").value(hasItem(DEFAULT_TARGET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hasErrors").value(hasItem(DEFAULT_HAS_ERRORS.booleanValue())));
    }

    @Test
    @Transactional
    public void getJunoCommandLog() throws Exception {
        // Initialize the database
        junoCommandLogRepository.saveAndFlush(junoCommandLog);

        // Get the junoCommandLog
        restJunoCommandLogMockMvc
            .perform(get("/api/juno-command-logs/{id}", junoCommandLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(junoCommandLog.getId().intValue()))
            .andExpect(jsonPath("$.ntuId").value(DEFAULT_NTU_ID.intValue()))
            .andExpect(jsonPath("$.switchId").value(DEFAULT_SWITCH_ID.intValue()))
            .andExpect(jsonPath("$.serviceId").value(DEFAULT_SERVICE_ID.intValue()))
            .andExpect(jsonPath("$.vxcId").value(DEFAULT_VXC_ID.intValue()))
            .andExpect(jsonPath("$.deviceUrl").value(DEFAULT_DEVICE_URL))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID))
            .andExpect(jsonPath("$.command").value(DEFAULT_COMMAND))
            .andExpect(jsonPath("$.cmdResponse").value(DEFAULT_CMD_RESPONSE))
            .andExpect(jsonPath("$.executedDate").value(sameInstant(DEFAULT_EXECUTED_DATE)))
            .andExpect(jsonPath("$.eventType").value(DEFAULT_EVENT_TYPE.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.deviceName").value(DEFAULT_DEVICE_NAME))
            .andExpect(jsonPath("$.targetType").value(DEFAULT_TARGET_TYPE.toString()))
            .andExpect(jsonPath("$.hasErrors").value(DEFAULT_HAS_ERRORS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingJunoCommandLog() throws Exception {
        // Get the junoCommandLog
        restJunoCommandLogMockMvc.perform(get("/api/juno-command-logs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJunoCommandLog() throws Exception {
        // Initialize the database
        junoCommandLogRepository.saveAndFlush(junoCommandLog);

        int databaseSizeBeforeUpdate = junoCommandLogRepository.findAll().size();

        // Update the junoCommandLog
        JunoCommandLog updatedJunoCommandLog = junoCommandLogRepository.findById(junoCommandLog.getId()).get();
        // Disconnect from session so that the updates on updatedJunoCommandLog are not directly saved in db
        em.detach(updatedJunoCommandLog);
        updatedJunoCommandLog
            .ntuId(UPDATED_NTU_ID)
            .switchId(UPDATED_SWITCH_ID)
            .serviceId(UPDATED_SERVICE_ID)
            .vxcId(UPDATED_VXC_ID)
            .deviceUrl(UPDATED_DEVICE_URL)
            .uuid(UPDATED_UUID)
            .command(UPDATED_COMMAND)
            .cmdResponse(UPDATED_CMD_RESPONSE)
            .executedDate(UPDATED_EXECUTED_DATE)
            .eventType(UPDATED_EVENT_TYPE)
            .user(UPDATED_USER)
            .deviceName(UPDATED_DEVICE_NAME)
            .targetType(UPDATED_TARGET_TYPE)
            .hasErrors(UPDATED_HAS_ERRORS);
        JunoCommandLogDTO junoCommandLogDTO = junoCommandLogMapper.toDto(updatedJunoCommandLog);

        restJunoCommandLogMockMvc
            .perform(
                put("/api/juno-command-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(junoCommandLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the JunoCommandLog in the database
        List<JunoCommandLog> junoCommandLogList = junoCommandLogRepository.findAll();
        assertThat(junoCommandLogList).hasSize(databaseSizeBeforeUpdate);
        JunoCommandLog testJunoCommandLog = junoCommandLogList.get(junoCommandLogList.size() - 1);
        assertThat(testJunoCommandLog.getNtuId()).isEqualTo(UPDATED_NTU_ID);
        assertThat(testJunoCommandLog.getSwitchId()).isEqualTo(UPDATED_SWITCH_ID);
        assertThat(testJunoCommandLog.getServiceId()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testJunoCommandLog.getVxcId()).isEqualTo(UPDATED_VXC_ID);
        assertThat(testJunoCommandLog.getDeviceUrl()).isEqualTo(UPDATED_DEVICE_URL);
        assertThat(testJunoCommandLog.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testJunoCommandLog.getCommand()).isEqualTo(UPDATED_COMMAND);
        assertThat(testJunoCommandLog.getCmdResponse()).isEqualTo(UPDATED_CMD_RESPONSE);
        assertThat(testJunoCommandLog.getExecutedDate()).isEqualTo(UPDATED_EXECUTED_DATE);
        assertThat(testJunoCommandLog.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
        assertThat(testJunoCommandLog.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testJunoCommandLog.getDeviceName()).isEqualTo(UPDATED_DEVICE_NAME);
        assertThat(testJunoCommandLog.getTargetType()).isEqualTo(UPDATED_TARGET_TYPE);
        assertThat(testJunoCommandLog.isHasErrors()).isEqualTo(UPDATED_HAS_ERRORS);
    }

    @Test
    @Transactional
    public void updateNonExistingJunoCommandLog() throws Exception {
        int databaseSizeBeforeUpdate = junoCommandLogRepository.findAll().size();

        // Create the JunoCommandLog
        JunoCommandLogDTO junoCommandLogDTO = junoCommandLogMapper.toDto(junoCommandLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJunoCommandLogMockMvc
            .perform(
                put("/api/juno-command-logs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(junoCommandLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JunoCommandLog in the database
        List<JunoCommandLog> junoCommandLogList = junoCommandLogRepository.findAll();
        assertThat(junoCommandLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJunoCommandLog() throws Exception {
        // Initialize the database
        junoCommandLogRepository.saveAndFlush(junoCommandLog);

        int databaseSizeBeforeDelete = junoCommandLogRepository.findAll().size();

        // Delete the junoCommandLog
        restJunoCommandLogMockMvc
            .perform(delete("/api/juno-command-logs/{id}", junoCommandLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JunoCommandLog> junoCommandLogList = junoCommandLogRepository.findAll();
        assertThat(junoCommandLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
