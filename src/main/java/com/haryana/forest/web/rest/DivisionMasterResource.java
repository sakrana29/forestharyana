package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.DivisionMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.DivisionMasterDTO;
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
 * REST controller for managing DivisionMaster.
 */
@RestController
@RequestMapping("/api")
public class DivisionMasterResource {

    private final Logger log = LoggerFactory.getLogger(DivisionMasterResource.class);

    private static final String ENTITY_NAME = "divisionMaster";

    private final DivisionMasterService divisionMasterService;

    public DivisionMasterResource(DivisionMasterService divisionMasterService) {
        this.divisionMasterService = divisionMasterService;
    }

    /**
     * POST  /division-masters : Create a new divisionMaster.
     *
     * @param divisionMasterDTO the divisionMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new divisionMasterDTO, or with status 400 (Bad Request) if the divisionMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/division-masters")
    @Timed
    public ResponseEntity<DivisionMasterDTO> createDivisionMaster(@Valid @RequestBody DivisionMasterDTO divisionMasterDTO) throws URISyntaxException {
        log.debug("REST request to save DivisionMaster : {}", divisionMasterDTO);
        if (divisionMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new divisionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        divisionMasterDTO.setId(UUID.randomUUID());
        DivisionMasterDTO result = divisionMasterService.save(divisionMasterDTO);
        return ResponseEntity.created(new URI("/api/division-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /division-masters : Updates an existing divisionMaster.
     *
     * @param divisionMasterDTO the divisionMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated divisionMasterDTO,
     * or with status 400 (Bad Request) if the divisionMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the divisionMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/division-masters")
    @Timed
    public ResponseEntity<DivisionMasterDTO> updateDivisionMaster(@Valid @RequestBody DivisionMasterDTO divisionMasterDTO) throws URISyntaxException {
        log.debug("REST request to update DivisionMaster : {}", divisionMasterDTO);
        if (divisionMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DivisionMasterDTO result = divisionMasterService.save(divisionMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, divisionMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /division-masters : get all the divisionMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of divisionMasters in body
     */
    @GetMapping("/division-masters")
    @Timed
    public List<DivisionMasterDTO> getAllDivisionMasters() {
        log.debug("REST request to get all DivisionMasters");
        return divisionMasterService.findAll();
    }

    /**
     * GET  /division-masters/:id : get the "id" divisionMaster.
     *
     * @param id the id of the divisionMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the divisionMasterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/division-masters/{id}")
    @Timed
    public ResponseEntity<DivisionMasterDTO> getDivisionMaster(@PathVariable UUID id) {
        log.debug("REST request to get DivisionMaster : {}", id);
        Optional<DivisionMasterDTO> divisionMasterDTO = divisionMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(divisionMasterDTO);
    }

    /**
     * DELETE  /division-masters/:id : delete the "id" divisionMaster.
     *
     * @param id the id of the divisionMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/division-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteDivisionMaster(@PathVariable UUID id) {
        log.debug("REST request to delete DivisionMaster : {}", id);
        divisionMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
