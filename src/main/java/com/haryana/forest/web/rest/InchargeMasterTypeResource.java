package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.service.InchargeMasterTypeService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
import com.haryana.forest.service.dto.InchargeMasterTypeDTO;
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
 * REST controller for managing InchargeMasterType.
 */
@RestController
@RequestMapping("/api")
public class InchargeMasterTypeResource {

    private final Logger log = LoggerFactory.getLogger(InchargeMasterTypeResource.class);

    private static final String ENTITY_NAME = "inchargeMasterType";

    private final InchargeMasterTypeService inchargeMasterTypeService;

    public InchargeMasterTypeResource(InchargeMasterTypeService inchargeMasterTypeService) {
        this.inchargeMasterTypeService = inchargeMasterTypeService;
    }

    /**
     * POST  /incharge-master-types : Create a new inchargeMasterType.
     *
     * @param inchargeMasterTypeDTO the inchargeMasterTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inchargeMasterTypeDTO, or with status 400 (Bad Request) if the inchargeMasterType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/incharge-master-types")
    @Timed
    public ResponseEntity<InchargeMasterTypeDTO> createInchargeMasterType(@Valid @RequestBody InchargeMasterTypeDTO inchargeMasterTypeDTO) throws URISyntaxException {
        log.debug("REST request to save InchargeMasterType : {}", inchargeMasterTypeDTO);
        if (inchargeMasterTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new inchargeMasterType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        inchargeMasterTypeDTO.setId(UUID.randomUUID());
        InchargeMasterTypeDTO result = inchargeMasterTypeService.save(inchargeMasterTypeDTO);
        return ResponseEntity.created(new URI("/api/incharge-master-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /incharge-master-types : Updates an existing inchargeMasterType.
     *
     * @param inchargeMasterTypeDTO the inchargeMasterTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inchargeMasterTypeDTO,
     * or with status 400 (Bad Request) if the inchargeMasterTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the inchargeMasterTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/incharge-master-types")
    @Timed
    public ResponseEntity<InchargeMasterTypeDTO> updateInchargeMasterType(@Valid @RequestBody InchargeMasterTypeDTO inchargeMasterTypeDTO) throws URISyntaxException {
        log.debug("REST request to update InchargeMasterType : {}", inchargeMasterTypeDTO);
        if (inchargeMasterTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InchargeMasterTypeDTO result = inchargeMasterTypeService.save(inchargeMasterTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, inchargeMasterTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /incharge-master-types : get all the inchargeMasterTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inchargeMasterTypes in body
     */
    @GetMapping("/incharge-master-types")
    @Timed
    public List<InchargeMasterTypeDTO> getAllInchargeMasterTypes() {
        log.debug("REST request to get all InchargeMasterTypes");
        return inchargeMasterTypeService.findAll();
    }

    /**
     * GET  /incharge-master-types/:id : get the "id" inchargeMasterType.
     *
     * @param id the id of the inchargeMasterTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inchargeMasterTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/incharge-master-types/{id}")
    @Timed
    public ResponseEntity<InchargeMasterTypeDTO> getInchargeMasterType(@PathVariable UUID id) {
        log.debug("REST request to get InchargeMasterType : {}", id);
        Optional<InchargeMasterTypeDTO> inchargeMasterTypeDTO = inchargeMasterTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inchargeMasterTypeDTO);
    }

    /**
     * DELETE  /incharge-master-types/:id : delete the "id" inchargeMasterType.
     *
     * @param id the id of the inchargeMasterTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/incharge-master-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteInchargeMasterType(@PathVariable UUID id) {
        log.debug("REST request to delete InchargeMasterType : {}", id);
        inchargeMasterTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
