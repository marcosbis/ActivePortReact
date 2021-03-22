package com.activeport.web.service;

import com.activeport.web.service.dto.CircuitVlanDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.CircuitVlan}.
 */
public interface CircuitVlanService {
    /**
     * Save a circuitVlan.
     *
     * @param circuitVlanDTO the entity to save.
     * @return the persisted entity.
     */
    CircuitVlanDTO save(CircuitVlanDTO circuitVlanDTO);

    /**
     * Get all the circuitVlans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CircuitVlanDTO> findAll(Pageable pageable);

    /**
     * Get the "id" circuitVlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CircuitVlanDTO> findOne(Long id);

    /**
     * Delete the "id" circuitVlan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
