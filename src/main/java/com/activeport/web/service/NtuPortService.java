package com.activeport.web.service;

import com.activeport.web.service.dto.NtuPortDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.NtuPort}.
 */
public interface NtuPortService {
    /**
     * Save a ntuPort.
     *
     * @param ntuPortDTO the entity to save.
     * @return the persisted entity.
     */
    NtuPortDTO save(NtuPortDTO ntuPortDTO);

    /**
     * Get all the ntuPorts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NtuPortDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ntuPort.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NtuPortDTO> findOne(Long id);

    /**
     * Delete the "id" ntuPort.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
