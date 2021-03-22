package com.activeport.web.service;

import com.activeport.web.service.dto.DeviceConfigurationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.DeviceConfiguration}.
 */
public interface DeviceConfigurationService {
    /**
     * Save a deviceConfiguration.
     *
     * @param deviceConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    DeviceConfigurationDTO save(DeviceConfigurationDTO deviceConfigurationDTO);

    /**
     * Get all the deviceConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeviceConfigurationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deviceConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeviceConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" deviceConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
