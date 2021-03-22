package com.activeport.web.service;

import com.activeport.web.service.dto.ServiceCodeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ServiceCode}.
 */
public interface ServiceCodeService {
    /**
     * Save a serviceCode.
     *
     * @param serviceCodeDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceCodeDTO save(ServiceCodeDTO serviceCodeDTO);

    /**
     * Get all the serviceCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServiceCodeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" serviceCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceCodeDTO> findOne(Long id);

    /**
     * Delete the "id" serviceCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
