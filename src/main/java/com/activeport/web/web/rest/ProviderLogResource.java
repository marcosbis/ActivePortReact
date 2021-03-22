package com.activeport.web.web.rest;

import com.activeport.web.service.ProviderLogService;
import com.activeport.web.service.dto.ProviderLogDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.ProviderLog}.
 */
@RestController
@RequestMapping("/api")
public class ProviderLogResource {
    private final Logger log = LoggerFactory.getLogger(ProviderLogResource.class);

    private static final String ENTITY_NAME = "providerLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProviderLogService providerLogService;

    public ProviderLogResource(ProviderLogService providerLogService) {
        this.providerLogService = providerLogService;
    }

    /**
     * {@code POST  /provider-logs} : Create a new providerLog.
     *
     * @param providerLogDTO the providerLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new providerLogDTO, or with status {@code 400 (Bad Request)} if the providerLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/provider-logs")
    public ResponseEntity<ProviderLogDTO> createProviderLog(@RequestBody ProviderLogDTO providerLogDTO) throws URISyntaxException {
        log.debug("REST request to save ProviderLog : {}", providerLogDTO);
        if (providerLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new providerLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProviderLogDTO result = providerLogService.save(providerLogDTO);
        return ResponseEntity
            .created(new URI("/api/provider-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /provider-logs} : Updates an existing providerLog.
     *
     * @param providerLogDTO the providerLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated providerLogDTO,
     * or with status {@code 400 (Bad Request)} if the providerLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the providerLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/provider-logs")
    public ResponseEntity<ProviderLogDTO> updateProviderLog(@RequestBody ProviderLogDTO providerLogDTO) throws URISyntaxException {
        log.debug("REST request to update ProviderLog : {}", providerLogDTO);
        if (providerLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProviderLogDTO result = providerLogService.save(providerLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, providerLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /provider-logs} : get all the providerLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of providerLogs in body.
     */
    @GetMapping("/provider-logs")
    public ResponseEntity<List<ProviderLogDTO>> getAllProviderLogs(Pageable pageable) {
        log.debug("REST request to get a page of ProviderLogs");
        Page<ProviderLogDTO> page = providerLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /provider-logs/:id} : get the "id" providerLog.
     *
     * @param id the id of the providerLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the providerLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/provider-logs/{id}")
    public ResponseEntity<ProviderLogDTO> getProviderLog(@PathVariable Long id) {
        log.debug("REST request to get ProviderLog : {}", id);
        Optional<ProviderLogDTO> providerLogDTO = providerLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(providerLogDTO);
    }

    /**
     * {@code DELETE  /provider-logs/:id} : delete the "id" providerLog.
     *
     * @param id the id of the providerLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/provider-logs/{id}")
    public ResponseEntity<Void> deleteProviderLog(@PathVariable Long id) {
        log.debug("REST request to delete ProviderLog : {}", id);
        providerLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
