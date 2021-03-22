package com.activeport.web.web.rest;

import com.activeport.web.service.DeviceConfigurationService;
import com.activeport.web.service.dto.DeviceConfigurationDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.DeviceConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class DeviceConfigurationResource {
    private final Logger log = LoggerFactory.getLogger(DeviceConfigurationResource.class);

    private static final String ENTITY_NAME = "deviceConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeviceConfigurationService deviceConfigurationService;

    public DeviceConfigurationResource(DeviceConfigurationService deviceConfigurationService) {
        this.deviceConfigurationService = deviceConfigurationService;
    }

    /**
     * {@code POST  /device-configurations} : Create a new deviceConfiguration.
     *
     * @param deviceConfigurationDTO the deviceConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deviceConfigurationDTO, or with status {@code 400 (Bad Request)} if the deviceConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/device-configurations")
    public ResponseEntity<DeviceConfigurationDTO> createDeviceConfiguration(@RequestBody DeviceConfigurationDTO deviceConfigurationDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeviceConfiguration : {}", deviceConfigurationDTO);
        if (deviceConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new deviceConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeviceConfigurationDTO result = deviceConfigurationService.save(deviceConfigurationDTO);
        return ResponseEntity
            .created(new URI("/api/device-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /device-configurations} : Updates an existing deviceConfiguration.
     *
     * @param deviceConfigurationDTO the deviceConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviceConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the deviceConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deviceConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/device-configurations")
    public ResponseEntity<DeviceConfigurationDTO> updateDeviceConfiguration(@RequestBody DeviceConfigurationDTO deviceConfigurationDTO)
        throws URISyntaxException {
        log.debug("REST request to update DeviceConfiguration : {}", deviceConfigurationDTO);
        if (deviceConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeviceConfigurationDTO result = deviceConfigurationService.save(deviceConfigurationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviceConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /device-configurations} : get all the deviceConfigurations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deviceConfigurations in body.
     */
    @GetMapping("/device-configurations")
    public ResponseEntity<List<DeviceConfigurationDTO>> getAllDeviceConfigurations(Pageable pageable) {
        log.debug("REST request to get a page of DeviceConfigurations");
        Page<DeviceConfigurationDTO> page = deviceConfigurationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /device-configurations/:id} : get the "id" deviceConfiguration.
     *
     * @param id the id of the deviceConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deviceConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/device-configurations/{id}")
    public ResponseEntity<DeviceConfigurationDTO> getDeviceConfiguration(@PathVariable Long id) {
        log.debug("REST request to get DeviceConfiguration : {}", id);
        Optional<DeviceConfigurationDTO> deviceConfigurationDTO = deviceConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deviceConfigurationDTO);
    }

    /**
     * {@code DELETE  /device-configurations/:id} : delete the "id" deviceConfiguration.
     *
     * @param id the id of the deviceConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/device-configurations/{id}")
    public ResponseEntity<Void> deleteDeviceConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete DeviceConfiguration : {}", id);
        deviceConfigurationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
