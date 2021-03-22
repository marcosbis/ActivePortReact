package com.activeport.web.service;

import com.activeport.web.service.dto.PolicerRangeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.PolicerRange}.
 */
public interface PolicerRangeService {
    /**
     * Save a policerRange.
     *
     * @param policerRangeDTO the entity to save.
     * @return the persisted entity.
     */
    PolicerRangeDTO save(PolicerRangeDTO policerRangeDTO);

    /**
     * Get all the policerRanges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PolicerRangeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" policerRange.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PolicerRangeDTO> findOne(Long id);

    /**
     * Delete the "id" policerRange.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
