package com.activeport.web.service;

import com.activeport.web.service.dto.ItemCodeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.ItemCode}.
 */
public interface ItemCodeService {
    /**
     * Save a itemCode.
     *
     * @param itemCodeDTO the entity to save.
     * @return the persisted entity.
     */
    ItemCodeDTO save(ItemCodeDTO itemCodeDTO);

    /**
     * Get all the itemCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemCodeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemCodeDTO> findOne(Long id);

    /**
     * Delete the "id" itemCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
