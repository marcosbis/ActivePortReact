package com.activeport.web.web.rest;

import com.activeport.web.service.TileTenantConfigurationService;
import com.activeport.web.service.dto.TileTenantConfigurationDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.TileTenantConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class TileTenantConfigurationResource {
    private final Logger log = LoggerFactory.getLogger(TileTenantConfigurationResource.class);

    private static final String ENTITY_NAME = "tileTenantConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TileTenantConfigurationService tileTenantConfigurationService;

    public TileTenantConfigurationResource(TileTenantConfigurationService tileTenantConfigurationService) {
        this.tileTenantConfigurationService = tileTenantConfigurationService;
    }

    /**
     * {@code POST  /tile-tenant-configurations} : Create a new tileTenantConfiguration.
     *
     * @param tileTenantConfigurationDTO the tileTenantConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tileTenantConfigurationDTO, or with status {@code 400 (Bad Request)} if the tileTenantConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tile-tenant-configurations")
    public ResponseEntity<TileTenantConfigurationDTO> createTileTenantConfiguration(
        @RequestBody TileTenantConfigurationDTO tileTenantConfigurationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save TileTenantConfiguration : {}", tileTenantConfigurationDTO);
        if (tileTenantConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new tileTenantConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TileTenantConfigurationDTO result = tileTenantConfigurationService.save(tileTenantConfigurationDTO);
        return ResponseEntity
            .created(new URI("/api/tile-tenant-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tile-tenant-configurations} : Updates an existing tileTenantConfiguration.
     *
     * @param tileTenantConfigurationDTO the tileTenantConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tileTenantConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the tileTenantConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tileTenantConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tile-tenant-configurations")
    public ResponseEntity<TileTenantConfigurationDTO> updateTileTenantConfiguration(
        @RequestBody TileTenantConfigurationDTO tileTenantConfigurationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update TileTenantConfiguration : {}", tileTenantConfigurationDTO);
        if (tileTenantConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TileTenantConfigurationDTO result = tileTenantConfigurationService.save(tileTenantConfigurationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tileTenantConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tile-tenant-configurations} : get all the tileTenantConfigurations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tileTenantConfigurations in body.
     */
    @GetMapping("/tile-tenant-configurations")
    public ResponseEntity<List<TileTenantConfigurationDTO>> getAllTileTenantConfigurations(Pageable pageable) {
        log.debug("REST request to get a page of TileTenantConfigurations");
        Page<TileTenantConfigurationDTO> page = tileTenantConfigurationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tile-tenant-configurations/:id} : get the "id" tileTenantConfiguration.
     *
     * @param id the id of the tileTenantConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tileTenantConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tile-tenant-configurations/{id}")
    public ResponseEntity<TileTenantConfigurationDTO> getTileTenantConfiguration(@PathVariable Long id) {
        log.debug("REST request to get TileTenantConfiguration : {}", id);
        Optional<TileTenantConfigurationDTO> tileTenantConfigurationDTO = tileTenantConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tileTenantConfigurationDTO);
    }

    /**
     * {@code DELETE  /tile-tenant-configurations/:id} : delete the "id" tileTenantConfiguration.
     *
     * @param id the id of the tileTenantConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tile-tenant-configurations/{id}")
    public ResponseEntity<Void> deleteTileTenantConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete TileTenantConfiguration : {}", id);
        tileTenantConfigurationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
