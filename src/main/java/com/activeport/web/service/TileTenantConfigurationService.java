package com.activeport.web.service;

import com.activeport.web.service.dto.TileTenantConfigurationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.TileTenantConfiguration}.
 */
public interface TileTenantConfigurationService {
    /**
     * Save a tileTenantConfiguration.
     *
     * @param tileTenantConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    TileTenantConfigurationDTO save(TileTenantConfigurationDTO tileTenantConfigurationDTO);

    /**
     * Get all the tileTenantConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TileTenantConfigurationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tileTenantConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TileTenantConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" tileTenantConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
