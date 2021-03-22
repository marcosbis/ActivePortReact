package com.activeport.web.service;

import com.activeport.web.service.dto.TileConfigurationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.TileConfiguration}.
 */
public interface TileConfigurationService {
    /**
     * Save a tileConfiguration.
     *
     * @param tileConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    TileConfigurationDTO save(TileConfigurationDTO tileConfigurationDTO);

    /**
     * Get all the tileConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TileConfigurationDTO> findAll(Pageable pageable);

    /**
     * Get all the tileConfigurations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<TileConfigurationDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" tileConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TileConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" tileConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
