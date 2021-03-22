package com.activeport.web.web.rest;

import com.activeport.web.service.ThirdPartyApiService;
import com.activeport.web.service.dto.ThirdPartyApiDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.ThirdPartyApi}.
 */
@RestController
@RequestMapping("/api")
public class ThirdPartyApiResource {
    private final Logger log = LoggerFactory.getLogger(ThirdPartyApiResource.class);

    private static final String ENTITY_NAME = "thirdPartyApi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThirdPartyApiService thirdPartyApiService;

    public ThirdPartyApiResource(ThirdPartyApiService thirdPartyApiService) {
        this.thirdPartyApiService = thirdPartyApiService;
    }

    /**
     * {@code POST  /third-party-apis} : Create a new thirdPartyApi.
     *
     * @param thirdPartyApiDTO the thirdPartyApiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thirdPartyApiDTO, or with status {@code 400 (Bad Request)} if the thirdPartyApi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/third-party-apis")
    public ResponseEntity<ThirdPartyApiDTO> createThirdPartyApi(@Valid @RequestBody ThirdPartyApiDTO thirdPartyApiDTO)
        throws URISyntaxException {
        log.debug("REST request to save ThirdPartyApi : {}", thirdPartyApiDTO);
        if (thirdPartyApiDTO.getId() != null) {
            throw new BadRequestAlertException("A new thirdPartyApi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThirdPartyApiDTO result = thirdPartyApiService.save(thirdPartyApiDTO);
        return ResponseEntity
            .created(new URI("/api/third-party-apis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /third-party-apis} : Updates an existing thirdPartyApi.
     *
     * @param thirdPartyApiDTO the thirdPartyApiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thirdPartyApiDTO,
     * or with status {@code 400 (Bad Request)} if the thirdPartyApiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thirdPartyApiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/third-party-apis")
    public ResponseEntity<ThirdPartyApiDTO> updateThirdPartyApi(@Valid @RequestBody ThirdPartyApiDTO thirdPartyApiDTO)
        throws URISyntaxException {
        log.debug("REST request to update ThirdPartyApi : {}", thirdPartyApiDTO);
        if (thirdPartyApiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThirdPartyApiDTO result = thirdPartyApiService.save(thirdPartyApiDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thirdPartyApiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /third-party-apis} : get all the thirdPartyApis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thirdPartyApis in body.
     */
    @GetMapping("/third-party-apis")
    public ResponseEntity<List<ThirdPartyApiDTO>> getAllThirdPartyApis(Pageable pageable) {
        log.debug("REST request to get a page of ThirdPartyApis");
        Page<ThirdPartyApiDTO> page = thirdPartyApiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /third-party-apis/:id} : get the "id" thirdPartyApi.
     *
     * @param id the id of the thirdPartyApiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thirdPartyApiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/third-party-apis/{id}")
    public ResponseEntity<ThirdPartyApiDTO> getThirdPartyApi(@PathVariable Long id) {
        log.debug("REST request to get ThirdPartyApi : {}", id);
        Optional<ThirdPartyApiDTO> thirdPartyApiDTO = thirdPartyApiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thirdPartyApiDTO);
    }

    /**
     * {@code DELETE  /third-party-apis/:id} : delete the "id" thirdPartyApi.
     *
     * @param id the id of the thirdPartyApiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/third-party-apis/{id}")
    public ResponseEntity<Void> deleteThirdPartyApi(@PathVariable Long id) {
        log.debug("REST request to delete ThirdPartyApi : {}", id);
        thirdPartyApiService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
