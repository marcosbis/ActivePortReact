package com.activeport.web.web.rest;

import com.activeport.web.service.CentralSwitchService;
import com.activeport.web.service.dto.CentralSwitchDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.CentralSwitch}.
 */
@RestController
@RequestMapping("/api")
public class CentralSwitchResource {
    private final Logger log = LoggerFactory.getLogger(CentralSwitchResource.class);

    private static final String ENTITY_NAME = "centralSwitch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CentralSwitchService centralSwitchService;

    public CentralSwitchResource(CentralSwitchService centralSwitchService) {
        this.centralSwitchService = centralSwitchService;
    }

    /**
     * {@code POST  /central-switches} : Create a new centralSwitch.
     *
     * @param centralSwitchDTO the centralSwitchDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new centralSwitchDTO, or with status {@code 400 (Bad Request)} if the centralSwitch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/central-switches")
    public ResponseEntity<CentralSwitchDTO> createCentralSwitch(@RequestBody CentralSwitchDTO centralSwitchDTO) throws URISyntaxException {
        log.debug("REST request to save CentralSwitch : {}", centralSwitchDTO);
        if (centralSwitchDTO.getId() != null) {
            throw new BadRequestAlertException("A new centralSwitch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CentralSwitchDTO result = centralSwitchService.save(centralSwitchDTO);
        return ResponseEntity
            .created(new URI("/api/central-switches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /central-switches} : Updates an existing centralSwitch.
     *
     * @param centralSwitchDTO the centralSwitchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centralSwitchDTO,
     * or with status {@code 400 (Bad Request)} if the centralSwitchDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the centralSwitchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/central-switches")
    public ResponseEntity<CentralSwitchDTO> updateCentralSwitch(@RequestBody CentralSwitchDTO centralSwitchDTO) throws URISyntaxException {
        log.debug("REST request to update CentralSwitch : {}", centralSwitchDTO);
        if (centralSwitchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CentralSwitchDTO result = centralSwitchService.save(centralSwitchDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, centralSwitchDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /central-switches} : get all the centralSwitches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of centralSwitches in body.
     */
    @GetMapping("/central-switches")
    public ResponseEntity<List<CentralSwitchDTO>> getAllCentralSwitches(Pageable pageable) {
        log.debug("REST request to get a page of CentralSwitches");
        Page<CentralSwitchDTO> page = centralSwitchService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /central-switches/:id} : get the "id" centralSwitch.
     *
     * @param id the id of the centralSwitchDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the centralSwitchDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/central-switches/{id}")
    public ResponseEntity<CentralSwitchDTO> getCentralSwitch(@PathVariable Long id) {
        log.debug("REST request to get CentralSwitch : {}", id);
        Optional<CentralSwitchDTO> centralSwitchDTO = centralSwitchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centralSwitchDTO);
    }

    /**
     * {@code DELETE  /central-switches/:id} : delete the "id" centralSwitch.
     *
     * @param id the id of the centralSwitchDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/central-switches/{id}")
    public ResponseEntity<Void> deleteCentralSwitch(@PathVariable Long id) {
        log.debug("REST request to delete CentralSwitch : {}", id);
        centralSwitchService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
