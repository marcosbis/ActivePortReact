package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.ItemCode;
import com.activeport.web.repository.ItemCodeRepository;
import com.activeport.web.service.ItemCodeService;
import com.activeport.web.service.dto.ItemCodeDTO;
import com.activeport.web.service.mapper.ItemCodeMapper;
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
 * Integration tests for the {@link ItemCodeResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemCodeResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_RATE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_RATE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ACTIVATION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ACTIVATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Float DEFAULT_LOCAL_PRICE_RATE = 1F;
    private static final Float UPDATED_LOCAL_PRICE_RATE = 2F;

    private static final Float DEFAULT_LOCAL_PRICE_MONTLHY = 1F;
    private static final Float UPDATED_LOCAL_PRICE_MONTLHY = 2F;

    private static final Float DEFAULT_LOCAL_PRICE_ACTIVATION = 1F;
    private static final Float UPDATED_LOCAL_PRICE_ACTIVATION = 2F;

    private static final String DEFAULT_CODE_UP_LIFT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_UP_LIFT = "BBBBBBBBBB";

    private static final Float DEFAULT_UP_LIFT = 1F;
    private static final Float UPDATED_UP_LIFT = 2F;

    private static final Boolean DEFAULT_USE_UP_LIFT = false;
    private static final Boolean UPDATED_USE_UP_LIFT = true;

    private static final Boolean DEFAULT_USE_LOCAL_PRICE = false;
    private static final Boolean UPDATED_USE_LOCAL_PRICE = true;

    @Autowired
    private ItemCodeRepository itemCodeRepository;

    @Autowired
    private ItemCodeMapper itemCodeMapper;

    @Autowired
    private ItemCodeService itemCodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemCodeMockMvc;

    private ItemCode itemCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemCode createEntity(EntityManager em) {
        ItemCode itemCode = new ItemCode()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .codeRate(DEFAULT_CODE_RATE)
            .codeActivation(DEFAULT_CODE_ACTIVATION)
            .description(DEFAULT_DESCRIPTION)
            .localPriceRate(DEFAULT_LOCAL_PRICE_RATE)
            .localPriceMontlhy(DEFAULT_LOCAL_PRICE_MONTLHY)
            .localPriceActivation(DEFAULT_LOCAL_PRICE_ACTIVATION)
            .codeUpLift(DEFAULT_CODE_UP_LIFT)
            .upLift(DEFAULT_UP_LIFT)
            .useUpLift(DEFAULT_USE_UP_LIFT)
            .useLocalPrice(DEFAULT_USE_LOCAL_PRICE);
        return itemCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemCode createUpdatedEntity(EntityManager em) {
        ItemCode itemCode = new ItemCode()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .codeRate(UPDATED_CODE_RATE)
            .codeActivation(UPDATED_CODE_ACTIVATION)
            .description(UPDATED_DESCRIPTION)
            .localPriceRate(UPDATED_LOCAL_PRICE_RATE)
            .localPriceMontlhy(UPDATED_LOCAL_PRICE_MONTLHY)
            .localPriceActivation(UPDATED_LOCAL_PRICE_ACTIVATION)
            .codeUpLift(UPDATED_CODE_UP_LIFT)
            .upLift(UPDATED_UP_LIFT)
            .useUpLift(UPDATED_USE_UP_LIFT)
            .useLocalPrice(UPDATED_USE_LOCAL_PRICE);
        return itemCode;
    }

    @BeforeEach
    public void initTest() {
        itemCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemCode() throws Exception {
        int databaseSizeBeforeCreate = itemCodeRepository.findAll().size();
        // Create the ItemCode
        ItemCodeDTO itemCodeDTO = itemCodeMapper.toDto(itemCode);
        restItemCodeMockMvc
            .perform(
                post("/api/item-codes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemCodeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemCode in the database
        List<ItemCode> itemCodeList = itemCodeRepository.findAll();
        assertThat(itemCodeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemCode testItemCode = itemCodeList.get(itemCodeList.size() - 1);
        assertThat(testItemCode.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemCode.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testItemCode.getCodeRate()).isEqualTo(DEFAULT_CODE_RATE);
        assertThat(testItemCode.getCodeActivation()).isEqualTo(DEFAULT_CODE_ACTIVATION);
        assertThat(testItemCode.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemCode.getLocalPriceRate()).isEqualTo(DEFAULT_LOCAL_PRICE_RATE);
        assertThat(testItemCode.getLocalPriceMontlhy()).isEqualTo(DEFAULT_LOCAL_PRICE_MONTLHY);
        assertThat(testItemCode.getLocalPriceActivation()).isEqualTo(DEFAULT_LOCAL_PRICE_ACTIVATION);
        assertThat(testItemCode.getCodeUpLift()).isEqualTo(DEFAULT_CODE_UP_LIFT);
        assertThat(testItemCode.getUpLift()).isEqualTo(DEFAULT_UP_LIFT);
        assertThat(testItemCode.isUseUpLift()).isEqualTo(DEFAULT_USE_UP_LIFT);
        assertThat(testItemCode.isUseLocalPrice()).isEqualTo(DEFAULT_USE_LOCAL_PRICE);
    }

    @Test
    @Transactional
    public void createItemCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemCodeRepository.findAll().size();

        // Create the ItemCode with an existing ID
        itemCode.setId(1L);
        ItemCodeDTO itemCodeDTO = itemCodeMapper.toDto(itemCode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemCodeMockMvc
            .perform(
                post("/api/item-codes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemCode in the database
        List<ItemCode> itemCodeList = itemCodeRepository.findAll();
        assertThat(itemCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllItemCodes() throws Exception {
        // Initialize the database
        itemCodeRepository.saveAndFlush(itemCode);

        // Get all the itemCodeList
        restItemCodeMockMvc
            .perform(get("/api/item-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].codeRate").value(hasItem(DEFAULT_CODE_RATE)))
            .andExpect(jsonPath("$.[*].codeActivation").value(hasItem(DEFAULT_CODE_ACTIVATION)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].localPriceRate").value(hasItem(DEFAULT_LOCAL_PRICE_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].localPriceMontlhy").value(hasItem(DEFAULT_LOCAL_PRICE_MONTLHY.doubleValue())))
            .andExpect(jsonPath("$.[*].localPriceActivation").value(hasItem(DEFAULT_LOCAL_PRICE_ACTIVATION.doubleValue())))
            .andExpect(jsonPath("$.[*].codeUpLift").value(hasItem(DEFAULT_CODE_UP_LIFT)))
            .andExpect(jsonPath("$.[*].upLift").value(hasItem(DEFAULT_UP_LIFT.doubleValue())))
            .andExpect(jsonPath("$.[*].useUpLift").value(hasItem(DEFAULT_USE_UP_LIFT.booleanValue())))
            .andExpect(jsonPath("$.[*].useLocalPrice").value(hasItem(DEFAULT_USE_LOCAL_PRICE.booleanValue())));
    }

    @Test
    @Transactional
    public void getItemCode() throws Exception {
        // Initialize the database
        itemCodeRepository.saveAndFlush(itemCode);

        // Get the itemCode
        restItemCodeMockMvc
            .perform(get("/api/item-codes/{id}", itemCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemCode.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.codeRate").value(DEFAULT_CODE_RATE))
            .andExpect(jsonPath("$.codeActivation").value(DEFAULT_CODE_ACTIVATION))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.localPriceRate").value(DEFAULT_LOCAL_PRICE_RATE.doubleValue()))
            .andExpect(jsonPath("$.localPriceMontlhy").value(DEFAULT_LOCAL_PRICE_MONTLHY.doubleValue()))
            .andExpect(jsonPath("$.localPriceActivation").value(DEFAULT_LOCAL_PRICE_ACTIVATION.doubleValue()))
            .andExpect(jsonPath("$.codeUpLift").value(DEFAULT_CODE_UP_LIFT))
            .andExpect(jsonPath("$.upLift").value(DEFAULT_UP_LIFT.doubleValue()))
            .andExpect(jsonPath("$.useUpLift").value(DEFAULT_USE_UP_LIFT.booleanValue()))
            .andExpect(jsonPath("$.useLocalPrice").value(DEFAULT_USE_LOCAL_PRICE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItemCode() throws Exception {
        // Get the itemCode
        restItemCodeMockMvc.perform(get("/api/item-codes/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemCode() throws Exception {
        // Initialize the database
        itemCodeRepository.saveAndFlush(itemCode);

        int databaseSizeBeforeUpdate = itemCodeRepository.findAll().size();

        // Update the itemCode
        ItemCode updatedItemCode = itemCodeRepository.findById(itemCode.getId()).get();
        // Disconnect from session so that the updates on updatedItemCode are not directly saved in db
        em.detach(updatedItemCode);
        updatedItemCode
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .codeRate(UPDATED_CODE_RATE)
            .codeActivation(UPDATED_CODE_ACTIVATION)
            .description(UPDATED_DESCRIPTION)
            .localPriceRate(UPDATED_LOCAL_PRICE_RATE)
            .localPriceMontlhy(UPDATED_LOCAL_PRICE_MONTLHY)
            .localPriceActivation(UPDATED_LOCAL_PRICE_ACTIVATION)
            .codeUpLift(UPDATED_CODE_UP_LIFT)
            .upLift(UPDATED_UP_LIFT)
            .useUpLift(UPDATED_USE_UP_LIFT)
            .useLocalPrice(UPDATED_USE_LOCAL_PRICE);
        ItemCodeDTO itemCodeDTO = itemCodeMapper.toDto(updatedItemCode);

        restItemCodeMockMvc
            .perform(put("/api/item-codes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemCodeDTO)))
            .andExpect(status().isOk());

        // Validate the ItemCode in the database
        List<ItemCode> itemCodeList = itemCodeRepository.findAll();
        assertThat(itemCodeList).hasSize(databaseSizeBeforeUpdate);
        ItemCode testItemCode = itemCodeList.get(itemCodeList.size() - 1);
        assertThat(testItemCode.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemCode.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testItemCode.getCodeRate()).isEqualTo(UPDATED_CODE_RATE);
        assertThat(testItemCode.getCodeActivation()).isEqualTo(UPDATED_CODE_ACTIVATION);
        assertThat(testItemCode.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemCode.getLocalPriceRate()).isEqualTo(UPDATED_LOCAL_PRICE_RATE);
        assertThat(testItemCode.getLocalPriceMontlhy()).isEqualTo(UPDATED_LOCAL_PRICE_MONTLHY);
        assertThat(testItemCode.getLocalPriceActivation()).isEqualTo(UPDATED_LOCAL_PRICE_ACTIVATION);
        assertThat(testItemCode.getCodeUpLift()).isEqualTo(UPDATED_CODE_UP_LIFT);
        assertThat(testItemCode.getUpLift()).isEqualTo(UPDATED_UP_LIFT);
        assertThat(testItemCode.isUseUpLift()).isEqualTo(UPDATED_USE_UP_LIFT);
        assertThat(testItemCode.isUseLocalPrice()).isEqualTo(UPDATED_USE_LOCAL_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingItemCode() throws Exception {
        int databaseSizeBeforeUpdate = itemCodeRepository.findAll().size();

        // Create the ItemCode
        ItemCodeDTO itemCodeDTO = itemCodeMapper.toDto(itemCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemCodeMockMvc
            .perform(put("/api/item-codes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemCode in the database
        List<ItemCode> itemCodeList = itemCodeRepository.findAll();
        assertThat(itemCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemCode() throws Exception {
        // Initialize the database
        itemCodeRepository.saveAndFlush(itemCode);

        int databaseSizeBeforeDelete = itemCodeRepository.findAll().size();

        // Delete the itemCode
        restItemCodeMockMvc
            .perform(delete("/api/item-codes/{id}", itemCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemCode> itemCodeList = itemCodeRepository.findAll();
        assertThat(itemCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
