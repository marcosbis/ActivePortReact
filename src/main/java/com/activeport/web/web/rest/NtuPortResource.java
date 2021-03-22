package com.activeport.web.web.rest;

import com.activeport.web.service.NtuPortService;
import com.activeport.web.service.dto.NtuPortDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.NtuPort}.
 */
@RestController
@RequestMapping("/api")
public class NtuPortResource {
    private final Logger log = LoggerFactory.getLogger(NtuPortResource.class);

    private static final String ENTITY_NAME = "ntuPort";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NtuPortService ntuPortService;

    public NtuPortResource(NtuPortService ntuPortService) {
        this.ntuPortService = ntuPortService;
    }

    /**
     * {@code POST  /ntu-ports} : Create a new ntuPort.
     *
     * @param ntuPortDTO the ntuPortDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ntuPortDTO, or with status {@code 400 (Bad Request)} if the ntuPort has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ntu-ports")
    public ResponseEntity<NtuPortDTO> createNtuPort(@RequestBody NtuPortDTO ntuPortDTO) throws URISyntaxException {
        log.debug("REST request to save NtuPort : {}", ntuPortDTO);
        if (ntuPortDTO.getId() != null) {
            throw new BadRequestAlertException("A new ntuPort cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NtuPortDTO result = ntuPortService.save(ntuPortDTO);
        return ResponseEntity
            .created(new URI("/api/ntu-ports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ntu-ports} : Updates an existing ntuPort.
     *
     * @param ntuPortDTO the ntuPortDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ntuPortDTO,
     * or with status {@code 400 (Bad Request)} if the ntuPortDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ntuPortDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ntu-ports")
    public ResponseEntity<NtuPortDTO> updateNtuPort(@RequestBody NtuPortDTO ntuPortDTO) throws URISyntaxException {
        log.debug("REST request to update NtuPort : {}", ntuPortDTO);
        if (ntuPortDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NtuPortDTO result = ntuPortService.save(ntuPortDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ntuPortDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ntu-ports} : get all the ntuPorts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ntuPorts in body.
     */
    @GetMapping("/ntu-ports")
    public ResponseEntity<List<NtuPortDTO>> getAllNtuPorts(Pageable pageable) {
        log.debug("REST request to get a page of NtuPorts");
        Page<NtuPortDTO> page = ntuPortService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ntu-ports/:id} : get the "id" ntuPort.
     *
     * @param id the id of the ntuPortDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ntuPortDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ntu-ports/{id}")
    public ResponseEntity<NtuPortDTO> getNtuPort(@PathVariable Long id) {
        log.debug("REST request to get NtuPort : {}", id);
        Optional<NtuPortDTO> ntuPortDTO = ntuPortService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ntuPortDTO);
    }

    /**
     * {@code DELETE  /ntu-ports/:id} : delete the "id" ntuPort.
     *
     * @param id the id of the ntuPortDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ntu-ports/{id}")
    public ResponseEntity<Void> deleteNtuPort(@PathVariable Long id) {
        log.debug("REST request to delete NtuPort : {}", id);
        ntuPortService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
