package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ProviderPort;
import com.activeport.web.domain.enumeration.AwsTypeEnum;
import com.activeport.web.domain.enumeration.ConnetionTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.activeport.web.repository.ProviderPortRepository;
import com.activeport.web.service.ProviderPortService;
import com.activeport.web.service.dto.ProviderPortDTO;
import com.activeport.web.service.mapper.ProviderPortMapper;
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
 * Integration tests for the {@link ProviderPortResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProviderPortResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UID = "AAAAAAAAAA";
    private static final String UPDATED_UID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final PartnerTypeEnum DEFAULT_TYPE = PartnerTypeEnum.MEGAPORT;
    private static final PartnerTypeEnum UPDATED_TYPE = PartnerTypeEnum.ISP;

    private static final ConnetionTypeEnum DEFAULT_CONNECTION = ConnetionTypeEnum.LAYER2;
    private static final ConnetionTypeEnum UPDATED_CONNECTION = ConnetionTypeEnum.LAYER3;

    private static final AwsTypeEnum DEFAULT_PORT_TYPE = AwsTypeEnum.PUBLIC;
    private static final AwsTypeEnum UPDATED_PORT_TYPE = AwsTypeEnum.PRIVATE;

    private static final String DEFAULT_PORT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PORT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MARKET = "AAAAAAAAAA";
    private static final String UPDATED_MARKET = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOCATION_ID = 1;
    private static final Integer UPDATED_LOCATION_ID = 2;

    @Autowired
    private ProviderPortRepository providerPortRepository;

    @Autowired
    private ProviderPortMapper providerPortMapper;

    @Autowired
    private ProviderPortService providerPortService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProviderPortMockMvc;

    private ProviderPort providerPort;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProviderPort createEntity(EntityManager em) {
        ProviderPort providerPort = new ProviderPort()
            .name(DEFAULT_NAME)
            .uid(DEFAULT_UID)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .connection(DEFAULT_CONNECTION)
            .portType(DEFAULT_PORT_TYPE)
            .portId(DEFAULT_PORT_ID)
            .market(DEFAULT_MARKET)
            .locationId(DEFAULT_LOCATION_ID);
        return providerPort;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProviderPort createUpdatedEntity(EntityManager em) {
        ProviderPort providerPort = new ProviderPort()
            .name(UPDATED_NAME)
            .uid(UPDATED_UID)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .connection(UPDATED_CONNECTION)
            .portType(UPDATED_PORT_TYPE)
            .portId(UPDATED_PORT_ID)
            .market(UPDATED_MARKET)
            .locationId(UPDATED_LOCATION_ID);
        return providerPort;
    }

    @BeforeEach
    public void initTest() {
        providerPort = createEntity(em);
    }

    @Test
    @Transactional
    public void createProviderPort() throws Exception {
        int databaseSizeBeforeCreate = providerPortRepository.findAll().size();
        // Create the ProviderPort
        ProviderPortDTO providerPortDTO = providerPortMapper.toDto(providerPort);
        restProviderPortMockMvc
            .perform(
                post("/api/provider-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerPortDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProviderPort in the database
        List<ProviderPort> providerPortList = providerPortRepository.findAll();
        assertThat(providerPortList).hasSize(databaseSizeBeforeCreate + 1);
        ProviderPort testProviderPort = providerPortList.get(providerPortList.size() - 1);
        assertThat(testProviderPort.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProviderPort.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testProviderPort.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProviderPort.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProviderPort.getConnection()).isEqualTo(DEFAULT_CONNECTION);
        assertThat(testProviderPort.getPortType()).isEqualTo(DEFAULT_PORT_TYPE);
        assertThat(testProviderPort.getPortId()).isEqualTo(DEFAULT_PORT_ID);
        assertThat(testProviderPort.getMarket()).isEqualTo(DEFAULT_MARKET);
        assertThat(testProviderPort.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
    }

    @Test
    @Transactional
    public void createProviderPortWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providerPortRepository.findAll().size();

        // Create the ProviderPort with an existing ID
        providerPort.setId(1L);
        ProviderPortDTO providerPortDTO = providerPortMapper.toDto(providerPort);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProviderPortMockMvc
            .perform(
                post("/api/provider-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerPortDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProviderPort in the database
        List<ProviderPort> providerPortList = providerPortRepository.findAll();
        assertThat(providerPortList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerPortRepository.findAll().size();
        // set the field null
        providerPort.setName(null);

        // Create the ProviderPort, which fails.
        ProviderPortDTO providerPortDTO = providerPortMapper.toDto(providerPort);

        restProviderPortMockMvc
            .perform(
                post("/api/provider-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerPortDTO))
            )
            .andExpect(status().isBadRequest());

        List<ProviderPort> providerPortList = providerPortRepository.findAll();
        assertThat(providerPortList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerPortRepository.findAll().size();
        // set the field null
        providerPort.setType(null);

        // Create the ProviderPort, which fails.
        ProviderPortDTO providerPortDTO = providerPortMapper.toDto(providerPort);

        restProviderPortMockMvc
            .perform(
                post("/api/provider-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerPortDTO))
            )
            .andExpect(status().isBadRequest());

        List<ProviderPort> providerPortList = providerPortRepository.findAll();
        assertThat(providerPortList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProviderPorts() throws Exception {
        // Initialize the database
        providerPortRepository.saveAndFlush(providerPort);

        // Get all the providerPortList
        restProviderPortMockMvc
            .perform(get("/api/provider-ports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(providerPort.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].connection").value(hasItem(DEFAULT_CONNECTION.toString())))
            .andExpect(jsonPath("$.[*].portType").value(hasItem(DEFAULT_PORT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].portId").value(hasItem(DEFAULT_PORT_ID)))
            .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID)));
    }

    @Test
    @Transactional
    public void getProviderPort() throws Exception {
        // Initialize the database
        providerPortRepository.saveAndFlush(providerPort);

        // Get the providerPort
        restProviderPortMockMvc
            .perform(get("/api/provider-ports/{id}", providerPort.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(providerPort.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.connection").value(DEFAULT_CONNECTION.toString()))
            .andExpect(jsonPath("$.portType").value(DEFAULT_PORT_TYPE.toString()))
            .andExpect(jsonPath("$.portId").value(DEFAULT_PORT_ID))
            .andExpect(jsonPath("$.market").value(DEFAULT_MARKET))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID));
    }

    @Test
    @Transactional
    public void getNonExistingProviderPort() throws Exception {
        // Get the providerPort
        restProviderPortMockMvc.perform(get("/api/provider-ports/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProviderPort() throws Exception {
        // Initialize the database
        providerPortRepository.saveAndFlush(providerPort);

        int databaseSizeBeforeUpdate = providerPortRepository.findAll().size();

        // Update the providerPort
        ProviderPort updatedProviderPort = providerPortRepository.findById(providerPort.getId()).get();
        // Disconnect from session so that the updates on updatedProviderPort are not directly saved in db
        em.detach(updatedProviderPort);
        updatedProviderPort
            .name(UPDATED_NAME)
            .uid(UPDATED_UID)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .connection(UPDATED_CONNECTION)
            .portType(UPDATED_PORT_TYPE)
            .portId(UPDATED_PORT_ID)
            .market(UPDATED_MARKET)
            .locationId(UPDATED_LOCATION_ID);
        ProviderPortDTO providerPortDTO = providerPortMapper.toDto(updatedProviderPort);

        restProviderPortMockMvc
            .perform(
                put("/api/provider-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerPortDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProviderPort in the database
        List<ProviderPort> providerPortList = providerPortRepository.findAll();
        assertThat(providerPortList).hasSize(databaseSizeBeforeUpdate);
        ProviderPort testProviderPort = providerPortList.get(providerPortList.size() - 1);
        assertThat(testProviderPort.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProviderPort.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testProviderPort.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProviderPort.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProviderPort.getConnection()).isEqualTo(UPDATED_CONNECTION);
        assertThat(testProviderPort.getPortType()).isEqualTo(UPDATED_PORT_TYPE);
        assertThat(testProviderPort.getPortId()).isEqualTo(UPDATED_PORT_ID);
        assertThat(testProviderPort.getMarket()).isEqualTo(UPDATED_MARKET);
        assertThat(testProviderPort.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingProviderPort() throws Exception {
        int databaseSizeBeforeUpdate = providerPortRepository.findAll().size();

        // Create the ProviderPort
        ProviderPortDTO providerPortDTO = providerPortMapper.toDto(providerPort);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProviderPortMockMvc
            .perform(
                put("/api/provider-ports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(providerPortDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProviderPort in the database
        List<ProviderPort> providerPortList = providerPortRepository.findAll();
        assertThat(providerPortList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProviderPort() throws Exception {
        // Initialize the database
        providerPortRepository.saveAndFlush(providerPort);

        int databaseSizeBeforeDelete = providerPortRepository.findAll().size();

        // Delete the providerPort
        restProviderPortMockMvc
            .perform(delete("/api/provider-ports/{id}", providerPort.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProviderPort> providerPortList = providerPortRepository.findAll();
        assertThat(providerPortList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
