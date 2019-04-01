package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.NurseryMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.NurseryMasterDTO;
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
 * REST controller for managing NurseryMaster.
 */
@RestController
@RequestMapping("/api")
public class NurseryMasterResource {

    private final Logger log = LoggerFactory.getLogger(NurseryMasterResource.class);

    private static final String ENTITY_NAME = "nurseryMaster";

    private final NurseryMasterService nurseryMasterService;

    public NurseryMasterResource(NurseryMasterService nurseryMasterService) {
        this.nurseryMasterService = nurseryMasterService;
    }

    /**
     * POST  /nursery-masters : Create a new nurseryMaster.
     *
     * @param nurseryMasterDTO the nurseryMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nurseryMasterDTO, or with status 400 (Bad Request) if the nurseryMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nursery-masters")
    @Timed
    public ResponseEntity<NurseryMasterDTO> createNurseryMaster(@Valid @RequestBody NurseryMasterDTO nurseryMasterDTO) throws URISyntaxException {
        log.debug("REST request to save NurseryMaster : {}", nurseryMasterDTO);
        if (nurseryMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new nurseryMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        nurseryMasterDTO.setId(UUID.randomUUID());
        NurseryMasterDTO result = nurseryMasterService.save(nurseryMasterDTO);
        return ResponseEntity.created(new URI("/api/nursery-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nursery-masters : Updates an existing nurseryMaster.
     *
     * @param nurseryMasterDTO the nurseryMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nurseryMasterDTO,
     * or with status 400 (Bad Request) if the nurseryMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the nurseryMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nursery-masters")
    @Timed
    public ResponseEntity<NurseryMasterDTO> updateNurseryMaster(@Valid @RequestBody NurseryMasterDTO nurseryMasterDTO) throws URISyntaxException {
        log.debug("REST request to update NurseryMaster : {}", nurseryMasterDTO);
        if (nurseryMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NurseryMasterDTO result = nurseryMasterService.save(nurseryMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nurseryMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nursery-masters : get all the nurseryMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nurseryMasters in body
     */
    @GetMapping("/nursery-masters")
    @Timed
    public List<NurseryMasterDTO> getAllNurseryMasters() {
        log.debug("REST request to get all NurseryMasters");
        return nurseryMasterService.findAll();
    }

    /**
     * GET  /nursery-masters/:id : get the "id" nurseryMaster.
     *
     * @param id the id of the nurseryMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nurseryMasterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nursery-masters/{id}")
    @Timed
    public ResponseEntity<NurseryMasterDTO> getNurseryMaster(@PathVariable UUID id) {
        log.debug("REST request to get NurseryMaster : {}", id);
        Optional<NurseryMasterDTO> nurseryMasterDTO = nurseryMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nurseryMasterDTO);
    }

    /**
     * DELETE  /nursery-masters/:id : delete the "id" nurseryMaster.
     *
     * @param id the id of the nurseryMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nursery-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteNurseryMaster(@PathVariable UUID id) {
        log.debug("REST request to delete NurseryMaster : {}", id);
        nurseryMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
