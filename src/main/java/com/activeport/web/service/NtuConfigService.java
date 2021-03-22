package com.activeport.web.service;

import com.activeport.web.service.dto.NtuConfigDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.NtuConfig}.
 */
public interface NtuConfigService {
    /**
     * Save a ntuConfig.
     *
     * @param ntuConfigDTO the entity to save.
     * @return the persisted entity.
     */
    NtuConfigDTO save(NtuConfigDTO ntuConfigDTO);

    /**
     * Get all the ntuConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NtuConfigDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ntuConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NtuConfigDTO> findOne(Long id);

    /**
     * Delete the "id" ntuConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
