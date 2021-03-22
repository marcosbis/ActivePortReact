package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.PolicerSchedule;
import com.activeport.web.domain.enumeration.ScheduleDayEnum;
import com.activeport.web.repository.PolicerScheduleRepository;
import com.activeport.web.service.PolicerScheduleService;
import com.activeport.web.service.dto.PolicerScheduleDTO;
import com.activeport.web.service.mapper.PolicerScheduleMapper;
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
 * Integration tests for the {@link PolicerScheduleResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PolicerScheduleResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ScheduleDayEnum DEFAULT_DAYS = ScheduleDayEnum.MONDAY;
    private static final ScheduleDayEnum UPDATED_DAYS = ScheduleDayEnum.TUESDAY;

    @Autowired
    private PolicerScheduleRepository policerScheduleRepository;

    @Mock
    private PolicerScheduleRepository policerScheduleRepositoryMock;

    @Autowired
    private PolicerScheduleMapper policerScheduleMapper;

    @Mock
    private PolicerScheduleService policerScheduleServiceMock;

    @Autowired
    private PolicerScheduleService policerScheduleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPolicerScheduleMockMvc;

    private PolicerSchedule policerSchedule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PolicerSchedule createEntity(EntityManager em) {
        PolicerSchedule policerSchedule = new PolicerSchedule().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).days(DEFAULT_DAYS);
        return policerSchedule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PolicerSchedule createUpdatedEntity(EntityManager em) {
        PolicerSchedule policerSchedule = new PolicerSchedule().name(UPDATED_NAME).description(UPDATED_DESCRIPTION).days(UPDATED_DAYS);
        return policerSchedule;
    }

    @BeforeEach
    public void initTest() {
        policerSchedule = createEntity(em);
    }

    @Test
    @Transactional
    public void createPolicerSchedule() throws Exception {
        int databaseSizeBeforeCreate = policerScheduleRepository.findAll().size();
        // Create the PolicerSchedule
        PolicerScheduleDTO policerScheduleDTO = policerScheduleMapper.toDto(policerSchedule);
        restPolicerScheduleMockMvc
            .perform(
                post("/api/policer-schedules")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerScheduleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PolicerSchedule in the database
        List<PolicerSchedule> policerScheduleList = policerScheduleRepository.findAll();
        assertThat(policerScheduleList).hasSize(databaseSizeBeforeCreate + 1);
        PolicerSchedule testPolicerSchedule = policerScheduleList.get(policerScheduleList.size() - 1);
        assertThat(testPolicerSchedule.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPolicerSchedule.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPolicerSchedule.getDays()).isEqualTo(DEFAULT_DAYS);
    }

    @Test
    @Transactional
    public void createPolicerScheduleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = policerScheduleRepository.findAll().size();

        // Create the PolicerSchedule with an existing ID
        policerSchedule.setId(1L);
        PolicerScheduleDTO policerScheduleDTO = policerScheduleMapper.toDto(policerSchedule);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPolicerScheduleMockMvc
            .perform(
                post("/api/policer-schedules")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PolicerSchedule in the database
        List<PolicerSchedule> policerScheduleList = policerScheduleRepository.findAll();
        assertThat(policerScheduleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPolicerSchedules() throws Exception {
        // Initialize the database
        policerScheduleRepository.saveAndFlush(policerSchedule);

        // Get all the policerScheduleList
        restPolicerScheduleMockMvc
            .perform(get("/api/policer-schedules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(policerSchedule.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].days").value(hasItem(DEFAULT_DAYS.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllPolicerSchedulesWithEagerRelationshipsIsEnabled() throws Exception {
        when(policerScheduleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPolicerScheduleMockMvc.perform(get("/api/policer-schedules?eagerload=true")).andExpect(status().isOk());

        verify(policerScheduleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllPolicerSchedulesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(policerScheduleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPolicerScheduleMockMvc.perform(get("/api/policer-schedules?eagerload=true")).andExpect(status().isOk());

        verify(policerScheduleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getPolicerSchedule() throws Exception {
        // Initialize the database
        policerScheduleRepository.saveAndFlush(policerSchedule);

        // Get the policerSchedule
        restPolicerScheduleMockMvc
            .perform(get("/api/policer-schedules/{id}", policerSchedule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(policerSchedule.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.days").value(DEFAULT_DAYS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPolicerSchedule() throws Exception {
        // Get the policerSchedule
        restPolicerScheduleMockMvc.perform(get("/api/policer-schedules/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePolicerSchedule() throws Exception {
        // Initialize the database
        policerScheduleRepository.saveAndFlush(policerSchedule);

        int databaseSizeBeforeUpdate = policerScheduleRepository.findAll().size();

        // Update the policerSchedule
        PolicerSchedule updatedPolicerSchedule = policerScheduleRepository.findById(policerSchedule.getId()).get();
        // Disconnect from session so that the updates on updatedPolicerSchedule are not directly saved in db
        em.detach(updatedPolicerSchedule);
        updatedPolicerSchedule.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).days(UPDATED_DAYS);
        PolicerScheduleDTO policerScheduleDTO = policerScheduleMapper.toDto(updatedPolicerSchedule);

        restPolicerScheduleMockMvc
            .perform(
                put("/api/policer-schedules")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerScheduleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PolicerSchedule in the database
        List<PolicerSchedule> policerScheduleList = policerScheduleRepository.findAll();
        assertThat(policerScheduleList).hasSize(databaseSizeBeforeUpdate);
        PolicerSchedule testPolicerSchedule = policerScheduleList.get(policerScheduleList.size() - 1);
        assertThat(testPolicerSchedule.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPolicerSchedule.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPolicerSchedule.getDays()).isEqualTo(UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void updateNonExistingPolicerSchedule() throws Exception {
        int databaseSizeBeforeUpdate = policerScheduleRepository.findAll().size();

        // Create the PolicerSchedule
        PolicerScheduleDTO policerScheduleDTO = policerScheduleMapper.toDto(policerSchedule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPolicerScheduleMockMvc
            .perform(
                put("/api/policer-schedules")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(policerScheduleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PolicerSchedule in the database
        List<PolicerSchedule> policerScheduleList = policerScheduleRepository.findAll();
        assertThat(policerScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePolicerSchedule() throws Exception {
        // Initialize the database
        policerScheduleRepository.saveAndFlush(policerSchedule);

        int databaseSizeBeforeDelete = policerScheduleRepository.findAll().size();

        // Delete the policerSchedule
        restPolicerScheduleMockMvc
            .perform(delete("/api/policer-schedules/{id}", policerSchedule.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PolicerSchedule> policerScheduleList = policerScheduleRepository.findAll();
        assertThat(policerScheduleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
