package com.activeport.web.web.rest;

import com.activeport.web.service.RateChangeLogService;
import com.activeport.web.service.dto.RateChangeLogDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.RateChangeLog}.
 */
@RestController
@RequestMapping("/api")
public class RateChangeLogResource {
    private final Logger log = LoggerFactory.getLogger(RateChangeLogResource.class);

    private static final String ENTITY_NAME = "rateChangeLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RateChangeLogService rateChangeLogService;

    public RateChangeLogResource(RateChangeLogService rateChangeLogService) {
        this.rateChangeLogService = rateChangeLogService;
    }

    /**
     * {@code POST  /rate-change-logs} : Create a new rateChangeLog.
     *
     * @param rateChangeLogDTO the rateChangeLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rateChangeLogDTO, or with status {@code 400 (Bad Request)} if the rateChangeLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rate-change-logs")
    public ResponseEntity<RateChangeLogDTO> createRateChangeLog(@RequestBody RateChangeLogDTO rateChangeLogDTO) throws URISyntaxException {
        log.debug("REST request to save RateChangeLog : {}", rateChangeLogDTO);
        if (rateChangeLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new rateChangeLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RateChangeLogDTO result = rateChangeLogService.save(rateChangeLogDTO);
        return ResponseEntity
            .created(new URI("/api/rate-change-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rate-change-logs} : Updates an existing rateChangeLog.
     *
     * @param rateChangeLogDTO the rateChangeLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rateChangeLogDTO,
     * or with status {@code 400 (Bad Request)} if the rateChangeLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rateChangeLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rate-change-logs")
    public ResponseEntity<RateChangeLogDTO> updateRateChangeLog(@RequestBody RateChangeLogDTO rateChangeLogDTO) throws URISyntaxException {
        log.debug("REST request to update RateChangeLog : {}", rateChangeLogDTO);
        if (rateChangeLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RateChangeLogDTO result = rateChangeLogService.save(rateChangeLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rateChangeLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rate-change-logs} : get all the rateChangeLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rateChangeLogs in body.
     */
    @GetMapping("/rate-change-logs")
    public ResponseEntity<List<RateChangeLogDTO>> getAllRateChangeLogs(Pageable pageable) {
        log.debug("REST request to get a page of RateChangeLogs");
        Page<RateChangeLogDTO> page = rateChangeLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rate-change-logs/:id} : get the "id" rateChangeLog.
     *
     * @param id the id of the rateChangeLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rateChangeLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rate-change-logs/{id}")
    public ResponseEntity<RateChangeLogDTO> getRateChangeLog(@PathVariable Long id) {
        log.debug("REST request to get RateChangeLog : {}", id);
        Optional<RateChangeLogDTO> rateChangeLogDTO = rateChangeLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rateChangeLogDTO);
    }

    /**
     * {@code DELETE  /rate-change-logs/:id} : delete the "id" rateChangeLog.
     *
     * @param id the id of the rateChangeLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rate-change-logs/{id}")
    public ResponseEntity<Void> deleteRateChangeLog(@PathVariable Long id) {
        log.debug("REST request to delete RateChangeLog : {}", id);
        rateChangeLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
