package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.VntuDownlinkPort;
import com.activeport.web.repository.VntuDownlinkPortRepository;
import com.activeport.web.service.VntuDownlinkPortService;
import com.activeport.web.service.dto.VntuDownlinkPortDTO;
import com.activeport.web.service.mapper.VntuDownlinkPortMapper;
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
 * Integration tests for the {@link VntuDownlinkPortResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VntuDownlinkPortResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UID = "AAAAAAAAAA";
    private static final String UPDATED_UID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_TENANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_TENANT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_ORG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_TENANT_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_ASSIGNED_VNTU_ID = 1L;
    private static final Long UPDATED_ASSIGNED_VNTU_ID = 2L;

    private static final String DEFAULT_ASSIGNED_VNTU_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_VNTU_NAME = "BBBBBBBBBB";

    @Autowired
    private VntuDownlinkPortRepository vntuDownlinkPortRepository;

    @Autowired
    private VntuDownlinkPortMapper vntuDownlinkPortMapper;

    @Autowired
    private VntuDownlinkPortService vntuDownlinkPortService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVntuDownlinkPortMockMvc;

    private VntuDownlinkPort vntuDownlinkPort;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VntuDownlinkPort createEntity(EntityManager em) {
        VntuDownlinkPort vntuDownlinkPort = new VntuDownlinkPort()
            .name(DEFAULT_NAME)
            .uid(DEFAULT_UID)
            .description(DEFAULT_DESCRIPTION)
            .assignedOrgName(DEFAULT_ASSIGNED_ORG_NAME)
            .assignedTenantName(DEFAULT_ASSIGNED_TENANT_NAME)
            .assignedOrgId(DEFAULT_ASSIGNED_ORG_ID)
            .assignedTenantId(DEFAULT_ASSIGNED_TENANT_ID)
            .assignedVntuId(DEFAULT_ASSIGNED_VNTU_ID)
            .assignedVntuName(DEFAULT_ASSIGNED_VNTU_NAME);
        return vntuDownlinkPort;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VntuDownlinkPort createUpdatedEntity(EntityManager em) {
        VntuDownlinkPort vntuDownlinkPort = new VntuDownlinkPort()
            .name(UPDATED_NAME)
            .uid(UPDATED_UID)
            .description(UPDATED_DESCRIPTION)
            .assignedOrgName(UPDATED_ASSIGNED_ORG_NAME)
            .assignedTenantName(UPDATED_ASSIGNED_TENANT_NAME)
            .assignedOrgId(UPDATED_ASSIGNED_ORG_ID)
            .assignedTenantId(UPDATED_ASSIGNED_TENANT_ID)
            .assignedVntuId(UPDATED_ASSIGNED_VNTU_ID)
            .assignedVntuName(UPDATED_ASSIGNED_VNTU_NAME);
        return vntuDownlinkPort;
    }

    @BeforeEach
    public void initTest() {
        vntuDownlinkPort = createEntity(em);
    }

    @Test
    @Transactional
    public void createVntuDownlinkPort() throws Exception {
        int databaseSizeBeforeCreate = vntuDownlinkPortRepository.findAll().size();
        // Create the VntuDownlinkPort
        VntuDownlinkPortDTO vntuDownlinkPortDTO = vntuDownlinkPortMapper.toDto(vntuDownlinkPort);
        restVntuDownlinkPortMockMvc
            .perform(
                post("/api/vntu-downlink-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vntuDownlinkPortDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VntuDownlinkPort in the database
        List<VntuDownlinkPort> vntuDownlinkPortList = vntuDownlinkPortRepository.findAll();
        assertThat(vntuDownlinkPortList).hasSize(databaseSizeBeforeCreate + 1);
        VntuDownlinkPort testVntuDownlinkPort = vntuDownlinkPortList.get(vntuDownlinkPortList.size() - 1);
        assertThat(testVntuDownlinkPort.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVntuDownlinkPort.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testVntuDownlinkPort.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVntuDownlinkPort.getAssignedOrgName()).isEqualTo(DEFAULT_ASSIGNED_ORG_NAME);
        assertThat(testVntuDownlinkPort.getAssignedTenantName()).isEqualTo(DEFAULT_ASSIGNED_TENANT_NAME);
        assertThat(testVntuDownlinkPort.getAssignedOrgId()).isEqualTo(DEFAULT_ASSIGNED_ORG_ID);
        assertThat(testVntuDownlinkPort.getAssignedTenantId()).isEqualTo(DEFAULT_ASSIGNED_TENANT_ID);
        assertThat(testVntuDownlinkPort.getAssignedVntuId()).isEqualTo(DEFAULT_ASSIGNED_VNTU_ID);
        assertThat(testVntuDownlinkPort.getAssignedVntuName()).isEqualTo(DEFAULT_ASSIGNED_VNTU_NAME);
    }

    @Test
    @Transactional
    public void createVntuDownlinkPortWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vntuDownlinkPortRepository.findAll().size();

        // Create the VntuDownlinkPort with an existing ID
        vntuDownlinkPort.setId(1L);
        VntuDownlinkPortDTO vntuDownlinkPortDTO = vntuDownlinkPortMapper.toDto(vntuDownlinkPort);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVntuDownlinkPortMockMvc
            .perform(
                post("/api/vntu-downlink-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vntuDownlinkPortDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VntuDownlinkPort in the database
        List<VntuDownlinkPort> vntuDownlinkPortList = vntuDownlinkPortRepository.findAll();
        assertThat(vntuDownlinkPortList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVntuDownlinkPorts() throws Exception {
        // Initialize the database
        vntuDownlinkPortRepository.saveAndFlush(vntuDownlinkPort);

        // Get all the vntuDownlinkPortList
        restVntuDownlinkPortMockMvc
            .perform(get("/api/vntu-downlink-ports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vntuDownlinkPort.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].assignedOrgName").value(hasItem(DEFAULT_ASSIGNED_ORG_NAME)))
            .andExpect(jsonPath("$.[*].assignedTenantName").value(hasItem(DEFAULT_ASSIGNED_TENANT_NAME)))
            .andExpect(jsonPath("$.[*].assignedOrgId").value(hasItem(DEFAULT_ASSIGNED_ORG_ID)))
            .andExpect(jsonPath("$.[*].assignedTenantId").value(hasItem(DEFAULT_ASSIGNED_TENANT_ID)))
            .andExpect(jsonPath("$.[*].assignedVntuId").value(hasItem(DEFAULT_ASSIGNED_VNTU_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignedVntuName").value(hasItem(DEFAULT_ASSIGNED_VNTU_NAME)));
    }

    @Test
    @Transactional
    public void getVntuDownlinkPort() throws Exception {
        // Initialize the database
        vntuDownlinkPortRepository.saveAndFlush(vntuDownlinkPort);

        // Get the vntuDownlinkPort
        restVntuDownlinkPortMockMvc
            .perform(get("/api/vntu-downlink-ports/{id}", vntuDownlinkPort.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vntuDownlinkPort.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.assignedOrgName").value(DEFAULT_ASSIGNED_ORG_NAME))
            .andExpect(jsonPath("$.assignedTenantName").value(DEFAULT_ASSIGNED_TENANT_NAME))
            .andExpect(jsonPath("$.assignedOrgId").value(DEFAULT_ASSIGNED_ORG_ID))
            .andExpect(jsonPath("$.assignedTenantId").value(DEFAULT_ASSIGNED_TENANT_ID))
            .andExpect(jsonPath("$.assignedVntuId").value(DEFAULT_ASSIGNED_VNTU_ID.intValue()))
            .andExpect(jsonPath("$.assignedVntuName").value(DEFAULT_ASSIGNED_VNTU_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingVntuDownlinkPort() throws Exception {
        // Get the vntuDownlinkPort
        restVntuDownlinkPortMockMvc.perform(get("/api/vntu-downlink-ports/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVntuDownlinkPort() throws Exception {
        // Initialize the database
        vntuDownlinkPortRepository.saveAndFlush(vntuDownlinkPort);

        int databaseSizeBeforeUpdate = vntuDownlinkPortRepository.findAll().size();

        // Update the vntuDownlinkPort
        VntuDownlinkPort updatedVntuDownlinkPort = vntuDownlinkPortRepository.findById(vntuDownlinkPort.getId()).get();
        // Disconnect from session so that the updates on updatedVntuDownlinkPort are not directly saved in db
        em.detach(updatedVntuDownlinkPort);
        updatedVntuDownlinkPort
            .name(UPDATED_NAME)
            .uid(UPDATED_UID)
            .description(UPDATED_DESCRIPTION)
            .assignedOrgName(UPDATED_ASSIGNED_ORG_NAME)
            .assignedTenantName(UPDATED_ASSIGNED_TENANT_NAME)
            .assignedOrgId(UPDATED_ASSIGNED_ORG_ID)
            .assignedTenantId(UPDATED_ASSIGNED_TENANT_ID)
            .assignedVntuId(UPDATED_ASSIGNED_VNTU_ID)
            .assignedVntuName(UPDATED_ASSIGNED_VNTU_NAME);
        VntuDownlinkPortDTO vntuDownlinkPortDTO = vntuDownlinkPortMapper.toDto(updatedVntuDownlinkPort);

        restVntuDownlinkPortMockMvc
            .perform(
                put("/api/vntu-downlink-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vntuDownlinkPortDTO))
            )
            .andExpect(status().isOk());

        // Validate the VntuDownlinkPort in the database
        List<VntuDownlinkPort> vntuDownlinkPortList = vntuDownlinkPortRepository.findAll();
        assertThat(vntuDownlinkPortList).hasSize(databaseSizeBeforeUpdate);
        VntuDownlinkPort testVntuDownlinkPort = vntuDownlinkPortList.get(vntuDownlinkPortList.size() - 1);
        assertThat(testVntuDownlinkPort.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVntuDownlinkPort.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testVntuDownlinkPort.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVntuDownlinkPort.getAssignedOrgName()).isEqualTo(UPDATED_ASSIGNED_ORG_NAME);
        assertThat(testVntuDownlinkPort.getAssignedTenantName()).isEqualTo(UPDATED_ASSIGNED_TENANT_NAME);
        assertThat(testVntuDownlinkPort.getAssignedOrgId()).isEqualTo(UPDATED_ASSIGNED_ORG_ID);
        assertThat(testVntuDownlinkPort.getAssignedTenantId()).isEqualTo(UPDATED_ASSIGNED_TENANT_ID);
        assertThat(testVntuDownlinkPort.getAssignedVntuId()).isEqualTo(UPDATED_ASSIGNED_VNTU_ID);
        assertThat(testVntuDownlinkPort.getAssignedVntuName()).isEqualTo(UPDATED_ASSIGNED_VNTU_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingVntuDownlinkPort() throws Exception {
        int databaseSizeBeforeUpdate = vntuDownlinkPortRepository.findAll().size();

        // Create the VntuDownlinkPort
        VntuDownlinkPortDTO vntuDownlinkPortDTO = vntuDownlinkPortMapper.toDto(vntuDownlinkPort);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVntuDownlinkPortMockMvc
            .perform(
                put("/api/vntu-downlink-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vntuDownlinkPortDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VntuDownlinkPort in the database
        List<VntuDownlinkPort> vntuDownlinkPortList = vntuDownlinkPortRepository.findAll();
        assertThat(vntuDownlinkPortList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVntuDownlinkPort() throws Exception {
        // Initialize the database
        vntuDownlinkPortRepository.saveAndFlush(vntuDownlinkPort);

        int databaseSizeBeforeDelete = vntuDownlinkPortRepository.findAll().size();

        // Delete the vntuDownlinkPort
        restVntuDownlinkPortMockMvc
            .perform(delete("/api/vntu-downlink-ports/{id}", vntuDownlinkPort.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VntuDownlinkPort> vntuDownlinkPortList = vntuDownlinkPortRepository.findAll();
        assertThat(vntuDownlinkPortList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
