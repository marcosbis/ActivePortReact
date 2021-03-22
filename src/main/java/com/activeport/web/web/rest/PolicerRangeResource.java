package com.activeport.web.web.rest;

import com.activeport.web.service.PolicerRangeService;
import com.activeport.web.service.dto.PolicerRangeDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.PolicerRange}.
 */
@RestController
@RequestMapping("/api")
public class PolicerRangeResource {
    private final Logger log = LoggerFactory.getLogger(PolicerRangeResource.class);

    private static final String ENTITY_NAME = "policerRange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PolicerRangeService policerRangeService;

    public PolicerRangeResource(PolicerRangeService policerRangeService) {
        this.policerRangeService = policerRangeService;
    }

    /**
     * {@code POST  /policer-ranges} : Create a new policerRange.
     *
     * @param policerRangeDTO the policerRangeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new policerRangeDTO, or with status {@code 400 (Bad Request)} if the policerRange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/policer-ranges")
    public ResponseEntity<PolicerRangeDTO> createPolicerRange(@RequestBody PolicerRangeDTO policerRangeDTO) throws URISyntaxException {
        log.debug("REST request to save PolicerRange : {}", policerRangeDTO);
        if (policerRangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new policerRange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PolicerRangeDTO result = policerRangeService.save(policerRangeDTO);
        return ResponseEntity
            .created(new URI("/api/policer-ranges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /policer-ranges} : Updates an existing policerRange.
     *
     * @param policerRangeDTO the policerRangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated policerRangeDTO,
     * or with status {@code 400 (Bad Request)} if the policerRangeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the policerRangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/policer-ranges")
    public ResponseEntity<PolicerRangeDTO> updatePolicerRange(@RequestBody PolicerRangeDTO policerRangeDTO) throws URISyntaxException {
        log.debug("REST request to update PolicerRange : {}", policerRangeDTO);
        if (policerRangeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PolicerRangeDTO result = policerRangeService.save(policerRangeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, policerRangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /policer-ranges} : get all the policerRanges.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of policerRanges in body.
     */
    @GetMapping("/policer-ranges")
    public ResponseEntity<List<PolicerRangeDTO>> getAllPolicerRanges(Pageable pageable) {
        log.debug("REST request to get a page of PolicerRanges");
        Page<PolicerRangeDTO> page = policerRangeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /policer-ranges/:id} : get the "id" policerRange.
     *
     * @param id the id of the policerRangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the policerRangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/policer-ranges/{id}")
    public ResponseEntity<PolicerRangeDTO> getPolicerRange(@PathVariable Long id) {
        log.debug("REST request to get PolicerRange : {}", id);
        Optional<PolicerRangeDTO> policerRangeDTO = policerRangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(policerRangeDTO);
    }

    /**
     * {@code DELETE  /policer-ranges/:id} : delete the "id" policerRange.
     *
     * @param id the id of the policerRangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/policer-ranges/{id}")
    public ResponseEntity<Void> deletePolicerRange(@PathVariable Long id) {
        log.debug("REST request to delete PolicerRange : {}", id);
        policerRangeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
