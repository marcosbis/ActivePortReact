package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ServiceCode;
import com.activeport.web.domain.enumeration.CreationTypeEnum;
import com.activeport.web.domain.enumeration.HostedTypeEnum;
import com.activeport.web.repository.ServiceCodeRepository;
import com.activeport.web.service.ServiceCodeService;
import com.activeport.web.service.dto.ServiceCodeDTO;
import com.activeport.web.service.mapper.ServiceCodeMapper;
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
 * Integration tests for the {@link ServiceCodeResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServiceCodeResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMAND = "AAAAAAAAAA";
    private static final String UPDATED_COMMAND = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final String DEFAULT_SERVICE_URL = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_URL = "BBBBBBBBBB";

    private static final HostedTypeEnum DEFAULT_HOSTED_TYPE = HostedTypeEnum.CIRCUIT;
    private static final HostedTypeEnum UPDATED_HOSTED_TYPE = HostedTypeEnum.VLAN;

    private static final CreationTypeEnum DEFAULT_CREATION_TYPE = CreationTypeEnum.CIRCUITVLAN;
    private static final CreationTypeEnum UPDATED_CREATION_TYPE = CreationTypeEnum.AUTOGENVLAN;

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_DTO_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_DTO_CLASS = "BBBBBBBBBB";

    @Autowired
    private ServiceCodeRepository serviceCodeRepository;

    @Autowired
    private ServiceCodeMapper serviceCodeMapper;

    @Autowired
    private ServiceCodeService serviceCodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceCodeMockMvc;

    private ServiceCode serviceCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceCode createEntity(EntityManager em) {
        ServiceCode serviceCode = new ServiceCode()
            .name(DEFAULT_NAME)
            .command(DEFAULT_COMMAND)
            .description(DEFAULT_DESCRIPTION)
            .enabled(DEFAULT_ENABLED)
            .serviceUrl(DEFAULT_SERVICE_URL)
            .hostedType(DEFAULT_HOSTED_TYPE)
            .creationType(DEFAULT_CREATION_TYPE)
            .tag(DEFAULT_TAG)
            .dtoClass(DEFAULT_DTO_CLASS);
        return serviceCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceCode createUpdatedEntity(EntityManager em) {
        ServiceCode serviceCode = new ServiceCode()
            .name(UPDATED_NAME)
            .command(UPDATED_COMMAND)
            .description(UPDATED_DESCRIPTION)
            .enabled(UPDATED_ENABLED)
            .serviceUrl(UPDATED_SERVICE_URL)
            .hostedType(UPDATED_HOSTED_TYPE)
            .creationType(UPDATED_CREATION_TYPE)
            .tag(UPDATED_TAG)
            .dtoClass(UPDATED_DTO_CLASS);
        return serviceCode;
    }

    @BeforeEach
    public void initTest() {
        serviceCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceCode() throws Exception {
        int databaseSizeBeforeCreate = serviceCodeRepository.findAll().size();
        // Create the ServiceCode
        ServiceCodeDTO serviceCodeDTO = serviceCodeMapper.toDto(serviceCode);
        restServiceCodeMockMvc
            .perform(
                post("/api/service-codes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceCodeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceCode in the database
        List<ServiceCode> serviceCodeList = serviceCodeRepository.findAll();
        assertThat(serviceCodeList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceCode testServiceCode = serviceCodeList.get(serviceCodeList.size() - 1);
        assertThat(testServiceCode.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServiceCode.getCommand()).isEqualTo(DEFAULT_COMMAND);
        assertThat(testServiceCode.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testServiceCode.isEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testServiceCode.getServiceUrl()).isEqualTo(DEFAULT_SERVICE_URL);
        assertThat(testServiceCode.getHostedType()).isEqualTo(DEFAULT_HOSTED_TYPE);
        assertThat(testServiceCode.getCreationType()).isEqualTo(DEFAULT_CREATION_TYPE);
        assertThat(testServiceCode.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testServiceCode.getDtoClass()).isEqualTo(DEFAULT_DTO_CLASS);
    }

    @Test
    @Transactional
    public void createServiceCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceCodeRepository.findAll().size();

        // Create the ServiceCode with an existing ID
        serviceCode.setId(1L);
        ServiceCodeDTO serviceCodeDTO = serviceCodeMapper.toDto(serviceCode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceCodeMockMvc
            .perform(
                post("/api/service-codes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceCode in the database
        List<ServiceCode> serviceCodeList = serviceCodeRepository.findAll();
        assertThat(serviceCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllServiceCodes() throws Exception {
        // Initialize the database
        serviceCodeRepository.saveAndFlush(serviceCode);

        // Get all the serviceCodeList
        restServiceCodeMockMvc
            .perform(get("/api/service-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].command").value(hasItem(DEFAULT_COMMAND.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].serviceUrl").value(hasItem(DEFAULT_SERVICE_URL)))
            .andExpect(jsonPath("$.[*].hostedType").value(hasItem(DEFAULT_HOSTED_TYPE.toString())))
            .andExpect(jsonPath("$.[*].creationType").value(hasItem(DEFAULT_CREATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].dtoClass").value(hasItem(DEFAULT_DTO_CLASS)));
    }

    @Test
    @Transactional
    public void getServiceCode() throws Exception {
        // Initialize the database
        serviceCodeRepository.saveAndFlush(serviceCode);

        // Get the serviceCode
        restServiceCodeMockMvc
            .perform(get("/api/service-codes/{id}", serviceCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceCode.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.command").value(DEFAULT_COMMAND.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.serviceUrl").value(DEFAULT_SERVICE_URL))
            .andExpect(jsonPath("$.hostedType").value(DEFAULT_HOSTED_TYPE.toString()))
            .andExpect(jsonPath("$.creationType").value(DEFAULT_CREATION_TYPE.toString()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.dtoClass").value(DEFAULT_DTO_CLASS));
    }

    @Test
    @Transactional
    public void getNonExistingServiceCode() throws Exception {
        // Get the serviceCode
        restServiceCodeMockMvc.perform(get("/api/service-codes/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceCode() throws Exception {
        // Initialize the database
        serviceCodeRepository.saveAndFlush(serviceCode);

        int databaseSizeBeforeUpdate = serviceCodeRepository.findAll().size();

        // Update the serviceCode
        ServiceCode updatedServiceCode = serviceCodeRepository.findById(serviceCode.getId()).get();
        // Disconnect from session so that the updates on updatedServiceCode are not directly saved in db
        em.detach(updatedServiceCode);
        updatedServiceCode
            .name(UPDATED_NAME)
            .command(UPDATED_COMMAND)
            .description(UPDATED_DESCRIPTION)
            .enabled(UPDATED_ENABLED)
            .serviceUrl(UPDATED_SERVICE_URL)
            .hostedType(UPDATED_HOSTED_TYPE)
            .creationType(UPDATED_CREATION_TYPE)
            .tag(UPDATED_TAG)
            .dtoClass(UPDATED_DTO_CLASS);
        ServiceCodeDTO serviceCodeDTO = serviceCodeMapper.toDto(updatedServiceCode);

        restServiceCodeMockMvc
            .perform(
                put("/api/service-codes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceCodeDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceCode in the database
        List<ServiceCode> serviceCodeList = serviceCodeRepository.findAll();
        assertThat(serviceCodeList).hasSize(databaseSizeBeforeUpdate);
        ServiceCode testServiceCode = serviceCodeList.get(serviceCodeList.size() - 1);
        assertThat(testServiceCode.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServiceCode.getCommand()).isEqualTo(UPDATED_COMMAND);
        assertThat(testServiceCode.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testServiceCode.isEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testServiceCode.getServiceUrl()).isEqualTo(UPDATED_SERVICE_URL);
        assertThat(testServiceCode.getHostedType()).isEqualTo(UPDATED_HOSTED_TYPE);
        assertThat(testServiceCode.getCreationType()).isEqualTo(UPDATED_CREATION_TYPE);
        assertThat(testServiceCode.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testServiceCode.getDtoClass()).isEqualTo(UPDATED_DTO_CLASS);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceCode() throws Exception {
        int databaseSizeBeforeUpdate = serviceCodeRepository.findAll().size();

        // Create the ServiceCode
        ServiceCodeDTO serviceCodeDTO = serviceCodeMapper.toDto(serviceCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceCodeMockMvc
            .perform(
                put("/api/service-codes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceCode in the database
        List<ServiceCode> serviceCodeList = serviceCodeRepository.findAll();
        assertThat(serviceCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceCode() throws Exception {
        // Initialize the database
        serviceCodeRepository.saveAndFlush(serviceCode);

        int databaseSizeBeforeDelete = serviceCodeRepository.findAll().size();

        // Delete the serviceCode
        restServiceCodeMockMvc
            .perform(delete("/api/service-codes/{id}", serviceCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceCode> serviceCodeList = serviceCodeRepository.findAll();
        assertThat(serviceCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
