package com.activeport.web.service;

import com.activeport.web.service.dto.XeroAccountDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.XeroAccount}.
 */
public interface XeroAccountService {
    /**
     * Save a xeroAccount.
     *
     * @param xeroAccountDTO the entity to save.
     * @return the persisted entity.
     */
    XeroAccountDTO save(XeroAccountDTO xeroAccountDTO);

    /**
     * Get all the xeroAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<XeroAccountDTO> findAll(Pageable pageable);

    /**
     * Get the "id" xeroAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<XeroAccountDTO> findOne(Long id);

    /**
     * Delete the "id" xeroAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
