package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.RealmIp;
import com.activeport.web.repository.RealmIpRepository;
import com.activeport.web.service.RealmIpService;
import com.activeport.web.service.dto.RealmIpDTO;
import com.activeport.web.service.mapper.RealmIpMapper;
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
 * Integration tests for the {@link RealmIpResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RealmIpResourceIT {
    private static final String DEFAULT_SUBNET = "AAAAAAAAAA";
    private static final String UPDATED_SUBNET = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MASK = "AAAAAAAAAA";
    private static final String UPDATED_MASK = "BBBBBBBBBB";

    private static final String DEFAULT_SUBNET_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SUBNET_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_IP = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_IP = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_IP = "AAAAAAAAAA";
    private static final String UPDATED_LAST_IP = "BBBBBBBBBB";

    private static final String DEFAULT_BROADCAST = "AAAAAAAAAA";
    private static final String UPDATED_BROADCAST = "BBBBBBBBBB";

    private static final String DEFAULT_CIR = "AAAAAAAAAA";
    private static final String UPDATED_CIR = "BBBBBBBBBB";

    private static final String DEFAULT_IPSEC_GATEWAY = "AAAAAAAAAA";
    private static final String UPDATED_IPSEC_GATEWAY = "BBBBBBBBBB";

    @Autowired
    private RealmIpRepository realmIpRepository;

    @Autowired
    private RealmIpMapper realmIpMapper;

    @Autowired
    private RealmIpService realmIpService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRealmIpMockMvc;

    private RealmIp realmIp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RealmIp createEntity(EntityManager em) {
        RealmIp realmIp = new RealmIp()
            .subnet(DEFAULT_SUBNET)
            .name(DEFAULT_NAME)
            .desciption(DEFAULT_DESCIPTION)
            .mask(DEFAULT_MASK)
            .subnetSize(DEFAULT_SUBNET_SIZE)
            .firstIp(DEFAULT_FIRST_IP)
            .lastIp(DEFAULT_LAST_IP)
            .broadcast(DEFAULT_BROADCAST)
            .cir(DEFAULT_CIR)
            .ipsecGateway(DEFAULT_IPSEC_GATEWAY);
        return realmIp;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RealmIp createUpdatedEntity(EntityManager em) {
        RealmIp realmIp = new RealmIp()
            .subnet(UPDATED_SUBNET)
            .name(UPDATED_NAME)
            .desciption(UPDATED_DESCIPTION)
            .mask(UPDATED_MASK)
            .subnetSize(UPDATED_SUBNET_SIZE)
            .firstIp(UPDATED_FIRST_IP)
            .lastIp(UPDATED_LAST_IP)
            .broadcast(UPDATED_BROADCAST)
            .cir(UPDATED_CIR)
            .ipsecGateway(UPDATED_IPSEC_GATEWAY);
        return realmIp;
    }

    @BeforeEach
    public void initTest() {
        realmIp = createEntity(em);
    }

    @Test
    @Transactional
    public void createRealmIp() throws Exception {
        int databaseSizeBeforeCreate = realmIpRepository.findAll().size();
        // Create the RealmIp
        RealmIpDTO realmIpDTO = realmIpMapper.toDto(realmIp);
        restRealmIpMockMvc
            .perform(post("/api/realm-ips").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(realmIpDTO)))
            .andExpect(status().isCreated());

        // Validate the RealmIp in the database
        List<RealmIp> realmIpList = realmIpRepository.findAll();
        assertThat(realmIpList).hasSize(databaseSizeBeforeCreate + 1);
        RealmIp testRealmIp = realmIpList.get(realmIpList.size() - 1);
        assertThat(testRealmIp.getSubnet()).isEqualTo(DEFAULT_SUBNET);
        assertThat(testRealmIp.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRealmIp.getDesciption()).isEqualTo(DEFAULT_DESCIPTION);
        assertThat(testRealmIp.getMask()).isEqualTo(DEFAULT_MASK);
        assertThat(testRealmIp.getSubnetSize()).isEqualTo(DEFAULT_SUBNET_SIZE);
        assertThat(testRealmIp.getFirstIp()).isEqualTo(DEFAULT_FIRST_IP);
        assertThat(testRealmIp.getLastIp()).isEqualTo(DEFAULT_LAST_IP);
        assertThat(testRealmIp.getBroadcast()).isEqualTo(DEFAULT_BROADCAST);
        assertThat(testRealmIp.getCir()).isEqualTo(DEFAULT_CIR);
        assertThat(testRealmIp.getIpsecGateway()).isEqualTo(DEFAULT_IPSEC_GATEWAY);
    }

    @Test
    @Transactional
    public void createRealmIpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = realmIpRepository.findAll().size();

        // Create the RealmIp with an existing ID
        realmIp.setId(1L);
        RealmIpDTO realmIpDTO = realmIpMapper.toDto(realmIp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRealmIpMockMvc
            .perform(post("/api/realm-ips").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(realmIpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RealmIp in the database
        List<RealmIp> realmIpList = realmIpRepository.findAll();
        assertThat(realmIpList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRealmIps() throws Exception {
        // Initialize the database
        realmIpRepository.saveAndFlush(realmIp);

        // Get all the realmIpList
        restRealmIpMockMvc
            .perform(get("/api/realm-ips?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(realmIp.getId().intValue())))
            .andExpect(jsonPath("$.[*].subnet").value(hasItem(DEFAULT_SUBNET)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desciption").value(hasItem(DEFAULT_DESCIPTION)))
            .andExpect(jsonPath("$.[*].mask").value(hasItem(DEFAULT_MASK)))
            .andExpect(jsonPath("$.[*].subnetSize").value(hasItem(DEFAULT_SUBNET_SIZE)))
            .andExpect(jsonPath("$.[*].firstIp").value(hasItem(DEFAULT_FIRST_IP)))
            .andExpect(jsonPath("$.[*].lastIp").value(hasItem(DEFAULT_LAST_IP)))
            .andExpect(jsonPath("$.[*].broadcast").value(hasItem(DEFAULT_BROADCAST)))
            .andExpect(jsonPath("$.[*].cir").value(hasItem(DEFAULT_CIR)))
            .andExpect(jsonPath("$.[*].ipsecGateway").value(hasItem(DEFAULT_IPSEC_GATEWAY)));
    }

    @Test
    @Transactional
    public void getRealmIp() throws Exception {
        // Initialize the database
        realmIpRepository.saveAndFlush(realmIp);

        // Get the realmIp
        restRealmIpMockMvc
            .perform(get("/api/realm-ips/{id}", realmIp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(realmIp.getId().intValue()))
            .andExpect(jsonPath("$.subnet").value(DEFAULT_SUBNET))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.desciption").value(DEFAULT_DESCIPTION))
            .andExpect(jsonPath("$.mask").value(DEFAULT_MASK))
            .andExpect(jsonPath("$.subnetSize").value(DEFAULT_SUBNET_SIZE))
            .andExpect(jsonPath("$.firstIp").value(DEFAULT_FIRST_IP))
            .andExpect(jsonPath("$.lastIp").value(DEFAULT_LAST_IP))
            .andExpect(jsonPath("$.broadcast").value(DEFAULT_BROADCAST))
            .andExpect(jsonPath("$.cir").value(DEFAULT_CIR))
            .andExpect(jsonPath("$.ipsecGateway").value(DEFAULT_IPSEC_GATEWAY));
    }

    @Test
    @Transactional
    public void getNonExistingRealmIp() throws Exception {
        // Get the realmIp
        restRealmIpMockMvc.perform(get("/api/realm-ips/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRealmIp() throws Exception {
        // Initialize the database
        realmIpRepository.saveAndFlush(realmIp);

        int databaseSizeBeforeUpdate = realmIpRepository.findAll().size();

        // Update the realmIp
        RealmIp updatedRealmIp = realmIpRepository.findById(realmIp.getId()).get();
        // Disconnect from session so that the updates on updatedRealmIp are not directly saved in db
        em.detach(updatedRealmIp);
        updatedRealmIp
            .subnet(UPDATED_SUBNET)
            .name(UPDATED_NAME)
            .desciption(UPDATED_DESCIPTION)
            .mask(UPDATED_MASK)
            .subnetSize(UPDATED_SUBNET_SIZE)
            .firstIp(UPDATED_FIRST_IP)
            .lastIp(UPDATED_LAST_IP)
            .broadcast(UPDATED_BROADCAST)
            .cir(UPDATED_CIR)
            .ipsecGateway(UPDATED_IPSEC_GATEWAY);
        RealmIpDTO realmIpDTO = realmIpMapper.toDto(updatedRealmIp);

        restRealmIpMockMvc
            .perform(put("/api/realm-ips").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(realmIpDTO)))
            .andExpect(status().isOk());

        // Validate the RealmIp in the database
        List<RealmIp> realmIpList = realmIpRepository.findAll();
        assertThat(realmIpList).hasSize(databaseSizeBeforeUpdate);
        RealmIp testRealmIp = realmIpList.get(realmIpList.size() - 1);
        assertThat(testRealmIp.getSubnet()).isEqualTo(UPDATED_SUBNET);
        assertThat(testRealmIp.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRealmIp.getDesciption()).isEqualTo(UPDATED_DESCIPTION);
        assertThat(testRealmIp.getMask()).isEqualTo(UPDATED_MASK);
        assertThat(testRealmIp.getSubnetSize()).isEqualTo(UPDATED_SUBNET_SIZE);
        assertThat(testRealmIp.getFirstIp()).isEqualTo(UPDATED_FIRST_IP);
        assertThat(testRealmIp.getLastIp()).isEqualTo(UPDATED_LAST_IP);
        assertThat(testRealmIp.getBroadcast()).isEqualTo(UPDATED_BROADCAST);
        assertThat(testRealmIp.getCir()).isEqualTo(UPDATED_CIR);
        assertThat(testRealmIp.getIpsecGateway()).isEqualTo(UPDATED_IPSEC_GATEWAY);
    }

    @Test
    @Transactional
    public void updateNonExistingRealmIp() throws Exception {
        int databaseSizeBeforeUpdate = realmIpRepository.findAll().size();

        // Create the RealmIp
        RealmIpDTO realmIpDTO = realmIpMapper.toDto(realmIp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRealmIpMockMvc
            .perform(put("/api/realm-ips").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(realmIpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RealmIp in the database
        List<RealmIp> realmIpList = realmIpRepository.findAll();
        assertThat(realmIpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRealmIp() throws Exception {
        // Initialize the database
        realmIpRepository.saveAndFlush(realmIp);

        int databaseSizeBeforeDelete = realmIpRepository.findAll().size();

        // Delete the realmIp
        restRealmIpMockMvc
            .perform(delete("/api/realm-ips/{id}", realmIp.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RealmIp> realmIpList = realmIpRepository.findAll();
        assertThat(realmIpList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
