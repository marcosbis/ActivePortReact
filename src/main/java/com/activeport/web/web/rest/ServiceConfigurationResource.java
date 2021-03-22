package com.activeport.web.web.rest;

import com.activeport.web.service.ServiceConfigurationService;
import com.activeport.web.service.dto.ServiceConfigurationDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.ServiceConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class ServiceConfigurationResource {
    private final Logger log = LoggerFactory.getLogger(ServiceConfigurationResource.class);

    private static final String ENTITY_NAME = "serviceConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceConfigurationService serviceConfigurationService;

    public ServiceConfigurationResource(ServiceConfigurationService serviceConfigurationService) {
        this.serviceConfigurationService = serviceConfigurationService;
    }

    /**
     * {@code POST  /service-configurations} : Create a new serviceConfiguration.
     *
     * @param serviceConfigurationDTO the serviceConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceConfigurationDTO, or with status {@code 400 (Bad Request)} if the serviceConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-configurations")
    public ResponseEntity<ServiceConfigurationDTO> createServiceConfiguration(@RequestBody ServiceConfigurationDTO serviceConfigurationDTO)
        throws URISyntaxException {
        log.debug("REST request to save ServiceConfiguration : {}", serviceConfigurationDTO);
        if (serviceConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceConfigurationDTO result = serviceConfigurationService.save(serviceConfigurationDTO);
        return ResponseEntity
            .created(new URI("/api/service-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-configurations} : Updates an existing serviceConfiguration.
     *
     * @param serviceConfigurationDTO the serviceConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the serviceConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-configurations")
    public ResponseEntity<ServiceConfigurationDTO> updateServiceConfiguration(@RequestBody ServiceConfigurationDTO serviceConfigurationDTO)
        throws URISyntaxException {
        log.debug("REST request to update ServiceConfiguration : {}", serviceConfigurationDTO);
        if (serviceConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceConfigurationDTO result = serviceConfigurationService.save(serviceConfigurationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-configurations} : get all the serviceConfigurations.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceConfigurations in body.
     */
    @GetMapping("/service-configurations")
    public ResponseEntity<List<ServiceConfigurationDTO>> getAllServiceConfigurations(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of ServiceConfigurations");
        Page<ServiceConfigurationDTO> page;
        if (eagerload) {
            page = serviceConfigurationService.findAllWithEagerRelationships(pageable);
        } else {
            page = serviceConfigurationService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /service-configurations/:id} : get the "id" serviceConfiguration.
     *
     * @param id the id of the serviceConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-configurations/{id}")
    public ResponseEntity<ServiceConfigurationDTO> getServiceConfiguration(@PathVariable Long id) {
        log.debug("REST request to get ServiceConfiguration : {}", id);
        Optional<ServiceConfigurationDTO> serviceConfigurationDTO = serviceConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceConfigurationDTO);
    }

    /**
     * {@code DELETE  /service-configurations/:id} : delete the "id" serviceConfiguration.
     *
     * @param id the id of the serviceConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-configurations/{id}")
    public ResponseEntity<Void> deleteServiceConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete ServiceConfiguration : {}", id);
        serviceConfigurationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
