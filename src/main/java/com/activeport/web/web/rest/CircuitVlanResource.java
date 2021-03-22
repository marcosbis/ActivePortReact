package com.activeport.web.web.rest;

import com.activeport.web.service.CircuitVlanService;
import com.activeport.web.service.dto.CircuitVlanDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.CircuitVlan}.
 */
@RestController
@RequestMapping("/api")
public class CircuitVlanResource {
    private final Logger log = LoggerFactory.getLogger(CircuitVlanResource.class);

    private static final String ENTITY_NAME = "circuitVlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CircuitVlanService circuitVlanService;

    public CircuitVlanResource(CircuitVlanService circuitVlanService) {
        this.circuitVlanService = circuitVlanService;
    }

    /**
     * {@code POST  /circuit-vlans} : Create a new circuitVlan.
     *
     * @param circuitVlanDTO the circuitVlanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new circuitVlanDTO, or with status {@code 400 (Bad Request)} if the circuitVlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/circuit-vlans")
    public ResponseEntity<CircuitVlanDTO> createCircuitVlan(@RequestBody CircuitVlanDTO circuitVlanDTO) throws URISyntaxException {
        log.debug("REST request to save CircuitVlan : {}", circuitVlanDTO);
        if (circuitVlanDTO.getId() != null) {
            throw new BadRequestAlertException("A new circuitVlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CircuitVlanDTO result = circuitVlanService.save(circuitVlanDTO);
        return ResponseEntity
            .created(new URI("/api/circuit-vlans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /circuit-vlans} : Updates an existing circuitVlan.
     *
     * @param circuitVlanDTO the circuitVlanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated circuitVlanDTO,
     * or with status {@code 400 (Bad Request)} if the circuitVlanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the circuitVlanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/circuit-vlans")
    public ResponseEntity<CircuitVlanDTO> updateCircuitVlan(@RequestBody CircuitVlanDTO circuitVlanDTO) throws URISyntaxException {
        log.debug("REST request to update CircuitVlan : {}", circuitVlanDTO);
        if (circuitVlanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CircuitVlanDTO result = circuitVlanService.save(circuitVlanDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, circuitVlanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /circuit-vlans} : get all the circuitVlans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of circuitVlans in body.
     */
    @GetMapping("/circuit-vlans")
    public ResponseEntity<List<CircuitVlanDTO>> getAllCircuitVlans(Pageable pageable) {
        log.debug("REST request to get a page of CircuitVlans");
        Page<CircuitVlanDTO> page = circuitVlanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /circuit-vlans/:id} : get the "id" circuitVlan.
     *
     * @param id the id of the circuitVlanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the circuitVlanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/circuit-vlans/{id}")
    public ResponseEntity<CircuitVlanDTO> getCircuitVlan(@PathVariable Long id) {
        log.debug("REST request to get CircuitVlan : {}", id);
        Optional<CircuitVlanDTO> circuitVlanDTO = circuitVlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(circuitVlanDTO);
    }

    /**
     * {@code DELETE  /circuit-vlans/:id} : delete the "id" circuitVlan.
     *
     * @param id the id of the circuitVlanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/circuit-vlans/{id}")
    public ResponseEntity<Void> deleteCircuitVlan(@PathVariable Long id) {
        log.debug("REST request to delete CircuitVlan : {}", id);
        circuitVlanService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
