package com.activeport.web.service;

import com.activeport.web.service.dto.VntuDownlinkPortDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.VntuDownlinkPort}.
 */
public interface VntuDownlinkPortService {
    /**
     * Save a vntuDownlinkPort.
     *
     * @param vntuDownlinkPortDTO the entity to save.
     * @return the persisted entity.
     */
    VntuDownlinkPortDTO save(VntuDownlinkPortDTO vntuDownlinkPortDTO);

    /**
     * Get all the vntuDownlinkPorts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VntuDownlinkPortDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vntuDownlinkPort.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VntuDownlinkPortDTO> findOne(Long id);

    /**
     * Delete the "id" vntuDownlinkPort.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
