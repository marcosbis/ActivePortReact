package com.activeport.web.service;

import com.activeport.web.service.dto.ProviderPortDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ProviderPort}.
 */
public interface ProviderPortService {
    /**
     * Save a providerPort.
     *
     * @param providerPortDTO the entity to save.
     * @return the persisted entity.
     */
    ProviderPortDTO save(ProviderPortDTO providerPortDTO);

    /**
     * Get all the providerPorts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProviderPortDTO> findAll(Pageable pageable);

    /**
     * Get the "id" providerPort.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProviderPortDTO> findOne(Long id);

    /**
     * Delete the "id" providerPort.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
