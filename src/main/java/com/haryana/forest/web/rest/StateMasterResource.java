package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.StateMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.StateMasterDTO;
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
 * REST controller for managing StateMaster.
 */
@RestController
@RequestMapping("/api")
public class StateMasterResource {

    private final Logger log = LoggerFactory.getLogger(StateMasterResource.class);

    private static final String ENTITY_NAME = "stateMaster";

    private final StateMasterService stateMasterService;

    public StateMasterResource(StateMasterService stateMasterService) {
        this.stateMasterService = stateMasterService;
    }

    /**
     * POST  /state-masters : Create a new stateMaster.
     *
     * @param stateMasterDTO the stateMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stateMasterDTO, or with status 400 (Bad Request) if the stateMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/state-masters")
    @Timed
    public ResponseEntity<StateMasterDTO> createStateMaster(@Valid @RequestBody StateMasterDTO stateMasterDTO) throws URISyntaxException {
        log.debug("REST request to save StateMaster : {}", stateMasterDTO);
        if (stateMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new stateMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        stateMasterDTO.setId(UUID.randomUUID());
        StateMasterDTO result = stateMasterService.save(stateMasterDTO);
        return ResponseEntity.created(new URI("/api/state-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /state-masters : Updates an existing stateMaster.
     *
     * @param stateMasterDTO the stateMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stateMasterDTO,
     * or with status 400 (Bad Request) if the stateMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the stateMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/state-masters")
    @Timed
    public ResponseEntity<StateMasterDTO> updateStateMaster(@Valid @RequestBody StateMasterDTO stateMasterDTO) throws URISyntaxException {
        log.debug("REST request to update StateMaster : {}", stateMasterDTO);
        if (stateMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StateMasterDTO result = stateMasterService.save(stateMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stateMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /state-masters : get all the stateMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of stateMasters in body
     */
    @GetMapping("/state-masters")
    @Timed
    public List<StateMasterDTO> getAllStateMasters() {
        log.debug("REST request to get all StateMasters");
        return stateMasterService.findAll();
    }

    /**
     * GET  /state-masters/:id : get the "id" stateMaster.
     *
     * @param id the id of the stateMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stateMasterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/state-masters/{id}")
    @Timed
    public ResponseEntity<StateMasterDTO> getStateMaster(@PathVariable UUID id) {
        log.debug("REST request to get StateMaster : {}", id);
        Optional<StateMasterDTO> stateMasterDTO = stateMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stateMasterDTO);
    }

    /**
     * DELETE  /state-masters/:id : delete the "id" stateMaster.
     *
     * @param id the id of the stateMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/state-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteStateMaster(@PathVariable UUID id) {
        log.debug("REST request to delete StateMaster : {}", id);
        stateMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
