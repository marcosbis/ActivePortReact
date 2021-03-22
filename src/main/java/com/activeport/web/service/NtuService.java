package com.activeport.web.service;

import com.activeport.web.service.dto.NtuDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.Ntu}.
 */
public interface NtuService {
    /**
     * Save a ntu.
     *
     * @param ntuDTO the entity to save.
     * @return the persisted entity.
     */
    NtuDTO save(NtuDTO ntuDTO);

    /**
     * Get all the ntus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NtuDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ntu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NtuDTO> findOne(Long id);

    /**
     * Delete the "id" ntu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
