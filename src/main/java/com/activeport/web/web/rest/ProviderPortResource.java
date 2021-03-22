package com.activeport.web.web.rest;

import com.activeport.web.service.ProviderPortService;
import com.activeport.web.service.dto.ProviderPortDTO;
import com.activeport.web.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
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
 * REST controller for managing {@link com.activeport.web.domain.ProviderPort}.
 */
@RestController
@RequestMapping("/api")
public class ProviderPortResource {
    private final Logger log = LoggerFactory.getLogger(ProviderPortResource.class);

    private static final String ENTITY_NAME = "providerPort";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProviderPortService providerPortService;

    public ProviderPortResource(ProviderPortService providerPortService) {
        this.providerPortService = providerPortService;
    }

    /**
     * {@code POST  /provider-ports} : Create a new providerPort.
     *
     * @param providerPortDTO the providerPortDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new providerPortDTO, or with status {@code 400 (Bad Request)} if the providerPort has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/provider-ports")
    public ResponseEntity<ProviderPortDTO> createProviderPort(@Valid @RequestBody ProviderPortDTO providerPortDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProviderPort : {}", providerPortDTO);
        if (providerPortDTO.getId() != null) {
            throw new BadRequestAlertException("A new providerPort cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProviderPortDTO result = providerPortService.save(providerPortDTO);
        return ResponseEntity
            .created(new URI("/api/provider-ports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /provider-ports} : Updates an existing providerPort.
     *
     * @param providerPortDTO the providerPortDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated providerPortDTO,
     * or with status {@code 400 (Bad Request)} if the providerPortDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the providerPortDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/provider-ports")
    public ResponseEntity<ProviderPortDTO> updateProviderPort(@Valid @RequestBody ProviderPortDTO providerPortDTO)
        throws URISyntaxException {
        log.debug("REST request to update ProviderPort : {}", providerPortDTO);
        if (providerPortDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProviderPortDTO result = providerPortService.save(providerPortDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, providerPortDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /provider-ports} : get all the providerPorts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of providerPorts in body.
     */
    @GetMapping("/provider-ports")
    public ResponseEntity<List<ProviderPortDTO>> getAllProviderPorts(Pageable pageable) {
        log.debug("REST request to get a page of ProviderPorts");
        Page<ProviderPortDTO> page = providerPortService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /provider-ports/:id} : get the "id" providerPort.
     *
     * @param id the id of the providerPortDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the providerPortDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/provider-ports/{id}")
    public ResponseEntity<ProviderPortDTO> getProviderPort(@PathVariable Long id) {
        log.debug("REST request to get ProviderPort : {}", id);
        Optional<ProviderPortDTO> providerPortDTO = providerPortService.findOne(id);
        return ResponseUtil.wrapOrNotFound(providerPortDTO);
    }

    /**
     * {@code DELETE  /provider-ports/:id} : delete the "id" providerPort.
     *
     * @param id the id of the providerPortDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/provider-ports/{id}")
    public ResponseEntity<Void> deleteProviderPort(@PathVariable Long id) {
        log.debug("REST request to delete ProviderPort : {}", id);
        providerPortService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
