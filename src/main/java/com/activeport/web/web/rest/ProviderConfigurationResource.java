package com.activeport.web.web.rest;

import com.activeport.web.service.ProviderConfigurationService;
import com.activeport.web.service.dto.ProviderConfigurationDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.ProviderConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class ProviderConfigurationResource {
    private final Logger log = LoggerFactory.getLogger(ProviderConfigurationResource.class);

    private static final String ENTITY_NAME = "providerConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProviderConfigurationService providerConfigurationService;

    public ProviderConfigurationResource(ProviderConfigurationService providerConfigurationService) {
        this.providerConfigurationService = providerConfigurationService;
    }

    /**
     * {@code POST  /provider-configurations} : Create a new providerConfiguration.
     *
     * @param providerConfigurationDTO the providerConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new providerConfigurationDTO, or with status {@code 400 (Bad Request)} if the providerConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/provider-configurations")
    public ResponseEntity<ProviderConfigurationDTO> createProviderConfiguration(
        @Valid @RequestBody ProviderConfigurationDTO providerConfigurationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save ProviderConfiguration : {}", providerConfigurationDTO);
        if (providerConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new providerConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProviderConfigurationDTO result = providerConfigurationService.save(providerConfigurationDTO);
        return ResponseEntity
            .created(new URI("/api/provider-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /provider-configurations} : Updates an existing providerConfiguration.
     *
     * @param providerConfigurationDTO the providerConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated providerConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the providerConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the providerConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/provider-configurations")
    public ResponseEntity<ProviderConfigurationDTO> updateProviderConfiguration(
        @Valid @RequestBody ProviderConfigurationDTO providerConfigurationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update ProviderConfiguration : {}", providerConfigurationDTO);
        if (providerConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProviderConfigurationDTO result = providerConfigurationService.save(providerConfigurationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, providerConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /provider-configurations} : get all the providerConfigurations.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of providerConfigurations in body.
     */
    @GetMapping("/provider-configurations")
    public ResponseEntity<List<ProviderConfigurationDTO>> getAllProviderConfigurations(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of ProviderConfigurations");
        Page<ProviderConfigurationDTO> page;
        if (eagerload) {
            page = providerConfigurationService.findAllWithEagerRelationships(pageable);
        } else {
            page = providerConfigurationService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /provider-configurations/:id} : get the "id" providerConfiguration.
     *
     * @param id the id of the providerConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the providerConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/provider-configurations/{id}")
    public ResponseEntity<ProviderConfigurationDTO> getProviderConfiguration(@PathVariable Long id) {
        log.debug("REST request to get ProviderConfiguration : {}", id);
        Optional<ProviderConfigurationDTO> providerConfigurationDTO = providerConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(providerConfigurationDTO);
    }

    /**
     * {@code DELETE  /provider-configurations/:id} : delete the "id" providerConfiguration.
     *
     * @param id the id of the providerConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/provider-configurations/{id}")
    public ResponseEntity<Void> deleteProviderConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete ProviderConfiguration : {}", id);
        providerConfigurationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
