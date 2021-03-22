package com.activeport.web.service;

import com.activeport.web.service.dto.ServiceTypeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ServiceType}.
 */
public interface ServiceTypeService {
    /**
     * Save a serviceType.
     *
     * @param serviceTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceTypeDTO save(ServiceTypeDTO serviceTypeDTO);

    /**
     * Get all the serviceTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServiceTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" serviceType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceTypeDTO> findOne(Long id);

    /**
     * Delete the "id" serviceType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
