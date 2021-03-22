package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.CircuitVlan;
import com.activeport.web.domain.enumeration.ConnectTypeEnum;
import com.activeport.web.domain.enumeration.InternetTypeEnum;
import com.activeport.web.repository.CircuitVlanRepository;
import com.activeport.web.service.CircuitVlanService;
import com.activeport.web.service.dto.CircuitVlanDTO;
import com.activeport.web.service.mapper.CircuitVlanMapper;
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
 * Integration tests for the {@link CircuitVlanResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CircuitVlanResourceIT {
    private static final String DEFAULT_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_KEY = "BBBBBBBBBB";

    private static final Integer DEFAULT_VLAN_ID = 1;
    private static final Integer UPDATED_VLAN_ID = 2;

    private static final String DEFAULT_RD = "AAAAAAAAAA";
    private static final String UPDATED_RD = "BBBBBBBBBB";

    private static final Long DEFAULT_SERVICE_ID = 1L;
    private static final Long UPDATED_SERVICE_ID = 2L;

    private static final String DEFAULT_TENANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_CHILD_SERVICE_ID = 1L;
    private static final Long UPDATED_CHILD_SERVICE_ID = 2L;

    private static final Long DEFAULT_CHILD_NTU_ID = 1L;
    private static final Long UPDATED_CHILD_NTU_ID = 2L;

    private static final String DEFAULT_REALM_IP = "AAAAAAAAAA";
    private static final String UPDATED_REALM_IP = "BBBBBBBBBB";

    private static final InternetTypeEnum DEFAULT_INTERNET_TYPE = InternetTypeEnum.LAYER2;
    private static final InternetTypeEnum UPDATED_INTERNET_TYPE = InternetTypeEnum.STATIC_ADDRESS;

    private static final ConnectTypeEnum DEFAULT_TYPE = ConnectTypeEnum.AWS;
    private static final ConnectTypeEnum UPDATED_TYPE = ConnectTypeEnum.IX;

    @Autowired
    private CircuitVlanRepository circuitVlanRepository;

    @Autowired
    private CircuitVlanMapper circuitVlanMapper;

    @Autowired
    private CircuitVlanService circuitVlanService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCircuitVlanMockMvc;

    private CircuitVlan circuitVlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CircuitVlan createEntity(EntityManager em) {
        CircuitVlan circuitVlan = new CircuitVlan()
            .zone(DEFAULT_ZONE)
            .serviceKey(DEFAULT_SERVICE_KEY)
            .vlanId(DEFAULT_VLAN_ID)
            .rd(DEFAULT_RD)
            .serviceId(DEFAULT_SERVICE_ID)
            .tenantName(DEFAULT_TENANT_NAME)
            .childServiceId(DEFAULT_CHILD_SERVICE_ID)
            .childNtuId(DEFAULT_CHILD_NTU_ID)
            .realmIp(DEFAULT_REALM_IP)
            .internetType(DEFAULT_INTERNET_TYPE)
            .type(DEFAULT_TYPE);
        return circuitVlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CircuitVlan createUpdatedEntity(EntityManager em) {
        CircuitVlan circuitVlan = new CircuitVlan()
            .zone(UPDATED_ZONE)
            .serviceKey(UPDATED_SERVICE_KEY)
            .vlanId(UPDATED_VLAN_ID)
            .rd(UPDATED_RD)
            .serviceId(UPDATED_SERVICE_ID)
            .tenantName(UPDATED_TENANT_NAME)
            .childServiceId(UPDATED_CHILD_SERVICE_ID)
            .childNtuId(UPDATED_CHILD_NTU_ID)
            .realmIp(UPDATED_REALM_IP)
            .internetType(UPDATED_INTERNET_TYPE)
            .type(UPDATED_TYPE);
        return circuitVlan;
    }

    @BeforeEach
    public void initTest() {
        circuitVlan = createEntity(em);
    }

    @Test
    @Transactional
    public void createCircuitVlan() throws Exception {
        int databaseSizeBeforeCreate = circuitVlanRepository.findAll().size();
        // Create the CircuitVlan
        CircuitVlanDTO circuitVlanDTO = circuitVlanMapper.toDto(circuitVlan);
        restCircuitVlanMockMvc
            .perform(
                post("/api/circuit-vlans")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(circuitVlanDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CircuitVlan in the database
        List<CircuitVlan> circuitVlanList = circuitVlanRepository.findAll();
        assertThat(circuitVlanList).hasSize(databaseSizeBeforeCreate + 1);
        CircuitVlan testCircuitVlan = circuitVlanList.get(circuitVlanList.size() - 1);
        assertThat(testCircuitVlan.getZone()).isEqualTo(DEFAULT_ZONE);
        assertThat(testCircuitVlan.getServiceKey()).isEqualTo(DEFAULT_SERVICE_KEY);
        assertThat(testCircuitVlan.getVlanId()).isEqualTo(DEFAULT_VLAN_ID);
        assertThat(testCircuitVlan.getRd()).isEqualTo(DEFAULT_RD);
        assertThat(testCircuitVlan.getServiceId()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testCircuitVlan.getTenantName()).isEqualTo(DEFAULT_TENANT_NAME);
        assertThat(testCircuitVlan.getChildServiceId()).isEqualTo(DEFAULT_CHILD_SERVICE_ID);
        assertThat(testCircuitVlan.getChildNtuId()).isEqualTo(DEFAULT_CHILD_NTU_ID);
        assertThat(testCircuitVlan.getRealmIp()).isEqualTo(DEFAULT_REALM_IP);
        assertThat(testCircuitVlan.getInternetType()).isEqualTo(DEFAULT_INTERNET_TYPE);
        assertThat(testCircuitVlan.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createCircuitVlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = circuitVlanRepository.findAll().size();

        // Create the CircuitVlan with an existing ID
        circuitVlan.setId(1L);
        CircuitVlanDTO circuitVlanDTO = circuitVlanMapper.toDto(circuitVlan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCircuitVlanMockMvc
            .perform(
                post("/api/circuit-vlans")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(circuitVlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CircuitVlan in the database
        List<CircuitVlan> circuitVlanList = circuitVlanRepository.findAll();
        assertThat(circuitVlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCircuitVlans() throws Exception {
        // Initialize the database
        circuitVlanRepository.saveAndFlush(circuitVlan);

        // Get all the circuitVlanList
        restCircuitVlanMockMvc
            .perform(get("/api/circuit-vlans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(circuitVlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].zone").value(hasItem(DEFAULT_ZONE)))
            .andExpect(jsonPath("$.[*].serviceKey").value(hasItem(DEFAULT_SERVICE_KEY)))
            .andExpect(jsonPath("$.[*].vlanId").value(hasItem(DEFAULT_VLAN_ID)))
            .andExpect(jsonPath("$.[*].rd").value(hasItem(DEFAULT_RD)))
            .andExpect(jsonPath("$.[*].serviceId").value(hasItem(DEFAULT_SERVICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenantName").value(hasItem(DEFAULT_TENANT_NAME)))
            .andExpect(jsonPath("$.[*].childServiceId").value(hasItem(DEFAULT_CHILD_SERVICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].childNtuId").value(hasItem(DEFAULT_CHILD_NTU_ID.intValue())))
            .andExpect(jsonPath("$.[*].realmIp").value(hasItem(DEFAULT_REALM_IP)))
            .andExpect(jsonPath("$.[*].internetType").value(hasItem(DEFAULT_INTERNET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getCircuitVlan() throws Exception {
        // Initialize the database
        circuitVlanRepository.saveAndFlush(circuitVlan);

        // Get the circuitVlan
        restCircuitVlanMockMvc
            .perform(get("/api/circuit-vlans/{id}", circuitVlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(circuitVlan.getId().intValue()))
            .andExpect(jsonPath("$.zone").value(DEFAULT_ZONE))
            .andExpect(jsonPath("$.serviceKey").value(DEFAULT_SERVICE_KEY))
            .andExpect(jsonPath("$.vlanId").value(DEFAULT_VLAN_ID))
            .andExpect(jsonPath("$.rd").value(DEFAULT_RD))
            .andExpect(jsonPath("$.serviceId").value(DEFAULT_SERVICE_ID.intValue()))
            .andExpect(jsonPath("$.tenantName").value(DEFAULT_TENANT_NAME))
            .andExpect(jsonPath("$.childServiceId").value(DEFAULT_CHILD_SERVICE_ID.intValue()))
            .andExpect(jsonPath("$.childNtuId").value(DEFAULT_CHILD_NTU_ID.intValue()))
            .andExpect(jsonPath("$.realmIp").value(DEFAULT_REALM_IP))
            .andExpect(jsonPath("$.internetType").value(DEFAULT_INTERNET_TYPE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCircuitVlan() throws Exception {
        // Get the circuitVlan
        restCircuitVlanMockMvc.perform(get("/api/circuit-vlans/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCircuitVlan() throws Exception {
        // Initialize the database
        circuitVlanRepository.saveAndFlush(circuitVlan);

        int databaseSizeBeforeUpdate = circuitVlanRepository.findAll().size();

        // Update the circuitVlan
        CircuitVlan updatedCircuitVlan = circuitVlanRepository.findById(circuitVlan.getId()).get();
        // Disconnect from session so that the updates on updatedCircuitVlan are not directly saved in db
        em.detach(updatedCircuitVlan);
        updatedCircuitVlan
            .zone(UPDATED_ZONE)
            .serviceKey(UPDATED_SERVICE_KEY)
            .vlanId(UPDATED_VLAN_ID)
            .rd(UPDATED_RD)
            .serviceId(UPDATED_SERVICE_ID)
            .tenantName(UPDATED_TENANT_NAME)
            .childServiceId(UPDATED_CHILD_SERVICE_ID)
            .childNtuId(UPDATED_CHILD_NTU_ID)
            .realmIp(UPDATED_REALM_IP)
            .internetType(UPDATED_INTERNET_TYPE)
            .type(UPDATED_TYPE);
        CircuitVlanDTO circuitVlanDTO = circuitVlanMapper.toDto(updatedCircuitVlan);

        restCircuitVlanMockMvc
            .perform(
                put("/api/circuit-vlans").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(circuitVlanDTO))
            )
            .andExpect(status().isOk());

        // Validate the CircuitVlan in the database
        List<CircuitVlan> circuitVlanList = circuitVlanRepository.findAll();
        assertThat(circuitVlanList).hasSize(databaseSizeBeforeUpdate);
        CircuitVlan testCircuitVlan = circuitVlanList.get(circuitVlanList.size() - 1);
        assertThat(testCircuitVlan.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testCircuitVlan.getServiceKey()).isEqualTo(UPDATED_SERVICE_KEY);
        assertThat(testCircuitVlan.getVlanId()).isEqualTo(UPDATED_VLAN_ID);
        assertThat(testCircuitVlan.getRd()).isEqualTo(UPDATED_RD);
        assertThat(testCircuitVlan.getServiceId()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testCircuitVlan.getTenantName()).isEqualTo(UPDATED_TENANT_NAME);
        assertThat(testCircuitVlan.getChildServiceId()).isEqualTo(UPDATED_CHILD_SERVICE_ID);
        assertThat(testCircuitVlan.getChildNtuId()).isEqualTo(UPDATED_CHILD_NTU_ID);
        assertThat(testCircuitVlan.getRealmIp()).isEqualTo(UPDATED_REALM_IP);
        assertThat(testCircuitVlan.getInternetType()).isEqualTo(UPDATED_INTERNET_TYPE);
        assertThat(testCircuitVlan.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCircuitVlan() throws Exception {
        int databaseSizeBeforeUpdate = circuitVlanRepository.findAll().size();

        // Create the CircuitVlan
        CircuitVlanDTO circuitVlanDTO = circuitVlanMapper.toDto(circuitVlan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCircuitVlanMockMvc
            .perform(
                put("/api/circuit-vlans").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(circuitVlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CircuitVlan in the database
        List<CircuitVlan> circuitVlanList = circuitVlanRepository.findAll();
        assertThat(circuitVlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCircuitVlan() throws Exception {
        // Initialize the database
        circuitVlanRepository.saveAndFlush(circuitVlan);

        int databaseSizeBeforeDelete = circuitVlanRepository.findAll().size();

        // Delete the circuitVlan
        restCircuitVlanMockMvc
            .perform(delete("/api/circuit-vlans/{id}", circuitVlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CircuitVlan> circuitVlanList = circuitVlanRepository.findAll();
        assertThat(circuitVlanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
