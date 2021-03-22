package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.MarketPlace;
import com.activeport.web.domain.enumeration.CircuitTypeEnum;
import com.activeport.web.domain.enumeration.ConnectTypeEnum;
import com.activeport.web.domain.enumeration.FirewallStatusEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.activeport.web.domain.enumeration.ProvisioningStatusEnum;
import com.activeport.web.domain.enumeration.ServiceStateEnum;
import com.activeport.web.domain.enumeration.VXCTypeEnum;
import com.activeport.web.repository.MarketPlaceRepository;
import com.activeport.web.service.MarketPlaceService;
import com.activeport.web.service.dto.MarketPlaceDTO;
import com.activeport.web.service.mapper.MarketPlaceMapper;
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
 * Integration tests for the {@link MarketPlaceResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MarketPlaceResourceIT {
    private static final String DEFAULT_COMPANY_UID = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_UID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_UID = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_UID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOCATION_ID = 1;
    private static final Integer UPDATED_LOCATION_ID = 2;

    private static final Integer DEFAULT_SPEED = 1;
    private static final Integer UPDATED_SPEED = 2;

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_BANDWIDTH = 1;
    private static final Integer UPDATED_BANDWIDTH = 2;

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_METRO = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_METRO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PORT_ID = 1;
    private static final Integer UPDATED_PORT_ID = 2;

    private static final ConnectTypeEnum DEFAULT_TYPE = ConnectTypeEnum.AWS;
    private static final ConnectTypeEnum UPDATED_TYPE = ConnectTypeEnum.IX;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_KEY = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATE_LIMIT = 1;
    private static final Integer UPDATED_RATE_LIMIT = 2;

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UID = "BBBBBBBBBB";

    private static final Integer DEFAULT_RE_TAGGED_VLAN_ID = 1;
    private static final Integer UPDATED_RE_TAGGED_VLAN_ID = 2;

    private static final ProvisioningStatusEnum DEFAULT_PROVISIONING_STATUS = ProvisioningStatusEnum.NEW;
    private static final ProvisioningStatusEnum UPDATED_PROVISIONING_STATUS = ProvisioningStatusEnum.DESIGN;

    private static final Integer DEFAULT_VLAN_ID_A = 1;
    private static final Integer UPDATED_VLAN_ID_A = 2;

    private static final Integer DEFAULT_VLAN_ID_B = 1;
    private static final Integer UPDATED_VLAN_ID_B = 2;

    private static final Integer DEFAULT_VLAN_ID_S = 1;
    private static final Integer UPDATED_VLAN_ID_S = 2;

    private static final Long DEFAULT_NTU_ID = 1L;
    private static final Long UPDATED_NTU_ID = 2L;

    private static final String DEFAULT_USER_IP = "AAAAAAAAAA";
    private static final String UPDATED_USER_IP = "BBBBBBBBBB";

    private static final String DEFAULT_FIREWALL_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_FIREWALL_PRICE = "BBBBBBBBBB";

    private static final FirewallStatusEnum DEFAULT_FIREWALL_STATUS = FirewallStatusEnum.DESIGN;
    private static final FirewallStatusEnum UPDATED_FIREWALL_STATUS = FirewallStatusEnum.IN_PROGRESS;

    private static final ServiceStateEnum DEFAULT_STATE = ServiceStateEnum.DESIGN;
    private static final ServiceStateEnum UPDATED_STATE = ServiceStateEnum.ACTIVE;

    private static final String DEFAULT_B_END_PRODUCT_UID = "AAAAAAAAAA";
    private static final String UPDATED_B_END_PRODUCT_UID = "BBBBBBBBBB";

    private static final PartnerTypeEnum DEFAULT_PARTNER_TYPE = PartnerTypeEnum.MEGAPORT;
    private static final PartnerTypeEnum UPDATED_PARTNER_TYPE = PartnerTypeEnum.ISP;

    private static final CircuitTypeEnum DEFAULT_CIRCUIT_TYPE = CircuitTypeEnum.VLAN;
    private static final CircuitTypeEnum UPDATED_CIRCUIT_TYPE = CircuitTypeEnum.CIRCUIT;

    private static final String DEFAULT_USER_SUBNET = "AAAAAAAAAA";
    private static final String UPDATED_USER_SUBNET = "BBBBBBBBBB";

    private static final String DEFAULT_MY_GW = "AAAAAAAAAA";
    private static final String UPDATED_MY_GW = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_PORT_GW = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_PORT_GW = "BBBBBBBBBB";

    private static final String DEFAULT_AWS_AUTH_KEY = "AAAAAAAAAA";
    private static final String UPDATED_AWS_AUTH_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_AWS_IP = "AAAAAAAAAA";
    private static final String UPDATED_AWS_IP = "BBBBBBBBBB";

    private static final Integer DEFAULT_ASN = 1;
    private static final Integer UPDATED_ASN = 2;

    private static final Integer DEFAULT_PEER_ASN = 1;
    private static final Integer UPDATED_PEER_ASN = 2;

    private static final VXCTypeEnum DEFAULT_VXC_TYPE = VXCTypeEnum.PRIMARY;
    private static final VXCTypeEnum UPDATED_VXC_TYPE = VXCTypeEnum.SECONDARY;

    @Autowired
    private MarketPlaceRepository marketPlaceRepository;

    @Autowired
    private MarketPlaceMapper marketPlaceMapper;

    @Autowired
    private MarketPlaceService marketPlaceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMarketPlaceMockMvc;

    private MarketPlace marketPlace;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MarketPlace createEntity(EntityManager em) {
        MarketPlace marketPlace = new MarketPlace()
            .companyUid(DEFAULT_COMPANY_UID)
            .companyName(DEFAULT_COMPANY_NAME)
            .partnerUid(DEFAULT_PARTNER_UID)
            .accountId(DEFAULT_ACCOUNT_ID)
            .title(DEFAULT_TITLE)
            .locationId(DEFAULT_LOCATION_ID)
            .speed(DEFAULT_SPEED)
            .rank(DEFAULT_RANK)
            .bandwidth(DEFAULT_BANDWIDTH)
            .locationName(DEFAULT_LOCATION_NAME)
            .locationMetro(DEFAULT_LOCATION_METRO)
            .portId(DEFAULT_PORT_ID)
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .serviceKey(DEFAULT_SERVICE_KEY)
            .rateLimit(DEFAULT_RATE_LIMIT)
            .price(DEFAULT_PRICE)
            .uuid(DEFAULT_UUID)
            .productUid(DEFAULT_PRODUCT_UID)
            .reTaggedVlanId(DEFAULT_RE_TAGGED_VLAN_ID)
            .provisioningStatus(DEFAULT_PROVISIONING_STATUS)
            .vlanIdA(DEFAULT_VLAN_ID_A)
            .vlanIdB(DEFAULT_VLAN_ID_B)
            .vlanIdS(DEFAULT_VLAN_ID_S)
            .ntuId(DEFAULT_NTU_ID)
            .userIp(DEFAULT_USER_IP)
            .firewallPrice(DEFAULT_FIREWALL_PRICE)
            .firewallStatus(DEFAULT_FIREWALL_STATUS)
            .state(DEFAULT_STATE)
            .bEndProductUid(DEFAULT_B_END_PRODUCT_UID)
            .partnerType(DEFAULT_PARTNER_TYPE)
            .circuitType(DEFAULT_CIRCUIT_TYPE)
            .userSubnet(DEFAULT_USER_SUBNET)
            .myGw(DEFAULT_MY_GW)
            .activePortGw(DEFAULT_ACTIVE_PORT_GW)
            .awsAuthKey(DEFAULT_AWS_AUTH_KEY)
            .awsIp(DEFAULT_AWS_IP)
            .asn(DEFAULT_ASN)
            .peerAsn(DEFAULT_PEER_ASN)
            .vxcType(DEFAULT_VXC_TYPE);
        return marketPlace;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MarketPlace createUpdatedEntity(EntityManager em) {
        MarketPlace marketPlace = new MarketPlace()
            .companyUid(UPDATED_COMPANY_UID)
            .companyName(UPDATED_COMPANY_NAME)
            .partnerUid(UPDATED_PARTNER_UID)
            .accountId(UPDATED_ACCOUNT_ID)
            .title(UPDATED_TITLE)
            .locationId(UPDATED_LOCATION_ID)
            .speed(UPDATED_SPEED)
            .rank(UPDATED_RANK)
            .bandwidth(UPDATED_BANDWIDTH)
            .locationName(UPDATED_LOCATION_NAME)
            .locationMetro(UPDATED_LOCATION_METRO)
            .portId(UPDATED_PORT_ID)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .serviceKey(UPDATED_SERVICE_KEY)
            .rateLimit(UPDATED_RATE_LIMIT)
            .price(UPDATED_PRICE)
            .uuid(UPDATED_UUID)
            .productUid(UPDATED_PRODUCT_UID)
            .reTaggedVlanId(UPDATED_RE_TAGGED_VLAN_ID)
            .provisioningStatus(UPDATED_PROVISIONING_STATUS)
            .vlanIdA(UPDATED_VLAN_ID_A)
            .vlanIdB(UPDATED_VLAN_ID_B)
            .vlanIdS(UPDATED_VLAN_ID_S)
            .ntuId(UPDATED_NTU_ID)
            .userIp(UPDATED_USER_IP)
            .firewallPrice(UPDATED_FIREWALL_PRICE)
            .firewallStatus(UPDATED_FIREWALL_STATUS)
            .state(UPDATED_STATE)
            .bEndProductUid(UPDATED_B_END_PRODUCT_UID)
            .partnerType(UPDATED_PARTNER_TYPE)
            .circuitType(UPDATED_CIRCUIT_TYPE)
            .userSubnet(UPDATED_USER_SUBNET)
            .myGw(UPDATED_MY_GW)
            .activePortGw(UPDATED_ACTIVE_PORT_GW)
            .awsAuthKey(UPDATED_AWS_AUTH_KEY)
            .awsIp(UPDATED_AWS_IP)
            .asn(UPDATED_ASN)
            .peerAsn(UPDATED_PEER_ASN)
            .vxcType(UPDATED_VXC_TYPE);
        return marketPlace;
    }

    @BeforeEach
    public void initTest() {
        marketPlace = createEntity(em);
    }

    @Test
    @Transactional
    public void createMarketPlace() throws Exception {
        int databaseSizeBeforeCreate = marketPlaceRepository.findAll().size();
        // Create the MarketPlace
        MarketPlaceDTO marketPlaceDTO = marketPlaceMapper.toDto(marketPlace);
        restMarketPlaceMockMvc
            .perform(
                post("/api/market-places")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketPlaceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the MarketPlace in the database
        List<MarketPlace> marketPlaceList = marketPlaceRepository.findAll();
        assertThat(marketPlaceList).hasSize(databaseSizeBeforeCreate + 1);
        MarketPlace testMarketPlace = marketPlaceList.get(marketPlaceList.size() - 1);
        assertThat(testMarketPlace.getCompanyUid()).isEqualTo(DEFAULT_COMPANY_UID);
        assertThat(testMarketPlace.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testMarketPlace.getPartnerUid()).isEqualTo(DEFAULT_PARTNER_UID);
        assertThat(testMarketPlace.getAccountId()).isEqualTo(DEFAULT_ACCOUNT_ID);
        assertThat(testMarketPlace.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMarketPlace.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testMarketPlace.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testMarketPlace.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMarketPlace.getBandwidth()).isEqualTo(DEFAULT_BANDWIDTH);
        assertThat(testMarketPlace.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testMarketPlace.getLocationMetro()).isEqualTo(DEFAULT_LOCATION_METRO);
        assertThat(testMarketPlace.getPortId()).isEqualTo(DEFAULT_PORT_ID);
        assertThat(testMarketPlace.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMarketPlace.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMarketPlace.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMarketPlace.getServiceKey()).isEqualTo(DEFAULT_SERVICE_KEY);
        assertThat(testMarketPlace.getRateLimit()).isEqualTo(DEFAULT_RATE_LIMIT);
        assertThat(testMarketPlace.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testMarketPlace.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testMarketPlace.getProductUid()).isEqualTo(DEFAULT_PRODUCT_UID);
        assertThat(testMarketPlace.getReTaggedVlanId()).isEqualTo(DEFAULT_RE_TAGGED_VLAN_ID);
        assertThat(testMarketPlace.getProvisioningStatus()).isEqualTo(DEFAULT_PROVISIONING_STATUS);
        assertThat(testMarketPlace.getVlanIdA()).isEqualTo(DEFAULT_VLAN_ID_A);
        assertThat(testMarketPlace.getVlanIdB()).isEqualTo(DEFAULT_VLAN_ID_B);
        assertThat(testMarketPlace.getVlanIdS()).isEqualTo(DEFAULT_VLAN_ID_S);
        assertThat(testMarketPlace.getNtuId()).isEqualTo(DEFAULT_NTU_ID);
        assertThat(testMarketPlace.getUserIp()).isEqualTo(DEFAULT_USER_IP);
        assertThat(testMarketPlace.getFirewallPrice()).isEqualTo(DEFAULT_FIREWALL_PRICE);
        assertThat(testMarketPlace.getFirewallStatus()).isEqualTo(DEFAULT_FIREWALL_STATUS);
        assertThat(testMarketPlace.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testMarketPlace.getbEndProductUid()).isEqualTo(DEFAULT_B_END_PRODUCT_UID);
        assertThat(testMarketPlace.getPartnerType()).isEqualTo(DEFAULT_PARTNER_TYPE);
        assertThat(testMarketPlace.getCircuitType()).isEqualTo(DEFAULT_CIRCUIT_TYPE);
        assertThat(testMarketPlace.getUserSubnet()).isEqualTo(DEFAULT_USER_SUBNET);
        assertThat(testMarketPlace.getMyGw()).isEqualTo(DEFAULT_MY_GW);
        assertThat(testMarketPlace.getActivePortGw()).isEqualTo(DEFAULT_ACTIVE_PORT_GW);
        assertThat(testMarketPlace.getAwsAuthKey()).isEqualTo(DEFAULT_AWS_AUTH_KEY);
        assertThat(testMarketPlace.getAwsIp()).isEqualTo(DEFAULT_AWS_IP);
        assertThat(testMarketPlace.getAsn()).isEqualTo(DEFAULT_ASN);
        assertThat(testMarketPlace.getPeerAsn()).isEqualTo(DEFAULT_PEER_ASN);
        assertThat(testMarketPlace.getVxcType()).isEqualTo(DEFAULT_VXC_TYPE);
    }

    @Test
    @Transactional
    public void createMarketPlaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = marketPlaceRepository.findAll().size();

        // Create the MarketPlace with an existing ID
        marketPlace.setId(1L);
        MarketPlaceDTO marketPlaceDTO = marketPlaceMapper.toDto(marketPlace);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarketPlaceMockMvc
            .perform(
                post("/api/market-places")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(marketPlaceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MarketPlace in the database
        List<MarketPlace> marketPlaceList = marketPlaceRepository.findAll();
        assertThat(marketPlaceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMarketPlaces() throws Exception {
        // Initialize the database
        marketPlaceRepository.saveAndFlush(marketPlace);

        // Get all the marketPlaceList
        restMarketPlaceMockMvc
            .perform(get("/api/market-places?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(marketPlace.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyUid").value(hasItem(DEFAULT_COMPANY_UID)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].partnerUid").value(hasItem(DEFAULT_PARTNER_UID)))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].bandwidth").value(hasItem(DEFAULT_BANDWIDTH)))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].locationMetro").value(hasItem(DEFAULT_LOCATION_METRO)))
            .andExpect(jsonPath("$.[*].portId").value(hasItem(DEFAULT_PORT_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].serviceKey").value(hasItem(DEFAULT_SERVICE_KEY)))
            .andExpect(jsonPath("$.[*].rateLimit").value(hasItem(DEFAULT_RATE_LIMIT)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].productUid").value(hasItem(DEFAULT_PRODUCT_UID)))
            .andExpect(jsonPath("$.[*].reTaggedVlanId").value(hasItem(DEFAULT_RE_TAGGED_VLAN_ID)))
            .andExpect(jsonPath("$.[*].provisioningStatus").value(hasItem(DEFAULT_PROVISIONING_STATUS.toString())))
            .andExpect(jsonPath("$.[*].vlanIdA").value(hasItem(DEFAULT_VLAN_ID_A)))
            .andExpect(jsonPath("$.[*].vlanIdB").value(hasItem(DEFAULT_VLAN_ID_B)))
            .andExpect(jsonPath("$.[*].vlanIdS").value(hasItem(DEFAULT_VLAN_ID_S)))
            .andExpect(jsonPath("$.[*].ntuId").value(hasItem(DEFAULT_NTU_ID.intValue())))
            .andExpect(jsonPath("$.[*].userIp").value(hasItem(DEFAULT_USER_IP)))
            .andExpect(jsonPath("$.[*].firewallPrice").value(hasItem(DEFAULT_FIREWALL_PRICE)))
            .andExpect(jsonPath("$.[*].firewallStatus").value(hasItem(DEFAULT_FIREWALL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].bEndProductUid").value(hasItem(DEFAULT_B_END_PRODUCT_UID)))
            .andExpect(jsonPath("$.[*].partnerType").value(hasItem(DEFAULT_PARTNER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].circuitType").value(hasItem(DEFAULT_CIRCUIT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].userSubnet").value(hasItem(DEFAULT_USER_SUBNET)))
            .andExpect(jsonPath("$.[*].myGw").value(hasItem(DEFAULT_MY_GW)))
            .andExpect(jsonPath("$.[*].activePortGw").value(hasItem(DEFAULT_ACTIVE_PORT_GW)))
            .andExpect(jsonPath("$.[*].awsAuthKey").value(hasItem(DEFAULT_AWS_AUTH_KEY)))
            .andExpect(jsonPath("$.[*].awsIp").value(hasItem(DEFAULT_AWS_IP)))
            .andExpect(jsonPath("$.[*].asn").value(hasItem(DEFAULT_ASN)))
            .andExpect(jsonPath("$.[*].peerAsn").value(hasItem(DEFAULT_PEER_ASN)))
            .andExpect(jsonPath("$.[*].vxcType").value(hasItem(DEFAULT_VXC_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getMarketPlace() throws Exception {
        // Initialize the database
        marketPlaceRepository.saveAndFlush(marketPlace);

        // Get the marketPlace
        restMarketPlaceMockMvc
            .perform(get("/api/market-places/{id}", marketPlace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(marketPlace.getId().intValue()))
            .andExpect(jsonPath("$.companyUid").value(DEFAULT_COMPANY_UID))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.partnerUid").value(DEFAULT_PARTNER_UID))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID))
            .andExpect(jsonPath("$.speed").value(DEFAULT_SPEED))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.bandwidth").value(DEFAULT_BANDWIDTH))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.locationMetro").value(DEFAULT_LOCATION_METRO))
            .andExpect(jsonPath("$.portId").value(DEFAULT_PORT_ID))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.serviceKey").value(DEFAULT_SERVICE_KEY))
            .andExpect(jsonPath("$.rateLimit").value(DEFAULT_RATE_LIMIT))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID))
            .andExpect(jsonPath("$.productUid").value(DEFAULT_PRODUCT_UID))
            .andExpect(jsonPath("$.reTaggedVlanId").value(DEFAULT_RE_TAGGED_VLAN_ID))
            .andExpect(jsonPath("$.provisioningStatus").value(DEFAULT_PROVISIONING_STATUS.toString()))
            .andExpect(jsonPath("$.vlanIdA").value(DEFAULT_VLAN_ID_A))
            .andExpect(jsonPath("$.vlanIdB").value(DEFAULT_VLAN_ID_B))
            .andExpect(jsonPath("$.vlanIdS").value(DEFAULT_VLAN_ID_S))
            .andExpect(jsonPath("$.ntuId").value(DEFAULT_NTU_ID.intValue()))
            .andExpect(jsonPath("$.userIp").value(DEFAULT_USER_IP))
            .andExpect(jsonPath("$.firewallPrice").value(DEFAULT_FIREWALL_PRICE))
            .andExpect(jsonPath("$.firewallStatus").value(DEFAULT_FIREWALL_STATUS.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.bEndProductUid").value(DEFAULT_B_END_PRODUCT_UID))
            .andExpect(jsonPath("$.partnerType").value(DEFAULT_PARTNER_TYPE.toString()))
            .andExpect(jsonPath("$.circuitType").value(DEFAULT_CIRCUIT_TYPE.toString()))
            .andExpect(jsonPath("$.userSubnet").value(DEFAULT_USER_SUBNET))
            .andExpect(jsonPath("$.myGw").value(DEFAULT_MY_GW))
            .andExpect(jsonPath("$.activePortGw").value(DEFAULT_ACTIVE_PORT_GW))
            .andExpect(jsonPath("$.awsAuthKey").value(DEFAULT_AWS_AUTH_KEY))
            .andExpect(jsonPath("$.awsIp").value(DEFAULT_AWS_IP))
            .andExpect(jsonPath("$.asn").value(DEFAULT_ASN))
            .andExpect(jsonPath("$.peerAsn").value(DEFAULT_PEER_ASN))
            .andExpect(jsonPath("$.vxcType").value(DEFAULT_VXC_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMarketPlace() throws Exception {
        // Get the marketPlace
        restMarketPlaceMockMvc.perform(get("/api/market-places/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMarketPlace() throws Exception {
        // Initialize the database
        marketPlaceRepository.saveAndFlush(marketPlace);

        int databaseSizeBeforeUpdate = marketPlaceRepository.findAll().size();

        // Update the marketPlace
        MarketPlace updatedMarketPlace = marketPlaceRepository.findById(marketPlace.getId()).get();
        // Disconnect from session so that the updates on updatedMarketPlace are not directly saved in db
        em.detach(updatedMarketPlace);
        updatedMarketPlace
            .companyUid(UPDATED_COMPANY_UID)
            .companyName(UPDATED_COMPANY_NAME)
            .partnerUid(UPDATED_PARTNER_UID)
            .accountId(UPDATED_ACCOUNT_ID)
            .title(UPDATED_TITLE)
            .locationId(UPDATED_LOCATION_ID)
            .speed(UPDATED_SPEED)
            .rank(UPDATED_RANK)
            .bandwidth(UPDATED_BANDWIDTH)
            .locationName(UPDATED_LOCATION_NAME)
            .locationMetro(UPDATED_LOCATION_METRO)
            .portId(UPDATED_PORT_ID)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .serviceKey(UPDATED_SERVICE_KEY)
            .rateLimit(UPDATED_RATE_LIMIT)
            .price(UPDATED_PRICE)
            .uuid(UPDATED_UUID)
            .productUid(UPDATED_PRODUCT_UID)
            .reTaggedVlanId(UPDATED_RE_TAGGED_VLAN_ID)
            .provisioningStatus(UPDATED_PROVISIONING_STATUS)
            .vlanIdA(UPDATED_VLAN_ID_A)
            .vlanIdB(UPDATED_VLAN_ID_B)
            .vlanIdS(UPDATED_VLAN_ID_S)
            .ntuId(UPDATED_NTU_ID)
            .userIp(UPDATED_USER_IP)
            .firewallPrice(UPDATED_FIREWALL_PRICE)
            .firewallStatus(UPDATED_FIREWALL_STATUS)
            .state(UPDATED_STATE)
            .bEndProductUid(UPDATED_B_END_PRODUCT_UID)
            .partnerType(UPDATED_PARTNER_TYPE)
            .circuitType(UPDATED_CIRCUIT_TYPE)
            .userSubnet(UPDATED_USER_SUBNET)
            .myGw(UPDATED_MY_GW)
            .activePortGw(UPDATED_ACTIVE_PORT_GW)
            .awsAuthKey(UPDATED_AWS_AUTH_KEY)
            .awsIp(UPDATED_AWS_IP)
            .asn(UPDATED_ASN)
            .peerAsn(UPDATED_PEER_ASN)
            .vxcType(UPDATED_VXC_TYPE);
        MarketPlaceDTO marketPlaceDTO = marketPlaceMapper.toDto(updatedMarketPlace);

        restMarketPlaceMockMvc
            .perform(
                put("/api/market-places").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(marketPlaceDTO))
            )
            .andExpect(status().isOk());

        // Validate the MarketPlace in the database
        List<MarketPlace> marketPlaceList = marketPlaceRepository.findAll();
        assertThat(marketPlaceList).hasSize(databaseSizeBeforeUpdate);
        MarketPlace testMarketPlace = marketPlaceList.get(marketPlaceList.size() - 1);
        assertThat(testMarketPlace.getCompanyUid()).isEqualTo(UPDATED_COMPANY_UID);
        assertThat(testMarketPlace.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testMarketPlace.getPartnerUid()).isEqualTo(UPDATED_PARTNER_UID);
        assertThat(testMarketPlace.getAccountId()).isEqualTo(UPDATED_ACCOUNT_ID);
        assertThat(testMarketPlace.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMarketPlace.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testMarketPlace.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testMarketPlace.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMarketPlace.getBandwidth()).isEqualTo(UPDATED_BANDWIDTH);
        assertThat(testMarketPlace.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testMarketPlace.getLocationMetro()).isEqualTo(UPDATED_LOCATION_METRO);
        assertThat(testMarketPlace.getPortId()).isEqualTo(UPDATED_PORT_ID);
        assertThat(testMarketPlace.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMarketPlace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMarketPlace.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMarketPlace.getServiceKey()).isEqualTo(UPDATED_SERVICE_KEY);
        assertThat(testMarketPlace.getRateLimit()).isEqualTo(UPDATED_RATE_LIMIT);
        assertThat(testMarketPlace.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testMarketPlace.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testMarketPlace.getProductUid()).isEqualTo(UPDATED_PRODUCT_UID);
        assertThat(testMarketPlace.getReTaggedVlanId()).isEqualTo(UPDATED_RE_TAGGED_VLAN_ID);
        assertThat(testMarketPlace.getProvisioningStatus()).isEqualTo(UPDATED_PROVISIONING_STATUS);
        assertThat(testMarketPlace.getVlanIdA()).isEqualTo(UPDATED_VLAN_ID_A);
        assertThat(testMarketPlace.getVlanIdB()).isEqualTo(UPDATED_VLAN_ID_B);
        assertThat(testMarketPlace.getVlanIdS()).isEqualTo(UPDATED_VLAN_ID_S);
        assertThat(testMarketPlace.getNtuId()).isEqualTo(UPDATED_NTU_ID);
        assertThat(testMarketPlace.getUserIp()).isEqualTo(UPDATED_USER_IP);
        assertThat(testMarketPlace.getFirewallPrice()).isEqualTo(UPDATED_FIREWALL_PRICE);
        assertThat(testMarketPlace.getFirewallStatus()).isEqualTo(UPDATED_FIREWALL_STATUS);
        assertThat(testMarketPlace.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testMarketPlace.getbEndProductUid()).isEqualTo(UPDATED_B_END_PRODUCT_UID);
        assertThat(testMarketPlace.getPartnerType()).isEqualTo(UPDATED_PARTNER_TYPE);
        assertThat(testMarketPlace.getCircuitType()).isEqualTo(UPDATED_CIRCUIT_TYPE);
        assertThat(testMarketPlace.getUserSubnet()).isEqualTo(UPDATED_USER_SUBNET);
        assertThat(testMarketPlace.getMyGw()).isEqualTo(UPDATED_MY_GW);
        assertThat(testMarketPlace.getActivePortGw()).isEqualTo(UPDATED_ACTIVE_PORT_GW);
        assertThat(testMarketPlace.getAwsAuthKey()).isEqualTo(UPDATED_AWS_AUTH_KEY);
        assertThat(testMarketPlace.getAwsIp()).isEqualTo(UPDATED_AWS_IP);
        assertThat(testMarketPlace.getAsn()).isEqualTo(UPDATED_ASN);
        assertThat(testMarketPlace.getPeerAsn()).isEqualTo(UPDATED_PEER_ASN);
        assertThat(testMarketPlace.getVxcType()).isEqualTo(UPDATED_VXC_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMarketPlace() throws Exception {
        int databaseSizeBeforeUpdate = marketPlaceRepository.findAll().size();

        // Create the MarketPlace
        MarketPlaceDTO marketPlaceDTO = marketPlaceMapper.toDto(marketPlace);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMarketPlaceMockMvc
            .perform(
                put("/api/market-places").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(marketPlaceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MarketPlace in the database
        List<MarketPlace> marketPlaceList = marketPlaceRepository.findAll();
        assertThat(marketPlaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMarketPlace() throws Exception {
        // Initialize the database
        marketPlaceRepository.saveAndFlush(marketPlace);

        int databaseSizeBeforeDelete = marketPlaceRepository.findAll().size();

        // Delete the marketPlace
        restMarketPlaceMockMvc
            .perform(delete("/api/market-places/{id}", marketPlace.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MarketPlace> marketPlaceList = marketPlaceRepository.findAll();
        assertThat(marketPlaceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
