package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.NtuType;
import com.activeport.web.domain.enumeration.OsTypeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import com.activeport.web.repository.NtuTypeRepository;
import com.activeport.web.service.NtuTypeService;
import com.activeport.web.service.dto.NtuTypeDTO;
import com.activeport.web.service.mapper.NtuTypeMapper;
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
 * Integration tests for the {@link NtuTypeResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NtuTypeResourceIT {
    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ETHERNET_PORTS = 1;
    private static final Integer UPDATED_ETHERNET_PORTS = 2;

    private static final Integer DEFAULT_SFP_PORTS = 1;
    private static final Integer UPDATED_SFP_PORTS = 2;

    private static final String DEFAULT_PICTURE_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final PortServiceTypeEnum DEFAULT_PORT_SERVICE_TYPE = PortServiceTypeEnum.EDGE;
    private static final PortServiceTypeEnum UPDATED_PORT_SERVICE_TYPE = PortServiceTypeEnum.SWITCH;

    private static final OsTypeEnum DEFAULT_OS_TYPE = OsTypeEnum.JUNOS;
    private static final OsTypeEnum UPDATED_OS_TYPE = OsTypeEnum.MIKROTIK;

    private static final String DEFAULT_ETHER_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_ETHER_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_SFP_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_SFP_PREFIX = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_INDEX = 1;
    private static final Integer UPDATED_START_INDEX = 2;

    private static final String DEFAULT_PORT_TEMPLATE = "AAAAAAAAAA";
    private static final String UPDATED_PORT_TEMPLATE = "BBBBBBBBBB";

    @Autowired
    private NtuTypeRepository ntuTypeRepository;

    @Autowired
    private NtuTypeMapper ntuTypeMapper;

    @Autowired
    private NtuTypeService ntuTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNtuTypeMockMvc;

    private NtuType ntuType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NtuType createEntity(EntityManager em) {
        NtuType ntuType = new NtuType()
            .model(DEFAULT_MODEL)
            .ethernetPorts(DEFAULT_ETHERNET_PORTS)
            .sfpPorts(DEFAULT_SFP_PORTS)
            .pictureContentType(DEFAULT_PICTURE_CONTENT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .portServiceType(DEFAULT_PORT_SERVICE_TYPE)
            .osType(DEFAULT_OS_TYPE)
            .etherPrefix(DEFAULT_ETHER_PREFIX)
            .sfpPrefix(DEFAULT_SFP_PREFIX)
            .startIndex(DEFAULT_START_INDEX)
            .portTemplate(DEFAULT_PORT_TEMPLATE);
        return ntuType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NtuType createUpdatedEntity(EntityManager em) {
        NtuType ntuType = new NtuType()
            .model(UPDATED_MODEL)
            .ethernetPorts(UPDATED_ETHERNET_PORTS)
            .sfpPorts(UPDATED_SFP_PORTS)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .portServiceType(UPDATED_PORT_SERVICE_TYPE)
            .osType(UPDATED_OS_TYPE)
            .etherPrefix(UPDATED_ETHER_PREFIX)
            .sfpPrefix(UPDATED_SFP_PREFIX)
            .startIndex(UPDATED_START_INDEX)
            .portTemplate(UPDATED_PORT_TEMPLATE);
        return ntuType;
    }

    @BeforeEach
    public void initTest() {
        ntuType = createEntity(em);
    }

    @Test
    @Transactional
    public void createNtuType() throws Exception {
        int databaseSizeBeforeCreate = ntuTypeRepository.findAll().size();
        // Create the NtuType
        NtuTypeDTO ntuTypeDTO = ntuTypeMapper.toDto(ntuType);
        restNtuTypeMockMvc
            .perform(post("/api/ntu-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the NtuType in the database
        List<NtuType> ntuTypeList = ntuTypeRepository.findAll();
        assertThat(ntuTypeList).hasSize(databaseSizeBeforeCreate + 1);
        NtuType testNtuType = ntuTypeList.get(ntuTypeList.size() - 1);
        assertThat(testNtuType.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testNtuType.getEthernetPorts()).isEqualTo(DEFAULT_ETHERNET_PORTS);
        assertThat(testNtuType.getSfpPorts()).isEqualTo(DEFAULT_SFP_PORTS);
        assertThat(testNtuType.getPictureContentType()).isEqualTo(DEFAULT_PICTURE_CONTENT_TYPE);
        assertThat(testNtuType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNtuType.getPortServiceType()).isEqualTo(DEFAULT_PORT_SERVICE_TYPE);
        assertThat(testNtuType.getOsType()).isEqualTo(DEFAULT_OS_TYPE);
        assertThat(testNtuType.getEtherPrefix()).isEqualTo(DEFAULT_ETHER_PREFIX);
        assertThat(testNtuType.getSfpPrefix()).isEqualTo(DEFAULT_SFP_PREFIX);
        assertThat(testNtuType.getStartIndex()).isEqualTo(DEFAULT_START_INDEX);
        assertThat(testNtuType.getPortTemplate()).isEqualTo(DEFAULT_PORT_TEMPLATE);
    }

    @Test
    @Transactional
    public void createNtuTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ntuTypeRepository.findAll().size();

        // Create the NtuType with an existing ID
        ntuType.setId(1L);
        NtuTypeDTO ntuTypeDTO = ntuTypeMapper.toDto(ntuType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNtuTypeMockMvc
            .perform(post("/api/ntu-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NtuType in the database
        List<NtuType> ntuTypeList = ntuTypeRepository.findAll();
        assertThat(ntuTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNtuTypes() throws Exception {
        // Initialize the database
        ntuTypeRepository.saveAndFlush(ntuType);

        // Get all the ntuTypeList
        restNtuTypeMockMvc
            .perform(get("/api/ntu-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ntuType.getId().intValue())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].ethernetPorts").value(hasItem(DEFAULT_ETHERNET_PORTS)))
            .andExpect(jsonPath("$.[*].sfpPorts").value(hasItem(DEFAULT_SFP_PORTS)))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].portServiceType").value(hasItem(DEFAULT_PORT_SERVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].osType").value(hasItem(DEFAULT_OS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].etherPrefix").value(hasItem(DEFAULT_ETHER_PREFIX)))
            .andExpect(jsonPath("$.[*].sfpPrefix").value(hasItem(DEFAULT_SFP_PREFIX)))
            .andExpect(jsonPath("$.[*].startIndex").value(hasItem(DEFAULT_START_INDEX)))
            .andExpect(jsonPath("$.[*].portTemplate").value(hasItem(DEFAULT_PORT_TEMPLATE.toString())));
    }

    @Test
    @Transactional
    public void getNtuType() throws Exception {
        // Initialize the database
        ntuTypeRepository.saveAndFlush(ntuType);

        // Get the ntuType
        restNtuTypeMockMvc
            .perform(get("/api/ntu-types/{id}", ntuType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ntuType.getId().intValue()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.ethernetPorts").value(DEFAULT_ETHERNET_PORTS))
            .andExpect(jsonPath("$.sfpPorts").value(DEFAULT_SFP_PORTS))
            .andExpect(jsonPath("$.pictureContentType").value(DEFAULT_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.portServiceType").value(DEFAULT_PORT_SERVICE_TYPE.toString()))
            .andExpect(jsonPath("$.osType").value(DEFAULT_OS_TYPE.toString()))
            .andExpect(jsonPath("$.etherPrefix").value(DEFAULT_ETHER_PREFIX))
            .andExpect(jsonPath("$.sfpPrefix").value(DEFAULT_SFP_PREFIX))
            .andExpect(jsonPath("$.startIndex").value(DEFAULT_START_INDEX))
            .andExpect(jsonPath("$.portTemplate").value(DEFAULT_PORT_TEMPLATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNtuType() throws Exception {
        // Get the ntuType
        restNtuTypeMockMvc.perform(get("/api/ntu-types/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNtuType() throws Exception {
        // Initialize the database
        ntuTypeRepository.saveAndFlush(ntuType);

        int databaseSizeBeforeUpdate = ntuTypeRepository.findAll().size();

        // Update the ntuType
        NtuType updatedNtuType = ntuTypeRepository.findById(ntuType.getId()).get();
        // Disconnect from session so that the updates on updatedNtuType are not directly saved in db
        em.detach(updatedNtuType);
        updatedNtuType
            .model(UPDATED_MODEL)
            .ethernetPorts(UPDATED_ETHERNET_PORTS)
            .sfpPorts(UPDATED_SFP_PORTS)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .portServiceType(UPDATED_PORT_SERVICE_TYPE)
            .osType(UPDATED_OS_TYPE)
            .etherPrefix(UPDATED_ETHER_PREFIX)
            .sfpPrefix(UPDATED_SFP_PREFIX)
            .startIndex(UPDATED_START_INDEX)
            .portTemplate(UPDATED_PORT_TEMPLATE);
        NtuTypeDTO ntuTypeDTO = ntuTypeMapper.toDto(updatedNtuType);

        restNtuTypeMockMvc
            .perform(put("/api/ntu-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuTypeDTO)))
            .andExpect(status().isOk());

        // Validate the NtuType in the database
        List<NtuType> ntuTypeList = ntuTypeRepository.findAll();
        assertThat(ntuTypeList).hasSize(databaseSizeBeforeUpdate);
        NtuType testNtuType = ntuTypeList.get(ntuTypeList.size() - 1);
        assertThat(testNtuType.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testNtuType.getEthernetPorts()).isEqualTo(UPDATED_ETHERNET_PORTS);
        assertThat(testNtuType.getSfpPorts()).isEqualTo(UPDATED_SFP_PORTS);
        assertThat(testNtuType.getPictureContentType()).isEqualTo(UPDATED_PICTURE_CONTENT_TYPE);
        assertThat(testNtuType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNtuType.getPortServiceType()).isEqualTo(UPDATED_PORT_SERVICE_TYPE);
        assertThat(testNtuType.getOsType()).isEqualTo(UPDATED_OS_TYPE);
        assertThat(testNtuType.getEtherPrefix()).isEqualTo(UPDATED_ETHER_PREFIX);
        assertThat(testNtuType.getSfpPrefix()).isEqualTo(UPDATED_SFP_PREFIX);
        assertThat(testNtuType.getStartIndex()).isEqualTo(UPDATED_START_INDEX);
        assertThat(testNtuType.getPortTemplate()).isEqualTo(UPDATED_PORT_TEMPLATE);
    }

    @Test
    @Transactional
    public void updateNonExistingNtuType() throws Exception {
        int databaseSizeBeforeUpdate = ntuTypeRepository.findAll().size();

        // Create the NtuType
        NtuTypeDTO ntuTypeDTO = ntuTypeMapper.toDto(ntuType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNtuTypeMockMvc
            .perform(put("/api/ntu-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ntuTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NtuType in the database
        List<NtuType> ntuTypeList = ntuTypeRepository.findAll();
        assertThat(ntuTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNtuType() throws Exception {
        // Initialize the database
        ntuTypeRepository.saveAndFlush(ntuType);

        int databaseSizeBeforeDelete = ntuTypeRepository.findAll().size();

        // Delete the ntuType
        restNtuTypeMockMvc
            .perform(delete("/api/ntu-types/{id}", ntuType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NtuType> ntuTypeList = ntuTypeRepository.findAll();
        assertThat(ntuTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
