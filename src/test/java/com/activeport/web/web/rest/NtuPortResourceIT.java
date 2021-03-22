package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.NtuPort;
import com.activeport.web.domain.enumeration.PortTypeEnum;
import com.activeport.web.repository.NtuPortRepository;
import com.activeport.web.service.NtuPortService;
import com.activeport.web.service.dto.NtuPortDTO;
import com.activeport.web.service.mapper.NtuPortMapper;
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
 * Integration tests for the {@link NtuPortResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NtuPortResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MAC = "AAAAAAAAAA";
    private static final String UPDATED_MAC = "BBBBBBBBBB";

    private static final Integer DEFAULT_PORT = 1;
    private static final Integer UPDATED_PORT = 2;

    private static final PortTypeEnum DEFAULT_PORT_TYPE = PortTypeEnum.ETHERNET;
    private static final PortTypeEnum UPDATED_PORT_TYPE = PortTypeEnum.SFP;

    private static final Boolean DEFAULT_TRUNK = false;
    private static final Boolean UPDATED_TRUNK = true;

    private static final Boolean DEFAULT_JUMBO = false;
    private static final Boolean UPDATED_JUMBO = true;

    private static final String DEFAULT_PORT_SPEED = "AAAAAAAAAA";
    private static final String UPDATED_PORT_SPEED = "BBBBBBBBBB";

    private static final Boolean DEFAULT_INTERNET_PORT = false;
    private static final Boolean UPDATED_INTERNET_PORT = true;

    private static final String DEFAULT_UPLINK_PORT = "AAAAAAAAAA";
    private static final String UPDATED_UPLINK_PORT = "BBBBBBBBBB";

    @Autowired
    private NtuPortRepository ntuPortRepository;

    @Autowired
    private NtuPortMapper ntuPortMapper;

    @Autowired
    private NtuPortService ntuPortService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNtuPortMockMvc;

    private NtuPort ntuPort;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NtuPort createEntity(EntityManager em) {
        NtuPort ntuPort = new NtuPort()
            .name(DEFAULT_NAME)
            .label(DEFAULT_LABEL)
            .description(DEFAULT_DESCRIPTION)
            .mac(DEFAULT_MAC)
            .port(DEFAULT_PORT)
            .portType(DEFAULT_PORT_TYPE)
            .trunk(DEFAULT_TRUNK)
            .jumbo(DEFAULT_JUMBO)
            .portSpeed(DEFAULT_PORT_SPEED)
            .internetPort(DEFAULT_INTERNET_PORT)
            .uplinkPort(DEFAULT_UPLINK_PORT);
        return ntuPort;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NtuPort createUpdatedEntity(EntityManager em) {
        NtuPort ntuPort = new NtuPort()
            .name(UPDATED_NAME)
            .label(UPDATED_LABEL)
            .description(UPDATED_DESCRIPTION)
            .mac(UPDATED_MAC)
            .port(UPDATED_PORT)
            .portType(UPDATED_PORT_TYPE)
            .trunk(UPDATED_TRUNK)
            .jumbo(UPDATED_JUMBO)
            .portSpeed(UPDATED_PORT_SPEED)
            .internetPort(UPDATED_INTERNET_PORT)
            .uplinkPort(UPDATED_UPLINK_PORT);
        return ntuPort;
    }

    @BeforeEach
    public void initTest() {
        ntuPort = createEntity(em);
    }

    @Test
    @Transactional
    public void createNtuPort() throws Exception {
        int databaseSizeBeforeCreate = ntuPortRepository.findAll().size();
        // Create the NtuPort
        NtuPortDTO ntuPortDTO = ntuPortMapper.toDto(ntuPort);
        restNtuPortMockMvc
            .perform(post("/api/ntu-ports").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuPortDTO)))
            .andExpect(status().isCreated());

        // Validate the NtuPort in the database
        List<NtuPort> ntuPortList = ntuPortRepository.findAll();
        assertThat(ntuPortList).hasSize(databaseSizeBeforeCreate + 1);
        NtuPort testNtuPort = ntuPortList.get(ntuPortList.size() - 1);
        assertThat(testNtuPort.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNtuPort.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testNtuPort.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNtuPort.getMac()).isEqualTo(DEFAULT_MAC);
        assertThat(testNtuPort.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testNtuPort.getPortType()).isEqualTo(DEFAULT_PORT_TYPE);
        assertThat(testNtuPort.isTrunk()).isEqualTo(DEFAULT_TRUNK);
        assertThat(testNtuPort.isJumbo()).isEqualTo(DEFAULT_JUMBO);
        assertThat(testNtuPort.getPortSpeed()).isEqualTo(DEFAULT_PORT_SPEED);
        assertThat(testNtuPort.isInternetPort()).isEqualTo(DEFAULT_INTERNET_PORT);
        assertThat(testNtuPort.getUplinkPort()).isEqualTo(DEFAULT_UPLINK_PORT);
    }

    @Test
    @Transactional
    public void createNtuPortWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ntuPortRepository.findAll().size();

        // Create the NtuPort with an existing ID
        ntuPort.setId(1L);
        NtuPortDTO ntuPortDTO = ntuPortMapper.toDto(ntuPort);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNtuPortMockMvc
            .perform(post("/api/ntu-ports").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuPortDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NtuPort in the database
        List<NtuPort> ntuPortList = ntuPortRepository.findAll();
        assertThat(ntuPortList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNtuPorts() throws Exception {
        // Initialize the database
        ntuPortRepository.saveAndFlush(ntuPort);

        // Get all the ntuPortList
        restNtuPortMockMvc
            .perform(get("/api/ntu-ports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ntuPort.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].mac").value(hasItem(DEFAULT_MAC)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)))
            .andExpect(jsonPath("$.[*].portType").value(hasItem(DEFAULT_PORT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trunk").value(hasItem(DEFAULT_TRUNK.booleanValue())))
            .andExpect(jsonPath("$.[*].jumbo").value(hasItem(DEFAULT_JUMBO.booleanValue())))
            .andExpect(jsonPath("$.[*].portSpeed").value(hasItem(DEFAULT_PORT_SPEED)))
            .andExpect(jsonPath("$.[*].internetPort").value(hasItem(DEFAULT_INTERNET_PORT.booleanValue())))
            .andExpect(jsonPath("$.[*].uplinkPort").value(hasItem(DEFAULT_UPLINK_PORT)));
    }

    @Test
    @Transactional
    public void getNtuPort() throws Exception {
        // Initialize the database
        ntuPortRepository.saveAndFlush(ntuPort);

        // Get the ntuPort
        restNtuPortMockMvc
            .perform(get("/api/ntu-ports/{id}", ntuPort.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ntuPort.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.mac").value(DEFAULT_MAC))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT))
            .andExpect(jsonPath("$.portType").value(DEFAULT_PORT_TYPE.toString()))
            .andExpect(jsonPath("$.trunk").value(DEFAULT_TRUNK.booleanValue()))
            .andExpect(jsonPath("$.jumbo").value(DEFAULT_JUMBO.booleanValue()))
            .andExpect(jsonPath("$.portSpeed").value(DEFAULT_PORT_SPEED))
            .andExpect(jsonPath("$.internetPort").value(DEFAULT_INTERNET_PORT.booleanValue()))
            .andExpect(jsonPath("$.uplinkPort").value(DEFAULT_UPLINK_PORT));
    }

    @Test
    @Transactional
    public void getNonExistingNtuPort() throws Exception {
        // Get the ntuPort
        restNtuPortMockMvc.perform(get("/api/ntu-ports/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNtuPort() throws Exception {
        // Initialize the database
        ntuPortRepository.saveAndFlush(ntuPort);

        int databaseSizeBeforeUpdate = ntuPortRepository.findAll().size();

        // Update the ntuPort
        NtuPort updatedNtuPort = ntuPortRepository.findById(ntuPort.getId()).get();
        // Disconnect from session so that the updates on updatedNtuPort are not directly saved in db
        em.detach(updatedNtuPort);
        updatedNtuPort
            .name(UPDATED_NAME)
            .label(UPDATED_LABEL)
            .description(UPDATED_DESCRIPTION)
            .mac(UPDATED_MAC)
            .port(UPDATED_PORT)
            .portType(UPDATED_PORT_TYPE)
            .trunk(UPDATED_TRUNK)
            .jumbo(UPDATED_JUMBO)
            .portSpeed(UPDATED_PORT_SPEED)
            .internetPort(UPDATED_INTERNET_PORT)
            .uplinkPort(UPDATED_UPLINK_PORT);
        NtuPortDTO ntuPortDTO = ntuPortMapper.toDto(updatedNtuPort);

        restNtuPortMockMvc
            .perform(put("/api/ntu-ports").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuPortDTO)))
            .andExpect(status().isOk());

        // Validate the NtuPort in the database
        List<NtuPort> ntuPortList = ntuPortRepository.findAll();
        assertThat(ntuPortList).hasSize(databaseSizeBeforeUpdate);
        NtuPort testNtuPort = ntuPortList.get(ntuPortList.size() - 1);
        assertThat(testNtuPort.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNtuPort.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testNtuPort.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNtuPort.getMac()).isEqualTo(UPDATED_MAC);
        assertThat(testNtuPort.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testNtuPort.getPortType()).isEqualTo(UPDATED_PORT_TYPE);
        assertThat(testNtuPort.isTrunk()).isEqualTo(UPDATED_TRUNK);
        assertThat(testNtuPort.isJumbo()).isEqualTo(UPDATED_JUMBO);
        assertThat(testNtuPort.getPortSpeed()).isEqualTo(UPDATED_PORT_SPEED);
        assertThat(testNtuPort.isInternetPort()).isEqualTo(UPDATED_INTERNET_PORT);
        assertThat(testNtuPort.getUplinkPort()).isEqualTo(UPDATED_UPLINK_PORT);
    }

    @Test
    @Transactional
    public void updateNonExistingNtuPort() throws Exception {
        int databaseSizeBeforeUpdate = ntuPortRepository.findAll().size();

        // Create the NtuPort
        NtuPortDTO ntuPortDTO = ntuPortMapper.toDto(ntuPort);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNtuPortMockMvc
            .perform(put("/api/ntu-ports").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuPortDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NtuPort in the database
        List<NtuPort> ntuPortList = ntuPortRepository.findAll();
        assertThat(ntuPortList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNtuPort() throws Exception {
        // Initialize the database
        ntuPortRepository.saveAndFlush(ntuPort);

        int databaseSizeBeforeDelete = ntuPortRepository.findAll().size();

        // Delete the ntuPort
        restNtuPortMockMvc
            .perform(delete("/api/ntu-ports/{id}", ntuPort.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NtuPort> ntuPortList = ntuPortRepository.findAll();
        assertThat(ntuPortList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
