package com.haryana.forest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.haryana.forest.domain.BlockMaster;
import com.haryana.forest.service.BlockMasterService;
import com.haryana.forest.web.rest.errors.BadRequestAlertException;
import com.haryana.forest.web.rest.util.HeaderUtil;
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
 * REST controller for managing BlockMaster.
 */
@RestController
@RequestMapping("/api")
public class BlockMasterResource {

    private final Logger log = LoggerFactory.getLogger(BlockMasterResource.class);

    private static final String ENTITY_NAME = "blockMaster";

    private final BlockMasterService blockMasterService;

    public BlockMasterResource(BlockMasterService blockMasterService) {
        this.blockMasterService = blockMasterService;
    }

    /**
     * POST  /block-masters : Create a new blockMaster.
     *
     * @param blockMaster the blockMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new blockMaster, or with status 400 (Bad Request) if the blockMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/block-masters")
    @Timed
    public ResponseEntity<BlockMaster> createBlockMaster(@Valid @RequestBody BlockMaster blockMaster) throws URISyntaxException {
        log.debug("REST request to save BlockMaster : {}", blockMaster);
        if (blockMaster.getId() != null) {
            throw new BadRequestAlertException("A new blockMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        blockMaster.setId(UUID.randomUUID());
        BlockMaster result = blockMasterService.save(blockMaster);
        return ResponseEntity.created(new URI("/api/block-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /block-masters : Updates an existing blockMaster.
     *
     * @param blockMaster the blockMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated blockMaster,
     * or with status 400 (Bad Request) if the blockMaster is not valid,
     * or with status 500 (Internal Server Error) if the blockMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/block-masters")
    @Timed
    public ResponseEntity<BlockMaster> updateBlockMaster(@Valid @RequestBody BlockMaster blockMaster) throws URISyntaxException {
        log.debug("REST request to update BlockMaster : {}", blockMaster);
        if (blockMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlockMaster result = blockMasterService.save(blockMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, blockMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /block-masters : get all the blockMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of blockMasters in body
     */
    @GetMapping("/block-masters")
    @Timed
    public List<BlockMaster> getAllBlockMasters() {
        log.debug("REST request to get all BlockMasters");
        return blockMasterService.findAll();
    }

    /**
     * GET  /block-masters/:id : get the "id" blockMaster.
     *
     * @param id the id of the blockMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the blockMaster, or with status 404 (Not Found)
     */
    @GetMapping("/block-masters/{id}")
    @Timed
    public ResponseEntity<BlockMaster> getBlockMaster(@PathVariable UUID id) {
        log.debug("REST request to get BlockMaster : {}", id);
        Optional<BlockMaster> blockMaster = blockMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blockMaster);
    }

    /**
     * DELETE  /block-masters/:id : delete the "id" blockMaster.
     *
     * @param id the id of the blockMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/block-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteBlockMaster(@PathVariable UUID id) {
        log.debug("REST request to delete BlockMaster : {}", id);
        blockMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
