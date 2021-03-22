package com.activeport.web.web.rest;

import com.activeport.web.service.PolicerScheduleService;
import com.activeport.web.service.dto.PolicerScheduleDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.PolicerSchedule}.
 */
@RestController
@RequestMapping("/api")
public class PolicerScheduleResource {
    private final Logger log = LoggerFactory.getLogger(PolicerScheduleResource.class);

    private static final String ENTITY_NAME = "policerSchedule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PolicerScheduleService policerScheduleService;

    public PolicerScheduleResource(PolicerScheduleService policerScheduleService) {
        this.policerScheduleService = policerScheduleService;
    }

    /**
     * {@code POST  /policer-schedules} : Create a new policerSchedule.
     *
     * @param policerScheduleDTO the policerScheduleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new policerScheduleDTO, or with status {@code 400 (Bad Request)} if the policerSchedule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/policer-schedules")
    public ResponseEntity<PolicerScheduleDTO> createPolicerSchedule(@RequestBody PolicerScheduleDTO policerScheduleDTO)
        throws URISyntaxException {
        log.debug("REST request to save PolicerSchedule : {}", policerScheduleDTO);
        if (policerScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new policerSchedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PolicerScheduleDTO result = policerScheduleService.save(policerScheduleDTO);
        return ResponseEntity
            .created(new URI("/api/policer-schedules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /policer-schedules} : Updates an existing policerSchedule.
     *
     * @param policerScheduleDTO the policerScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated policerScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the policerScheduleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the policerScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/policer-schedules")
    public ResponseEntity<PolicerScheduleDTO> updatePolicerSchedule(@RequestBody PolicerScheduleDTO policerScheduleDTO)
        throws URISyntaxException {
        log.debug("REST request to update PolicerSchedule : {}", policerScheduleDTO);
        if (policerScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PolicerScheduleDTO result = policerScheduleService.save(policerScheduleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, policerScheduleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /policer-schedules} : get all the policerSchedules.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of policerSchedules in body.
     */
    @GetMapping("/policer-schedules")
    public ResponseEntity<List<PolicerScheduleDTO>> getAllPolicerSchedules(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of PolicerSchedules");
        Page<PolicerScheduleDTO> page;
        if (eagerload) {
            page = policerScheduleService.findAllWithEagerRelationships(pageable);
        } else {
            page = policerScheduleService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /policer-schedules/:id} : get the "id" policerSchedule.
     *
     * @param id the id of the policerScheduleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the policerScheduleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/policer-schedules/{id}")
    public ResponseEntity<PolicerScheduleDTO> getPolicerSchedule(@PathVariable Long id) {
        log.debug("REST request to get PolicerSchedule : {}", id);
        Optional<PolicerScheduleDTO> policerScheduleDTO = policerScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(policerScheduleDTO);
    }

    /**
     * {@code DELETE  /policer-schedules/:id} : delete the "id" policerSchedule.
     *
     * @param id the id of the policerScheduleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/policer-schedules/{id}")
    public ResponseEntity<Void> deletePolicerSchedule(@PathVariable Long id) {
        log.debug("REST request to delete PolicerSchedule : {}", id);
        policerScheduleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
