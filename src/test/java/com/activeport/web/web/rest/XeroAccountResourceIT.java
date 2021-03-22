package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.XeroAccount;
import com.activeport.web.repository.XeroAccountRepository;
import com.activeport.web.service.XeroAccountService;
import com.activeport.web.service.dto.XeroAccountDTO;
import com.activeport.web.service.mapper.XeroAccountMapper;
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
 * Integration tests for the {@link XeroAccountResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class XeroAccountResourceIT {
    private static final String DEFAULT_CONTACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ID = "BBBBBBBBBB";

    @Autowired
    private XeroAccountRepository xeroAccountRepository;

    @Autowired
    private XeroAccountMapper xeroAccountMapper;

    @Autowired
    private XeroAccountService xeroAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restXeroAccountMockMvc;

    private XeroAccount xeroAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static XeroAccount createEntity(EntityManager em) {
        XeroAccount xeroAccount = new XeroAccount().contactId(DEFAULT_CONTACT_ID);
        return xeroAccount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static XeroAccount createUpdatedEntity(EntityManager em) {
        XeroAccount xeroAccount = new XeroAccount().contactId(UPDATED_CONTACT_ID);
        return xeroAccount;
    }

    @BeforeEach
    public void initTest() {
        xeroAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createXeroAccount() throws Exception {
        int databaseSizeBeforeCreate = xeroAccountRepository.findAll().size();
        // Create the XeroAccount
        XeroAccountDTO xeroAccountDTO = xeroAccountMapper.toDto(xeroAccount);
        restXeroAccountMockMvc
            .perform(
                post("/api/xero-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(xeroAccountDTO))
            )
            .andExpect(status().isCreated());

        // Validate the XeroAccount in the database
        List<XeroAccount> xeroAccountList = xeroAccountRepository.findAll();
        assertThat(xeroAccountList).hasSize(databaseSizeBeforeCreate + 1);
        XeroAccount testXeroAccount = xeroAccountList.get(xeroAccountList.size() - 1);
        assertThat(testXeroAccount.getContactId()).isEqualTo(DEFAULT_CONTACT_ID);
    }

    @Test
    @Transactional
    public void createXeroAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = xeroAccountRepository.findAll().size();

        // Create the XeroAccount with an existing ID
        xeroAccount.setId(1L);
        XeroAccountDTO xeroAccountDTO = xeroAccountMapper.toDto(xeroAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restXeroAccountMockMvc
            .perform(
                post("/api/xero-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(xeroAccountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the XeroAccount in the database
        List<XeroAccount> xeroAccountList = xeroAccountRepository.findAll();
        assertThat(xeroAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllXeroAccounts() throws Exception {
        // Initialize the database
        xeroAccountRepository.saveAndFlush(xeroAccount);

        // Get all the xeroAccountList
        restXeroAccountMockMvc
            .perform(get("/api/xero-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(xeroAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].contactId").value(hasItem(DEFAULT_CONTACT_ID)));
    }

    @Test
    @Transactional
    public void getXeroAccount() throws Exception {
        // Initialize the database
        xeroAccountRepository.saveAndFlush(xeroAccount);

        // Get the xeroAccount
        restXeroAccountMockMvc
            .perform(get("/api/xero-accounts/{id}", xeroAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(xeroAccount.getId().intValue()))
            .andExpect(jsonPath("$.contactId").value(DEFAULT_CONTACT_ID));
    }

    @Test
    @Transactional
    public void getNonExistingXeroAccount() throws Exception {
        // Get the xeroAccount
        restXeroAccountMockMvc.perform(get("/api/xero-accounts/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateXeroAccount() throws Exception {
        // Initialize the database
        xeroAccountRepository.saveAndFlush(xeroAccount);

        int databaseSizeBeforeUpdate = xeroAccountRepository.findAll().size();

        // Update the xeroAccount
        XeroAccount updatedXeroAccount = xeroAccountRepository.findById(xeroAccount.getId()).get();
        // Disconnect from session so that the updates on updatedXeroAccount are not directly saved in db
        em.detach(updatedXeroAccount);
        updatedXeroAccount.contactId(UPDATED_CONTACT_ID);
        XeroAccountDTO xeroAccountDTO = xeroAccountMapper.toDto(updatedXeroAccount);

        restXeroAccountMockMvc
            .perform(
                put("/api/xero-accounts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(xeroAccountDTO))
            )
            .andExpect(status().isOk());

        // Validate the XeroAccount in the database
        List<XeroAccount> xeroAccountList = xeroAccountRepository.findAll();
        assertThat(xeroAccountList).hasSize(databaseSizeBeforeUpdate);
        XeroAccount testXeroAccount = xeroAccountList.get(xeroAccountList.size() - 1);
        assertThat(testXeroAccount.getContactId()).isEqualTo(UPDATED_CONTACT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingXeroAccount() throws Exception {
        int databaseSizeBeforeUpdate = xeroAccountRepository.findAll().size();

        // Create the XeroAccount
        XeroAccountDTO xeroAccountDTO = xeroAccountMapper.toDto(xeroAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restXeroAccountMockMvc
            .perform(
                put("/api/xero-accounts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(xeroAccountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the XeroAccount in the database
        List<XeroAccount> xeroAccountList = xeroAccountRepository.findAll();
        assertThat(xeroAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteXeroAccount() throws Exception {
        // Initialize the database
        xeroAccountRepository.saveAndFlush(xeroAccount);

        int databaseSizeBeforeDelete = xeroAccountRepository.findAll().size();

        // Delete the xeroAccount
        restXeroAccountMockMvc
            .perform(delete("/api/xero-accounts/{id}", xeroAccount.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<XeroAccount> xeroAccountList = xeroAccountRepository.findAll();
        assertThat(xeroAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
