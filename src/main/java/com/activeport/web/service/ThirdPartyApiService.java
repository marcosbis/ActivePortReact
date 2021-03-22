package com.activeport.web.service;

import com.activeport.web.service.dto.ThirdPartyApiDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ThirdPartyApi}.
 */
public interface ThirdPartyApiService {
    /**
     * Save a thirdPartyApi.
     *
     * @param thirdPartyApiDTO the entity to save.
     * @return the persisted entity.
     */
    ThirdPartyApiDTO save(ThirdPartyApiDTO thirdPartyApiDTO);

    /**
     * Get all the thirdPartyApis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ThirdPartyApiDTO> findAll(Pageable pageable);

    /**
     * Get the "id" thirdPartyApi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThirdPartyApiDTO> findOne(Long id);

    /**
     * Delete the "id" thirdPartyApi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
