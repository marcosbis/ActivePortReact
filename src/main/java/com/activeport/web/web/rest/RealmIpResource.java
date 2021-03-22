package com.activeport.web.web.rest;

import com.activeport.web.service.RealmIpService;
import com.activeport.web.service.dto.RealmIpDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.RealmIp}.
 */
@RestController
@RequestMapping("/api")
public class RealmIpResource {
    private final Logger log = LoggerFactory.getLogger(RealmIpResource.class);

    private static final String ENTITY_NAME = "realmIp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RealmIpService realmIpService;

    public RealmIpResource(RealmIpService realmIpService) {
        this.realmIpService = realmIpService;
    }

    /**
     * {@code POST  /realm-ips} : Create a new realmIp.
     *
     * @param realmIpDTO the realmIpDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new realmIpDTO, or with status {@code 400 (Bad Request)} if the realmIp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/realm-ips")
    public ResponseEntity<RealmIpDTO> createRealmIp(@RequestBody RealmIpDTO realmIpDTO) throws URISyntaxException {
        log.debug("REST request to save RealmIp : {}", realmIpDTO);
        if (realmIpDTO.getId() != null) {
            throw new BadRequestAlertException("A new realmIp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RealmIpDTO result = realmIpService.save(realmIpDTO);
        return ResponseEntity
            .created(new URI("/api/realm-ips/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /realm-ips} : Updates an existing realmIp.
     *
     * @param realmIpDTO the realmIpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated realmIpDTO,
     * or with status {@code 400 (Bad Request)} if the realmIpDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the realmIpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/realm-ips")
    public ResponseEntity<RealmIpDTO> updateRealmIp(@RequestBody RealmIpDTO realmIpDTO) throws URISyntaxException {
        log.debug("REST request to update RealmIp : {}", realmIpDTO);
        if (realmIpDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RealmIpDTO result = realmIpService.save(realmIpDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, realmIpDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /realm-ips} : get all the realmIps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of realmIps in body.
     */
    @GetMapping("/realm-ips")
    public ResponseEntity<List<RealmIpDTO>> getAllRealmIps(Pageable pageable) {
        log.debug("REST request to get a page of RealmIps");
        Page<RealmIpDTO> page = realmIpService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /realm-ips/:id} : get the "id" realmIp.
     *
     * @param id the id of the realmIpDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the realmIpDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/realm-ips/{id}")
    public ResponseEntity<RealmIpDTO> getRealmIp(@PathVariable Long id) {
        log.debug("REST request to get RealmIp : {}", id);
        Optional<RealmIpDTO> realmIpDTO = realmIpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(realmIpDTO);
    }

    /**
     * {@code DELETE  /realm-ips/:id} : delete the "id" realmIp.
     *
     * @param id the id of the realmIpDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/realm-ips/{id}")
    public ResponseEntity<Void> deleteRealmIp(@PathVariable Long id) {
        log.debug("REST request to delete RealmIp : {}", id);
        realmIpService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
