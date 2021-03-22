package com.activeport.web.web.rest;

import com.activeport.web.service.ItemCodeService;
import com.activeport.web.service.dto.ItemCodeDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.ItemCode}.
 */
@RestController
@RequestMapping("/api")
public class ItemCodeResource {
    private final Logger log = LoggerFactory.getLogger(ItemCodeResource.class);

    private static final String ENTITY_NAME = "itemCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemCodeService itemCodeService;

    public ItemCodeResource(ItemCodeService itemCodeService) {
        this.itemCodeService = itemCodeService;
    }

    /**
     * {@code POST  /item-codes} : Create a new itemCode.
     *
     * @param itemCodeDTO the itemCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemCodeDTO, or with status {@code 400 (Bad Request)} if the itemCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-codes")
    public ResponseEntity<ItemCodeDTO> createItemCode(@RequestBody ItemCodeDTO itemCodeDTO) throws URISyntaxException {
        log.debug("REST request to save ItemCode : {}", itemCodeDTO);
        if (itemCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemCodeDTO result = itemCodeService.save(itemCodeDTO);
        return ResponseEntity
            .created(new URI("/api/item-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-codes} : Updates an existing itemCode.
     *
     * @param itemCodeDTO the itemCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemCodeDTO,
     * or with status {@code 400 (Bad Request)} if the itemCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-codes")
    public ResponseEntity<ItemCodeDTO> updateItemCode(@RequestBody ItemCodeDTO itemCodeDTO) throws URISyntaxException {
        log.debug("REST request to update ItemCode : {}", itemCodeDTO);
        if (itemCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemCodeDTO result = itemCodeService.save(itemCodeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-codes} : get all the itemCodes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemCodes in body.
     */
    @GetMapping("/item-codes")
    public ResponseEntity<List<ItemCodeDTO>> getAllItemCodes(Pageable pageable) {
        log.debug("REST request to get a page of ItemCodes");
        Page<ItemCodeDTO> page = itemCodeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-codes/:id} : get the "id" itemCode.
     *
     * @param id the id of the itemCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-codes/{id}")
    public ResponseEntity<ItemCodeDTO> getItemCode(@PathVariable Long id) {
        log.debug("REST request to get ItemCode : {}", id);
        Optional<ItemCodeDTO> itemCodeDTO = itemCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemCodeDTO);
    }

    /**
     * {@code DELETE  /item-codes/:id} : delete the "id" itemCode.
     *
     * @param id the id of the itemCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-codes/{id}")
    public ResponseEntity<Void> deleteItemCode(@PathVariable Long id) {
        log.debug("REST request to delete ItemCode : {}", id);
        itemCodeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
