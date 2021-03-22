package com.activeport.web.web.rest;

import com.activeport.web.service.NtuTypeService;
import com.activeport.web.service.dto.NtuTypeDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.NtuType}.
 */
@RestController
@RequestMapping("/api")
public class NtuTypeResource {
    private final Logger log = LoggerFactory.getLogger(NtuTypeResource.class);

    private static final String ENTITY_NAME = "ntuType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NtuTypeService ntuTypeService;

    public NtuTypeResource(NtuTypeService ntuTypeService) {
        this.ntuTypeService = ntuTypeService;
    }

    /**
     * {@code POST  /ntu-types} : Create a new ntuType.
     *
     * @param ntuTypeDTO the ntuTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ntuTypeDTO, or with status {@code 400 (Bad Request)} if the ntuType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ntu-types")
    public ResponseEntity<NtuTypeDTO> createNtuType(@RequestBody NtuTypeDTO ntuTypeDTO) throws URISyntaxException {
        log.debug("REST request to save NtuType : {}", ntuTypeDTO);
        if (ntuTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new ntuType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NtuTypeDTO result = ntuTypeService.save(ntuTypeDTO);
        return ResponseEntity
            .created(new URI("/api/ntu-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ntu-types} : Updates an existing ntuType.
     *
     * @param ntuTypeDTO the ntuTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ntuTypeDTO,
     * or with status {@code 400 (Bad Request)} if the ntuTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ntuTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ntu-types")
    public ResponseEntity<NtuTypeDTO> updateNtuType(@RequestBody NtuTypeDTO ntuTypeDTO) throws URISyntaxException {
        log.debug("REST request to update NtuType : {}", ntuTypeDTO);
        if (ntuTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NtuTypeDTO result = ntuTypeService.save(ntuTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ntuTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ntu-types} : get all the ntuTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ntuTypes in body.
     */
    @GetMapping("/ntu-types")
    public ResponseEntity<List<NtuTypeDTO>> getAllNtuTypes(Pageable pageable) {
        log.debug("REST request to get a page of NtuTypes");
        Page<NtuTypeDTO> page = ntuTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ntu-types/:id} : get the "id" ntuType.
     *
     * @param id the id of the ntuTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ntuTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ntu-types/{id}")
    public ResponseEntity<NtuTypeDTO> getNtuType(@PathVariable Long id) {
        log.debug("REST request to get NtuType : {}", id);
        Optional<NtuTypeDTO> ntuTypeDTO = ntuTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ntuTypeDTO);
    }

    /**
     * {@code DELETE  /ntu-types/:id} : delete the "id" ntuType.
     *
     * @param id the id of the ntuTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ntu-types/{id}")
    public ResponseEntity<Void> deleteNtuType(@PathVariable Long id) {
        log.debug("REST request to delete NtuType : {}", id);
        ntuTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
