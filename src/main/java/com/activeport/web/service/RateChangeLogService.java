package com.activeport.web.service;

import com.activeport.web.service.dto.RateChangeLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.RateChangeLog}.
 */
public interface RateChangeLogService {
    /**
     * Save a rateChangeLog.
     *
     * @param rateChangeLogDTO the entity to save.
     * @return the persisted entity.
     */
    RateChangeLogDTO save(RateChangeLogDTO rateChangeLogDTO);

    /**
     * Get all the rateChangeLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RateChangeLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" rateChangeLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RateChangeLogDTO> findOne(Long id);

    /**
     * Delete the "id" rateChangeLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
