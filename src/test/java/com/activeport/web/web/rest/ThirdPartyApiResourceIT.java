package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ThirdPartyApi;
import com.activeport.web.domain.enumeration.ApiType;
import com.activeport.web.domain.enumeration.PortApiTypeEnum;
import com.activeport.web.repository.ThirdPartyApiRepository;
import com.activeport.web.service.ThirdPartyApiService;
import com.activeport.web.service.dto.ThirdPartyApiDTO;
import com.activeport.web.service.mapper.ThirdPartyApiMapper;
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
 * Integration tests for the {@link ThirdPartyApiResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ThirdPartyApiResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ApiType DEFAULT_API = ApiType.MEGAPORT;
    private static final ApiType UPDATED_API = ApiType.XERO;

    private static final String DEFAULT_STAGE = "AAAAAAAAAA";
    private static final String UPDATED_STAGE = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_SECRET = "BBBBBBBBBB";

    private static final String DEFAULT_PRIVATE_KEY_CERT = "AAAAAAAAAA";
    private static final String UPDATED_PRIVATE_KEY_CERT = "BBBBBBBBBB";

    private static final String DEFAULT_PRIVATE_KEY_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PRIVATE_KEY_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_UID = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_UID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UID = "BBBBBBBBBB";

    private static final String DEFAULT_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_ALLOW_SHARED_PORTS_UID = "AAAAAAAAAA";
    private static final String UPDATED_ALLOW_SHARED_PORTS_UID = "BBBBBBBBBB";

    private static final PortApiTypeEnum DEFAULT_CONNECTION_TYPE = PortApiTypeEnum.SHARED;
    private static final PortApiTypeEnum UPDATED_CONNECTION_TYPE = PortApiTypeEnum.PRIVATE;

    @Autowired
    private ThirdPartyApiRepository thirdPartyApiRepository;

    @Autowired
    private ThirdPartyApiMapper thirdPartyApiMapper;

    @Autowired
    private ThirdPartyApiService thirdPartyApiService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restThirdPartyApiMockMvc;

    private ThirdPartyApi thirdPartyApi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThirdPartyApi createEntity(EntityManager em) {
        ThirdPartyApi thirdPartyApi = new ThirdPartyApi()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .api(DEFAULT_API)
            .stage(DEFAULT_STAGE)
            .username(DEFAULT_USERNAME)
            .secret(DEFAULT_SECRET)
            .privateKeyCert(DEFAULT_PRIVATE_KEY_CERT)
            .privateKeyPassword(DEFAULT_PRIVATE_KEY_PASSWORD)
            .billingUid(DEFAULT_BILLING_UID)
            .productUid(DEFAULT_PRODUCT_UID)
            .endpoint(DEFAULT_ENDPOINT)
            .allowSharedPortsUid(DEFAULT_ALLOW_SHARED_PORTS_UID)
            .connectionType(DEFAULT_CONNECTION_TYPE);
        return thirdPartyApi;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThirdPartyApi createUpdatedEntity(EntityManager em) {
        ThirdPartyApi thirdPartyApi = new ThirdPartyApi()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .api(UPDATED_API)
            .stage(UPDATED_STAGE)
            .username(UPDATED_USERNAME)
            .secret(UPDATED_SECRET)
            .privateKeyCert(UPDATED_PRIVATE_KEY_CERT)
            .privateKeyPassword(UPDATED_PRIVATE_KEY_PASSWORD)
            .billingUid(UPDATED_BILLING_UID)
            .productUid(UPDATED_PRODUCT_UID)
            .endpoint(UPDATED_ENDPOINT)
            .allowSharedPortsUid(UPDATED_ALLOW_SHARED_PORTS_UID)
            .connectionType(UPDATED_CONNECTION_TYPE);
        return thirdPartyApi;
    }

    @BeforeEach
    public void initTest() {
        thirdPartyApi = createEntity(em);
    }

    @Test
    @Transactional
    public void createThirdPartyApi() throws Exception {
        int databaseSizeBeforeCreate = thirdPartyApiRepository.findAll().size();
        // Create the ThirdPartyApi
        ThirdPartyApiDTO thirdPartyApiDTO = thirdPartyApiMapper.toDto(thirdPartyApi);
        restThirdPartyApiMockMvc
            .perform(
                post("/api/third-party-apis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thirdPartyApiDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ThirdPartyApi in the database
        List<ThirdPartyApi> thirdPartyApiList = thirdPartyApiRepository.findAll();
        assertThat(thirdPartyApiList).hasSize(databaseSizeBeforeCreate + 1);
        ThirdPartyApi testThirdPartyApi = thirdPartyApiList.get(thirdPartyApiList.size() - 1);
        assertThat(testThirdPartyApi.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testThirdPartyApi.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testThirdPartyApi.getApi()).isEqualTo(DEFAULT_API);
        assertThat(testThirdPartyApi.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testThirdPartyApi.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testThirdPartyApi.getSecret()).isEqualTo(DEFAULT_SECRET);
        assertThat(testThirdPartyApi.getPrivateKeyCert()).isEqualTo(DEFAULT_PRIVATE_KEY_CERT);
        assertThat(testThirdPartyApi.getPrivateKeyPassword()).isEqualTo(DEFAULT_PRIVATE_KEY_PASSWORD);
        assertThat(testThirdPartyApi.getBillingUid()).isEqualTo(DEFAULT_BILLING_UID);
        assertThat(testThirdPartyApi.getProductUid()).isEqualTo(DEFAULT_PRODUCT_UID);
        assertThat(testThirdPartyApi.getEndpoint()).isEqualTo(DEFAULT_ENDPOINT);
        assertThat(testThirdPartyApi.getAllowSharedPortsUid()).isEqualTo(DEFAULT_ALLOW_SHARED_PORTS_UID);
        assertThat(testThirdPartyApi.getConnectionType()).isEqualTo(DEFAULT_CONNECTION_TYPE);
    }

    @Test
    @Transactional
    public void createThirdPartyApiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thirdPartyApiRepository.findAll().size();

        // Create the ThirdPartyApi with an existing ID
        thirdPartyApi.setId(1L);
        ThirdPartyApiDTO thirdPartyApiDTO = thirdPartyApiMapper.toDto(thirdPartyApi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThirdPartyApiMockMvc
            .perform(
                post("/api/third-party-apis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thirdPartyApiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThirdPartyApi in the database
        List<ThirdPartyApi> thirdPartyApiList = thirdPartyApiRepository.findAll();
        assertThat(thirdPartyApiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = thirdPartyApiRepository.findAll().size();
        // set the field null
        thirdPartyApi.setName(null);

        // Create the ThirdPartyApi, which fails.
        ThirdPartyApiDTO thirdPartyApiDTO = thirdPartyApiMapper.toDto(thirdPartyApi);

        restThirdPartyApiMockMvc
            .perform(
                post("/api/third-party-apis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thirdPartyApiDTO))
            )
            .andExpect(status().isBadRequest());

        List<ThirdPartyApi> thirdPartyApiList = thirdPartyApiRepository.findAll();
        assertThat(thirdPartyApiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApiIsRequired() throws Exception {
        int databaseSizeBeforeTest = thirdPartyApiRepository.findAll().size();
        // set the field null
        thirdPartyApi.setApi(null);

        // Create the ThirdPartyApi, which fails.
        ThirdPartyApiDTO thirdPartyApiDTO = thirdPartyApiMapper.toDto(thirdPartyApi);

        restThirdPartyApiMockMvc
            .perform(
                post("/api/third-party-apis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thirdPartyApiDTO))
            )
            .andExpect(status().isBadRequest());

        List<ThirdPartyApi> thirdPartyApiList = thirdPartyApiRepository.findAll();
        assertThat(thirdPartyApiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllThirdPartyApis() throws Exception {
        // Initialize the database
        thirdPartyApiRepository.saveAndFlush(thirdPartyApi);

        // Get all the thirdPartyApiList
        restThirdPartyApiMockMvc
            .perform(get("/api/third-party-apis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thirdPartyApi.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].api").value(hasItem(DEFAULT_API.toString())))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].secret").value(hasItem(DEFAULT_SECRET)))
            .andExpect(jsonPath("$.[*].privateKeyCert").value(hasItem(DEFAULT_PRIVATE_KEY_CERT)))
            .andExpect(jsonPath("$.[*].privateKeyPassword").value(hasItem(DEFAULT_PRIVATE_KEY_PASSWORD)))
            .andExpect(jsonPath("$.[*].billingUid").value(hasItem(DEFAULT_BILLING_UID)))
            .andExpect(jsonPath("$.[*].productUid").value(hasItem(DEFAULT_PRODUCT_UID)))
            .andExpect(jsonPath("$.[*].endpoint").value(hasItem(DEFAULT_ENDPOINT)))
            .andExpect(jsonPath("$.[*].allowSharedPortsUid").value(hasItem(DEFAULT_ALLOW_SHARED_PORTS_UID)))
            .andExpect(jsonPath("$.[*].connectionType").value(hasItem(DEFAULT_CONNECTION_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getThirdPartyApi() throws Exception {
        // Initialize the database
        thirdPartyApiRepository.saveAndFlush(thirdPartyApi);

        // Get the thirdPartyApi
        restThirdPartyApiMockMvc
            .perform(get("/api/third-party-apis/{id}", thirdPartyApi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(thirdPartyApi.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.api").value(DEFAULT_API.toString()))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.secret").value(DEFAULT_SECRET))
            .andExpect(jsonPath("$.privateKeyCert").value(DEFAULT_PRIVATE_KEY_CERT))
            .andExpect(jsonPath("$.privateKeyPassword").value(DEFAULT_PRIVATE_KEY_PASSWORD))
            .andExpect(jsonPath("$.billingUid").value(DEFAULT_BILLING_UID))
            .andExpect(jsonPath("$.productUid").value(DEFAULT_PRODUCT_UID))
            .andExpect(jsonPath("$.endpoint").value(DEFAULT_ENDPOINT))
            .andExpect(jsonPath("$.allowSharedPortsUid").value(DEFAULT_ALLOW_SHARED_PORTS_UID))
            .andExpect(jsonPath("$.connectionType").value(DEFAULT_CONNECTION_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingThirdPartyApi() throws Exception {
        // Get the thirdPartyApi
        restThirdPartyApiMockMvc.perform(get("/api/third-party-apis/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThirdPartyApi() throws Exception {
        // Initialize the database
        thirdPartyApiRepository.saveAndFlush(thirdPartyApi);

        int databaseSizeBeforeUpdate = thirdPartyApiRepository.findAll().size();

        // Update the thirdPartyApi
        ThirdPartyApi updatedThirdPartyApi = thirdPartyApiRepository.findById(thirdPartyApi.getId()).get();
        // Disconnect from session so that the updates on updatedThirdPartyApi are not directly saved in db
        em.detach(updatedThirdPartyApi);
        updatedThirdPartyApi
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .api(UPDATED_API)
            .stage(UPDATED_STAGE)
            .username(UPDATED_USERNAME)
            .secret(UPDATED_SECRET)
            .privateKeyCert(UPDATED_PRIVATE_KEY_CERT)
            .privateKeyPassword(UPDATED_PRIVATE_KEY_PASSWORD)
            .billingUid(UPDATED_BILLING_UID)
            .productUid(UPDATED_PRODUCT_UID)
            .endpoint(UPDATED_ENDPOINT)
            .allowSharedPortsUid(UPDATED_ALLOW_SHARED_PORTS_UID)
            .connectionType(UPDATED_CONNECTION_TYPE);
        ThirdPartyApiDTO thirdPartyApiDTO = thirdPartyApiMapper.toDto(updatedThirdPartyApi);

        restThirdPartyApiMockMvc
            .perform(
                put("/api/third-party-apis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thirdPartyApiDTO))
            )
            .andExpect(status().isOk());

        // Validate the ThirdPartyApi in the database
        List<ThirdPartyApi> thirdPartyApiList = thirdPartyApiRepository.findAll();
        assertThat(thirdPartyApiList).hasSize(databaseSizeBeforeUpdate);
        ThirdPartyApi testThirdPartyApi = thirdPartyApiList.get(thirdPartyApiList.size() - 1);
        assertThat(testThirdPartyApi.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testThirdPartyApi.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testThirdPartyApi.getApi()).isEqualTo(UPDATED_API);
        assertThat(testThirdPartyApi.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testThirdPartyApi.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testThirdPartyApi.getSecret()).isEqualTo(UPDATED_SECRET);
        assertThat(testThirdPartyApi.getPrivateKeyCert()).isEqualTo(UPDATED_PRIVATE_KEY_CERT);
        assertThat(testThirdPartyApi.getPrivateKeyPassword()).isEqualTo(UPDATED_PRIVATE_KEY_PASSWORD);
        assertThat(testThirdPartyApi.getBillingUid()).isEqualTo(UPDATED_BILLING_UID);
        assertThat(testThirdPartyApi.getProductUid()).isEqualTo(UPDATED_PRODUCT_UID);
        assertThat(testThirdPartyApi.getEndpoint()).isEqualTo(UPDATED_ENDPOINT);
        assertThat(testThirdPartyApi.getAllowSharedPortsUid()).isEqualTo(UPDATED_ALLOW_SHARED_PORTS_UID);
        assertThat(testThirdPartyApi.getConnectionType()).isEqualTo(UPDATED_CONNECTION_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingThirdPartyApi() throws Exception {
        int databaseSizeBeforeUpdate = thirdPartyApiRepository.findAll().size();

        // Create the ThirdPartyApi
        ThirdPartyApiDTO thirdPartyApiDTO = thirdPartyApiMapper.toDto(thirdPartyApi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThirdPartyApiMockMvc
            .perform(
                put("/api/third-party-apis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thirdPartyApiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThirdPartyApi in the database
        List<ThirdPartyApi> thirdPartyApiList = thirdPartyApiRepository.findAll();
        assertThat(thirdPartyApiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThirdPartyApi() throws Exception {
        // Initialize the database
        thirdPartyApiRepository.saveAndFlush(thirdPartyApi);

        int databaseSizeBeforeDelete = thirdPartyApiRepository.findAll().size();

        // Delete the thirdPartyApi
        restThirdPartyApiMockMvc
            .perform(delete("/api/third-party-apis/{id}", thirdPartyApi.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ThirdPartyApi> thirdPartyApiList = thirdPartyApiRepository.findAll();
        assertThat(thirdPartyApiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
