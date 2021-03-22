package com.activeport.web.service;

import com.activeport.web.service.dto.ServiceCommandDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ServiceCommand}.
 */
public interface ServiceCommandService {
    /**
     * Save a serviceCommand.
     *
     * @param serviceCommandDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceCommandDTO save(ServiceCommandDTO serviceCommandDTO);

    /**
     * Get all the serviceCommands.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServiceCommandDTO> findAll(Pageable pageable);

    /**
     * Get the "id" serviceCommand.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceCommandDTO> findOne(Long id);

    /**
     * Delete the "id" serviceCommand.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
