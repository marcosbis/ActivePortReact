package com.activeport.web.service;

import com.activeport.web.service.dto.ProviderConfigurationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ProviderConfiguration}.
 */
public interface ProviderConfigurationService {
    /**
     * Save a providerConfiguration.
     *
     * @param providerConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    ProviderConfigurationDTO save(ProviderConfigurationDTO providerConfigurationDTO);

    /**
     * Get all the providerConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProviderConfigurationDTO> findAll(Pageable pageable);

    /**
     * Get all the providerConfigurations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ProviderConfigurationDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" providerConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProviderConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" providerConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
