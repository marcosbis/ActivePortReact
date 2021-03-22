package com.activeport.web.service;

import com.activeport.web.service.dto.UserServiceDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.activeport.web.domain.UserService}.
 */
public interface UserServiceService {
    /**
     * Save a userService.
     *
     * @param userServiceDTO the entity to save.
     * @return the persisted entity.
     */
    UserServiceDTO save(UserServiceDTO userServiceDTO);

    /**
     * Get all the userServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserServiceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserServiceDTO> findOne(Long id);

    /**
     * Delete the "id" userService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
