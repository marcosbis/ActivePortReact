package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.Tenant;
import com.activeport.web.repository.TenantRepository;
import com.activeport.web.service.TenantService;
import com.activeport.web.service.dto.TenantDTO;
import com.activeport.web.service.mapper.TenantMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link TenantResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TenantResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISABLE_ACCESS = false;
    private static final Boolean UPDATED_DISABLE_ACCESS = true;

    private static final Integer DEFAULT_ILM_DAYS = 1;
    private static final Integer UPDATED_ILM_DAYS = 2;

    private static final Instant DEFAULT_SLM_DAYS = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SLM_DAYS = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenantMockMvc;

    private Tenant tenant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tenant createEntity(EntityManager em) {
        Tenant tenant = new Tenant()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .tenantId(DEFAULT_TENANT_ID)
            .disableAccess(DEFAULT_DISABLE_ACCESS)
            .ilmDays(DEFAULT_ILM_DAYS)
            .slmDays(DEFAULT_SLM_DAYS);
        return tenant;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tenant createUpdatedEntity(EntityManager em) {
        Tenant tenant = new Tenant()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tenantId(UPDATED_TENANT_ID)
            .disableAccess(UPDATED_DISABLE_ACCESS)
            .ilmDays(UPDATED_ILM_DAYS)
            .slmDays(UPDATED_SLM_DAYS);
        return tenant;
    }

    @BeforeEach
    public void initTest() {
        tenant = createEntity(em);
    }

    @Test
    @Transactional
    public void createTenant() throws Exception {
        int databaseSizeBeforeCreate = tenantRepository.findAll().size();
        // Create the Tenant
        TenantDTO tenantDTO = tenantMapper.toDto(tenant);
        restTenantMockMvc
            .perform(post("/api/tenants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeCreate + 1);
        Tenant testTenant = tenantList.get(tenantList.size() - 1);
        assertThat(testTenant.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTenant.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenant.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testTenant.isDisableAccess()).isEqualTo(DEFAULT_DISABLE_ACCESS);
        assertThat(testTenant.getIlmDays()).isEqualTo(DEFAULT_ILM_DAYS);
        assertThat(testTenant.getSlmDays()).isEqualTo(DEFAULT_SLM_DAYS);
    }

    @Test
    @Transactional
    public void createTenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenantRepository.findAll().size();

        // Create the Tenant with an existing ID
        tenant.setId(1L);
        TenantDTO tenantDTO = tenantMapper.toDto(tenant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenantMockMvc
            .perform(post("/api/tenants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTenants() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList
        restTenantMockMvc
            .perform(get("/api/tenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)))
            .andExpect(jsonPath("$.[*].disableAccess").value(hasItem(DEFAULT_DISABLE_ACCESS.booleanValue())))
            .andExpect(jsonPath("$.[*].ilmDays").value(hasItem(DEFAULT_ILM_DAYS)))
            .andExpect(jsonPath("$.[*].slmDays").value(hasItem(DEFAULT_SLM_DAYS.toString())));
    }

    @Test
    @Transactional
    public void getTenant() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get the tenant
        restTenantMockMvc
            .perform(get("/api/tenants/{id}", tenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenant.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID))
            .andExpect(jsonPath("$.disableAccess").value(DEFAULT_DISABLE_ACCESS.booleanValue()))
            .andExpect(jsonPath("$.ilmDays").value(DEFAULT_ILM_DAYS))
            .andExpect(jsonPath("$.slmDays").value(DEFAULT_SLM_DAYS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTenant() throws Exception {
        // Get the tenant
        restTenantMockMvc.perform(get("/api/tenants/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTenant() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        int databaseSizeBeforeUpdate = tenantRepository.findAll().size();

        // Update the tenant
        Tenant updatedTenant = tenantRepository.findById(tenant.getId()).get();
        // Disconnect from session so that the updates on updatedTenant are not directly saved in db
        em.detach(updatedTenant);
        updatedTenant
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tenantId(UPDATED_TENANT_ID)
            .disableAccess(UPDATED_DISABLE_ACCESS)
            .ilmDays(UPDATED_ILM_DAYS)
            .slmDays(UPDATED_SLM_DAYS);
        TenantDTO tenantDTO = tenantMapper.toDto(updatedTenant);

        restTenantMockMvc
            .perform(put("/api/tenants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isOk());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeUpdate);
        Tenant testTenant = tenantList.get(tenantList.size() - 1);
        assertThat(testTenant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTenant.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenant.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testTenant.isDisableAccess()).isEqualTo(UPDATED_DISABLE_ACCESS);
        assertThat(testTenant.getIlmDays()).isEqualTo(UPDATED_ILM_DAYS);
        assertThat(testTenant.getSlmDays()).isEqualTo(UPDATED_SLM_DAYS);
    }

    @Test
    @Transactional
    public void updateNonExistingTenant() throws Exception {
        int databaseSizeBeforeUpdate = tenantRepository.findAll().size();

        // Create the Tenant
        TenantDTO tenantDTO = tenantMapper.toDto(tenant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenantMockMvc
            .perform(put("/api/tenants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTenant() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        int databaseSizeBeforeDelete = tenantRepository.findAll().size();

        // Delete the tenant
        restTenantMockMvc
            .perform(delete("/api/tenants/{id}", tenant.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
