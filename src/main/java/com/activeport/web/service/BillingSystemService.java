package com.activeport.web.service;

import com.activeport.web.service.dto.BillingSystemDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.BillingSystem}.
 */
public interface BillingSystemService {
    /**
     * Save a billingSystem.
     *
     * @param billingSystemDTO the entity to save.
     * @return the persisted entity.
     */
    BillingSystemDTO save(BillingSystemDTO billingSystemDTO);

    /**
     * Get all the billingSystems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillingSystemDTO> findAll(Pageable pageable);

    /**
     * Get the "id" billingSystem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillingSystemDTO> findOne(Long id);

    /**
     * Delete the "id" billingSystem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
