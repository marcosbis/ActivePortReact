package com.activeport.web.service;

import com.activeport.web.service.dto.RealmIpDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.RealmIp}.
 */
public interface RealmIpService {
    /**
     * Save a realmIp.
     *
     * @param realmIpDTO the entity to save.
     * @return the persisted entity.
     */
    RealmIpDTO save(RealmIpDTO realmIpDTO);

    /**
     * Get all the realmIps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RealmIpDTO> findAll(Pageable pageable);

    /**
     * Get the "id" realmIp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RealmIpDTO> findOne(Long id);

    /**
     * Delete the "id" realmIp.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
