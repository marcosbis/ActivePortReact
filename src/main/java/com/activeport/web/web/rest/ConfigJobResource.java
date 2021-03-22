package com.activeport.web.web.rest;

import com.activeport.web.service.ConfigJobService;
import com.activeport.web.service.dto.ConfigJobDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.ConfigJob}.
 */
@RestController
@RequestMapping("/api")
public class ConfigJobResource {
    private final Logger log = LoggerFactory.getLogger(ConfigJobResource.class);

    private static final String ENTITY_NAME = "configJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConfigJobService configJobService;

    public ConfigJobResource(ConfigJobService configJobService) {
        this.configJobService = configJobService;
    }

    /**
     * {@code POST  /config-jobs} : Create a new configJob.
     *
     * @param configJobDTO the configJobDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new configJobDTO, or with status {@code 400 (Bad Request)} if the configJob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/config-jobs")
    public ResponseEntity<ConfigJobDTO> createConfigJob(@RequestBody ConfigJobDTO configJobDTO) throws URISyntaxException {
        log.debug("REST request to save ConfigJob : {}", configJobDTO);
        if (configJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new configJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfigJobDTO result = configJobService.save(configJobDTO);
        return ResponseEntity
            .created(new URI("/api/config-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /config-jobs} : Updates an existing configJob.
     *
     * @param configJobDTO the configJobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated configJobDTO,
     * or with status {@code 400 (Bad Request)} if the configJobDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the configJobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/config-jobs")
    public ResponseEntity<ConfigJobDTO> updateConfigJob(@RequestBody ConfigJobDTO configJobDTO) throws URISyntaxException {
        log.debug("REST request to update ConfigJob : {}", configJobDTO);
        if (configJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConfigJobDTO result = configJobService.save(configJobDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, configJobDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /config-jobs} : get all the configJobs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of configJobs in body.
     */
    @GetMapping("/config-jobs")
    public ResponseEntity<List<ConfigJobDTO>> getAllConfigJobs(Pageable pageable) {
        log.debug("REST request to get a page of ConfigJobs");
        Page<ConfigJobDTO> page = configJobService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /config-jobs/:id} : get the "id" configJob.
     *
     * @param id the id of the configJobDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the configJobDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/config-jobs/{id}")
    public ResponseEntity<ConfigJobDTO> getConfigJob(@PathVariable Long id) {
        log.debug("REST request to get ConfigJob : {}", id);
        Optional<ConfigJobDTO> configJobDTO = configJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(configJobDTO);
    }

    /**
     * {@code DELETE  /config-jobs/:id} : delete the "id" configJob.
     *
     * @param id the id of the configJobDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/config-jobs/{id}")
    public ResponseEntity<Void> deleteConfigJob(@PathVariable Long id) {
        log.debug("REST request to delete ConfigJob : {}", id);
        configJobService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
