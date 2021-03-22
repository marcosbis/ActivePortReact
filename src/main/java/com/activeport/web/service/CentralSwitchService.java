package com.activeport.web.service;

import com.activeport.web.service.dto.CentralSwitchDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.CentralSwitch}.
 */
public interface CentralSwitchService {
    /**
     * Save a centralSwitch.
     *
     * @param centralSwitchDTO the entity to save.
     * @return the persisted entity.
     */
    CentralSwitchDTO save(CentralSwitchDTO centralSwitchDTO);

    /**
     * Get all the centralSwitches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CentralSwitchDTO> findAll(Pageable pageable);

    /**
     * Get the "id" centralSwitch.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CentralSwitchDTO> findOne(Long id);

    /**
     * Delete the "id" centralSwitch.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
