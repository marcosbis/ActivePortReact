package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.Partner;
import com.activeport.web.domain.enumeration.AwsTypeEnum;
import com.activeport.web.domain.enumeration.ConnetionTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.activeport.web.repository.PartnerRepository;
import com.activeport.web.service.PartnerService;
import com.activeport.web.service.dto.PartnerDTO;
import com.activeport.web.service.mapper.PartnerMapper;
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
 * Integration tests for the {@link PartnerResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PartnerResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final PartnerTypeEnum DEFAULT_TYPE = PartnerTypeEnum.MEGAPORT;
    private static final PartnerTypeEnum UPDATED_TYPE = PartnerTypeEnum.ISP;

    private static final ConnetionTypeEnum DEFAULT_CONNECTION = ConnetionTypeEnum.LAYER2;
    private static final ConnetionTypeEnum UPDATED_CONNECTION = ConnetionTypeEnum.LAYER3;

    private static final AwsTypeEnum DEFAULT_PORT_TYPE = AwsTypeEnum.PUBLIC;
    private static final AwsTypeEnum UPDATED_PORT_TYPE = AwsTypeEnum.PRIVATE;

    private static final String DEFAULT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_MARKET = "AAAAAAAAAA";
    private static final String UPDATED_MARKET = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOCATION_ID = 1;
    private static final Integer UPDATED_LOCATION_ID = 2;

    private static final Boolean DEFAULT_VXCPERMITTED = false;
    private static final Boolean UPDATED_VXCPERMITTED = true;

    private static final String DEFAULT_LOCATION_IX = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_IX = "BBBBBBBBBB";

    private static final String DEFAULT_VLAN_PORT = "AAAAAAAAAA";
    private static final String UPDATED_VLAN_PORT = "BBBBBBBBBB";

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PartnerMapper partnerMapper;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPartnerMockMvc;

    private Partner partner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Partner createEntity(EntityManager em) {
        Partner partner = new Partner()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .connection(DEFAULT_CONNECTION)
            .portType(DEFAULT_PORT_TYPE)
            .port(DEFAULT_PORT)
            .market(DEFAULT_MARKET)
            .locationId(DEFAULT_LOCATION_ID)
            .vxcpermitted(DEFAULT_VXCPERMITTED)
            .locationIx(DEFAULT_LOCATION_IX)
            .vlanPort(DEFAULT_VLAN_PORT);
        return partner;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Partner createUpdatedEntity(EntityManager em) {
        Partner partner = new Partner()
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .connection(UPDATED_CONNECTION)
            .portType(UPDATED_PORT_TYPE)
            .port(UPDATED_PORT)
            .market(UPDATED_MARKET)
            .locationId(UPDATED_LOCATION_ID)
            .vxcpermitted(UPDATED_VXCPERMITTED)
            .locationIx(UPDATED_LOCATION_IX)
            .vlanPort(UPDATED_VLAN_PORT);
        return partner;
    }

    @BeforeEach
    public void initTest() {
        partner = createEntity(em);
    }

    @Test
    @Transactional
    public void createPartner() throws Exception {
        int databaseSizeBeforeCreate = partnerRepository.findAll().size();
        // Create the Partner
        PartnerDTO partnerDTO = partnerMapper.toDto(partner);
        restPartnerMockMvc
            .perform(post("/api/partners").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
            .andExpect(status().isCreated());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeCreate + 1);
        Partner testPartner = partnerList.get(partnerList.size() - 1);
        assertThat(testPartner.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPartner.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPartner.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPartner.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPartner.getConnection()).isEqualTo(DEFAULT_CONNECTION);
        assertThat(testPartner.getPortType()).isEqualTo(DEFAULT_PORT_TYPE);
        assertThat(testPartner.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testPartner.getMarket()).isEqualTo(DEFAULT_MARKET);
        assertThat(testPartner.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testPartner.isVxcpermitted()).isEqualTo(DEFAULT_VXCPERMITTED);
        assertThat(testPartner.getLocationIx()).isEqualTo(DEFAULT_LOCATION_IX);
        assertThat(testPartner.getVlanPort()).isEqualTo(DEFAULT_VLAN_PORT);
    }

    @Test
    @Transactional
    public void createPartnerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = partnerRepository.findAll().size();

        // Create the Partner with an existing ID
        partner.setId(1L);
        PartnerDTO partnerDTO = partnerMapper.toDto(partner);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartnerMockMvc
            .perform(post("/api/partners").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = partnerRepository.findAll().size();
        // set the field null
        partner.setName(null);

        // Create the Partner, which fails.
        PartnerDTO partnerDTO = partnerMapper.toDto(partner);

        restPartnerMockMvc
            .perform(post("/api/partners").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
            .andExpect(status().isBadRequest());

        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = partnerRepository.findAll().size();
        // set the field null
        partner.setType(null);

        // Create the Partner, which fails.
        PartnerDTO partnerDTO = partnerMapper.toDto(partner);

        restPartnerMockMvc
            .perform(post("/api/partners").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
            .andExpect(status().isBadRequest());

        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPartners() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        // Get all the partnerList
        restPartnerMockMvc
            .perform(get("/api/partners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partner.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].connection").value(hasItem(DEFAULT_CONNECTION.toString())))
            .andExpect(jsonPath("$.[*].portType").value(hasItem(DEFAULT_PORT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)))
            .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID)))
            .andExpect(jsonPath("$.[*].vxcpermitted").value(hasItem(DEFAULT_VXCPERMITTED.booleanValue())))
            .andExpect(jsonPath("$.[*].locationIx").value(hasItem(DEFAULT_LOCATION_IX)))
            .andExpect(jsonPath("$.[*].vlanPort").value(hasItem(DEFAULT_VLAN_PORT)));
    }

    @Test
    @Transactional
    public void getPartner() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        // Get the partner
        restPartnerMockMvc
            .perform(get("/api/partners/{id}", partner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(partner.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.connection").value(DEFAULT_CONNECTION.toString()))
            .andExpect(jsonPath("$.portType").value(DEFAULT_PORT_TYPE.toString()))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT))
            .andExpect(jsonPath("$.market").value(DEFAULT_MARKET))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID))
            .andExpect(jsonPath("$.vxcpermitted").value(DEFAULT_VXCPERMITTED.booleanValue()))
            .andExpect(jsonPath("$.locationIx").value(DEFAULT_LOCATION_IX))
            .andExpect(jsonPath("$.vlanPort").value(DEFAULT_VLAN_PORT));
    }

    @Test
    @Transactional
    public void getNonExistingPartner() throws Exception {
        // Get the partner
        restPartnerMockMvc.perform(get("/api/partners/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePartner() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();

        // Update the partner
        Partner updatedPartner = partnerRepository.findById(partner.getId()).get();
        // Disconnect from session so that the updates on updatedPartner are not directly saved in db
        em.detach(updatedPartner);
        updatedPartner
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .connection(UPDATED_CONNECTION)
            .portType(UPDATED_PORT_TYPE)
            .port(UPDATED_PORT)
            .market(UPDATED_MARKET)
            .locationId(UPDATED_LOCATION_ID)
            .vxcpermitted(UPDATED_VXCPERMITTED)
            .locationIx(UPDATED_LOCATION_IX)
            .vlanPort(UPDATED_VLAN_PORT);
        PartnerDTO partnerDTO = partnerMapper.toDto(updatedPartner);

        restPartnerMockMvc
            .perform(put("/api/partners").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
            .andExpect(status().isOk());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
        Partner testPartner = partnerList.get(partnerList.size() - 1);
        assertThat(testPartner.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPartner.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPartner.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPartner.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPartner.getConnection()).isEqualTo(UPDATED_CONNECTION);
        assertThat(testPartner.getPortType()).isEqualTo(UPDATED_PORT_TYPE);
        assertThat(testPartner.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testPartner.getMarket()).isEqualTo(UPDATED_MARKET);
        assertThat(testPartner.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testPartner.isVxcpermitted()).isEqualTo(UPDATED_VXCPERMITTED);
        assertThat(testPartner.getLocationIx()).isEqualTo(UPDATED_LOCATION_IX);
        assertThat(testPartner.getVlanPort()).isEqualTo(UPDATED_VLAN_PORT);
    }

    @Test
    @Transactional
    public void updateNonExistingPartner() throws Exception {
        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();

        // Create the Partner
        PartnerDTO partnerDTO = partnerMapper.toDto(partner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartnerMockMvc
            .perform(put("/api/partners").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePartner() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        int databaseSizeBeforeDelete = partnerRepository.findAll().size();

        // Delete the partner
        restPartnerMockMvc
            .perform(delete("/api/partners/{id}", partner.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
