package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.BeatMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.BeatMasterDTO;
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
 * REST controller for managing BeatMaster.
 */
@RestController
@RequestMapping("/api")
public class BeatMasterResource {

    private final Logger log = LoggerFactory.getLogger(BeatMasterResource.class);

    private static final String ENTITY_NAME = "beatMaster";

    private final BeatMasterService beatMasterService;

    public BeatMasterResource(BeatMasterService beatMasterService) {
        this.beatMasterService = beatMasterService;
    }

    /**
     * POST  /beat-masters : Create a new beatMaster.
     *
     * @param beatMasterDTO the beatMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new beatMasterDTO, or with status 400 (Bad Request) if the beatMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/beat-masters")
    @Timed
    public ResponseEntity<BeatMasterDTO> createBeatMaster(@Valid @RequestBody BeatMasterDTO beatMasterDTO) throws URISyntaxException {
        log.debug("REST request to save BeatMaster : {}", beatMasterDTO);
        if (beatMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new beatMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        beatMasterDTO.setId(UUID.randomUUID());
        BeatMasterDTO result = beatMasterService.save(beatMasterDTO);
        return ResponseEntity.created(new URI("/api/beat-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /beat-masters : Updates an existing beatMaster.
     *
     * @param beatMasterDTO the beatMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated beatMasterDTO,
     * or with status 400 (Bad Request) if the beatMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the beatMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/beat-masters")
    @Timed
    public ResponseEntity<BeatMasterDTO> updateBeatMaster(@Valid @RequestBody BeatMasterDTO beatMasterDTO) throws URISyntaxException {
        log.debug("REST request to update BeatMaster : {}", beatMasterDTO);
        if (beatMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BeatMasterDTO result = beatMasterService.save(beatMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, beatMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /beat-masters : get all the beatMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of beatMasters in body
     */
    @GetMapping("/beat-masters")
    @Timed
    public List<BeatMasterDTO> getAllBeatMasters() {
        log.debug("REST request to get all BeatMasters");
        return beatMasterService.findAll();
    }

    /**
     * GET  /beat-masters/:id : get the "id" beatMaster.
     *
     * @param id the id of the beatMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the beatMasterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/beat-masters/{id}")
    @Timed
    public ResponseEntity<BeatMasterDTO> getBeatMaster(@PathVariable UUID id) {
        log.debug("REST request to get BeatMaster : {}", id);
        Optional<BeatMasterDTO> beatMasterDTO = beatMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beatMasterDTO);
    }

    /**
     * DELETE  /beat-masters/:id : delete the "id" beatMaster.
     *
     * @param id the id of the beatMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/beat-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteBeatMaster(@PathVariable UUID id) {
        log.debug("REST request to delete BeatMaster : {}", id);
        beatMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
