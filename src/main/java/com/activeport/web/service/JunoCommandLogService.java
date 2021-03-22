package com.activeport.web.service;

import com.activeport.web.service.dto.JunoCommandLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.JunoCommandLog}.
 */
public interface JunoCommandLogService {
    /**
     * Save a junoCommandLog.
     *
     * @param junoCommandLogDTO the entity to save.
     * @return the persisted entity.
     */
    JunoCommandLogDTO save(JunoCommandLogDTO junoCommandLogDTO);

    /**
     * Get all the junoCommandLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JunoCommandLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" junoCommandLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JunoCommandLogDTO> findOne(Long id);

    /**
     * Delete the "id" junoCommandLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
