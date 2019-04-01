package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.CircleMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.CircleMasterDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing CircleMaster.
 */
@RestController
@RequestMapping("/api")
public class CircleMasterResource {

    private final Logger log = LoggerFactory.getLogger(CircleMasterResource.class);

    private static final String ENTITY_NAME = "circleMaster";

    private final CircleMasterService circleMasterService;

    public CircleMasterResource(CircleMasterService circleMasterService) {
        this.circleMasterService = circleMasterService;
    }

    /**
     * POST  /circle-masters : Create a new circleMaster.
     *
     * @param circleMasterDTO the circleMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new circleMasterDTO, or with status 400 (Bad Request) if the circleMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/circle-masters")
    @Timed
    public ResponseEntity<CircleMasterDTO> createCircleMaster(@Valid @RequestBody CircleMasterDTO circleMasterDTO) throws URISyntaxException {
        log.debug("REST request to save CircleMaster : {}", circleMasterDTO);
        if (circleMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new circleMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        circleMasterDTO.setId(UUID.randomUUID());
        CircleMasterDTO result = circleMasterService.save(circleMasterDTO);
        return ResponseEntity.created(new URI("/api/circle-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /circle-masters : Updates an existing circleMaster.
     *
     * @param circleMasterDTO the circleMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated circleMasterDTO,
     * or with status 400 (Bad Request) if the circleMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the circleMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/circle-masters")
    @Timed
    public ResponseEntity<CircleMasterDTO> updateCircleMaster(@Valid @RequestBody CircleMasterDTO circleMasterDTO) throws URISyntaxException {
        log.debug("REST request to update CircleMaster : {}", circleMasterDTO);
        if (circleMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CircleMasterDTO result = circleMasterService.save(circleMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, circleMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /circle-masters : get all the circleMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of circleMasters in body
     */
    @GetMapping("/circle-masters")
    @Timed
    public List<CircleMasterDTO> getAllCircleMasters() {
        log.debug("REST request to get all CircleMasters");
        return circleMasterService.findAll();
    }

    /**
     * GET  /circle-masters/:id : get the "id" circleMaster.
     *
     * @param id the id of the circleMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the circleMasterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/circle-masters/{id}")
    @Timed
    public ResponseEntity<CircleMasterDTO> getCircleMaster(@PathVariable UUID id) {
        log.debug("REST request to get CircleMaster : {}", id);
        Optional<CircleMasterDTO> circleMasterDTO = circleMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(circleMasterDTO);
    }

    /**
     * DELETE  /circle-masters/:id : delete the "id" circleMaster.
     *
     * @param id the id of the circleMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/circle-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteCircleMaster(@PathVariable UUID id) {
        log.debug("REST request to delete CircleMaster : {}", id);
        circleMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
