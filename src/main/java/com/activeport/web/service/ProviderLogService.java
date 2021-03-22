package com.activeport.web.service;

import com.activeport.web.service.dto.ProviderLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ProviderLog}.
 */
public interface ProviderLogService {
    /**
     * Save a providerLog.
     *
     * @param providerLogDTO the entity to save.
     * @return the persisted entity.
     */
    ProviderLogDTO save(ProviderLogDTO providerLogDTO);

    /**
     * Get all the providerLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProviderLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" providerLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProviderLogDTO> findOne(Long id);

    /**
     * Delete the "id" providerLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
