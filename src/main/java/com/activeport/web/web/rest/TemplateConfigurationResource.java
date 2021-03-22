package com.activeport.web.web.rest;

import com.activeport.web.service.TemplateConfigurationService;
import com.activeport.web.service.dto.TemplateConfigurationDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.TemplateConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class TemplateConfigurationResource {
    private final Logger log = LoggerFactory.getLogger(TemplateConfigurationResource.class);

    private static final String ENTITY_NAME = "templateConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TemplateConfigurationService templateConfigurationService;

    public TemplateConfigurationResource(TemplateConfigurationService templateConfigurationService) {
        this.templateConfigurationService = templateConfigurationService;
    }

    /**
     * {@code POST  /template-configurations} : Create a new templateConfiguration.
     *
     * @param templateConfigurationDTO the templateConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new templateConfigurationDTO, or with status {@code 400 (Bad Request)} if the templateConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/template-configurations")
    public ResponseEntity<TemplateConfigurationDTO> createTemplateConfiguration(
        @RequestBody TemplateConfigurationDTO templateConfigurationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save TemplateConfiguration : {}", templateConfigurationDTO);
        if (templateConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new templateConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemplateConfigurationDTO result = templateConfigurationService.save(templateConfigurationDTO);
        return ResponseEntity
            .created(new URI("/api/template-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /template-configurations} : Updates an existing templateConfiguration.
     *
     * @param templateConfigurationDTO the templateConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated templateConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the templateConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the templateConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/template-configurations")
    public ResponseEntity<TemplateConfigurationDTO> updateTemplateConfiguration(
        @RequestBody TemplateConfigurationDTO templateConfigurationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update TemplateConfiguration : {}", templateConfigurationDTO);
        if (templateConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemplateConfigurationDTO result = templateConfigurationService.save(templateConfigurationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, templateConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /template-configurations} : get all the templateConfigurations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of templateConfigurations in body.
     */
    @GetMapping("/template-configurations")
    public ResponseEntity<List<TemplateConfigurationDTO>> getAllTemplateConfigurations(Pageable pageable) {
        log.debug("REST request to get a page of TemplateConfigurations");
        Page<TemplateConfigurationDTO> page = templateConfigurationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /template-configurations/:id} : get the "id" templateConfiguration.
     *
     * @param id the id of the templateConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the templateConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/template-configurations/{id}")
    public ResponseEntity<TemplateConfigurationDTO> getTemplateConfiguration(@PathVariable Long id) {
        log.debug("REST request to get TemplateConfiguration : {}", id);
        Optional<TemplateConfigurationDTO> templateConfigurationDTO = templateConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(templateConfigurationDTO);
    }

    /**
     * {@code DELETE  /template-configurations/:id} : delete the "id" templateConfiguration.
     *
     * @param id the id of the templateConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/template-configurations/{id}")
    public ResponseEntity<Void> deleteTemplateConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete TemplateConfiguration : {}", id);
        templateConfigurationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
