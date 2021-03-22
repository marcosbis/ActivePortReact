package com.activeport.web.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.activeport.web.ActivePortApp;
import com.activeport.web.domain.EntityManager;
import com.activeport.web.repository.EntityManagerRepository;
import com.activeport.web.service.EntityManagerService;
import com.activeport.web.service.dto.EntityManagerDTO;
import com.activeport.web.service.mapper.EntityManagerMapper;
import java.util.List;
import java.util.UUID;
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
 * Integration tests for the {@link EntityManagerResource} REST controller.
 */
@SpringBootTest(classes = ActivePortApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntityManagerResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final UUID DEFAULT_UID = UUID.randomUUID();
    private static final UUID UPDATED_UID = UUID.randomUUID();

    @Autowired
    private EntityManagerRepository entityManagerRepository;

    @Autowired
    private EntityManagerMapper entityManagerMapper;

    @Autowired
    private EntityManagerService entityManagerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityManagerMockMvc;

    private EntityManager entityManager;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityManager createEntity(EntityManager em) {
        EntityManager entityManager = new EntityManager().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).uid(DEFAULT_UID);
        return entityManager;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityManager createUpdatedEntity(EntityManager em) {
        EntityManager entityManager = new EntityManager().name(UPDATED_NAME).description(UPDATED_DESCRIPTION).uid(UPDATED_UID);
        return entityManager;
    }

    @BeforeEach
    public void initTest() {
        entityManager = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityManager() throws Exception {
        int databaseSizeBeforeCreate = entityManagerRepository.findAll().size();
        // Create the EntityManager
        EntityManagerDTO entityManagerDTO = entityManagerMapper.toDto(entityManager);
        restEntityManagerMockMvc
            .perform(
                post("/api/entity-managers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityManagerDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EntityManager in the database
        List<EntityManager> entityManagerList = entityManagerRepository.findAll();
        assertThat(entityManagerList).hasSize(databaseSizeBeforeCreate + 1);
        EntityManager testEntityManager = entityManagerList.get(entityManagerList.size() - 1);
        assertThat(testEntityManager.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEntityManager.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEntityManager.getUid()).isEqualTo(DEFAULT_UID);
    }

    @Test
    @Transactional
    public void createEntityManagerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityManagerRepository.findAll().size();

        // Create the EntityManager with an existing ID
        entityManager.setId(1L);
        EntityManagerDTO entityManagerDTO = entityManagerMapper.toDto(entityManager);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityManagerMockMvc
            .perform(
                post("/api/entity-managers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityManagerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityManager in the database
        List<EntityManager> entityManagerList = entityManagerRepository.findAll();
        assertThat(entityManagerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEntityManagers() throws Exception {
        // Initialize the database
        entityManagerRepository.saveAndFlush(entityManager);

        // Get all the entityManagerList
        restEntityManagerMockMvc
            .perform(get("/api/entity-managers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityManager.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID.toString())));
    }

    @Test
    @Transactional
    public void getEntityManager() throws Exception {
        // Initialize the database
        entityManagerRepository.saveAndFlush(entityManager);

        // Get the entityManager
        restEntityManagerMockMvc
            .perform(get("/api/entity-managers/{id}", entityManager.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityManager.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEntityManager() throws Exception {
        // Get the entityManager
        restEntityManagerMockMvc.perform(get("/api/entity-managers/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityManager() throws Exception {
        // Initialize the database
        entityManagerRepository.saveAndFlush(entityManager);

        int databaseSizeBeforeUpdate = entityManagerRepository.findAll().size();

        // Update the entityManager
        EntityManager updatedEntityManager = entityManagerRepository.findById(entityManager.getId()).get();
        // Disconnect from session so that the updates on updatedEntityManager are not directly saved in db
        em.detach(updatedEntityManager);
        updatedEntityManager.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).uid(UPDATED_UID);
        EntityManagerDTO entityManagerDTO = entityManagerMapper.toDto(updatedEntityManager);

        restEntityManagerMockMvc
            .perform(
                put("/api/entity-managers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityManagerDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityManager in the database
        List<EntityManager> entityManagerList = entityManagerRepository.findAll();
        assertThat(entityManagerList).hasSize(databaseSizeBeforeUpdate);
        EntityManager testEntityManager = entityManagerList.get(entityManagerList.size() - 1);
        assertThat(testEntityManager.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEntityManager.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEntityManager.getUid()).isEqualTo(UPDATED_UID);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityManager() throws Exception {
        int databaseSizeBeforeUpdate = entityManagerRepository.findAll().size();

        // Create the EntityManager
        EntityManagerDTO entityManagerDTO = entityManagerMapper.toDto(entityManager);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityManagerMockMvc
            .perform(
                put("/api/entity-managers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityManagerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityManager in the database
        List<EntityManager> entityManagerList = entityManagerRepository.findAll();
        assertThat(entityManagerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityManager() throws Exception {
        // Initialize the database
        entityManagerRepository.saveAndFlush(entityManager);

        int databaseSizeBeforeDelete = entityManagerRepository.findAll().size();

        // Delete the entityManager
        restEntityManagerMockMvc
            .perform(delete("/api/entity-managers/{id}", entityManager.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityManager> entityManagerList = entityManagerRepository.findAll();
        assertThat(entityManagerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
