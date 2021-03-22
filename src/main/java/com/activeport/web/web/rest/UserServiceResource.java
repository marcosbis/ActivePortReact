package com.activeport.web.web.rest;

import com.activeport.web.service.UserServiceService;
import com.activeport.web.service.dto.UserServiceDTO;
import com.activeport.web.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.activeport.web.domain.UserService}.
 */
@RestController
@RequestMapping("/api")
public class UserServiceResource {
    private final Logger log = LoggerFactory.getLogger(UserServiceResource.class);

    private static final String ENTITY_NAME = "userService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserServiceService userServiceService;

    public UserServiceResource(UserServiceService userServiceService) {
        this.userServiceService = userServiceService;
    }

    /**
     * {@code POST  /user-services} : Create a new userService.
     *
     * @param userServiceDTO the userServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userServiceDTO, or with status {@code 400 (Bad Request)} if the userService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-services")
    public ResponseEntity<UserServiceDTO> createUserService(@RequestBody UserServiceDTO userServiceDTO) throws URISyntaxException {
        log.debug("REST request to save UserService : {}", userServiceDTO);
        if (userServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new userService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserServiceDTO result = userServiceService.save(userServiceDTO);
        return ResponseEntity
            .created(new URI("/api/user-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-services} : Updates an existing userService.
     *
     * @param userServiceDTO the userServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userServiceDTO,
     * or with status {@code 400 (Bad Request)} if the userServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-services")
    public ResponseEntity<UserServiceDTO> updateUserService(@RequestBody UserServiceDTO userServiceDTO) throws URISyntaxException {
        log.debug("REST request to update UserService : {}", userServiceDTO);
        if (userServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserServiceDTO result = userServiceService.save(userServiceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-services} : get all the userServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userServices in body.
     */
    @GetMapping("/user-services")
    public ResponseEntity<List<UserServiceDTO>> getAllUserServices(Pageable pageable) {
        log.debug("REST request to get a page of UserServices");
        Page<UserServiceDTO> page = userServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-services/:id} : get the "id" userService.
     *
     * @param id the id of the userServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-services/{id}")
    public ResponseEntity<UserServiceDTO> getUserService(@PathVariable Long id) {
        log.debug("REST request to get UserService : {}", id);
        Optional<UserServiceDTO> userServiceDTO = userServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userServiceDTO);
    }

    /**
     * {@code DELETE  /user-services/:id} : delete the "id" userService.
     *
     * @param id the id of the userServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-services/{id}")
    public ResponseEntity<Void> deleteUserService(@PathVariable Long id) {
        log.debug("REST request to delete UserService : {}", id);
        userServiceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
