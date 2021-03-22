package com.activeport.web.web.rest;

import static com.activeport.web.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.BillingSystem;
import com.activeport.web.domain.enumeration.BillingTypeEnum;
import com.activeport.web.repository.BillingSystemRepository;
import com.activeport.web.service.BillingSystemService;
import com.activeport.web.service.dto.BillingSystemDTO;
import com.activeport.web.service.mapper.BillingSystemMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link BillingSystemResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillingSystemResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BillingTypeEnum DEFAULT_API = BillingTypeEnum.XERO;
    private static final BillingTypeEnum UPDATED_API = BillingTypeEnum.HOSTBILL;

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

    private static final ZonedDateTime DEFAULT_START_BILLING = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_BILLING = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    @Autowired
    private BillingSystemRepository billingSystemRepository;

    @Autowired
    private BillingSystemMapper billingSystemMapper;

    @Autowired
    private BillingSystemService billingSystemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingSystemMockMvc;

    private BillingSystem billingSystem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingSystem createEntity(EntityManager em) {
        BillingSystem billingSystem = new BillingSystem()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .api(DEFAULT_API)
            .stage(DEFAULT_STAGE)
            .username(DEFAULT_USERNAME)
            .secret(DEFAULT_SECRET)
            .privateKeyCert(DEFAULT_PRIVATE_KEY_CERT)
            .privateKeyPassword(DEFAULT_PRIVATE_KEY_PASSWORD)
            .billingUid(DEFAULT_BILLING_UID)
            .startBilling(DEFAULT_START_BILLING)
            .currencyCode(DEFAULT_CURRENCY_CODE);
        return billingSystem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingSystem createUpdatedEntity(EntityManager em) {
        BillingSystem billingSystem = new BillingSystem()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .api(UPDATED_API)
            .stage(UPDATED_STAGE)
            .username(UPDATED_USERNAME)
            .secret(UPDATED_SECRET)
            .privateKeyCert(UPDATED_PRIVATE_KEY_CERT)
            .privateKeyPassword(UPDATED_PRIVATE_KEY_PASSWORD)
            .billingUid(UPDATED_BILLING_UID)
            .startBilling(UPDATED_START_BILLING)
            .currencyCode(UPDATED_CURRENCY_CODE);
        return billingSystem;
    }

    @BeforeEach
    public void initTest() {
        billingSystem = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillingSystem() throws Exception {
        int databaseSizeBeforeCreate = billingSystemRepository.findAll().size();
        // Create the BillingSystem
        BillingSystemDTO billingSystemDTO = billingSystemMapper.toDto(billingSystem);
        restBillingSystemMockMvc
            .perform(
                post("/api/billing-systems")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billingSystemDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BillingSystem in the database
        List<BillingSystem> billingSystemList = billingSystemRepository.findAll();
        assertThat(billingSystemList).hasSize(databaseSizeBeforeCreate + 1);
        BillingSystem testBillingSystem = billingSystemList.get(billingSystemList.size() - 1);
        assertThat(testBillingSystem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBillingSystem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBillingSystem.getApi()).isEqualTo(DEFAULT_API);
        assertThat(testBillingSystem.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testBillingSystem.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testBillingSystem.getSecret()).isEqualTo(DEFAULT_SECRET);
        assertThat(testBillingSystem.getPrivateKeyCert()).isEqualTo(DEFAULT_PRIVATE_KEY_CERT);
        assertThat(testBillingSystem.getPrivateKeyPassword()).isEqualTo(DEFAULT_PRIVATE_KEY_PASSWORD);
        assertThat(testBillingSystem.getBillingUid()).isEqualTo(DEFAULT_BILLING_UID);
        assertThat(testBillingSystem.getStartBilling()).isEqualTo(DEFAULT_START_BILLING);
        assertThat(testBillingSystem.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
    }

    @Test
    @Transactional
    public void createBillingSystemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billingSystemRepository.findAll().size();

        // Create the BillingSystem with an existing ID
        billingSystem.setId(1L);
        BillingSystemDTO billingSystemDTO = billingSystemMapper.toDto(billingSystem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingSystemMockMvc
            .perform(
                post("/api/billing-systems")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billingSystemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BillingSystem in the database
        List<BillingSystem> billingSystemList = billingSystemRepository.findAll();
        assertThat(billingSystemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = billingSystemRepository.findAll().size();
        // set the field null
        billingSystem.setName(null);

        // Create the BillingSystem, which fails.
        BillingSystemDTO billingSystemDTO = billingSystemMapper.toDto(billingSystem);

        restBillingSystemMockMvc
            .perform(
                post("/api/billing-systems")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billingSystemDTO))
            )
            .andExpect(status().isBadRequest());

        List<BillingSystem> billingSystemList = billingSystemRepository.findAll();
        assertThat(billingSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApiIsRequired() throws Exception {
        int databaseSizeBeforeTest = billingSystemRepository.findAll().size();
        // set the field null
        billingSystem.setApi(null);

        // Create the BillingSystem, which fails.
        BillingSystemDTO billingSystemDTO = billingSystemMapper.toDto(billingSystem);

        restBillingSystemMockMvc
            .perform(
                post("/api/billing-systems")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billingSystemDTO))
            )
            .andExpect(status().isBadRequest());

        List<BillingSystem> billingSystemList = billingSystemRepository.findAll();
        assertThat(billingSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBillingSystems() throws Exception {
        // Initialize the database
        billingSystemRepository.saveAndFlush(billingSystem);

        // Get all the billingSystemList
        restBillingSystemMockMvc
            .perform(get("/api/billing-systems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingSystem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].api").value(hasItem(DEFAULT_API.toString())))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].secret").value(hasItem(DEFAULT_SECRET)))
            .andExpect(jsonPath("$.[*].privateKeyCert").value(hasItem(DEFAULT_PRIVATE_KEY_CERT)))
            .andExpect(jsonPath("$.[*].privateKeyPassword").value(hasItem(DEFAULT_PRIVATE_KEY_PASSWORD)))
            .andExpect(jsonPath("$.[*].billingUid").value(hasItem(DEFAULT_BILLING_UID)))
            .andExpect(jsonPath("$.[*].startBilling").value(hasItem(sameInstant(DEFAULT_START_BILLING))))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)));
    }

    @Test
    @Transactional
    public void getBillingSystem() throws Exception {
        // Initialize the database
        billingSystemRepository.saveAndFlush(billingSystem);

        // Get the billingSystem
        restBillingSystemMockMvc
            .perform(get("/api/billing-systems/{id}", billingSystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingSystem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.api").value(DEFAULT_API.toString()))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.secret").value(DEFAULT_SECRET))
            .andExpect(jsonPath("$.privateKeyCert").value(DEFAULT_PRIVATE_KEY_CERT))
            .andExpect(jsonPath("$.privateKeyPassword").value(DEFAULT_PRIVATE_KEY_PASSWORD))
            .andExpect(jsonPath("$.billingUid").value(DEFAULT_BILLING_UID))
            .andExpect(jsonPath("$.startBilling").value(sameInstant(DEFAULT_START_BILLING)))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingBillingSystem() throws Exception {
        // Get the billingSystem
        restBillingSystemMockMvc.perform(get("/api/billing-systems/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillingSystem() throws Exception {
        // Initialize the database
        billingSystemRepository.saveAndFlush(billingSystem);

        int databaseSizeBeforeUpdate = billingSystemRepository.findAll().size();

        // Update the billingSystem
        BillingSystem updatedBillingSystem = billingSystemRepository.findById(billingSystem.getId()).get();
        // Disconnect from session so that the updates on updatedBillingSystem are not directly saved in db
        em.detach(updatedBillingSystem);
        updatedBillingSystem
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .api(UPDATED_API)
            .stage(UPDATED_STAGE)
            .username(UPDATED_USERNAME)
            .secret(UPDATED_SECRET)
            .privateKeyCert(UPDATED_PRIVATE_KEY_CERT)
            .privateKeyPassword(UPDATED_PRIVATE_KEY_PASSWORD)
            .billingUid(UPDATED_BILLING_UID)
            .startBilling(UPDATED_START_BILLING)
            .currencyCode(UPDATED_CURRENCY_CODE);
        BillingSystemDTO billingSystemDTO = billingSystemMapper.toDto(updatedBillingSystem);

        restBillingSystemMockMvc
            .perform(
                put("/api/billing-systems")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billingSystemDTO))
            )
            .andExpect(status().isOk());

        // Validate the BillingSystem in the database
        List<BillingSystem> billingSystemList = billingSystemRepository.findAll();
        assertThat(billingSystemList).hasSize(databaseSizeBeforeUpdate);
        BillingSystem testBillingSystem = billingSystemList.get(billingSystemList.size() - 1);
        assertThat(testBillingSystem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBillingSystem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBillingSystem.getApi()).isEqualTo(UPDATED_API);
        assertThat(testBillingSystem.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testBillingSystem.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testBillingSystem.getSecret()).isEqualTo(UPDATED_SECRET);
        assertThat(testBillingSystem.getPrivateKeyCert()).isEqualTo(UPDATED_PRIVATE_KEY_CERT);
        assertThat(testBillingSystem.getPrivateKeyPassword()).isEqualTo(UPDATED_PRIVATE_KEY_PASSWORD);
        assertThat(testBillingSystem.getBillingUid()).isEqualTo(UPDATED_BILLING_UID);
        assertThat(testBillingSystem.getStartBilling()).isEqualTo(UPDATED_START_BILLING);
        assertThat(testBillingSystem.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingBillingSystem() throws Exception {
        int databaseSizeBeforeUpdate = billingSystemRepository.findAll().size();

        // Create the BillingSystem
        BillingSystemDTO billingSystemDTO = billingSystemMapper.toDto(billingSystem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingSystemMockMvc
            .perform(
                put("/api/billing-systems")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billingSystemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BillingSystem in the database
        List<BillingSystem> billingSystemList = billingSystemRepository.findAll();
        assertThat(billingSystemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillingSystem() throws Exception {
        // Initialize the database
        billingSystemRepository.saveAndFlush(billingSystem);

        int databaseSizeBeforeDelete = billingSystemRepository.findAll().size();

        // Delete the billingSystem
        restBillingSystemMockMvc
            .perform(delete("/api/billing-systems/{id}", billingSystem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillingSystem> billingSystemList = billingSystemRepository.findAll();
        assertThat(billingSystemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
