package com.activeport.web.service;

import com.activeport.web.service.dto.UserRoleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.UserRole}.
 */
public interface UserRoleService {
    /**
     * Save a userRole.
     *
     * @param userRoleDTO the entity to save.
     * @return the persisted entity.
     */
    UserRoleDTO save(UserRoleDTO userRoleDTO);

    /**
     * Get all the userRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserRoleDTO> findAll(Pageable pageable);

    /**
     * Get all the userRoles with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<UserRoleDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" userRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserRoleDTO> findOne(Long id);

    /**
     * Delete the "id" userRole.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
