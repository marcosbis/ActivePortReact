package com.activeport.web.web.rest;

import com.activeport.web.service.TileConfigurationService;
import com.activeport.web.service.dto.TileConfigurationDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.TileConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class TileConfigurationResource {
    private final Logger log = LoggerFactory.getLogger(TileConfigurationResource.class);

    private static final String ENTITY_NAME = "tileConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TileConfigurationService tileConfigurationService;

    public TileConfigurationResource(TileConfigurationService tileConfigurationService) {
        this.tileConfigurationService = tileConfigurationService;
    }

    /**
     * {@code POST  /tile-configurations} : Create a new tileConfiguration.
     *
     * @param tileConfigurationDTO the tileConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tileConfigurationDTO, or with status {@code 400 (Bad Request)} if the tileConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tile-configurations")
    public ResponseEntity<TileConfigurationDTO> createTileConfiguration(@RequestBody TileConfigurationDTO tileConfigurationDTO)
        throws URISyntaxException {
        log.debug("REST request to save TileConfiguration : {}", tileConfigurationDTO);
        if (tileConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new tileConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TileConfigurationDTO result = tileConfigurationService.save(tileConfigurationDTO);
        return ResponseEntity
            .created(new URI("/api/tile-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tile-configurations} : Updates an existing tileConfiguration.
     *
     * @param tileConfigurationDTO the tileConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tileConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the tileConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tileConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tile-configurations")
    public ResponseEntity<TileConfigurationDTO> updateTileConfiguration(@RequestBody TileConfigurationDTO tileConfigurationDTO)
        throws URISyntaxException {
        log.debug("REST request to update TileConfiguration : {}", tileConfigurationDTO);
        if (tileConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TileConfigurationDTO result = tileConfigurationService.save(tileConfigurationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tileConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tile-configurations} : get all the tileConfigurations.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tileConfigurations in body.
     */
    @GetMapping("/tile-configurations")
    public ResponseEntity<List<TileConfigurationDTO>> getAllTileConfigurations(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of TileConfigurations");
        Page<TileConfigurationDTO> page;
        if (eagerload) {
            page = tileConfigurationService.findAllWithEagerRelationships(pageable);
        } else {
            page = tileConfigurationService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tile-configurations/:id} : get the "id" tileConfiguration.
     *
     * @param id the id of the tileConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tileConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tile-configurations/{id}")
    public ResponseEntity<TileConfigurationDTO> getTileConfiguration(@PathVariable Long id) {
        log.debug("REST request to get TileConfiguration : {}", id);
        Optional<TileConfigurationDTO> tileConfigurationDTO = tileConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tileConfigurationDTO);
    }

    /**
     * {@code DELETE  /tile-configurations/:id} : delete the "id" tileConfiguration.
     *
     * @param id the id of the tileConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tile-configurations/{id}")
    public ResponseEntity<Void> deleteTileConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete TileConfiguration : {}", id);
        tileConfigurationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
