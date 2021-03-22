package com.activeport.web.web.rest;

import com.activeport.web.service.XeroAccountService;
import com.activeport.web.service.dto.XeroAccountDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.XeroAccount}.
 */
@RestController
@RequestMapping("/api")
public class XeroAccountResource {
    private final Logger log = LoggerFactory.getLogger(XeroAccountResource.class);

    private static final String ENTITY_NAME = "xeroAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final XeroAccountService xeroAccountService;

    public XeroAccountResource(XeroAccountService xeroAccountService) {
        this.xeroAccountService = xeroAccountService;
    }

    /**
     * {@code POST  /xero-accounts} : Create a new xeroAccount.
     *
     * @param xeroAccountDTO the xeroAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new xeroAccountDTO, or with status {@code 400 (Bad Request)} if the xeroAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/xero-accounts")
    public ResponseEntity<XeroAccountDTO> createXeroAccount(@RequestBody XeroAccountDTO xeroAccountDTO) throws URISyntaxException {
        log.debug("REST request to save XeroAccount : {}", xeroAccountDTO);
        if (xeroAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new xeroAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        XeroAccountDTO result = xeroAccountService.save(xeroAccountDTO);
        return ResponseEntity
            .created(new URI("/api/xero-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /xero-accounts} : Updates an existing xeroAccount.
     *
     * @param xeroAccountDTO the xeroAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated xeroAccountDTO,
     * or with status {@code 400 (Bad Request)} if the xeroAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the xeroAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/xero-accounts")
    public ResponseEntity<XeroAccountDTO> updateXeroAccount(@RequestBody XeroAccountDTO xeroAccountDTO) throws URISyntaxException {
        log.debug("REST request to update XeroAccount : {}", xeroAccountDTO);
        if (xeroAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        XeroAccountDTO result = xeroAccountService.save(xeroAccountDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, xeroAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /xero-accounts} : get all the xeroAccounts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of xeroAccounts in body.
     */
    @GetMapping("/xero-accounts")
    public ResponseEntity<List<XeroAccountDTO>> getAllXeroAccounts(Pageable pageable) {
        log.debug("REST request to get a page of XeroAccounts");
        Page<XeroAccountDTO> page = xeroAccountService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /xero-accounts/:id} : get the "id" xeroAccount.
     *
     * @param id the id of the xeroAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the xeroAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/xero-accounts/{id}")
    public ResponseEntity<XeroAccountDTO> getXeroAccount(@PathVariable Long id) {
        log.debug("REST request to get XeroAccount : {}", id);
        Optional<XeroAccountDTO> xeroAccountDTO = xeroAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(xeroAccountDTO);
    }

    /**
     * {@code DELETE  /xero-accounts/:id} : delete the "id" xeroAccount.
     *
     * @param id the id of the xeroAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/xero-accounts/{id}")
    public ResponseEntity<Void> deleteXeroAccount(@PathVariable Long id) {
        log.debug("REST request to delete XeroAccount : {}", id);
        xeroAccountService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
