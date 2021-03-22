package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.AddressAllocation;
import com.activeport.web.domain.enumeration.AllocationTypeEnum;
import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import com.activeport.web.repository.AddressAllocationRepository;
import com.activeport.web.service.AddressAllocationService;
import com.activeport.web.service.dto.AddressAllocationDTO;
import com.activeport.web.service.mapper.AddressAllocationMapper;
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
 * Integration tests for the {@link AddressAllocationResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AddressAllocationResourceIT {
    private static final String DEFAULT_SUBNET = "AAAAAAAAAA";
    private static final String UPDATED_SUBNET = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_NAME = "BBBBBBBBBB";

    private static final NtuModeEnum DEFAULT_DEVICE_MODE = NtuModeEnum.DEMO;
    private static final NtuModeEnum UPDATED_DEVICE_MODE = NtuModeEnum.EDGE;

    private static final Long DEFAULT_DEVICE_ID = 1L;
    private static final Long UPDATED_DEVICE_ID = 2L;

    private static final PortServiceTypeEnum DEFAULT_DEVICE_TYPE = PortServiceTypeEnum.EDGE;
    private static final PortServiceTypeEnum UPDATED_DEVICE_TYPE = PortServiceTypeEnum.SWITCH;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final AllocationTypeEnum DEFAULT_ALLOCATION_TYPE = AllocationTypeEnum.RESERVED;
    private static final AllocationTypeEnum UPDATED_ALLOCATION_TYPE = AllocationTypeEnum.AUTO;

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    @Autowired
    private AddressAllocationRepository addressAllocationRepository;

    @Autowired
    private AddressAllocationMapper addressAllocationMapper;

    @Autowired
    private AddressAllocationService addressAllocationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAddressAllocationMockMvc;

    private AddressAllocation addressAllocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressAllocation createEntity(EntityManager em) {
        AddressAllocation addressAllocation = new AddressAllocation()
            .subnet(DEFAULT_SUBNET)
            .deviceName(DEFAULT_DEVICE_NAME)
            .deviceMode(DEFAULT_DEVICE_MODE)
            .deviceId(DEFAULT_DEVICE_ID)
            .deviceType(DEFAULT_DEVICE_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .allocationType(DEFAULT_ALLOCATION_TYPE)
            .serialNumber(DEFAULT_SERIAL_NUMBER);
        return addressAllocation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressAllocation createUpdatedEntity(EntityManager em) {
        AddressAllocation addressAllocation = new AddressAllocation()
            .subnet(UPDATED_SUBNET)
            .deviceName(UPDATED_DEVICE_NAME)
            .deviceMode(UPDATED_DEVICE_MODE)
            .deviceId(UPDATED_DEVICE_ID)
            .deviceType(UPDATED_DEVICE_TYPE)
            .description(UPDATED_DESCRIPTION)
            .allocationType(UPDATED_ALLOCATION_TYPE)
            .serialNumber(UPDATED_SERIAL_NUMBER);
        return addressAllocation;
    }

    @BeforeEach
    public void initTest() {
        addressAllocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddressAllocation() throws Exception {
        int databaseSizeBeforeCreate = addressAllocationRepository.findAll().size();
        // Create the AddressAllocation
        AddressAllocationDTO addressAllocationDTO = addressAllocationMapper.toDto(addressAllocation);
        restAddressAllocationMockMvc
            .perform(
                post("/api/address-allocations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressAllocationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AddressAllocation in the database
        List<AddressAllocation> addressAllocationList = addressAllocationRepository.findAll();
        assertThat(addressAllocationList).hasSize(databaseSizeBeforeCreate + 1);
        AddressAllocation testAddressAllocation = addressAllocationList.get(addressAllocationList.size() - 1);
        assertThat(testAddressAllocation.getSubnet()).isEqualTo(DEFAULT_SUBNET);
        assertThat(testAddressAllocation.getDeviceName()).isEqualTo(DEFAULT_DEVICE_NAME);
        assertThat(testAddressAllocation.getDeviceMode()).isEqualTo(DEFAULT_DEVICE_MODE);
        assertThat(testAddressAllocation.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testAddressAllocation.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testAddressAllocation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAddressAllocation.getAllocationType()).isEqualTo(DEFAULT_ALLOCATION_TYPE);
        assertThat(testAddressAllocation.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void createAddressAllocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressAllocationRepository.findAll().size();

        // Create the AddressAllocation with an existing ID
        addressAllocation.setId(1L);
        AddressAllocationDTO addressAllocationDTO = addressAllocationMapper.toDto(addressAllocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressAllocationMockMvc
            .perform(
                post("/api/address-allocations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressAllocationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressAllocation in the database
        List<AddressAllocation> addressAllocationList = addressAllocationRepository.findAll();
        assertThat(addressAllocationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAddressAllocations() throws Exception {
        // Initialize the database
        addressAllocationRepository.saveAndFlush(addressAllocation);

        // Get all the addressAllocationList
        restAddressAllocationMockMvc
            .perform(get("/api/address-allocations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressAllocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].subnet").value(hasItem(DEFAULT_SUBNET)))
            .andExpect(jsonPath("$.[*].deviceName").value(hasItem(DEFAULT_DEVICE_NAME)))
            .andExpect(jsonPath("$.[*].deviceMode").value(hasItem(DEFAULT_DEVICE_MODE.toString())))
            .andExpect(jsonPath("$.[*].deviceId").value(hasItem(DEFAULT_DEVICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].allocationType").value(hasItem(DEFAULT_ALLOCATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)));
    }

    @Test
    @Transactional
    public void getAddressAllocation() throws Exception {
        // Initialize the database
        addressAllocationRepository.saveAndFlush(addressAllocation);

        // Get the addressAllocation
        restAddressAllocationMockMvc
            .perform(get("/api/address-allocations/{id}", addressAllocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(addressAllocation.getId().intValue()))
            .andExpect(jsonPath("$.subnet").value(DEFAULT_SUBNET))
            .andExpect(jsonPath("$.deviceName").value(DEFAULT_DEVICE_NAME))
            .andExpect(jsonPath("$.deviceMode").value(DEFAULT_DEVICE_MODE.toString()))
            .andExpect(jsonPath("$.deviceId").value(DEFAULT_DEVICE_ID.intValue()))
            .andExpect(jsonPath("$.deviceType").value(DEFAULT_DEVICE_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.allocationType").value(DEFAULT_ALLOCATION_TYPE.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingAddressAllocation() throws Exception {
        // Get the addressAllocation
        restAddressAllocationMockMvc.perform(get("/api/address-allocations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddressAllocation() throws Exception {
        // Initialize the database
        addressAllocationRepository.saveAndFlush(addressAllocation);

        int databaseSizeBeforeUpdate = addressAllocationRepository.findAll().size();

        // Update the addressAllocation
        AddressAllocation updatedAddressAllocation = addressAllocationRepository.findById(addressAllocation.getId()).get();
        // Disconnect from session so that the updates on updatedAddressAllocation are not directly saved in db
        em.detach(updatedAddressAllocation);
        updatedAddressAllocation
            .subnet(UPDATED_SUBNET)
            .deviceName(UPDATED_DEVICE_NAME)
            .deviceMode(UPDATED_DEVICE_MODE)
            .deviceId(UPDATED_DEVICE_ID)
            .deviceType(UPDATED_DEVICE_TYPE)
            .description(UPDATED_DESCRIPTION)
            .allocationType(UPDATED_ALLOCATION_TYPE)
            .serialNumber(UPDATED_SERIAL_NUMBER);
        AddressAllocationDTO addressAllocationDTO = addressAllocationMapper.toDto(updatedAddressAllocation);

        restAddressAllocationMockMvc
            .perform(
                put("/api/address-allocations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressAllocationDTO))
            )
            .andExpect(status().isOk());

        // Validate the AddressAllocation in the database
        List<AddressAllocation> addressAllocationList = addressAllocationRepository.findAll();
        assertThat(addressAllocationList).hasSize(databaseSizeBeforeUpdate);
        AddressAllocation testAddressAllocation = addressAllocationList.get(addressAllocationList.size() - 1);
        assertThat(testAddressAllocation.getSubnet()).isEqualTo(UPDATED_SUBNET);
        assertThat(testAddressAllocation.getDeviceName()).isEqualTo(UPDATED_DEVICE_NAME);
        assertThat(testAddressAllocation.getDeviceMode()).isEqualTo(UPDATED_DEVICE_MODE);
        assertThat(testAddressAllocation.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testAddressAllocation.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testAddressAllocation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAddressAllocation.getAllocationType()).isEqualTo(UPDATED_ALLOCATION_TYPE);
        assertThat(testAddressAllocation.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingAddressAllocation() throws Exception {
        int databaseSizeBeforeUpdate = addressAllocationRepository.findAll().size();

        // Create the AddressAllocation
        AddressAllocationDTO addressAllocationDTO = addressAllocationMapper.toDto(addressAllocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressAllocationMockMvc
            .perform(
                put("/api/address-allocations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(addressAllocationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressAllocation in the database
        List<AddressAllocation> addressAllocationList = addressAllocationRepository.findAll();
        assertThat(addressAllocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAddressAllocation() throws Exception {
        // Initialize the database
        addressAllocationRepository.saveAndFlush(addressAllocation);

        int databaseSizeBeforeDelete = addressAllocationRepository.findAll().size();

        // Delete the addressAllocation
        restAddressAllocationMockMvc
            .perform(delete("/api/address-allocations/{id}", addressAllocation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AddressAllocation> addressAllocationList = addressAllocationRepository.findAll();
        assertThat(addressAllocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
