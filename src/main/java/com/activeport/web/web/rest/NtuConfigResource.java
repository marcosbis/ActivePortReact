package com.activeport.web.web.rest;

import com.activeport.web.service.NtuConfigService;
import com.activeport.web.service.dto.NtuConfigDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.NtuConfig}.
 */
@RestController
@RequestMapping("/api")
public class NtuConfigResource {
    private final Logger log = LoggerFactory.getLogger(NtuConfigResource.class);

    private static final String ENTITY_NAME = "ntuConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NtuConfigService ntuConfigService;

    public NtuConfigResource(NtuConfigService ntuConfigService) {
        this.ntuConfigService = ntuConfigService;
    }

    /**
     * {@code POST  /ntu-configs} : Create a new ntuConfig.
     *
     * @param ntuConfigDTO the ntuConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ntuConfigDTO, or with status {@code 400 (Bad Request)} if the ntuConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ntu-configs")
    public ResponseEntity<NtuConfigDTO> createNtuConfig(@RequestBody NtuConfigDTO ntuConfigDTO) throws URISyntaxException {
        log.debug("REST request to save NtuConfig : {}", ntuConfigDTO);
        if (ntuConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new ntuConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NtuConfigDTO result = ntuConfigService.save(ntuConfigDTO);
        return ResponseEntity
            .created(new URI("/api/ntu-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ntu-configs} : Updates an existing ntuConfig.
     *
     * @param ntuConfigDTO the ntuConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ntuConfigDTO,
     * or with status {@code 400 (Bad Request)} if the ntuConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ntuConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ntu-configs")
    public ResponseEntity<NtuConfigDTO> updateNtuConfig(@RequestBody NtuConfigDTO ntuConfigDTO) throws URISyntaxException {
        log.debug("REST request to update NtuConfig : {}", ntuConfigDTO);
        if (ntuConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NtuConfigDTO result = ntuConfigService.save(ntuConfigDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ntuConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ntu-configs} : get all the ntuConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ntuConfigs in body.
     */
    @GetMapping("/ntu-configs")
    public ResponseEntity<List<NtuConfigDTO>> getAllNtuConfigs(Pageable pageable) {
        log.debug("REST request to get a page of NtuConfigs");
        Page<NtuConfigDTO> page = ntuConfigService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ntu-configs/:id} : get the "id" ntuConfig.
     *
     * @param id the id of the ntuConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ntuConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ntu-configs/{id}")
    public ResponseEntity<NtuConfigDTO> getNtuConfig(@PathVariable Long id) {
        log.debug("REST request to get NtuConfig : {}", id);
        Optional<NtuConfigDTO> ntuConfigDTO = ntuConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ntuConfigDTO);
    }

    /**
     * {@code DELETE  /ntu-configs/:id} : delete the "id" ntuConfig.
     *
     * @param id the id of the ntuConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ntu-configs/{id}")
    public ResponseEntity<Void> deleteNtuConfig(@PathVariable Long id) {
        log.debug("REST request to delete NtuConfig : {}", id);
        ntuConfigService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
