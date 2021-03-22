package com.activeport.web.web.rest;

import com.activeport.web.service.ServiceCommandService;
import com.activeport.web.service.dto.ServiceCommandDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.ServiceCommand}.
 */
@RestController
@RequestMapping("/api")
public class ServiceCommandResource {
    private final Logger log = LoggerFactory.getLogger(ServiceCommandResource.class);

    private static final String ENTITY_NAME = "serviceCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceCommandService serviceCommandService;

    public ServiceCommandResource(ServiceCommandService serviceCommandService) {
        this.serviceCommandService = serviceCommandService;
    }

    /**
     * {@code POST  /service-commands} : Create a new serviceCommand.
     *
     * @param serviceCommandDTO the serviceCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceCommandDTO, or with status {@code 400 (Bad Request)} if the serviceCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-commands")
    public ResponseEntity<ServiceCommandDTO> createServiceCommand(@RequestBody ServiceCommandDTO serviceCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to save ServiceCommand : {}", serviceCommandDTO);
        if (serviceCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceCommandDTO result = serviceCommandService.save(serviceCommandDTO);
        return ResponseEntity
            .created(new URI("/api/service-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-commands} : Updates an existing serviceCommand.
     *
     * @param serviceCommandDTO the serviceCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceCommandDTO,
     * or with status {@code 400 (Bad Request)} if the serviceCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-commands")
    public ResponseEntity<ServiceCommandDTO> updateServiceCommand(@RequestBody ServiceCommandDTO serviceCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to update ServiceCommand : {}", serviceCommandDTO);
        if (serviceCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceCommandDTO result = serviceCommandService.save(serviceCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-commands} : get all the serviceCommands.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceCommands in body.
     */
    @GetMapping("/service-commands")
    public ResponseEntity<List<ServiceCommandDTO>> getAllServiceCommands(Pageable pageable) {
        log.debug("REST request to get a page of ServiceCommands");
        Page<ServiceCommandDTO> page = serviceCommandService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /service-commands/:id} : get the "id" serviceCommand.
     *
     * @param id the id of the serviceCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-commands/{id}")
    public ResponseEntity<ServiceCommandDTO> getServiceCommand(@PathVariable Long id) {
        log.debug("REST request to get ServiceCommand : {}", id);
        Optional<ServiceCommandDTO> serviceCommandDTO = serviceCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceCommandDTO);
    }

    /**
     * {@code DELETE  /service-commands/:id} : delete the "id" serviceCommand.
     *
     * @param id the id of the serviceCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-commands/{id}")
    public ResponseEntity<Void> deleteServiceCommand(@PathVariable Long id) {
        log.debug("REST request to delete ServiceCommand : {}", id);
        serviceCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
