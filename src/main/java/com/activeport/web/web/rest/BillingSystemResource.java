package com.activeport.web.web.rest;

import com.activeport.web.service.BillingSystemService;
import com.activeport.web.service.dto.BillingSystemDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.BillingSystem}.
 */
@RestController
@RequestMapping("/api")
public class BillingSystemResource {
    private final Logger log = LoggerFactory.getLogger(BillingSystemResource.class);

    private static final String ENTITY_NAME = "billingSystem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingSystemService billingSystemService;

    public BillingSystemResource(BillingSystemService billingSystemService) {
        this.billingSystemService = billingSystemService;
    }

    /**
     * {@code POST  /billing-systems} : Create a new billingSystem.
     *
     * @param billingSystemDTO the billingSystemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingSystemDTO, or with status {@code 400 (Bad Request)} if the billingSystem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billing-systems")
    public ResponseEntity<BillingSystemDTO> createBillingSystem(@Valid @RequestBody BillingSystemDTO billingSystemDTO)
        throws URISyntaxException {
        log.debug("REST request to save BillingSystem : {}", billingSystemDTO);
        if (billingSystemDTO.getId() != null) {
            throw new BadRequestAlertException("A new billingSystem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillingSystemDTO result = billingSystemService.save(billingSystemDTO);
        return ResponseEntity
            .created(new URI("/api/billing-systems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billing-systems} : Updates an existing billingSystem.
     *
     * @param billingSystemDTO the billingSystemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingSystemDTO,
     * or with status {@code 400 (Bad Request)} if the billingSystemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingSystemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billing-systems")
    public ResponseEntity<BillingSystemDTO> updateBillingSystem(@Valid @RequestBody BillingSystemDTO billingSystemDTO)
        throws URISyntaxException {
        log.debug("REST request to update BillingSystem : {}", billingSystemDTO);
        if (billingSystemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillingSystemDTO result = billingSystemService.save(billingSystemDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billingSystemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /billing-systems} : get all the billingSystems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingSystems in body.
     */
    @GetMapping("/billing-systems")
    public ResponseEntity<List<BillingSystemDTO>> getAllBillingSystems(Pageable pageable) {
        log.debug("REST request to get a page of BillingSystems");
        Page<BillingSystemDTO> page = billingSystemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billing-systems/:id} : get the "id" billingSystem.
     *
     * @param id the id of the billingSystemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingSystemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billing-systems/{id}")
    public ResponseEntity<BillingSystemDTO> getBillingSystem(@PathVariable Long id) {
        log.debug("REST request to get BillingSystem : {}", id);
        Optional<BillingSystemDTO> billingSystemDTO = billingSystemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billingSystemDTO);
    }

    /**
     * {@code DELETE  /billing-systems/:id} : delete the "id" billingSystem.
     *
     * @param id the id of the billingSystemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billing-systems/{id}")
    public ResponseEntity<Void> deleteBillingSystem(@PathVariable Long id) {
        log.debug("REST request to delete BillingSystem : {}", id);
        billingSystemService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
