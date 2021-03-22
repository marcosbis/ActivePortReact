package com.activeport.web.web.rest;

import com.activeport.web.service.NotificationEventService;
import com.activeport.web.service.dto.NotificationEventDTO;
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
 * REST controller for managing {@link com.activeport.web.domain.NotificationEvent}.
 */
@RestController
@RequestMapping("/api")
public class NotificationEventResource {
    private final Logger log = LoggerFactory.getLogger(NotificationEventResource.class);

    private static final String ENTITY_NAME = "notificationEvent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationEventService notificationEventService;

    public NotificationEventResource(NotificationEventService notificationEventService) {
        this.notificationEventService = notificationEventService;
    }

    /**
     * {@code POST  /notification-events} : Create a new notificationEvent.
     *
     * @param notificationEventDTO the notificationEventDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationEventDTO, or with status {@code 400 (Bad Request)} if the notificationEvent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-events")
    public ResponseEntity<NotificationEventDTO> createNotificationEvent(@RequestBody NotificationEventDTO notificationEventDTO)
        throws URISyntaxException {
        log.debug("REST request to save NotificationEvent : {}", notificationEventDTO);
        if (notificationEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationEventDTO result = notificationEventService.save(notificationEventDTO);
        return ResponseEntity
            .created(new URI("/api/notification-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-events} : Updates an existing notificationEvent.
     *
     * @param notificationEventDTO the notificationEventDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationEventDTO,
     * or with status {@code 400 (Bad Request)} if the notificationEventDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationEventDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-events")
    public ResponseEntity<NotificationEventDTO> updateNotificationEvent(@RequestBody NotificationEventDTO notificationEventDTO)
        throws URISyntaxException {
        log.debug("REST request to update NotificationEvent : {}", notificationEventDTO);
        if (notificationEventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationEventDTO result = notificationEventService.save(notificationEventDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notificationEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notification-events} : get all the notificationEvents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationEvents in body.
     */
    @GetMapping("/notification-events")
    public ResponseEntity<List<NotificationEventDTO>> getAllNotificationEvents(Pageable pageable) {
        log.debug("REST request to get a page of NotificationEvents");
        Page<NotificationEventDTO> page = notificationEventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /notification-events/:id} : get the "id" notificationEvent.
     *
     * @param id the id of the notificationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-events/{id}")
    public ResponseEntity<NotificationEventDTO> getNotificationEvent(@PathVariable Long id) {
        log.debug("REST request to get NotificationEvent : {}", id);
        Optional<NotificationEventDTO> notificationEventDTO = notificationEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationEventDTO);
    }

    /**
     * {@code DELETE  /notification-events/:id} : delete the "id" notificationEvent.
     *
     * @param id the id of the notificationEventDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-events/{id}")
    public ResponseEntity<Void> deleteNotificationEvent(@PathVariable Long id) {
        log.debug("REST request to delete NotificationEvent : {}", id);
        notificationEventService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
