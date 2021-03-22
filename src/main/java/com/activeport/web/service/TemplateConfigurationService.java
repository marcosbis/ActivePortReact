package com.activeport.web.service;

import com.activeport.web.service.dto.TemplateConfigurationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.TemplateConfiguration}.
 */
public interface TemplateConfigurationService {
    /**
     * Save a templateConfiguration.
     *
     * @param templateConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    TemplateConfigurationDTO save(TemplateConfigurationDTO templateConfigurationDTO);

    /**
     * Get all the templateConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TemplateConfigurationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" templateConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TemplateConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" templateConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
