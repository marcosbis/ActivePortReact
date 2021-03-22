package com.activeport.web.web.rest;

import com.activeport.web.service.AddressAllocationService;
import com.activeport.web.service.dto.AddressAllocationDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.AddressAllocation}.
 */
@RestController
@RequestMapping("/api")
public class AddressAllocationResource {
    private final Logger log = LoggerFactory.getLogger(AddressAllocationResource.class);

    private static final String ENTITY_NAME = "addressAllocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AddressAllocationService addressAllocationService;

    public AddressAllocationResource(AddressAllocationService addressAllocationService) {
        this.addressAllocationService = addressAllocationService;
    }

    /**
     * {@code POST  /address-allocations} : Create a new addressAllocation.
     *
     * @param addressAllocationDTO the addressAllocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addressAllocationDTO, or with status {@code 400 (Bad Request)} if the addressAllocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/address-allocations")
    public ResponseEntity<AddressAllocationDTO> createAddressAllocation(@RequestBody AddressAllocationDTO addressAllocationDTO)
        throws URISyntaxException {
        log.debug("REST request to save AddressAllocation : {}", addressAllocationDTO);
        if (addressAllocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new addressAllocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressAllocationDTO result = addressAllocationService.save(addressAllocationDTO);
        return ResponseEntity
            .created(new URI("/api/address-allocations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /address-allocations} : Updates an existing addressAllocation.
     *
     * @param addressAllocationDTO the addressAllocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addressAllocationDTO,
     * or with status {@code 400 (Bad Request)} if the addressAllocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addressAllocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/address-allocations")
    public ResponseEntity<AddressAllocationDTO> updateAddressAllocation(@RequestBody AddressAllocationDTO addressAllocationDTO)
        throws URISyntaxException {
        log.debug("REST request to update AddressAllocation : {}", addressAllocationDTO);
        if (addressAllocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddressAllocationDTO result = addressAllocationService.save(addressAllocationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, addressAllocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /address-allocations} : get all the addressAllocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addressAllocations in body.
     */
    @GetMapping("/address-allocations")
    public ResponseEntity<List<AddressAllocationDTO>> getAllAddressAllocations(Pageable pageable) {
        log.debug("REST request to get a page of AddressAllocations");
        Page<AddressAllocationDTO> page = addressAllocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /address-allocations/:id} : get the "id" addressAllocation.
     *
     * @param id the id of the addressAllocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addressAllocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/address-allocations/{id}")
    public ResponseEntity<AddressAllocationDTO> getAddressAllocation(@PathVariable Long id) {
        log.debug("REST request to get AddressAllocation : {}", id);
        Optional<AddressAllocationDTO> addressAllocationDTO = addressAllocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addressAllocationDTO);
    }

    /**
     * {@code DELETE  /address-allocations/:id} : delete the "id" addressAllocation.
     *
     * @param id the id of the addressAllocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/address-allocations/{id}")
    public ResponseEntity<Void> deleteAddressAllocation(@PathVariable Long id) {
        log.debug("REST request to delete AddressAllocation : {}", id);
        addressAllocationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
