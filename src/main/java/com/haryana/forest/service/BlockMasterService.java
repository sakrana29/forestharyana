package com.haryana.forest.service;

import com.haryana.forest.domain.BlockMaster;
import com.haryana.forest.repository.BlockMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing BlockMaster.
 */
@Service
public class BlockMasterService {

    private final Logger log = LoggerFactory.getLogger(BlockMasterService.class);

    private final BlockMasterRepository blockMasterRepository;

    public BlockMasterService(BlockMasterRepository blockMasterRepository) {
        this.blockMasterRepository = blockMasterRepository;
    }

    /**
     * Save a blockMaster.
     *
     * @param blockMaster the entity to save
     * @return the persisted entity
     */
    public BlockMaster save(BlockMaster blockMaster) {
        log.debug("Request to save BlockMaster : {}", blockMaster);
        return blockMasterRepository.save(blockMaster);
    }

    /**
     * Get all the blockMasters.
     *
     * @return the list of entities
     */
    public List<BlockMaster> findAll() {
        log.debug("Request to get all BlockMasters");
        return blockMasterRepository.findAll();
    }


    /**
     * Get one blockMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<BlockMaster> findOne(UUID id) {
        log.debug("Request to get BlockMaster : {}", id);
        return blockMasterRepository.findById(id);
    }

    /**
     * Delete the blockMaster by id.
     *
     * @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete BlockMaster : {}", id);
        blockMasterRepository.deleteById(id);
    }
}
