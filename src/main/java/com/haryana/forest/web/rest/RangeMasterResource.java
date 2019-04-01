package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.RangeMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.RangeMasterDTO;
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
 * REST controller for managing RangeMaster.
 */
@RestController
@RequestMapping("/api")
public class RangeMasterResource {

    private final Logger log = LoggerFactory.getLogger(RangeMasterResource.class);

    private static final String ENTITY_NAME = "rangeMaster";

    private final RangeMasterService rangeMasterService;

    public RangeMasterResource(RangeMasterService rangeMasterService) {
        this.rangeMasterService = rangeMasterService;
    }

    /**
     * POST  /range-masters : Create a new rangeMaster.
     *
     * @param rangeMasterDTO the rangeMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rangeMasterDTO, or with status 400 (Bad Request) if the rangeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/range-masters")
    @Timed
    public ResponseEntity<RangeMasterDTO> createRangeMaster(@Valid @RequestBody RangeMasterDTO rangeMasterDTO) throws URISyntaxException {
        log.debug("REST request to save RangeMaster : {}", rangeMasterDTO);
        if (rangeMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new rangeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rangeMasterDTO.setId(UUID.randomUUID());
        RangeMasterDTO result = rangeMasterService.save(rangeMasterDTO);
        return ResponseEntity.created(new URI("/api/range-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /range-masters : Updates an existing rangeMaster.
     *
     * @param rangeMasterDTO the rangeMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rangeMasterDTO,
     * or with status 400 (Bad Request) if the rangeMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the rangeMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/range-masters")
    @Timed
    public ResponseEntity<RangeMasterDTO> updateRangeMaster(@Valid @RequestBody RangeMasterDTO rangeMasterDTO) throws URISyntaxException {
        log.debug("REST request to update RangeMaster : {}", rangeMasterDTO);
        if (rangeMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RangeMasterDTO result = rangeMasterService.save(rangeMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rangeMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /range-masters : get all the rangeMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rangeMasters in body
     */
    @GetMapping("/range-masters")
    @Timed
    public List<RangeMasterDTO> getAllRangeMasters() {
        log.debug("REST request to get all RangeMasters");
        return rangeMasterService.findAll();
    }

    /**
     * GET  /range-masters/:id : get the "id" rangeMaster.
     *
     * @param id the id of the rangeMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rangeMasterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/range-masters/{id}")
    @Timed
    public ResponseEntity<RangeMasterDTO> getRangeMaster(@PathVariable UUID id) {
        log.debug("REST request to get RangeMaster : {}", id);
        Optional<RangeMasterDTO> rangeMasterDTO = rangeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rangeMasterDTO);
    }

    /**
     * DELETE  /range-masters/:id : delete the "id" rangeMaster.
     *
     * @param id the id of the rangeMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/range-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteRangeMaster(@PathVariable UUID id) {
        log.debug("REST request to delete RangeMaster : {}", id);
        rangeMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
