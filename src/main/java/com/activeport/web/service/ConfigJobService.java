package com.activeport.web.service;

import com.activeport.web.service.dto.ConfigJobDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ConfigJob}.
 */
public interface ConfigJobService {
    /**
     * Save a configJob.
     *
     * @param configJobDTO the entity to save.
     * @return the persisted entity.
     */
    ConfigJobDTO save(ConfigJobDTO configJobDTO);

    /**
     * Get all the configJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ConfigJobDTO> findAll(Pageable pageable);

    /**
     * Get the "id" configJob.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConfigJobDTO> findOne(Long id);

    /**
     * Delete the "id" configJob.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
