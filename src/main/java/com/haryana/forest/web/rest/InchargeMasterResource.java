package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.InchargeMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.InchargeMasterDTO;
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
 * REST controller for managing InchargeMaster.
 */
@RestController
@RequestMapping("/api")
public class InchargeMasterResource {

    private final Logger log = LoggerFactory.getLogger(InchargeMasterResource.class);

    private static final String ENTITY_NAME = "inchargeMaster";

    private final InchargeMasterService inchargeMasterService;

    public InchargeMasterResource(InchargeMasterService inchargeMasterService) {
        this.inchargeMasterService = inchargeMasterService;
    }

    /**
     * POST  /incharge-masters : Create a new inchargeMaster.
     *
     * @param inchargeMasterDTO the inchargeMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inchargeMasterDTO, or with status 400 (Bad Request) if the inchargeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/incharge-masters")
    @Timed
    public ResponseEntity<InchargeMasterDTO> createInchargeMaster(@Valid @RequestBody InchargeMasterDTO inchargeMasterDTO) throws URISyntaxException {
        log.debug("REST request to save InchargeMaster : {}", inchargeMasterDTO);
        if (inchargeMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new inchargeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        inchargeMasterDTO.setId(UUID.randomUUID());
        InchargeMasterDTO result = inchargeMasterService.save(inchargeMasterDTO);
        return ResponseEntity.created(new URI("/api/incharge-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /incharge-masters : Updates an existing inchargeMaster.
     *
     * @param inchargeMasterDTO the inchargeMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inchargeMasterDTO,
     * or with status 400 (Bad Request) if the inchargeMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the inchargeMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/incharge-masters")
    @Timed
    public ResponseEntity<InchargeMasterDTO> updateInchargeMaster(@Valid @RequestBody InchargeMasterDTO inchargeMasterDTO) throws URISyntaxException {
        log.debug("REST request to update InchargeMaster : {}", inchargeMasterDTO);
        if (inchargeMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InchargeMasterDTO result = inchargeMasterService.save(inchargeMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, inchargeMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /incharge-masters : get all the inchargeMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inchargeMasters in body
     */
    @GetMapping("/incharge-masters")
    @Timed
    public List<InchargeMasterDTO> getAllInchargeMasters() {
        log.debug("REST request to get all InchargeMasters");
        return inchargeMasterService.findAll();
    }

    /**
     * GET  /incharge-masters/:id : get the "id" inchargeMaster.
     *
     * @param id the id of the inchargeMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inchargeMasterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/incharge-masters/{id}")
    @Timed
    public ResponseEntity<InchargeMasterDTO> getInchargeMaster(@PathVariable UUID id) {
        log.debug("REST request to get InchargeMaster : {}", id);
        Optional<InchargeMasterDTO> inchargeMasterDTO = inchargeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inchargeMasterDTO);
    }

    /**
     * DELETE  /incharge-masters/:id : delete the "id" inchargeMaster.
     *
     * @param id the id of the inchargeMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/incharge-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteInchargeMaster(@PathVariable UUID id) {
        log.debug("REST request to delete InchargeMaster : {}", id);
        inchargeMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
