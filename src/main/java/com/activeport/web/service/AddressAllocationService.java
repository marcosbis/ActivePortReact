package com.activeport.web.service;

import com.activeport.web.service.dto.AddressAllocationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.AddressAllocation}.
 */
public interface AddressAllocationService {
    /**
     * Save a addressAllocation.
     *
     * @param addressAllocationDTO the entity to save.
     * @return the persisted entity.
     */
    AddressAllocationDTO save(AddressAllocationDTO addressAllocationDTO);

    /**
     * Get all the addressAllocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AddressAllocationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" addressAllocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AddressAllocationDTO> findOne(Long id);

    /**
     * Delete the "id" addressAllocation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
