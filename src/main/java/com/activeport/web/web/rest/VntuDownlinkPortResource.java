package com.activeport.web.web.rest;

import com.activeport.web.service.VntuDownlinkPortService;
import com.activeport.web.service.dto.VntuDownlinkPortDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.VntuDownlinkPort}.
 */
@RestController
@RequestMapping("/api")
public class VntuDownlinkPortResource {
    private final Logger log = LoggerFactory.getLogger(VntuDownlinkPortResource.class);

    private static final String ENTITY_NAME = "vntuDownlinkPort";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VntuDownlinkPortService vntuDownlinkPortService;

    public VntuDownlinkPortResource(VntuDownlinkPortService vntuDownlinkPortService) {
        this.vntuDownlinkPortService = vntuDownlinkPortService;
    }

    /**
     * {@code POST  /vntu-downlink-ports} : Create a new vntuDownlinkPort.
     *
     * @param vntuDownlinkPortDTO the vntuDownlinkPortDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vntuDownlinkPortDTO, or with status {@code 400 (Bad Request)} if the vntuDownlinkPort has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vntu-downlink-ports")
    public ResponseEntity<VntuDownlinkPortDTO> createVntuDownlinkPort(@RequestBody VntuDownlinkPortDTO vntuDownlinkPortDTO)
        throws URISyntaxException {
        log.debug("REST request to save VntuDownlinkPort : {}", vntuDownlinkPortDTO);
        if (vntuDownlinkPortDTO.getId() != null) {
            throw new BadRequestAlertException("A new vntuDownlinkPort cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VntuDownlinkPortDTO result = vntuDownlinkPortService.save(vntuDownlinkPortDTO);
        return ResponseEntity
            .created(new URI("/api/vntu-downlink-ports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vntu-downlink-ports} : Updates an existing vntuDownlinkPort.
     *
     * @param vntuDownlinkPortDTO the vntuDownlinkPortDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vntuDownlinkPortDTO,
     * or with status {@code 400 (Bad Request)} if the vntuDownlinkPortDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vntuDownlinkPortDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vntu-downlink-ports")
    public ResponseEntity<VntuDownlinkPortDTO> updateVntuDownlinkPort(@RequestBody VntuDownlinkPortDTO vntuDownlinkPortDTO)
        throws URISyntaxException {
        log.debug("REST request to update VntuDownlinkPort : {}", vntuDownlinkPortDTO);
        if (vntuDownlinkPortDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VntuDownlinkPortDTO result = vntuDownlinkPortService.save(vntuDownlinkPortDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vntuDownlinkPortDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vntu-downlink-ports} : get all the vntuDownlinkPorts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vntuDownlinkPorts in body.
     */
    @GetMapping("/vntu-downlink-ports")
    public ResponseEntity<List<VntuDownlinkPortDTO>> getAllVntuDownlinkPorts(Pageable pageable) {
        log.debug("REST request to get a page of VntuDownlinkPorts");
        Page<VntuDownlinkPortDTO> page = vntuDownlinkPortService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vntu-downlink-ports/:id} : get the "id" vntuDownlinkPort.
     *
     * @param id the id of the vntuDownlinkPortDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vntuDownlinkPortDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vntu-downlink-ports/{id}")
    public ResponseEntity<VntuDownlinkPortDTO> getVntuDownlinkPort(@PathVariable Long id) {
        log.debug("REST request to get VntuDownlinkPort : {}", id);
        Optional<VntuDownlinkPortDTO> vntuDownlinkPortDTO = vntuDownlinkPortService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vntuDownlinkPortDTO);
    }

    /**
     * {@code DELETE  /vntu-downlink-ports/:id} : delete the "id" vntuDownlinkPort.
     *
     * @param id the id of the vntuDownlinkPortDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vntu-downlink-ports/{id}")
    public ResponseEntity<Void> deleteVntuDownlinkPort(@PathVariable Long id) {
        log.debug("REST request to delete VntuDownlinkPort : {}", id);
        vntuDownlinkPortService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
