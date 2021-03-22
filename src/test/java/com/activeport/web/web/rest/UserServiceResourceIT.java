package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.UserService;
import com.activeport.web.domain.enumeration.CircuitTypeEnum;
import com.activeport.web.domain.enumeration.ConnectTypeEnum;
import com.activeport.web.domain.enumeration.FirewallStatusEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.activeport.web.domain.enumeration.ProvisioningStatusEnum;
import com.activeport.web.domain.enumeration.ServiceStateEnum;
import com.activeport.web.domain.enumeration.VXCTypeEnum;
import com.activeport.web.repository.UserServiceRepository;
import com.activeport.web.service.UserServiceService;
import com.activeport.web.service.dto.UserServiceDTO;
import com.activeport.web.service.mapper.UserServiceMapper;
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
 * Integration tests for the {@link UserServiceResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserServiceResourceIT {
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
    private UserServiceRepository userServiceRepository;

    @Autowired
    private UserServiceMapper userServiceMapper;

    @Autowired
    private UserServiceService userServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserServiceMockMvc;

    private UserService userService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserService createEntity(EntityManager em) {
        UserService userService = new UserService()
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
        return userService;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserService createUpdatedEntity(EntityManager em) {
        UserService userService = new UserService()
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
        return userService;
    }

    @BeforeEach
    public void initTest() {
        userService = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserService() throws Exception {
        int databaseSizeBeforeCreate = userServiceRepository.findAll().size();
        // Create the UserService
        UserServiceDTO userServiceDTO = userServiceMapper.toDto(userService);
        restUserServiceMockMvc
            .perform(
                post("/api/user-services")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userServiceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserService in the database
        List<UserService> userServiceList = userServiceRepository.findAll();
        assertThat(userServiceList).hasSize(databaseSizeBeforeCreate + 1);
        UserService testUserService = userServiceList.get(userServiceList.size() - 1);
        assertThat(testUserService.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUserService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserService.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testUserService.getServiceKey()).isEqualTo(DEFAULT_SERVICE_KEY);
        assertThat(testUserService.getRateLimit()).isEqualTo(DEFAULT_RATE_LIMIT);
        assertThat(testUserService.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testUserService.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testUserService.getProductUid()).isEqualTo(DEFAULT_PRODUCT_UID);
        assertThat(testUserService.getReTaggedVlanId()).isEqualTo(DEFAULT_RE_TAGGED_VLAN_ID);
        assertThat(testUserService.getProvisioningStatus()).isEqualTo(DEFAULT_PROVISIONING_STATUS);
        assertThat(testUserService.getVlanIdA()).isEqualTo(DEFAULT_VLAN_ID_A);
        assertThat(testUserService.getVlanIdB()).isEqualTo(DEFAULT_VLAN_ID_B);
        assertThat(testUserService.getVlanIdS()).isEqualTo(DEFAULT_VLAN_ID_S);
        assertThat(testUserService.getNtuId()).isEqualTo(DEFAULT_NTU_ID);
        assertThat(testUserService.getUserIp()).isEqualTo(DEFAULT_USER_IP);
        assertThat(testUserService.getFirewallPrice()).isEqualTo(DEFAULT_FIREWALL_PRICE);
        assertThat(testUserService.getFirewallStatus()).isEqualTo(DEFAULT_FIREWALL_STATUS);
        assertThat(testUserService.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testUserService.getbEndProductUid()).isEqualTo(DEFAULT_B_END_PRODUCT_UID);
        assertThat(testUserService.getPartnerType()).isEqualTo(DEFAULT_PARTNER_TYPE);
        assertThat(testUserService.getCircuitType()).isEqualTo(DEFAULT_CIRCUIT_TYPE);
        assertThat(testUserService.getUserSubnet()).isEqualTo(DEFAULT_USER_SUBNET);
        assertThat(testUserService.getMyGw()).isEqualTo(DEFAULT_MY_GW);
        assertThat(testUserService.getActivePortGw()).isEqualTo(DEFAULT_ACTIVE_PORT_GW);
        assertThat(testUserService.getAwsAuthKey()).isEqualTo(DEFAULT_AWS_AUTH_KEY);
        assertThat(testUserService.getAwsIp()).isEqualTo(DEFAULT_AWS_IP);
        assertThat(testUserService.getAsn()).isEqualTo(DEFAULT_ASN);
        assertThat(testUserService.getPeerAsn()).isEqualTo(DEFAULT_PEER_ASN);
        assertThat(testUserService.getVxcType()).isEqualTo(DEFAULT_VXC_TYPE);
    }

    @Test
    @Transactional
    public void createUserServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userServiceRepository.findAll().size();

        // Create the UserService with an existing ID
        userService.setId(1L);
        UserServiceDTO userServiceDTO = userServiceMapper.toDto(userService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserServiceMockMvc
            .perform(
                post("/api/user-services")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userServiceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserService in the database
        List<UserService> userServiceList = userServiceRepository.findAll();
        assertThat(userServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserServices() throws Exception {
        // Initialize the database
        userServiceRepository.saveAndFlush(userService);

        // Get all the userServiceList
        restUserServiceMockMvc
            .perform(get("/api/user-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userService.getId().intValue())))
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
    public void getUserService() throws Exception {
        // Initialize the database
        userServiceRepository.saveAndFlush(userService);

        // Get the userService
        restUserServiceMockMvc
            .perform(get("/api/user-services/{id}", userService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userService.getId().intValue()))
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
    public void getNonExistingUserService() throws Exception {
        // Get the userService
        restUserServiceMockMvc.perform(get("/api/user-services/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserService() throws Exception {
        // Initialize the database
        userServiceRepository.saveAndFlush(userService);

        int databaseSizeBeforeUpdate = userServiceRepository.findAll().size();

        // Update the userService
        UserService updatedUserService = userServiceRepository.findById(userService.getId()).get();
        // Disconnect from session so that the updates on updatedUserService are not directly saved in db
        em.detach(updatedUserService);
        updatedUserService
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
        UserServiceDTO userServiceDTO = userServiceMapper.toDto(updatedUserService);

        restUserServiceMockMvc
            .perform(
                put("/api/user-services").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userServiceDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserService in the database
        List<UserService> userServiceList = userServiceRepository.findAll();
        assertThat(userServiceList).hasSize(databaseSizeBeforeUpdate);
        UserService testUserService = userServiceList.get(userServiceList.size() - 1);
        assertThat(testUserService.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUserService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserService.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUserService.getServiceKey()).isEqualTo(UPDATED_SERVICE_KEY);
        assertThat(testUserService.getRateLimit()).isEqualTo(UPDATED_RATE_LIMIT);
        assertThat(testUserService.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testUserService.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testUserService.getProductUid()).isEqualTo(UPDATED_PRODUCT_UID);
        assertThat(testUserService.getReTaggedVlanId()).isEqualTo(UPDATED_RE_TAGGED_VLAN_ID);
        assertThat(testUserService.getProvisioningStatus()).isEqualTo(UPDATED_PROVISIONING_STATUS);
        assertThat(testUserService.getVlanIdA()).isEqualTo(UPDATED_VLAN_ID_A);
        assertThat(testUserService.getVlanIdB()).isEqualTo(UPDATED_VLAN_ID_B);
        assertThat(testUserService.getVlanIdS()).isEqualTo(UPDATED_VLAN_ID_S);
        assertThat(testUserService.getNtuId()).isEqualTo(UPDATED_NTU_ID);
        assertThat(testUserService.getUserIp()).isEqualTo(UPDATED_USER_IP);
        assertThat(testUserService.getFirewallPrice()).isEqualTo(UPDATED_FIREWALL_PRICE);
        assertThat(testUserService.getFirewallStatus()).isEqualTo(UPDATED_FIREWALL_STATUS);
        assertThat(testUserService.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUserService.getbEndProductUid()).isEqualTo(UPDATED_B_END_PRODUCT_UID);
        assertThat(testUserService.getPartnerType()).isEqualTo(UPDATED_PARTNER_TYPE);
        assertThat(testUserService.getCircuitType()).isEqualTo(UPDATED_CIRCUIT_TYPE);
        assertThat(testUserService.getUserSubnet()).isEqualTo(UPDATED_USER_SUBNET);
        assertThat(testUserService.getMyGw()).isEqualTo(UPDATED_MY_GW);
        assertThat(testUserService.getActivePortGw()).isEqualTo(UPDATED_ACTIVE_PORT_GW);
        assertThat(testUserService.getAwsAuthKey()).isEqualTo(UPDATED_AWS_AUTH_KEY);
        assertThat(testUserService.getAwsIp()).isEqualTo(UPDATED_AWS_IP);
        assertThat(testUserService.getAsn()).isEqualTo(UPDATED_ASN);
        assertThat(testUserService.getPeerAsn()).isEqualTo(UPDATED_PEER_ASN);
        assertThat(testUserService.getVxcType()).isEqualTo(UPDATED_VXC_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserService() throws Exception {
        int databaseSizeBeforeUpdate = userServiceRepository.findAll().size();

        // Create the UserService
        UserServiceDTO userServiceDTO = userServiceMapper.toDto(userService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserServiceMockMvc
            .perform(
                put("/api/user-services").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userServiceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserService in the database
        List<UserService> userServiceList = userServiceRepository.findAll();
        assertThat(userServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserService() throws Exception {
        // Initialize the database
        userServiceRepository.saveAndFlush(userService);

        int databaseSizeBeforeDelete = userServiceRepository.findAll().size();

        // Delete the userService
        restUserServiceMockMvc
            .perform(delete("/api/user-services/{id}", userService.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserService> userServiceList = userServiceRepository.findAll();
        assertThat(userServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
