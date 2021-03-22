package com.activeport.web.web.rest;

import com.activeport.web.service.NtuService;
import com.activeport.web.service.dto.NtuDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.Ntu}.
 */
@RestController
@RequestMapping("/api")
public class NtuResource {
    private final Logger log = LoggerFactory.getLogger(NtuResource.class);

    private static final String ENTITY_NAME = "ntu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NtuService ntuService;

    public NtuResource(NtuService ntuService) {
        this.ntuService = ntuService;
    }

    /**
     * {@code POST  /ntus} : Create a new ntu.
     *
     * @param ntuDTO the ntuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ntuDTO, or with status {@code 400 (Bad Request)} if the ntu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ntus")
    public ResponseEntity<NtuDTO> createNtu(@Valid @RequestBody NtuDTO ntuDTO) throws URISyntaxException {
        log.debug("REST request to save Ntu : {}", ntuDTO);
        if (ntuDTO.getId() != null) {
            throw new BadRequestAlertException("A new ntu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NtuDTO result = ntuService.save(ntuDTO);
        return ResponseEntity
            .created(new URI("/api/ntus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ntus} : Updates an existing ntu.
     *
     * @param ntuDTO the ntuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ntuDTO,
     * or with status {@code 400 (Bad Request)} if the ntuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ntuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ntus")
    public ResponseEntity<NtuDTO> updateNtu(@Valid @RequestBody NtuDTO ntuDTO) throws URISyntaxException {
        log.debug("REST request to update Ntu : {}", ntuDTO);
        if (ntuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NtuDTO result = ntuService.save(ntuDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ntuDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ntus} : get all the ntus.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ntus in body.
     */
    @GetMapping("/ntus")
    public ResponseEntity<List<NtuDTO>> getAllNtus(Pageable pageable) {
        log.debug("REST request to get a page of Ntus");
        Page<NtuDTO> page = ntuService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ntus/:id} : get the "id" ntu.
     *
     * @param id the id of the ntuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ntuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ntus/{id}")
    public ResponseEntity<NtuDTO> getNtu(@PathVariable Long id) {
        log.debug("REST request to get Ntu : {}", id);
        Optional<NtuDTO> ntuDTO = ntuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ntuDTO);
    }

    /**
     * {@code DELETE  /ntus/:id} : delete the "id" ntu.
     *
     * @param id the id of the ntuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ntus/{id}")
    public ResponseEntity<Void> deleteNtu(@PathVariable Long id) {
        log.debug("REST request to delete Ntu : {}", id);
        ntuService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
