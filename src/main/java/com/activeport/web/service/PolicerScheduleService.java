package com.activeport.web.service;

import com.activeport.web.service.dto.PolicerScheduleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.PolicerSchedule}.
 */
public interface PolicerScheduleService {
    /**
     * Save a policerSchedule.
     *
     * @param policerScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    PolicerScheduleDTO save(PolicerScheduleDTO policerScheduleDTO);

    /**
     * Get all the policerSchedules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PolicerScheduleDTO> findAll(Pageable pageable);

    /**
     * Get all the policerSchedules with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<PolicerScheduleDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" policerSchedule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PolicerScheduleDTO> findOne(Long id);

    /**
     * Delete the "id" policerSchedule.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
