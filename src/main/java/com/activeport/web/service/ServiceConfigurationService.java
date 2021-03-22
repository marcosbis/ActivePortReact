package com.activeport.web.service;

import com.activeport.web.service.dto.ServiceConfigurationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ServiceConfiguration}.
 */
public interface ServiceConfigurationService {
    /**
     * Save a serviceConfiguration.
     *
     * @param serviceConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceConfigurationDTO save(ServiceConfigurationDTO serviceConfigurationDTO);

    /**
     * Get all the serviceConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServiceConfigurationDTO> findAll(Pageable pageable);

    /**
     * Get all the serviceConfigurations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ServiceConfigurationDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" serviceConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" serviceConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
