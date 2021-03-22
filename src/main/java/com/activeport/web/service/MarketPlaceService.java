package com.activeport.web.service;

import com.activeport.web.service.dto.MarketPlaceDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.MarketPlace}.
 */
public interface MarketPlaceService {
    /**
     * Save a marketPlace.
     *
     * @param marketPlaceDTO the entity to save.
     * @return the persisted entity.
     */
    MarketPlaceDTO save(MarketPlaceDTO marketPlaceDTO);

    /**
     * Get all the marketPlaces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MarketPlaceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" marketPlace.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MarketPlaceDTO> findOne(Long id);

    /**
     * Delete the "id" marketPlace.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
