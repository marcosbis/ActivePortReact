package com.activeport.web.service;

import com.activeport.web.service.dto.NtuTypeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.NtuType}.
 */
public interface NtuTypeService {
    /**
     * Save a ntuType.
     *
     * @param ntuTypeDTO the entity to save.
     * @return the persisted entity.
     */
    NtuTypeDTO save(NtuTypeDTO ntuTypeDTO);

    /**
     * Get all the ntuTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NtuTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ntuType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NtuTypeDTO> findOne(Long id);

    /**
     * Delete the "id" ntuType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
