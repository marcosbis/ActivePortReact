package com.activeport.web.web.rest;

import com.activeport.web.service.EntityManagerService;
import com.activeport.web.service.dto.EntityManagerDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.EntityManager}.
 */
@RestController
@RequestMapping("/api")
public class EntityManagerResource {
    private final Logger log = LoggerFactory.getLogger(EntityManagerResource.class);

    private static final String ENTITY_NAME = "entityManager";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityManagerService entityManagerService;

    public EntityManagerResource(EntityManagerService entityManagerService) {
        this.entityManagerService = entityManagerService;
    }

    /**
     * {@code POST  /entity-managers} : Create a new entityManager.
     *
     * @param entityManagerDTO the entityManagerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityManagerDTO, or with status {@code 400 (Bad Request)} if the entityManager has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-managers")
    public ResponseEntity<EntityManagerDTO> createEntityManager(@RequestBody EntityManagerDTO entityManagerDTO) throws URISyntaxException {
        log.debug("REST request to save EntityManager : {}", entityManagerDTO);
        if (entityManagerDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityManager cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityManagerDTO result = entityManagerService.save(entityManagerDTO);
        return ResponseEntity
            .created(new URI("/api/entity-managers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-managers} : Updates an existing entityManager.
     *
     * @param entityManagerDTO the entityManagerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityManagerDTO,
     * or with status {@code 400 (Bad Request)} if the entityManagerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityManagerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-managers")
    public ResponseEntity<EntityManagerDTO> updateEntityManager(@RequestBody EntityManagerDTO entityManagerDTO) throws URISyntaxException {
        log.debug("REST request to update EntityManager : {}", entityManagerDTO);
        if (entityManagerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityManagerDTO result = entityManagerService.save(entityManagerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityManagerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entity-managers} : get all the entityManagers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityManagers in body.
     */
    @GetMapping("/entity-managers")
    public ResponseEntity<List<EntityManagerDTO>> getAllEntityManagers(Pageable pageable) {
        log.debug("REST request to get a page of EntityManagers");
        Page<EntityManagerDTO> page = entityManagerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /entity-managers/:id} : get the "id" entityManager.
     *
     * @param id the id of the entityManagerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityManagerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-managers/{id}")
    public ResponseEntity<EntityManagerDTO> getEntityManager(@PathVariable Long id) {
        log.debug("REST request to get EntityManager : {}", id);
        Optional<EntityManagerDTO> entityManagerDTO = entityManagerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityManagerDTO);
    }

    /**
     * {@code DELETE  /entity-managers/:id} : delete the "id" entityManager.
     *
     * @param id the id of the entityManagerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-managers/{id}")
    public ResponseEntity<Void> deleteEntityManager(@PathVariable Long id) {
        log.debug("REST request to delete EntityManager : {}", id);
        entityManagerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
