package com.activeport.web.service;

import com.activeport.web.service.dto.EntityManagerDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.EntityManager}.
 */
public interface EntityManagerService {
    /**
     * Save a entityManager.
     *
     * @param entityManagerDTO the entity to save.
     * @return the persisted entity.
     */
    EntityManagerDTO save(EntityManagerDTO entityManagerDTO);

    /**
     * Get all the entityManagers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EntityManagerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" entityManager.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityManagerDTO> findOne(Long id);

    /**
     * Delete the "id" entityManager.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
