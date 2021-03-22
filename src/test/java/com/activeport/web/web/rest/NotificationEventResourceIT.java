package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.NotificationEvent;
import com.activeport.web.domain.enumeration.EventStatusEnum;
import com.activeport.web.domain.enumeration.EventTypeEnum;
import com.activeport.web.repository.NotificationEventRepository;
import com.activeport.web.service.NotificationEventService;
import com.activeport.web.service.dto.NotificationEventDTO;
import com.activeport.web.service.mapper.NotificationEventMapper;
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
 * Integration tests for the {@link NotificationEventResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NotificationEventResourceIT {
    private static final String DEFAULT_NTU_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NTU_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_NTU_ID = 1L;
    private static final Long UPDATED_NTU_ID = 2L;

    private static final EventStatusEnum DEFAULT_STATUS = EventStatusEnum.COMPLETED;
    private static final EventStatusEnum UPDATED_STATUS = EventStatusEnum.REQUEST;

    private static final EventTypeEnum DEFAULT_TYPE = EventTypeEnum.FIREWALL_ACTIVATE;
    private static final EventTypeEnum UPDATED_TYPE = EventTypeEnum.FIREWALL_DEACTIVATE;

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SERVICE_ID = 1L;
    private static final Long UPDATED_SERVICE_ID = 2L;

    private static final Long DEFAULT_VXC_ID = 1L;
    private static final Long UPDATED_VXC_ID = 2L;

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private NotificationEventRepository notificationEventRepository;

    @Autowired
    private NotificationEventMapper notificationEventMapper;

    @Autowired
    private NotificationEventService notificationEventService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotificationEventMockMvc;

    private NotificationEvent notificationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationEvent createEntity(EntityManager em) {
        NotificationEvent notificationEvent = new NotificationEvent()
            .ntuName(DEFAULT_NTU_NAME)
            .ntuId(DEFAULT_NTU_ID)
            .status(DEFAULT_STATUS)
            .type(DEFAULT_TYPE)
            .message(DEFAULT_MESSAGE)
            .serviceName(DEFAULT_SERVICE_NAME)
            .serviceId(DEFAULT_SERVICE_ID)
            .vxcId(DEFAULT_VXC_ID)
            .uuid(DEFAULT_UUID)
            .user(DEFAULT_USER)
            .errorMessage(DEFAULT_ERROR_MESSAGE);
        return notificationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationEvent createUpdatedEntity(EntityManager em) {
        NotificationEvent notificationEvent = new NotificationEvent()
            .ntuName(UPDATED_NTU_NAME)
            .ntuId(UPDATED_NTU_ID)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE)
            .message(UPDATED_MESSAGE)
            .serviceName(UPDATED_SERVICE_NAME)
            .serviceId(UPDATED_SERVICE_ID)
            .vxcId(UPDATED_VXC_ID)
            .uuid(UPDATED_UUID)
            .user(UPDATED_USER)
            .errorMessage(UPDATED_ERROR_MESSAGE);
        return notificationEvent;
    }

    @BeforeEach
    public void initTest() {
        notificationEvent = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificationEvent() throws Exception {
        int databaseSizeBeforeCreate = notificationEventRepository.findAll().size();
        // Create the NotificationEvent
        NotificationEventDTO notificationEventDTO = notificationEventMapper.toDto(notificationEvent);
        restNotificationEventMockMvc
            .perform(
                post("/api/notification-events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notificationEventDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NotificationEvent in the database
        List<NotificationEvent> notificationEventList = notificationEventRepository.findAll();
        assertThat(notificationEventList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationEvent testNotificationEvent = notificationEventList.get(notificationEventList.size() - 1);
        assertThat(testNotificationEvent.getNtuName()).isEqualTo(DEFAULT_NTU_NAME);
        assertThat(testNotificationEvent.getNtuId()).isEqualTo(DEFAULT_NTU_ID);
        assertThat(testNotificationEvent.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNotificationEvent.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testNotificationEvent.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testNotificationEvent.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testNotificationEvent.getServiceId()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testNotificationEvent.getVxcId()).isEqualTo(DEFAULT_VXC_ID);
        assertThat(testNotificationEvent.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testNotificationEvent.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testNotificationEvent.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
    }

    @Test
    @Transactional
    public void createNotificationEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationEventRepository.findAll().size();

        // Create the NotificationEvent with an existing ID
        notificationEvent.setId(1L);
        NotificationEventDTO notificationEventDTO = notificationEventMapper.toDto(notificationEvent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationEventMockMvc
            .perform(
                post("/api/notification-events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notificationEventDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotificationEvent in the database
        List<NotificationEvent> notificationEventList = notificationEventRepository.findAll();
        assertThat(notificationEventList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNotificationEvents() throws Exception {
        // Initialize the database
        notificationEventRepository.saveAndFlush(notificationEvent);

        // Get all the notificationEventList
        restNotificationEventMockMvc
            .perform(get("/api/notification-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].ntuName").value(hasItem(DEFAULT_NTU_NAME)))
            .andExpect(jsonPath("$.[*].ntuId").value(hasItem(DEFAULT_NTU_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].serviceId").value(hasItem(DEFAULT_SERVICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].vxcId").value(hasItem(DEFAULT_VXC_ID.intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].errorMessage").value(hasItem(DEFAULT_ERROR_MESSAGE.toString())));
    }

    @Test
    @Transactional
    public void getNotificationEvent() throws Exception {
        // Initialize the database
        notificationEventRepository.saveAndFlush(notificationEvent);

        // Get the notificationEvent
        restNotificationEventMockMvc
            .perform(get("/api/notification-events/{id}", notificationEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationEvent.getId().intValue()))
            .andExpect(jsonPath("$.ntuName").value(DEFAULT_NTU_NAME))
            .andExpect(jsonPath("$.ntuId").value(DEFAULT_NTU_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME))
            .andExpect(jsonPath("$.serviceId").value(DEFAULT_SERVICE_ID.intValue()))
            .andExpect(jsonPath("$.vxcId").value(DEFAULT_VXC_ID.intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.errorMessage").value(DEFAULT_ERROR_MESSAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNotificationEvent() throws Exception {
        // Get the notificationEvent
        restNotificationEventMockMvc.perform(get("/api/notification-events/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificationEvent() throws Exception {
        // Initialize the database
        notificationEventRepository.saveAndFlush(notificationEvent);

        int databaseSizeBeforeUpdate = notificationEventRepository.findAll().size();

        // Update the notificationEvent
        NotificationEvent updatedNotificationEvent = notificationEventRepository.findById(notificationEvent.getId()).get();
        // Disconnect from session so that the updates on updatedNotificationEvent are not directly saved in db
        em.detach(updatedNotificationEvent);
        updatedNotificationEvent
            .ntuName(UPDATED_NTU_NAME)
            .ntuId(UPDATED_NTU_ID)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE)
            .message(UPDATED_MESSAGE)
            .serviceName(UPDATED_SERVICE_NAME)
            .serviceId(UPDATED_SERVICE_ID)
            .vxcId(UPDATED_VXC_ID)
            .uuid(UPDATED_UUID)
            .user(UPDATED_USER)
            .errorMessage(UPDATED_ERROR_MESSAGE);
        NotificationEventDTO notificationEventDTO = notificationEventMapper.toDto(updatedNotificationEvent);

        restNotificationEventMockMvc
            .perform(
                put("/api/notification-events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notificationEventDTO))
            )
            .andExpect(status().isOk());

        // Validate the NotificationEvent in the database
        List<NotificationEvent> notificationEventList = notificationEventRepository.findAll();
        assertThat(notificationEventList).hasSize(databaseSizeBeforeUpdate);
        NotificationEvent testNotificationEvent = notificationEventList.get(notificationEventList.size() - 1);
        assertThat(testNotificationEvent.getNtuName()).isEqualTo(UPDATED_NTU_NAME);
        assertThat(testNotificationEvent.getNtuId()).isEqualTo(UPDATED_NTU_ID);
        assertThat(testNotificationEvent.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNotificationEvent.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testNotificationEvent.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testNotificationEvent.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testNotificationEvent.getServiceId()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testNotificationEvent.getVxcId()).isEqualTo(UPDATED_VXC_ID);
        assertThat(testNotificationEvent.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testNotificationEvent.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testNotificationEvent.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificationEvent() throws Exception {
        int databaseSizeBeforeUpdate = notificationEventRepository.findAll().size();

        // Create the NotificationEvent
        NotificationEventDTO notificationEventDTO = notificationEventMapper.toDto(notificationEvent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationEventMockMvc
            .perform(
                put("/api/notification-events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notificationEventDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotificationEvent in the database
        List<NotificationEvent> notificationEventList = notificationEventRepository.findAll();
        assertThat(notificationEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotificationEvent() throws Exception {
        // Initialize the database
        notificationEventRepository.saveAndFlush(notificationEvent);

        int databaseSizeBeforeDelete = notificationEventRepository.findAll().size();

        // Delete the notificationEvent
        restNotificationEventMockMvc
            .perform(delete("/api/notification-events/{id}", notificationEvent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationEvent> notificationEventList = notificationEventRepository.findAll();
        assertThat(notificationEventList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
